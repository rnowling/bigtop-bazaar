package org.apache.bigtop.bazaar.datagenerator.configuration;

import java.util.Vector;

public class BoothParameters
{
	Vector<Booth> booths;
	
	public BoothParameters()
	{
		this.booths = new Vector<Booth>();
	}
	
	public void addBooth(Booth booth)
	{
		booths.add(booth);
	}
	
	public Vector<Booth> getBooths()
	{
		return booths;
	}
}
