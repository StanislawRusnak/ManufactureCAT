package procedure;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Procedure {
	//===== main fields used in TableView table 
	private StringProperty type; 	//type of procedure (np toczenie wzd�u�ne)
	private StringProperty parameters;  //procedure parameters
	private StringProperty machine;     //machine type
	private DoubleProperty time;		//procedure lasting time
	private DoubleProperty cost;		//cost of procedure performing
	
	
	//=====additional fields necessary to calculating main fields values
	private double additionalTime;		//additional time (np. dosuni�cie narz�dzia, wymiana narz itp)
	
	public Procedure() {
		this.type = new SimpleStringProperty();
		this.parameters = new SimpleStringProperty();
		this.machine = new SimpleStringProperty();
		this.time = new SimpleDoubleProperty();
		this.cost = new SimpleDoubleProperty();
	}
	public Procedure(String type,String parameters, String machine,Double time, Double cost) {
		this.type = new SimpleStringProperty();
		this.parameters = new SimpleStringProperty();
		this.machine = new SimpleStringProperty();
		this.time = new SimpleDoubleProperty();
		this.cost = new SimpleDoubleProperty();
		setType(type);
		setParameters(parameters);
		setMachine(machine);
		setTime(time);
		setCost(cost);
	}
	
	// getters and setters
    public String getType() {
        return type.get();
    }
 
    public void setType(String type) {
        this.type.setValue(type);
    }
 
    public StringProperty typeProperty() {
        return type;
    }
    //==
    public String getParameters() {
        return parameters.get();
    }
 
    public void setParameters(String parameters) {
        this.parameters.setValue(parameters);
    }
 
    public StringProperty parametersProperty() {
        return parameters;
    }
    //==
    public String getMachine() {
        return machine.get();
    }
 
    public void setMachine(String machine) {
        this.machine.setValue(machine);
    }
 
    public StringProperty machineProperty() {
        return machine;
    }
    //==
    public Double getTime() {
        return time.get();
    }
 
    public void setTime(Double time) {		// rounding UP to one hundreth of minute / zaokr�glanie w G�R� do jednej setnej minuty
    	time*=100;
    	time = Math.ceil(time);
    	time/=100;
        this.time.setValue(time);
    }
 
    public DoubleProperty timeProperty() {	
        return time;
    }
    //==
    public Double getCost() {
        return cost.get();
    }
 
    public void setCost(Double cost) {	// rounding UP to one grosh / zaokr�glanie w G�R� do jednego grosza
    	cost*=100;
    	cost= Math.ceil(cost);
    	cost/=100;
        this.cost.setValue(cost);
    }
 
    public DoubleProperty costProperty() {
        return cost;
    }
	//==
	public double getAdditionalTime() {
		return additionalTime;
	}

	public void setAdditionalTime(double additionalTime) {
		this.additionalTime = additionalTime;
	}

	@Override
	public String toString() {
		return "Procedure [type=" + type + ", parameters=" + parameters + ", machine=" + machine + ", time=" + time
				+ ", cost=" + cost + ", additionalTime=" + additionalTime + "]";
	}
}
