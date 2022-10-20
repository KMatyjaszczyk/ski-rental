package pl.itkurnik.skirental.domain.item;

import lombok.*;
import org.hibernate.annotations.Type;
import pl.itkurnik.skirental.domain.equipment.Equipment;
import pl.itkurnik.skirental.domain.equipment.Size;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "size_id", nullable = false)
    private Size size;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_status_id", nullable = false)
    private ItemStatus itemStatus;

    @Column(name = "last_service_date")
    private LocalDate lastServiceDate;

    @Column(name = "purchase_price", nullable = false, precision = 8, scale = 2)
    private BigDecimal purchasePrice;

    @Column(name = "description")
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;

    @OneToMany(mappedBy = "item")
    private Set<Price> prices = new LinkedHashSet<>();
}