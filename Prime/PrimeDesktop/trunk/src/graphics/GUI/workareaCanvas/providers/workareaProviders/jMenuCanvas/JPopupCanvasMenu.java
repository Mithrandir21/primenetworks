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



import graphics.PrimeMain;

import java.awt.Point;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import widgets.WorkareaCanvas;
import actions.canvasActions.ActionDeleteAllConnections;
import actions.canvasActions.ActionDeleteAllRooms;
import actions.canvasActions.ActionDeleteAllWidgets;
import actions.canvasActions.ActionDeleteEverything;
import actions.canvasActions.ActionOpenNetworkRules;
import actions.canvasActions.ActionResetZoom;
import actions.canvasActions.ActionZoomIn;
import actions.canvasActions.ActionZoomOut;
import actions.systemActions.ActionPaste;


/**
 * The class that provides a JPopupMenu to be shown on any given canvas. The
 * methods in the class both creates and returns the menus.
 * 
 * @author Bahram Malaekeh
 */
public class JPopupCanvasMenu
{
	/**
	 * The actual JPopup menu that will contain the menus.
	 */
	private JPopupMenu popup = new JPopupMenu();


	/**
	 * The class that will listen for actions and then perform the correct
	 * action.
	 */
	private WorkareaCanvasActionListener canvasActListener;


	/**
	 * The canvas which the action will be performed on.
	 */
	private WorkareaCanvas canvas;


	/**
	 * Constructor for the class that takes a WorkareaCanvas as a parameter.
	 * 
	 * @param canvas
	 */
	public JPopupCanvasMenu(WorkareaCanvas canvas)
	{
		this.canvas = canvas;
	}



	/**
	 * The function calls the menu creator and returns the finished JPopupMenu.
	 * 
	 * @param localLocation
	 *            The location(Point) on the WorkareaCanvas that the menu will
	 *            be shown at
	 * @return The JPopupMenu that will be shown when a user right clicks on an
	 *         empty part of a WorkareaCanvas.
	 */
	public JPopupMenu createPopupMenu(Point localLocation)
	{
		// The action listener that will determine what actions from the
		// JPopupMenu
		// the user wants to perform.
		canvasActListener = new WorkareaCanvasActionListener(canvas,
				localLocation);

		// -------------------------------
		JMenuItem menuItem = new JMenuItem(new ActionPaste(
				PrimeMain.texts.getString("canvasMenuPasteWidgetAction")));

		// If neither the copy or cut pointers point to anything, the past
		// button will be disabled
		if ( PrimeMain.copyWidget == null && PrimeMain.cutWidget == null )
		{
			menuItem.setEnabled(false);
		}
		popup.add(menuItem);
		// --------------------------------

		popup.addSeparator();

		// Adds the "add objects" menu
		ObjectCreationMenues();

		createZoomMenu();

		popup.addSeparator();

		// Adds the "remove" menu
		createRemoveMenu();


		// -------------------------------
		JMenuItem openNetworkRules = new JMenuItem(new ActionOpenNetworkRules(
				PrimeMain.texts.getString("canvasMenuOpenNetworkRules")));
		popup.add(openNetworkRules);

		return popup;
	}




	/**
	 * Creates the initial submenus.
	 */
	private void ObjectCreationMenues()
	{

		JMenuItem submenuAdd = new JMenu(
				PrimeMain.texts.getString("canvasMenuAddNewDevices"));



		JMenuItem submenuDesktop = createAddDesktop(new JMenu(
				PrimeMain.texts.getString("canvasMenuAddClients")));
		submenuAdd.add(submenuDesktop);


		JMenuItem submenuServer = createAddServer(new JMenu(
				PrimeMain.texts.getString("canvasMenuAddServers")));
		submenuAdd.add(submenuServer);


		JMenuItem submenuInfrastructur = createAddInfrastructur(new JMenu(
				PrimeMain.texts.getString("canvasMenuAddInfrasctructur")));
		submenuAdd.add(submenuInfrastructur);


		JMenuItem submenuPeripheral = createAddPeripheral(new JMenu(
				PrimeMain.texts.getString("canvasMenuAddPeripheral")));
		submenuAdd.add(submenuPeripheral);



		popup.add(submenuAdd);
	}


	/**
	 * Creates the menus for the creation desktop objects.
	 */
	private JMenuItem createAddDesktop(JMenuItem submenuDesktop)
	{

		JMenuItem menuItem = new JMenuItem();

		menuItem = new JMenuItem(PrimeMain.texts.getString("desktop"));
		menuItem.setActionCommand("CreateNewST_Desktop_Item");
		menuItem.addActionListener(canvasActListener);
		submenuDesktop.add(menuItem);

		menuItem = new JMenuItem(PrimeMain.texts.getString("laptop"));
		menuItem.setActionCommand("CreateNewST_Laptop_Item");
		menuItem.addActionListener(canvasActListener);
		submenuDesktop.add(menuItem);

		menuItem = new JMenuItem(PrimeMain.texts.getString("thinClient"));
		menuItem.setActionCommand("CreateNewST_ThinClient_Item");
		menuItem.addActionListener(canvasActListener);
		submenuDesktop.add(menuItem);


		return submenuDesktop;
	}



