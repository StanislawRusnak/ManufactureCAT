package controller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class ControlPaneController implements Initializable {
	@FXML
	private MainController main;
	@FXML
	private Button loadShortMenuItem;

	@FXML
	private Button saveShortMenuItem;

	@FXML
	private Button printShortMenuItem;

	@FXML
	private Button latheShortMenuItem;

	@FXML
	private Button drillShortMenuItem;

	@FXML
	private Button cutShortMenuItem;

	@FXML
	private Button otherShortMenuItem;

	@Override
	public void initialize(URL location, ResourceBundle resources) { 
		latheShortMenuItem.setOnAction(x -> main.menuPaneController.createLatheWindow());
	}

	public void init(MainController mainController) {
		main = mainController;
		
	}
	public Button getLatheShortMenuItem() {
		return latheShortMenuItem;
	}
}
