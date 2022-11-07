package pl.itkurnik.skirental.util;

import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public class MultipartFileUtils {

    public static String extractExtension(MultipartFile file) {
        String fileName = Optional.ofNullable(file.getOriginalFilename())
                .orElseThrow(() -> new RuntimeException("File name is null"));

        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
