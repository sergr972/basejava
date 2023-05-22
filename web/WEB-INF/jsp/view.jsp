<%@ page import="com.urise.webapp.model.ListSection" %>
<%@ page import="com.urise.webapp.model.OrganizationSection" %>
<%@ page import="com.urise.webapp.model.TextSection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.urise.webapp.Util.HtmlUtil" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" scope="request" type="com.urise.webapp.model.Resume"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>
    <p>
    <p>
    <hr>
    <table cellpadding="2">
        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<com.urise.webapp.model.SectionType, com.urise.webapp.model.Section>"/>
            <c:set var="type" value="${sectionEntry.key}"/>
            <c:set var="section" value="${sectionEntry.value}"/>
            <jsp:useBean id="section" type="com.urise.webapp.model.Section"/>
        <tr>
            <td colspan="2"><h2><a name="type.name">${type.title}</a></h2></td>
        </tr>
        <c:choose>
        <c:when test="${type=='OBJECTIVE'}">
        <tr>
            <td colspan="2">
                <h3><%=((TextSection) section).getText()%>
                </h3>
            </td>
        </tr>
        </c:when>
        <c:when test="${type=='PERSONAL'}">
        <tr>
            <td colspan="2">
                <%=((TextSection) section).getText()%>
            </td>
        </tr>
        </c:when>
        <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
        <tr>
            <td colspan="2">
                <ul>
                    <c:forEach var="item" items="<%=((ListSection) section).getItems()%>">
                        <li>${item}</li>
                    </c:forEach>
                </ul>
            </td>
        </tr>
        </c:when>
        <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
        <c:forEach var="org" items="<%=((OrganizationSection) section).getOrganizations()%>">
        <tr>
            <td colspan="2">
                <c:choose>
                    <c:when test="${empty org.webSite}">
                        <h3>${org.webSite}</h3>
                    </c:when>
                    <c:otherwise>
                        <h3><a href="${org.webSite}">${org.webSite}</a></h3>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <c:forEach var="period" items="${org.periods}">
            <jsp:useBean id="period" type="com.urise.webapp.model.Organization.Period"/>
        <tr>
            <td width="15%" style="vertical-align: top"><%=HtmlUtil.formatDates(period)%>
            </td>
            <td><b>${period.title}</b><br>${period.description}</td>
        </tr>
        </c:forEach>
        </c:forEach>
        </c:when>
        </c:choose>
        </c:forEach>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>

