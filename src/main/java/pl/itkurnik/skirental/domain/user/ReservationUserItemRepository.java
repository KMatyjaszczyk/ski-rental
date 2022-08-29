package pl.itkurnik.skirental.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.user.ReservationUserItem;

public interface ReservationUserItemRepository extends JpaRepository<ReservationUserItem, Integer> {
}