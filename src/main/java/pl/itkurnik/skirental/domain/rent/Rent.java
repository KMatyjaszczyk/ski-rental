package pl.itkurnik.skirental.domain.rent;

import lombok.*;
import org.hibernate.annotations.Type;
import pl.itkurnik.skirental.domain.payment.Payment;
import pl.itkurnik.skirental.domain.report.Report;
import pl.itkurnik.skirental.domain.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rent")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "rent_date", nullable = false)
    private LocalTime rentDate;

    @Column(name = "planned_return_date", nullable = false)
    private LocalDate plannedReturnDate;

    @Column(name = "end_date")
    private LocalTime endDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rent_status_id", nullable = false)
    private RentStatus rentStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_document_type_id", nullable = false)
    private ClientDocumentType clientDocumentType;

    @Column(name = "client_document_number", nullable = false, length = 20)
    private String clientDocumentNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private User employee;

    @Column(name = "description")
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @OneToMany(mappedBy = "rent")
    private Set<Payment> payments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "rent")
    private Set<Report> reports = new LinkedHashSet<>();

    @OneToMany(mappedBy = "rent")
    private Set<RentItem> rentItems = new LinkedHashSet<>();
}