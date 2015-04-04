package org.apache.bigtop.bazaar.datagenerator;

import java.util.Vector;

import org.apache.bigtop.bazaar.datagenerator.configuration.Booth;
import org.apache.bigtop.bazaar.datagenerator.configuration.BoothParameters;
import org.apache.bigtop.bazaar.datagenerator.latentvariablemodel.LatentVariableModel;

public class BoothGenerator
{
	BoothParameters params;
	LatentVariableModel model;
	
	public BoothGenerator(BoothParameters params, LatentVariableModel model)
	{
		this.params = params;
		this.model = model;
	}
	
	public Vector<Booth> generate()
	{
		return this.params.getBooths();
	}
}
