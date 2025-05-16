package bankAPI.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransferResponse(
        Long id,
        String sourceAccount,
        String destinationAccount,
        BigDecimal amount,
        BigDecimal fee,
        LocalDate transferDate,
        LocalDate schedulingDate
) {}