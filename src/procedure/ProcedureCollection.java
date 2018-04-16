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
}
