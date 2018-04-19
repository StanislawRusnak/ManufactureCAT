package procedure;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProcessInfo {
	private StringProperty operatorName;		//imie i nazwisko operatora, osoby dodaj¹cej zabieg
	private StringProperty partName;			//nazwa wykonywanego detalu/czêœci
	private StringProperty date;				//data dodania procesu
	private IntegerProperty partQuantity;		//liczba sztuk do wykonania w serii
	
	public ProcessInfo(String operatorName, String partName, Integer partQuantity) {
		this.operatorName = new SimpleStringProperty();
		this.partName = new SimpleStringProperty();
		this.date = new SimpleStringProperty();
		this.partQuantity = new SimpleIntegerProperty();
		setOperatorName(operatorName);
		setPartName(partName);
		setPartQuantity(partQuantity);
		setDate(addDate());
	}
	
	private String addDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		Date date = new Date();
		return dateFormat.format(date);
	}
	// getters and setters
    public String getOperatorName() {
        return operatorName.get();
    }
 
    public void setOperatorName(String operatorName) {
        this.operatorName.setValue(operatorName);
    }
 
    public StringProperty operatorNameProperty() {
        return operatorName;
    }
    //==
    public String getPartName() {
        return partName.get();
    }
 
    public void setPartName(String partName) {
        this.partName.setValue(partName);
    }
 
    public StringProperty partNameProperty() {
        return partName;
    }
    //==
    public String getDate() {
        return date.get();
    }
 
    public void setDate(String date) {
        this.date.setValue(date);
    }
 
    public StringProperty dateProperty() {
        return date;
    }
    //==
    public Integer getPartQuantity() {
        return partQuantity.get();
    }
 
    public void setPartQuantity(Integer partQuantity) {
        this.partQuantity.setValue(partQuantity);
    }
 
    public IntegerProperty partQuantityProperty() {
        return partQuantity;
    }
	@Override
	public String toString() {
		return "Nazwa czêœci:  " + partName.getValue() + "\n"+
			   "Seria:  " + partQuantity.getValue() + " szt.\n"+
			   "Utworzony przez:  " + operatorName.getValue() + "\n"+
			   "Data i godzina dodania:  " + date.getValue();
	}	
}
