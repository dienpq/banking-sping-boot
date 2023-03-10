package banking.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

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

    private String identifier;

    private LocalDate dateIdentifier;

    private String addressIdentifier;

    private String domicileResidence;

    private String currentResidence;

    private String phone;

    private String email;

    private String maritalStatus;

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

    private String desLoanPurpose;

    private Double priceLoan;

    private Integer timeLoan;

    private Integer timeLoanCurrent;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Loan loan;
}
