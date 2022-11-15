package pl.itkurnik.skirental.s3;

public class S3Exception extends RuntimeException {

    public S3Exception(Exception e) {
        super(String.format("Error during S3 integration: %s", e.getMessage()), e);
    }
}
