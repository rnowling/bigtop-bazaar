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
import org.apache.bigtop.bazaar.datagenerator.latentvariablemodel.Vector;

public class GaussianPotential implements Potential
{
	Vec2D center;
	double variance;
	Vector scale;
	
	public GaussianPotential(Vec2D center, double radius, Vector strength)
	{
		// use 3 std devs distance
		variance = (radius / 3.0) * (radius  / 3.0);
		// set height exactly to strength
		scale = strength.scalarMult(Math.sqrt(2.0 * Math.PI * variance));
		this.center = center;
	}
	
	@Override
	public double compute(Vec2D[] positions, Vec2D[] forces)
	{
		double totalEnergy = 0.0;
		
		// -1.0 b/c we want it rotated 180 degrees
		// scale because we want to control height
		Vector constants = scale.scalarMult(-1.0 / Math.sqrt(2.0 * Math.PI * variance));
		
		for(int i = 0; i < positions.length; i++)
		{
			Vec2D pos = positions[i];
			
			Vec2D diff = pos.sub(center);
			double r = diff.norm();
			
			double exp = Math.exp(-1.0 * r * r / (2.0 * variance));
			double dexpdr = -2.0 * r / (2.0 * variance);
			double forceScalar = constants.getElement(i) * exp * dexpdr;
			Vec2D forceVector = diff.scalarMult(-1.0 * forceScalar);
			totalEnergy += constants.getElement(i) * exp;
			
			forces[i] = forces[i].add(forceVector);
		}

		return totalEnergy;
	}
}
