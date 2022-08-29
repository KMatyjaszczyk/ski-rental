package pl.itkurnik.skirental.domain.rent;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.rent.Rent;

public interface RentRepository extends JpaRepository<Rent, Integer> {
}