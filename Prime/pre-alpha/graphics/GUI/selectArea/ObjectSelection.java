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
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.Border;

import peripheral.Scanner;
import servers.BackupServer;
import servers.FirewallServer;
import servers.HTTPServer;
import servers.MailServer;
import servers.ProxyServer;
import clients.Desktop;
import clients.Laptop;


/**
 * NOTE - Maybe set inn a vertical tab for all the different object categories.
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

		initHardwareButtonIcons();

		initExternalHardwareButtonIcons();

		initInfrastructureButtonIcons();

		initSoftwareButtonIcons();

		// /////////////
		JTextField tf = new JTextField();
		// tf.setDragEnabled(true);

		this.add(tf);
		// ////////////
	}


	private void initClientButtonIcons()
	{
		this.add(makeImageIcon("images/objectImages/client.png", Desktop.class, "Desktop"));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon("images/objectImages/client.png", Laptop.class, "Laptop"));

		this.add(new JToolBar.Separator());


		this.add(new JSeparator());
	}


	private void initServerButtonIcons()
	{
		this
				.add(makeImageIcon("images/objectImages/web-server.png", HTTPServer.class,
						"Web Server"));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon("images/objectImages/data-server.png", BackupServer.class,
				"Backup Server"));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon("images/objectImages/email-server.png", MailServer.class,
				"Email Server"));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon("images/objectImages/firewall-server.png", FirewallServer.class,
				"Firewall Server"));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon("images/objectImages/proxy-server.png", ProxyServer.class,
				"Proxy Server"));

		this.add(new JToolBar.Separator());



		this.add(new JSeparator());
	}

	private void initHardwareButtonIcons()
	{
		this.add(makeImageIcon("images/objectImages/harddisc.png", HDD.class, "Harddisc"));

		this.add(new JToolBar.Separator());


		this.add(new JSeparator());
	}

	private void initExternalHardwareButtonIcons()
	{
		this.add(makeImageIcon("images/objectImages/scanner.png", Scanner.class, "Scanner"));

		this.add(new JToolBar.Separator());


		this.add(new JSeparator());
	}


	private void initInfrastructureButtonIcons()
	{
		this.add(makeImageIcon("images/objectImages/hub.png", Hub.class, "Hub"));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon("images/objectImages/switch.jpg", Switch.class, "Switch"));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon("images/objectImages/router.jpg", Router.class, "Router"));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon("images/objectImages/WirelessRouter.gif", Router.class,
				"Wireless Router"));

		this.add(new JToolBar.Separator());


		this.add(new JSeparator());
	}


	private void initSoftwareButtonIcons()
	{

	}


	@SuppressWarnings("unchecked")
	private WidgetIcon makeImageIcon(String imgURL, Class objectType, String text)
	{
		ImageIcon Icon = ImageLocator.createImageIcon(this.getClass().getResource(imgURL));
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
