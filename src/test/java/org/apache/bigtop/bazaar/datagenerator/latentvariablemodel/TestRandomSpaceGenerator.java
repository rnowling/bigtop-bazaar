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
package org.apache.bigtop.bazaar.datagenerator.latentvariablemodel;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.apache.bigtop.bazaar.datagenerator.base.Matrix;
import org.apache.bigtop.bazaar.datagenerator.base.Vec;
import org.junit.Test;


public class TestRandomSpaceGenerator
{
	static final double EPS = 1e-5;
	
	@Test
	public void testNorms()
	{
		Random rng = new Random();
		Sampler<Double> sampler = new BoundedBiGaussianMixtureSampler(0.0, 1.0, 0.25, 0.75, 0.1, 0.9, 0.2, rng);
		RandomSpaceGenerator generator = new RandomSpaceGenerator(sampler);
		
		final int rows = 10;
		final int columns = 5;
		final double EXPECTED_NORM = 1.0;
		
		Matrix randomMatrix = generator.generate(rows, columns);
		
		for(int c = 0; c < columns; c++)
		{
			Vec col = randomMatrix.getColumn(c);
			double norm = col.norm();
			assertEquals("norms: ", 
						EXPECTED_NORM, norm, EPS);
		}
	}
	
	@Test
	public void testOrthogonal()
	{
		Random rng = new Random();
		Sampler<Double> sampler = new BoundedBiGaussianMixtureSampler(0.0, 1.0, 0.25, 0.75, 0.1, 0.9, 0.2, rng);
		RandomSpaceGenerator generator = new RandomSpaceGenerator(sampler);
		
		final int rows = 10;
		final int columns = 5;
		final double EXPECTED_DOT_SAME = 1.0;
		final double EXPECTED_DOT_ORTH = 0.0;
		
		Matrix randomMatrix = generator.generate(rows, columns);
		
		for(int c1 = 0; c1 < columns; c1++)
		{
			Vec col1 = randomMatrix.getColumn(c1);
			for(int c2 = 0; c2 < columns; c2++)
			{
				Vec col2 = randomMatrix.getColumn(c2);
				
				double dot = col1.dot(col2);
				
				double expected = 0;
				if(c1 == c2)
				{
					expected = EXPECTED_DOT_SAME;
				}
				else
				{
					expected = EXPECTED_DOT_ORTH;
				}
				
				assertEquals(col1.equals(col2) + " " + (c1 == c2) + " dot product: ", 
							expected, dot, EPS);
			}
		}
	}
}


