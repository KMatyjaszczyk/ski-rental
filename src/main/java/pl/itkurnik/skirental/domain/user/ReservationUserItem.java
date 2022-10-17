package pl.itkurnik.skirental.domain.user;

import lombok.*;
import pl.itkurnik.skirental.domain.item.Item;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reservation_user_item")
public class ReservationUserItem { // TODO KM to be removed
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(name = "reserved_from", nullable = false)
    private LocalDate reservedFrom;

    @Column(name = "reserved_to", nullable = false)
    private LocalDate reservedTo;
}