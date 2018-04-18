package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//latheAddPaneController.init(this);
		menuPaneController.init(this);
		controlPaneController.init(this);
		contentPaneController.init(this);
		configureTable();
		configureMenu();
	}

	public void configureTable() {
		TableView<Procedure> contentTable = contentPaneController.getContentTable();
		Procedure lathe1 = new Lathe(50,30.5,200,4,2,6,1000,20,"Toczenie zewnêtrzne","tokarka1");
		Procedure l2 = new Lathe(50,30.5,200,4,2,6,1000,20,"Toczenie zewnêtrzne","tokarka1");
		Procedure l3 = new Lathe(50,30.5,200,4,2,6,1000,20,"Toczenie zewnêtrzne","tokarka1");
		Procedure l4 = new Lathe(50,30.5,200,4,2,6,1000,20,"Toczenie zewnêtrzne","tokarka1");
		collection.addProcedure(lathe1);
		collection.addProcedure(l2);
		collection.addProcedure(l3);
		collection.addProcedure(l4);
		contentTable.setItems(collection.getProcedureList());
	}
	
	public void configureMenu() {
		MenuItem latheMenuItem = menuPaneController.getLatheMenuItem();
		Button latheShort = controlPaneController.getLatheShortMenuItem();
		latheMenuItem.setOnAction(x->menuPaneController.createLatheWindow());
		latheShort.setOnAction(x->menuPaneController.createLatheWindow()); 
	}
	

}
