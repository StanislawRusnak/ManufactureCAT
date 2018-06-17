package procedure;

public class Lathe extends Procedure {
	private double diameterBefore; // diameter before machining (mm)
	private double diameterAfter; // diameter after machining (mm)
	private double latheLength; // length of machined surface (mm)
	private double feed;		 // feed (mm/obr)
	private double depthOfCut; // depth of lathe (mm)
	private double idleTrack; // idle path of tool, l1+l2 (mm)
	private double rpm; 	  // rotational speed of tool (obr/min)
	private double costPerHour; //cost of one hour procedure performing [zl]

	//longitudinal lathe
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
		setCost(costOfLathe(getTime()));
	}
	//transverse lathe
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
		setCost(costOfLathe(getTime()));
	}
	private double timeOfLatheLongit() {				//calculating longitudinal lathe time [min]
		double time = -1;
		double i = Math.ceil(((diameterBefore - diameterAfter) / depthOfCut)); // rounding up number of repetations to integer / zaokr¹glenie liczby przejœæ do ca³kowitych w góre
		time = ((latheLength + idleTrack) / (feed * rpm)) * i;
		return time;
	}
	private double timeOfLatheTransver() {			//calculating transverse lathe time [min]
		double time = -1;
		double i = Math.ceil((latheLength / depthOfCut)); 
		time = (((diameterBefore/2) + idleTrack) / (feed * rpm)) * i;
		return time;
	}

	private double costOfLathe(double time) {
		double cost = -1;
		cost = (costPerHour/60) * time;			//cost of one hour, converted to cost of one minute 
		return cost;
	}

	private String toStringLongit() {
		return  "Œrednica pocz¹tkowa: " + diameterBefore +" [mm]"+ 
				"\nŒrednica koñcowa: " + diameterAfter +" [mm]"+ 
				"\nD³ugoœæ toczenia: " + latheLength +" [mm]"+ 
				"\nDroga ja³owa: " + idleTrack +" [mm]"+
				"\nPosuw: " + feed + " [mm/obr]"+ 
				"\nG³êbokoœæ skrawania: " + depthOfCut +" [mm]"+
				"\nPrêdkoœæ obr. wrzeciona: "+ rpm +" [obr/min]"+
				"\nCzas pomocniczy: "+ getAdditionalTime() +" [min]";
	}
	private String toStringTransver() {
		return  "Œrednica pocz¹tkowa: " + diameterBefore +" [mm]"+  
				"\nD³ugoœæ toczenia: " + latheLength +" [mm]"+ 
				"\nDroga ja³owa: " + idleTrack +" [mm]"+
				"\nPosuw: " + feed + " [mm/obr]"+ 
				"\nG³êbokoœæ skrawania: " + depthOfCut +" [mm]"+
				"\nPrêdkoœæ obr. wrzeciona: "+ rpm +" [obr/min]"+
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
