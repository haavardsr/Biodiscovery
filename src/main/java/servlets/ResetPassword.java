package servlets;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.SignatureException;
import models.User;
import utils.DBFunctions;
import utils.DBUtils;
import utils.SecureUtils;
import utils.Validation;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;


@WebServlet(name = "ResetPassword", urlPatterns = {"/resetpassword"})
public class ResetPassword extends Servlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        super.setCSRF(request);
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        request.setAttribute("title", "Reset password");
        String token = (String) request.getParameter("key");
        try {

            Jws<Claims> claims = Validation.decodeJWT(token);
            String email = claims.getBody().getId();
            User user = DBFunctions.getUser(email);
            if(user != null){
                request.getRequestDispatcher("resetpassword.jsp").forward(request, response);

            }else{
                PrintWriter pw = response.getWriter();
                pw.println("wrong reset token");
            }
        }catch(ExpiredJwtException e){
            PrintWriter pw = response.getWriter();
            pw.println("This link has expired. Reset link is valid for 24 hours. Try sending a new forgotten password request");

        }catch(SignatureException e){
            PrintWriter pw = response.getWriter();
            pw.println("error reset token");

        }




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        request.setAttribute("title", "Forgot password");


        String password1 = (String) request.getParameter("password1");
        String password2 = (String) request.getParameter("password2");
        String email = (String) request.getParameter("email");

        HttpSession session = request.getSession();
        if(Validation.validatePasswords(password1, password2)){

            String salt = SecureUtils.makeSalt();
            String password = SecureUtils.makePassword(password1,salt);
            PreparedStatement ps;
            Connection db;

            String query = "update user set password=?, salt=?, verificationKey='' where email=?";

            try {
                db = DBUtils.getINSTANCE().getConnection();
                ps = db.prepareStatement(query);
                ps.setString(1, password);
                ps.setString(2, salt);
                ps.setString(3, email);
                ps.execute();
                session.setAttribute("success", "Your password has now been reset. <a href='login'>Login.</a>");
            } catch (Exception e) {
                e.printStackTrace();
            }




        }else{
            session.setAttribute("error", "The passwords do not match");

        }
        request.getRequestDispatcher("resetpassword.jsp").forward(request, response);





    }


}
