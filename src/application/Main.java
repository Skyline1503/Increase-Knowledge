package application;

import java.io.IOException;

import export.ReadChallenges;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import json.Link;

public class Main extends Application {
	// This is a variable that is used to store the primary stage of the application.
	private static Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {

		// This is the code that calls the JSON file and sets the values of the variables.
		Link.jsonCall();
		
        //Read the file txt
		try {
			ReadChallenges.readFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// This is the code that sets the title of the game and the icon of the game.
		getPrimaryStage().setTitle("Increase Knowledge");
		getPrimaryStage().getIcons().add(new Image("img/logo/logoNoText.png"));

		// This is the code that creates the scene of the game.
		try {
			AppMainMenuBp root = new AppMainMenuBp();
			Scene scene = new Scene(root);
			// This is setting the height, width, minimum height, minimum width, full screen, style class, style
			// sheet and scene of the primary stage.
			getPrimaryStage().setHeight(1800);
			getPrimaryStage().setWidth(1000);
			getPrimaryStage().setMinWidth(1800);
			getPrimaryStage().setMinHeight(1000);
			getPrimaryStage().setFullScreen(true);
			root.getStyleClass().add("sceneBack");
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			getPrimaryStage().setScene(scene);
			getPrimaryStage().show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function launches the program
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Returns the primary stage of the application
	 * 
	 * @return The stage object.
	 */
	public static Stage getPrimaryStage() {
		if(primaryStage == null)
		{
			primaryStage = new Stage();
		}
		return primaryStage;
	}
}