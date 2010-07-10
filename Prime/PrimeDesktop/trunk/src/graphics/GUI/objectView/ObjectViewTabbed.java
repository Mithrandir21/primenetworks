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
package graphics.GUI.objectView;


import graphics.PrimeMain;
import graphics.GUI.objectView.Connections.NetworkConnectionsView;
import graphics.GUI.objectView.General.GeneralObjectView;
import graphics.GUI.objectView.Hardware.HardwareView.Overview.HardwareObjectView;
import graphics.GUI.objectView.Network.NetworkView;
import graphics.GUI.objectView.Software.EditSoftware.EditOverview.SoftwareObjectView;

import javax.swing.JTabbedPane;

import widgets.WidgetObject;


/**
 * The JTabbedPane class that will contain all the different tabs showing all
 * object information.
 * 
 * @author Bahram Malaekeh
 */
public class ObjectViewTabbed extends JTabbedPane
{
	public GeneralObjectView genObjView;

	public HardwareObjectView hardObjView;

	public SoftwareObjectView softObjView;

	public NetworkConnectionsView conObjView;

	public NetworkView netObjView;


	/**
	 * Constructor that creates all the different tabs that show different
	 * information about the given object. This includes general, hardware,
	 * software and network information.
	 * 
	 * @param obj
	 *            The object that will be examined for information
	 */
	public ObjectViewTabbed(WidgetObject obj)
	{
		genObjView = new GeneralObjectView(obj.getObject());
		this.addTab(PrimeMain.texts.getString("generalTabLabel"), null,
				genObjView, PrimeMain.texts.getString("generalTabDescription"));


		hardObjView = new HardwareObjectView(obj.getObject());
		this.addTab(PrimeMain.texts.getString("hardwareTabLabel"), null,
				hardObjView, PrimeMain.texts
						.getString("hardwareTabDescription"));


		softObjView = new SoftwareObjectView(obj.getObject());
		this.addTab(PrimeMain.texts.getString("softwareTabLabel"), null,
				softObjView, PrimeMain.texts
						.getString("softwareTabDescription"));


		conObjView = new NetworkConnectionsView(obj.getObject());
		this.addTab(PrimeMain.texts.getString("connectionTabLabel"), null,
				conObjView, PrimeMain.texts
						.getString("connectionTabDescription"));


		netObjView = new NetworkView(obj);
		this.addTab(PrimeMain.texts.getString("networkTabLabel"), null,
				netObjView, PrimeMain.texts.getString("networkTabDescription"));
	}


	/**
	 * Gets the hardware editor that where hardware can be edited.
	 */
	public HardwareObjectView getHardwareEditor()
	{
		return hardObjView;
	}


	/**
	 * Gets the software editor that where software can be edited.
	 */
	public SoftwareObjectView getSoftwareEditor()
	{
		return softObjView;
	}







	/**
	 * Calls the update functions in the classes views to update the information
	 * it contains about the classes given object. This includes both hardware
	 * and software views.
	 */
	public void updateTabInfo()
	{
		hardObjView.updateTabInfo();
		softObjView.updateTabInfo();
	}
}
