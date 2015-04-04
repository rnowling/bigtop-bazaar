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
package org.apache.bigtop.bazaar.datagenerator.configuration;



public class ParticleSimulationParameters
{
	long steps;
	double timestep;
	double temperature;
	double damping;
	double boundaryRadius;
	double boundaryStrength;
	double particleMass;

	public void setSteps(long steps)
	{
		this.steps = steps;
	}

	public void setTimestep(double timestep)
	{
		this.timestep = timestep;
	}

	public void setTemperature(double temperature)
	{
		this.temperature = temperature;
	}

	public void setDamping(double damping)
	{
		this.damping = damping;
	}

	public void setBoundaryRadius(double boundaryRadius)
	{
		this.boundaryRadius = boundaryRadius;
	}

	public void setBoundaryStrength(double boundaryStrength)
	{
		this.boundaryStrength = boundaryStrength;
	}

	public void setParticleMass(double particleMass)
	{
		this.particleMass = particleMass;
	}

	public long getSteps()
	{
		return steps;
	}
	
	public double getTimestep()
	{
		return timestep;
	}
	
	public double getTemperature()
	{
		return temperature;
	}
	
	public double getDamping()
	{
		return damping;
	}
	
	public double getBoundaryRadius()
	{
		return boundaryRadius;
	}
	
	public double getBoundaryStrength()
	{
		return boundaryStrength;
	}
	
	public double getParticleMass()
	{
		return particleMass;
	}
	
	public String toString()
	{
		String str = "steps: " + steps + "\n" +
				"timestep: " + timestep + "\n" +
				"temperature: " + temperature + "\n" +
				"damping: " + damping + "\n" +
				"particle mass: " + particleMass + "\n" +
				"boundary radius: " + boundaryRadius + "\n" +
				"boundary strength: " + boundaryStrength + "\n" +
				"booths: \n";
		
		return str;
	}
}
