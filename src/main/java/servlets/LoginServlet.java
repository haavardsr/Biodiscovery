package servlets;

import utils.Validation;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;



@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends Servlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        super.setCSRF(request);
        response.setContentType("text/html;charset=utf-8");

        request.setAttribute("title", "Log inn");

        request.getRequestDispatcher("login.jsp").forward(request, response);




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        if(super.verifiyCSRF(request)){

            String email = (String) request.getParameter("email"); /*Taking the input of mail and pw*/
            String password = (String) request.getParameter("password");
            HttpSession session = request.getSession();
            Cookie c = new Cookie("email",email);
            response.addCookie(c);

            if(Validation.validateUser(email, password)){
                session.setAttribute("email", email);/*saving  the attribute for later*/
                session.setAttribute("error", null);
                response.sendRedirect("/fileUpload");
            }else{
                session.setAttribute("error", "Incorrect email or password");
                session.setAttribute("email", null);
                request.getRequestDispatcher("login.jsp").forward(request, response);

            }

        }else{
            response.sendRedirect("login");

        }


    }




}
