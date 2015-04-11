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
package org.apache.bigtop.bazaar.datagenerator.base;

import static org.junit.Assert.assertEquals;

import org.apache.bigtop.bazaar.datagenerator.base.Vec;
import org.junit.Test;


public class TestVec
{
	static final double EPS = 1e-5;
	
	@Test
	public void testNorm()
	{		
		final int rows = 10;
		final double EXPECTED_NORM = Math.sqrt(rows * 1.0);
		
		Vec vec = new Vec(rows, 1.0);
		
		assertEquals("norm: ", EXPECTED_NORM, vec.norm(), EPS);
	}
	
	@Test
	public void testNormalize()
	{		
		final int rows = 10;
		final double EXPECTED_NORM = 1.0;
		
		Vec vec = new Vec(rows, 1.0).normalize();
		
		assertEquals("norm: ", EXPECTED_NORM, vec.norm(), EPS);
	}
	
	@Test
	public void testScalarMult()
	{
		final int rows = 10;
		final double EXPECTED_VALUE = 2.0;
		
		Vec vec = new Vec(rows, 1.0).scalarMult(2.0);
		
		for(int i = 0; i < vec.getLength(); i++)
		{
			assertEquals("element: ", EXPECTED_VALUE, vec.getElement(i), EPS);
		}
	}
	
	@Test
	public void testDot()
	{
		final int rows = 3;
		final double EXPECTED_ORTHOG_DOT = 0.0;
		final double EXPECTED_SAME_DOT = 1.0;
		
		Vec vec = new Vec(rows, 0.0);
		vec.setElement(0, 1.0);
		vec.setElement(2, 1.0);
		
		Vec orthog = new Vec(rows, 0.0);
		vec.setElement(1, 1.0);
	
		double orthogDot = vec.dot(orthog);
		assertEquals("orthog dot: ", EXPECTED_ORTHOG_DOT, orthogDot, EPS);
		
		Vec normVec = vec.normalize();
		double sameDot = normVec.dot(normVec);
		assertEquals("same dot: ", EXPECTED_SAME_DOT, sameDot, EPS);
		
	}
}


