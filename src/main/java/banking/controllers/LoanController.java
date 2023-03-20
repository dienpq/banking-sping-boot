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

import banking.dto.LoanDto;
import banking.entities.Loan;
import banking.entities.TypeLoan;
import banking.entities.User;
import banking.response.ErrorResponse;
import banking.response.SuccessResponse;
import banking.responsitories.LoanRepository;
import banking.responsitories.TypeLoanRepository;
import banking.responsitories.UserRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("loan")
public class LoanController {
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TypeLoanRepository typeLoanRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getLoan(@PathVariable("id") Long id) {
        Optional<Loan> loan = loanRepository.findById(id);
        if (!loan.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Loan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.ok(loan);
    }

    @PostMapping
    public ResponseEntity<Object> createLoan(@Valid @RequestBody LoanDto loanDto) {
        Optional<User> user = userRepository.findById(loanDto.getUserId());
        if (!user.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Loan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Optional<TypeLoan> typeLoan = typeLoanRepository.findById(loanDto.getTypeLoanId());
        if (!typeLoan.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Loan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Loan loan = new Loan();
        loan.setCode(loanDto.getCode());
        loan.setPrice(loanDto.getPrice());
        loan.setPriceRemaining(loanDto.getPriceRemaining());
        loan.setUser(user.get());
        loan.setTypeLoan(typeLoan.get());

        Loan savedLoan = loanRepository.save(loan);
        return ResponseEntity.ok(savedLoan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateLoan(@PathVariable("id") Long id, @Valid @RequestBody LoanDto loanDto) {
        Optional<Loan> existingLoan = loanRepository.findById(id);
        if (!existingLoan.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Loan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Optional<User> user = userRepository.findById(loanDto.getUserId());
        if (!user.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Optional<TypeLoan> typeLoan = typeLoanRepository.findById(loanDto.getTypeLoanId());
        if (!typeLoan.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Type loan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Loan loan = new Loan();

        loan.setId(id);
        loan.setCode(loanDto.getCode());
        loan.setPrice(loanDto.getPrice());
        loan.setPriceRemaining(loanDto.getPriceRemaining());
        loan.setUser(user.get());
        loan.setTypeLoan(typeLoan.get());

        Loan savedLoan = loanRepository.save(loan);
        return ResponseEntity.ok(savedLoan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteLoan(@PathVariable("id") Long id) {
        Optional<Loan> existingLoan = loanRepository.findById(id);
        if (!existingLoan.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Loan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        loanRepository.delete(existingLoan.get());
        SuccessResponse success = new SuccessResponse(200, "Delete loan successfull");
        return ResponseEntity.status(HttpStatus.OK).body(success);
    }
}
