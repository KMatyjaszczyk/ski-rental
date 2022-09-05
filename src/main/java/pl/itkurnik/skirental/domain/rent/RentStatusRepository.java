package pl.itkurnik.skirental.domain.rent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RentStatusRepository extends JpaRepository<RentStatus, Integer> {
}