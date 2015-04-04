package org.apache.bigtop.bazaar.datagenerator.configuration;

public class RecommendationsParameters
{
	int numberBooths;
	int numberLatentFactors;
	double interactionStrength;
	
	public int getNumberBooths()
	{
		return numberBooths;
	}
	
	public void setNumberBooths(int numberBooths)
	{
		this.numberBooths = numberBooths;
	}
	
	public int getNumberLatentFactors()
	{
		return numberLatentFactors;
	}
	
	public void setNumberLatentFactors(int numberLatentFactors)
	{
		this.numberLatentFactors = numberLatentFactors;
	}
	
	public double getInteractionStrength()
	{
		return interactionStrength;
	}
	
	public void setInteractionStrength(double interactionStrength)
	{
		this.interactionStrength = interactionStrength;
	}
}
