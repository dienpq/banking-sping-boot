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

import banking.dto.CommitmentContentDto;
import banking.entities.CommitmentContent;
import banking.entities.TypeLoan;
import banking.response.ErrorResponse;
import banking.response.SuccessResponse;
import banking.responsitories.CommitmentContentRepository;
import banking.responsitories.TypeLoanRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("commitment-content")
public class CommitmentContentController {
    @Autowired
    private CommitmentContentRepository commitmentContentRepository;

    @Autowired
    private TypeLoanRepository typeLoanRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCommitmentContent(@PathVariable("id") Long id) {
        Optional<CommitmentContent> commitmentContent = commitmentContentRepository.findById(id);
        if (!commitmentContent.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Commitment content not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.ok(commitmentContent);
    }

    @PostMapping
    public ResponseEntity<Object> createCommitmentContent(
            @Valid @RequestBody CommitmentContentDto commitmentContentDto) {
        Optional<TypeLoan> typeLoan = typeLoanRepository.findById(commitmentContentDto.getTypeLoanId());
        if (!typeLoan.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Type loan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        CommitmentContent commitmentContent = new CommitmentContent();
        commitmentContent.setDes(commitmentContentDto.getDes());
        commitmentContent.setTypeLoan(typeLoan.get());

        CommitmentContent savedCommitmentContent = commitmentContentRepository.save(commitmentContent);
        return ResponseEntity.ok(savedCommitmentContent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCommitmentContent(@PathVariable("id") Long id,
            @Valid @RequestBody CommitmentContentDto commitmentContentDto) {
        Optional<CommitmentContent> existingCommitmentContent = commitmentContentRepository.findById(id);
        if (!existingCommitmentContent.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Commitment content not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Optional<TypeLoan> typeLoan = typeLoanRepository.findById(commitmentContentDto.getTypeLoanId());
        if (!typeLoan.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Type loan not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        CommitmentContent commitmentContent = new CommitmentContent();

        commitmentContent.setId(id);
        commitmentContent.setDes(commitmentContentDto.getDes());
        commitmentContent.setTypeLoan(typeLoan.get());

        CommitmentContent savedCommitmentContent = commitmentContentRepository.save(commitmentContent);
        return ResponseEntity.ok(savedCommitmentContent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCommitmentContent(@PathVariable("id") Long id) {
        Optional<CommitmentContent> existingCommitmentContent = commitmentContentRepository.findById(id);
        if (!existingCommitmentContent.isPresent()) {
            ErrorResponse error = new ErrorResponse(404, "Commitment content not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        commitmentContentRepository.delete(existingCommitmentContent.get());
        SuccessResponse success = new SuccessResponse(200, "Delete commitment content successfull");
        return ResponseEntity.status(HttpStatus.OK).body(success);
    }
}
