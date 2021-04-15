package base_package.rate_strategy;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RateStrategyExecutor {
    public static BigDecimal executeRateStrategy(LocalDateTime enterTime, LocalDateTime exitTime, IRateStrategy rateStrategy) {
        return rateStrategy.calculateChargeAmount(enterTime, exitTime);
    }
}
