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
        request.setAttribute("title", "Add user");
        request.getRequestDispatcher("createUser.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();

        Gson json = new Gson();
        PrintWriter pw = response.getWriter();
        String finalResult;


        //   if (Validation.isSuperUser(request) && super.verifiyCSRF(request)) {
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
            finalResult = json.toJson(DBFunctions.singleKeyValueToJson("error", "Invalid first name"));
            pw.print(finalResult);
            pw.close();
        } else {
            if (last_name.equals("")) {
                finalResult = json.toJson(DBFunctions.singleKeyValueToJson("error", "Invalid last name"));
                pw.print(finalResult);
                pw.close();
            } else {
                if (email.equals("")) {
                    finalResult = json.toJson(DBFunctions.singleKeyValueToJson("error", "Invalid e-mail address"));
                    pw.print(finalResult);
                    pw.close();
                } else {

                    if (!Validation.validatePasswords(password1, password2)) {
                        finalResult = json.toJson(DBFunctions.singleKeyValueToJson("error", "The passwords do not match! They must be exactly the same. try again"));
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
                            response.sendRedirect("login");


                        } catch (SQLException | ClassNotFoundException e) {

                            e.printStackTrace();
                            if (e instanceof SQLIntegrityConstraintViolationException) {

                                finalResult = json.toJson(DBFunctions.singleKeyValueToJson("error", "User with the same e-mail address already exists!"));
                                pw.print(finalResult);
                                pw.close();


                            } else {


                                finalResult = json.toJson(DBFunctions.singleKeyValueToJson("error", "Something went wrong! Please try again later. (error code: 500)"));
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
}



