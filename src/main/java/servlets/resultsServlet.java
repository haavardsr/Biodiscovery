package servlets;

import com.google.gson.Gson;
import utils.DBFunctions;
import utils.DBUtils;
import utils.HtmlHelper;
import utils.Validation;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.awt.*;
import java.io.File;
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
import java.util.logging.Logger;


@WebServlet(name = "results", urlPatterns = {"/results/antismash"})
public class resultsServlet extends Servlet {
    private static final String UPLOAD_DIR = "usr/local/tomcat/webapps/results/antismash";
    Logger logger = Logger.getLogger(resultsServlet.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        request.setAttribute("title", "results");

        super.setCSRF(request);
        if (Validation.isAuthenticated(request)) {

        } else {
            request.getSession().setAttribute("error", "You need to login to access other pages");
            response.sendRedirect("login");
        }
    }
}