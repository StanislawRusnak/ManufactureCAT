package procedure;

public class Grind extends Procedure{
	private double surplus;			//machining surplus [mm]
	private double rpm;				//rotational speed of machined object [obr/min]
	private double feed;			//feed [mm/obr]
	private double grindLength;		//machined length [mm]
	private double grindWidth;		//width of grinding wheel [mm]
	private int grindReps;			// number of grinding repetations / liczba przejœæ œciernicy po przedmiocie obrabianym
	private double costPerHour;		//cost of one hour procedure performing [zl]
	
	//longitudinal grinding
	public Grind(double rpm, double feed, double grindLength, double grindWidth, int grindReps
				,double costPerHour,String type,String machine,double additionalTime) {
		this.rpm = rpm;
		this.feed = feed;
		this.grindLength = grindLength;
		this.grindWidth = grindWidth;
		this.grindReps = grindReps;
		this.costPerHour = costPerHour;
		setAdditionalTime(additionalTime);
		setType(type);
		setParameters(toStringLongit());
		setMachine(machine);
		setTime(timeOfGrindLongit()+getAdditionalTime());
		setCost(costOfGrind(getTime()));
	}

	//transverse grinding
	public Grind(double surplus, double rpm, double feed, double costPerHour,String type,
			String machine,double additionalTime) {
		this.surplus = surplus;
		this.rpm = rpm;
		this.feed = feed;
		this.costPerHour = costPerHour;
		setAdditionalTime(additionalTime);
		setType(type);
		setParameters(toStringTransver());
		setMachine(machine);
		setTime(timeOfGrindTransver()+getAdditionalTime());
		setCost(costOfGrind(getTime()));
	}
	
	private double timeOfGrindLongit() {				//calculating longitudinal grinding time [min]
		double time = -1;
		time = ((grindLength + ((1/3)*grindWidth))*grindReps)/(rpm*feed);
		return time;
	}
	
	private double timeOfGrindTransver() {				//calculating transverse grinding time [min]
		double time = -1;
		time = surplus/(rpm*feed);
		return time;
	}
	
	private double costOfGrind(double time) {
		double cost = -1;
		cost = (costPerHour/60) * time;		//cost of one hour, converted to cost of one minute 
		return cost;
	}
	
	private String toStringLongit() { 
		return	"D³ugoœæ obróbkowa: " + grindLength +" [mm]"+ 
				"\nLiczba przejœæ: " + grindReps +
				"\nPosuw: " + feed + " [mm/obr]"+ 
				"\nPrêdkoœæ obr. przedmiotu: "+ rpm +" [obr/min]"+
				"\nCzas pomocniczy: "+ getAdditionalTime() +" [min]";
	}
	
	private String toStringTransver() { 
		return	"Naddatek na obróbkê: " + surplus +" [mm]"+
				"\nPosuw: " + feed + " [mm/obr]"+ 
				"\nPrêdkoœæ obr. przedmiotu: "+ rpm +" [obr/min]"+
				"\nCzas pomocniczy: "+ getAdditionalTime() +" [min]";
	}

	public double getSurplus() {
		return surplus;
	}

	public void setSurplus(double surplus) {
		this.surplus = surplus;
	}

	public double getRpm() {
		return rpm;
	}

	public void setRpm(double rpm) {
		this.rpm = rpm;
	}

	public double getFeed() {
		return feed;
	}

	public void setFeed(double feed) {
		this.feed = feed;
	}

	public double getGrindLength() {
		return grindLength;
	}

	public void setGrindLength(double grindLength) {
		this.grindLength = grindLength;
	}

	public double getGrindWidth() {
		return grindWidth;
	}

	public void setGrindWidth(double grindWidth) {
		this.grindWidth = grindWidth;
	}

	public int getGrindReps() {
		return grindReps;
	}

	public void setGrindReps(int grindReps) {
		this.grindReps = grindReps;
	}

	public double getCostPerHour() {
		return costPerHour;
	}

	public void setCostPerHour(double costPerHour) {
		this.costPerHour = costPerHour;
	}
}
