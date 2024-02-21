package com.bananaapps.bananamusic.domain.music;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @Min(1)
    private Long id;

    @NotBlank
    private String location;
    @Min(1)
    private int quantity;

    @Version
    @JsonIgnore
    private int version;

    @ManyToOne
    @JoinColumn(name = "tuneId")
    @NotNull
    private Song item;

    public Backlog(String location, int quantity) {
        // Set the location and quantity
        setLocation(location);
        setQuantity(quantity);
    }

}
