package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		final String appName = "Manufacture CAT v0.1";
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/view/MainPane.fxml"));
			Scene scene = new Scene(parent);
			primaryStage.setScene(scene);
			primaryStage.setTitle(appName);
			primaryStage.show();
			
		}catch(Exception e) {
			System.out.println("The exception was indicated during app starting.");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);

	}

}
