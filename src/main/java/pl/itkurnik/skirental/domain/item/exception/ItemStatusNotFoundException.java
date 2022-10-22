package pl.itkurnik.skirental.domain.item.exception;

public class ItemStatusNotFoundException extends RuntimeException {

    public ItemStatusNotFoundException(Integer id) {
        super(String.format("Item status with id %d not found", id));
    }

    public ItemStatusNotFoundException(String name) {
        super(String.format("Item status with name %s not found", name));
    }
}
