package pl.itkurnik.skirental.s3;

public class S3Exception extends RuntimeException {

    public S3Exception(Exception e) {
        super("Error during S3 integration", e);
    }
}
