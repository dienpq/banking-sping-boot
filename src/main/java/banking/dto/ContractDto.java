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

    // Họ tên
    private String fullname;

    // Giới tính
    private String gender;

    // Ngày sinh
    private LocalDate birthday;

    // Quốc tịch
    private String national;

    // Số CCCD
    private String idNumber;

    // Ngày cấp
    private LocalDate issuedDate;

    // Nơi cấp
    private String issuedPlace;

    // Địa chỉ thương trú
    private String permanentAddress;

    // Nơi ở hiện tại
    private String currentResidence;

    // Số điện thoại
    private String phone;

    // Email
    private String email;

    // Tình trạng hôn nhân
    private String marital;

    // Trình độ học vấn
    private String academicLevel;

    // Hình thức sở hữu nhà ở
    private String homeOwnership;

    // Phương tiện đi lại
    private String vehicles;

    // Tên công ty
    private String nameCompany;

    // Số điện thoại công ty
    private String phoneCompany;

    // Địa chỉ công ty
    private String addressCompany;

    // Nghề nghiệp
    private String job;

    // Loại hình hợp đồng lao động
    private String typeContractJob;

    // Hình thức nhận lương
    private String typeReceiveWage;

    // Mục đính vay vốn
    private String loanPurpose;

    // Mô tả mục đính vay vốn
    private String desLoanPurpose;

    // Số tiền vay
    private Double priceLoan;

    // Thời hạn vay
    private Integer timeLoan;

    // Ân hạn gốc
    private Integer timeLoanCurrent;

    // Phương thức trả nợ
    private String debtPaymentMethod;

    // Đề xuất khác
    private String otherSuggestions;

    // Lương
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

    // Các khoản thu nhập bất thườn khác
    private String otherExtraordinaryIncome;

    @NotNull
    private Long loanId;
}
