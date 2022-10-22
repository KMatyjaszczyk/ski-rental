package pl.itkurnik.skirental.domain.item.util;

import pl.itkurnik.skirental.domain.item.Price;
import pl.itkurnik.skirental.domain.item.dto.PriceInfoDto;

import java.util.List;
import java.util.stream.Collectors;

public class PriceMapper {

    public static PriceInfoDto mapToInfoDto(Price price) {
        PriceInfoDto priceInfo = new PriceInfoDto();

        priceInfo.setId(price.getId());
        priceInfo.setItemId(price.getItem().getId());
        priceInfo.setPriceValue(price.getPriceValue());
        priceInfo.setValidFrom(price.getValidFrom());
        priceInfo.setValidTo(price.getValidTo());
        priceInfo.setDescription(price.getDescription());

        return priceInfo;
    }

    public static List<PriceInfoDto> mapAllToInfoDto(List<Price> prices) {
        return prices.stream()
                .map(PriceMapper::mapToInfoDto)
                .collect(Collectors.toList());
    }
}
