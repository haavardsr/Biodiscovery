package servlets;

import org.apache.commons.codec.EncoderException;
import utils.CSRF;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;


/**
 * A servlet filter for filtering all requests for checking:
 * 1. The CSRF-token exists in the post request
 * 2. Add the CSRF-token to the request session on login get request
 * */
public class Servlet extends HttpServlet {
    public synchronized boolean verifiyCSRF(HttpServletRequest request){
        HttpSession session = request.getSession();
        String method = request.getMethod();


        if (method.equals("POST")) {
            String paramCsrf = request.getParameter("csrf");
            String sessionCsrf = (String) session.getAttribute("csrf");
            System.out.println("Session: "+ sessionCsrf);
            System.out.println("request: "+ paramCsrf);
            if (sessionCsrf == null || paramCsrf == null) {
                return false;
            } else {
                return sessionCsrf.equals(paramCsrf);
            }
        }
        return false;

    }
    public synchronized void setCSRF(HttpServletRequest request) {


        HttpSession session = request.getSession();
        String method = request.getMethod();


        if (method.equals("GET")) {
            try {
                session.setAttribute("csrf", CSRF.getToken());

            } catch (NoSuchAlgorithmException | EncoderException e) {
                e.printStackTrace();
            }


        }
    }
}
