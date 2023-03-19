package banking.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import banking.entities.User;
import banking.responsitories.UserRepository;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        if (savedUser == null) {
            throw new RuntimeException("Failed to save user");
        }
        return ResponseEntity.created(URI.create("/users/" + savedUser.getId()))
                .body(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        Optional<User> existingUser = userRepository.findById(id);
        if (!existingUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        user.setId(id);
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (!existingUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        userRepository.delete(existingUser.get());
        return ResponseEntity.noContent().build();
    }
}
