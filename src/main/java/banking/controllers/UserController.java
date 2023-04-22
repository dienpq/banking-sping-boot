package banking.controllers;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import banking.dto.UserDto;
import banking.entities.User;
import banking.reponsitories.UserRepository;
import banking.response.ErrorResponse;
import banking.response.SuccessResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable("id") Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserDto userDto) {
        Optional<User> existingUser = userRepository.findById(id);
        if (!existingUser.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        User user = new User();

        user.setId(id);
        user.setAccount(existingUser.get().getAccount());
        user.setPassword(existingUser.get().getPassword());
        user.setFullname(userDto.getFullname());
        user.setBirthday(userDto.getBirthday());
        user.setGender(userDto.getGender());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        user.setBankAccount(userDto.getBankAccount());
        user.setPrice(userDto.getPrice());
        user.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (!existingUser.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        userRepository.delete(existingUser.get());
        SuccessResponse success = new SuccessResponse(200, "Delete user successfull");
        return ResponseEntity.status(HttpStatus.OK).body(success);
    }
}
