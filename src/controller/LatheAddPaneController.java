package controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

public class LatheAddPaneController implements Initializable {
	@FXML
	private ComboBox<String> latheType;

	@FXML
	private TextField latheMachine;

	@FXML
	private TextField latheCost;

	@FXML
	private TextField diameterBeforeLathe;

	@FXML
	private TextField diameterAfterLathe;

	@FXML
	private TextField latheLength;

	@FXML
	private TextField latheIdlePath;

	@FXML
	private TextField latheFeed;

	@FXML
	private TextField latheDepth;

	@FXML
	private TextField latheRpm;
    @FXML
    private Button addProcedure;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		latheType.getItems().addAll("Toczenie zewnêtrzne","Toczenie wewnêtrzne","Toczenie poprzeczne");

	}
	
	public void createLatheWindow() {
		Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/LatheAddPane.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(parent,700,600);
        Stage stage = new Stage();
        stage.setTitle("Dodawanie zabiegu toczenia");
        stage.setScene(scene);
        stage.show();
	}
}
