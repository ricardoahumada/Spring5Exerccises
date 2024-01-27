package com.bananaapps.bananamusic.domain.music;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="Item")
@NamedQuery(name="MusicItem.findByArtist",
query="Select mi " +
      "FROM MusicItem mi " +
      " WHERE mi.artist = :artist")
public class MusicItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String title, artist;
	private LocalDate releaseDate;
	@Column(name="cost")
	private BigDecimal price;
	@Enumerated(EnumType.STRING)
	private MusicCategory musicCategory;
	
	@Version
	private int        version;	


	@OneToMany(mappedBy="item", cascade=CascadeType.ALL, orphanRemoval=true )	
	private Collection<Inventory> inventoryRecords = new ArrayList<Inventory>();
	public Collection<Inventory> getInventoryRecords() { 
		return inventoryRecords;
	}
	public void setInventoryRecords(Collection<Inventory> i) { inventoryRecords = i; }

	public void addInventoryRecord(String location, int quantity) {
		Inventory iv = new Inventory(location, quantity);

		getInventoryRecords().add(iv);
		iv.setItem(this);
	}	
	
	@Transient
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public MusicItem(Long id) {
		setId(id);
	}

	public Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}


	public MusicItem(String title, String artist, String releaseDate,
			BigDecimal price, MusicCategory musicCategory) {
		this.setTitle(title);
		this.setArtist(artist);
		this.setReleaseDateAsString(releaseDate);
		this.setPrice(price);
		this.setMusicCategory(musicCategory);
	}

	public MusicItem(Long id, String title, String artist, String releaseDate,
			BigDecimal price, MusicCategory musicCategory) {
		this.setId(id);
		this.setTitle(title);
		this.setArtist(artist);
		this.setReleaseDateAsString(releaseDate);
		this.setPrice(price);
		this.setMusicCategory(musicCategory);
	}

	public void setReleaseDateAsString(String releaseDateString) {
		releaseDate = LocalDate.parse(releaseDateString, formatter);
	}

	@Override
	public boolean equals(Object o) {
		boolean result = false;
		if (o == this) {
			result = true;
		}
		else if (o instanceof MusicItem) {
			MusicItem other = (MusicItem) o;
			result = Objects.equals(this.getTitle(),       other.getTitle()) &&
					Objects.equals(this.getArtist(),      other.getArtist()) &&
					Objects.equals(this.getReleaseDate(), other.getReleaseDate());
		}
		return result; 
	}


	@Override
	public int hashCode() {
		return Objects.hash(this.title, this.artist, this.releaseDate);
	}

}
