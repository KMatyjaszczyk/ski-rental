package pl.itkurnik.skirental.domain.rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.rent.Rent;

import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Integer> {

    List<Rent> findAllByClient_Id(Integer clientId);
}