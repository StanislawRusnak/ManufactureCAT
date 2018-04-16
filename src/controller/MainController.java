package controller;



import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;


import javafx.scene.control.TableView;

import procedure.Lathe;
import procedure.Procedure;
import procedure.ProcedureCollection;

public class MainController implements Initializable {

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
		configureTable();
		configureMenu();

	}

	private void configureTable() {
		TableView<Procedure> contentTable = contentPaneController.getContentTable();
		ProcedureCollection collection = new ProcedureCollection();
		Procedure lathe1 = new Lathe(50,30.5,200,4,2,6,1000,20,"Toczenie zewnêtrzne","tokarka1");
		Procedure lathe2 = new Lathe(55,40.5,250,3,3,8,1200,20,"Toczenie wewnêtrzne","tokarka2");
		collection.addProcedure(lathe1);
		collection.addProcedure(lathe2);
		contentTable.setItems(collection.getProcedureList());
	}
	
	public void configureMenu() {

	}
	


}
