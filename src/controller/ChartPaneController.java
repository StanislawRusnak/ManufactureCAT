package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import main.Main;

public class ChartPaneController implements Initializable {
	MainController main = Main.mainController;
	@FXML
	private PieChart timePieChart;
	@FXML
	private PieChart costPieChart;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configureBarChart();
		// configTimePieChart();
		// configCostPieChart();
	}

	private void configureBarChart() {
	
	}
}