	/**
	 * Creates the menus for the creation server objects.
	 */
	private JMenuItem createAddServer(JMenuItem submenuServer)
	{

		JMenuItem menuItem = new JMenuItem();

		menuItem = new JMenuItem(PrimeMain.texts.getString("GeneralServer"));
		menuItem.setActionCommand("CreateNewST_GeneralServer_Item");
		menuItem.addActionListener(canvasActListener);
		submenuServer.add(menuItem);

		menuItem = new JMenuItem(PrimeMain.texts.getString("antivirusServer"));
		menuItem.setActionCommand("CreateNewST_AntivirusServer_Item");
		menuItem.addActionListener(canvasActListener);
		submenuServer.add(menuItem);

		menuItem = new JMenuItem(PrimeMain.texts.getString("httpServer"));
		menuItem.setActionCommand("CreateNewST_HTTPServer_Item");
		menuItem.addActionListener(canvasActListener);
		submenuServer.add(menuItem);

		menuItem = new JMenuItem(PrimeMain.texts.getString("backupServer"));
		menuItem.setActionCommand("CreateNewST_BackupServer_Item");
		menuItem.addActionListener(canvasActListener);
		submenuServer.add(menuItem);

		menuItem = new JMenuItem(PrimeMain.texts.getString("NASServer"));
		menuItem.setActionCommand("CreateNewST_NASServer_Item");
		menuItem.addActionListener(canvasActListener);
		submenuServer.add(menuItem);

		menuItem = new JMenuItem(PrimeMain.texts.getString("databaseServer"));
		menuItem.setActionCommand("CreateNewST_DatabaseServer_Item");
		menuItem.addActionListener(canvasActListener);
		submenuServer.add(menuItem);

		menuItem = new JMenuItem(
				PrimeMain.texts.getString("virtualizationServer"));
		menuItem.setActionCommand("CreateNewST_VirtualizationServer_Item");
		menuItem.addActionListener(canvasActListener);
		submenuServer.add(menuItem);

		menuItem = new JMenuItem(PrimeMain.texts.getString("mailServer"));
		menuItem.setActionCommand("CreateNewST_MailServer_Item");
		menuItem.addActionListener(canvasActListener);
		submenuServer.add(menuItem);

		menuItem = new JMenuItem(PrimeMain.texts.getString("firewallServer"));
		menuItem.setActionCommand("CreateNewST_FirewallServer_Item");
		menuItem.addActionListener(canvasActListener);
		submenuServer.add(menuItem);

		menuItem = new JMenuItem(PrimeMain.texts.getString("proxyServer"));
		menuItem.setActionCommand("CreateNewST_ProxyServer_Item");
		menuItem.addActionListener(canvasActListener);
		submenuServer.add(menuItem);

		menuItem = new JMenuItem(PrimeMain.texts.getString("printerServer"));
		menuItem.setActionCommand("CreateNewST_PrinterServer_Item");
		menuItem.addActionListener(canvasActListener);
		submenuServer.add(menuItem);


		return submenuServer;
	}



	/**
	 * Creates the menus for the creation infrastructur objects.
	 */
	private JMenuItem createAddInfrastructur(JMenuItem submenuInfrastructur)
	{

		JMenuItem menuItem = new JMenuItem();

		menuItem = new JMenuItem(PrimeMain.texts.getString("hub"));
		menuItem.setActionCommand("CreateNewST_Hub_Item");
		menuItem.addActionListener(canvasActListener);
		submenuInfrastructur.add(menuItem);

		menuItem = new JMenuItem(PrimeMain.texts.getString("switch"));
		menuItem.setActionCommand("CreateNewST_Switch_Item");
		menuItem.addActionListener(canvasActListener);
		submenuInfrastructur.add(menuItem);

		menuItem = new JMenuItem(PrimeMain.texts.getString("router"));
		menuItem.setActionCommand("CreateNewST_Router_Item");
		menuItem.addActionListener(canvasActListener);
		submenuInfrastructur.add(menuItem);

		menuItem = new JMenuItem(PrimeMain.texts.getString("modem"));
		menuItem.setActionCommand("CreateNewST_Modem_Item");
		menuItem.addActionListener(canvasActListener);
		submenuInfrastructur.add(menuItem);

		menuItem = new JMenuItem(PrimeMain.texts.getString("wirelessRouter"));
		menuItem.setActionCommand("CreateNewST_WirelessRouter_Item");
		menuItem.addActionListener(canvasActListener);
		submenuInfrastructur.add(menuItem);

		menuItem = new JMenuItem(PrimeMain.texts.getString("internet"));
		menuItem.setActionCommand("CreateNewST_Internet_Item");
		menuItem.addActionListener(canvasActListener);
		submenuInfrastructur.add(menuItem);

		return submenuInfrastructur;
	}




