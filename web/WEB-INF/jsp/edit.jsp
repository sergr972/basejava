<%@ page import="com.urise.webapp.model.ContactType" %>
<%@ page import="com.urise.webapp.model.SectionType" %>
<%@ page import="com.urise.webapp.model.ListSection" %>
<%@ page import="com.urise.webapp.model.OrganizationSection" %>
<%@ page import="com.urise.webapp.Util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/theme/${theme}.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/edit-style.css">
    <jsp:useBean id="resume" scope="request" type="com.urise.webapp.model.Resume"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<form method="post" action="resume" enctype="application/x-www-form-urlencoded">
    <input type="hidden" name="uuid" value="${resume.uuid}">
    <input type="hidden" name="theme" value="${theme}">
    <div class="scrollable-panel">
        <div class="form-wrapper">
            <div class="section">ФИО</div>
            <input class="field" type="text" name="fullName" size=55 placeholder="ФИО" value="${resume.fullName}" required>

            <div class="section">Контакты</div>

            <c:forEach var="type" items="<%=ContactType.values()%>">
                <input class="field" type="text" name="${type.name()}" size=30 placeholder="${type.title}"
                       value="${resume.getContact(type)}">
            </c:forEach>

            <div class="spacer"></div>

            <div class="section">Секции</div>

            <c:forEach var="type" items="<%=SectionType.values()%>">
                <c:set var="section" value="${resume.getSection(type)}"/>
                <jsp:useBean id="section" type="com.urise.webapp.model.Section"/>
                <div class="field-label">${type.title}</div>
                <c:choose>
                    <c:when test="${type=='OBJECTIVE' || type=='PERSONAL'}">
                        <textarea class="field" name='${type}'><%=section%></textarea>
                    </c:when>
                    <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                        <textarea class="field" name='${type}'><%=String.join("\n", ((ListSection) section).getItems())%></textarea>
                    </c:when>
                    <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                        <c:forEach var="org" items="<%=((OrganizationSection) section).getOrganizations()%>" varStatus="counter">
                            <c:choose>
                                <c:when test="${counter.index == 0}">
                                </c:when>
                                <c:otherwise>
                                    <div class="spacer"></div>
                                </c:otherwise>
                            </c:choose>

                            <input class="field" type="text" placeholder="Название учереждения" name='${type}' size=100 value="${org.name}">
                            <input class="field" type="text" placeholder="Сайт учереждения:" name='${type}website' size=100 value="${org.webSite}">

                            <c:forEach var="pos" items="${org.periods}">
                                <jsp:useBean id="pos" type="com.urise.webapp.model.Organization.Period"/>

                                <div class="date-section">
                                    <input class="field date" name="${type}${counter.index}startDate"
                                           placeholder="Начальная дата: ММ/ГГГГ"
                                           size=10
                                           value="<%=DateUtil.format(pos.getStartDate())%>">
                                    <input class="field date date-margin" name="${type}${counter.index}endDate"
                                           placeholder="Конечная дата: ММ/ГГГГ"
                                           size=10
                                           value="<%=DateUtil.format(pos.getEndDate())%>">
                                </div>

                                <input class="field" type="text" placeholder="Должность/Статус"
                                       name='${type}${counter.index}title' size=75
                                       value="${pos.title}">
                                <textarea class="field" placeholder="Описание" name="${type}${counter.index}description">${pos.description}</textarea>

                            </c:forEach>
                        </c:forEach>
                    </c:when>
                </c:choose>
            </c:forEach>

            <div class="spacer"></div>

            <div class="button-section">
                <button class="red-cancel-button" onclick="window.history.back()">Отменить</button>
                <button class="green-submit-button" type="submit">Сохранить</button>
            </div>

        </div>
    </div>
</form>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>