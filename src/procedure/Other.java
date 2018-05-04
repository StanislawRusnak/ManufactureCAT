package procedure;

public class Other extends Procedure{
	private double costPerHour;		//koszt godziny wykonywania zabiegu [zl]
	private double mainTime;		//czas g³ówny zabiegu [min]
	private String otherParameters;		//inne parametry zabiegu
	
	public Other(double costPerHour,String type,String machine,double additionalTime,
				 double mainTime, String otherParameters) {
		this.costPerHour = costPerHour;
		this.mainTime = mainTime;
		this.otherParameters = otherParameters;
		setAdditionalTime(additionalTime);
		setType(type);
		setParameters(toString());
		setMachine(machine);
		setTime(mainTime + getAdditionalTime());
		setCost(costOfOther(getTime()));
	}
	
	private double costOfOther(double time) {
		double cost = -1;
		cost = (costPerHour/60) * time;		//koszt godzinny przeliczany na 1 minute
		return cost;
	}
	@Override
	public String toString() {
		return	otherParameters + "\nCzas g³ówny: "+ mainTime +" [min]"+
				"\nCzas pomocniczy: "+ getAdditionalTime() +" [min]";
	}

	public double getCostPerHour() {
		return costPerHour;
	}

	public void setCostPerHour(double costPerHour) {
		this.costPerHour = costPerHour;
	}

	public double getMainTime() {
		return mainTime;
	}

	public void setMainTime(double mainTime) {
		this.mainTime = mainTime;
	}

	public String getOtherParameters() {
		return otherParameters;
	}

	public void setOtherParameters(String otherParameters) {
		this.otherParameters = otherParameters;
	}
}
