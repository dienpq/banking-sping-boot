package banking.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Null;

@Entity
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Null
    private String code;

    @Null
    private String fullname;

    @Null
    private String gender;

    @Null
    private LocalDate birthday;

    @Null
    private String national;

    @Null
    private String identifier;

    @Null
    private LocalDate dateIdentifier;

    @Null
    private String addressIdentifier;

    @Null
    private String domicileResidence;

    @Null
    private String currentResidence;

    @Null
    private String phone;

    @Null
    private String email;

    @Null
    private String maritalStatus;

    @Null
    private String academicLevel;

    @Null
    private String homeOwnership;

    @Null
    private String vehicles;

    @Null
    private String nameCompany;

    @Null
    private String phoneCompany;

    @Null
    private String addressCompany;

    @Null
    private String job;

    @Null
    private String typeContractJob;

    @Null
    private String typeReceiveWage;

    @Null
    private String loanPurpose;

    @Null
    private String desLoanPurpose;

    @Null
    private Double priceLoan;

    @Null
    private Integer timeLoan;

    @Null
    private Integer timeLoanCurrent;

    @Null
    private String debtPaymentMethod;

    @Null
    private String otherSuggestions;

    @Null
    private Double wage;

    @Null
    private Double dividend;

    @Null
    private Double profit;

    @Null
    private Double propertyRentalIncome;

    @Null
    private String otherIncome;

    @Null
    private Double wageWifeOrHusband;

    @Null
    private Double dividendWifeOrHusband;

    @Null
    private Double profitWifeOrHusband;

    @Null
    private Double propertyRentalIncomeWifeOrHusband;

    @Null
    private String otherIncomeWifeOrHusband;

    @Null
    private Double wageSupporter;

    @Null
    private Double dividendSupporter;

    @Null
    private Double profitSupporter;

    @Null
    private Double propertyRentalIncomeSupporter;

    @Null
    private String otherIncomeSupporter;

    @Null
    private Double costOfLiving;

    @Null
    private Double interestPaymentsOnLoans;

    @Null
    private String otherCosts;

    @Null
    private String otherExtraordinaryIncome;

    @OneToOne(cascade = CascadeType.ALL)

    @Null
    @JsonIgnore
    private Loan loan;
}
