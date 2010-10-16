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
package graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuCanvas;


import graphics.GraphicalFunctions;
import graphics.PrimeMain;
import graphics.GUI.workareaCanvas.providers.ActionsAdder;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import managment.CreateObjects;
import managment.DesktopCanvasManagment;
import objects.clientObjects.Desktop;
import objects.clientObjects.Laptop;
import objects.clientObjects.ThinClient;
import objects.infrastructureObjects.Hub;
import objects.infrastructureObjects.Internet;
import objects.infrastructureObjects.Modem;
import objects.infrastructureObjects.Router;
import objects.infrastructureObjects.Switch;
import objects.peripheralObjects.ExternalHDD;
import objects.peripheralObjects.Fax;
import objects.peripheralObjects.MultifunctionPrinter;
import objects.peripheralObjects.NetworkMultifunctionPrinter;
import objects.peripheralObjects.NetworkPrinter;
import objects.peripheralObjects.Printer;
import objects.peripheralObjects.Scanner;
import objects.serverObjects.AntivirusServer;
import objects.serverObjects.BackupServer;
import objects.serverObjects.DatabaseServer;
import objects.serverObjects.FirewallServer;
import objects.serverObjects.GenericServer;
import objects.serverObjects.HTTPServer;
import objects.serverObjects.MailServer;
import objects.serverObjects.NASServer;
import objects.serverObjects.PrinterServer;
import objects.serverObjects.ProxyServer;
import objects.serverObjects.VirtualizationServer;
import widgets.WidgetIcon;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;
import actions.canvasActions.ActionAddWidgetToWorkareaCanvas;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class WorkareaCanvasActionListener implements ActionListener
{
	/**
	 * The canvas that the menu will be shown on.
	 */
	private WorkareaCanvas canvas;


	/**
	 * The location where the user has right clicked.
	 */
	private Point location;


	/**
	 * A constructor for this class.
	 * 
	 * @param canvas
	 *            The WorkareaCanvas that the menu will be shown on.
	 * @param newObjPoint
	 *            The location(Point) that the menu will be shown at.
	 */
	public WorkareaCanvasActionListener(WorkareaCanvas canvas, Point newObjPoint)
	{
		this.canvas = canvas;
		location = newObjPoint;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		JMenuItem action = (JMenuItem) e.getSource();

		String actionName = "";


		if ( action.getActionCommand() != null )
		{
			actionName = action.getActionCommand();
		}


		if ( !actionName.equals("") )
		{

			boolean set = false;
			Class<?> objectType = null;
			ImageIcon objectIcon = null;
			objects.Object newObject = null;
			WidgetObject newWidgetObject = null;

			if ( actionName.equals("CreateNewST_Desktop_Item") )
			{
				objectType = Desktop.class;
				objectIcon = PrimeMain.objectImageIcons.get(objectType);

				set = true;
			}
			else if ( actionName.equals("CreateNewST_Laptop_Item") )
			{
				objectType = Laptop.class;
				objectIcon = PrimeMain.objectImageIcons.get(objectType);

				set = true;
			}
			else if ( actionName.equals("CreateNewST_ThinClient_Item") )
			{
				objectType = ThinClient.class;
				objectIcon = PrimeMain.objectImageIcons.get(objectType);

				set = true;
			}
			else if ( actionName.equals("CreateNewST_GeneralServer_Item") )
			{
				objectType = GenericServer.class;
				objectIcon = PrimeMain.objectImageIcons.get(objectType);

				set = true;
			}
			else if ( actionName.equals("CreateNewST_AntivirusServer_Item") )
			{
				objectType = AntivirusServer.class;
				objectIcon = PrimeMain.objectImageIcons.get(objectType);

				set = true;
			}
			else if ( actionName.equals("CreateNewST_HTTPServer_Item") )
			{
				objectType = HTTPServer.class;
				objectIcon = PrimeMain.objectImageIcons.get(objectType);

				set = true;
			}
			else if ( actionName.equals("CreateNewST_BackupServer_Item") )
			{
				objectType = BackupServer.class;
				objectIcon = PrimeMain.objectImageIcons.get(objectType);

				set = true;
			}
			else if ( actionName.equals("CreateNewST_NASServer_Item") )
			{
				objectType = NASServer.class;
				objectIcon = PrimeMain.objectImageIcons.get(objectType);

				set = true;
			}
			else if ( actionName.equals("CreateNewST_DatabaseServer_Item") )
			{
				objectType = DatabaseServer.class;
				objectIcon = PrimeMain.objectImageIcons.get(objectType);

				set = true;
			}
			else if ( actionName
					.equals("CreateNewST_VirtualizationServer_Item") )
			{
				objectType = VirtualizationServer.class;
				objectIcon = PrimeMain.objectImageIcons.get(objectType);

				set = true;
			}
			else if ( actionName.equals("CreateNewST_MailServer_Item") )
			{
				objectType = MailServer.class;
				objectIcon = PrimeMain.objectImageIcons.get(objectType);

				set = true;
			}
			else if ( actionName.equals("CreateNewST_FirewallServer_Item") )
			{
				objectType = FirewallServer.class;
				objectIcon = PrimeMain.objectImageIcons.get(objectType);

				set = true;
			}
			else if ( actionName.equals("CreateNewST_ProxyServer_Item") )
			{
				objectType = ProxyServer.class;
				objectIcon = PrimeMain.objectImageIcons.get(objectType);

				set = true;
			}
			else if ( actionName.equals("CreateNewST_PrinterServer_Item") )
			{
				objectType = PrinterServer.class;
				objectIcon = PrimeMain.objectImageIcons.get(objectType);

				set = true;
			}
			else if ( actionName.equals("CreateNewST_Hub_Item") )
			{
				objectType = Hub.class;
				objectIcon = PrimeMain.objectImageIcons.get(objectType);

				set = true;
			}
			else if ( actionName.equals("CreateNewST_Switch_Item") )
			{
				objectType = Switch.class;
				objectIcon = PrimeMain.objectImageIcons.get(objectType);

				set = true;
			}
			else if ( actionName.equals("CreateNewST_Router_Item") )
			{
				objectType = Router.class;
				objectIcon = PrimeMain.objectImageIcons.get(objectType);

				set = true;
			}
			else if ( actionName.equals("CreateNewST_Modem_Item") )
			{
				objectType = Modem.class;
				objectIcon = PrimeMain.objectImageIcons.get(objectType);

				set = true;
			}
			else if ( actionName.equals("CreateNewST_WirelessRouter_Item") )
			{
				objectType = Router.class;
				objectIcon = PrimeMain.objectImageIcons.get(objectType);

				set = true;
			}
			else if ( actionName.equals("CreateNewST_Internet_Item") )
			{
				objectType = Internet.class;
				objectIcon = PrimeMain.objectImageIcons.get(objectType);

				set = true;
			}
			else if ( actionName.equals("CreateNewST_NAS_Item") )
			{
				objectType = ExternalHDD.class;
				objectIcon = PrimeMain.objectImageIcons.get(objectType);

				set = true;
			}
			else if ( actionName.equals("CreateNewST_Scanner_Item") )
			{
				objectType = Scanner.class;
				objectIcon = PrimeMain.objectImageIcons.get(objectType);

				set = true;
			}
			else if ( actionName.equals("CreateNewST_Printer_Item") )
			{
				objectType = Printer.class;
				objectIcon = PrimeMain.objectImageIcons.get(objectType);

				set = true;
			}
			else if ( actionName.equals("CreateNewST_Fax_Item") )
			{
				objectType = Fax.class;
				objectIcon = PrimeMain.objectImageIcons.get(objectType);

				set = true;
			}
			else if ( actionName.equals("CreateNewST_MFP_Item") )
			{
				objectType = MultifunctionPrinter.class;
				objectIcon = PrimeMain.objectImageIcons.get(objectType);

				set = true;
			}
			else if ( actionName.equals("CreateNewST_NetworkPrinter_Item") )
			{
				objectType = NetworkPrinter.class;
				objectIcon = PrimeMain.objectImageIcons.get(objectType);

				set = true;
			}
			else if ( actionName.equals("CreateNewST_NetworkMFP_Item") )
			{
				objectType = NetworkMultifunctionPrinter.class;
				objectIcon = PrimeMain.objectImageIcons.get(objectType);

				set = true;
			}




			if ( set )
			{
				WidgetIcon newObjectIcon = new WidgetIcon(objectIcon,
						objectType);

				// Sets up the WidgetIcon
				GraphicalFunctions.widgetIconSetup(newObjectIcon, objectIcon);


				// Creates a new Object that will be added to the WidgetObject
				newObject = CreateObjects.CreateObject(newObjectIcon,
						canvas.getNumberOfWidgetsOnTheScene());

				// Creates a new WidgetObject that will be added to the scene
				newWidgetObject = new WidgetObject(canvas.getScene(),
						newObject, objectIcon.getImage());

				// // Adds the given object to the given location
				ActionAddWidgetToWorkareaCanvas actionAdd = new ActionAddWidgetToWorkareaCanvas(
						PrimeMain.texts
								.getString("actionAddWidgetToCanvasDescriptionText"),
						canvas, newWidgetObject, location);
				actionAdd.performAction(true);

				// Adds the actions that the new widget supports
				ActionsAdder.makeWidgetObjectReady(canvas, newWidgetObject);

				// Updates the sidebar with the object properties
				PrimeMain.updatePropertiesObjectArea(newObject, false);
			}
		}

		DesktopCanvasManagment.canvasCleanUp(canvas);
	}
}
