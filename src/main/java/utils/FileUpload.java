package utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUpload {

    public static String uploadFile(HttpServletRequest request, String realPath) throws IOException, ServletException {
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        String path = realPath+ File.separator + fileName;
        InputStream is = filePart.getInputStream();
        byte[] b = new byte[is.available()];
        FileOutputStream fos = new FileOutputStream(path);
        int bytesRead;
        while ((bytesRead = is.read(b)) != -1) {
            fos.write(b, 0, bytesRead);
        }
        fos.flush();
        fos.close();
        return "media/Biodiscovery" + fileName;
    }
}


