package com.bananaapps.bananamusic.domain.music;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PurchaseOrderLineSong {
    private Long lineNumber;
    private Song song;
    private Integer quantity;
    private Double unitPrice;


}
