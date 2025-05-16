package bankAPI.dto;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

public record TransferRequest(
        @NotBlank
        @Pattern(regexp = "^[0-9]{10}$", message = "Conta de destino deve ter 10 dígitos")
        String destinationAccount,

        @NotBlank
        @Pattern(regexp = "^[0-9]{10}$", message = "Conta de origem deve ter 10 dígitos")
        String sourceAccount,

        @Positive
        BigDecimal amount,

        @FutureOrPresent
        LocalDate transferDate
) {}