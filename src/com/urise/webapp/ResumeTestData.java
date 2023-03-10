package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.Month;

public class ResumeTestData {

    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.addContact(ContactType.MOBILE_PHONE, "+7(921) 123-4567");
        resume.addContact(ContactType.SKYPE, "skype:serg.ivanov");
        resume.addContact(ContactType.MAIL, "s.ivanov@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/sivanov/");
        resume.addContact(ContactType.GITHUB, "https://github.com/sivanov");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        resume.addContact(ContactType.HOME_PAGE, "http://sivanov.ru/");
        resume.addSection(SectionType.OBJECTIVE, new TextSection("Objective"));
        resume.addSection(SectionType.PERSONAL, new TextSection("Personal Text"));
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection("achievements1",
                "achievements2", "achievements3"));
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection("qualification1",
                "qualification2", "qualification3"));
        resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(new Organization("experience1", "http://experience1.ru/",
                new Organization.Period(2013, Month.OCTOBER,
                        "title1", "description1"))));
        resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(new Organization("experience2", "http://experience2.ru/",
                new Organization.Period(2014, Month.OCTOBER, 2016, Month.JANUARY,
                        "title2", "description2"))));
        resume.addSection(SectionType.EDUCATION, new OrganizationSection(new Organization("education1", "http://www.education1.ru/",
                new Organization.Period(1997, Month.SEPTEMBER, 1998, Month.MARCH,
                        "Студент", "6 месяцев обучения цифровым телефонным сетям (Москва)"))));
        resume.addSection(SectionType.EDUCATION, new OrganizationSection(new Organization("education2",
                "http://www.education2.ru/",
                new Organization.Period(1993, Month.SEPTEMBER, 1996, Month.JULY,
                        "Аспирант", "Аспирантура (программист С, С++)"),
                new Organization.Period(1987, Month.SEPTEMBER, 1993, Month.JULY,
                        "Студент", "Инженер (программист Fortran, C)"))));
        return resume;
    }

    public static void main(String[] args) {
//        Resume resume = new Resume("uuid1", "Григорий Кислин");
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

        Resume testResume = createResume("uuid1", "Иван Иванов");
        System.out.println(testResume);
        System.out.println(testResume.getContacts());
        System.out.println(testResume.getSections());

//        System.out.println(contacts);
//        System.out.println(sections);
//        System.out.println(organizationList);
//        System.out.println(educationList);
    }
}
