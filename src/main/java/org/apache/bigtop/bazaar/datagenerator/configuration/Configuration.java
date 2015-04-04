package org.apache.bigtop.bazaar.datagenerator.configuration;


public class Configuration
{
	ParticleSimulationParameters simulationParameters;
	BoothParameters boothParameters;
	RecommendationsParameters recommendationsParameters;
	int booths;
	int customers;
	
	public ParticleSimulationParameters getSimulationParameters()
	{
		return simulationParameters;
	}
	
	public void setSimulationParameters(ParticleSimulationParameters params)
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

	public RecommendationsParameters getRecommendationsParameters()
	{
		return recommendationsParameters;
	}

	public void setRecommendationsParameters(
			RecommendationsParameters recommendationsParameters)
	{
		this.recommendationsParameters = recommendationsParameters;
	}

	public int getCustomers()
	{
		return customers;
	}

	public void setCustomers(int customers)
	{
		this.customers = customers;
	}

	public int getBooths()
	{
		return booths;
	}

	public void setBooths(int booths)
	{
		this.booths = booths;
	}
}
