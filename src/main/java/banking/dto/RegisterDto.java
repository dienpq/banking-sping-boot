package banking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterDto {
    @NotNull
    private String account;

    @NotNull
    private String password;
}
