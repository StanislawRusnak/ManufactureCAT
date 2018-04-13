package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import procedure.Procedure;

public class ContentPaneController implements Initializable {
	
   public static final String TYPE_COLUMN = "Typ zabiegu";
   public static final String PARAMETERS_COLUMN = "Parametry";
   public static final String MACHINE_COLUMN = "Obrabiarka";
   public static final String TIME_COLUMN = "Czas";
   public static final String COST_COLUMN = "Koszt";
	
    @FXML
    private TableView<Procedure> contentTable;
	public TableView<Procedure> getContentTable() {
		return contentTable;
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configureTable();
		
	}
    private void configureTable() {
        TableColumn<Procedure, String> typeColumn = new TableColumn<Procedure, String>(TYPE_COLUMN);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        TableColumn<Procedure, String> parametersColumn = new TableColumn<Procedure, String>(PARAMETERS_COLUMN);
        parametersColumn.setCellValueFactory(new PropertyValueFactory<>("parameters"));
        TableColumn<Procedure, String> machineColumn = new TableColumn<Procedure, String>(MACHINE_COLUMN);
        machineColumn.setCellValueFactory(new PropertyValueFactory<>("machine"));
        TableColumn<Procedure, String> timeColumn = new TableColumn<Procedure, String>(TIME_COLUMN);
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        TableColumn<Procedure, String> costColumn = new TableColumn<Procedure, String>(COST_COLUMN);
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        
         
        getContentTable().getColumns().add(typeColumn);
        getContentTable().getColumns().add(parametersColumn);
        getContentTable().getColumns().add(machineColumn);
        getContentTable().getColumns().add(timeColumn);
        getContentTable().getColumns().add(costColumn);

    }

}
