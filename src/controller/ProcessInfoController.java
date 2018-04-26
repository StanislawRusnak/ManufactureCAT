package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
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
    private TextField preparingTimeField;

    @FXML
    private BorderPane infoPane;
    @FXML
    private Button addInfo;
    private ProcessInfo processInfo;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		addInfo.setOnAction(x->configAddProcessInfo());
	
		infoPane.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.ENTER) {
					configAddProcessInfo();
					try {
						event.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	public void configAddProcessInfo() {
		String exception = null;
		try {
		processInfo = new ProcessInfo(
				operatorName.getText(),
				partName.getText(),
				Integer.parseInt(partQuantity.getText()),
				Double.parseDouble(preparingTimeField.getText())
				);
		Main.mainController.getProcessInfo().setText(processInfo.toString());
		Main.mainController.partQ = Integer.parseInt(partQuantity.getText());
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
	}

	public ProcessInfo getProcessInfo() {
		return processInfo;
	}
}
