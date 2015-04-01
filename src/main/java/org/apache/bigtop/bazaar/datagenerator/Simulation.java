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
package org.apache.bigtop.bazaar.datagenerator;

import org.apache.bigtop.bazaar.datagenerator.base.SimulationState;
import org.apache.bigtop.bazaar.datagenerator.base.Topology;
import org.apache.bigtop.bazaar.datagenerator.base.Vec2D;
import org.apache.bigtop.bazaar.datagenerator.integrators.Integrator;

public class Simulation
{
	Topology topology;
	Integrator integrator;
	SimulationState currentState;
	
	public Simulation(Topology topology, Integrator integrator)
	{
		this.topology = topology;
		this.integrator = integrator;
		
		initializeSimulationState();
	}
	
	protected void initializeSimulationState()
	{
		currentState = new SimulationState(topology.getNumberParticles());
		currentState.setTime(0.0);
		currentState.setPotentialEnergy(0.0);
		currentState.setKineticEnergy(0.0);
		
		for(int i = 0; i < topology.getNumberParticles(); i++)
		{
			currentState.setVelocities(i, topology.getInitialVelocities(i));
			currentState.setPositions(i, topology.getInitialPositions(i));
			currentState.setForces(i, new Vec2D(0.0, 0.0));
		}
	}
	
	public SimulationState step()
	{
		currentState = integrator.integrate(currentState);
		
		return currentState;
	}
}