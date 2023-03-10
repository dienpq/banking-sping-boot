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

import banking.models.Loan;
import banking.responsitories.LoanRepository;

@RestController
@RequestMapping("loan")
public class LoanController {
    @Autowired
    private LoanRepository loanRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoan(@PathVariable("id") Long id) {
        Optional<Loan> loan = loanRepository.findById(id);
        return loan.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody Loan loan) {
        Loan savedLoan = loanRepository.save(loan);
        if (savedLoan == null) {
            throw new RuntimeException("Failed to save loan");
        }
        return ResponseEntity.created(URI.create("/loans/" + savedLoan.getId()))
                .body(savedLoan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Loan> updateLoan(@PathVariable("id") Long id, @RequestBody Loan loan) {
        Optional<Loan> existingLoan = loanRepository.findById(id);
        if (!existingLoan.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        loan.setId(id);
        Loan savedLoan = loanRepository.save(loan);
        return ResponseEntity.ok(savedLoan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable("id") Long id) {
        Optional<Loan> existingLoan = loanRepository.findById(id);
        if (!existingLoan.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        loanRepository.delete(existingLoan.get());
        return ResponseEntity.noContent().build();
    }
}
