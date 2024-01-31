package com.bananaapps.bananamusic.domain.music;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class PurchaseOrderLineSong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lineNumber;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "orderId")
    private PurchaseOrder order;

    @OneToOne
    @JoinColumn(name = "songId")
    private Song song;
    private Integer quantity;
    private Double unitPrice;


}
