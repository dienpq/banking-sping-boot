package banking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

    @JoinColumn(nullable = false)
    @ManyToOne(targetEntity = TypeLoan.class)
    @JsonIgnore
    private TypeLoan typeLoan;

    @JoinColumn(nullable = false)
    @ManyToOne(targetEntity = User.class)
    @JsonIgnore
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Contract contract;
}
