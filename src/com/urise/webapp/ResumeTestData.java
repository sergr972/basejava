package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.UUID;

public class ResumeTestData {

    public static Resume R1;

    public static final String UUID1 = UUID.randomUUID().toString();

    static {
        R1 = new Resume(UUID1, "Григорий Кислин");
        R1.addContact(ContactType.MOBILE_PHONE, "+7(921) 855-0482");
        R1.addContact(ContactType.SKYPE, "skype:grigory.kislin");
        R1.addContact(ContactType.MAIL, "gkislin@yandex.ru");
        R1.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        R1.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        R1.addContact(ContactType.STATCKOVERFLOW, "https://stackoverflow.com/users/548473");
        R1.addContact(ContactType.HOME_PAGE, "http://gkislin.ru/");
        R1.addSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и " +
                "корпоративного обучения по Java Web и Enterprise технологиям"));
        R1.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, " +
                "сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        R1.addSection(SectionType.ACHIEVEMENT, new ListSection("Achievements1",
                "Achievements2", "Achievements"));
        R1.addSection(SectionType.QUALIFICATIONS, new ListSection("JEE AS: " +
                "GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce", "Qualification3"));
        R1.addSection(SectionType.EXPERIENCE, new OrganizationSection(new Organization("Java Online Projects", "http://javaops.ru/)",
                new Organization.Period(LocalDate.of(2013, 10, 1),
                LocalDate.now(), "Автор проекта.",
                "Создание, организация и проведение Java онлайн проектов и стажировок."))));
        R1.addSection(SectionType.EDUCATION, new OrganizationSection(new Organization(
                "Coursera","https://www.coursera.org/course/progfun",
                new Organization.Period(LocalDate.of(2013, 3, 1),
                        LocalDate.of(2013, 5, 1),
                        "'Functional Programming Principles in Scala' by Martin Odersky", ""))));
    }

    public static void main(String[] args) {
        System.out.println(R1);
        System.out.println(R1.getContacts());
        System.out.println(R1.getSections());
    }
}
