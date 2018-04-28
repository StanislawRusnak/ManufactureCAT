package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.Main;
import procedure.Drill;
import procedure.Procedure;

public class DrillAddPaneController implements Initializable {
	@FXML
	private BorderPane drillPane;
	@FXML
	private ComboBox<String> drillType;
	@FXML
	private TextField drillMachine;
	@FXML
	private TextField drillCost;
	@FXML
	private TextField drillDiameter;
	@FXML
	private TextField drillLength;
	@FXML
	private TextField drillIdlePath;
	@FXML
	private TextField drillFeed;
	@FXML
	private TextField drillRpm;
	@FXML
	private TextField additionalTimeDrill;
	@FXML
	private ImageView helpImageDrill;
	@FXML
	private Button addDrillProcedure;

	private Stage drillStage;
	public static final String CLOSE_DRILLING = "Wiercenie nieprzelotowe";
	public static final String OPEN_DRILLING = "Wiercenie przelotowe";
	public static final String HOLE_DRILLING = "Powiercanie";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		drillType.getItems().addAll(CLOSE_DRILLING,OPEN_DRILLING, HOLE_DRILLING);
		drillType.setOnAction(x -> loadDrillType());
		addDrillProcedure.setOnAction(x -> configureDrillAdd());
	}

	private void loadDrillType() {
		try {
			helpImageDrill.setFitWidth(410);
			helpImageDrill.setFitHeight(400);

			if (drillType.getValue() == CLOSE_DRILLING) { // zmiana obrazow podpowiedzi w zaleznosci od typu zabiegu
				helpImageDrill.setImage(new Image("/res/wiercenieNieprzel.PNG"));
			} else if (drillType.getValue() == OPEN_DRILLING) {
				helpImageDrill.setImage(new Image("/res/wierceniePrzel.PNG"));
			} else if (drillType.getValue() == HOLE_DRILLING) {
				helpImageDrill.setImage(new Image("/res/powiercanie.PNG"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected Procedure createDrillObject() {
		Procedure drill;
			drill = new Drill(Double.parseDouble(drillDiameter.getText()),
					Double.parseDouble(drillLength.getText()),
					Double.parseDouble(drillFeed.getText()),
					Double.parseDouble(drillIdlePath.getText()),
					Double.parseDouble(drillRpm.getText()),
					Double.parseDouble(drillCost.getText()),
					drillType.getValue(),
					drillMachine.getText(),
					Double.parseDouble(additionalTimeDrill.getText()));	
		return drill;
	}

	public void configureDrillAdd() {
		String exception = null;
		try {
			Main.mainController.collection.addProcedure(createDrillObject());
		} catch (Exception e) {
			exception = e.getMessage();
			Main.mainController.generateDataWarning(e);
		}
		if (exception == null) {
			drillStage = (Stage) drillFeed.getScene().getWindow();
			drillStage.close();
		}
	}
}
