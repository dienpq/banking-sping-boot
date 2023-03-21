package banking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import banking.Mail.Email;
import banking.Mail.EmailService;
import banking.response.ErrorResponse;
import banking.response.SuccessResponse;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("mail")
public class MailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<Object> sendEmail(@Valid @RequestBody Email email) {
        try {
            emailService.sendEmail(email.getEmailTo(), email.getSubject(), email.getContent());
            SuccessResponse success = new SuccessResponse(200, "Send email successfull");
            return ResponseEntity.status(HttpStatus.OK).body(success);
        } catch (MessagingException e) {
            ErrorResponse error = new ErrorResponse(500, "Send email fail");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
