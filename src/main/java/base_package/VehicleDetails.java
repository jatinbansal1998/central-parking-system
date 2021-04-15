package base_package;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
public class VehicleDetails {
    private String vehicleNumber;
    private String driverLicenceNumber;
    private Boolean isParked;
    private String currentlyParkedAtLotId;
    private LocalDateTime enterTime;
    private LocalDateTime exitTime;
    private VehicleType vehicleType;

    public VehicleDetails(String vehicleNumber, String driverLicenceNumber, LocalDateTime enterTime, VehicleType vehicleType) {
        this.vehicleNumber = vehicleNumber;
        this.driverLicenceNumber = driverLicenceNumber;
        this.enterTime = enterTime;
        this.vehicleType = vehicleType;
    }
}
