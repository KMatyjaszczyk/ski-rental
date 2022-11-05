package pl.itkurnik.skirental.domain.equipment.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@RequiredArgsConstructor
public class CreateEquipmentImageRequest {
    private final Integer equipmentId;
    private final MultipartFile image;
}
