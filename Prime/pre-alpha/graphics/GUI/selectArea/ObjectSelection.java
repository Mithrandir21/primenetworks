/**
 * 
 */
package graphics.GUI.selectArea;


import graphics.ImageLocator;
import graphics.WidgetIcon;
import hardware.HDD;
import infrastructure.Hub;
import infrastructure.Router;
import infrastructure.Switch;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.border.Border;

import peripheral.Printer;
import peripheral.Scanner;
import servers.BackupServer;
import servers.FirewallServer;
import servers.HTTPServer;
import servers.MailServer;
import servers.ProxyServer;
import clients.Desktop;
import clients.Laptop;
import clients.ThinClient;


/**
 * NOTE - Maybe set in a vertical tab for all the different object categories.
 */

/**
 * Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ObjectSelection extends JPanel
{

	// A simple border that is gray
	Border grayline = BorderFactory.createLineBorder(Color.GRAY);



	public ObjectSelection()
	{

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(grayline);

		initClientButtonIcons();

		initServerButtonIcons();

		initExternalHardwareButtonIcons();

		initInfrastructureButtonIcons();
	}


	private void initClientButtonIcons()
	{
		this.add(makeImageIcon("Desktop", Desktop.class, "Desktop"));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon("Laptop", Laptop.class, "Laptop"));

		this.add(new JToolBar.Separator());
		
		this.add(makeImageIcon("Screen", ThinClient.class, "Thin Client"));

		this.add(new JToolBar.Separator());


		this.add(new JSeparator());
	}


	private void initServerButtonIcons()
	{
		this.add(makeImageIcon("Web-server", HTTPServer.class, "Web Server"));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon("Data-server", BackupServer.class, "Backup Server"));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon("Email-server", MailServer.class, "Email Server"));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon("Firewall-server", FirewallServer.class, "Firewall Server"));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon("Proxy-server", ProxyServer.class, "Proxy Server"));

		this.add(new JToolBar.Separator());



		this.add(new JSeparator());
	}

	private void initExternalHardwareButtonIcons()
	{
		this.add(makeImageIcon("Scanner", Scanner.class, "Scanner"));

		this.add(new JToolBar.Separator());		
		
		this.add(makeImageIcon("Printer", Printer.class, "Printer"));

		this.add(new JToolBar.Separator());


		this.add(new JSeparator());
	}


	private void initInfrastructureButtonIcons()
	{
		this.add(makeImageIcon("Hub", Hub.class, "Hub"));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon("Switch", Switch.class, "Switch"));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon("Router", Router.class, "Router"));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon("WirelessRouter", Router.class, "Wireless Router"));

		this.add(new JToolBar.Separator());


		this.add(new JSeparator());
	}


	@SuppressWarnings("unchecked")
	private WidgetIcon makeImageIcon(String name, Class objectType, String text)
	{
		ImageIcon Icon = ImageLocator.getImageIconObject(name);
		WidgetIcon iconButton = new WidgetIcon(Icon, objectType, text);

		iconButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		iconButton.setAlignmentY(Component.TOP_ALIGNMENT);
		// iconButton.setBorder(grayline);

		return iconButton;
	}


	/**
	 * @param text
	 * @return
	 */
	private JLabel makeCenteredJLabel(String text)
	{
		JLabel label = new JLabel(text);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setAlignmentY(Component.TOP_ALIGNMENT);


		return label;
	}



}
