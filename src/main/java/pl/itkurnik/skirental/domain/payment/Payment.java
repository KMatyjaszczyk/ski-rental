package pl.itkurnik.skirental.domain.payment;

import lombok.*;
import org.hibernate.annotations.Type;
import pl.itkurnik.skirental.domain.rent.Rent;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rent_id", nullable = false)
    private Rent rent;

    @Column(name = "amount", nullable = false, precision = 9, scale = 2)
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "realisation_date")
    private LocalTime realisationDate;

    @Column(name = "description")
    @Type(type = "org.hibernate.type.TextType")
    private String description;
}