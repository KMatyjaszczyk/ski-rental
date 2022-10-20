package pl.itkurnik.skirental.domain.equipment.exception;

public class SizeNotFoundException extends RuntimeException {

    public SizeNotFoundException(Integer id) {
        super(String.format("Size with id %s not found", id));
    }
}
