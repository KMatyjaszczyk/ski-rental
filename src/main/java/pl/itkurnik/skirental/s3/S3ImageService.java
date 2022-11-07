package pl.itkurnik.skirental.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class S3ImageService {
    private final AmazonS3 s3client;

    @Value("${s3.bucket-name:bucket-name}")
    private String bucketName;

    public List<S3ObjectSummary> getImageSummaries() {
        return s3client.listObjects(bucketName).getObjectSummaries();
    }

    public void putImage(MultipartFile image, String imageUuid) {
        try {
            InputStream imageInputStream = image.getInputStream();
            ObjectMetadata objectMetadata = new ObjectMetadata();

            PutObjectRequest request = new PutObjectRequest(
                    bucketName, imageUuid, imageInputStream, objectMetadata);
            s3client.putObject(request);
        } catch (Exception e) {
            throw new S3Exception(e);
        }
    }
}
