package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	protected ComboBox<String> latheType;
	@FXML
	protected TextField latheMachine;
	@FXML
	protected TextField latheCost;
	@FXML
	protected TextField diameterBeforeLathe;
	@FXML
	protected TextField diameterAfterLathe;
	@FXML
	protected TextField latheLength;
	@FXML
	protected TextField latheIdlePath;
	@FXML
	protected TextField latheFeed;
	@FXML
	protected TextField latheDepth;
	@FXML
	protected TextField latheRpm;
	@FXML
	protected Button addProcedure;
	@FXML
	private Label diamAfterLatheLabel;
    @FXML
    private ImageView helpImageLathe;
    @FXML
    protected TextField additionalTime;
    
    private Stage stage;
    public static final String TRANSVE_LATHE = "Toczenie poprzeczne";
    public static final String INNER_LATHE = "Toczenie wewnêtrzne";
    public static final String OUTER_LATHE = "Toczenie zewnêtrzne";
	//public Button getAddProcedure() {
	//	return addProcedure;
	//}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		latheType.getItems().addAll(INNER_LATHE, OUTER_LATHE, TRANSVE_LATHE);
		Button latheAdd = addProcedure;
		latheAdd.setOnAction(x -> configureLatheAdd());
		latheType.setOnAction(x-> loadLatheType());
		
	}

	private void loadLatheType() {
		try {
			helpImageLathe.setFitWidth(500);
			helpImageLathe.setFitHeight(450);
			
		if(latheType.getValue() == TRANSVE_LATHE) {				//jeœli wybierzemy toczenie poprzeczne to zmien obraz i ukryj sredn koncowa
				diameterAfterLathe.setVisible(false);
				diamAfterLatheLabel.setVisible(false);
				helpImageLathe.setImage(new Image("/res/toczeniePoprzeczne.PNG"));
				
			}else if(latheType.getValue() == OUTER_LATHE) {
				diameterAfterLathe.setVisible(true);
				diamAfterLatheLabel.setVisible(true);
				helpImageLathe.setImage(new Image("/res/toczenieZewn.PNG"));
				
			}else if(latheType.getValue() == INNER_LATHE) {
				diameterAfterLathe.setVisible(true);
				diamAfterLatheLabel.setVisible(true);
				helpImageLathe.setImage(new Image("/res/toczenieWewn.PNG"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	protected Procedure createLatheObject() {
		Procedure lathe;
			if(latheType.getValue() == TRANSVE_LATHE) {
				lathe = new Lathe(Double.parseDouble(diameterBeforeLathe.getText()),
						Double.parseDouble(latheLength.getText()),
						Double.parseDouble(latheFeed.getText()), 
						Double.parseDouble(latheDepth.getText()),
						Double.parseDouble(latheIdlePath.getText()), 
						Double.parseDouble(latheRpm.getText()),
						Double.parseDouble(latheCost.getText()), 
						latheType.getValue(), 
						latheMachine.getText(),
						Double.parseDouble(additionalTime.getText()));
			}else {
				 lathe = new Lathe(Double.parseDouble(diameterBeforeLathe.getText()),
						Double.parseDouble(diameterAfterLathe.getText()), 
						Double.parseDouble(latheLength.getText()),
						Double.parseDouble(latheFeed.getText()), 
						Double.parseDouble(latheDepth.getText()),
						Double.parseDouble(latheIdlePath.getText()), 
						Double.parseDouble(latheRpm.getText()),
						Double.parseDouble(latheCost.getText()), 
						latheType.getValue(), 
						latheMachine.getText(),
						Double.parseDouble(additionalTime.getText()));
			}
			return lathe;
	}
	public void configureLatheAdd() {
		String exception = null;
		try {
			Main.mainController.collection.addProcedure(createLatheObject());
		} catch (Exception e) {
			exception = e.getMessage();
			generateDataWarning(e);
		}
		if (exception == null) {
			stage = (Stage) latheFeed.getScene().getWindow();
			stage.close();
		}
	}
	public void generateDataWarning(Exception e) {
		Alert alert = new Alert(AlertType.CONFIRMATION,
				"Z³y format danych. Wpisz zabieg ponownie.\nPrzyczyna: " + e.getMessage(), ButtonType.OK);
		alert.showAndWait();
		if (alert.getResult() == ButtonType.OK) {
			alert.close();
		}
	}
}
