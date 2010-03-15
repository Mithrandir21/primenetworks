/**
 * 
 */
package graphics.GUI.selectArea;


import graphics.GraphicalFunctions;
import graphics.PrimeMain1;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.border.Border;

import objects.Object;
import objects.clientObjects.Desktop;
import objects.clientObjects.Laptop;
import objects.clientObjects.ThinClient;
import objects.infrastructureObjects.Hub;
import objects.infrastructureObjects.Internet;
import objects.infrastructureObjects.Modem;
import objects.infrastructureObjects.Router;
import objects.infrastructureObjects.Switch;
import objects.infrastructureObjects.WirelessRouter;
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
import widgets.WorkareaCanvas;



/**
 * NOTE - Maybe set in a vertical tab for all the different object categories.
 */

/**
 * This JPanel extension is where the images of the different {@link Object
 * Objects} are placed. They can be clicked and dragged into an open {@link WorkareaCanvas}.
 * 
 * @author Bahram Malaekeh
 * @version 1.0
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

		this.setPreferredSize(new Dimension(290, getCompSize()));
	}


	private void initClientButtonIcons()
	{
		this.add(makeImageIcon(Desktop.class));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon(Laptop.class));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon(ThinClient.class));

		this.add(new JToolBar.Separator());


		this.add(new JSeparator());
	}


	private void initServerButtonIcons()
	{
		this.add(makeImageIcon(HTTPServer.class));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon(BackupServer.class));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon(DatabaseServer.class));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon(MailServer.class));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon(FirewallServer.class));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon(ProxyServer.class));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon(PrinterServer.class));

		this.add(new JToolBar.Separator());



		this.add(new JSeparator());
	}

	private void initExternalHardwareButtonIcons()
	{
		this.add(makeImageIcon(Scanner.class));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon(Printer.class));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon(Fax.class));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon(MultifunctionPrinter.class));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon(NetworkPrinter.class));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon(NetworkMultifunctionPrinter.class));

		this.add(new JToolBar.Separator());


		this.add(new JSeparator());
	}


	private void initInfrastructureButtonIcons()
	{
		this.add(makeImageIcon(Hub.class));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon(Switch.class));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon(Router.class));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon(Modem.class));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon(WirelessRouter.class));

		this.add(new JToolBar.Separator());

		this.add(makeImageIcon(Internet.class));

		this.add(new JToolBar.Separator());


		this.add(new JSeparator());
	}


	@SuppressWarnings("unchecked")
	private WidgetIcon makeImageIcon(Class objectType)
	{
		ImageIcon Icon = PrimeMain1.objectImageIcons.get(objectType);

		WidgetIcon iconButton = null;

		try
		{
			iconButton = new WidgetIcon(Icon, objectType, objectType
					.getSimpleName());
		}
		catch ( Exception e )
		{
			System.out.println("NullPointerException" + " - ObjectSelection "
					+ " - " + objectType + "\n\n");
			System.exit(0);
		}


		// Sets up the WidgetIcon
		GraphicalFunctions.widgetIconSetup(iconButton);

		iconButton.setSize(Icon.getIconWidth(), Icon.getIconHeight());

		// iconButton.setVerticalTextPosition(AbstractButton.BOTTOM);
		// iconButton.setHorizontalTextPosition(AbstractButton.CENTER);
		iconButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		iconButton.setAlignmentY(Component.TOP_ALIGNMENT);
		// iconButton.setBorder(grayline);

		return iconButton;
	}



	/**
	 * Gets the vertical size of all the components in this JPanel.
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
