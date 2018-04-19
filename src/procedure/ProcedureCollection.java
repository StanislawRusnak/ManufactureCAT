package procedure;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProcedureCollection {
	private ObservableList<Procedure> procedureList;

	public ProcedureCollection() {
		this.procedureList = FXCollections.observableArrayList();
	}

	public void addProcedure(Procedure procedure) {
		procedureList.add(procedure);
	}

	public ObservableList<Procedure> getProcedureList() {
		return procedureList;
	}

	public double sumOfTime() {
		double sumOfTime = 0;
		for (Procedure procedure : procedureList) {
			sumOfTime += procedure.getTime();
		}
		return sumOfTime;
	}
	public double sumOfCost() {
		double sumOfCost=0;
		for (Procedure procedure : procedureList) {
			sumOfCost += procedure.getCost();
		}
		return sumOfCost;
	}
}
