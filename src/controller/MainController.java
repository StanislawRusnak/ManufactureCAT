package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import procedure.Lathe;
import procedure.Procedure;
import procedure.ProcedureCollection;

public class MainController implements Initializable {

	ProcedureCollection collection = new ProcedureCollection();
	public Integer partQ;
	@FXML
	protected ContentPaneController contentPaneController;
	@FXML
	protected MenuPaneController menuPaneController;
	@FXML
	protected ControlPaneController controlPaneController;
	@FXML
	private TextField timeSumField;
	@FXML
	private TextField costSumField;
	@FXML
	private TextField seriesCostField;
	@FXML
	private TextArea processInfoField;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		menuPaneController.init(this);
		controlPaneController.init(this);
		contentPaneController.init(this);
		configureTable();
		configureMenu();
		configureSumInfo();
	}
	
	public void configureTable() {
		TableView<Procedure> contentTable = contentPaneController.getContentTable();
		Procedure lathe1 = new Lathe(50, 30.5, 200, 4, 2, 6, 1000, 20, "Toczenie zewnêtrzne", "tokarka1",2);
		collection.addProcedure(lathe1);
		contentTable.setItems(collection.getProcedureList());	
	}
	

	public void configureMenu() {
		MenuItem latheMenuItem = menuPaneController.getLatheMenuItem();
		Button latheShort = controlPaneController.getLatheShortMenuItem();
		latheMenuItem.setOnAction(x -> menuPaneController.createLatheWindow());
		latheShort.setOnAction(x -> menuPaneController.createLatheWindow());
	}

	public void configureSumInfo() {
		
		collection.getProcedureList().addListener(new ListChangeListener<Procedure>() {
			@Override
			public void onChanged(Change<? extends Procedure> c) {
				timeSumField.setText(String.valueOf(collection.sumOfTime()));
				costSumField.setText(String.valueOf(collection.sumOfCost()));
				seriesCostField.setText(String.valueOf(collection.sumOfCost()*partQ));
				}
		});
	}
	public TextArea getProcessInfo() {
		return processInfoField;
	}
	
	public void generateDataWarning(Exception e) {
		Alert alert = new Alert(AlertType.WARNING,
				"Z³y format danych. Wpisz zabieg ponownie.\nPrzyczyna: " + e.getMessage(), ButtonType.OK);
		alert.showAndWait();
		if (alert.getResult() == ButtonType.OK) {
			alert.close();
		}
	}
}
