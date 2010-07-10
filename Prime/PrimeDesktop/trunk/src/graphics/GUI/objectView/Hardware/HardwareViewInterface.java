/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010  Bahram Malaekeh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package graphics.GUI.objectView.Hardware;


import objects.Hardware;



/**
 * An interface class implemented by all the hardware view panels.
 * 
 * @author Bahram Malaekeh
 */
public interface HardwareViewInterface
{


	/**
	 * Here the actual data is written to the object.
	 * True is returned is no errors were found in the new data.
	 */
	public boolean save();


	/**
	 * This function should be used to validate the information set in the view.
	 */
	public boolean validateNecessaryData();


	/**
	 * Returns the hardware the views is displaying.
	 */
	public Hardware getViewHardware();

}
