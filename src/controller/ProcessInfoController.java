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
import procedure.Procedure;
import procedure.ProcessInfo;

public class ProcessInfoController implements Initializable {
    @FXML
    private BorderPane processPane;
    @FXML
    private TextField partName;

    @FXML
    private TextField partQuantity;

    @FXML
    private TextField halfProduct;
    
    @FXML
    private TextField halfProductCost;

    @FXML
    private TextField preparingTimeField;

    @FXML
    private Button addInfo;
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		addInfo.setOnAction(x->configAddProcessInfo());
		processPane.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
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
		ProcessInfo procInfo = Main.mainController.processInfo;
		Procedure halfProductObject = Main.mainController.halfProduct;
		
		try {
			procInfo.setPartName(partName.getText());
			procInfo.setPartQuantity(Integer.parseInt(partQuantity.getText()));
			procInfo.setPreparingTime(Double.parseDouble(preparingTimeField.getText()));
			procInfo.setHalfProduct(halfProduct.getText());
			procInfo.setHalfProductCost(Double.parseDouble(halfProductCost.getText()));
			Main.mainController.getProcessInfo().setText(procInfo.toString());
			halfProductObject.setParameters("Pr�t okr�g�y "+halfProduct.getText()+" [mm]");
			halfProductObject.setCost(Double.parseDouble(halfProductCost.getText()));
		} catch (Exception e) {
			exception = e.getMessage();
			Main.mainController.generateDataWarning(e);
		}
		if (exception == null) {
			Stage stage= (Stage) partName.getScene().getWindow();
			stage.close();
		}
	}
}

