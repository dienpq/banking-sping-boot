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

import banking.entities.Collateral;
import banking.responsitories.CollateralRepository;

@RestController
@RequestMapping("collateral")
public class CollateralController {
    @Autowired
    private CollateralRepository collateralRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Collateral> getcollateral(@PathVariable("id") Long id) {
        Optional<Collateral> collateral = collateralRepository.findById(id);
        return collateral.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Collateral> createcollateral(@RequestBody Collateral collateral) {
        Collateral savedCollateral = collateralRepository.save(collateral);
        if (savedCollateral == null) {
            throw new RuntimeException("Failed to save collateral");
        }
        return ResponseEntity.created(URI.create("/collaterals/" + savedCollateral.getId()))
                .body(savedCollateral);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Collateral> updatecollateral(@PathVariable("id") Long id,
            @RequestBody Collateral collateral) {
        Optional<Collateral> existingCollateral = collateralRepository.findById(id);
        if (!existingCollateral.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        collateral.setId(id);
        Collateral savedcollateral = collateralRepository.save(collateral);
        return ResponseEntity.ok(savedcollateral);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletecollateral(@PathVariable("id") Long id) {
        Optional<Collateral> existingCollateral = collateralRepository.findById(id);
        if (!existingCollateral.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        collateralRepository.delete(existingCollateral.get());
        return ResponseEntity.noContent().build();
    }
}
