package graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuCanvas;


import graphics.GUI.workareaCanvas.WorkareaCanvas;

import java.awt.Point;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;


/**
 * The class that provides a JPopupMenu to be shown on any given canvas.
 * 
 * The methods in the class both creates and returns the menus.
 * 
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
	 * The class that will listen for actions and then perform the correct action.
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
	 *            The location(Point) on the WorkareaCanvas that the menu will be shown at
	 * @return The JPopupMenu that will be shown when a user right clicks on an empty part of a WorkareaCanvas.
	 */
	public JPopupMenu createPopupMenu(Point localLocation)
	{
		// The action listener that will determine what actions from the JPopupMenu
		// the user wants to perform.
		canvasActListener = new WorkareaCanvasActionListener(canvas, localLocation);

		ObjectCreationMenues();

		return popup;
	}




	/**
	 * Creates the initial submenus.
	 * 
	 */
	private void ObjectCreationMenues()
	{

		JMenuItem submenuAdd = new JMenu("Add new devices");



		JMenuItem submenuDesktop = createAddDesktop(new JMenu("Add Clients"));
		submenuAdd.add(submenuDesktop);


		JMenuItem submenuServer = createAddServer(new JMenu("Add Server"));
		submenuAdd.add(submenuServer);


		JMenuItem submenuInfrastructur = createAddInfrastructur(new JMenu("Add Infrastructur"));
		submenuAdd.add(submenuInfrastructur);


		JMenuItem submenuPeripheral = createAddPeripheral(new JMenu("Add Peripheral"));
		submenuAdd.add(submenuPeripheral);



		popup.add(submenuAdd);
	}


	/**
	 * Creates the menus for the creation desktop objects.
	 */
	private JMenuItem createAddDesktop(JMenuItem submenuDesktop)
	{

		JMenuItem menuItem = new JMenuItem();

		menuItem = new JMenuItem("Desktop");
		menuItem.setActionCommand("CreateNewST_Desktop_Item");
		menuItem.addActionListener(canvasActListener);
		submenuDesktop.add(menuItem);

		menuItem = new JMenuItem("Laptop");
		menuItem.setActionCommand("CreateNewST_Laptop_Item");
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

		menuItem = new JMenuItem("Web Server");
		menuItem.setActionCommand("CreateNewST_HTTPServer_Item");
		menuItem.addActionListener(canvasActListener);
		submenuServer.add(menuItem);

		menuItem = new JMenuItem("Mail Server");
		menuItem.setActionCommand("CreateNewST_MailServer_Item");
		menuItem.addActionListener(canvasActListener);
		submenuServer.add(menuItem);

		menuItem = new JMenuItem("Backup Server");
		menuItem.setActionCommand("CreateNewST_BackupServer_Item");
		menuItem.addActionListener(canvasActListener);
		submenuServer.add(menuItem);

		menuItem = new JMenuItem("Firewall Server");
		menuItem.setActionCommand("CreateNewST_FirewallServer_Item");
		menuItem.addActionListener(canvasActListener);
		submenuServer.add(menuItem);

		menuItem = new JMenuItem("Proxy Server");
		menuItem.setActionCommand("CreateNewST_ProxyServer_Item");
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

		menuItem = new JMenuItem("Hub");
		menuItem.setActionCommand("CreateNewST_Hub_Item");
		menuItem.addActionListener(canvasActListener);
		submenuInfrastructur.add(menuItem);

		menuItem = new JMenuItem("Switch");
		menuItem.setActionCommand("CreateNewST_Switch_Item");
		menuItem.addActionListener(canvasActListener);
		submenuInfrastructur.add(menuItem);

		menuItem = new JMenuItem("Router");
		menuItem.setActionCommand("CreateNewST_Router_Item");
		menuItem.addActionListener(canvasActListener);
		submenuInfrastructur.add(menuItem);

		menuItem = new JMenuItem("Wireless Router");
		menuItem.setActionCommand("CreateNewST_WirelessRouter_Item");
		menuItem.addActionListener(canvasActListener);
		submenuInfrastructur.add(menuItem);

		menuItem = new JMenuItem("Internet");
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

		menuItem = new JMenuItem("Scanner");
		menuItem.setActionCommand("CreateNewST_Scanner_Item");
		menuItem.addActionListener(canvasActListener);
		submenuPeripheral.add(menuItem);


		return submenuPeripheral;
	}


}
