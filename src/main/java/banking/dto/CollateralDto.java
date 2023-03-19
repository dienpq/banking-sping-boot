package banking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CollateralDto {
    @NotNull
    private String name;

    @NotNull
    private String owner;

    @NotNull
    private String relationOwnerAndCustomer;

    @NotNull
    private String status;

    @NotNull
    private Long contractId;
}
