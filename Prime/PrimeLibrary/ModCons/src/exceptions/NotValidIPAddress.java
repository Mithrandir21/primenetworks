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
package exceptions;


/**
 * This exception is thrown when a given string is not a valid IP address.
 * 
 * @author Bahram Malaekeh
 * @version 1.0
 */
public class NotValidIPAddress extends Exception
{
	// The text that is not a valid IP address
	String text;



	/**
	 * A constructor that set the given string as the exceptions field of text. The given String is the invalid IP
	 * address string.
	 * 
	 * @param IPtext
	 *            The invalid IP address string.
	 */
	public NotValidIPAddress(String IPtext)
	{
		this.text = IPtext;
	}




	/*
	 * (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage()
	{
		String output = "This, " + this.text + ", is not a valid IP address.";

		return output;
	}

}
