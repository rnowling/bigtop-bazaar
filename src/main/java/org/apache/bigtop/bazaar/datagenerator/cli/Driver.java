package org.apache.bigtop.bazaar.datagenerator.cli;

import java.io.IOException;

import org.apache.bigtop.bazaar.datagenerator.configuration.Configuration;
import org.apache.bigtop.bazaar.datagenerator.configuration.ConfigurationReader;

public class Driver
{
	String configFilePath;
	
	static final int NARGS = 1;
	
	private void printUsage()
	{
		String usage = "BigTop Bazaar Data Generator\n" +
				"\n" +
				"Usage: java -jar bigtop-bazaar-data-generator.jar configurationFile.json\n" +
				"\n";
		
		System.out.println(usage);
	}
	
	private void parseArgs(String[] args)
	{
		if(args.length != NARGS)
		{
			printUsage();
			System.exit(1);
		}
		
		configFilePath = args[0];
	}
	
	private Configuration readConfiguration() throws IOException
	{
		ConfigurationReader reader = new ConfigurationReader(this.configFilePath);
		return reader.readConfiguration();
	}
	
	private void run(String[] args) throws IOException
	{
		parseArgs(args);
		Configuration configuration = readConfiguration();
		
		System.out.println(configuration.toString());
	}
	
	public static void main(String[] args) throws IOException
	{
		Driver driver = new Driver();
		driver.run(args);
	}

}
