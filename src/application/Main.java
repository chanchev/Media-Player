package application;
	
import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

/**
 * 
 * @author Chanchev M
 * Date: September 1, 2018
 * Purpose: Main screen with starting display
 */
public class Main extends Application {
	
	MenuBar menu;// top bar
	Menu fileMenu; //menu bar option
	MenuItem openItem; //file menu option
	
	FileChooser choose; //pick the media file
	Player mediaPlayer;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);// current scene being displayed
			
			choose= new FileChooser();
			
			
			menu= new MenuBar();
			fileMenu= new Menu("file");
			
			openItem=new MenuItem("open");
			fileMenu.getItems().add(openItem);
			menu.getMenus().add(fileMenu);
			
			
			openItem.setOnAction((e)->{
				
				try {
					//opens up menu option to pick a media file
					File mediaFile= choose.showOpenDialog(primaryStage);
					if(mediaPlayer != null) {
						mediaPlayer.player.dispose();
					}
					System.out.println(mediaFile.getAbsolutePath()+" "+mediaFile.toURI().toURL().toExternalForm());
					mediaPlayer= new Player(mediaFile.toURI().toURL().toExternalForm());//calls the player class
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				root.setCenter(mediaPlayer);
			});
			
			
			
			root.setTop(menu); //add menu bar to the top
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//method in application class which calls start method
		launch(args);
	}
}
