package controller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class ControlPaneController implements Initializable {
	
	@FXML
	private MainController main;
	@FXML
	private Button loadShortMenuItem;
	@FXML
	private Button saveShortMenuItem;
	@FXML
	private Button printShortMenuItem;
	@FXML
	private Button latheShortMenuItem;
	@FXML
	private Button drillShortMenuItem;
	@FXML
	private Button grindShortMenuItem;
	@FXML
	private Button otherShortMenuItem;
    @FXML
    private Button costAnalysShort;
    @FXML
    private Button timeAnalysShort;
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) { 
		latheShortMenuItem.setOnAction(x -> main.menuPaneController.createLatheWindow());
		drillShortMenuItem.setOnAction(x -> main.menuPaneController.createDrillWindow());
		grindShortMenuItem.setOnAction(x -> main.menuPaneController.createGrindWindow());
		otherShortMenuItem.setOnAction(x -> main.menuPaneController.createOtherWindow());
        saveShortMenuItem.setOnAction(x -> main.exportImport.exportExcel());
        printShortMenuItem.setOnAction(x -> main.exportImport.printTable());
        loadShortMenuItem.setOnAction(x -> main.exportImport.importExcel());
        costAnalysShort.setOnAction(x -> {main.menuPaneController.tabChoice = "cost";main.menuPaneController.createChartWindow();});
        timeAnalysShort.setOnAction(x -> {main.menuPaneController.tabChoice = "time";main.menuPaneController.createChartWindow();});
	}

	public void init(MainController mainController) {
		main = mainController;
		
	}
	public Button getLatheShortMenuItem() {
		return latheShortMenuItem;
	}
}
