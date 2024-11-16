package com.spring.mail.sender.controllers;

import com.spring.mail.sender.models.EmailDTO;
import com.spring.mail.sender.models.EmailFileDTO;
import com.spring.mail.sender.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/v1/mail")
public class MailSenderController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendMessage")
    public ResponseEntity<?> requestEmail(@RequestBody EmailDTO emailDTO){
        System.out.println("Mensaje recibido " + emailDTO);

        emailService.sendEmail(emailDTO.user(),emailDTO.subject(), emailDTO.message());

        Map<String, String> response = new HashMap<>();
        response.put("state", "Enviado");
        return ResponseEntity.ok(response );
    }

    @PostMapping("/sendMessageFile")
    public ResponseEntity<?> requestEmailWithFile(@ModelAttribute EmailFileDTO emailFileDTO){
        try {
            String fileName = emailFileDTO.getFile().getOriginalFilename();
            Path path = Paths.get("src/main/resources/files/" + fileName);
            Files.createDirectories(path.getParent());
            Files.copy(emailFileDTO.getFile().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            File file = path.toFile();

            emailService.SendEmailWithFile(emailFileDTO.getUser(), emailFileDTO.getSubject(), emailFileDTO.getMessage(), file);

            Map<String, String> response = new HashMap<>();
            response.put("state", "Enviado");

            return ResponseEntity.ok(response);

        } catch (Exception e){
            throw new RuntimeException("Error enviando archivo " + e.getMessage());
        }

    }
}
