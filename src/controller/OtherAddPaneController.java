package controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.Main;
import procedure.Procedure;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class OtherAddPaneController implements Initializable{
	    @FXML
	    private BorderPane grindPane;
	    @FXML
	    private TextArea otherParametersArea;
	    @FXML
	    private TextField otherType;
	    @FXML
	    private TextField otherMachine;
	    @FXML
	    private TextField otherCost;
	    @FXML
	    private Label repsLabel;
	    @FXML
	    private TextField otherTime;
	    @FXML
	    private TextField additionalTimeOther;
	    @FXML
	    private Button addOtherProcedure;
	    private Stage otherStage;
	    private double numOtherTime = -1;
	    private double numOtherAdditTime = -1;	
	    private double numOtherCost = -1;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		addOtherProcedure.setOnAction(x -> configureOtherAdd());
	}
	
	private Procedure createOtherObject() {
		numOtherTime = Double.parseDouble(otherTime.getText());
		numOtherAdditTime = Double.parseDouble(additionalTimeOther.getText());
		numOtherCost = Double.parseDouble(otherCost.getText());
		
		Procedure other = null;
			other = new Procedure(otherType.getText(),
					printOtherParam(), otherMachine.getText(),getOtherTime(), getOtherCost(getOtherTime()));
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
	private String printOtherParam() {
		String parameters = null;
		parameters = "Czas g³ówny zabiegu: " + numOtherTime +" [min]"+
					 "\nCzas pomocniczy zabiegu: " + numOtherAdditTime +" [min]\n"+
					  otherParametersArea.getText();
		return parameters;
		
	}
	private double getOtherTime() {
		double totalTime = -1;
		totalTime = numOtherTime + numOtherAdditTime;
		return totalTime;
	}
	private double getOtherCost(double time) {
		double cost = -1;
		cost = time * (numOtherCost/60);
		return cost;
	}
}
