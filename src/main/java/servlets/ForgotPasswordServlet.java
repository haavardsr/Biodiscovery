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


@WebServlet(name = "ForgotPasswordServlet", urlPatterns = {"/forgotpassword"})
public class ForgotPasswordServlet extends Servlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        super.setCSRF(request);
        response.setContentType("text/html;charset=utf-8");
        request.setAttribute("title", "Glemt passord");
        request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        request.setAttribute("title", "Glemt passord");

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
                        "Tilbakestille password",
                        "<h2>Trykk på linken for å tilbakestille ditt passord </h2><a href='http://localhost:8081/resetpassword?key="+ token +"'>http://localhost:8081/restpassword?key=hei</a>");

                session.setAttribute("success", "Vi har nå sendt en epost med instruksjoner for å tilbakestille passordet ditt.");

            } catch (Exception e) {
                e.printStackTrace();
                session.setAttribute("error", "Noe gikk galt! vennligst prøv igjen.");

            }


        } else {
            session.setAttribute("error", "Vi fant ingen bruker med denne e-post adressen. Vennligst sjekk at du angir riktig e-post adresse.");
        }
        request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);



    }

}
