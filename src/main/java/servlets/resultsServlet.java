package servlets;

import com.google.gson.Gson;
import utils.DBFunctions;
import utils.DBUtils;
import utils.HtmlHelper;
import utils.Validation;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import models.User;
import utils.DBFunctions;
import utils.DBUtils;
import utils.EmailClient;
import utils.Validation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;



@WebServlet(name = "results", urlPatterns = {"/results"})
public class resultsServlet extends Servlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        request.setAttribute("title", "results");
        //PrintWriter out = response.getWriter();
        //HtmlHelper.writeHtmlStart(out, "Results");
        //HtmlHelper.writeHtmlEnd(out);

        super.setCSRF(request);
        if (Validation.isAuthenticated(request)) {

            request.getRequestDispatcher("/usr/local/tomcat/ResultsDIR/minionsample2/analysis/antismash/index.html").forward(request, response);
        } else {
            request.getSession().setAttribute("error", "You need to login to access other pages");
            response.sendRedirect("login");
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        User user = DBFunctions.getUser(email);
        if (user != null) {
        }

    }

}