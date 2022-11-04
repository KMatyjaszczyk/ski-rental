package pl.itkurnik.skirental.domain.report;

import lombok.*;
import org.hibernate.annotations.Type;
import pl.itkurnik.skirental.domain.rent.Rent;

import javax.persistence.*;
import java.time.LocalTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rent_id", nullable = false)
    private Rent rent;

    @Column(name = "report_date", nullable = false)
    private LocalTime reportDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "report_type_id", nullable = false)
    private ReportType reportType;

    @Column(name = "description", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "report_status_id", nullable = false)
    private ReportStatus reportStatus;
}