package pl.itkurnik.skirental.domain.rent;

public enum RentStatuses {
    RENTED("RENTED"),
    FINISHED("FINISHED"),
    PAID("PAID");

    private final String name;

    RentStatuses(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
