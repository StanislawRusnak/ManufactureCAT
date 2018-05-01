package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.Main;
import procedure.Grind;
import procedure.Procedure;

public class GrindAddPaneController implements Initializable {
	@FXML
	private BorderPane grindPane;
	@FXML
	private ComboBox<String> grindType;
	@FXML
	private TextField grindMachine;
	@FXML
	private TextField grindCost;
	@FXML
	private TextField grindFeed;
	@FXML
	private TextField grindRpm;
	@FXML
	private TextField grindSurplus;
	@FXML
	private TextField grindLength;
	@FXML
	private TextField grindWidth;
	@FXML
	private TextField grindReps;
    @FXML
    private Label repsLabel;
    @FXML
    private Label widthLabel;
    @FXML
    private Label lengthLabel;
    @FXML
    private Label surplusLabel;
	@FXML
	private TextField additionalTimeGrind;
	@FXML
	private ImageView helpImageGrind;
	@FXML
	private Button addGrindProcedure;
	
	private Stage grindStage;
    private static final String TRANSVE_GRIND = "Szlifowanie wg³êbne";
    private static final String LONGIT_GRIND = "Szlifowanie wzd³u¿ne";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		grindType.getItems().addAll(LONGIT_GRIND,TRANSVE_GRIND);
		grindType.setOnAction(x -> loadGrindType());
		addGrindProcedure.setOnAction(x -> configureGrindAdd());
		
	}

	private void loadGrindType() {
		try {
			helpImageGrind.setFitWidth(500);
			helpImageGrind.setFitHeight(400);
			
		if(grindType.getValue() == LONGIT_GRIND) {				//jeœli wybierzemy szlifowanie wzd³u¿ne to zmien obraz i ukryj niepotrzebne pola
				grindSurplus.setVisible(false);
				surplusLabel.setVisible(false);
				
				grindLength.setVisible(true);
				grindWidth.setVisible(true);
				grindReps.setVisible(true);
				lengthLabel.setVisible(true);
				widthLabel.setVisible(true);
				repsLabel.setVisible(true);
				helpImageGrind.setImage(new Image("/res/szlifowanieWzdluzne.PNG"));
				
			}else if(grindType.getValue() == TRANSVE_GRIND) {
				grindSurplus.setVisible(true);
				surplusLabel.setVisible(true);
				
				grindLength.setVisible(false);
				grindWidth.setVisible(false);
				grindReps.setVisible(false);
				lengthLabel.setVisible(false);
				widthLabel.setVisible(false);
				repsLabel.setVisible(false);
				helpImageGrind.setImage(new Image("/res/szlifowanieWglebne.PNG"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private Procedure createGrindObject() {
		Procedure grind = null;
		if(grindType.getValue() == LONGIT_GRIND) {
			grind = new Grind(Double.parseDouble(grindRpm.getText()),
					Double.parseDouble(grindFeed.getText()),
					Double.parseDouble(grindLength.getText()), 
					Double.parseDouble(grindWidth.getText()),
					Integer.parseInt(grindReps.getText()), 
					Double.parseDouble(grindCost.getText()),
					grindType.getValue(), 
					grindMachine.getText(),
					Double.parseDouble(additionalTimeGrind.getText()));
		}else {
			grind = new Grind(Double.parseDouble(grindSurplus.getText()),
					Double.parseDouble(grindRpm.getText()),
					Double.parseDouble(grindFeed.getText()), 
					Double.parseDouble(grindCost.getText()),
					grindType.getValue(), 
					grindMachine.getText(),
					Double.parseDouble(additionalTimeGrind.getText())); 
		}
		return grind;
	}
	private void configureGrindAdd() {
		String exception = null;
		try {
			Main.mainController.collection.addProcedure(createGrindObject());
		} catch (Exception e) {
			exception = e.getMessage();
			Main.mainController.generateDataWarning(e);
		}
		if (exception == null) {
			grindStage = (Stage) grindFeed.getScene().getWindow();
			grindStage.close();
		}
	
	}
}
