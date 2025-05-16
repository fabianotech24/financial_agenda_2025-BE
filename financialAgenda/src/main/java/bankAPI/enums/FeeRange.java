package bankAPI.enums;

import java.math.BigDecimal;

public enum FeeRange {
    SAME_DAY(0, 0, BigDecimal.valueOf(2.5), BigDecimal.valueOf(3.0)),
    UP_TO_10_DAYS(1, 10, null, BigDecimal.valueOf(12.0)),
    UP_TO_20_DAYS(11, 20, BigDecimal.valueOf(8.2), null),
    UP_TO_30_DAYS(21, 30, BigDecimal.valueOf(6.9), null),
    UP_TO_40_DAYS(31, 40, BigDecimal.valueOf(4.7), null),
    UP_TO_50_DAYS(41, 50, BigDecimal.valueOf(1.7), null),
    INVALID_RANGE(-1, -1, null, null);

    private final int minDays;
    private final int maxDays;
    private final BigDecimal percentageFee;
    private final BigDecimal fixedFee;

    FeeRange(int minDays, int maxDays, BigDecimal percentageFee, BigDecimal fixedFee) {
        this.minDays = minDays;
        this.maxDays = maxDays;
        this.percentageFee = percentageFee;
        this.fixedFee = fixedFee;
    }

    public static FeeRange fromDays(long days) {
        for (FeeRange range : values()) {
            if (days >= range.minDays && days <= range.maxDays) {
                return range;
            }
        }
        return INVALID_RANGE;
    }

    public BigDecimal getFeeValue() {
        return percentageFee != null ? percentageFee.divide(BigDecimal.valueOf(100)) : fixedFee;
    }

    public BigDecimal getFixedFee() {
        return fixedFee;
    }

    public BigDecimal getPercentageFee() {
        return percentageFee;
    }
}