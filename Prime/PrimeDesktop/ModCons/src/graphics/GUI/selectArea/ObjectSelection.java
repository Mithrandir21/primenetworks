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
package graphics.GUI.selectArea;


import exceptions.ObjectNotFoundException;
import graphics.GraphicalFunctions;
import graphics.ImageLocator;
import graphics.PrimeMain;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import managment.ArrayManagment;
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
import objects.peripheralObjects.ExternalHDD;
import objects.peripheralObjects.Fax;
import objects.peripheralObjects.GenericDevice;
import objects.peripheralObjects.MultifunctionPrinter;
import objects.peripheralObjects.NetworkMultifunctionPrinter;
import objects.peripheralObjects.NetworkPrinter;
import objects.peripheralObjects.Printer;
import objects.peripheralObjects.Scanner;
import objects.rackUnits.Rack;
import objects.serverObjects.AntivirusServer;
import objects.serverObjects.BackupServer;
import objects.serverObjects.DatabaseServer;
import objects.serverObjects.FirewallServer;
import objects.serverObjects.GenericServer;
import objects.serverObjects.HTTPServer;
import objects.serverObjects.MailServer;
import objects.serverObjects.NASServer;
import objects.serverObjects.PrinterServer;
import objects.serverObjects.ProxyServer;
import objects.serverObjects.VirtualizationServer;

import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

import widgets.WidgetIcon;
import widgets.WorkareaCanvas;



/**
 * NOTE - Maybe set in a vertical tab for all the different object categories.
 */

/**
 * This JPanel extension is where the images of the different {@link Object
 * Objects} are placed. They can be clicked and dragged into an open
 * {@link WorkareaCanvas}.
 * 
 * @author Bahram Malaekeh
 * @version 1.0
 */
public class ObjectSelection extends JPanel
{
	private static JXTaskPane genericDevicePane;

	private static MouseListener mouseLis;

	private static boolean transferable;

	private static boolean showUnknown;



	/**
	 * TODO - Description NEEDED!
	 */
	public ObjectSelection()
	{
		setupObjectsGroups(null, false, false, false);
	}


	/**
	 * TODO - Description NEEDED!
	 */
	public ObjectSelection(boolean transferable, boolean customObjects,
			boolean unknown)
	{
		setupObjectsGroups(null, transferable, customObjects, unknown);
	}


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param mouseLis
	 */
	public ObjectSelection(MouseListener mouseLis, boolean customObjects,
			boolean unknown)
	{
		setupObjectsGroups(mouseLis, false, customObjects, unknown);
	}


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param mouseLis
	 */
	public ObjectSelection(MouseListener mouseLis, boolean transferable,
			boolean customObjects, boolean unknown)
	{
		setupObjectsGroups(mouseLis, transferable, customObjects, unknown);
	}


