package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.Main;
import procedure.Drill;
import procedure.Grind;
import procedure.Lathe;
import procedure.Other;
import procedure.Procedure;

public class ContentPaneController implements Initializable {
   public static final String NUMBER_COLUMN = "Lp";
   public static final String TYPE_COLUMN = "Typ zabiegu";
   public static final String PARAMETERS_COLUMN = "Parametry";
   public static final String MACHINE_COLUMN = "Obrabiarka";
   public static final String TIME_COLUMN = "Czas [min]";
   public static final String COST_COLUMN = "Koszt [z³]";
	@FXML
	private MainController main;
    @FXML
    private TableView<Procedure> contentTable;
	public TableView<Procedure> getContentTable() {
		return contentTable;
	}
	private int index = 0;
	Procedure selected = null;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configureTable();		//budowanie tabeli
		configureTableEvents();	//konfiguracja usuwania i edytowania zabiegow w tabeli
		
	}
    private void configureTable() {
    	TableColumn<Procedure, String> numberColumn = new TableColumn<Procedure, String>("Lp");
    	numberColumn.setCellValueFactory(new Callback<CellDataFeatures<Procedure, String>, ObservableValue<String>>() {
    		  @Override public ObservableValue<String> call(CellDataFeatures<Procedure, String> p) {
    		    return new ReadOnlyObjectWrapper<String>(contentTable.getItems().indexOf(p.getValue()) +1+ "");}
    		});   
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
        
        contentTable.getColumns().add(numberColumn);
        contentTable.getColumns().add(typeColumn);
        contentTable.getColumns().add(parametersColumn);
        contentTable.getColumns().add(machineColumn);
        contentTable.getColumns().add(timeColumn);
        contentTable.getColumns().add(costColumn);
        
        typeColumn.setSortable(false);
        parametersColumn.setSortable(false);
        machineColumn.setSortable(false);
        timeColumn.setSortable(false);
        costColumn.setSortable(false);
        parametersColumn.setMinWidth(100);
        numberColumn.setMinWidth(30);
        numberColumn.setMaxWidth(30);
        numberColumn.setSortable(false);
        numberColumn.setStyle("-fx-alignment: CENTER;");
        typeColumn.setStyle("-fx-alignment: CENTER;");
        parametersColumn.setStyle("-fx-alignment: CENTER;");
        machineColumn.setStyle("-fx-alignment: CENTER;");
        costColumn.setStyle("-fx-alignment: CENTER;");
        timeColumn.setStyle("-fx-alignment: CENTER;");
    }

	public void configureTableEvents() {
		contentTable.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.DELETE) {
					getAndDeleteProcedure();
				}
			}
		});
		
		contentTable.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				index = contentTable.getSelectionModel().getSelectedIndex();
				selected = main.collection.getProcedureList().get(index);
				if(event.getClickCount() == 5) {
					Alert alert = new Alert(AlertType.WARNING,
							"Panieee, wez pan zostaw t¹ myszke w spokoju !\nNie mêcz jej ! " , ButtonType.OK);
					alert.showAndWait();
					if (alert.getResult() == ButtonType.OK) {
						alert.close();
						}
				} else if(event.getClickCount() == 2 && selected instanceof Lathe) {
					editLatheProcedure();
				}else if(event.getClickCount() == 2 && selected instanceof Drill) {
					editDrillProcedure();
				}else if(event.getClickCount() == 2 && selected instanceof Grind) {
					editGrindProcedure();
				}else if(event.getClickCount() == 2 && selected instanceof Other) {
					editOtherProcedure();
				}else if(event.getClickCount() == 2 && selected instanceof Procedure) {
					Main.mainController.menuPaneController.createProcessInfoWindow();
				}
			}
		});
	}
	protected void getAndDeleteProcedure() {
		main.collection.getProcedureList().remove(index);
	}
	private void editLatheProcedure() {
		Lathe editedProcedure;
		editedProcedure = (Lathe) selected;
		LatheAddPaneController latheAddPaneContr = main.menuPaneController.createLatheWindow();
		//kopiowanie pól starego zabiegu do okna edytowania
		latheAddPaneContr.additionalTime.setText(String.valueOf(editedProcedure.getAdditionalTime()));
		latheAddPaneContr.diameterAfterLathe.setText(String.valueOf(editedProcedure.getDiameterAfter()));
		latheAddPaneContr.diameterBeforeLathe.setText(String.valueOf(editedProcedure.getDiameterBefore()));
		latheAddPaneContr.latheCost.setText(String.valueOf(editedProcedure.getCostPerHour()));
		latheAddPaneContr.latheDepth.setText(String.valueOf(editedProcedure.getDepthOfCut()));
		latheAddPaneContr.latheFeed.setText(String.valueOf(editedProcedure.getFeed()));
		latheAddPaneContr.latheIdlePath.setText(String.valueOf(editedProcedure.getIdleTrack()));
		latheAddPaneContr.latheLength.setText(String.valueOf(editedProcedure.getLatheLength()));
		latheAddPaneContr.latheMachine.setText(String.valueOf(editedProcedure.getMachine()));
		latheAddPaneContr.latheRpm.setText(String.valueOf(editedProcedure.getRpm()));
		latheAddPaneContr.latheType.setValue(String.valueOf(editedProcedure.getType()));
		
		//po kliknieciu 'dodaj' usuwanie starego obiektu i dodanie w jego miejsce nowego w kolekcji
		latheAddPaneContr.addProcedure.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String exception = null;
				try {
					main.collection.getProcedureList().remove(editedProcedure);
					main.collection.getProcedureList().add(index, latheAddPaneContr.createLatheObject());
				} catch (Exception e) {
					exception = e.getMessage();
					main.generateDataWarning(e);
				}
				if (exception == null) {
					Stage stage;
					stage = (Stage) latheAddPaneContr.latheFeed.getScene().getWindow();
					stage.close();
				}	
			}
		});
	}
	
	private void editDrillProcedure() {
		Drill editedProcedure;
		editedProcedure = (Drill) selected;
		DrillAddPaneController drillAddPaneContr = main.menuPaneController.createDrillWindow();
		//kopiowanie pól starego zabiegu do okna edytowania
		drillAddPaneContr.drillDiameter.setText(String.valueOf(editedProcedure.getDiameterAfter()));
		drillAddPaneContr.drillLength.setText(String.valueOf(editedProcedure.getDrillLength()));
		drillAddPaneContr.drillFeed.setText(String.valueOf(editedProcedure.getFeed()));
		drillAddPaneContr.drillIdlePath.setText(String.valueOf(editedProcedure.getIdlePath()));
		drillAddPaneContr.drillRpm.setText(String.valueOf(editedProcedure.getRpm()));
		drillAddPaneContr.drillCost.setText(String.valueOf(editedProcedure.getCostPerHour()));
		drillAddPaneContr.drillType.setValue(String.valueOf(editedProcedure.getType()));
		drillAddPaneContr.drillMachine.setText(String.valueOf(editedProcedure.getMachine()));
		drillAddPaneContr.additionalTimeDrill.setText(String.valueOf(editedProcedure.getAdditionalTime()));

		//po kliknieciu 'dodaj' usuwanie starego obiektu i dodanie w jego miejsce nowego w kolekcji
		drillAddPaneContr.addDrillProcedure.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String exception = null;
				try {
					main.collection.getProcedureList().remove(editedProcedure);
					main.collection.getProcedureList().add(index, drillAddPaneContr.createDrillObject());
				} catch (Exception e) {
					exception = e.getMessage();
					main.generateDataWarning(e);
				}
				if (exception == null) {
					Stage stage;
					stage = (Stage) drillAddPaneContr.drillFeed.getScene().getWindow();
					stage.close();
				}	
			}
		});
	} 
	
	private void editGrindProcedure() {
		Grind editedProcedure;
		editedProcedure = (Grind) selected;
		GrindAddPaneController grindAddPaneContr = main.menuPaneController.createGrindWindow();
		//kopiowanie pól starego zabiegu do okna edytowania
		grindAddPaneContr.grindRpm.setText(String.valueOf(editedProcedure.getRpm()));
		grindAddPaneContr.grindFeed.setText(String.valueOf(editedProcedure.getFeed()));
		grindAddPaneContr.grindLength.setText(String.valueOf(editedProcedure.getGrindLength()));
		grindAddPaneContr.grindWidth.setText(String.valueOf(editedProcedure.getGrindWidth()));
		grindAddPaneContr.grindReps.setText(String.valueOf(editedProcedure.getGrindReps()));
		grindAddPaneContr.grindCost.setText(String.valueOf(editedProcedure.getCostPerHour()));
		grindAddPaneContr.grindType.setValue(String.valueOf(editedProcedure.getType()));
		grindAddPaneContr.grindMachine.setText(String.valueOf(editedProcedure.getMachine()));
		grindAddPaneContr.additionalTimeGrind.setText(String.valueOf(editedProcedure.getAdditionalTime()));
		grindAddPaneContr.grindSurplus.setText(String.valueOf(editedProcedure.getSurplus()));


		//po kliknieciu 'dodaj' usuwanie starego obiektu i dodanie w jego miejsce nowego w kolekcji
		grindAddPaneContr.addGrindProcedure.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String exception = null;
				try {
					main.collection.getProcedureList().remove(editedProcedure);
					main.collection.getProcedureList().add(index, grindAddPaneContr.createGrindObject());
				} catch (Exception e) {
					exception = e.getMessage();
					main.generateDataWarning(e);
				}
				if (exception == null) {
					Stage stage;
					stage = (Stage) grindAddPaneContr.grindFeed.getScene().getWindow();
					stage.close();
				}	
			}
		});
	} 
	
	private void editOtherProcedure() {
		Other editedProcedure;
		editedProcedure = (Other) selected;
		OtherAddPaneController otherAddPaneContr = main.menuPaneController.createOtherWindow();
		//kopiowanie pól starego zabiegu do okna edytowania
		otherAddPaneContr.otherCost.setText(String.valueOf(editedProcedure.getCostPerHour()));
		otherAddPaneContr.otherType.setText(String.valueOf(editedProcedure.getType()));
		otherAddPaneContr.otherMachine.setText(String.valueOf(editedProcedure.getMachine()));
		otherAddPaneContr.additionalTimeOther.setText(String.valueOf(editedProcedure.getAdditionalTime()));
		otherAddPaneContr.otherTime.setText(String.valueOf(editedProcedure.getMainTime()));
		otherAddPaneContr.otherParametersArea.setText(String.valueOf(editedProcedure.getOtherParameters()));

		//po kliknieciu 'dodaj' usuwanie starego obiektu i dodanie w jego miejsce nowego w kolekcji
		otherAddPaneContr.addOtherProcedure.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String exception = null;
				try {
					main.collection.getProcedureList().remove(editedProcedure);
					main.collection.getProcedureList().add(index, otherAddPaneContr.createOtherObject());
				} catch (Exception e) {
					exception = e.getMessage();
					main.generateDataWarning(e);
				}
				if (exception == null) {
					Stage stage;
					stage = (Stage) otherAddPaneContr.otherMachine.getScene().getWindow();
					stage.close();
				}	
			}
		});
	} 
	
    public void init(MainController mainController) {
		main = mainController;
	}
}
