package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import procedure.Lathe;
import procedure.Procedure;
import javafx.fxml.Initializable;

public class LatheAddPaneController implements Initializable {
	@FXML
	private ContentPaneController contentPaneController;
	@FXML
	private MenuPaneController menuPaneController;
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
    @FXML
    private TextField preparingTime;
    @FXML
    private TextField additionalTime;

	public Button getAddProcedure() {
		return addProcedure;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		latheType.getItems().addAll("Toczenie zewnêtrzne", "Toczenie wewnêtrzne", "Toczenie poprzeczne");
		Button latheAdd = getAddProcedure();
		latheAdd.setOnAction(x -> configureLatheAdd());
	}

	public void configureLatheAdd() {
		String exception = null;
		try {
			Procedure lathe = new Lathe(Double.parseDouble(diameterBeforeLathe.getText()),
					Double.parseDouble(diameterAfterLathe.getText()), 
					Double.parseDouble(latheLength.getText()),
					Double.parseDouble(latheFeed.getText()), 
					Double.parseDouble(latheDepth.getText()),
					Double.parseDouble(latheIdlePath.getText()), 
					Double.parseDouble(latheRpm.getText()),
					Double.parseDouble(latheCost.getText()), 
					latheType.getValue(), 
					latheMachine.getText(),
					Double.parseDouble(preparingTime.getText()),
					Double.parseDouble(additionalTime.getText()));
			Main.mainController.collection.addProcedure(lathe);

		} catch (Exception e) {
			exception = e.getMessage();
			Alert alert = new Alert(AlertType.CONFIRMATION,
					"Z³y format danych. Wpisz zabieg ponownie.\nPrzyczyna: " + e.getMessage(), ButtonType.OK);
			alert.showAndWait();
			if (alert.getResult() == ButtonType.OK) {
				alert.close();
			}
		}
		if (exception == null) {
			Stage stage = (Stage) latheFeed.getScene().getWindow();
			stage.close();
		}
	}
}
