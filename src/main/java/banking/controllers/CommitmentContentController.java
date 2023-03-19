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

import banking.entities.CommitmentContent;
import banking.responsitories.CommitmentContentRepository;

@RestController
@RequestMapping("commitment-content")
public class CommitmentContentController {
    @Autowired
    private CommitmentContentRepository commitmentContentRepository;

    @GetMapping("/{id}")
    public ResponseEntity<CommitmentContent> getCommitmentContent(@PathVariable("id") Long id) {
        Optional<CommitmentContent> commitmentContent = commitmentContentRepository.findById(id);
        return commitmentContent.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CommitmentContent> createCommitmentContent(@RequestBody CommitmentContent commitmentContent) {
        CommitmentContent savedCommitmentContent = commitmentContentRepository.save(commitmentContent);
        if (savedCommitmentContent == null) {
            throw new RuntimeException("Failed to save commitmentContent");
        }
        return ResponseEntity.created(URI.create("/commitmentContents/" + savedCommitmentContent.getId()))
                .body(savedCommitmentContent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommitmentContent> updateCommitmentContent(@PathVariable("id") Long id,
            @RequestBody CommitmentContent commitmentContent) {
        Optional<CommitmentContent> existingCommitmentContent = commitmentContentRepository.findById(id);
        if (!existingCommitmentContent.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        commitmentContent.setId(id);
        CommitmentContent savedCommitmentContent = commitmentContentRepository.save(commitmentContent);
        return ResponseEntity.ok(savedCommitmentContent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommitmentContent(@PathVariable("id") Long id) {
        Optional<CommitmentContent> existingCommitmentContent = commitmentContentRepository.findById(id);
        if (!existingCommitmentContent.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        commitmentContentRepository.delete(existingCommitmentContent.get());
        return ResponseEntity.noContent().build();
    }
}
