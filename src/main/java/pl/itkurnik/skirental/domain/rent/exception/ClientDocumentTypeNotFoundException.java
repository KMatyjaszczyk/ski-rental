package pl.itkurnik.skirental.domain.rent.exception;

public class ClientDocumentTypeNotFoundException extends RuntimeException {

    public ClientDocumentTypeNotFoundException(Integer id) {
        super(String.format("Client document type with id %d not found", id));
    }
}
