package banking.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String fullname;

    private String gender;

    private LocalDate birthday;

    private String national;

    private String idNumber;

    private LocalDate issuedDate;

    private String issuedPlace;

    private String permanentAddress;

    private String currentResidence;

    private String phone;

    private String email;

    private String marital;

    private String academicLevel;

    private String homeOwnership;

    private String vehicles;

    private String nameCompany;

    private String phoneCompany;

    private String addressCompany;

    private String job;

    private String typeContractJob;

    private String typeReceiveWage;

    private String loanPurpose;

    private Double priceLoan;

    private Integer timeLoan;

    private Float interestLoan;

    private String debtPaymentMethod;

    private String otherSuggestions;

    private Double wage;

    private Double dividend;

    private Double profit;

    private Double propertyRentalIncome;

    private String otherIncome;

    private Double wageWifeOrHusband;

    private Double dividendWifeOrHusband;

    private Double profitWifeOrHusband;

    private Double propertyRentalIncomeWifeOrHusband;

    private String otherIncomeWifeOrHusband;

    private Double wageSupporter;

    private Double dividendSupporter;

    private Double profitSupporter;

    private Double propertyRentalIncomeSupporter;

    private String otherIncomeSupporter;

    private Double costOfLiving;

    private Double interestPaymentsOnLoans;

    private String otherCosts;

    private String otherExtraordinaryIncome;

    private int status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    @JsonIgnore
    private Loan loan;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
