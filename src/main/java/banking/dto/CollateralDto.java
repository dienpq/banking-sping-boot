package banking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
