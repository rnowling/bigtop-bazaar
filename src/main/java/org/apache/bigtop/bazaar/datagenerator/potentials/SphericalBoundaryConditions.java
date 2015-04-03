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
package org.apache.bigtop.bazaar.datagenerator.potentials;

import org.apache.bigtop.bazaar.datagenerator.base.Vec2D;

public class SphericalBoundaryConditions implements Potential
{
	Vec2D center;
	double radius;
	double strength;
	
	public SphericalBoundaryConditions(Vec2D center, double radius, double strength)
	{
		this.center = center;
		this.radius = radius;
		this.strength = strength;
	}
	
	@Override
	public double compute(Vec2D[] positions, Vec2D[] forces)
	{
		double totalEnergy = 0.0;
		
		for(int i = 0; i < positions.length; i++)
		{
			Vec2D pos = positions[i];
			Vec2D diff = pos.sub(center);
			double distCenter = diff.norm();
			
			double diffCircum = distCenter - radius;
			forces[i] = new Vec2D(0.0, 0.0);
			
			// outside circumference
			if(diffCircum > 0.0)
			{
				totalEnergy += strength * diffCircum * diffCircum;
				double scalarForce = 2.0 * strength * diffCircum;
				Vec2D forceVector = diff.scalarMult(-1.0 * scalarForce / distCenter);
				forces[i] = forces[i].add(forceVector);
			}
		}
		
		return totalEnergy;
	}
}
