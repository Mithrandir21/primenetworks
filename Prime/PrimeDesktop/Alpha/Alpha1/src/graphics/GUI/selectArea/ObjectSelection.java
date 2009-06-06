/**
 * 
 */
package graphics.GUI.selectArea;


import graphics.GraphicalFunctions;
import graphics.ImageLocator;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.border.Border;

import objects.clientObjects.Desktop;
import objects.clientObjects.Laptop;
import objects.clientObjects.ThinClient;
import objects.infrastructureObjects.Hub;
import objects.infrastructureObjects.Internet;
import objects.infrastructureObjects.Router;
import objects.infrastructureObjects.Switch;
import objects.infrastructureObjects.WirelessRouter;
import objects.peripheralObjects.NetworkPrinter;
import objects.peripheralObjects.Printer;
import objects.peripheralObjects.Scanner;
import objects.serverObjects.BackupServer;
import objects.serverObjects.FirewallServer;
import objects.serverObjects.HTTPServer;
import objects.serverObjects.MailServer;
import objects.serverObjects.ProxyServer;
import widgets.WidgetIcon;



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

		this.setPreferredSize(new Dimension(270, getCompSize()));
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

		this.add(makeImageIcon("PrinterNetwork", NetworkPrinter.class, "Network Printer"));

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

		this.add(makeImageIcon("WirelessRouter", WirelessRouter.class, "Wireless Router"));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon("Internet", Internet.class, "Internet"));

		this.add(new JToolBar.Separator());


		this.add(new JSeparator());
	}


	@SuppressWarnings("unchecked")
	private WidgetIcon makeImageIcon(String name, Class objectType, String text)
	{
		ImageIcon Icon = ImageLocator.getImageIconObject(name);
		WidgetIcon iconButton = new WidgetIcon(Icon, objectType, text);
		
		// Sets up the WidgetIcon
		GraphicalFunctions.widgetIconSetup(iconButton);
		
		iconButton.setSize(Icon.getIconWidth(), Icon.getIconHeight());

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



	/**
	 * Javadoc-TODO - Description
	 * 
	 * @return
	 */
	private int getCompSize()
	{
		int size = 0;

		for ( int i = 0; i < this.getComponentCount(); i++ )
		{
			size += this.getComponent(i).getHeight();
		}


		return size + (this.getComponentCount() * 5);
	}


}
