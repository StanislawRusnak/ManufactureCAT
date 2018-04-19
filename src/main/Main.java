package main;

import java.io.IOException;

import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	public static MainController mainController;
	private static BorderPane mainLayout;
	private Stage stage;
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		final String appName = "Manufacture CAT v0.1";
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/MainPane.fxml"));
			mainLayout = loader.load();
			mainController = loader.getController();
			Scene scene = new Scene(mainLayout);
			primaryStage.setScene(scene);
			primaryStage.setTitle(appName);
			primaryStage.centerOnScreen();
			
		}catch(Exception e) {
			System.out.println("The exception was indicated during app starting.");
			e.printStackTrace();
		}
		createProcessInfoWindow();
		stage.setOnHiding(x->primaryStage.show());
	}

	public void createProcessInfoWindow() {
		Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/ProcessInfoPane.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(parent);
        stage = new Stage();
        stage.setTitle("Ekran powitalny");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
	}
}
