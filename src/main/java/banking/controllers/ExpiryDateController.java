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

import banking.dto.ExpiryDateDto;
import banking.entities.ExpiryDate;
import banking.entities.TypeLoan;
import banking.reponsitories.ExpiryDateReponsitory;
import banking.reponsitories.TypeLoanRepository;
import banking.response.ErrorResponse;
import banking.response.SuccessResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("expiry-date")
public class ExpiryDateController {
    @Autowired
    private ExpiryDateReponsitory expiryDateResponsitory;

    @Autowired
    private TypeLoanRepository typeLoanRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBank(@PathVariable("id") Long id) {
        Optional<ExpiryDate> expiryDate = expiryDateResponsitory.findById(id);
        if (!expiryDate.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "ExpiryDate not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.ok(expiryDate);
    }

    @PostMapping
    public ResponseEntity<Object> createBank(@Valid @RequestBody ExpiryDateDto expiryDateDto) {
        Optional<TypeLoan> typeLoan = typeLoanRepository.findById(expiryDateDto.getTypeLoanId());
        if (!typeLoan.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Type loan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        ExpiryDate expiryDate = new ExpiryDate();

        expiryDate.setAmountMonth(expiryDateDto.getAmountMonth());
        expiryDate.setInterest(expiryDateDto.getInterest());
        expiryDate.setPenaltyInterestRate(expiryDateDto.getPenaltyInterestRate());
        expiryDate.setTypeLoan(typeLoan.get());

        ExpiryDate savedExpiryDate = expiryDateResponsitory.save(expiryDate);
        return ResponseEntity.ok(savedExpiryDate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBank(@PathVariable("id") Long id,
            @Valid @RequestBody ExpiryDateDto expiryDateDto) {
        Optional<ExpiryDate> existingExpiryDate = expiryDateResponsitory.findById(id);
        if (!existingExpiryDate.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "ExpiryDate not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Optional<TypeLoan> typeLoan = typeLoanRepository.findById(expiryDateDto.getTypeLoanId());
        if (!typeLoan.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Type loan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        ExpiryDate expiryDate = new ExpiryDate();

        expiryDate.setId(id);
        expiryDate.setAmountMonth(expiryDateDto.getAmountMonth());
        expiryDate.setInterest(expiryDateDto.getInterest());
        expiryDate.setPenaltyInterestRate(expiryDateDto.getPenaltyInterestRate());
        expiryDate.setTypeLoan(typeLoan.get());

        ExpiryDate savedExpiryDate = expiryDateResponsitory.save(expiryDate);
        return ResponseEntity.ok(savedExpiryDate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBank(@PathVariable("id") Long id) {
        Optional<ExpiryDate> existingExpiryDate = expiryDateResponsitory.findById(id);
        if (!existingExpiryDate.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "ExpiryDate not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        expiryDateResponsitory.delete(existingExpiryDate.get());
        SuccessResponse success = new SuccessResponse(200, "Delete ExpiryDate successfull");
        return ResponseEntity.status(HttpStatus.OK).body(success);
    }
}
