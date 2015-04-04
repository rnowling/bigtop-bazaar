package org.apache.bigtop.bazaar.datagenerator;

import java.util.Vector;

import org.apache.bigtop.bazaar.datagenerator.configuration.Booth;
import org.apache.bigtop.bazaar.datagenerator.configuration.BoothParameters;

public class BoothGenerator
{
	BoothParameters params;
	
	public BoothGenerator(BoothParameters params)
	{
		this.params = params;
	}
	
	public Vector<Booth> generate()
	{
		return this.params.getBooths();
	}
}
