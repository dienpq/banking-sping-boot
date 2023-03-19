package banking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TypeLoan {
    @NotNull
    private String name;

    @NotNull
    private String invitation;

    @NotNull
    private Double interest;

    private String des;

    @NotNull
    private Long bankId;
}
