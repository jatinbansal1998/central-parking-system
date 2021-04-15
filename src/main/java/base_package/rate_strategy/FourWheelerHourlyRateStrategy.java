package base_package.rate_strategy;

import java.math.BigDecimal;

public class FourWheelerHourlyRateStrategy extends HourlyRateStrategy {
    private BigDecimal hourlyRate = new BigDecimal("100");

    public FourWheelerHourlyRateStrategy() {
        setHourlyRate(hourlyRate);
    }
}
