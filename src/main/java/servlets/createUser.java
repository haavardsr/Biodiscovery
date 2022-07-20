package servlets;


import com.google.gson.Gson;
import utils.DBFunctions;
import utils.DBUtils;
import utils.Validation;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;


@WebServlet(name = "createUser", urlPatterns = {"/createUser"})
public class createUser extends Servlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        request.setAttribute("title", "legg til Bruker");
        super.setCSRF(request);
        if (Validation.isSuperUser(request)) {
            request.getRequestDispatcher("createUser.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("error", "Du må logge inn for å få tilgang til andre sider");
            response.sendRedirect("login");
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();

        Gson json = new Gson();
        PrintWriter pw = response.getWriter();
        String finalResult;


        if (Validation.isSuperUser(request) && super.verifiyCSRF(request)) {
            String first_name = request.getParameter("first_name");
            String last_name = request.getParameter("last_name");
            String email = (request.getParameter("email")).toLowerCase();
            String tlf = (request.getParameter("tlf"));
            String superuser = (request.getParameter("is_superuser"));
            if (superuser == null) {
                superuser = "off";
            }
            String password1 = request.getParameter("password1");
            String password2 = request.getParameter("password2");

            String[] selected = request.getParameterValues("types");


            ArrayList<Integer> types = new ArrayList<>();
            if (selected != null) {
                for (String type_Id : selected) {
                    types.add(Integer.parseInt(type_Id));
                }
            }

            if (first_name.equals("")) {
                finalResult = json.toJson(DBFunctions.singleKeyValueToJson("error", "Ugyldig fornavn"));
                pw.print(finalResult);
                pw.close();
            } else {
                if (last_name.equals("")) {
                    finalResult = json.toJson(DBFunctions.singleKeyValueToJson("error", "Ugyldig etternavn"));
                    pw.print(finalResult);
                    pw.close();
                } else {
                    if (email.equals("")) {
                        finalResult = json.toJson(DBFunctions.singleKeyValueToJson("error", "Ugyldig e-post addresse"));
                        pw.print(finalResult);
                        pw.close();
                    } else {

                        if (!Validation.validatePasswords(password1, password2)) {
                            finalResult = json.toJson(DBFunctions.singleKeyValueToJson("error", "Passordene stemmer ikke med hverandre! De må være helt like. Prøv igjen"));
                            pw.print(finalResult);
                            pw.close();


                        } else {
                            Connection db = null;
                            PreparedStatement ps;
                            try {
                                DBFunctions.storeEmployee(first_name, last_name, email, tlf, superuser, password1);

                                String q = "select last_insert_id() as user_id";
                                db = DBUtils.getINSTANCE().getConnection();
                                ;
                                ps = db.prepareStatement(q);
                                ResultSet rs = ps.executeQuery();
                                int userId = 0;
                                if (rs.next()) {
                                    userId = rs.getInt("user_id");
                                }

                                if (userId != 0) {

                                    String query2 = "insert into (user_id) values (?,?,?) on duplicate KEY UPDATE user_id=?";
                                    ps = db.prepareStatement(query2);
                                    for (int i : types) {
                                        ps.setInt(1, userId);
                                        ps.setInt(2, userId);
                                        ps.execute();
                                    }

                                }
                                finalResult = json.toJson(DBFunctions.singleKeyValueToJson("success", "Bruker ble opprettet!"));
                                pw.print(finalResult);
                                pw.close();


                            } catch (SQLException | ClassNotFoundException e) {

                                e.printStackTrace();
                                if (e instanceof SQLIntegrityConstraintViolationException) {

                                    finalResult = json.toJson(DBFunctions.singleKeyValueToJson("error", "Bruker med samme e-post adresse eksisterer allerede!"));
                                    pw.print(finalResult);
                                    pw.close();


                                } else {


                                    finalResult = json.toJson(DBFunctions.singleKeyValueToJson("error", "Noe gikk galt! Vennligst prøv senere. (error-code: 500)"));
                                    pw.print(finalResult);
                                    pw.close();


                                }

                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }

                    }
                }
            }


        }
        session.setAttribute("error", "En feil har oppstått!");
    }
}




