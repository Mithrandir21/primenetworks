/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010 Bahram Malaekeh
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package jUnitTests;


import junit.framework.Assert;
import managment.GroupManagment;

import org.junit.Test;


/**
 * This test class tests different functions in the {@link GroupManagment}
 * class.
 * 
 * @author Bahram Malaekeh
 */
public class GroupsManagmentTest
{
	/**
	 * Test method for
	 * {@link managment.ArrayManagment#addItems(java.lang.String[], java.lang.String[])}
	 * .
	 */
	@Test
	public final void testAddGroupsDataItems()
	{
		String[][] mainGroup = { { "Group1", "Network1" },
				{ "Group2", "Network1" }, { "Group3", "Network1" },
				{ "Group4", "Network1" } };


		String[][] newGroup = { { "GG1", "Net2" }, { "GG2", "Net2" },
				{ "GG3", "Net2" }, { "GG4", "Net2" } };


		String[][] newArray = GroupManagment.addGroupsData(mainGroup, newGroup);


		Assert.assertNotNull(newArray);
		Assert.assertEquals(8, newArray.length);
		Assert.assertEquals(2, newArray[0].length);
		Assert.assertEquals("GG2", newArray[5][0]);



		String[][] newGroup2 = { { "GGS1", "Net3" }, { "GGS2", "Net3" },
				{ "GGS3", "Net3" }, { "GGS4", "Net3" } };


		newArray = GroupManagment.addGroupsData(newArray, newGroup2);

		Assert.assertNotNull(newArray);
		Assert.assertEquals(12, newArray.length);
		Assert.assertEquals(2, newArray[0].length);
		Assert.assertEquals("GGS3", newArray[10][0]);



		String[][] newGroup3 = {};


		newArray = GroupManagment.addGroupsData(newArray, newGroup3);

		Assert.assertNotNull(newArray);
		Assert.assertEquals(12, newArray.length);
		Assert.assertEquals(2, newArray[0].length);
		Assert.assertEquals("GGS3", newArray[10][0]);
	}
}
