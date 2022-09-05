package pl.itkurnik.skirental.domain.rentaldata;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalDataRepository extends JpaRepository<RentalData, Integer> {
}