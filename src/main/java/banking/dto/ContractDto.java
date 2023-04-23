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
    // Họ tên
    @NotNull
    private String fullname;

    // Giới tính
    @NotNull
    private String gender;

    // Ngày sinh
    @NotNull
    private LocalDate birthday;

    // Quốc tịch
    @NotNull
    private String nationality;

    // Số CCCD
    @NotNull
    private String idNumber;

    // Ngày cấp
    @NotNull
    private LocalDate issuedDate;

    // Nơi cấp
    @NotNull
    private String issuedPlace;

    // Địa chỉ thương trú
    @NotNull
    private String permanentAddress;

    // Nơi ở hiện tại
    @NotNull
    private String currentResidence;

    // Số điện thoại
    @NotNull
    private String phone;

    // Email
    @NotNull
    private String email;

    // Tình trạng hôn nhân
    @NotNull
    private String marital;

    // Trình độ học vấn
    @NotNull
    private String academicLevel;

    // Hình thức sở hữu nhà ở
    @NotNull
    private String homeOwnership;

    // Phương tiện đi lại
    @NotNull
    private String vehicles;

    // Tên công ty
    private String nameCompany;

    // Số điện thoại công ty
    private String phoneCompany;

    // Địa chỉ công ty
    private String addressCompany;

    // Nghề nghiệp
    @NotNull
    private String job;

    // Loại hình hợp đồng lao động
    @NotNull
    private String typeContractJob;

    // Hình thức nhận lương
    @NotNull
    private String typeReceiveWage;

    // Mục đính vay vốn
    @NotNull
    private String loanPurpose;

    // Số tiền vay
    @NotNull
    private Double priceLoan;

    // Thời hạn vay
    @NotNull
    private Integer timeLoan;

    // Lãi suất
    @NotNull
    private Double interestLoan;

    // Lãi suất trả muộn/phạt
    @NotNull
    private Double penaltyInterestRate;

    // Phương thức trả nợ
    @NotNull
    private String debtPaymentMethod;

    // Đề xuất khác
    private String otherSuggestions;

    // Lương
    @NotNull
    private Double wage;

    // Cổ tức
    private Double dividend;

    // Lợi nhuận
    private Double profit;

    // Thu nhập từ cho thuê tài sản
    private Double propertyRentalIncome;

    // Thu nhập khác
    private String otherIncome;

    // Lương của vợ hoặc chồng
    private Double wageWifeOrHusband;

    // Cổ tức của vợ hoặc chồng
    private Double dividendWifeOrHusband;

    // Lợi nhuận từ kinh doanh của vợ hoặc chồng
    private Double profitWifeOrHusband;

    // Thu nhập từ cho thuê tài sản của vợ hoặc chồng
    private Double propertyRentalIncomeWifeOrHusband;

    // Thu nhập khác của vợ hoặc chồng
    private String otherIncomeWifeOrHusband;

    // Lương của người hỗ trợ trả nựo
    private Double wageSupporter;

    // Cổ tức của người hỗ trợ trả nợ
    private Double dividendSupporter;

    // Lợi nhuận từ kinh doanh của người hỗ trợ trả nợ
    private Double profitSupporter;

    // Thu nhập từ cho thuê bất động sản của người hỗ trợ trả nợ
    private Double propertyRentalIncomeSupporter;

    // Thu nhập khác của người hỗ trợ trả nợ
    private String otherIncomeSupporter;

    // Chi phí sinh hoạt
    private Double costOfLiving;

    // Chi phí trả gốc lãi các khoản vay
    private Double interestPaymentsOnLoans;

    // Chi phí khác
    private String otherCosts;

    // Các khoản thu nhập bất thường khác
    private String otherExtraordinaryIncome;

    @NotNull
    private Long loanId;
}
