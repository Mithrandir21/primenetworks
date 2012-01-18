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
package connections;


import java.util.ArrayList;


/**
 * This class contains only static final fields that represent different
 * hardware and software specific values. These fields are used for validation
 * and verification throughout the system.
 * 
 * @author Bahram Malaekeh
 */
public class ConnectionUtils
{
	public static final String RJ45 = "RJ-45";

	public static final String USB = "USB";

	public static final String Coax = "Coaxial";

	public static final String Fiber = "Fiber";

	public static final String FireWire = "FireWire";

	public static final String Wireless = "Wireless";

	public static final String Wired = "Wired";

	public static final String FullDuplex = "Full Duplex";

	public static final String HalfDuplex = "Half Duplex";

	public static final String PCI = "PCI";

	public static final String Integrated = "Integrated";


	public static final ArrayList<String> networkConnectionTypes = new ArrayList<String>()
	{
		{
			add(RJ45);
			add(Coax);
			add(Fiber);
			add(Wireless);
			add(Wired);
		}
	};

}
