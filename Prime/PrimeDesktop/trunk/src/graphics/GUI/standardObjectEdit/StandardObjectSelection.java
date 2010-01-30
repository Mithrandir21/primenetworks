/**
 * 
 */
package graphics.GUI.standardObjectEdit;


import exceptions.ObjectNotFoundException;
import graphics.PrimeMain1;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.Border;

import managment.ArrayManagment;
import objects.Object;
import objects.clientObjects.Desktop;
import objects.clientObjects.Laptop;
import objects.clientObjects.ThinClient;
import objects.infrastructureObjects.Hub;
import objects.infrastructureObjects.Internet;
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
import widgets.WidgetButton;


/**
 * This extended JPnel class will contain {@link WidgetButton WidgetButtons} of
 * all the systems standard object.
 * 
 * @author Bahram Malaekeh
 */
public class StandardObjectSelection extends JPanel implements ActionListener
{

	// A simple border that is gray
	Border grayline = BorderFactory.createLineBorder(Color.GRAY);


	/**
	 * TODO - Description NEEDED!
	 */
	public StandardObjectSelection()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(grayline);

		initClientButtonIcons();

		initServerButtonIcons();

		initExternalHardwareButtonIcons();

		initInfrastructureButtonIcons();

		// this.setPreferredSize(new Dimension(290, getCompSize()));
	}

	private void initClientButtonIcons()
	{
		this.add(Box.createVerticalStrut(10));

		this.add(makeImageIcon(Desktop.class));

		this.add(Box.createVerticalStrut(5));

		this.add(makeImageIcon(Laptop.class));

		this.add(Box.createVerticalStrut(5));

		this.add(makeImageIcon(ThinClient.class));

		this.add(Box.createVerticalStrut(15));


		this.add(new JSeparator());
	}

	private void initServerButtonIcons()
	{
		this.add(Box.createVerticalStrut(10));

		this.add(makeImageIcon(HTTPServer.class));

		this.add(Box.createVerticalStrut(5));

		this.add(makeImageIcon(BackupServer.class));

		this.add(Box.createVerticalStrut(5));

		this.add(makeImageIcon(DatabaseServer.class));

		this.add(Box.createVerticalStrut(5));

		this.add(makeImageIcon(MailServer.class));

		this.add(Box.createVerticalStrut(5));

		this.add(makeImageIcon(FirewallServer.class));

		this.add(Box.createVerticalStrut(5));

		this.add(makeImageIcon(ProxyServer.class));

		this.add(Box.createVerticalStrut(5));

		this.add(makeImageIcon(PrinterServer.class));

		this.add(Box.createVerticalStrut(15));


		this.add(new JSeparator());
	}

	private void initExternalHardwareButtonIcons()
	{
		this.add(Box.createVerticalStrut(10));

		this.add(makeImageIcon(Scanner.class));

		this.add(Box.createVerticalStrut(5));

		this.add(makeImageIcon(Printer.class));

		this.add(Box.createVerticalStrut(5));

		this.add(makeImageIcon(Fax.class));

		this.add(Box.createVerticalStrut(5));

		this.add(makeImageIcon(MultifunctionPrinter.class));

		this.add(Box.createVerticalStrut(5));

		this.add(makeImageIcon(NetworkPrinter.class));

		this.add(Box.createVerticalStrut(5));

		this.add(makeImageIcon(NetworkMultifunctionPrinter.class));

		this.add(Box.createVerticalStrut(15));


		this.add(new JSeparator());
	}


	private void initInfrastructureButtonIcons()
	{
		this.add(Box.createVerticalStrut(10));

		this.add(makeImageIcon(Hub.class));

		this.add(Box.createVerticalStrut(5));

		this.add(makeImageIcon(Switch.class));

		this.add(Box.createVerticalStrut(5));

		this.add(makeImageIcon(Router.class));

		this.add(Box.createVerticalStrut(5));

		this.add(makeImageIcon(WirelessRouter.class));

		this.add(Box.createVerticalStrut(5));

		this.add(makeImageIcon(Internet.class));

		this.add(Box.createVerticalStrut(15));
	}





	/**
	 * This function creates a {@link WidgetButton} to be placed to represent a
	 * standard {@link Object}.
	 */
	@SuppressWarnings("unchecked")
	private WidgetButton makeImageIcon(Class objectType)
	{
		ImageIcon Icon = PrimeMain1.objectImageIcons.get(objectType);

		WidgetButton iconButton = null;


		try
		{
			iconButton = new WidgetButton(Icon, objectType, objectType
					.getSimpleName());
		}
		catch ( Exception e )
		{
			System.out.println("NullPointerException - StandardObjectSelection"
					+ " - " + objectType + "\n\n");
			System.exit(0);
		}

		// iconButton.setVerticalTextPosition(AbstractButton.BOTTOM);
		// iconButton.setHorizontalTextPosition(AbstractButton.CENTER);
		iconButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		iconButton.setAlignmentY(Component.TOP_ALIGNMENT);

		iconButton.addActionListener(this);

		return iconButton;
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


	/**
	 * This function checks whether or not the second parameter(givenClass) is a
	 * subclass of the first parameter(rightClass).
	 * 
	 * @return Returns a boolean on whether or not the second parameter(Class)
	 *         is a subclass of the first parameter.
	 */
	@SuppressWarnings("unchecked")
	public boolean superClassCheck(Class rightClass, Class givenClass)
	{
		Class currentClass = givenClass;

		// While the current class is not the top abstract class, Object
		while ( currentClass != Object.class )
		{
			// If the current class i the right class
			if ( currentClass.equals(rightClass) )
			{
				return true;
			}
			// If not, the current will become the classes super c
			else
			{
				currentClass = currentClass.getSuperclass();
			}
		}

		return false;
	}


	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event)
	{
		WidgetButton button = (WidgetButton) event.getSource();

		Object newObject = null;

		try
		{
			// Gets the object in the given ArrayList with the given class
			newObject = ArrayManagment.getSpesificComponent(button
					.getClassType(), PrimeMain1.objectlist);
		}
		catch ( ObjectNotFoundException e )
		{
			System.out.println(PrimeMain1.texts
					.getString("standardObjectsNotFoundInArrayMsg")
					+ " - " + button.getName());
			e.printStackTrace();
		}

		// Changes the object currently viewed
		PrimeMain1.stdObjView.getSplitView().getHardStdObjView()
				.changeObjectView(newObject);
	}
}
