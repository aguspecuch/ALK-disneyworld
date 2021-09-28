package ar.com.alkemy.disneyworld.system;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ar.com.alkemy.disneyworld.entities.Usuario;

import java.io.IOException;

@Service

public class EmailService {

    @Value("${spring.sendgrid.api-key}")
    private String api_key;
    @Value("${spring.sendgrid.fromEmail}")
    private String fromEmail;

    public void sendEmail(Usuario user) {

        Email from = new Email(fromEmail);
        String subject = "¡Bienvenido a DisneyWorld!";
        Email to = new Email(user.getEmail());
        Content content = new Content("text/plain", "Bienvenido " + user.getFullName() + ".\nTu nombre de usuario es: "
                + user.getUsername() + "\n¡Gracias por registrarte!");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(api_key);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
