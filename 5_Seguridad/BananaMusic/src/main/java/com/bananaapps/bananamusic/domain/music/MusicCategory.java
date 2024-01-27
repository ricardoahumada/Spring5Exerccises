package com.bananaapps.bananamusic.domain.music;

public enum MusicCategory {
	ALTERNATIVE ("Alternative"), 
	BLUES ("Blues"), 
	CLASSICAL ("Classical"), 
	CLASSIC_ROCK ("Classic Rock"), 
	COUNTRY ("Country"), 
	JAZZ ("Jazz"), 
	POP("Pop"),
	RAP ("Rap"),
	ROCK ("Rock");

	private String description;

	private MusicCategory (String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
