package base_package;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingHistory {
    private String parkingLotId; // will replace with name
    private LocalDateTime enterTime;
    private LocalDateTime exitTime;
    private BigDecimal amountCharged;
}
