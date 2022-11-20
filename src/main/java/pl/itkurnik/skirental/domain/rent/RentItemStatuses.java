package pl.itkurnik.skirental.domain.rent;

public enum RentItemStatuses {
    RENTED("RENTED"),
    FINISHED("FINISHED");

    private final String name;

    RentItemStatuses(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
