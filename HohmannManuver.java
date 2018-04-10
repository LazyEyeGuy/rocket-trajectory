import java.lang.Math;
import java.lang.*;

public class HohmannManuver 
{
	public double initHeight;
	public double finalHeight;
	
	protected final void ChangeInitHeight(double newInitHeight)
	{
		this.initHeight = newInitHeight;
	}
	protected final double DryMass()
	{
		return this.initHeight;
	}
	
	protected final void ChangeFinalHeight(double newFinalHeight)
	{
		this.finalHeight = newFinalHeight;
	}
	protected final double FinalHeight()
	{
		return this.finalHeight;
	}
	
	public HohmannManuver(double initHeight, double finalHeight)
	{
		this.ChangeInitHeight(initHeight);
		this.ChangeFinalHeight(finalHeight);
	}
	
	public HohmannManuver()
	{
		
	}
	
	public double DeltaV()
	{
		long earthGM = 398600441800000L;
				
		/*Delta-V calculation 1*/
		double periapsis;
		double apoapsis;
		
		if(initHeight < finalHeight)
		{
			periapsis = initHeight;
			apoapsis = finalHeight;
		}
		else
		{
			periapsis = finalHeight;
			apoapsis = initHeight;
		}
		
		//Lower orbit calculation set
		double semiMajorOneOne = periapsis * 2;
		double semiMajorOne = semiMajorOneOne / 2;
		double lowerOneOne = 2 / periapsis;
		double lowerOneTwo = 1 / semiMajorOne;
		double lowerOneThree = lowerOneOne - lowerOneTwo;
		double lowerOneFour = earthGM * lowerOneThree;
		double lowerOne  = Math.sqrt(lowerOneFour);
		
		double semiMajorTwoOne = periapsis + apoapsis;
		double semiMajorTwo = semiMajorTwoOne / 2;
		double lowerTwoOne = 2 / periapsis;
		double lowerTwoTwo = 1 / semiMajorTwo;
		double lowerTwoThree = lowerTwoOne - lowerTwoTwo;
		double lowerTwoFour = earthGM * lowerTwoThree;
		double lowerTwo = Math.sqrt(lowerTwoFour);
		
		//Higher orbit calculation set
		double semiMajorThreeOne = periapsis + apoapsis;
		double semiMajorThree = semiMajorThreeOne / 2;
		double upperOneOne = 2 / apoapsis;
		double upperOneTwo = 1 / semiMajorThree;
		double upperOneThree = upperOneOne - upperOneTwo;
		double upperOneFour = earthGM * upperOneThree;
		double upperOne = Math.sqrt(upperOneFour);
		
		double semiMajorFourOne = apoapsis * 2;
		double semiMajorFour = semiMajorFourOne / 2;
		double upperTwoOne = 2 / apoapsis;
		double upperTwoTwo = 1 / semiMajorFour;
		double upperTwoThree = upperTwoOne - upperTwoTwo;
		double upperTwoFour = earthGM * upperTwoThree;
		double upperTwo = Math.sqrt(upperTwoFour);
				
		double lower = lowerTwo - lowerOne;
		double upper = upperTwo - upperOne;
		double deltaV = upper + lower;
		return deltaV;
	}
	
	public double BurnTime(double initMass, double deltaV, double specificImpulse, double engineThrust, double craftDeltaV)
	{
		double ve = specificImpulse * 9.81;
		double k = 0;
		
		if(deltaV < ve * .1)
		{
			k = 1;
		}else if(deltaV < ve * .35 && deltaV >= ve * .1)
		{
			k = 0.9;
		}else if(deltaV < ve * .65 && deltaV >= ve * .35)
		{
			k = 0.8;
		}else if(deltaV < ve * .85 && deltaV >= ve * .65)
		{
			k = 0.7;
		}else if(deltaV >= ve * .85)
		{
			k = 0.6;
		}
		
		double burnTimeOne = initMass / engineThrust;
		double burnTimeTwo = k * deltaV;
		double burnTime = burnTimeOne * burnTimeTwo;
		
		double deltaVRemaining = craftDeltaV - deltaV;
		double deltaVUsed = deltaV / craftDeltaV * 100;
		double deltaVRemainingPercent = 100 - deltaVUsed;
				
		System.out.println("\n" + deltaVRemaining + "m/s Delta V remaining");
		System.out.println(deltaVUsed + "% of Delta V used, " + deltaVRemainingPercent + "% remaining\n");
		return burnTime;
	}
}
