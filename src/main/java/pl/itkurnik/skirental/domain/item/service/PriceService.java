package pl.itkurnik.skirental.domain.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.item.Price;
import pl.itkurnik.skirental.domain.item.exception.PriceNotFoundException;
import pl.itkurnik.skirental.domain.item.repository.PriceRepository;

@Service
@RequiredArgsConstructor
public class PriceService {
    private final PriceRepository priceRepository;

    public Price findById(Integer id) {
        return priceRepository.findById(id)
                .orElseThrow(() -> new PriceNotFoundException(id));
    }
}
