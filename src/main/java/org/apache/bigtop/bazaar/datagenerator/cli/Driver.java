/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.bigtop.bazaar.datagenerator.cli;

import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import org.apache.bigtop.bazaar.datagenerator.BoothGenerator;
import org.apache.bigtop.bazaar.datagenerator.CustomerWeightsGenerator;
import org.apache.bigtop.bazaar.datagenerator.LatentVariableGenerator;
import org.apache.bigtop.bazaar.datagenerator.ParticleSimulation;
import org.apache.bigtop.bazaar.datagenerator.base.SimulationState;
import org.apache.bigtop.bazaar.datagenerator.configuration.Booth;
import org.apache.bigtop.bazaar.datagenerator.configuration.BoothParameters;
import org.apache.bigtop.bazaar.datagenerator.configuration.Configuration;
import org.apache.bigtop.bazaar.datagenerator.configuration.ConfigurationReader;
import org.apache.bigtop.bazaar.datagenerator.configuration.ParticleSimulationParameters;
import org.apache.bigtop.bazaar.datagenerator.configuration.RecommendationsParameters;
import org.apache.bigtop.bazaar.datagenerator.latentvariablemodel.Matrix;

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
		Configuration configuration = reader.readConfiguration();
		return configuration;
	}
	
	
	private void runSimulation(ParticleSimulation simulation, long steps)
	{
		for(long i = 0; i < steps; i++)
		{
			SimulationState state = simulation.step();
			
			if(i % 100 == 0)
			{
				System.out.println("Step: " + i + ", " +
						"Time: " + state.getTime() + ", " +
						"Kinetic Energy: " + state.getKineticEnergy() + ", " +
						"Potential Energy: " + state.getPotentialEnergy());
			}
		}
	}
	
	private void run(String[] args) throws IOException
	{
		parseArgs(args);
		Configuration config = readConfiguration();
		
		ParticleSimulationParameters particleSimParams = config.getSimulationParameters();
		RecommendationsParameters recParams = config.getRecommendationsParameters();
		BoothParameters boothParams = config.getBoothParameters();
		
		System.out.println(particleSimParams);
		System.out.println(recParams);
		System.out.println(boothParams);
		
		Random rng = new Random();
		
		System.out.println("Generating booths");
		BoothGenerator boothGenerator = new BoothGenerator(boothParams);
		Vector<Booth> booths = boothGenerator.generate();
		
		System.out.println();
		for(Booth booth : booths)
		{
			System.out.println("Booth: " + booth.getPositionX() + " " + booth.getPositionY() + " " + booth.getStrength());
		}
		System.out.println();
		
		System.out.println("Generating latent variables");
		LatentVariableGenerator lvmGenerator =
				new LatentVariableGenerator(recParams, booths.size(), rng);
		Matrix latentVariables = lvmGenerator.generate();
		
		System.out.println("Generating user recommendations");
		CustomerWeightsGenerator custWeightsGenerator =
					new CustomerWeightsGenerator(recParams, rng);
		Matrix customerWeights = custWeightsGenerator.generate(config.getCustomers());
		
		Matrix recommendations = latentVariables.multiply(customerWeights)
				.scalarMultiply(recParams.getInteractionStrengthScaleFactor());
		
		if(recParams.getInteractionStrengthOverride() != -1.0)
		{
			recommendations = new Matrix(booths.size(), config.getCustomers(), recParams.getInteractionStrengthOverride());
		}
		
		System.out.println("Simulating particles");
		ParticleSimulation simulation = new ParticleSimulation(config.getSimulationParameters(), booths, 
				recommendations, rng);
		runSimulation(simulation, config.getSimulationParameters().getSteps());
	}
	
	public static void main(String[] args) throws IOException
	{
		Driver driver = new Driver();
		driver.run(args);
	}

}
