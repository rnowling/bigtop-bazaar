package org.apache.bigtop.bazaar.datagenerator;

import java.util.Random;

import org.apache.bigtop.bazaar.datagenerator.configuration.LatentVariableModelParameters;
import org.apache.bigtop.bazaar.datagenerator.latentvariablemodel.LatentVariableModel;

public class LatentVariableModelGenerator
{
	int booths;
	int users;
	double fillValue;
	Random rng;
	
	public LatentVariableModelGenerator(LatentVariableModelParameters parameters, Random rng)
	{
		this.booths = parameters.getNumberBooths();
		this.users = parameters.getNumberUsers();
		this.fillValue = parameters.getInteractionStrength();
		this.rng = rng;
	}
	
	public LatentVariableModel generate()
	{
		return new LatentVariableModel(booths, users, fillValue);
	}
}
