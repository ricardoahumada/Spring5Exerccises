package com.bananaapps.bananamusic.domain.music;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "DownloadableItem")
@PrimaryKeyJoinColumn(name = "tuneId")
public class OfflineSong extends Song {
    @Pattern(regexp = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")
    private String url;

    @NotBlank
    private String fileType;

    public OfflineSong(String num, String title,
                       String artist, LocalDate releaseDate, BigDecimal price) {
        setTitle(title);
        setArtist(artist);
        setReleaseDate(releaseDate);
        setPrice(price);
    }

}
