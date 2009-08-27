/**
 * 
 */
package graphics.GUI.standardObjectEdit;


import graphics.ImageLocator;
import graphics.PrimeMain1;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.Border;

import objects.Clients;
import objects.ExternalHardware;
import objects.Infrastructure;
import objects.Object;
import objects.Servers;
import widgets.WidgetButton;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class StandardObjectSelection extends JPanel
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
	 * TODO - Description
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
	 * TODO - Description
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
	 * TODO - Description
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
	 * TODO - Description
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
	 * TODO - Description
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
			System.out.println("NullPointerException" + " - " + name + "\n\n");
			System.exit(0);
		}
		//
		// Dimension dim = new Dimension(super.getWidth(), Icon.getIconHeight());
		//
		// iconButton.setSize(dim);
		// iconButton.setPreferredSize(dim);
		// iconButton.setMinimumSize(dim);
		//
		iconButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		iconButton.setAlignmentY(Component.TOP_ALIGNMENT);
		// iconButton.setBorder(grayline);

		return iconButton;
	}


	/**
	 * TODO - Description
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
}
