package pl.itkurnik.skirental.domain.item.exception;

public class ItemStatusNotFoundException extends RuntimeException {

    public ItemStatusNotFoundException(String name) {
        super(String.format("Item status with name %s not found", name));
    }
}
