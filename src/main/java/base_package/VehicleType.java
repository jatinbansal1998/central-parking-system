package base_package;

public enum VehicleType {
    TWO_WHEELER("Two Wheeler"), FOUR_WHEELER("Four Wheeler");
    private String value;

    private VehicleType(String value) {
        this.value = value;
    }
}
