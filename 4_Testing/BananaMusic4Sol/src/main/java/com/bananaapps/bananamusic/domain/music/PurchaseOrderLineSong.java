package com.bananaapps.bananamusic.domain.music;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class PurchaseOrderLineSong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    private Long lineNumber;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "orderId")
    private PurchaseOrder order;

    @OneToOne
    @JoinColumn(name = "songId")
    private Song song;

    @Min(1)
    private Integer quantity;

    @Min(0)
    private Double unitPrice;


}
