package servlets;


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

/**
 * Forgot Password servlet. This will see if the user has a email registered in the Database and sends an email with a token included in the link. can reset password or use the link if token is not included
 */

@WebServlet(name = "ForgotPasswordServlet", urlPatterns = {"/forgotpassword"})
public class ForgotPasswordServlet extends Servlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        super.setCSRF(request);
        response.setContentType("text/html;charset=utf-8");
        request.setAttribute("title", "Forgot Password");
        request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        request.setAttribute("title", "Forgot Password");

        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        User user = DBFunctions.getUser(email);
        if (user != null) {

            Connection db = null;
            PreparedStatement ps;

            try {
                db = DBUtils.getINSTANCE().getConnection();
                String token = Validation.createToken(email);


                String q = "update user set verificationKey=? where email=?";
                ps = db.prepareStatement(q);
                ps.setString(1,token);
                ps.setString(2,user.getEmail());
                EmailClient.sendAsHtml(user.getEmail(),
                        "Reset password",
                        "<h2>Click on the link to reset your password </h2><a href='http://localhost:8081/resetpassword?key="+ token +"'>http://localhost:8081/restpassword?key=hei</a>");

                session.setAttribute("success", "We have now sent an email with instructions to reset your password.");

            } catch (Exception e) {
                e.printStackTrace();
                session.setAttribute("error", "Something went wrong! please try again.");

            }


        } else {
            session.setAttribute("error", "We did not find any users with this e-mail address. Please check that you enter the correct email address.");
        }
        request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);



    }

}
