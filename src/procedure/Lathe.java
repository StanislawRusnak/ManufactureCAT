package procedure;

public class Lathe extends Procedure {
	private double diameterBefore = 50; // �rednica przed skrawaniem (mm)
	private double diameterAfter = 30; // srednica po skrawaniu (mm)
	private double latheLength = 100; // d�ugo�� toczenia powie�chni obrabianej (mm)
	private double feed = 2; // posuw (mm/obr)
	private double depthOfCut = 1; // g��boko�� skrawania (mm)
	private double idleTrack = 5; // droga ja�owa, l1+l2 (mm)
	private double rpm = 1000; // obroty (obr/min)
	private double costPerHour = 2; // koszt godziny wykonywania zabiegu (zl)

	public Lathe(double diameterBefore, double diameterAfter, double latheLength, double feed,
			double depthOfCut, double idleTrack, double rpm, double costPerHour,String type,
			String machine,double preparingTime, double additionalTime) {
		this.diameterBefore = diameterBefore;
		this.diameterAfter = diameterAfter;
		this.latheLength = latheLength;
		this.feed = feed;
		this.depthOfCut = depthOfCut;
		this.idleTrack = idleTrack;
		this.rpm = rpm;
		this.costPerHour = costPerHour;
		setPreparingTime(preparingTime);
		setAdditionalTime(additionalTime);

		setType(type);
		setParameters(toString());
		setMachine(machine);
		setTime(timeOfLathe()+getPreparingTime()+getAdditionalTime());
		setCost(costOfLathe());
	}
	
	public double timeOfLathe() {
		double time = -1;
		time = ((latheLength + idleTrack) / (feed * rpm)) * ((diameterBefore - diameterAfter) / depthOfCut);
		return time;
	}

	public double costOfLathe() {
		double cost = -1;
		cost = costPerHour * timeOfLathe();
		return cost;
	}

	@Override
	public String toString() {
		return  "�rednica poczatkowa: " + diameterBefore +" [mm]"+ 
				"\n�rednica ko�cowa: " + diameterAfter +" [mm]"+ 
				"\nD�ugo�� toczenia: " + latheLength +" [mm]"+ 
				"\nDroga ja�owa: " + idleTrack +" [mm]"+
				"\nPosuw: " + feed + " [mm/obr]"+ 
				"\nG�eboko�� skrawania: " + depthOfCut +" [mm]"+
				"\nPr�dko�� obr. wrzeciona: "+ rpm +" [obr/min]"+
				"\nCzas przygot-zak: "+ getPreparingTime() +" [min]"+
				"\nCzas pomocniczy: "+ getAdditionalTime() +" [min]";
	}
}
