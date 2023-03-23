package com.urise.webapp;

import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;
import com.urise.webapp.model.TextSection;

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
//        resume.addSection(SectionType.ACHIEVEMENT, new ListSection("achievements1",
//                "achievements2", "achievements3"));
//        resume.addSection(SectionType.QUALIFICATIONS, new ListSection("qualification1",
//                "qualification2", "qualification3"));
//
//        resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(
//                new Organization("experience1", "http://experience1.ru/",
//                    new Organization.Period(2013, Month.OCTOBER, "title1", "description1")),
//                new Organization("experience2", "http://experience2.ru/",
//                    new Organization.Period(2014, Month.OCTOBER, 2016, Month.JANUARY,
//                        "title2", "description2"))));
//
//        resume.addSection(SectionType.EDUCATION, new OrganizationSection(
//                new Organization("education1", "http://www.education1.ru/",
//                    new Organization.Period(1997, Month.SEPTEMBER, 1998, Month.MARCH,
//                        "Студент", "6 месяцев обучения цифровым телефонным сетям (Москва)")),
//                new Organization("education2",
//                "http://www.education2.ru/",
//                    new Organization.Period(1993, Month.SEPTEMBER, 1996, Month.JULY,
//                        "Аспирант", "Аспирантура (программист С, С++)"),
//                    new Organization.Period(1987, Month.SEPTEMBER, 1993, Month.JULY,
//                        "Студент", "Инженер (программист Fortran, C)"))));

        return resume;
    }

    public static void main(String[] args) {

        Resume testResume = createResume("uuid1", "Серж Иванов");
        System.out.println(testResume);
        System.out.println(testResume.getContacts());
        System.out.println(testResume.getSections());
    }
}
