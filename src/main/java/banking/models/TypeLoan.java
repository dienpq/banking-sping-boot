package banking.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class TypeLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String invitation;

    @NotNull
    private Double interest;

    private String des;

    @ManyToOne(targetEntity = Bank.class)
    @JsonIgnore
    private Bank bank;

    @OneToMany(mappedBy = "typeLoan", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Loan> loans;

    @OneToMany(mappedBy = "typeLoan", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CommitmentContent> commitmentContents;
}
