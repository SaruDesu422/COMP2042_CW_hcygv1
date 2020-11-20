package view;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MyStage extends World{

	MediaPlayer mediaPlayer;
	
	/**
	* A pane used for each levels.
	* <pre>
	* Method:<br>playMusic()<br>stopMusic()
	* </pre>
    *
	*/
	public MyStage() {}
	
	/**
	* Plays the preselected audio file.
    *
	*/
	public void playMusic() {
		String musicFile = "media/audio/Frogger Main Song Theme (loop).mp3";
		Media sound = new Media(new File(musicFile).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	    mediaPlayer.play();
	}
	
	/**
	* Stops the currently playing audio file.
    *
	*/
	public void stopMusic() {
		mediaPlayer.stop();
	}
	
	@Override
	public void act(long now) {}
}
