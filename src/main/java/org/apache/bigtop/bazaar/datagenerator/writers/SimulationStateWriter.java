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
package org.apache.bigtop.bazaar.datagenerator.writers;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.bigtop.bazaar.datagenerator.moleculardynamics.SimulationState;
import org.apache.bigtop.bazaar.datagenerator.moleculardynamics.Vec2D;

public class SimulationStateWriter
{
	String flname;
	Writer writer;
	
	public SimulationStateWriter(String flname)
	{
		this.flname = flname;
	}
	
	public void write(SimulationState state) throws IOException
	{
		if(writer == null)
		{
			Path path = Paths.get(flname);
			writer = Files.newBufferedWriter(path, Charset.forName("US-ASCII"));
		}
		
		writer.write("particles: " + state.getNumberParticles() + " " + state.getStep() + 
				" " + state.getTime() + " " + state.getKineticEnergy() + " " + 
				state.getPotentialEnergy() + "\n");
		Vec2D[] positions = state.getPositions();
		Vec2D[] velocities = state.getVelocities();
		Vec2D[] forces = state.getForces();
		for(int i = 0; i < state.getNumberParticles(); i++)
		{
			writer.write(positions[i].getX() + " " + positions[i].getY() + " " + 
					velocities[i].getX() + " " + velocities[i].getY() + " " +
					forces[i].getX() + " " + forces[i].getY() + "\n");
		}
	}
	
	public void close() throws IOException
	{
		writer.close();
	}
}
