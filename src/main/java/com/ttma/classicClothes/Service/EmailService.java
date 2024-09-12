package com.ttma.classicClothes.Service;

import com.ttma.classicClothes.model.Orders;
import com.ttma.classicClothes.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
   private final JavaMailSender mailSender;
   @Value("spring.mail.username")
   private String fromEmail;

   public void sendOrderConfirm(Orders order){
       SimpleMailMessage mailMessage = new SimpleMailMessage();
       mailMessage.setFrom(fromEmail);
       mailMessage.setTo(order.getUser().getEmail());
       mailMessage.setSubject("Order confirmation");
       mailMessage.setText("Your order has been confirmed: " + order.getId());
       mailSender.send(mailMessage);
   }
   public void sendConfirmCode(User user){
       SimpleMailMessage mailMessage = new SimpleMailMessage();
       mailMessage.setFrom(fromEmail);
       mailMessage.setTo(user.getEmail());
       mailMessage.setSubject("Confirm email");
       mailMessage.setText("Your email code: " + user.getConfirmCode());
       mailSender.send(mailMessage);

   }
}
