package pl.itkurnik.skirental.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class S3ImageService {
    private final AmazonS3 s3client;

    @Value("${s3.bucket-name:bucket-name}")
    private String bucketName;

    public void upload(MultipartFile image, String imageUuid) {
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

    public String receiveUrl(String imageUuid) {
        try {
            return s3client.getUrl(bucketName, imageUuid).toString();
        } catch (Exception e) {
            throw new S3Exception(e);
        }
    }

    public void delete(String imageUuid) {
        try {
            DeleteObjectRequest request = new DeleteObjectRequest(
                    bucketName, imageUuid);
            s3client.deleteObject(request);
        } catch (Exception e) {
            throw new S3Exception(e);
        }
    }
}
