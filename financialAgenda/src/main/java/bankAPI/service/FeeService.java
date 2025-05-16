package bankAPI.service;

import bankAPI.enums.FeeRange;
import bankAPI.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class FeeService {

    private static final int MAX_SCHEDULING_DAYS = 50;

    public BigDecimal calculateFee(BigDecimal amount, LocalDate transferDate) {
        validateTransfer(amount, transferDate);
        long daysBetween = calculateDaysBetween(transferDate);
        FeeRange feeRange = FeeRange.fromDays(daysBetween);
        return calculateFeeForRange(amount, feeRange);
    }

    private void validateTransfer(BigDecimal amount, LocalDate transferDate) {
        validateAmount(amount);
        validateTransferDate(transferDate);
    }

    private void validateAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("O valor da transferência deve ser positivo");
        }
    }

    private void validateTransferDate(LocalDate transferDate) {
        if (transferDate.isBefore(LocalDate.now())) {
            throw new BusinessException("Data de transferência não pode ser no passado");
        }

        long daysBetween = calculateDaysBetween(transferDate);
        if (daysBetween > MAX_SCHEDULING_DAYS) {
            throw new BusinessException("Transferências com mais de " + MAX_SCHEDULING_DAYS + " dias de antecedência não são permitidas");
        }
    }

    private long calculateDaysBetween(LocalDate transferDate) {
        return ChronoUnit.DAYS.between(LocalDate.now(), transferDate);
    }

    private BigDecimal calculateFeeForRange(BigDecimal amount, FeeRange feeRange) {
        switch (feeRange) {
            case SAME_DAY:
                return calculateSameDayFee(amount);
            case UP_TO_10_DAYS:
                return FeeRange.UP_TO_10_DAYS.getFeeValue();
            case UP_TO_20_DAYS:
            case UP_TO_30_DAYS:
            case UP_TO_40_DAYS:
            case UP_TO_50_DAYS:
                return amount.multiply(feeRange.getFeeValue());
            default:
                throw new BusinessException("Taxa não aplicável para esta data");
        }
    }

    private BigDecimal calculateSameDayFee(BigDecimal amount) {
        BigDecimal percentageFee = amount.multiply(FeeRange.SAME_DAY.getFeeValue().divide(BigDecimal.valueOf(100)));
        return percentageFee.max(FeeRange.SAME_DAY.getFixedFee());
    }
}