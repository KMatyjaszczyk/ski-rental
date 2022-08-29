package pl.itkurnik.skirental.domain.equipment;

import lombok.*;
import org.hibernate.annotations.Type;
import pl.itkurnik.skirental.domain.item.Size;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "equipment_category")
public class EquipmentCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description")
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;

    @OneToMany(mappedBy = "equipmentCategory")
    private Set<Size> sizes = new LinkedHashSet<>();
}