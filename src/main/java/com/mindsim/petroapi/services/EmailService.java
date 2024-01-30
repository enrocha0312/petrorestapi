package com.mindsim.petroapi.services;

import com.mindsim.petroapi.email.model.MensagemEmail;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.file.Files;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(MensagemEmail mensagemEmail){
        try {
            byte[] conteudoArquivo = Files.readAllBytes(mensagemEmail.getCaminhoArquivo());
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
            mimeMessageHelper.setFrom(mensagemEmail.getRemetente());//sender
            mimeMessageHelper.setSubject(mensagemEmail.getAssunto());
            mimeMessageHelper.setText(mensagemEmail.getTexto(), true);
            mimeMessageHelper.setTo(mensagemEmail.getDestinatarios()
                    .toArray(new String[mensagemEmail.getDestinatarios().size()]));
            mimeMessageHelper.addAttachment(mensagemEmail.getNomeArquivo(), new ByteArrayResource(conteudoArquivo));
            javaMailSender.send(mimeMessage);
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
