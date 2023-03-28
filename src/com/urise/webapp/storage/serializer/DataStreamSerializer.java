package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class DataStreamSerializer implements StreamSerializer {

    String str;

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());

            writeWithException(r.getContacts().entrySet(), dos, contactTypeStringEntry -> {
                dos.writeUTF(contactTypeStringEntry.getKey().name());
                dos.writeUTF(contactTypeStringEntry.getValue());
            });

            writeWithException(r.getSections().entrySet(), dos, sectionTypeSectionEntry -> {
                Section section = sectionTypeSectionEntry.getValue();
                SectionType sectionType = SectionType.valueOf(sectionTypeSectionEntry.getKey().name());
                dos.writeUTF(sectionType.name());

                switch (sectionType) {
                    case OBJECTIVE, PERSONAL -> dos.writeUTF(((TextSection) section).getText());
                    case ACHIEVEMENT, QUALIFICATIONS ->
                            writeWithException(((ListSection) section).getItems(), dos, dos::writeUTF);
                    case EXPERIENCE, EDUCATION ->
                            writeWithException(((OrganizationSection) section).getOrganizations(), dos, organization -> {
                                dos.writeUTF(organization.getName());
                                dos.writeUTF((str = organization.getWebSite()) == null ? "null" : str);
                                writeWithException(organization.getPeriods(), dos, period -> {
                                    dos.writeUTF(period.getStartDate().toString());
                                    dos.writeUTF(period.getEndDate().toString());
                                    dos.writeUTF(period.getTitle());
                                    dos.writeUTF((str = period.getDescription()) == null ? "null" : str);
                                });
                            });
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            Resume resume = new Resume(dis.readUTF(), dis.readUTF());
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            size = dis.readInt();
            for (int i = 0; i < size; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case OBJECTIVE, PERSONAL ->
                            resume.addSection(sectionType, new TextSection(dis.readUTF()));
                    case ACHIEVEMENT, QUALIFICATIONS ->
                            resume.addSection(sectionType, new ListSection(readWithException(dis, dis::readUTF)));
                    case EDUCATION, EXPERIENCE ->
                            resume.addSection(sectionType, new OrganizationSection(
                                    readWithException(dis, () -> new Organization(
                                            dis.readUTF(), (str = dis.readUTF()).equals("null") ? null : str
                                            , readWithException(dis, () -> new Organization.Period(
                                                    getParse(dis), getParse(dis), dis.readUTF()
                                            , (str = dis.readUTF()).equals("null") ? null : str))))));
                }
            }
            return resume;
        }
    }

    private LocalDate getParse(DataInputStream dis) throws IOException {
        return LocalDate.parse(dis.readUTF());
    }

    private <T> void writeWithException(Collection<T> collect, DataOutputStream dos, ConsumerWrite<T> action) throws IOException {
        Objects.requireNonNull(collect);
        Objects.requireNonNull(dos);
        Objects.requireNonNull(action);
        dos.writeInt(collect.size());
        for (T t : collect) {
            action.write(t);
        }
    }

    private <T> List<T> readWithException(DataInputStream dis, ConsumerRead<T> action) throws IOException {
        Objects.requireNonNull(dis);
        Objects.requireNonNull(action);
        List<T> consumerList = new ArrayList<>();
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            consumerList.add(action.read());
        }
        return consumerList;
    }

    @FunctionalInterface
    private interface ConsumerWrite<T> {
        void write(T t) throws IOException;
    }

    @FunctionalInterface
    private interface ConsumerRead<T> {
        T read() throws IOException;
    }
}
