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

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TestVec2D
{
	static final double EPS = 1e-5;
	
	@Test
	public void testAddition()
	{
		Vec2D vec1 = new Vec2D(3.0, 4.0);
		Vec2D vec2 = new Vec2D(2.0, 6.0);
		
		Vec2D result = vec1.add(vec2);
		
		assertEquals(result.getX(), 5.0, EPS);
		assertEquals(result.getY(), 10.0, EPS);
	}
	
	@Test
	public void testSubtraction()
	{
		Vec2D vec1 = new Vec2D(3.0, 4.0);
		Vec2D vec2 = new Vec2D(2.0, 6.0);
		
		Vec2D result = vec1.sub(vec2);
		
		assertEquals(result.getX(), 1.0, EPS);
		assertEquals(result.getY(), -2.0, EPS);
	}
	
	@Test
	public void testScalarMult()
	{
		Vec2D vec1 = new Vec2D(3.0, -4.0);
		Vec2D result = vec1.scalarMult(2.0);
		
		assertEquals(result.getX(), 6.0, EPS);
		assertEquals(result.getY(), -8.0, EPS);
	}
	
	@Test
	public void testNorm()
	{
		Vec2D vec1 = new Vec2D(3.0, -4.0);
		
		assertEquals(vec1.norm(), Math.sqrt(9.0 + 16.0), EPS);
	}
	
	@Test
	public void testSquaredNorm()
	{
		Vec2D vec1 = new Vec2D(3.0, -4.0);
		
		assertEquals(vec1.squaredNorm(), 9.0 + 16.0, EPS);
	}
	
	@Test
	public void testDistance()
	{
		Vec2D vec1 = new Vec2D(3.0, 4.0);
		Vec2D vec2 = new Vec2D(2.0, 6.0);
		
		double distance = vec1.distance(vec2);
		
		assertEquals(distance, Math.sqrt(5.0), EPS);
	}
}


