package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import procedure.Lathe;
import procedure.Procedure;
import procedure.ProcedureCollection;

public class MainController implements Initializable {

	ProcedureCollection collection = new ProcedureCollection();
	@FXML
	private ContentPaneController contentPaneController;
	@FXML
	private MenuPaneController menuPaneController;
	@FXML
	private ControlPaneController controlPaneController;
	@FXML
	private LatheAddPaneController latheAddPaneController;
	@FXML
	private TextField timeSumField;
	@FXML
	private TextField costSumField;
	@FXML
	private TextArea processInfo;

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
		Procedure lathe1 = new Lathe(50, 30.5, 200, 4, 2, 6, 1000, 20, "Toczenie zewnêtrzne", "tokarka1");
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
			}
		});

	}

	public TextArea getProcessInfo() {
		return processInfo;
	}

}
