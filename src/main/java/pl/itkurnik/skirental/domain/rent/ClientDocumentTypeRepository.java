package pl.itkurnik.skirental.domain.rent;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.rent.ClientDocumentType;

public interface ClientDocumentTypeRepository extends JpaRepository<ClientDocumentType, Integer> {
}