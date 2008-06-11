/**
 * 
 */
package graphics.GUI.workareaCanvas.providers;

import graphics.PrimeMain1;
import graphics.GUI.workareaCanvas.WorkareaCanvas;

import java.awt.event.MouseListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * @author Bam
 *
 */
public class CanvasMenu
{
	private static JPopupMenu popup = new JPopupMenu();
	
	
	public static JPopupMenu createPopupMenu(WorkareaCanvas canvas)
	{
		InitialMenues(canvas);
		

		popup.addSeparator();
		
		
		ObjectCreationMenues(canvas);
		
		
		return popup;
	}
	
	
	
	private static void InitialMenues(WorkareaCanvas canvas)
	{
		JMenuItem menuItem;
		
		menuItem = new JMenuItem("A popup menu item");
		menuItem.setName("CreateNewItem");
		menuItem.addActionListener(canvas);
		popup.add(menuItem);
		
		menuItem = new JMenuItem("Another popup menu item");
		menuItem.setName("CreateAnotherNewItem");
		menuItem.addActionListener(canvas);
		popup.add(menuItem);

		menuItem = new JMenuItem("Yet Another popup menu item");
		menuItem.setName("CreateYetAnotherNewItem");
		menuItem.addActionListener(canvas);
		popup.add(menuItem);
	}
	
	
	private static void ObjectCreationMenues(WorkareaCanvas canvas)
	{
		
		JMenuItem submenuAdd = new JMenu("Add new devices");
		
		
		JMenuItem submenuDesktop = createAddDesktop(canvas,new JMenu("Add Clients"));
		
		submenuAdd.add(submenuDesktop);
		
		
		JMenuItem submenuServer = createAddServer(canvas,new JMenu("Add Server"));
		
		submenuAdd.add(submenuServer);
		
		
		
		JMenuItem submenuInfrastructur = 
			createAddInfrastructur(canvas,new JMenu("Add Infrastructur"));
		
		submenuAdd.add(submenuInfrastructur);
		
		
		
		JMenuItem submenuPeripheral = createAddPeripheral(canvas, new JMenu("Add Peripheral"));
		
		submenuAdd.add(submenuPeripheral);
		
		

		
        popup.add(submenuAdd);
	}
	
	
	private static JMenuItem createAddDesktop(WorkareaCanvas canvas,JMenuItem submenuDesktop)
	{
		
		JMenuItem menuItem = new JMenuItem();
		
		menuItem = new JMenuItem("Desktop");
		menuItem.setName("CreateNewST_Desktop_Item");
		menuItem.addActionListener(canvas);
		submenuDesktop.add(menuItem);

		menuItem = new JMenuItem("Laptop");
		menuItem.setName("CreateNewST_Laptop_Item");
		menuItem.addActionListener(canvas);
		submenuDesktop.add(menuItem);

		

		return submenuDesktop;
	}
	
	
	
	private static JMenuItem createAddServer(WorkareaCanvas canvas,JMenuItem submenuServer)
	{
		
		JMenuItem menuItem = new JMenuItem();
		
		menuItem = new JMenuItem("Web Server");
		menuItem.setName("CreateNewST_HTTPServer_Item");
		menuItem.addActionListener(canvas);
		submenuServer.add(menuItem);

		menuItem = new JMenuItem("Mail Server");
		menuItem.setName("CreateNewST_MailServer_Item");
		menuItem.addActionListener(canvas);
		submenuServer.add(menuItem);
        
		menuItem = new JMenuItem("Backup Server");
		menuItem.setName("CreateNewST_BackupServer_Item");
		menuItem.addActionListener(canvas);
		submenuServer.add(menuItem);
		
		menuItem = new JMenuItem("Firewall Server");
		menuItem.setName("CreateNewST_FirewallServer_Item");
		menuItem.addActionListener(canvas);
		submenuServer.add(menuItem);
		
		menuItem = new JMenuItem("Proxy Server");
		menuItem.setName("CreateNewST_ProxyServer_Item");
		menuItem.addActionListener(canvas);
		submenuServer.add(menuItem);
		

		return submenuServer;
	}
	
	
	
	private static JMenuItem createAddInfrastructur(WorkareaCanvas canvas
			,JMenuItem submenuInfrastructur)
	{
		
		JMenuItem menuItem = new JMenuItem();
		
		menuItem = new JMenuItem("Hub");
		menuItem.setName("CreateNewST_Hub_Item");
		menuItem.addActionListener(canvas);
		submenuInfrastructur.add(menuItem);

		menuItem = new JMenuItem("Switch");
		menuItem.setName("CreateNewST_Switch_Item");
		menuItem.addActionListener(canvas);
		submenuInfrastructur.add(menuItem);
        
		menuItem = new JMenuItem("Router");
		menuItem.setName("CreateNewST_Router_Item");
		menuItem.addActionListener(canvas);
		submenuInfrastructur.add(menuItem);
		
		menuItem = new JMenuItem("Wireless Router");
		menuItem.setName("CreateNewST_WirelessRouter_Item");
		menuItem.addActionListener(canvas);
		submenuInfrastructur.add(menuItem);
		

		return submenuInfrastructur;
	}
	
	
	
	
	private static JMenuItem createAddPeripheral(WorkareaCanvas canvas
			,JMenuItem submenuPeripheral)
	{
		
		JMenuItem menuItem = new JMenuItem();
		
		menuItem = new JMenuItem("Scanner");
		menuItem.setName("CreateNewST_Scanner_Item");
		menuItem.addActionListener(canvas);
		submenuPeripheral.add(menuItem);
		

		return submenuPeripheral;
	}
}
