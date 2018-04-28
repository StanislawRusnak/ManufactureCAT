package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import procedure.Lathe;
import procedure.Procedure;
import procedure.ProcedureCollection;
import procedure.ProcessInfo;

public class MainController implements Initializable {
	protected ProcessInfo processInfo = new ProcessInfo("Brak", "Brak", "Brak",0.0, 0, 0);	//generowanie pustego szablonu-obiektu procesInfo
	ProcedureCollection collection = new ProcedureCollection();
	Procedure halfProduct = new Procedure("Zakup pó³fabrykatu","-","-",0.0,0.0);
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
	private TextField seriesTimeField;
	@FXML
	private TextArea processInfoField;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		menuPaneController.init(this);
		controlPaneController.init(this);
		contentPaneController.init(this);
		configureTable();
		configureSumInfo();
	}
	
	public void configureTable() {
		TableView<Procedure> contentTable = contentPaneController.getContentTable();
		Procedure lathe1 = new Lathe(444, 333, 222, 5, 4, 3, 1222, 34, "Toczenie zewnêtrzne", "tokarka1",2);
		collection.addProcedure(halfProduct);
		collection.addProcedure(lathe1);
		contentTable.setItems(collection.getProcedureList());	
	}

	public void configureSumInfo() {
		collection.getProcedureList().addListener(new ListChangeListener<Procedure>() {
			@Override
			public void onChanged(Change<? extends Procedure> c) {
				timeSumField.setText(String.valueOf(roundUp(collection.sumOfTime())));
				costSumField.setText(String.valueOf(roundUp(collection.sumOfCost())));
				seriesCostField.setText(String.valueOf(roundUp(collection.sumOfCost()*processInfo.getPartQuantity())));
				seriesTimeField.setText(String.valueOf(roundUp((collection.sumOfTime()*processInfo.getPartQuantity())+processInfo.getPreparingTime())));
			}
		});
	}
	public TextArea getProcessInfo() {
		return processInfoField;
	}
	
	public void generateDataWarning(Exception e) {
		Alert alert = new Alert(AlertType.ERROR,
				"Z³y format danych. Wpisz zabieg ponownie.\nPrzyczyna: " + e.getMessage(), ButtonType.OK);
		alert.showAndWait();
		if (alert.getResult() == ButtonType.OK) {
			alert.close();
		}
	}
	public double roundUp(double number) {		//zaokr¹glanie double w górê do setnych czêœci 
		number*=100;
    	number= Math.ceil(number);
    	number/=100;
		return number;
	}
	
	public MenuPaneController getMenuPaneController() {
		return menuPaneController;
	}
}
