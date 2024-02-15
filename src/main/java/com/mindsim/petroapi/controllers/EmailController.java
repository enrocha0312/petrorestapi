package com.mindsim.petroapi.controllers;

import com.mindsim.petroapi.email.model.MensagemEmail;
import com.mindsim.petroapi.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/petroapi/enviaremails")
public class EmailController {
    @Autowired
    private EmailService emailService;
    @PostMapping("/mensagemteste")
    public ResponseEntity<?> enviarEmail(@RequestBody MensagemEmail mensagemEmail){
        try{
            emailService.sendEmailWithTestMessage(mensagemEmail);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/enviaranexo")
    public ResponseEntity<?> enviarArquivo(@RequestBody MensagemEmail mensagemEmail){
        try{
            emailService.sendEmailWithFile(mensagemEmail);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
