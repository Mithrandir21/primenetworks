/**
 * 
 */
package graphics.GUI.standardObjectEdit;


import exceptions.ObjectNotFoundException;
import graphics.ImageLocator;
import graphics.PrimeMain1;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.Border;

import managment.ArrayManagment;
import objects.Clients;
import objects.ExternalHardware;
import objects.Infrastructure;
import objects.Object;
import objects.Servers;
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
	 * A constructor that calls all the initiation function this class holds to
	 * set up the different standard object buttons.
	 */
	public StandardObjectSelection()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(grayline);

		// this.setSize(new Dimension(290, 450));
		// this.setPreferredSize(new Dimension(290, 450));
		// this.setMinimumSize(new Dimension(290, 450));

		intiClients();

		this.add(new JSeparator());

		initServers();

		this.add(new JSeparator());

		initExternalHardware();

		this.add(new JSeparator());

		initInfrastructure();
	}


	/**
	 * Sets up {@link WidgetButton WidgetButtons} for sub classes of
	 * {@link Clients}.
	 */
	private void intiClients()
	{
		for ( int i = 0; i < PrimeMain1.objectlist.size(); i++ )
		{
			if ( superClassCheck(Clients.class, PrimeMain1.objectlist.get(i)
					.getClass()) )
			{
				this.add(makeImageIcon(PrimeMain1.objectlist.get(i)
						.getObjectName(), PrimeMain1.objectlist.get(i)
						.getClass(), PrimeMain1.objectlist.get(i)
						.getObjectName()));
			}
		}
	}



	/**
	 * Sets up {@link WidgetButton WidgetButtons} for sub classes of
	 * {@link Servers}.
	 */
	private void initServers()
	{
		for ( int i = 0; i < PrimeMain1.objectlist.size(); i++ )
		{
			if ( superClassCheck(Servers.class, PrimeMain1.objectlist.get(i)
					.getClass()) )
			{
				this.add(makeImageIcon(PrimeMain1.objectlist.get(i)
						.getObjectName(), PrimeMain1.objectlist.get(i)
						.getClass(), PrimeMain1.objectlist.get(i)
						.getObjectName()));
			}
		}
	}



	/**
	 * Sets up {@link WidgetButton WidgetButtons} for sub classes of
	 * {@link ExternalHardware}.
	 */
	private void initExternalHardware()
	{
		for ( int i = 0; i < PrimeMain1.objectlist.size(); i++ )
		{
			if ( superClassCheck(ExternalHardware.class, PrimeMain1.objectlist
					.get(i).getClass()) )
			{
				// System.out
				// .println(PrimeMain1.objectlist.get(i).getObjectName());
				this.add(makeImageIcon(PrimeMain1.objectlist.get(i)
						.getObjectName(), PrimeMain1.objectlist.get(i)
						.getClass(), PrimeMain1.objectlist.get(i)
						.getObjectName()));
			}
		}
	}


	/**
	 * Sets up {@link WidgetButton WidgetButtons} for sub classes of
	 * {@link Infrastructure}.
	 */
	private void initInfrastructure()
	{
		for ( int i = 0; i < PrimeMain1.objectlist.size(); i++ )
		{
			if ( superClassCheck(Infrastructure.class, PrimeMain1.objectlist
					.get(i).getClass()) )
			{
				this.add(makeImageIcon(PrimeMain1.objectlist.get(i)
						.getObjectName(), PrimeMain1.objectlist.get(i)
						.getClass(), PrimeMain1.objectlist.get(i)
						.getObjectName()));
			}
		}
	}







	/**
	 * This function creates a {@link WidgetButton} to be placed to represent a
	 * standard {@link Object}.
	 */
	@SuppressWarnings("unchecked")
	private WidgetButton makeImageIcon(String name, Class objectType,
			String text)
	{
		ImageIcon Icon = ImageLocator.getImageIconObject(name);

		WidgetButton iconButton = null;

		try
		{
			iconButton = new WidgetButton(Icon, objectType, text);
		}
		catch ( Exception e )
		{
			System.out.println("NullPointerException - StandardObjectSelection"
					+ " - " + name + "\n\n");
			System.exit(0);
		}
		//
		// Dimension dim = new Dimension(super.getWidth(),
		// Icon.getIconHeight());
		//
		// iconButton.setSize(dim);
		// iconButton.setPreferredSize(dim);
		// iconButton.setMinimumSize(dim);
		//
		iconButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		iconButton.setAlignmentY(Component.TOP_ALIGNMENT);
		// iconButton.setBorder(grayline);

		iconButton.addActionListener(this);

		return iconButton;
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
