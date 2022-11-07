package pl.itkurnik.skirental.domain.equipment.util;

import pl.itkurnik.skirental.domain.equipment.dto.ImageUrlModel;
import pl.itkurnik.skirental.domain.equipment.dto.ImageUrlResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ImageUrlMapper {

    public static ImageUrlResponse mapToResponse(ImageUrlModel imageUrlModel) {
        ImageUrlResponse response = new ImageUrlResponse();

        response.setId(imageUrlModel.getId());
        response.setEquipmentId(imageUrlModel.getEquipmentId());
        response.setUrl(imageUrlModel.getUrl());

        return response;
    }

    public static List<ImageUrlResponse> mapAllToResponse(List<ImageUrlModel> imageUrlModels) {
        return imageUrlModels.stream()
                .map(ImageUrlMapper::mapToResponse)
                .collect(Collectors.toList());
    }
}
