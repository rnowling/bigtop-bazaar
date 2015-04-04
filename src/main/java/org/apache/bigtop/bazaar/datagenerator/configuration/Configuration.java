package org.apache.bigtop.bazaar.datagenerator.configuration;


public class Configuration
{
	SimulationParameters simulationParameters;
	BoothParameters boothParameters;
	
	public SimulationParameters getSimulationParameters()
	{
		return simulationParameters;
	}
	
	public void setSimulationParameters(SimulationParameters params)
	{
		this.simulationParameters = params;
	}

	public BoothParameters getBoothParameters()
	{
		return boothParameters;
	}

	public void setBoothParameters(BoothParameters boothParameters)
	{
		this.boothParameters = boothParameters;
	}
}
