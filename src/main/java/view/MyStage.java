package view;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MyStage extends World{

	MediaPlayer mediaPlayer;
	
	/**
	 * A pane used for each levels.
	 */
	public MyStage() {}
	
	/**
	 * Plays the preselected audio file on loop.
	 */
	public void playMusic() {
		String musicFile = "media/audio/Frogger Main Song Theme (loop).mp3";
		Media sound = new Media(new File(musicFile).toURI().toString());
		this.mediaPlayer = new MediaPlayer(sound);
		this.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	    this.mediaPlayer.play();
	}
	
	/**
	 * Stops the currently playing audio file.
	 */
	public void stopMusic() {
		this.mediaPlayer.stop();
	}
	
	@Override
	public void act(long now) {}
}
