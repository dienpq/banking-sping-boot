package banking.controllers;

import java.util.List;
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

import banking.dto.TypeLoanDto;
import banking.entities.Bank;
import banking.entities.ExpiryDate;
import banking.entities.TypeLoan;
import banking.reponsitories.BankRepository;
import banking.reponsitories.ExpiryDateReponsitory;
import banking.reponsitories.TypeLoanRepository;
import banking.response.ErrorResponse;
import banking.response.SuccessResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("type-loan")
public class TypeLoanController {
    @Autowired
    private TypeLoanRepository typeLoanRepository;

    @Autowired
    private ExpiryDateReponsitory expiryDateReponsitory;

    @Autowired
    private BankRepository bankRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTypeLoan(@PathVariable("id") Long id) {
        Optional<TypeLoan> typeLoan = typeLoanRepository.findById(id);
        if (!typeLoan.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Type loan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.ok(typeLoan);
    }

    @GetMapping
    public ResponseEntity<Object> getAllTypeLoan() {
        Iterable<TypeLoan> typeLoans = typeLoanRepository.findAll();
        if (!typeLoans.iterator().hasNext()) {
            ErrorResponse error = new ErrorResponse(404, "Type loan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.ok(typeLoans);
    }

    @PostMapping
    public ResponseEntity<Object> createTypeLoan(@Valid @RequestBody TypeLoanDto typeLoanDto) {
        Optional<Bank> bank = bankRepository.findById(typeLoanDto.getBankId());
        if (!bank.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Bank not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        TypeLoan typeLoan = new TypeLoan();
        typeLoan.setName(typeLoanDto.getName());
        typeLoan.setInvitation(typeLoanDto.getInvitation());
        typeLoan.setDes(typeLoanDto.getDes());
        typeLoan.setBank(bank.get());

        TypeLoan savedTypeLoan = typeLoanRepository.save(typeLoan);
        return ResponseEntity.ok(savedTypeLoan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTypeLoan(@PathVariable("id") Long id,
            @Valid @RequestBody TypeLoanDto typeLoanDto) {
        Optional<TypeLoan> existingTypeLoan = typeLoanRepository.findById(id);
        if (!existingTypeLoan.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Type loan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Optional<Bank> bank = bankRepository.findById(typeLoanDto.getBankId());
        if (!bank.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Bank not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        TypeLoan typeLoan = new TypeLoan();

        typeLoan.setId(id);
        typeLoan.setName(typeLoanDto.getName());
        typeLoan.setInvitation(typeLoanDto.getInvitation());
        typeLoan.setDes(typeLoanDto.getDes());
        typeLoan.setBank(bank.get());

        TypeLoan savedTypeLoan = typeLoanRepository.save(typeLoan);
        return ResponseEntity.ok(savedTypeLoan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTypeLoan(@PathVariable("id") Long id) {
        Optional<TypeLoan> existingTypeLoan = typeLoanRepository.findById(id);
        if (!existingTypeLoan.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Type loan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        typeLoanRepository.delete(existingTypeLoan.get());
        SuccessResponse success = new SuccessResponse(200, "Delete type loan successfull");
        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @GetMapping("/{id}/expiry-date")
    public ResponseEntity<Object> findAllByTypeLoanId(@PathVariable("id") Long id) {
        Optional<TypeLoan> existingTypeLoan = typeLoanRepository.findById(id);
        if (!existingTypeLoan.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Type loan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        List<ExpiryDate> expiryDate = expiryDateReponsitory.findAllByTypeLoanId(id);
        return ResponseEntity.ok(expiryDate);
    }
}
