package me.nadd.tilegame;

import java.io.File;

import javafx.scene.media.Media;

public enum Sound {
	
        //  EFFECTS  //
	DEATH("DeathSound.wav"),
        NEXT_LEVEL("NextLevelSounds.mp3"),
        
        //  MUSIC  //
        DEFAULT_GAME_MUSIC("basic1.mp3"),
        HIGHER_LEVEL_MUSIC("basic3.mp3"),
        HORDE_LEVEL_MUSIC("basic5boss.mp3");
	
	private final Media audio;
        
	Sound(String file) {
		File soundFile = new File("build//classes//resources//" + file);
                this.audio = soundFile.exists() ?
                        new Media(soundFile.toURI().toString()) : null;
                
                if(this.audio == null)
                    System.out.println("[MISSING FILE] " + soundFile.getAbsoluteFile());
	}
	
	public Media getAudio() {
		return this.audio;
	}
}
