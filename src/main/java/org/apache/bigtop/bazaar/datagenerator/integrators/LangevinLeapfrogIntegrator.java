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
package org.apache.bigtop.bazaar.datagenerator.integrators;

import org.apache.bigtop.bazaar.datagenerator.base.SimulationState;

public class LangevinLeapfrogIntegrator implements Integrator
{
	double timestep;
	double temperature;
	double gamma;
	
	/**
	 * Langevin Leapfrog integrator for the Langevin stochastic differential
	 * equation.
	 * 
	 * @param timestep Timestep in units of ps
	 * @param temperature Temperature in units of K
	 * @param gamma Damping in units of 1/ps
	 */
	public LangevinLeapfrogIntegrator(double timestep, double temperature, double gamma)
	{
		this.timestep = timestep;
		this.temperature = temperature;
		this.gamma = gamma;
	}
	
	@Override
	public SimulationState integrate(SimulationState state)
	{	
		double kineticEnergy = 0.0;
		for(int i = 0; i < state.getNumberParticles(); i++)
		{
			kineticEnergy += 0.5 * state.getVelocities(i).squaredNorm();
		}
		
		state.setKineticEnergy(kineticEnergy);
		state.setTime(state.getTime() + timestep);
		
		return state;
	}

}
