package banking.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class TypeLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String invitation;

    @Column(nullable = false)
    private Double interest;

    private String des;

    @Column(nullable = false)
    @ManyToOne(targetEntity = Bank.class)
    @JsonIgnore
    private Bank bank;

    @Column(nullable = false)
    @OneToMany(mappedBy = "typeLoan", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Loan> loans;

    @OneToMany(mappedBy = "typeLoan", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CommitmentContent> commitmentContents;
}
