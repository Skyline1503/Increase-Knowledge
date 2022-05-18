package modele;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URISyntaxException;

public class MusicPlayer {
	private Media media;
	private MediaPlayer mediaPlayer;

	// The constructor of the class MusicPlayer. It is called when we create a new object of the class
	// MusicPlayer.
	public MusicPlayer(String son) {
		setMedia(son);
		setMediaPlayer(getMedia());
		getMediaPlayer().play();
		getMediaPlayer().setAutoPlay(true);
		getMediaPlayer().setCycleCount(MediaPlayer.INDEFINITE);
	}

	/**
	 * This function returns the media object.
	 * 
	 * @return The media object.
	 */
	public Media getMedia() {
		return media;
	}

	/**
	 * It takes a string as a parameter, creates a new media object with the string as the file path, and
	 * returns the media object
	 * 
	 * @param son The name of the sound file.
	 * @return The media object is being returned.
	 */
	public Media setMedia(String son) {
		media = null;
		try {
			media = new Media(getClass().getResource(son).toURI().toString());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return media;
	}

	/**
	 * This function returns the media player.
	 * 
	 * @return The mediaPlayer object.
	 */
	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

	/**
	 * `setMediaPlayer()` sets the media player to the media
	 * 
	 * @param m The media file to be played.
	 * @return The mediaPlayer object is being returned.
	 */
	public MediaPlayer setMediaPlayer(Media m)
	{
		return mediaPlayer = new MediaPlayer(getMedia());
	}
}