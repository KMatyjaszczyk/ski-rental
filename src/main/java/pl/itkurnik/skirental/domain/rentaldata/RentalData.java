package pl.itkurnik.skirental.domain.rentaldata;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rental_data")
public class RentalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "company_name", nullable = false, length = 50)
    private String companyName;

    @Column(name = "tax_id", nullable = false, length = 10)
    private String taxId;

    @Column(name = "regon", nullable = false, length = 9)
    private String regon;

    @Column(name = "email_address", length = 254)
    private String emailAddress;

    @Column(name = "city", length = 50)
    private String city;

    @Column(name = "post_code", length = 10)
    private String postCode;

    @Column(name = "street", length = 50)
    private String street;

    @Column(name = "house_number", length = 10)
    private String houseNumber;

    @Column(name = "description")
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;
}