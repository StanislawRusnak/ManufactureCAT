package controller;



import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import procedure.Lathe;
import javafx.fxml.Initializable;

public class LatheAddPaneController  implements Initializable  {
	@FXML
	private MainController main; //null!
	@FXML
	private ContentPaneController contentPaneController; // null!
	@FXML
	private MenuPaneController menuPaneController;  //null!
	@FXML
	private ComboBox<String> latheType;

	@FXML
	private TextField latheMachine;

	@FXML
	private TextField latheCost;

	@FXML
	private TextField diameterBeforeLathe;

	@FXML
	private TextField diameterAfterLathe;

	@FXML
	private TextField latheLength;

	@FXML
	private TextField latheIdlePath;

	@FXML
	private TextField latheFeed;

	@FXML
	private TextField latheDepth;

	@FXML
	private TextField latheRpm;
    @FXML
    private Button addProcedure;

	public Button getAddProcedure() {
		return addProcedure;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		latheType.getItems().addAll("Toczenie zewnêtrzne","Toczenie wewnêtrzne","Toczenie poprzeczne");
		Button latheAdd = getAddProcedure();
		latheAdd.setOnAction(x->configureLatheAdd());
	}
	public void configureLatheAdd() {
		System.out.println("Dodajê procedure do listy");
		/*Procedure lathe = new Lathe(
				Double.parseDouble(diameterBeforeLathe.getText()),
				Double.parseDouble(diameterAfterLathe.getText()),
				Double.parseDouble(latheLength.getText()),
				Double.parseDouble(latheFeed.getText()),
				Double.parseDouble(latheDepth.getText()),
				Double.parseDouble(latheIdlePath.getText()),
				Double.parseDouble(latheRpm.getText()),
				Double.parseDouble(latheCost.getText()),
				latheType.getAccessibleText(),
				latheMachine.getText()
				); */
		
		main.collection.addProcedure(new Lathe(55,40.5,250,3,3,8,1200,20,"Toczenie wewnêtrzne proba3","tokarka3"));
	}

	public void init(MainController mainController) {
		main = mainController;
		
	}
}
