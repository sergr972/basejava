<%@ page import="com.urise.webapp.model.ContactType" %>
<%@ page import="com.urise.webapp.model.Resume" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/theme/light.css">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/resume-list-styles.css">
    <title>Список всех резюме</title>
</head>
<body>
<div class="themes">
    <div class="theme-title">Тема</div>
    <div class="theme-selector">
        <form action="" method="GET">
            <select name="theme" onchange="this.form.submit()">
                <option value="light" selected>Светлая</option>
                <option value="dark" >Темная</option>
                <option value="purple" >Фиолетовая</option>
            </select>
        </form>
    </div>
</div>

<div class="header">
    <a class="no-underline-anchor" href="resume?theme=light">
        <div class="arrow-dot">
            <img src="img/left_arrow.svg" alt="">
        </div>
    </a>
    <a class="text-anchor" href="resume?theme=light">
        <span class="resumes-control-title">Управление резюме</span>
    </a>
</div>

<div class="scrollable-panel">
    <div class="table-wrapper">
        <div class="add-resume">
            <a class="no-underline-anchor" href="resume?action=add&theme=light">
                <img src="img/light/add-person.svg" alt="">
            </a>
            <a class="text-anchor" href="resume?action=add&theme=light">
                <p class="add-resume-title">Добавить резюме</p>
            </a>
        </div>
        <div class="resumes-list">
            <table>

                <tr class="t-header">
                    <th class="name-column">Имя</th>
                    <th class="info-column">Контакты</th>
                    <th class="img-column">Редактировать</th>
                    <th class="img-column">Удалить</th>
                </tr>
                <jsp:useBean id="resumes" scope="request" type="java.util.List"/>

                <c:forEach items="${resumes}" var="resume">
                    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume"/>
                <tr class="t-body">
                    <td class="name-column">
                        <a class="contact-link"
                           href="resume?uuid=${resume.uuid}&action=view&theme=light">${resume.fullName}</a>
                    </td>
                    <td class="info-column">
                        <a class="contact-link" href='mailto:ContactType.MAIL.toHtml(resume.getContact(ContactType.MAIL))'></a>
                    </td>
                    <td class="img-column">
                        <a class="no-underline-anchor" href="resume?uuid=${resume.uuid}&action=edit&theme=light">
                            <img src="img/light/edit.svg" alt="">
                        </a>
                    </td>
                    <td class="img-column">
                        <a class="no-underline-anchor" href="resume?uuid=${resume.uuid}&action=delete">
                            <img src="img/delete.png" alt="">
                        </a>
                    </td>
                </tr>
                </c:forEach>

<%--                <c:forEach items="${resumes}" var="resume">--%>
<%--                <tr class="t-body">--%>
<%--                    <td class="name-column">--%>
<%--                        <a class="contact-link"--%>
<%--                           href="resume?uuid=${resume.uuid}&action=view&theme=light">${resume.fullName}</a>--%>
<%--                    </td>--%>
<%--                    <td class="info-column">--%>
<%--                        <a class="contact-link" href='mailto:ContactType.MAIL.toHtml(resume.getContact(ContactType.MAIL))'>${resume.fullName}</a>--%>
<%--                    </td>--%>
<%--                    <td class="img-column">--%>
<%--                        <a class="no-underline-anchor" href="resume?uuid=${resume.uuid}&action=edit&theme=light">--%>
<%--                            <img src="img/light/edit.svg" alt="">--%>
<%--                        </a>--%>
<%--                    </td>--%>
<%--                    <a class="no-underline-anchor" href="resume?uuid=${resume.uuid}&action=delete">--%>
<%--                        <img src="img/delete.png" alt="">--%>
<%--                    </a>--%>
<%--                </tr>--%>
<%--                </c:forEach>--%>
            </table>
        </div>
    </div>
</div>

<div class="footer">
    <a class="footer-text-anchor" href="http://javaops.ru/reg/basejava">
        <div>Проект: разработка web-приложения «База данных резюме»</div>
    </a>
</div>

</body>
</html>
