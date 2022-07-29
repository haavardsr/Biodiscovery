package servlets;

import com.google.gson.Gson;
import utils.DBFunctions;
import utils.HtmlHelper;
import utils.Validation;
import java.io.File;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.logging.Logger;
import models.User;



@WebServlet(name = "fileUpload", urlPatterns = "/fileUpload")
@MultipartConfig(maxFileSize = 1024*1024*300)
public class FileUploadServlet extends Servlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        request.setAttribute("title", "fileUpload");
        PrintWriter out = response.getWriter();
        HtmlHelper.writeHtmlStart(out, "Upload a fastaq file");
        writeFileUploadForm(out, null);
        HtmlHelper.writeHtmlEnd(out);

        super.setCSRF(request);
        if(Validation.isAuthenticated(request)){

            request.getRequestDispatcher("fileUpload.jsp").forward(request, response);
        }else{
            request.getSession().setAttribute("error", "You need to login to access other pages");
            response.sendRedirect("login");
        }

    }

    private static final String UPLOAD_DIR = "/../../FastqDIR";
    Logger logger = Logger.getLogger(FileUploadServlet.class.getName());

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try{
                // gets absolute path of the web application
                String applicationPath = request.getServletContext().getRealPath("");
                // constructs path of the directory to save uploaded file
                String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
                // creates the save directory if it does not exists
                File fileSaveDir = new File(uploadFilePath);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdirs();
                }
                logger.info("Upload File Directory="+fileSaveDir.getAbsolutePath());


//nytt
            String file_name = request.getParameter("file_name");

            Gson json = new Gson();
            PrintWriter pw = response.getWriter();
            String finalResult;

            if (file_name.equals("")) {
                finalResult = json.toJson(DBFunctions.singleKeyValueToJson("error", "Missing file name"));
                pw.print(finalResult);
                pw.close();
            }
//nytt
            String fileName = null;
            //Get all the parts from request and write it to the file on server
            for (Part part : request.getParts()) {
                fileName = getFileName(part);
                if (isFastqFile(fileName)){
                    Cookie[] cookies = request.getCookies();
                    String email = null;
                    for(Cookie cookie : cookies){
                        if(cookie.getName().equals("email")){
                            email = cookie.getValue();
                        }
                    }
                    /**
                     * Name of file when uploaded.
                     */
                    User user = DBFunctions.getUser(email);
                    part.write(uploadFilePath + File.separator + user.getId() + '_' + file_name + ".fastq");
                }



                }

                request.setAttribute("message", fileName + " File uploaded successfully!");
                getServletContext().getRequestDispatcher("/response.jsp").forward(
                      request, response);
            } catch (ServletException e) {
            throw new RuntimeException(e);
        }
        HtmlHelper.writeHtmlEnd(out);
    }

    private void writeFileUploadForm(PrintWriter out, String errorMessage) {

        if(errorMessage!=null)

        {
            out.println("<h3>"+errorMessage+"</h3>");
        }
        out.println("<form action='fileUpload' method='POST' enctype='multipart/form-data'>");
        out.println("<label for='file'>Upload a file</label> ");
        out.println("<input type='file' name='file'/>");
        out.println("<input type='submit' value='Upload file'/>");
        out.println("</form>");
        out.println("<label for='myfile'>Select a file:</label>");
        out.println("<input type='file' id='myfile' name='myfile'>");


/**
 * Set the file upload format. below you can see fastq is only allowed
 */
    }
    private boolean isFastqFile(String fileName) {
        return Arrays.asList("fastq")
                .contains(org.apache.commons.io.FilenameUtils.getExtension(fileName));
    }
    /**
     * Utility method to get file name from HTTP header content-disposition
     */
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }
}

