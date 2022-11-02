package pl.itkurnik.skirental.domain.rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.rent.ClientDocumentType;

public interface ClientDocumentTypeRepository extends JpaRepository<ClientDocumentType, Integer> {
}