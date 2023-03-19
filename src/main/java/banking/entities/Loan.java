package banking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

@Data
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String code;

    @NotNull
    private Double price;

    @NotNull
    private Double priceRemaining;

    @Null
    private String des;

    @ManyToOne(targetEntity = TypeLoan.class)
    @JsonIgnore
    private TypeLoan typeLoan;

    @ManyToOne(targetEntity = User.class)
    @JsonIgnore
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Contract contract;
}
