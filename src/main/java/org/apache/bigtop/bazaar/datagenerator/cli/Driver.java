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

import org.apache.bigtop.bazaar.datagenerator.Simulation;
import org.apache.bigtop.bazaar.datagenerator.base.SimulationState;
import org.apache.bigtop.bazaar.datagenerator.base.Topology;
import org.apache.bigtop.bazaar.datagenerator.base.Vec2D;
import org.apache.bigtop.bazaar.datagenerator.base.VelocitySampler;
import org.apache.bigtop.bazaar.datagenerator.configuration.Configuration;
import org.apache.bigtop.bazaar.datagenerator.configuration.ConfigurationReader;
import org.apache.bigtop.bazaar.datagenerator.integrators.LangevinLeapfrogIntegrator;
import org.apache.bigtop.bazaar.datagenerator.integrators.Integrator;

public class Driver
{
	String configFilePath;
	Configuration configuration;
	Topology topology;
	Random rng;
	Simulation simulation;
	
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
	
	private void readConfiguration() throws IOException
	{
		ConfigurationReader reader = new ConfigurationReader(this.configFilePath);
		configuration = reader.readConfiguration();
	}
	
	private void initializeRng()
	{
		rng = new Random();
	}
	
	private void buildTopology()
	{
		topology = new Topology(configuration.getNumberParticles(),
				configuration.getTemperature());
		
		for(int i = 0; i < configuration.getNumberParticles(); i++)
		{
			topology.setParticleMass(i, configuration.getParticleMass());
			topology.setInitialPositions(i, new Vec2D(0.0, 0.0));
		}
		
		VelocitySampler velocitySampler = new VelocitySampler(rng);
		velocitySampler.sample(topology);
	}
	
	private void buildSimulation()
	{
		Integrator integrator = new LangevinLeapfrogIntegrator(configuration.getTimestep(),
				configuration.getTemperature(), configuration.getDamping());
		simulation = new Simulation(topology, integrator);
	}
	
	private void runSimulation()
	{
		for(long i = 0; i < configuration.getSteps(); i++)
		{
			SimulationState state = simulation.step();
			
			System.out.println("Step: " + i + ", " +
					"Time: " + state.getTime() + ", " +
					"Kinetic Energy: " + state.getKineticEnergy() + ", " +
					"Potential Energy: " + state.getPotentialEnergy());
		}
	}
	
	private void run(String[] args) throws IOException
	{
		parseArgs(args);
		readConfiguration();
		
		System.out.println(configuration.toString());
		
		initializeRng();
		buildTopology();
		buildSimulation();
		
		runSimulation();
	}
	
	public static void main(String[] args) throws IOException
	{
		Driver driver = new Driver();
		driver.run(args);
	}

}
