package me.nadd.tilegame;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public enum Sound {
	
	DEATH("DeathSound.wav");
	
	private Media audio;
	Sound(String file) {
		this.audio = new Media(new File("src/resources/" + file).toURI().toString());
	}
	
	public void play() {
		new MediaPlayer(this.audio).play();
	}
}
