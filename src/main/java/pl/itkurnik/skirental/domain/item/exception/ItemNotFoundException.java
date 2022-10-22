package pl.itkurnik.skirental.domain.item.exception;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(Integer id) {
        super(String.format("Item with id %s not found", id));
    }
}
