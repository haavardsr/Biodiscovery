package utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * Below is the sender email, password given by the SMTP
 */

public class EmailClient {
    private static final String senderEmail = "resetbruker@gmail.com";
    private static final String senderPassword;

    static {
        try {
            senderPassword = ScannerFile.getPassword();
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
     * Test . Change Biodiscoveryas@gmail.com and try with your own email
     */

    public static void main(String[] args) throws MessagingException {
        EmailClient.sendAsHtml("resetbruker@gmail.com",
                "Test email",
                "<h2>Java Mail Example</h2><p>hi there!</p>");
    }
}