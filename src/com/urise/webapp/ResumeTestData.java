package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.Month;
import java.util.UUID;

public class ResumeTestData {
    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final String UUID_4 = UUID.randomUUID().toString();

    public static final Resume R1;
    public static final Resume R2;
    public static final Resume R3;
    public static final Resume R4;

    public static final String UUID_NOT_EXIST = "dummy";

    static {
        R1 = ResumeTestData.createResume(UUID_1, "Name1");
//        R2 = ResumeTestData.createResume(UUID_2, "Name2");
//        R3 = ResumeTestData.createResume(UUID_3, "Name3");
//        R4 = ResumeTestData.createResume(UUID_4, "Name4");

//        R1 = new Resume(UUID_1, "Name1");
        R2 = new Resume(UUID_2, "Name2");
        R3 = new Resume(UUID_3, "Name3");
        R4 = new Resume(UUID_4, "Name4");

        R2.setContact(ContactType.MAIL, "mail2@gmail.ru");
        R3.setContact(ContactType.MAIL, "mail3@ya.ru");
        R4.setContact(ContactType.PHONE, "44444");
        R4.setContact(ContactType.SKYPE, "Skype");
    }

    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.setContact(ContactType.PHONE, "+7(921) 123-4567");
        resume.setContact(ContactType.SKYPE, "skype:serg.ivanov");
        resume.setContact(ContactType.MAIL, "s.ivanov@yandex.ru");
        resume.setContact(ContactType.LINKEDIN, "https://www.linkedin.com/sivanov/");
        resume.setContact(ContactType.GITHUB, "https://github.com/sivanov");
        resume.setContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        resume.setContact(ContactType.HOME_PAGE, "http://sivanov.ru/");

        resume.setSection(SectionType.OBJECTIVE, new TextSection("Objective"));
        resume.setSection(SectionType.PERSONAL, new TextSection("Personal Text"));
        resume.setSection(SectionType.ACHIEVEMENT, new ListSection("achievements1",
                "achievements2", "achievements3"));
        resume.setSection(SectionType.QUALIFICATIONS, new ListSection("qualification1",
                "qualification2", "qualification3"));

        resume.setSection(SectionType.EXPERIENCE, new OrganizationSection(
                new Organization("experience1", "http://experience1.ru/",
                    new Organization.Period(2013, Month.OCTOBER, "title1", "description1")),
                new Organization("experience2", "http://experience2.ru/",
                    new Organization.Period(2014, Month.OCTOBER, 2016, Month.JANUARY,
                        "title2", "description2"))));

        resume.setSection(SectionType.EDUCATION, new OrganizationSection(
                new Organization("education1",null,
                    new Organization.Period(1997, Month.SEPTEMBER, 1998, Month.MARCH,
                        "Студент", "6 месяцев обучения цифровым телефонным сетям (Москва)")),
                new Organization("education2",
                "http://www.education2.ru/",
                    new Organization.Period(1993, Month.SEPTEMBER, 1996, Month.JULY,
                        "Аспирант", "Аспирантура (программист С, С++)"),
                    new Organization.Period(1987, Month.SEPTEMBER, 1993, Month.JULY,
                        "Студент", "Инженер (программист Fortran, C)"))));

        return resume;
    }
}
