import static java.lang.Math.log;

public class Rocket 
{
	public double dryMass;
	public double finalMass;
	public double specificImpulse;
	public double engineThrust;
	
	protected final void ChangeDryMass(double newDryMass)
	{
		this.dryMass = newDryMass;
	}
	protected final double DryMass()
	{
		return this.dryMass;
	}
	
	protected final void ChangeFinaMass(double newFinalMass)
	{
		this.finalMass = newFinalMass;
	}
	protected final double FinalMass()
	{
		return this.finalMass;
	}
	
	protected final void ChangeSpecificImpulse(double newSpecificImpulse)
	{
		this.specificImpulse = newSpecificImpulse;
	}
	protected final double SpecificImpuls()
	{
		return this.specificImpulse;
	}
	
	protected final void ChangeEngineThrust(double newEngineThrust)
	{
		this.engineThrust = newEngineThrust;
	}
	protected final double EngineThrust()
	{
		return this.engineThrust;
	}
	
	public Rocket(double finalMass, double dryMass, double specificImpulse, double engineThrust)
	{
		this.ChangeDryMass(dryMass);
		this.ChangeFinaMass(finalMass);
		this.ChangeSpecificImpulse(specificImpulse);
		this.ChangeEngineThrust(engineThrust);

	}
	
	public Rocket()
	{
		
	}
	
	public double DeltaVCalc()
	{
		double exhaustV = this.specificImpulse * 9.80665; //Specific Impulse measured in seconds, masses are measured in kg
		double deltaVOne = this.finalMass / this.dryMass;
		double deltaVTwo = log(deltaVOne);
		double deltaV = exhaustV * deltaVTwo;
		
		return deltaV;
	}
}
