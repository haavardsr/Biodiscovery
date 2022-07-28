package servlets;

import utils.Validation;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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