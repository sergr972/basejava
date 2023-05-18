package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResumeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Storage storage = Config.get().getStorage();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Type","text/html: charset=UTF-8");
        response.setContentType("text/html; charset=UTF-8");
//        String name = request.getParameter("name");
        response.getWriter().write("<table border=\"1\" align=\"center\">\n" +
                "<caption>Resumes</caption>\n" +
                "<tr align=\"center\">\n" +
                "<td>UUID</td>\n" +
                "<td>Name</td>\n" +
                "</tr>\n");
        for (Resume resume : storage.getAllSorted()) {
            response.getWriter().write("<tr align=\"center\">\n" +
                    "<td>" + resume.getUuid() + "</td>" +
                    "<td>" + resume.getFullName() + "</td>" +
                    "</tr>\n");
        }
        response.getWriter().write("</table>");
    }
}
