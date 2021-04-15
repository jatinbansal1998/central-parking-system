package base_package;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Register {
    private Map<String, List<ParkingHistory>> parkingHistory = new HashMap<>(); //Vehicle number , vehicle details
    private static Register register = null;

    private Register() {

    }

    public static Register getResisterInstance() {
        if (register == null)
            Register.register = new Register();
        return Register.register;
    }

    public void parkVehicle(VehicleDetails vehicleDetails, String parkingId) {
        Parking parking = ParkingFactory.getParkingLotInstance(parkingId);
        if (parking == null) {
            System.out.println("Invalid Parking ID");
            return;
        }
        boolean isParkedSuccessfully = parking.parkVehicle(vehicleDetails);
        if (isParkedSuccessfully) {
            ParkingHistory newParkingHistory = ParkingHistory.builder().parkingLotId(parking.getParkingId()).enterTime(vehicleDetails.getEnterTime()).build();
            if (parkingHistory.containsKey(vehicleDetails.getVehicleNumber())) {
            } else {
                parkingHistory.put(vehicleDetails.getVehicleNumber(), new ArrayList<>());
            }
            parkingHistory.get(vehicleDetails.getVehicleNumber()).add(newParkingHistory);
        }
    }

    public BigDecimal unParkVehicle(String vehicleNumber, LocalDateTime exitTime, String parkingId) {
        Parking parking = ParkingFactory.getParkingLotInstance(parkingId);
        if (parking == null) {
            System.out.println("Invalid Parking ID");
            return BigDecimal.ZERO;
        }
        BigDecimal returnedAmount = parking.unParkVehicle(vehicleNumber, exitTime);
        if (returnedAmount != null) {
            ParkingHistory lastPark = parkingHistory.get(vehicleNumber).get(parkingHistory.get(vehicleNumber).size() - 1);
            lastPark.setExitTime(exitTime);
            lastPark.setAmountCharged(returnedAmount);
        }
        return returnedAmount;
    }

    public void printRegister() {
        System.out.println(this.parkingHistory);
    }

    public void printRegisterForVehicleNumber(String vehicleNumber) {
        System.out.println(this.parkingHistory.get(vehicleNumber));
    }
}
