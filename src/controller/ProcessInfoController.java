package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.Main;
import procedure.ProcessInfo;

public class ProcessInfoController implements Initializable {
    @FXML
    private TextField partName;
    @FXML
    private TextField partQuantity;
    @FXML
    private TextField operatorName;
    @FXML
    private Button addInfo;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		addInfo.setOnAction(x->configAddProcessInfo());
	}

	public void configAddProcessInfo() {
		String exception = null;
		try {
		ProcessInfo processInfo = new ProcessInfo(
				operatorName.getText(),
				partName.getText(),
				Integer.parseInt(partQuantity.getText())
				);
		Main.mainController.getProcessInfo().setText(processInfo.toString());
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
			Stage stage= (Stage) partName.getScene().getWindow();
			stage.close();
		}
		/*
		Stage mainStage= (Stage) Main.mainController.getProcessInfo().getScene().getWindow(); //pobranie referencji na okno g³owne
		mainStage.show(); 	//pokazanie okna g³ownego  */
	}
}
