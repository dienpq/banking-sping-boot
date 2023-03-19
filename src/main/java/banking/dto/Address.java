package banking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Address {
    @NotNull
    private String name;

    @NotNull
    private Long bankId;
}
