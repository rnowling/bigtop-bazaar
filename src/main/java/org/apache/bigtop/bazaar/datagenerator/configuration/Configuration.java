package org.apache.bigtop.bazaar.datagenerator.configuration;


public class Configuration
{
	SimulationParameters simulationParameters;
	BoothParameters boothParameters;
	LatentVariableModelParameters latentVariableModelParameters;
	
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

	public LatentVariableModelParameters getLatentVariableModelParameters()
	{
		return latentVariableModelParameters;
	}

	public void setLatentVariableModelParameters(
			LatentVariableModelParameters latentVariableModelParameters)
	{
		this.latentVariableModelParameters = latentVariableModelParameters;
	}
}
