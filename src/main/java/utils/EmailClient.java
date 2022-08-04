package utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Scanner;

/**
 * Below is the sender email, password given by the SMTP
 */

public class EmailClient {
    private static final String senderEmail = "resetbruker@gmail.com";
    //private static final String senderPassword = "If not API key is used. Use this insted";
    private static final String senderPassword;

    static {
        try {
            senderPassword = getPassword();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendAsHtml(String to, String title, String html) throws MessagingException {

        Session session = createSession();

        //create message using session
        MimeMessage message = new MimeMessage(session);
        prepareEmailMessage(message, to, title, html);
        //sending message
        Transport.send(message);
    }

    private static void prepareEmailMessage(MimeMessage message, String to, String title, String html)
            throws MessagingException {
        message.setContent(html, "text/html; charset=utf-8");
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(title);
    }

    /**
     *SMTP info
     */
    private static Session createSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp-relay.sendinblue.com");
        props.put("mail.smtp.port", "587");

        return Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
    }

    /**
     * This is where we place the APIkey file it will read
     */
    public static String getPassword() throws FileNotFoundException {
        File file = new File("/usr/local/tomcat/APIkey/SenderPassword.txt");
        Scanner scan = new Scanner(file);
        String passwd = null;
        while(scan.hasNextLine()) passwd = scan.nextLine();
        return passwd;
    }

    /**
     * Test . e-mail and try with your own email
     */

    /*public static void main(String[] args) throws MessagingException {
        EmailClient.sendAsHtml("hsrosenlund95@gmail.com",
                "Test email",
                "<h2>Java Mail Example</h2><p>hi there!</p>");
    }*/
}