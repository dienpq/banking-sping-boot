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

import banking.entities.TypeLoan;
import banking.responsitories.TypeLoanRepository;

@RestController
@RequestMapping("type-loan")
public class TypeLoanController {
    @Autowired
    private TypeLoanRepository typeLoanRepository;

    @GetMapping("/{id}")
    public ResponseEntity<TypeLoan> getTypeLoan(@PathVariable("id") Long id) {
        Optional<TypeLoan> typeLoan = typeLoanRepository.findById(id);
        return typeLoan.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TypeLoan> createTypeLoan(@RequestBody TypeLoan typeLoan) {
        TypeLoan savedTypeLoan = typeLoanRepository.save(typeLoan);
        if (savedTypeLoan == null) {
            throw new RuntimeException("Failed to save typeLoan");
        }
        return ResponseEntity.created(URI.create("/typeLoans/" + savedTypeLoan.getId()))
                .body(savedTypeLoan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeLoan> updateTypeLoan(@PathVariable("id") Long id, @RequestBody TypeLoan typeLoan) {
        Optional<TypeLoan> existingTypeLoan = typeLoanRepository.findById(id);
        if (!existingTypeLoan.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        typeLoan.setId(id);
        TypeLoan savedTypeLoan = typeLoanRepository.save(typeLoan);
        return ResponseEntity.ok(savedTypeLoan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeLoan(@PathVariable("id") Long id) {
        Optional<TypeLoan> existingTypeLoan = typeLoanRepository.findById(id);
        if (!existingTypeLoan.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        typeLoanRepository.delete(existingTypeLoan.get());
        return ResponseEntity.noContent().build();
    }
}
