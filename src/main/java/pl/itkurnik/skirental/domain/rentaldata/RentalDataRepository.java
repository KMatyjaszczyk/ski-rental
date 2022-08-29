package pl.itkurnik.skirental.domain.rentaldata;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.rentaldata.RentalData;

public interface RentalDataRepository extends JpaRepository<RentalData, Integer> {
}