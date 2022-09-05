package pl.itkurnik.skirental.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationUserItemRepository extends JpaRepository<ReservationUserItem, Integer> {
}