/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.components;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
/**
 *
 * @author vhuunghia
 */
public class EmailNotification {
    public static void sendNotification(String recipientEmail, String subject, String messageText) {
        final String senderEmail = "2051052090nghia@ou.edu.vn"; // Điền địa chỉ email của bạn ở đây
        final String senderPassword = "01235369856"; // Điền mật khẩu email của bạn ở đây

        // Cấu hình thông tin máy chủ email
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com"); // Điền máy chủ SMTP của Gmail
        properties.put("mail.smtp.port", "587"); // Điền cổng SMTP của Gmail

        // Tạo phiên làm việc với máy chủ email
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Tạo đối tượng MimeMessage
            Message message = new MimeMessage(session);

            // Đặt thông tin người gửi, người nhận và tiêu đề
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);

            // Đặt nội dung email
            message.setText(messageText);

            // Gửi email
            Transport.send(message);

            System.out.println("Email đã được gửi thành công.");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Lỗi khi gửi email: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String recipientEmail = "nghiabongda2@gmail.com"; // Điền địa chỉ email người nhận ở đây
        String subject = "Thông báo từ ứng dụng của bạn";
        String messageText = "Chào bạn, điểm của bạn đã được cập nhật thành công.";

        sendNotification(recipientEmail, subject, messageText);
    }
}
