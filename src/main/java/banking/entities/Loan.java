package banking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Double priceRemaining;

    private String des;

    @Column(nullable = false)
    @ManyToOne(targetEntity = TypeLoan.class)
    @JsonIgnore
    private TypeLoan typeLoan;

    @Column(nullable = false)
    @ManyToOne(targetEntity = User.class)
    @JsonIgnore
    private User user;

    @Column(nullable = false)
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Contract contract;
}
