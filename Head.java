import java.util.Scanner;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static java.lang.Math.log;

public class Head extends Rocket
{
	public int stageInUse = 1;
	public double postLEODeltaV;
	public boolean firstStageUsed = false;

	public static void main(String[] args)
	{
		//Stage one of the Saturn V rocket
		Rocket saturnFive = new Rocket(2300000, 131000, 236, 35100);
		Rocket saturnFiveTwo = new Rocket(496200, 40100, 421, 5141);
		Rocket saturnFiveThree = new Rocket(123000, 13500, 421, 1000);
		//An example hohmann manuver, a pretty big one too. The moon is 385,000,000m from the Earth
		HohmannManuver hohmannOne = new HohmannManuver(6678000, 41264000);
		System.out.println("Manuver Delta V: " + hohmannOne.DeltaV() + " m/s");

		boolean firstStageUsedSub = false;
		double postLEODeltaVSub;
		int stageInUseSub = 1;
		
		postLEODeltaVSub = saturnFive.DeltaVCalc() - 9400;
		
		if(postLEODeltaVSub > 0)
		{
			System.out.println("Stage One Delta V: " + saturnFive.DeltaVCalc() + " m/s");
			System.out.println("Stage Two Delta V: " + saturnFiveTwo.DeltaVCalc() + " m/s");
			System.out.println("Stage Three Delta V: " + saturnFiveThree.DeltaVCalc() + " m/s");
			System.out.println(hohmannOne.BurnTime(saturnFive.finalMass, hohmannOne.DeltaV(), saturnFive.specificImpulse, saturnFive.engineThrust, saturnFive.DeltaVCalc()) + " sec burn");
		}
		else if(postLEODeltaVSub < 0)
		{
			postLEODeltaVSub = saturnFiveTwo.DeltaVCalc() - abs(postLEODeltaVSub);
			stageInUseSub = 2;
			
			System.out.println("Stage One Delta V: " + saturnFive.DeltaVCalc() + " m/s (Used up entering orbit)");
			System.out.println("Stage Two Delta V: " + saturnFiveTwo.DeltaVCalc() + " m/s (" + postLEODeltaVSub + " m/s after entering orbit)");
			System.out.println("Stage Three Delta V: " + saturnFiveThree.DeltaVCalc() + " m/s");
			System.out.println(hohmannOne.BurnTime(saturnFiveTwo.finalMass, hohmannOne.DeltaV(), saturnFiveTwo.specificImpulse, saturnFiveTwo.engineThrust, saturnFiveTwo.DeltaVCalc()) + " sec burn");
		}
		else if(postLEODeltaVSub < 0)
		{
			postLEODeltaVSub = saturnFiveThree.DeltaVCalc() - abs(postLEODeltaVSub);
			stageInUseSub = 2;
			
			System.out.println("Stage One Delta V: " + saturnFive.DeltaVCalc() + " m/s (Used up entering orbit)");
			System.out.println("Stage Two Delta V: " + saturnFiveTwo.DeltaVCalc() + " m/s (Used up entering orbit)");
			System.out.println("Stage Three Delta V: " + saturnFiveThree.DeltaVCalc() + " m/s (" + postLEODeltaVSub + " m/s after entering orbit)");
			System.out.println(hohmannOne.BurnTime(saturnFiveTwo.finalMass, hohmannOne.DeltaV(), saturnFiveTwo.specificImpulse, saturnFiveTwo.engineThrust, saturnFiveTwo.DeltaVCalc()) + " sec burn");
		}
		else
		{
			System.out.println("All Delta-V used up");
		}
	}	
}