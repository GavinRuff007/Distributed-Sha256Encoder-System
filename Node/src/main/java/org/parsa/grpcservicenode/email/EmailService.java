package org.parsa.grpcservicenode.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class EmailService {

    private static String destEmail;

    private static String nodeName;

    public static void sendDecryptedTextEmail(String decryptedText, String publicKey, int randomValue) {
        String to = getEmail();
        String from = "nodenotificationservice@gmail.com";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                // اینجا App Password را جایگزین پسورد قبلی کنید
                return new PasswordAuthentication("nodenotificationservice@gmail.com", "natq pzlp jkos skgq");
            }
        });

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Decrypted Text and Node Info");

            String content = "<h1>Decrypted Information</h1>"
                    + "<p><strong>Node Name:</strong> " + getNodeName() + "</p>"
                    + "<p><strong>Decrypted Text:</strong> " + decryptedText + "</p>"
                    + "<p><strong>Public Key:</strong> " + publicKey + "</p>"
                    + "<p><strong>Random Value:</strong> " + randomValue + "</p>"
                    + "<p><strong>Timestamp:</strong> " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "</p>";

            message.setContent(content, "text/html");

            Transport.send(message);
            System.out.println("Email sent for "+to+" successfully!");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    public static String getEmail(){
        return destEmail;
    }

    public static void setEmail(String email){
        destEmail = email;
    }

    public static String getNodeName() {
        return nodeName;
    }

    public static void setNodeName(String nodeName1){
        nodeName = nodeName1;
    }
}
