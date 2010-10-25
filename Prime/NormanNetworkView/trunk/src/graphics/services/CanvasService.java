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
package graphics.services;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class CanvasService implements Runnable
{
	/**
	 * The thread instance that will be run by the JVM.
	 */
	private volatile Thread service;



	/**
	 * The constructor that when called will create and start a new tread.
	 */
	public CanvasService()
	{
		service = new Thread(this);
		service.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		Thread thisThread = Thread.currentThread();

		while ( service == thisThread )
		{
			try
			{
				System.out.println("Testing" + System.currentTimeMillis());

				// Makes the thread wait for a bit
				Thread.sleep(1000);
			}
			catch ( InterruptedException e )
			{

			}
		}
	}



	/**
	 * A resume function that will start a new tread and run it(just as the
	 * constructor). This function is necessary
	 * because you might want to a variables to this class and might not want to
	 * create a new instance of the entire
	 * class to be able to start the thread again.
	 */
	public synchronized void serviceResume()
	{
		service = new Thread(this);
		service.start();
	}


	/**
	 * This function sets the thread to null, which will get the JVM garbage
	 * collector to remove the entire thread.
	 */
	public synchronized void serviceSuspend()
	{
		service = null;
	}

}
