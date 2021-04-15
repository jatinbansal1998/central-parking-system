package base_package;

import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@ToString
public class ParkingFactory {
    private static Map<String, Parking> parkingLots = new HashMap<>(); //parkingLotId, Parking

    public static Parking getParkingLotInstance(String parkingId) {
        return ParkingFactory.parkingLots.get(parkingId);
    }

    public static Parking createParkingLotInstance(String parkingId, int twoWheelerCapacity, int fourWheelerCapacity) {
        if (ParkingFactory.parkingLots.containsKey(parkingId)) {
            return ParkingFactory.parkingLots.get(parkingId);
        }
        ParkingFactory.parkingLots.put(parkingId, new Parking(twoWheelerCapacity, fourWheelerCapacity, parkingId));
        return ParkingFactory.parkingLots.get(parkingId);
    }

    public static void printParkingFactory() {
        System.out.println(parkingLots);
    }
}
