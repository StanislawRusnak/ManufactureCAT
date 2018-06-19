package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import main.Main;
import procedure.Procedure;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ChartPaneController implements Initializable {
	private MainController main = Main.mainController;
	private String tabChoice = main.menuPaneController.tabChoice;
	
	@FXML
	private TabPane tabPane;
	@FXML
	private Tab timeTab;

	@FXML
	private PieChart timePieChart;

	@FXML
	private Tab costTab;

	@FXML
	private PieChart costPieChart;

	private final ObservableList<PieChart.Data> timeList = FXCollections.observableArrayList();
	private final ObservableList<PieChart.Data> costList = FXCollections.observableArrayList();
	private final ObservableList<Procedure> prList = Main.mainController.collection.getProcedureList();
	private final double sumCost = Double.parseDouble(Main.mainController.costSumField.getText());
	private final double sumTime = Double.parseDouble(Main.mainController.timeSumField.getText());

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configTimePieChart();
		configCostPieChart();
		selectTab();
	}

	private void selectTab() {
		if (tabChoice.equals("time")) {
			tabPane.getSelectionModel().select(timeTab);
		} else {
			tabPane.getSelectionModel().select(costTab);
		}
	}

	private void configTimePieChart() {
		PieChart.Data timePie;
		double percent;
		for (Procedure tmp : prList) {
			percent = calcPercent(tmp.getTime(), sumTime);
			timePie = new PieChart.Data(tmp.getType() + " " + percent + " %", percent);
			timeList.add(timePie);
		}

		timePieChart.setData(timeList);
		timePieChart.setTitle("Porównanie czasów wykonania zabiegów");
		timePieChart.setLegendSide(Side.BOTTOM);
		timePieChart.setLabelsVisible(true);

		timeTab.setContent(timePieChart);
	}

	private void configCostPieChart() {
		PieChart.Data costPie;
		double percent;
		for (Procedure tmp : prList) {
			percent = calcPercent(tmp.getCost(), sumCost);
			costPie = new PieChart.Data(tmp.getType() + " " + percent + " %", percent);
			costList.add(costPie);
		}

		costPieChart.setData(costList);
		costPieChart.setTitle("Porównanie kosztów wykonania zabiegów");
		costPieChart.setLegendSide(Side.BOTTOM);
		costPieChart.setLabelsVisible(true);

		costTab.setContent(costPieChart);
	}

	private double calcPercent(double procedure, double sum) { //calculation of the percentage share of cost / time (obliczanie procentowego udzia³u kosztu/czasu)
		double perc = (procedure / sum) * 100;
		perc *= 10;
		perc = Math.round(perc);
		perc /= 10;
		return perc;
	}
}
