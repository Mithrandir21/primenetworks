/**
 * 
 */
package graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuCanvas;

import graphics.ImageLocator;
import graphics.WidgetIcon;
import graphics.GUI.selectArea.CreateObjectDragged;
import graphics.GUI.workareaCanvas.WorkareaCanvas;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import objects.clientObjects.Desktop;
import objects.clientObjects.Laptop;
import objects.infrastructureObjects.Hub;
import objects.infrastructureObjects.Router;
import objects.infrastructureObjects.Switch;
import objects.peripheralObjects.Scanner;
import objects.serverObjects.BackupServer;
import objects.serverObjects.FirewallServer;
import objects.serverObjects.HTTPServer;
import objects.serverObjects.MailServer;
import objects.serverObjects.ProxyServer;

import widgetManipulation.WidgetObject;
import actions.graphicalActions.WorkareaCanvasActions;

/**
 * Javadoc-TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
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
	 * @param canvas The WorkareaCanvas that the menu will be shown on.
	 * @param newObjPoint The location(Point) that the menu will be shown at.
	 */
	public WorkareaCanvasActionListener(WorkareaCanvas canvas, Point newObjPoint)
	{
		this.canvas = canvas;
		location = newObjPoint;
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
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
			else if ( actionName.equals("CreateNewST_HTTPServer_Item") )
			{
				objectType = HTTPServer.class;
				objectIcon = ImageLocator.getImageIconObject("Web-server");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_MailServer_Item") )
			{
				objectType = MailServer.class;
				objectIcon = ImageLocator.getImageIconObject("Email-server");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_BackupServer_Item") )
			{
				objectType = BackupServer.class;
				objectIcon = ImageLocator.getImageIconObject("Data-server");

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
			else if ( actionName.equals("CreateNewST_Scanner_Item") )
			{
				objectType = Scanner.class;
				objectIcon = ImageLocator.getImageIconObject("Scanner");

				set = true;
			}
			
			
			
			if ( set == true )
			{
				WidgetIcon newObjectIcon = new WidgetIcon(objectIcon,
						objectType);


				// Creates a new Object that will be added to the WidgetObject
				newObject = new CreateObjectDragged().CreateObject(
						newObjectIcon, canvas.getNumberOfWidgetsOnTheScene());

				// Creates a new WidgetObject that will be added to the scene
				newWidgetObject = new WidgetObject(canvas.getScene(), newObject, objectIcon
						.getImage());

				// Adds the given object to the given location
				canvas.addWidgetObject(newWidgetObject, location, true);
			}

		}

		canvas.cleanUp();
	}
}
