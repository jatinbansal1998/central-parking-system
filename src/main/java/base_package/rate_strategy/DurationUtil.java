package base_package.rate_strategy;

import java.time.Duration;
import java.time.LocalDateTime;

public class DurationUtil {
    public static Duration getDuration(LocalDateTime startTime, LocalDateTime endTime) {
        return Duration.between(startTime, endTime);
    }
}
