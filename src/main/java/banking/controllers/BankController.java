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

import banking.responsitories.BankRepository;
import banking.models.Bank;

@RestController
@RequestMapping("bank")
public class BankController {
    @Autowired
    private BankRepository bankRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Bank> getBank(@PathVariable("id") Long id) {
        Optional<Bank> bank = bankRepository.findById(id);
        return bank.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Bank> createBank(@RequestBody Bank Bank) {
        Bank savedBank = bankRepository.save(Bank);
        if (savedBank == null) {
            throw new RuntimeException("Failed to save bank");
        }
        return ResponseEntity.created(URI.create("/banks/" + savedBank.getId()))
                .body(savedBank);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bank> updateBank(@PathVariable("id") Long id, @RequestBody Bank bank) {
        Optional<Bank> existingBank = bankRepository.findById(id);
        if (!existingBank.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        bank.setId(id);
        Bank savedBank = bankRepository.save(bank);
        return ResponseEntity.ok(savedBank);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBank(@PathVariable("id") Long id) {
        Optional<Bank> existingBank = bankRepository.findById(id);
        if (!existingBank.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        bankRepository.delete(existingBank.get());
        return ResponseEntity.noContent().build();
    }
}
