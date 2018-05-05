package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.Main;

public class MenuPaneController implements Initializable {
	@FXML
	private MainController main;
	@FXML
	private ControlPaneController controlPaneController;
	@FXML
	private MenuPaneController menuPaneController;
    @FXML
    private Menu productMenu;

    @FXML
    private MenuItem editMenuItem;

    @FXML
    private MenuItem saveMenuItem;

    @FXML
    private MenuItem loadMenuItem;

    @FXML
    private MenuItem printMenuItem;

    @FXML
    private MenuItem closeMenuItem;

    @FXML
    private Menu procedureMenu;
    @FXML
    private MenuItem deleteProcedureMItem;
    @FXML
    private MenuItem latheMenuItem;

	public MenuItem getLatheMenuItem() {
		return latheMenuItem;
	}
	@FXML
    private MenuItem drillMenuitem;

    @FXML
    private MenuItem grindMenuItem;

    @FXML
    private MenuItem otherMenuItem;

    @FXML
    private Menu optionMenu;

    @FXML
    private MenuItem settingsMenuItem;

    @FXML
    private MenuItem avaibleMenuItem;

    @FXML
    private MenuItem editSemiMenuItem;

    @FXML
    private Menu helpMenu;

    @FXML
    private MenuItem aboutMenuItem;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configureMenu();			
	}
	
	private void configureMenu() {
		closeMenuItem.setOnAction(x -> Platform.exit());
        aboutMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                Parent parent = null;
                try {
                    parent = FXMLLoader.load(getClass().getResource("/view/AboutPane.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setTitle("Manufacture CAT v0.1 - informacje");
                stage.setScene(scene);
                stage.show();
            }
        });
    	latheMenuItem.setOnAction(x -> createLatheWindow());
        deleteProcedureMItem.setOnAction(x -> main.contentPaneController.getAndDeleteProcedure());
        editMenuItem.setOnAction(x -> createProcessInfoWindow());
        drillMenuitem.setOnAction(x -> createDrillWindow());
        grindMenuItem.setOnAction(x -> createGrindWindow());
        otherMenuItem.setOnAction(x -> createOtherWindow());
        saveMenuItem.setOnAction(x -> main.exportImport.exportExcel());
        printMenuItem.setOnAction(x -> main.exportImport.printTable());
        loadMenuItem.setOnAction(x -> main.exportImport.importExcel());
        
        
    }
	public void init(MainController mainController) {
		main = mainController;
		
	}
	public LatheAddPaneController createLatheWindow() {
		LatheAddPaneController windowController = null;
		BorderPane mainLayout;
        try {
        	FXMLLoader loader = new FXMLLoader();
        	loader.setLocation(getClass().getResource("/view/LatheAddPane.fxml"));
        	mainLayout = loader.load();
        	windowController = loader.getController();
        
        Scene scene = new Scene(mainLayout);
        Stage stage = new Stage();
        stage.setTitle("Dodawanie zabiegu toczenia");
        stage.setScene(scene);
        stage.show();
        
        } catch (IOException e) {
            e.printStackTrace();
        }
		return windowController;
	}
	public ProcessInfoController createProcessInfoWindow() {
		ProcessInfoController windowController=null;
		BorderPane mainLayout;
        try {
        	FXMLLoader loader = new FXMLLoader();
        	loader.setLocation(getClass().getResource("/view/ProcessInfoPane.fxml"));
        	mainLayout = loader.load();
        	windowController = loader.getController();
        
        Scene scene = new Scene(mainLayout);
        Stage stage = new Stage();
        stage.setTitle("Dodawanie podstawowych infomracji o operacji");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.setOnHiding(x -> Main.mainController.sumSet()); 	//wywo�anie funkcji od�wie�aj�cej pola sum
        
        } catch (IOException e) {
            e.printStackTrace();
        }
		return windowController;
	}
	
	public DrillAddPaneController createDrillWindow() {
		DrillAddPaneController windowController = null;
		BorderPane mainLayout;
        try {
        	FXMLLoader loader = new FXMLLoader();
        	loader.setLocation(getClass().getResource("/view/DrillAddPane.fxml"));
        	mainLayout = loader.load();
        	windowController = loader.getController();
        
        Scene scene = new Scene(mainLayout);
        Stage stage = new Stage();
        stage.setTitle("Dodawanie zabiegu wiercenia");
        stage.setScene(scene);
        stage.show();
        
        } catch (IOException e) {
            e.printStackTrace();
        }
		return windowController;
	}
	
	public GrindAddPaneController createGrindWindow() {
		GrindAddPaneController windowController = null;
		BorderPane mainLayout;
        try {
        	FXMLLoader loader = new FXMLLoader();
        	loader.setLocation(getClass().getResource("/view/GrindAddPane.fxml"));
        	mainLayout = loader.load();
        	windowController = loader.getController();
        
        Scene scene = new Scene(mainLayout);
        Stage stage = new Stage();
        stage.setTitle("Dodawanie zabiegu szlifowania");
        stage.setScene(scene);
        stage.show();
        
        } catch (IOException e) {
            e.printStackTrace();
        }
		return windowController;
	}
	
	public OtherAddPaneController createOtherWindow() {
		OtherAddPaneController windowController = null;
		BorderPane mainLayout;
        try {
        	FXMLLoader loader = new FXMLLoader();
        	loader.setLocation(getClass().getResource("/view/OtherAddPane.fxml"));
        	mainLayout = loader.load();
        	windowController = loader.getController();
        
        Scene scene = new Scene(mainLayout);
        Stage stage = new Stage();
        stage.setTitle("Dodawanie innego zabiegu");
        stage.setScene(scene);
        stage.show();
        
        } catch (IOException e) {
            e.printStackTrace();
        }
		return windowController;
	}
}
