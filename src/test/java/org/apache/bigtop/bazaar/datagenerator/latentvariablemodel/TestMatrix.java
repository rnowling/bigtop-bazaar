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

import org.junit.Test;


public class TestMatrix
{
	static final double EPS = 1e-5;
	
	@Test
	public void testMultiplySize()
	{
		Matrix rowMatrix = new Matrix(1, 5, 0.0);
		Matrix colMatrix = new Matrix(5, 1, 0.0);
		
		Matrix result = rowMatrix.multiply(colMatrix);
		final int EXPECTED_ROWS = 1;
		final int EXPECTED_COLUMNS = 1;
		
		assertEquals("rows: ", EXPECTED_ROWS, result.getRows());
		assertEquals("columns: ", EXPECTED_COLUMNS, result.getColumns());
	}
	
	@Test
	public void testMultiplyValues()
	{
		Matrix rowMatrix = new Matrix(2, 5, 0.0);
		Matrix colMatrix = new Matrix(5, 2, 0.0);
		
		for(int i = 0; i < 5; i++)
		{
			rowMatrix.setElement(0, i, 1.0);
			rowMatrix.setElement(1, i, 3.0);
			colMatrix.setElement(i, 0, 5.0);
			colMatrix.setElement(i, 1, 7.0);
		}
		
		
		Matrix result = rowMatrix.multiply(colMatrix);
		
		assertEquals("(0, 0): ", 25.0, result.getElement(0, 0), EPS);
		assertEquals("(0, 1): ", 35.0, result.getElement(0, 1), EPS);
		assertEquals("(1, 0): ", 75.0, result.getElement(1, 0), EPS);
		assertEquals("(1, 1): ", 105.0, result.getElement(1, 1), EPS);
	}
	
	@Test
	public void testTranspose()
	{
		Matrix matrix = new Matrix(2, 3, 0.0);
		matrix.setElement(0, 0, 0.0);
		matrix.setElement(0, 1, 1.0);
		matrix.setElement(0, 2, 2.0);
		matrix.setElement(1, 0, 3.0);
		matrix.setElement(1, 1, 4.0);
		matrix.setElement(1, 2, 5.0);
		
		Matrix transpose = matrix.transpose();
		
		assertEquals("matrix.rows = matrix.T.columns: ", matrix.getRows(), transpose.getColumns());
		assertEquals("matrix.columns = matrix.T.rows: ", matrix.getColumns(), transpose.getRows());
	}
}


