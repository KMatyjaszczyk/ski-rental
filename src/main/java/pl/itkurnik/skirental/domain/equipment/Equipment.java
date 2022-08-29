package pl.itkurnik.skirental.domain.equipment;

import lombok.*;
import org.hibernate.annotations.Type;
import pl.itkurnik.skirental.domain.item.Item;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "model", nullable = false, length = 50)
    private String model;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "equipment_category_id", nullable = false)
    private EquipmentCategory equipmentCategory;

    @Column(name = "description")
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;

    @OneToMany(mappedBy = "equipment")
    private Set<Item> items = new LinkedHashSet<>();
}