package procedure;

public class Lathe extends Procedure {
	private double diameterBefore; // �rednica przed skrawaniem (mm)
	private double diameterAfter; // srednica po skrawaniu (mm)
	private double latheLength; // d�ugo�� toczenia powie�chni obrabianej (mm)
	private double feed; // posuw (mm/obr)
	private double depthOfCut; // g��boko�� skrawania (mm)
	private double idleTrack; // droga ja�owa, l1+l2 (mm)
	private double rpm; // obroty (obr/min)
	private double costPerHour; // koszt godziny wykonywania zabiegu (zl)

	//konstruktor dla toczenia wzd�u�nego
	public Lathe(double diameterBefore, double diameterAfter, double latheLength, double feed,
			double depthOfCut, double idleTrack, double rpm, double costPerHour,String type,
			String machine, double additionalTime) {
		this.diameterBefore = diameterBefore;
		this.diameterAfter = diameterAfter;
		this.latheLength = latheLength;
		this.feed = feed;
		this.depthOfCut = depthOfCut;
		this.idleTrack = idleTrack;
		this.rpm = rpm;
		this.costPerHour = costPerHour;
		setAdditionalTime(additionalTime);
		setType(type);
		setParameters(toStringLongit());
		setMachine(machine);
		setTime(timeOfLatheLongit()+getAdditionalTime());
		setCost(costOfLathe(timeOfLatheLongit()));
	}
	//konstruktor dla toczenia poprzecznego
	public Lathe(double diameterBefore, double latheLength, double feed,
			double depthOfCut, double idleTrack, double rpm, double costPerHour,String type,
			String machine, double additionalTime) {
		this.diameterBefore = diameterBefore;
		this.latheLength = latheLength;
		this.feed = feed;
		this.depthOfCut = depthOfCut;
		this.idleTrack = idleTrack;
		this.rpm = rpm;
		this.costPerHour = costPerHour;
		setAdditionalTime(additionalTime);
		setType(type);
		setParameters(toStringTransver());
		setMachine(machine);
		setTime(timeOfLatheTransver()+getAdditionalTime());
		setCost(costOfLathe(timeOfLatheTransver()));
	}
	public double timeOfLatheLongit() {				//obliczanie czasu toczenia wzd�u�nego
		double time = -1;
		double i = Math.ceil(((diameterBefore - diameterAfter) / depthOfCut)); //zaokr�glenie liczby przej�� do ca�kowitych w g�re
		time = ((latheLength + idleTrack) / (feed * rpm)) * i;
		return time;
	}
	public double timeOfLatheTransver() {			//obliczanie czasu toczenia poprzecznego
		double time = -1;
		double i = Math.ceil((latheLength / depthOfCut)); 
		time = (((diameterBefore/2) + idleTrack) / (feed * rpm)) * i;
		return time;
	}

	public double costOfLathe(double time) {
		double cost = -1;
		cost = costPerHour * time;
		return cost;
	}

	public String toStringLongit() {
		return  "�rednica pocz�tkowa: " + diameterBefore +" [mm]"+ 
				"\n�rednica ko�cowa: " + diameterAfter +" [mm]"+ 
				"\nD�ugo�� toczenia: " + latheLength +" [mm]"+ 
				"\nDroga ja�owa: " + idleTrack +" [mm]"+
				"\nPosuw: " + feed + " [mm/obr]"+ 
				"\nG��boko�� skrawania: " + depthOfCut +" [mm]"+
				"\nPr�dko�� obr. wrzeciona: "+ rpm +" [obr/min]"+
				"\nCzas pomocniczy: "+ getAdditionalTime() +" [min]";
	}
	public String toStringTransver() {
		return  "�rednica pocz�tkowa: " + diameterBefore +" [mm]"+  
				"\nD�ugo�� toczenia: " + latheLength +" [mm]"+ 
				"\nDroga ja�owa: " + idleTrack +" [mm]"+
				"\nPosuw: " + feed + " [mm/obr]"+ 
				"\nG��boko�� skrawania: " + depthOfCut +" [mm]"+
				"\nPr�dko�� obr. wrzeciona: "+ rpm +" [obr/min]"+
				"\nCzas pomocniczy: "+ getAdditionalTime() +" [min]";
	}
	public double getDiameterBefore() {
		return diameterBefore;
	}
	public void setDiameterBefore(double diameterBefore) {
		this.diameterBefore = diameterBefore;
	}
	public double getDiameterAfter() {
		return diameterAfter;
	}
	public void setDiameterAfter(double diameterAfter) {
		this.diameterAfter = diameterAfter;
	}
	public double getLatheLength() {
		return latheLength;
	}
	public void setLatheLength(double latheLength) {
		this.latheLength = latheLength;
	}
	public double getFeed() {
		return feed;
	}
	public void setFeed(double feed) {
		this.feed = feed;
	}
	public double getDepthOfCut() {
		return depthOfCut;
	}
	public void setDepthOfCut(double depthOfCut) {
		this.depthOfCut = depthOfCut;
	}
	public double getIdleTrack() {
		return idleTrack;
	}
	public void setIdleTrack(double idleTrack) {
		this.idleTrack = idleTrack;
	}
	public double getRpm() {
		return rpm;
	}
	public void setRpm(double rpm) {
		this.rpm = rpm;
	}
	public double getCostPerHour() {
		return costPerHour;
	}
	public void setCostPerHour(double costPerHour) {
		this.costPerHour = costPerHour;
	}
}
