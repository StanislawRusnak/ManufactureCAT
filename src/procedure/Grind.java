package procedure;

public class Grind extends Procedure{
	private double surplus;			//naddatek obr�bkowy [mm]
	private double rpm;				//pr�dko�� obrotowa przedmiotu szlifowanego [obr/min]
	private double feed;			//posuw [mm/obr]
	private double grindLength;		//d�ugo�� do obr�bki [mm]
	private double grindWidth;		//szeroko�� �ciernicy [mm]
	private int grindReps;			//liczba przej�� �ciernicy po przedmiocie obrabianym
	private double costPerHour;		//koszt godziny wykonywania zabiegu [zl]
	
	//konstruktor dla szlifowania wzd�u�nego
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
		setCost(costOfGrind(timeOfGrindLongit()));
	}

	//konstruktor dla szlifowania poprzecznego
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
		setCost(costOfGrind(timeOfGrindTransver()));
	}
	
	private double timeOfGrindLongit() {				//obliczanie czasu szlifowania wzd�u�nego
		double time = -1;
		time = ((grindLength + ((1/3)*grindWidth))*grindReps)/(rpm*feed);
		return time;
	}
	
	private double timeOfGrindTransver() {				//obliczanie czasu szlifowania poprzecznego
		double time = -1;
		time = surplus/(rpm*feed);
		return time;
	}
	
	private double costOfGrind(double time) {
		double cost = -1;
		cost = (costPerHour/60) * time;		//koszt godzinny przeliczany na 1 minute
		return cost;
	}
	
	private String toStringLongit() { 
		return	"D�ugo�� obr�bkowa: " + grindLength +" [mm]"+ 
				"\nLiczba przej��: " + grindReps +
				"\nPosuw: " + feed + " [mm/obr]"+ 
				"\nPr�dko�� obr. przedmiotu: "+ rpm +" [obr/min]"+
				"\nCzas pomocniczy: "+ getAdditionalTime() +" [min]";
	}
	
	private String toStringTransver() { 
		return	"Naddatek na obr�bk�: " + surplus +" [mm]"+
				"\nPosuw: " + feed + " [mm/obr]"+ 
				"\nPr�dko�� obr. przedmiotu: "+ rpm +" [obr/min]"+
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
