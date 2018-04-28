package procedure;

public class Drill extends Procedure {
	private double diameterAfter;		// œrednica przed skrawaniem [mm]
	private double drillLength;			// d³ugoœæ wiercenia powie¿chni obrabianej (mm)
	private double feed;				// posuw (mm/obr)
	private double idlePath; 			// droga ja³owa, l1+l2 (mm)
	private double rpm; 				// obroty (obr/min)
	private double costPerHour; 		// koszt godziny wykonywania zabiegu (zl)
	
	public Drill(double diameterAfter,double drillLength,
			double feed,double idlePath,double rpm,double costPerHour,String type,
			String machine, double additionalTime) {
		this.diameterAfter = diameterAfter;
		this.drillLength = drillLength;
		this.feed = feed;
		this.idlePath = idlePath;
		this.rpm = rpm;
		this.costPerHour = costPerHour;
		setAdditionalTime(additionalTime);
		setType(type);
		setParameters(toString());
		setMachine(machine);
		setTime(timeOfDrill()+getAdditionalTime());
		setCost(costOfDrill(timeOfDrill()));
		
	}
	
	public double timeOfDrill() {			//obliczanie czasu wiercenia [min]
		double time = -1;
		time = (drillLength + idlePath) / (feed*rpm);
		return time;
	}

	public double costOfDrill(double time) {
		double cost = -1;
		cost = (costPerHour/60) * time;		//koszt godzinny przeliczany na 1 minute
		return cost;
	}
	
	@Override
	public String toString() {
		return  
				"Œrednica koñcowa: " + diameterAfter +" [mm]"+ 
				"\nD³ugoœæ wiercenia: " + drillLength +" [mm]"+ 
				"\nDroga ja³owa: " + idlePath +" [mm]"+
				"\nPosuw: " + feed + " [mm/obr]"+ 
				"\nPrêdkoœæ obr. wrzeciona: "+ rpm +" [obr/min]"+
				"\nCzas pomocniczy: "+ getAdditionalTime() +" [min]";
	}
}
