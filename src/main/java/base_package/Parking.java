package base_package;

import base_package.rate_strategy.FourWheelerHourlyRateStrategy;
import base_package.rate_strategy.RateStrategyExecutor;
import base_package.rate_strategy.TwoWheelerHourlyRateStrategy;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static base_package.VehicleType.FOUR_WHEELER;
import static base_package.VehicleType.TWO_WHEELER;

@ToString
public class Parking {
    private int twoWheelerCapacity;
    private int fourWheelerCapacity;
    private int usedTwoWheelerSpots = 0;
    private int usedFourWheelerSpots = 0;
    private Map<String, VehicleDetails> parkingMap = new HashMap<>();
    private final String parkingId;
    //parking name

    public Parking(int twoWheelerCapacity, int fourWheelerCapacity, String parkingId) {
        this.twoWheelerCapacity = twoWheelerCapacity;
        this.fourWheelerCapacity = fourWheelerCapacity;
        this.parkingId = parkingId;
    }

    public boolean isParkingSpotAvailable(VehicleType vehicleType) {
        boolean isSpotAvailable = false;
        if (vehicleType.equals(FOUR_WHEELER)) {
            if (usedFourWheelerSpots < fourWheelerCapacity)
                isSpotAvailable = true;
        } else if (vehicleType.equals(TWO_WHEELER)) {
            if (usedTwoWheelerSpots < twoWheelerCapacity)
                isSpotAvailable = true;
        }
        return isSpotAvailable;
    }

    public boolean parkVehicle(VehicleDetails vehicleDetails) {
        boolean isParked = false;
        if (isParkingSpotAvailable(vehicleDetails.getVehicleType())) {
            parkingMap.put(vehicleDetails.getVehicleNumber(), vehicleDetails);
            vehicleDetails.setIsParked(Boolean.TRUE);
            switch (vehicleDetails.getVehicleType()) {
                case TWO_WHEELER:
                    usedTwoWheelerSpots++;
                    break;
                case FOUR_WHEELER:
                    usedFourWheelerSpots++;
                    break;
            }
            System.out.println(new StringBuilder(vehicleDetails.getVehicleNumber()).append(" parked successfully"));
            vehicleDetails.setIsParked(Boolean.TRUE);
            vehicleDetails.setCurrentlyParkedAtLotId(this.getParkingId());
            isParked = true;
        } else System.out.println("no parking slot available for " + vehicleDetails.getVehicleNumber());
        return isParked;
    }

    public BigDecimal unParkVehicle(String vehicleNumber, LocalDateTime exitTime) {
        VehicleDetails vehicleDetails = parkingMap.get(vehicleNumber);
        if (vehicleDetails == null) {
            System.out.println("wrong vehicle number for this parking lot");
            return null;
        }
        switch (vehicleDetails.getVehicleType()) {
            case TWO_WHEELER:
                usedTwoWheelerSpots--;
                break;
            case FOUR_WHEELER:
                usedFourWheelerSpots--;
                break;
        }
        vehicleDetails.setIsParked(Boolean.FALSE);
        vehicleDetails.setCurrentlyParkedAtLotId(null);
        return calculateCharges(vehicleDetails.getEnterTime(), exitTime, vehicleDetails.getVehicleType());
    }

    public BigDecimal calculateCharges(LocalDateTime enterTime, LocalDateTime exitTime, VehicleType vehicleType) {
        BigDecimal payableAmount = null;
        switch (vehicleType) {
            case TWO_WHEELER:
                payableAmount = RateStrategyExecutor.executeRateStrategy(enterTime, exitTime, new TwoWheelerHourlyRateStrategy());
                break;
            case FOUR_WHEELER:
                payableAmount = RateStrategyExecutor.executeRateStrategy(enterTime, exitTime, new FourWheelerHourlyRateStrategy());
                break;
        }
        return payableAmount;
    }

    public String getParkingId() {
        return parkingId;
    }
}
