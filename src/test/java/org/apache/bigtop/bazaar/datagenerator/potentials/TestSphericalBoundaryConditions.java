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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.bigtop.bazaar.datagenerator.base.Vec2D;
import org.junit.Test;


public class TestSphericalBoundaryConditions
{
	static final double EPS = 1e-5;
	
	@Test
	public void testInside()
	{
		final double radius = 3.0;
		final double strength = 2.0;
		final Vec2D center = new Vec2D(0.0, 0.0);
		
		final double expectedPE = 0.0;
		
		Potential potential = new SphericalBoundaryConditions(center, radius, strength);
		potential.update(new Vec2D[]{center});
		final double observedPE = potential.getEnergy();
		final Vec2D observedForces = potential.getForces()[0];
		
		assertEquals("Observed PE: " + observedPE + ", expected PE: " + expectedPE,
				expectedPE, observedPE, EPS);
		
		Vec2D expectedForces = new Vec2D(0.0, 0.0);
		double forceDist = expectedForces.distance(observedForces);
		assertEquals("Observed force diff: " + forceDist, 0.0, forceDist, EPS);
	}
	
	@Test
	public void testBoundary()
	{
		final double radius = 3.0;
		final double strength = 2.0;
		final Vec2D center = new Vec2D(0.0, 0.0);
		final Vec2D particleLocation = new Vec2D(3.0, 0.0);
		
		final double expectedPE = 0.0;
		
		Potential potential = new SphericalBoundaryConditions(center, radius, strength);
		potential.update(new Vec2D[]{particleLocation});
		final double observedPE = potential.getEnergy();
		
		
		assertEquals("Observed PE: " + observedPE + ", expected PE: " + expectedPE,
				expectedPE, observedPE, EPS);
	}
	
	@Test
	public void testOutside()
	{
		final double radius = 3.0;
		final double strength = 2.0;
		final Vec2D center = new Vec2D(0.0, 0.0);
		final Vec2D particleLocation = new Vec2D(4.0, 0.0);
		
		Potential potential = new SphericalBoundaryConditions(center, radius, strength);
		potential.update(new Vec2D[]{particleLocation});
		final double observedPE = potential.getEnergy();
		final Vec2D observedForces = potential.getForces()[0];
		
		
		assertTrue("Observed PE: " + observedPE, observedPE > 0.0);
		assertTrue("Observed force: " + observedForces.getX(), observedForces.getX() < 0.0);
	}
}


