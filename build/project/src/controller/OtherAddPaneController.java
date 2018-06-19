package controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.Main;
import procedure.Other;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class OtherAddPaneController implements Initializable{
	    @FXML
	    private BorderPane grindPane;
	    @FXML
	    protected TextArea otherParametersArea;
	    @FXML
	    protected TextField otherType;
	    @FXML
	    protected TextField otherMachine;
	    @FXML
	    protected TextField otherCost;
	    @FXML
	    protected TextField otherTime;
	    @FXML
	    protected TextField additionalTimeOther;
	    @FXML
	    protected Button addOtherProcedure;
	    private Stage otherStage;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		addOtherProcedure.setOnAction(x -> configureOtherAdd());
	}
	
	protected Other createOtherObject() {
		Other other = null;
			other = new Other(Double.parseDouble(otherCost.getText()),
							  otherType.getText(),
							  otherMachine.getText(),
							  Double.parseDouble(additionalTimeOther.getText()),
							  Double.parseDouble(otherTime.getText()),
							  otherParametersArea.getText());		  			  
		return other;
	}
	private void configureOtherAdd() {
		String exception = null;
		try {
			Main.mainController.collection.addProcedure(createOtherObject());
		} catch (Exception e) {
			exception = e.getMessage();
			Main.mainController.generateDataWarning(e);
		}
		if (exception == null) {
			otherStage = (Stage) otherTime.getScene().getWindow();
			otherStage.close();
		}
	}
}
