package base_package.rate_strategy;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface IRateStrategy {
    BigDecimal calculateChargeAmount(LocalDateTime enterTime, LocalDateTime exitTime);
}
