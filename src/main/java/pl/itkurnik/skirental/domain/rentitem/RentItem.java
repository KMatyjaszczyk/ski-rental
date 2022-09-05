package pl.itkurnik.skirental.domain.rentitem;

import lombok.*;
import org.hibernate.annotations.Type;
import pl.itkurnik.skirental.domain.item.Item;
import pl.itkurnik.skirental.domain.rent.Rent;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rent_item")
public class RentItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rent_id", nullable = false)
    private Rent rent;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(name = "rent_price", nullable = false, precision = 8, scale = 2)
    private BigDecimal rentPrice;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rent_item_status_id", nullable = false)
    private RentItemStatus rentItemStatus;

    @Column(name = "rented_from", nullable = false)
    private Instant rentedFrom;

    @Column(name = "rented_to")
    private Instant rentedTo;

    @Column(name = "paid_amount", precision = 8, scale = 2)
    private BigDecimal paidAmount;

    @Column(name = "description")
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;
}