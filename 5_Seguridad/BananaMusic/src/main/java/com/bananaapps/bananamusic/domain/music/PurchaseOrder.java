package com.bananaapps.bananamusic.domain.music;

import com.bananaapps.bananamusic.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int status;
    private boolean valid;
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<PurchaseOrderLineSong> lineSongs;


    public boolean isValid() {
        return (orderDate.isBefore(LocalDate.now()) && user != null && user.getId() > 0L && lineSongs.size() > 0);
    }

    public double getTotalPrice() {
        if (lineSongs != null) {
            lineSongs.stream().reduce((acc, line) -> {
                return acc + line.getQuantity() * line.getUnitPrice();
            });
        } else return 0;
    }
}
