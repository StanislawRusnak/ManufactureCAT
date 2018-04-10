package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
public class MenuPaneController implements Initializable {
    @FXML
    private Menu productMenu;

    @FXML
    private MenuItem newMenuItem;

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
    private MenuItem latheMenuItem;

    @FXML
    private MenuItem drillMenuitem;

    @FXML
    private MenuItem cutMenuItem;

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
		
	}
}
