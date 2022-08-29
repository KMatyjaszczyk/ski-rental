package pl.itkurnik.skirental.domain.user;

import pl.itkurnik.skirental.domain.item.Item;
import pl.itkurnik.skirental.domain.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservation_user_item")
public class ReservationUserItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(name = "reserved_from", nullable = false)
    private LocalDate reservedFrom;

    @Column(name = "reserved_to", nullable = false)
    private LocalDate reservedTo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public LocalDate getReservedFrom() {
        return reservedFrom;
    }

    public void setReservedFrom(LocalDate reservedFrom) {
        this.reservedFrom = reservedFrom;
    }

    public LocalDate getReservedTo() {
        return reservedTo;
    }

    public void setReservedTo(LocalDate reservedTo) {
        this.reservedTo = reservedTo;
    }

}