package com.spring.mail.sender.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailFileDTO {
    private String[] user;
    private String subject;
    private String message;
    private MultipartFile file;
}
