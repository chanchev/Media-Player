package application;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
/**
 * 
 * @author Chanchev M
 * Date: September 1, 2018
 * Purpose: Obtains media path and plays the video
 */
public class Player extends BorderPane {
	Media media;
	MediaPlayer player;
	MediaView view;
	Pane mpane;
	MediaBar mediaBar;
	
	public Player(String file) {
		media= new Media(file); //loads the file
		player = new MediaPlayer(media); //plays the video
		view = new MediaView(player); //displays the video
		mpane= new Pane();
		mediaBar=new MediaBar(player); 
		mpane.getChildren().add(view);
		setCenter(mpane);
		setBottom(mediaBar);
		player.play();
		
		
	}
}
