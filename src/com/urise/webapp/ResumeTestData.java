package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ResumeTestData {

    public static final Resume R1;
    public static final Resume R2;
    public static final Resume R3;

    static {
        R1 = new Resume("uuid1", "Name 1");
        R2 = new Resume("uuid2", "Name 2");
        R3 = new Resume("uuid3", "Name 3");

        R1.addContact(ContactType.MOBILE_PHONE, "+7(921) 855-0482");
        R1.addContact(ContactType.SKYPE, "skype:grigory.kislin");
        R1.addContact(ContactType.MAIL, "gkislin@yandex.ru");
        R1.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        R1.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        R1.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        R1.addContact(ContactType.HOME_PAGE, "http://gkislin.ru/");
        R1.addSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и " +
                "корпоративного обучения по Java Web и Enterprise технологиям"));
        R1.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, " +
                "сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        R1.addSection(SectionType.ACHIEVEMENT, new ListSection("Achievements1",
                "Achievements2", "Achievements3"));
        R1.addSection(SectionType.QUALIFICATIONS, new ListSection("qualification1",
                "qualification2", "Qualification3"));
        R1.addSection(SectionType.EXPERIENCE, new OrganizationSection(new Organization("experience1", "http://experience1.ru/",
                new Organization.Period(2013, Month.OCTOBER,
                        "title1", "description1"))));
        R1.addSection(SectionType.EXPERIENCE, new OrganizationSection(new Organization("experience2", "http://experience2.ru/",
                new Organization.Period(2014, Month.OCTOBER,2016, Month.JANUARY,
                        "title2", "description2"))));
        R1.addSection(SectionType.EDUCATION, new OrganizationSection(new Organization("education1", "http://www.education1.ru/",
                new Organization.Period(1997, Month.SEPTEMBER, 1998, Month.MARCH,
                        "Студент", "6 месяцев обучения цифровым телефонным сетям (Москва)"))));
        R1.addSection(SectionType.EDUCATION, new OrganizationSection(new Organization("education2",
                "http://www.education2.ru/",
                new Organization.Period(1993, Month.SEPTEMBER, 1996, Month.JULY,
                        "Аспирант", "Аспирантура (программист С, С++)"),
                new Organization.Period(1987, Month.SEPTEMBER, 1993, Month.JULY,
                        "Студент", "Инженер (программист Fortran, C)"))));
    }

    public static void main(String[] args) {
//        Resume R1 = new Resume("uuid1", "Григорий Кислин");
//        Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
//        Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);
//        List<String> achievements = new ArrayList<>();
//        List<String> qualifications = new ArrayList<>();
//        List<Organization> organizationList = new ArrayList<>();
//        List<Organization> educationList = new ArrayList<>();

//        contacts.put(ContactType.MOBILE_PHONE, "+7(921) 855-0482");
//        contacts.put(ContactType.MOBILE_PHONE, "+7(921) 855-0482");
//        contacts.put(ContactType.SKYPE, "grigory.kislin");
//        contacts.put(ContactType.MAIL, "gkislin@yandex.ru");
//        contacts.put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
//        contacts.put(ContactType.GITHUB, "https://github.com/gkislin");
//        contacts.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
//        contacts.put(ContactType.HOME_PAGE, "http://gkislin.ru/");
//
//        sections.put(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного " +
//                "обучения по Java Web и Enterprise технологиям"));
//        sections.put(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика," +
//                " креативность, инициативность. Пурист кода и архитектуры."));
//
//        achievements.add("achievement1");
//        achievements.add("achievement2");
//        achievements.add("achievement3");
//
//        sections.put(SectionType.ACHIEVEMENT, new ListSection(achievements));
//
//        qualifications.add("qualification1");
//        qualifications.add("qualification2");
//        qualifications.add("qualification3");
//
//        sections.put(SectionType.QUALIFICATIONS, new ListSection(qualifications));
//
//        organizationList.add(new Organization("Java Online Projects", "http://javaops.ru/",
//                new Organization.Period(2013, Month.OCTOBER,
//                        "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.")));
//        organizationList.add(new Organization("Wrike", "https://www.wrike.com/",
//                new Organization.Period(2014, Month.OCTOBER,2016, Month.JANUARY,
//                        "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.")));
//
//        sections.put(SectionType.EXPERIENCE, new OrganizationSection(organizationList));
//
//        educationList.add(new Organization("Alcatel", "http://www.alcatel.ru/",
//                new Organization.Period(1997, Month.SEPTEMBER, 1998, Month.MARCH,
//                        "Студент", "6 месяцев обучения цифровым телефонным сетям (Москва)")));
//        educationList.add(new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
//                "http://www.itmo.ru/",
//                new Organization.Period(1993, Month.SEPTEMBER, 1996, Month.JULY,
//                        "Аспирант", "Аспирантура (программист С, С++)"),
//                new Organization.Period(1987, Month.SEPTEMBER, 1993, Month.JULY,
//                        "Студент", "Инженер (программист Fortran, C)")));
//
//        sections.put(SectionType.EDUCATION, new OrganizationSection(educationList));

        System.out.println(R1);
        System.out.println(R1.getContacts());
        System.out.println(R1.getSections());
//        System.out.println(contacts);
//        System.out.println(sections);
//        System.out.println(organizationList);
//        System.out.println(educationList);
    }
}
