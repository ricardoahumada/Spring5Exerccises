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
public class Backlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;
    private int quantity;

    @Version
    private int version;

    @ManyToOne
    @JoinColumn(name = "tuneId")
    private Song item;

    public Backlog(String location, int quantity) {
        // Set the location and quantity
        setLocation(location);
        setQuantity(quantity);
    }

}
