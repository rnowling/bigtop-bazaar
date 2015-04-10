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

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.bigtop.bazaar.datagenerator.configuration.Booth;
import org.apache.bigtop.bazaar.datagenerator.configuration.BoothParameters;
import org.junit.Test;


public class TestBoothGenerator
{
	static final double EPS = 1e-5;
	
	@Test
	public void testCenter()
	{
		BoothParameters params = new BoothParameters();
		params.setColumns(10);
		params.setRows(5);
		params.setRadii(1.0);
		params.setColumnSpacing(2.0);
		params.setRowSpacing(4.0);
		
		BoothGenerator gen = new BoothGenerator(params);
		Vector<Booth> booths = gen.generate();
		
		double xSum = 0.0;
		double ySum = 0.0;
		for(Booth booth : booths)
		{
			xSum += booth.getPositionX();
			ySum += booth.getPositionY();
		}
		
		double xCenter = xSum / booths.size();
		double yCenter = ySum / booths.size();
		
		assertEquals("center x: ", 0.0, xCenter, EPS);
		assertEquals("center y: ", 0.0, yCenter, EPS);
	}
	
	@Test
	public void testBoothCount()
	{
		BoothParameters params = new BoothParameters();
		params.setColumns(10);
		params.setRows(5);
		params.setRadii(1.0);
		params.setColumnSpacing(2.0);
		params.setRowSpacing(4.0);
		
		BoothGenerator gen = new BoothGenerator(params);
		Vector<Booth> booths = gen.generate();
		
		assertEquals("Booth count: ", params.getColumns() * params.getRows(),
				booths.size());
	}
	
	@Test
	public void testRowsColumns()
	{
		BoothParameters params = new BoothParameters();
		params.setColumns(10);
		params.setRows(5);
		params.setRadii(1.0);
		params.setColumnSpacing(2.0);
		params.setRowSpacing(4.0);
		
		BoothGenerator gen = new BoothGenerator(params);
		Vector<Booth> booths = gen.generate();
		
		Map<Double, Integer> rowBooths = new HashMap<Double, Integer>();
		Map<Double, Integer> colBooths = new HashMap<Double, Integer>();
		
		for(Booth booth : booths)
		{
			double posX = booth.getPositionX();
			double posY = booth.getPositionY();
			
			if(! rowBooths.containsKey(posX))
			{
				rowBooths.put(posX, 0);
			}
			
			if(! colBooths.containsKey(posY))
			{
				colBooths.put(posY, 0);
			}
			
			rowBooths.put(posX, rowBooths.get(posX) + 1);
			colBooths.put(posY, colBooths.get(posY) + 1);
		}
		
		// TODO: don't know these are flipped
		for(int count : rowBooths.values())
		{
			assertEquals("Row counts: ", params.getColumns(), count);
		}
		
		for(int count : colBooths.values())
		{
			assertEquals("Column counts: ", params.getRows(), count);
		}
	}
}


