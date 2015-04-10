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
package org.apache.bigtop.bazaar.datagenerator.configuration;


public class BoothParameters
{
	int rows;
	int columns;
	double rowSpacing;
	double columnSpacing;
	double radii;
	double strength;

	public int getRows()
	{
		return rows;
	}

	public void setRows(int rows)
	{
		this.rows = rows;
	}

	public int getColumns()
	{
		return columns;
	}

	public void setColumns(int columns)
	{
		this.columns = columns;
	}

	public double getRowSpacing()
	{
		return rowSpacing;
	}

	public void setRowSpacing(double rowSpacing)
	{
		this.rowSpacing = rowSpacing;
	}

	public double getColumnSpacing()
	{
		return columnSpacing;
	}

	public void setColumnSpacing(double columnSpacing)
	{
		this.columnSpacing = columnSpacing;
	}

	public double getRadii()
	{
		return radii;
	}

	public void setRadii(double radii)
	{
		this.radii = radii;
	}

	public double getStrength()
	{
		return strength;
	}

	public void setStrength(double strength)
	{
		this.strength = strength;
	}
	
	public String toString()
	{
		return "Rows: " + rows + "\n" +
				"Columns: " + columns + "\n" +
				"Row spacing: " + rowSpacing + "\n" +
				"Column spacing: " + columnSpacing + "\n" +
				"Radii: " + radii + "\n" +
				"Strength: " + strength + "\n";
	}
}
