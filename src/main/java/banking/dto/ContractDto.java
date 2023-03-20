package banking.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractDto {
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

    @NotNull
    private Long loanId;
}
