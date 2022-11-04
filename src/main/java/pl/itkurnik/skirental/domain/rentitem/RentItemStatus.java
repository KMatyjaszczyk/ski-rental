package pl.itkurnik.skirental.domain.rentitem;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rent_item_status")
public class RentItemStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description")
    @Type(type = "org.hibernate.type.TextType")
    private String description;
}