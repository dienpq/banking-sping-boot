package banking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressDto {
    @NotNull
    private String name;

    @NotNull
    private Long bankId;
}
