package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
/**
 * 
 * @author Chanchev M
 * Date: September 2, 2018
 * Purpose: controls of the file
 */
public class MediaBar extends HBox{
	
	Slider time;//controls time of video
	Slider volume;//controls volume of video
	
	Button pause; //stops and starts video
	Label volumeLBL;
	Button fwd;
	Button bwd;
	
	
	MediaPlayer player;
	
	public MediaBar(MediaPlayer play) {
		player= play;
		volume=new Slider();
		time= new Slider();
		pause= new Button("||");
		fwd= new Button("->");
		
		volumeLBL= new Label("Volume :");
		volume.prefWidth(70);
		volume.setMinWidth(30);
		volume.setValue(100);
		
		//display of menubar
		setAlignment(Pos.CENTER);
		setStyle("-fx-background-color:white");
		setPadding(new Insets(10,10,10,10));
		
		
		//pause or play the video
		pause.setOnAction((e)->{
			Status status =player.getStatus();
			
			if(status== Status.PLAYING) {
				player.pause();
				pause.setText(">");
				
			}else if(status==Status.PAUSED) {
				player.play();
				pause.setText("||");
			}
		});
		fwd.setOnAction((e)->{
			player.setRate(2);
		});
		
		
		
		//automatically moves the slider
		player.currentTimeProperty().addListener((o) -> {
			time.setValue(player.getCurrentTime().toMillis()/player.getTotalDuration().toMillis()*100);
			
		});
		//adding features to the media bar
		getChildren().add(pause);
		getChildren().add(volumeLBL);
		getChildren().add(volume);
		getChildren().add(time);
		getChildren().add(fwd);
	
		
		//changes time of video
		time.valueProperty().addListener((o)->{
			//stops lag
			if(time.isPressed()) {
				player.seek(player.getMedia().getDuration().multiply(time.getValue()/100));
			}
		});
		
		//adjusts volume
		volume.valueProperty().addListener((o)->{
			player.setVolume(volume.getValue()/100);
		});
	}
}
