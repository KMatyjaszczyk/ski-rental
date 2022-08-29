package pl.itkurnik.skirental.domain.item;

import lombok.*;
import org.hibernate.annotations.Type;
import pl.itkurnik.skirental.domain.equipment.EquipmentCategory;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "size")
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "size", nullable = false, length = 15)
    private String size;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "equipment_category_id", nullable = false)
    private EquipmentCategory equipmentCategory;

    @Column(name = "description")
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;
}