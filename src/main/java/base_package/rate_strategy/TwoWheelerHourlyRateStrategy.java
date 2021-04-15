package base_package.rate_strategy;

import java.math.BigDecimal;

public class TwoWheelerHourlyRateStrategy extends HourlyRateStrategy {
    private BigDecimal hourlyRate = new BigDecimal("50");

    public TwoWheelerHourlyRateStrategy() {
        setHourlyRate(hourlyRate);
    }
}
