package main;

import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	public static MainController mainController;
	private static BorderPane mainLayout;

	@Override
	public void start(Stage primaryStage) throws Exception {
		final String appName = "Manufacture CAT v0.1";
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/MainPane.fxml"));
			//Parent parent = FXMLLoader.load(getClass().getResource("/view/MainPane.fxml"));
			mainLayout = loader.load();
			mainController = loader.getController();
			Scene scene = new Scene(mainLayout);
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
