package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = new Resume("uuid1", "Григорий Кислин");
        Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
        Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);
        List<String> achievements = new ArrayList<>();
        List<String> qualifications = new ArrayList<>();
        List<Organization> organizationList = new ArrayList<>();
        List<Organization> educationList = new ArrayList<>();

        contacts.put(ContactType.MOBILE_PHONE, "+7(921) 855-0482");
        contacts.put(ContactType.MOBILE_PHONE, "+7(921) 855-0482");
        contacts.put(ContactType.SKYPE, "grigory.kislin");
        contacts.put(ContactType.MAIL, "gkislin@yandex.ru");
        contacts.put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        contacts.put(ContactType.GITHUB, "https://github.com/gkislin");
        contacts.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        contacts.put(ContactType.HOME_PAGE, "http://gkislin.ru/");

        sections.put(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного " +
                "обучения по Java Web и Enterprise технологиям"));
        sections.put(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика," +
                " креативность, инициативность. Пурист кода и архитектуры."));

        achievements.add("Организация команды и успешная реализация Java проектов для сторонних " +
                "заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, " +
                "система мониторинга показателей спортсменов на Spring Boot, " +
                "участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет.");
        achievements.add("С 2013 года: разработка проектов \"Разработка Web приложения\"," +
                "\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). " +
                "Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". " +
                "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achievements.add("Реализация двухфакторной аутентификации для онлайн платформы управления " +
                "проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievements.add(" Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. " +
                "Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления " +
                "окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и " +
                "авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        achievements.add("Реализация c нуля Rich Internet Application приложения на стеке " +
                "технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, " +
                "Highstock для алгоритмического трейдинга.");
        achievements.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия " +
                "слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). " +
                "Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. " +
                "Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        achievements.add("Реализация протоколов по приему платежей всех основных платежных " +
                "системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");

        sections.put(SectionType.ACHIEVEMENT, new ListSection(achievements));

        qualifications.add("JEE AS: GlassFish (v2.1, v3), " +
                "OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.add("* Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualifications.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle");
        qualifications.add("MySQL, SQLite, MS SQL, HSQLDB");
        qualifications.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        qualifications.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");
        qualifications.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, " +
                "MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), " +
                "Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT," +
                " JUnit, Selenium (htmlelements).");
        qualifications.add("Python: Django.");
        qualifications.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualifications.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualifications.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, " +
                "StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet," +
                " HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        qualifications.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix.");
        qualifications.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher," +
                " Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        qualifications.add("Отличное знание и опыт применения концепций ООП, SOA, " +
                "шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
        qualifications.add("Родной русский, английский \"upper intermediate\"");

        sections.put(SectionType.QUALIFICATIONS, new ListSection(qualifications));

        organizationList.add(new Organization("Java Online Projects", "http://javaops.ru/",
                new Organization.Period(LocalDate.of(2013, 10, 1), LocalDate.now(),
                        "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.")));
        organizationList.add(new Organization("Wrike", "https://www.wrike.com/",
                new Organization.Period(LocalDate.of(2014, 10, 1), LocalDate.of(2016, 1, 1),
                        "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.")));
        organizationList.add(new Organization("RIT Center", null,
                new Organization.Period(LocalDate.of(2012, 4, 1), LocalDate.of(2014, 10, 1),
                        "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python")));
        organizationList.add(new Organization("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/",
                new Organization.Period(LocalDate.of(2010, 12, 1), LocalDate.of(2012, 4, 1),
                        "Ведущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.")));
        organizationList.add(new Organization("Yota", "https://www.yota.ru/",
                new Organization.Period(LocalDate.of(2008, 6, 1), LocalDate.of(2010, 12, 1),
                        "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)")));
        organizationList.add(new Organization("Enkata", "http://enkata.com/",
                new Organization.Period(LocalDate.of(2007, 3, 1), LocalDate.of(2008, 6, 1),
                        "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).")));
        organizationList.add(new Organization("Siemens AG", "https://www.siemens.com/ru/ru/home.html",
                new Organization.Period(LocalDate.of(2005, 1, 1), LocalDate.of(2007, 2, 1),
                        "Разработчик ПО", "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).")));
        organizationList.add(new Organization("Alcatel", "http://www.alcatel.ru/",
                new Organization.Period(LocalDate.of(1997, 9, 1), LocalDate.of(2005, 1, 1),
                        "Инженер по аппаратному и программному тестированию",
                        "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).")));

        sections.put(SectionType.EXPERIENCE, new OrganizationSection(educationList));

        educationList.add(new Organization("Coursera", "https://www.coursera.org/course/progfun",
                new Organization.Period(LocalDate.of(2013, 3, 1), LocalDate.of(2013, 5, 1),
                        "Студент", "\"Functional Programming Principles in Scala\" by Martin Odersky")));
        educationList.add(new Organization("Luxoft", "http://www.luxoft-training.ru",
                new Organization.Period(LocalDate.of(2011, 3, 1), LocalDate.of(2011, 4, 1),
                        "Студент", "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"")));
        educationList.add(new Organization("Siemens AG", "http://www.siemens.ru/",
                new Organization.Period(LocalDate.of(2005, 1, 1), LocalDate.of(2005, 4, 1),
                        "Студент", "3 месяца обучения мобильным IN сетям (Берлин)")));
        educationList.add(new Organization("Alcatel", "http://www.alcatel.ru/",
                new Organization.Period(LocalDate.of(1997, 9, 1), LocalDate.of(1998, 3, 1),
                        "Студент", "6 месяцев обучения цифровым телефонным сетям (Москва)")));
        educationList.add(new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                "http://www.ifmo.ru/",
                new Organization.Period(LocalDate.of(1993, 9, 1), LocalDate.of(1996, 7, 1),
                        "Аспирант", "Аспирантура (программист С, С++)")));
        educationList.add(new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                "http://www.ifmo.ru/",
                new Organization.Period(LocalDate.of(1987, 9, 1), LocalDate.of(1993, 7, 1),
                        "Студент", "Инженер (программист Fortran, C)")));
        educationList.add(new Organization("Заочная физико-техническая школа при МФТИ", "https://mipt.ru/",
                new Organization.Period(LocalDate.of(1984, 9, 1), LocalDate.of(1987, 6, 1),
                        "Студент", "Закончил с отличием")));

        sections.put(SectionType.EDUCATION, new OrganizationSection(educationList));

        System.out.println(resume);
        System.out.println(contacts);
        System.out.println(sections);
        System.out.println(organizationList);
        System.out.println(educationList);
    }
}
