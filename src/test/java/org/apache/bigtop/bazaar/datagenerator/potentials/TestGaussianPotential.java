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

import org.apache.bigtop.bazaar.datagenerator.base.Vec2D;
import org.apache.bigtop.bazaar.datagenerator.potentials.GaussianPotential;
import org.junit.Test;


public class TestGaussianPotential
{
	static final double EPS = 1e-5;
	
	@Test
	public void testScalePE()
	{
		final double radius = 3.0;
		final double strength = 2.0;
		final Vec2D center = new Vec2D(0.0, 0.0);
		
		final double expectedPE = -1.0 * strength;
		
		GaussianPotential potential = new GaussianPotential(center, radius, strength);
		potential.update(new Vec2D[]{center});
		final double observedPE = potential.getEnergy();
		
		double percentError = (expectedPE - observedPE) / expectedPE;
		assertEquals("Observed PE: " + observedPE + ", expected PE: " + expectedPE + ", percent error: " + percentError,
				0.0, percentError, EPS);
	}
}


