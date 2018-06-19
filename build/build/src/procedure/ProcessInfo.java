package procedure;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProcessInfo {
	private StringProperty operatorName;		// name and surname of operator / procedure adding person 
	private StringProperty partName;			//name of performed part
	private StringProperty date;				//date of addition process
	private StringProperty halfProduct;			//half product name
	private DoubleProperty halfProductCost; 	//half product cost
	private IntegerProperty partQuantity;		//number of part to perform in one series
	private double preparingTime;				//preparing time + complementary time / czas przygotowawczo zakoñczeniowy + czas uzupe³niajacy
	
	public ProcessInfo(String operatorName, String partName, String halfProduct,Double halfProductCost,
			Integer partQuantity, double preparingTime) {
		this.operatorName = new SimpleStringProperty();
		this.partName = new SimpleStringProperty();
		this.date = new SimpleStringProperty();
		this.halfProduct = new SimpleStringProperty();
		this.halfProductCost = new SimpleDoubleProperty();
		this.partQuantity = new SimpleIntegerProperty();
		this.preparingTime = preparingTime;
		setOperatorName(operatorName);
		setPartName(partName);
		setHalfProduct(halfProduct);
		setHalfProductCost(halfProductCost);
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
    public String getHalfProduct() {
        return halfProduct.get();
    }
 
    public void setHalfProduct(String halfProduct) {
        this.halfProduct.setValue(halfProduct);
    }
 
    public StringProperty halfProductProperty() {
        return halfProduct;
    }
  //==
    public Double getHalfProductCost() {
        return halfProductCost.get();
    }
 
    public void setHalfProductCost(Double halfProductCost) {
        this.halfProductCost.setValue(halfProductCost);
    }
 
    public DoubleProperty halfProductCostProperty() {
        return halfProductCost;
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
			   "Czas przygotowawczo-zakoñczeniowy:  " + preparingTime + " [min]\n"+
			   "Utworzony przez:  " + operatorName.getValue() + "\n"+
			   "Data i godzina dodania:  " + date.getValue();
	}

	public double getPreparingTime() {
		return preparingTime;
	}

	public void setPreparingTime(double preparingTime) {
		this.preparingTime = preparingTime;
	}	
}
