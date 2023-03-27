package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());

            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            Map<SectionType, Section> sectionMap = r.getSections();
            dos.writeInt(sectionMap.size());
            for (Map.Entry<SectionType, Section> entry : sectionMap.entrySet()) {
                SectionType sectionType = SectionType.valueOf(entry.getKey().name());
                dos.writeUTF(sectionType.name());
                Section section = entry.getValue();

                switch (sectionType) {
                    case OBJECTIVE, PERSONAL -> dos.writeUTF(((TextSection) section).getText());

                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        List<String> items = ((ListSection) section).getItems();
                        dos.writeInt(items.size());
                        for (String item : items) {
                            dos.writeUTF(item);
                        }
                    }
                    case EXPERIENCE, EDUCATION -> {
                        List<Organization> organizations = ((OrganizationSection) section).getOrganizations();
                        dos.writeInt(organizations.size());
                        for (Organization organization : organizations) {
                            dos.writeUTF(organization.getName());
                            dos.writeUTF(organization.getWebSite() == null? "null":organization.getWebSite());
                            List<Organization.Period> periods = organization.getPeriods();
                            dos.writeInt(periods.size());
                            for (Organization.Period period : periods) {
                                dos.writeUTF(period.getStartDate().toString());
                                dos.writeUTF(period.getEndDate().toString());
                                dos.writeUTF(period.getTitle());
                                dos.writeUTF(period.getDescription()== null ? "null" : period.getDescription());
                            }
                        }
                    }
                }
            }
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
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        int countItem = dis.readInt();
                        List<String> items = new ArrayList<>(countItem);
                        for (int j = 0; j < countItem; j++) {
                            items.add(dis.readUTF());
                        }
                        resume.addSection(sectionType, new ListSection(items));
                    }
                    case EDUCATION, EXPERIENCE -> {
                        int countOrganization = dis.readInt();
                        List<Organization> organizations = new ArrayList<>(countOrganization);
                        for (int j = 0; j < countOrganization; j++) {
                            String name = dis.readUTF();
                            String website = dis.readUTF();
                            List<Organization.Period> periods = new ArrayList<>();
                            int countPeriods = dis.readInt();
                            for (int k = 0; k < countPeriods; k++) {
                                String str;
                                periods.add(new Organization.Period(LocalDate.parse(dis.readUTF())
                                        , LocalDate.parse(dis.readUTF())
                                        , dis.readUTF(), (str = dis.readUTF()).equals("null") ? null : str
                                ));
                            }
                            organizations.add(new Organization(name, website.equals("null")?null: website, periods));
                        }
                        resume.addSection(sectionType, new OrganizationSection(organizations));
                    }
                }
            }
            return resume;
        }
    }
}
