package com.example.maildemo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/mail")
public class MailController {

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    //spring-boot-starter-validation で必要
    public record SendMailRequest(
            @Email @NotBlank String to,
            @NotBlank String subject,
            @NotBlank String text
    ) {}

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody SendMailRequest req) {
        mailService.sendTextMail(req.to(), req.subject(), req.text());
        return ResponseEntity.ok("sent");
    }
}