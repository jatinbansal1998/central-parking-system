package base_package.rate_strategy;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class HourlyRateStrategy implements IRateStrategy {
    private BigDecimal hourlyRate;
/*
* 9-5 100
* 5-9 150
* */
    @Override
    public BigDecimal calculateChargeAmount(LocalDateTime enterTime, LocalDateTime exitTime) {
        long hoursParkedFor = DurationUtil.getDuration(enterTime, exitTime).toHours();
        return hourlyRate.multiply(new BigDecimal(hoursParkedFor));
    }

    protected void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
