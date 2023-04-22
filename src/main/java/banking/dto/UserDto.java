package banking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull
    private String fullname;

    @NotNull
    private String birthday;

    @NotNull
    private String gender;

    @NotNull
    private String phone;

    @NotNull
    private String email;

    @NotNull
    private String bankAccount;

    @NotNull
    private Double price;
}
