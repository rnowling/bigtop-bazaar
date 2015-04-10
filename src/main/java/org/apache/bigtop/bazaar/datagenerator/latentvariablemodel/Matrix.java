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

import java.util.Arrays;

public class Matrix
{
	int rows;
	int columns;
	double[][] matrix;
	
	public Matrix(int rows, int columns, double fillValue)
	{
		this.rows = rows;
		this.columns = columns;
		
		matrix = new double[rows][columns];
		
		for(int i = 0; i < rows; i++)
		{
			Arrays.fill(matrix[i], fillValue);
		}
	}
	
	public void setElement(int row, int column, double value)
	{
		matrix[row][column] = value;
	}
	
	public double getElement(int row, int column)
	{
		return matrix[row][column];
	}
	
	public void copyToRow(int row, Vector vector)
	{
		for(int i = 0; i < vector.getLength(); i++)
		{
			setElement(row, i, vector.getElement(i));
		}
	}
	
	public void copyToColumn(int column, Vector vector)
	{
		for(int i = 0; i < vector.getLength(); i++)
		{
			setElement(i, column, vector.getElement(i));
		}
	}
	
	public Vector getColumn(int column)
	{
		Vector vector = new Vector(rows, 0.0);
		for(int i = 0; i < vector.getLength(); i++)
		{
			vector.setElement(i, getElement(i, column));
		}
		
		return vector;
	}
	
	public int getRows()
	{
		return rows;
	}
	
	public int getColumns()
	{
		return columns;
	}
}
