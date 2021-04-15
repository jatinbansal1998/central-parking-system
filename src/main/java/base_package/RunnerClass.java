package base_package;

import java.time.LocalDateTime;

import static base_package.VehicleType.FOUR_WHEELER;
import static base_package.VehicleType.TWO_WHEELER;

public class RunnerClass {
    public static void main(String[] args) {
        Parking mall = ParkingFactory.createParkingLotInstance("mall001", 2, 2);
        Parking hospital = ParkingFactory.createParkingLotInstance("hospital002", 1, 3);
        Parking restaurant = ParkingFactory.createParkingLotInstance("restaurant003", 1, 1);
        Register register = Register.getResisterInstance();


        //park vehicle 1 four wheeler at hospital
        VehicleDetails vehicleDetails1 = new VehicleDetails("DL1", "LIC1", LocalDateTime.of(2021, 4, 14, 10, 10), FOUR_WHEELER);
        register.parkVehicle(vehicleDetails1, hospital.getParkingId());

        //park vehicle 2 two wheeler at hospital
        VehicleDetails vehicleDetails2 = new VehicleDetails("DL2", "LIC2", LocalDateTime.of(2021, 4, 14, 11, 10), TWO_WHEELER);
        register.parkVehicle(vehicleDetails2, hospital.getParkingId());

        //park vehicle 3 two wheeler at hospital
        VehicleDetails vehicleDetails3 = new VehicleDetails("DL3", "LIC3", LocalDateTime.of(2021, 4, 14, 15, 10), TWO_WHEELER);
        register.parkVehicle(vehicleDetails3, hospital.getParkingId());

        //un park vehicle 2
        System.out.println(new StringBuilder("Charges for ").append(vehicleDetails2.getVehicleNumber()).append(" ").append(register.unParkVehicle(vehicleDetails2.getVehicleNumber(), LocalDateTime.of(2021, 4, 14, 14, 10), hospital.getParkingId())));

        //park vehicle 3 two wheeler at hospital
        register.parkVehicle(vehicleDetails3, hospital.getParkingId());
        register.parkVehicle(vehicleDetails2, mall.getParkingId());
        register.printRegister();
        register.printRegisterForVehicleNumber("DL2");
    }
}
