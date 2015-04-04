package org.apache.bigtop.bazaar.datagenerator.configuration;

public class LatentVariableModelParameters
{
	int numberBooths;
	int numberUsers;
	double interactionStrength;
	
	public int getNumberBooths()
	{
		return numberBooths;
	}
	
	public void setNumberBooths(int numberBooths)
	{
		this.numberBooths = numberBooths;
	}
	
	public int getNumberUsers()
	{
		return numberUsers;
	}
	
	public void setNumberUsers(int numberUsers)
	{
		this.numberUsers = numberUsers;
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
