package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import com.urise.webapp.sql.SqlHelper;

import java.sql.*;
import java.util.*;

public class SqlStorage implements Storage {
    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.transactionalExecute(conn -> {
            Resume r;
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume WHERE uuid=?");
                 PreparedStatement psContacts = conn.prepareStatement("SELECT * FROM contact WHERE resume_uuid=?");
                 PreparedStatement psSections = conn.prepareStatement("SELECT * FROM section WHERE resume_uuid=?")) {

                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                r = new Resume(uuid, rs.getString("full_name"));

                psContacts.setString(1, uuid);
                ResultSet rsContacts = psContacts.executeQuery();
                while (rsContacts.next()) {
                    String cType = rsContacts.getString("type");
                    r.addContact(ContactType.valueOf(cType), rsContacts.getString("value"));
                }

                psSections.setString(1, uuid);
                ResultSet rsSections = psSections.executeQuery();

                while (rsSections.next()) {
                    addSection(rsSections, r);
                }
            }
            return r;
        });
    }

    @Override
    public void update(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, r.getFullName());
                ps.setString(2, r.getUuid());
                if (ps.executeUpdate() != 1) {
                    throw new NotExistStorageException(r.getUuid());
                }
            }
            deleteContacts(conn, r);
            deleteSections(conn, r);
            insertContact(conn, r);
            insertSection(conn, r);
            return null;
        });
    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                        ps.setString(1, r.getUuid());
                        ps.setString(2, r.getFullName());
                        ps.execute();
                    }
                    insertContact(conn, r);
                    insertSection(conn, r);
                    return null;
                }
        );
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume WHERE uuid=?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.transactionalExecute(conn -> {
            Map<String, Resume> resumes = new LinkedHashMap<>();

            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume r ORDER BY full_name, uuid");
                 PreparedStatement psContacts = conn.prepareStatement("SELECT * FROM contact");
                 PreparedStatement psSections = conn.prepareStatement("SELECT * FROM section")) {

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    resumes.put(uuid, new Resume(uuid, rs.getString("full_name")));
                }

                ResultSet rsContacts = psContacts.executeQuery();
                while (rsContacts.next()) {
                    resumes.get(rsContacts.getString("resume_uuid"))
                            .addContact(ContactType.valueOf(rsContacts.getString("type")),
                                    rsContacts.getString("value"));
                }

                ResultSet rsSections = psSections.executeQuery();
                while (rsSections.next()) {

                    addSection(rsSections, resumes.get(rsSections.getString("resume_uuid")));
                }

                return new ArrayList<>(resumes.values());
            }
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(*) FROM resume", st -> {
            ResultSet rs = st.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private void insertContact(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("" +
                "INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> entry : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, entry.getKey().name());
                ps.setString(3, entry.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertSection(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("" +
                "INSERT INTO section (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<SectionType, Section> entry : r.getSections().entrySet()) {
                ps.setString(1, r.getUuid());
                SectionType sectionType = entry.getKey();
                ps.setString(2, sectionType.name());
                Section section = entry.getValue();
                String value = switch (sectionType) {
                    case OBJECTIVE, PERSONAL -> ((TextSection) section).getText();
                    case ACHIEVEMENT, QUALIFICATIONS -> String.join("\n", ((ListSection) section).getItems());
                    default -> "";
                };
                ps.setString(3, value);
                ps.addBatch();
            }
            ps.executeBatch();
        }

    }

    private void deleteContacts(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM contact WHERE resume_uuid=?")) {
            ps.setString(1, r.getUuid());
            ps.execute();
        }
    }

    private void deleteSections(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM section WHERE resume_uuid=?")) {
            ps.setString(1, r.getUuid());
            ps.execute();
        }
    }

    private void addSection(ResultSet rs, Resume r) throws SQLException {
        SectionType type = SectionType.valueOf(rs.getString("type"));
        Section section = switch (type) {
            case PERSONAL, OBJECTIVE -> new TextSection(rs.getString("value"));
            case ACHIEVEMENT, QUALIFICATIONS -> new ListSection(new ArrayList<>(Arrays.asList(
                    rs.getString("value").split("\n"))));
            default -> throw new IllegalStateException("Unknown Section Type");
        };
        r.addSection(type, section);
    }
}