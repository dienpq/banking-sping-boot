package banking.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import banking.dto.LoginDto;
import banking.dto.RegisterDto;
import banking.entities.User;
import banking.reponsitories.UserRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterDto userDto) {
        User user = new User();
        user.setAccount(userDto.getAccount());
        user.setPassword(userDto.getPassword());

        user.setFullname("");
        user.setBirthday("");
        user.setGender("");
        user.setPhone("");
        user.setEmail("");
        user.setBankAccount("222340829");
        user.setPrice(0.0);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("login")
    public ResponseEntity<User> login(@Valid @RequestBody LoginDto userDto) {
        User user = new User();
        user.setAccount(userDto.getAccount());
        user.setPassword(userDto.getPassword());
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }
}
