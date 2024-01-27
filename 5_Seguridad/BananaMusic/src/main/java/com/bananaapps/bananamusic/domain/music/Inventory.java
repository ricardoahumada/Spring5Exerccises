package com.bananaapps.bananamusic.domain.music;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "INVENTORY")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;
    private int quantity;

    @Version
    private int version;

    @ManyToOne
    @JoinColumn(name = "itemId")
    private MusicItem item;

    public MusicItem getItem() {
        return item;
    }

    public void setItem(MusicItem mi) {
        item = mi;
    }


    public Inventory(String location, int quantity) {
        // Set the location and quantity
        setLocation(location);
        setQuantity(quantity);
    }


}
