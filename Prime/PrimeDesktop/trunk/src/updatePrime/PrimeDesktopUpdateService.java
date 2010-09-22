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
package updatePrime;


import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;


/**
 * This class contains method that help the system determine if the current
 * version of the application is the latest.
 * 
 * This is mainly done by checking the content of a text file on a server.
 * 
 * @author Bahram Malaekeh
 * 
 */
public class PrimeDesktopUpdateService
{
	/**
	 * This function runs the comparison between the local and remote versions.
	 * If the local version is older then the remote version, a call is made to
	 * UpdateDialog.
	 * 
	 * @see UpdateDialog
	 */
	public PrimeDesktopUpdateService()
	{
		String newestVersion = getNewestVersion();

		if ( newestVersion != null )
		{
			// if ( !newestVersion.equalsIgnoreCase(Settings.appVersion) )
			// {
			new UpdateDialog();
			// }
		}
	}





	/**
	 * This method returns a string that gives the current version contained
	 * inside a remote text file, which will represent the newest version of the
	 * application.
	 */
	private String getNewestVersion()
	{
		try
		{
			// The URL to the file on the server containing the newest version
			// text
			URL url = new URL(
					"http://primedesktop.freehostia.com/primeDesktopVersion.txt");
			URLConnection uconn = url.openConnection();
			HttpURLConnection conn = (HttpURLConnection) uconn;
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			conn.setInstanceFollowRedirects(true);
			conn.connect();

			// Gets the connections errors, if any
			java.io.InputStream errorStream = conn.getErrorStream();
			// If there are not any errors.
			if ( errorStream == null )
			{
				// Gets the content, ie the page
				Object content = conn.getContent();
				InputStream stream = (InputStream) content;

				StringBuilder sb = new StringBuilder();


				try
				{
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(stream, "UTF-8"));
					String line = reader.readLine();
					while ( line != null )
					{
						sb.append(line);
						line = reader.readLine();
					}
				}
				finally
				{
					stream.close();
				}

				return sb.toString();
			}
		}
		catch ( UnknownHostException e )
		{
			e.printStackTrace();
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}


		return null;
	}



	/**
	 * This function attempts to open the applications download page in the
	 * systems default web browser.
	 */
	public static void openUpdateURL()
	{
		if ( Desktop.isDesktopSupported() )
		{
			// System.err.println("Desktop is not supported (fatal)");
		}

		Desktop desktop = Desktop.getDesktop();

		if ( !desktop.isSupported(Desktop.Action.BROWSE) )
		{
			System.err
					.println("Desktop doesn't support the browse action (fatal)");
		}

		try
		{
			URL url = new URL("http://sourceforge.net/projects/prime/");
			URI uri = url.toURI();
			desktop.browse(uri);
		}
		catch ( Exception e )
		{
			System.err.println(e.getMessage());
		}
	}
}
