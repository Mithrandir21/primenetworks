/**
 * 
 */
package graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuCanvas;


import graphics.GraphicalFunctions;
import graphics.ImageLocator;
import graphics.PrimeMain1;
import graphics.GUI.CreateObjects;
import graphics.GUI.workareaCanvas.providers.ActionsAdder;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import objects.clientObjects.Desktop;
import objects.clientObjects.Laptop;
import objects.clientObjects.ThinClient;
import objects.infrastructureObjects.Hub;
import objects.infrastructureObjects.Internet;
import objects.infrastructureObjects.Router;
import objects.infrastructureObjects.Switch;
import objects.peripheralObjects.Fax;
import objects.peripheralObjects.MultifunctionPrinter;
import objects.peripheralObjects.NetworkMultifunctionPrinter;
import objects.peripheralObjects.NetworkPrinter;
import objects.peripheralObjects.Printer;
import objects.peripheralObjects.Scanner;
import objects.serverObjects.BackupServer;
import objects.serverObjects.DatabaseServer;
import objects.serverObjects.FirewallServer;
import objects.serverObjects.HTTPServer;
import objects.serverObjects.MailServer;
import objects.serverObjects.PrinterServer;
import objects.serverObjects.ProxyServer;
import widgets.WidgetIcon;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;
import actions.ActionPaste;


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
				objectIcon = ImageLocator.getImageIconObject("Desktop");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_Laptop_Item") )
			{
				objectType = Laptop.class;
				objectIcon = ImageLocator.getImageIconObject("Laptop");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_ThinClient_Item") )
			{
				objectType = ThinClient.class;
				objectIcon = ImageLocator.getImageIconObject("Screen");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_HTTPServer_Item") )
			{
				objectType = HTTPServer.class;
				objectIcon = ImageLocator.getImageIconObject("Web-server");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_BackupServer_Item") )
			{
				objectType = BackupServer.class;
				objectIcon = ImageLocator.getImageIconObject("Data-server");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_DatabaseServer_Item") )
			{
				objectType = DatabaseServer.class;
				objectIcon = ImageLocator.getImageIconObject("Database-server");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_MailServer_Item") )
			{
				objectType = MailServer.class;
				objectIcon = ImageLocator.getImageIconObject("Email-server");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_FirewallServer_Item") )
			{
				objectType = FirewallServer.class;
				objectIcon = ImageLocator.getImageIconObject("Firewall-server");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_ProxyServer_Item") )
			{
				objectType = ProxyServer.class;
				objectIcon = ImageLocator.getImageIconObject("Proxy-server");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_PrinterServer_Item") )
			{
				objectType = PrinterServer.class;
				objectIcon = ImageLocator.getImageIconObject("Printer-server");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_Hub_Item") )
			{
				objectType = Hub.class;
				objectIcon = ImageLocator.getImageIconObject("Hub");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_Switch_Item") )
			{
				objectType = Switch.class;
				objectIcon = ImageLocator.getImageIconObject("Switch");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_Router_Item") )
			{
				objectType = Router.class;
				objectIcon = ImageLocator.getImageIconObject("Router");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_WirelessRouter_Item") )
			{
				objectType = Router.class;
				objectIcon = ImageLocator.getImageIconObject("WirelessRouter");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_Internet_Item") )
			{
				objectType = Internet.class;
				objectIcon = ImageLocator.getImageIconObject("Internet");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_Scanner_Item") )
			{
				objectType = Scanner.class;
				objectIcon = ImageLocator.getImageIconObject("Scanner");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_Printer_Item") )
			{
				objectType = Printer.class;
				objectIcon = ImageLocator.getImageIconObject("Printer");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_Fax_Item") )
			{
				objectType = Fax.class;
				objectIcon = ImageLocator.getImageIconObject("Fax");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_MFP_Item") )
			{
				objectType = MultifunctionPrinter.class;
				objectIcon = ImageLocator
						.getImageIconObject("MultifunctionPrinter");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_NetworkPrinter_Item") )
			{
				objectType = NetworkPrinter.class;
				objectIcon = ImageLocator.getImageIconObject("NetworkPrinter");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_NetworkMFP_Item") )
			{
				objectType = NetworkMultifunctionPrinter.class;
				objectIcon = ImageLocator
						.getImageIconObject("NetworkMultifunctionPrinter");

				set = true;
			}




			if ( set == true )
			{
				WidgetIcon newObjectIcon = new WidgetIcon(objectIcon,
						objectType);

				// Sets up the WidgetIcon
				GraphicalFunctions.widgetIconSetup(newObjectIcon);


				// Creates a new Object that will be added to the WidgetObject
				newObject = new CreateObjects().CreateObject(newObjectIcon,
						canvas.getNumberOfWidgetsOnTheScene());

				// Creates a new WidgetObject that will be added to the scene
				newWidgetObject = new WidgetObject(canvas.getScene(),
						newObject, objectIcon.getImage());

				// Adds the given object to the given location
				canvas.addWidgetObject(newWidgetObject, location, true);

				// Adds the actions that the new widget supports
				ActionsAdder.makeWidgetObjectReady(canvas, newWidgetObject);

				// Updates the sidebar with the object properties
				PrimeMain1.updatePropertiesObjectArea(newObject, false);
			}


			// Wants to paste a new WidgetObject on the canvas
			if ( actionName.equals("PasteObject") )
			{
				JMenuItem pasteActionMenu = (JMenuItem) e.getSource();
				ActionPaste pasteAction = (ActionPaste) pasteActionMenu
						.getAction();
				pasteAction.performAction();


				// // The user wants to paste a new WidgetObject, but not
				// replace
				// // the current WidgetObject
				// WidgetObject copyFrom = null;
				//
				//
				// // Either the cut or copy pointers will be used
				// if ( PrimeMain1.copyWidget != null )
				// {
				// copyFrom = PrimeMain1.copyWidget;
				// }
				// else
				// {
				// assert PrimeMain1.cutWidget != null;
				//
				// copyFrom = PrimeMain1.cutWidget;
				// }
				//
				//
				// // The location of the new Widget
				// Point newLocation = new Point(location);
				//
				// // Creates a deep copy of the object within the classes
				// Widget
				// newObject = ComponentsManagment.deepObjectCopy(copyFrom
				// .getObject());
				//
				// // Creates a new WidgetObject
				// WidgetObject newWidget = new WidgetObject(canvas.getScene(),
				// newObject, copyFrom.getImage());
				//
				// // Sets the location of the object
				// newWidget.getObject().setLocation(newLocation);
				//
				// // Adds the newly created WidgetObject to the classes canvas
				// canvas.addWidgetObject(newWidget, newLocation, true);
				//
				// // Adds the clicking actions to the Widget on the scene
				// ActionsAdder.makeWidgetObjectReady(canvas, newWidget);
				//
				//
				// // When the paste function is finished, the cut and copy
				// should
				// // be reset to null. If the Cut object is the one used, that
				// // object will be removed from the canvas
				// if ( PrimeMain1.copyWidget != null )
				// {
				// PrimeMain1.copyWidget = null;
				// }
				// else
				// {
				// // Assures that the pointer is not null
				// assert PrimeMain1.cutWidget != null;
				//
				// // Removes the object from the canvas
				// WorkareaCanvasActions.deleteObject(CanvasManagment
				// .findCanvas(PrimeMain1.cutWidget.getScene(),
				// PrimeMain1.canvases), PrimeMain1.cutWidget);
				//
				// PrimeMain1.cutWidget = null;
				// }
			}

		}

		canvas.cleanUp();
	}
}
