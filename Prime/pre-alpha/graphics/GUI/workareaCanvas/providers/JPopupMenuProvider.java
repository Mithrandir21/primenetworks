package graphics.GUI.workareaCanvas.providers;


import graphics.PrimeMain1;
import graphics.GUI.workareaCanvas.WorkareaCanvas;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.netbeans.api.visual.widget.Widget;

import widgetManipulation.WidgetObject;


public class JPopupMenuProvider
{
	private JPopupMenu popup = new JPopupMenu();


	public JPopupMenu createPopupMenu(Widget widget)
	{

		WorkareaCanvas canvas = PrimeMain1.currentCanvas;

		PrimeMain1.currentCanvas.setCurrentWidgetObject((WidgetObject) widget);


		InitialMenues(canvas);

		// popup.addSeparator();
		//
		//
		// ObjectCreationMenues();

		// // Add listener to the text area so the popup menu can come up.
		// MouseListener popupListener = new PopupListener(popup);
		// PrimeMain1.myView.addMouseListener(popupListener);

		return popup;
	}

	private void InitialMenues(WorkareaCanvas canvas)
	{
		JMenuItem menuItem;

		menuItem = new JMenuItem("Delete this object");
		menuItem.setName("DeleteThisObject");
		menuItem.addActionListener(canvas);
		popup.add(menuItem);

		menuItem = new JMenuItem("Delete all connection to and from this object");
		menuItem.setName("DeleteConnectionsObject");
		menuItem.addActionListener(canvas);
		popup.add(menuItem);

		menuItem = new JMenuItem("A popup menu item");
		menuItem.setName("CreateNewItem");
		popup.add(menuItem);

		menuItem = new JMenuItem("Another popup menu item");
		menuItem.setName("CreateAnotherNewItem");
		popup.add(menuItem);

		menuItem = new JMenuItem("Yet Another popup menu item");
		menuItem.setName("CreateYetAnotherNewItem");
		popup.add(menuItem);
	}


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


	private JMenuItem createAddDesktop(JMenuItem submenuDesktop)
	{

		JMenuItem menuItem = new JMenuItem();

		menuItem = new JMenuItem("Desktop");
		menuItem.setName("CreateNewST_Desktop_Item");
		submenuDesktop.add(menuItem);

		menuItem = new JMenuItem("Laptop");
		menuItem.setName("CreateNewST_Laptop_Item");
		submenuDesktop.add(menuItem);



		return submenuDesktop;
	}



	private JMenuItem createAddServer(JMenuItem submenuServer)
	{

		JMenuItem menuItem = new JMenuItem();

		menuItem = new JMenuItem("Web Server");
		menuItem.setName("CreateNewST_HTTPServer_Item");
		submenuServer.add(menuItem);

		menuItem = new JMenuItem("Mail Server");
		menuItem.setName("CreateNewST_MailServer_Item");
		submenuServer.add(menuItem);

		menuItem = new JMenuItem("Backup Server");
		menuItem.setName("CreateNewST_BackupServer_Item");
		submenuServer.add(menuItem);

		menuItem = new JMenuItem("Firewall Server");
		menuItem.setName("CreateNewST_FirewallServer_Item");
		submenuServer.add(menuItem);

		menuItem = new JMenuItem("Proxy Server");
		menuItem.setName("CreateNewST_ProxyServer_Item");
		submenuServer.add(menuItem);


		return submenuServer;
	}



	private JMenuItem createAddInfrastructur(JMenuItem submenuInfrastructur)
	{

		JMenuItem menuItem = new JMenuItem();

		menuItem = new JMenuItem("Hub");
		menuItem.setName("CreateNewST_Hub_Item");
		submenuInfrastructur.add(menuItem);

		menuItem = new JMenuItem("Switch");
		menuItem.setName("CreateNewST_Switch_Item");
		submenuInfrastructur.add(menuItem);

		menuItem = new JMenuItem("Router");
		menuItem.setName("CreateNewST_Router_Item");
		submenuInfrastructur.add(menuItem);

		menuItem = new JMenuItem("Wireless Router");
		menuItem.setName("CreateNewST_WirelessRouter_Item");
		submenuInfrastructur.add(menuItem);


		return submenuInfrastructur;
	}




	private JMenuItem createAddPeripheral(JMenuItem submenuPeripheral)
	{

		JMenuItem menuItem = new JMenuItem();

		menuItem = new JMenuItem("Scanner");
		menuItem.setName("CreateNewST_Scanner_Item");
		submenuPeripheral.add(menuItem);


		return submenuPeripheral;
	}
}
