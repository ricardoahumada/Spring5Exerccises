package com.bananaapps.bananamusic.domain.music;

import com.bananaapps.bananamusic.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
    @Min(1)
    private Long id;

    @Min(0)
    @Max(1)
    private int status;

    @NotNull
    private boolean valid;
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PurchaseOrderLineSong> lineSongs;


    public boolean isValid() {
        return (orderDate.isBefore(LocalDate.now()) && user != null && user.getId() > 0L && lineSongs.size() > 0);
    }

    public double getTotalPrice() {
        double result = 0;
        if (lineSongs != null) {
            for (PurchaseOrderLineSong line : lineSongs) {
                result += line.getQuantity() * line.getUnitPrice();
            }
        }
        return result;
    }

    public PurchaseOrder(Long id, int status, LocalDate orderDate, User user, List<PurchaseOrderLineSong> lineSongs) {
        this.id = id;
        this.status = status;
        this.orderDate = orderDate;
        this.user = user;
        this.lineSongs = lineSongs;
    }
}
