package procedure;

public class Lathe extends Procedure {
	private double diameterBefore=50; // �rednica przed skrawaniem (mm)
	private double diameterAfter=30; // srednica po skrawaniu (mm)
	private double latheLength=100; // d�ugo�� toczenia powie�chni obrabianej (mm)
	private double feed=2; // posuw (mm/obr)
	private double depthOfCut=1; // g��boko�� skrawania (mm)
	private double idleTrack=5; // droga ja�owa, l1+l2 (mm)
	private double rpm=1000; // obroty (obr/min)

	public double timeOfLathe() {
		double time = -1;
		time = ((latheLength + idleTrack) / (feed * rpm)) * ((diameterBefore - diameterAfter) / depthOfCut);
		return time;
	}

}