	/**
	 * TODO - Description
	 */
	private void setupObjectsGroups(MouseListener mouseLis,
			boolean transferable, boolean customObjects, boolean unknown)
	{
		this.setLayout(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();

		this.showUnknown = unknown;
		this.mouseLis = mouseLis;
		this.transferable = transferable;


		d.fill = GridBagConstraints.BOTH;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		d.weighty = 1.0; // request any extra vertical space
		d.weightx = 1.0; // request any extra vertical space
		// d.anchor = GridBagConstraints.CENTER; // bottom of space
		// d.insets = new Insets(10, 10, 10, 10); // top padding
		// d.gridwidth = 1; // 2 columns wide
		// d.gridheight = 1; // 2 columns wide
		d.gridx = 0;
		d.gridy = 0;

		JXTaskPaneContainer tpc = new JXTaskPaneContainer();


		// Adds the group panel to the collapsible container
		tpc.add(initClientButtonIcons(mouseLis, transferable));


		// Adds the group panel to the collapsible container
		tpc.add(initServerButtonIcons(mouseLis, transferable));


		// Adds the group panel to the collapsible container
		tpc.add(initExternalHardwareButtonIcons(mouseLis, transferable));


		// Adds the group panel to the collapsible container
		tpc.add(initInfrastructureButtonIcons(mouseLis, transferable));


		// // Adds the group panel to the collapsible container
		// tpc.add(initRackButtonIcons(mouseLis, transferable));

		if ( customObjects )
		{
			// Adds the group panel to the collapsible container
			tpc.add(initGeneralObjectsButtonIcons(mouseLis, transferable));
		}


		JScrollPane scrollArea = new JScrollPane(tpc);
		// Increases how far the scroll bar scrolls on one step of a mouse wheel
		scrollArea.getVerticalScrollBar().setUnitIncrement(30);


		// Adds the container to this panel
		this.add(scrollArea, d);

		// this.setPreferredSize(new Dimension(240, getCompSize()));
	}


	/**
	 * TODO - Description
	 */
	private JXTaskPane initClientButtonIcons(MouseListener mouseLis,
			boolean transferable)
	{
		ArrayList<WidgetIcon> iconsList = new ArrayList<WidgetIcon>();

		iconsList.add(makeImageIcon(Desktop.class, mouseLis, transferable));
		iconsList.add(makeImageIcon(Laptop.class, mouseLis, transferable));
		iconsList.add(makeImageIcon(ThinClient.class, mouseLis, transferable));

		WidgetIcon[] widIcons = new WidgetIcon[iconsList.size()];
		iconsList.toArray(widIcons);


		return getWidgetGroup(
				PrimeMain.texts.getString("selectAreaDesktopGroupName"),
				widIcons, true);
	}

	/**
	 * TODO - Description
	 */
	private JXTaskPane initServerButtonIcons(MouseListener mouseLis,
			boolean transferable)
	{
		ArrayList<WidgetIcon> iconsList = new ArrayList<WidgetIcon>();

		iconsList.add(makeImageIcon(AntivirusServer.class, mouseLis,
				transferable));
		iconsList.add(makeImageIcon(HTTPServer.class, mouseLis, transferable));
		iconsList
				.add(makeImageIcon(BackupServer.class, mouseLis, transferable));
		iconsList.add(makeImageIcon(NASServer.class, mouseLis, transferable));
		iconsList.add(makeImageIcon(DatabaseServer.class, mouseLis,
				transferable));
		iconsList.add(makeImageIcon(VirtualizationServer.class, mouseLis,
				transferable));
		iconsList.add(makeImageIcon(MailServer.class, mouseLis, transferable));
		iconsList.add(makeImageIcon(FirewallServer.class, mouseLis,
				transferable));
		iconsList.add(makeImageIcon(ProxyServer.class, mouseLis, transferable));
		iconsList
				.add(makeImageIcon(PrinterServer.class, mouseLis, transferable));
		iconsList
				.add(makeImageIcon(GenericServer.class, mouseLis, transferable));

		WidgetIcon[] widIcons = new WidgetIcon[iconsList.size()];
		iconsList.toArray(widIcons);


		return getWidgetGroup(
				PrimeMain.texts.getString("selectAreaServerGroupName"),
				widIcons, true);
	}

	/**
	 * TODO - Description
	 */
	private JXTaskPane initExternalHardwareButtonIcons(MouseListener mouseLis,
			boolean transferable)
	{
		ArrayList<WidgetIcon> iconsList = new ArrayList<WidgetIcon>();

		iconsList.add(makeImageIcon(ExternalHDD.class, mouseLis, transferable));
		iconsList.add(makeImageIcon(Scanner.class, mouseLis, transferable));
		iconsList.add(makeImageIcon(Printer.class, mouseLis, transferable));
		iconsList.add(makeImageIcon(Fax.class, mouseLis, transferable));
		iconsList.add(makeImageIcon(MultifunctionPrinter.class, mouseLis,
				transferable));
		iconsList.add(makeImageIcon(NetworkPrinter.class, mouseLis,
				transferable));
		iconsList.add(makeImageIcon(NetworkMultifunctionPrinter.class,
				mouseLis, transferable));

		WidgetIcon[] widIcons = new WidgetIcon[iconsList.size()];
		iconsList.toArray(widIcons);


		return getWidgetGroup(
				PrimeMain.texts.getString("selectAreaExternalGroupName"),
				widIcons, false);
	}

	/**
	 * TODO - Description
	 */
	private JXTaskPane initInfrastructureButtonIcons(MouseListener mouseLis,
			boolean transferable)
	{
		ArrayList<WidgetIcon> iconsList = new ArrayList<WidgetIcon>();

		iconsList.add(makeImageIcon(Hub.class, mouseLis, transferable));
		iconsList.add(makeImageIcon(Switch.class, mouseLis, transferable));
		iconsList.add(makeImageIcon(Router.class, mouseLis, transferable));
		iconsList.add(makeImageIcon(Modem.class, mouseLis, transferable));
		iconsList.add(makeImageIcon(WirelessRouter.class, mouseLis,
				transferable));
		iconsList.add(makeImageIcon(Internet.class, mouseLis, transferable));

		WidgetIcon[] widIcons = new WidgetIcon[iconsList.size()];
		iconsList.toArray(widIcons);


		return getWidgetGroup(
				PrimeMain.texts.getString("selectAreaNetworkGroupName"),
				widIcons, false);
	}


	private JXTaskPane initGeneralObjectsButtonIcons(MouseListener mouseLis,
			boolean transferable)
	{
		WidgetIcon[] widIcons = getGenericDevicesIcons();

		genericDevicePane = getWidgetGroup(
				PrimeMain.texts.getString("selectAreaGeneralGroupName"),
				widIcons, false);
		return genericDevicePane;
	}



	/**
	 * TODO - Description
	 */
	private JXTaskPane initRackButtonIcons(MouseListener mouseLis,
			boolean transferable)
	{
		ArrayList<WidgetIcon> iconsList = new ArrayList<WidgetIcon>();

		iconsList.add(makeImageIcon(Rack.class, mouseLis, transferable));

		WidgetIcon[] widIcons = new WidgetIcon[iconsList.size()];
		iconsList.toArray(widIcons);


		return getWidgetGroup(
				PrimeMain.texts.getString("selectAreaRackNetworkGroupName"),
				widIcons, false);
	}


	/**
	 * TODO - Description
	 */
	private JXTaskPane getWidgetGroup(String groupName, WidgetIcon[] widIcons,
			boolean expanded)
	{
		JXTaskPane group = new JXTaskPane();
		group.setTitle(groupName);
		group.setSpecial(true);
		group.setCollapsed(!expanded);



		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();

		d.fill = GridBagConstraints.BOTH;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		// d.weighty = 1.0; // request any extra vertical space
		d.weightx = 1.0; // request any extra vertical space
		// d.anchor = GridBagConstraints.CENTER; // bottom of space
		d.insets = new Insets(0, 0, 5, 0); // top padding
		// d.gridwidth = 1; // 2 columns wide
		// d.gridheight = 1; // 2 columns wide
		d.gridy = 0;


		for ( int i = 0; i < widIcons.length; i++ )
		{
			d.gridy = i;
			panel.add(widIcons[i], d);
		}


		// Adds the panel with the objects to the group panel
		group.add(panel);

		return group;
	}




	/**
	 * TODO - Description
	 */
	@SuppressWarnings("unchecked")
	private WidgetIcon makeImageIcon(Class<?> objectType,
			MouseListener mouseLis, boolean transferable)
	{

		WidgetIcon iconButton = null;

		if ( objectType != null )
		{
			ImageIcon Icon = PrimeMain.objectImageIcons.get(objectType);

			try
			{
				iconButton = new WidgetIcon(Icon, objectType,
						objectType.getSimpleName());

				// Adds the given mouselistener if its not null
				if ( mouseLis != null )
				{
					iconButton.addMouseListener(mouseLis);
				}
			}
			catch ( Exception e )
			{
				System.out.println("NullPointerException"
						+ " - ObjectSelection " + " - " + objectType
						+ System.getProperty("line.separator"));
				System.exit(0);
			}


			if ( transferable )
			{
				// Sets up the WidgetIcon
				GraphicalFunctions.widgetIconSetup(iconButton, Icon);
			}

			iconButton.setSize(Icon.getIconWidth(), Icon.getIconHeight());

			// iconButton.setVerticalTextPosition(AbstractButton.BOTTOM);
			// iconButton.setHorizontalTextPosition(AbstractButton.CENTER);
			iconButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			iconButton.setAlignmentY(Component.TOP_ALIGNMENT);
			// iconButton.setBorder(grayline);
		}

		return iconButton;
	}


	/**
	 * TODO - Description
	 */
	@SuppressWarnings("unchecked")
	private static WidgetIcon makeImageIcon(ImageIcon Icon,
			Class<?> objectType, String objectName, String desc,
			MouseListener mouseLis, boolean transferable)
	{
		WidgetIcon iconButton = null;

		if ( Icon != null && objectType != null )
		{
			try
			{
				iconButton = new WidgetIcon(Icon, objectType, objectName, desc);

				// Adds the given mouselistener if its not null
				if ( mouseLis != null )
				{
					iconButton.addMouseListener(mouseLis);
				}
			}
			catch ( Exception e )
			{
				System.out.println("NullPointerException"
						+ " - ObjectSelection " + " - " + objectType
						+ System.getProperty("line.separator"));
				System.exit(0);
			}


			if ( transferable )
			{
				// Sets up the WidgetIcon
				GraphicalFunctions.widgetIconSetup(iconButton, Icon);
			}

			iconButton.setSize(Icon.getIconWidth(), Icon.getIconHeight());

			// iconButton.setVerticalTextPosition(AbstractButton.BOTTOM);
			// iconButton.setHorizontalTextPosition(AbstractButton.CENTER);
			iconButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			iconButton.setAlignmentY(Component.TOP_ALIGNMENT);
			// iconButton.setBorder(grayline);
		}

		return iconButton;
	}



	public static void reloadGenericDevicesIcons()
	{
		genericDevicePane.removeAll();


		WidgetIcon[] widIcons = getGenericDevicesIcons();


		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();

		d.fill = GridBagConstraints.BOTH;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		// d.weighty = 1.0; // request any extra vertical space
		d.weightx = 1.0; // request any extra vertical space
		// d.anchor = GridBagConstraints.CENTER; // bottom of space
		d.insets = new Insets(0, 0, 5, 0); // top padding
		// d.gridwidth = 1; // 2 columns wide
		// d.gridheight = 1; // 2 columns wide
		d.gridy = 0;


		for ( int i = 0; i < widIcons.length; i++ )
		{
			d.gridy = i;
			panel.add(widIcons[i], d);
		}


		// Adds the panel with the objects to the group panel
		genericDevicePane.add(panel);


		PrimeMain.tabSelection.repaint();
	}



	/**
	 * This function retrieves an Array of Icons for Generic Devices consisting
	 * of a single "Unknown" icon and then all the custom icons added by the
	 * users.
	 */
	private static WidgetIcon[] getGenericDevicesIcons()
	{
		PrimeMain.desktopProcLog.info("Getting Generic Devices Icons...");
		ArrayList<WidgetIcon> iconsList = new ArrayList<WidgetIcon>();

		if ( showUnknown )
		{
			iconsList.add(makeImageIcon(
					ImageLocator.getImageIconObject("Unknown"),
					GenericDevice.class,
					PrimeMain.texts.getString("selectAreaUnknownDeviceLabel"),
					PrimeMain.texts.getString("selectAreaUnknownDeviceLabel"),
					mouseLis, transferable));
		}

		// Attempts to get all the object within the Objectlist with the class
		// CustomObject.
		try
		{
			Object[] objs = ArrayManagment.getSpesificObjects(
					GenericDevice.class,
					PrimeMain.objectlist.toArray(new Object[1]));

			ImageIcon temp = null;

			if ( objs != null )
			{
				for ( int i = 0; i < objs.length; i++ )
				{
					// Gets the custom Icons for the object
					temp = objs[i].getVisualImage();

					if ( temp != null )
					{
						iconsList.add(makeImageIcon(temp, GenericDevice.class,
								objs[i].getObjectName(), "GenericDevice - "
										+ objs[i].getObjectName(), mouseLis,
								transferable));
					}
				}
			}

			PrimeMain.desktopProcLog
					.info("Generic Devices were found in Standard Devices.");
		}
		catch ( ObjectNotFoundException e )
		{
			PrimeMain.desktopProcLog
					.info("No Generic Devices were found in Standard Devices.");
		}


		WidgetIcon[] widIcons = new WidgetIcon[iconsList.size()];
		iconsList.toArray(widIcons);


		return widIcons;
	}
}