	/**
	 * Creates the menus for the creation peripheral objects.
	 */
	private JMenuItem createAddPeripheral(JMenuItem submenuPeripheral)
	{

		JMenuItem menuItem = new JMenuItem();

		menuItem = new JMenuItem(PrimeMain.texts.getString("nas"));
		menuItem.setActionCommand("CreateNewST_NAS_Item");
		menuItem.addActionListener(canvasActListener);
		submenuPeripheral.add(menuItem);

		menuItem = new JMenuItem(PrimeMain.texts.getString("scanner"));
		menuItem.setActionCommand("CreateNewST_Scanner_Item");
		menuItem.addActionListener(canvasActListener);
		submenuPeripheral.add(menuItem);

		menuItem = new JMenuItem(PrimeMain.texts.getString("printer"));
		menuItem.setActionCommand("CreateNewST_Printer_Item");
		menuItem.addActionListener(canvasActListener);
		submenuPeripheral.add(menuItem);

		menuItem = new JMenuItem(PrimeMain.texts.getString("fax"));
		menuItem.setActionCommand("CreateNewST_Fax_Item");
		menuItem.addActionListener(canvasActListener);
		submenuPeripheral.add(menuItem);

		menuItem = new JMenuItem(
				PrimeMain.texts.getString("multifunctionPrinter"));
		menuItem.setActionCommand("CreateNewST_MFP_Item");
		menuItem.addActionListener(canvasActListener);
		submenuPeripheral.add(menuItem);

		menuItem = new JMenuItem(PrimeMain.texts.getString("networkPrinter"));
		menuItem.setActionCommand("CreateNewST_NetworkPrinter_Item");
		menuItem.addActionListener(canvasActListener);
		submenuPeripheral.add(menuItem);

		menuItem = new JMenuItem(
				PrimeMain.texts.getString("networkMultifunctionPrinter"));
		menuItem.setActionCommand("CreateNewST_NetworkMFP_Item");
		menuItem.addActionListener(canvasActListener);
		submenuPeripheral.add(menuItem);

		return submenuPeripheral;
	}


	/**
	 * Creates a menu that holds remove actions for the canvas.
	 */
	private void createRemoveMenu()
	{
		JMenu submenu = new JMenu(
				PrimeMain.texts.getString("deleteSubMenuLabel"));

		JMenuItem removeAllWidgets = new JMenuItem(new ActionDeleteAllWidgets(
				PrimeMain.texts.getString("deleteAllWidgetsLabel")));
		removeAllWidgets.setIcon(null);
		submenu.add(removeAllWidgets);


		JMenuItem removeAllConnections = new JMenuItem(
				new ActionDeleteAllConnections(
						PrimeMain.texts.getString("deleteAllConnectionsLabel")));
		removeAllConnections.setIcon(null);
		submenu.add(removeAllConnections);


		JMenuItem removeAllRooms = new JMenuItem(new ActionDeleteAllRooms(
				PrimeMain.texts.getString("deleteAllRoomsLabel")));
		removeAllRooms.setIcon(null);
		submenu.add(removeAllRooms);


		// Adds a separator to the submenu
		submenu.addSeparator();


		JMenuItem removeEverything = new JMenuItem(new ActionDeleteEverything(
				PrimeMain.texts.getString("deleteEverythingLabel")));
		removeEverything.setIcon(null);
		submenu.add(removeEverything);


		popup.add(submenu);
	}


	/**
	 * Creates a menu that holds remove actions for the zoom function.
	 */
	private void createZoomMenu()
	{
		// Zoom Buttons

		JMenu submenuZoom = new JMenu(PrimeMain.texts.getString("zoomLabel"));

		JMenuItem resetZoom = new JMenuItem(new ActionResetZoom(
				PrimeMain.texts.getString("resetZoomLabel")));
		resetZoom.setIcon(null);
		submenuZoom.add(resetZoom);

		JMenuItem zoomIn = new JMenuItem(new ActionZoomIn(
				PrimeMain.texts.getString("zoomInLabel")));
		zoomIn.setIcon(null);
		submenuZoom.add(zoomIn);

		JMenuItem zoomOut = new JMenuItem(new ActionZoomOut(
				PrimeMain.texts.getString("zoomOutLabel")));
		zoomOut.setIcon(null);
		submenuZoom.add(zoomOut);



		popup.add(submenuZoom);
	}
}
