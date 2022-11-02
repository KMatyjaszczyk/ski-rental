package pl.itkurnik.skirental.domain.rent.util;

import pl.itkurnik.skirental.domain.rent.ClientDocumentType;
import pl.itkurnik.skirental.domain.rent.dto.ClientDocumentTypeInfoDto;

import java.util.List;
import java.util.stream.Collectors;

public class ClientDocumentTypeMapper {

    public static ClientDocumentTypeInfoDto mapToInfoDto(ClientDocumentType clientDocumentType) {
        ClientDocumentTypeInfoDto clientDocumentTypeInfo = new ClientDocumentTypeInfoDto();

        clientDocumentTypeInfo.setId(clientDocumentType.getId());
        clientDocumentTypeInfo.setName(clientDocumentType.getName());
        clientDocumentTypeInfo.setDescription(clientDocumentType.getDescription());

        return clientDocumentTypeInfo;
    }

    public static List<ClientDocumentTypeInfoDto> mapAllToInfoDto(List<ClientDocumentType> clientDocumentTypes) {
        return clientDocumentTypes.stream()
                .map(ClientDocumentTypeMapper::mapToInfoDto)
                .collect(Collectors.toList());
    }
}
