package banking.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import banking.dto.BankDto;
import banking.entities.Bank;
import banking.response.ErrorResponse;
import banking.response.SuccessResponse;
import banking.responsitories.BankRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("bank")
public class BankController {
    @Autowired
    private BankRepository bankRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBank(@PathVariable("id") Long id) {
        Optional<Bank> bank = bankRepository.findById(id);
        if (!bank.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Bank not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.ok(bank);
    }

    @PostMapping
    public ResponseEntity<Bank> createBank(@Valid @RequestBody BankDto bankDto) {
        Bank bank = new Bank();

        bank.setName(bankDto.getName());
        bank.setHotline(bankDto.getHotline());
        bank.setEmail(bankDto.getEmail());
        bank.setAccount(bankDto.getAccount());
        bank.setDes(bankDto.getDes());

        Bank savedBank = bankRepository.save(bank);
        return ResponseEntity.ok(savedBank);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBank(@PathVariable("id") Long id, @Valid @RequestBody BankDto bankDto) {
        Optional<Bank> existingBank = bankRepository.findById(id);
        if (!existingBank.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Bank not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Bank bank = new Bank();

        bank.setId(id);
        bank.setName(bankDto.getName());
        bank.setHotline(bankDto.getHotline());
        bank.setEmail(bankDto.getEmail());
        bank.setAccount(bankDto.getAccount());
        bank.setDes(bankDto.getDes());

        Bank savedBank = bankRepository.save(bank);
        return ResponseEntity.ok(savedBank);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBank(@PathVariable("id") Long id) {
        Optional<Bank> existingBank = bankRepository.findById(id);
        if (!existingBank.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Bank not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        bankRepository.delete(existingBank.get());
        SuccessResponse success = new SuccessResponse(200, "Delete bank successfull");
        return ResponseEntity.status(HttpStatus.OK).body(success);
    }
}
