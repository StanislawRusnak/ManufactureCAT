package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.Main;
import procedure.ProcessInfo;

public class LogInPaneController implements Initializable {
	@FXML
	private BorderPane logInPane;
	@FXML
	private TextField firstName;
	@FXML
	private TextField secondName;
	@FXML
	private Button logInButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		logInButton.setOnAction(x -> configAddLogInInfo());

		logInPane.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					configAddLogInInfo();
				}
			}
		});
	}

	public void configAddLogInInfo() {
		String exception = null;
		ProcessInfo procInfo = Main.mainController.processInfo;
		try {
			procInfo.setOperatorName(firstName.getText() + " " + secondName.getText());
			Main.mainController.getProcessInfo().setText(procInfo.toString());
		} catch (Exception e) {
			exception = e.getMessage();
			Main.mainController.generateDataWarning(e);
		}
		if (exception == null) {
			Stage stage = (Stage) firstName.getScene().getWindow();
			stage.close();
		}
	}
}
