/**
 * 
 */
package graphics.GUI.properties;


import graphics.PrimeMain;
import graphics.GUI.properties.objectTypes.CanvasPropertiesView;
import graphics.GUI.properties.objectTypes.ClientsPropertiesView;
import graphics.GUI.properties.objectTypes.InfrastructuresPropertiesView;
import graphics.GUI.properties.objectTypes.PeripheralsPropertiesView;
import graphics.GUI.properties.objectTypes.ServersPropertiesView;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import objects.Clients;
import objects.ExternalHardware;
import objects.Infrastructure;
import objects.Object;
import objects.Servers;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;


/**
 * This class will display the properties of any selected {@link WidgetObject} or {@link WorkareaCanvas}.
 * 
 * @author Bahram Malaekeh
 */
public class ObjectProperties extends JPanel
{
	private Object objectViewed = null;

	private WorkareaCanvas canvasViewed = null;

	/**
	 * A class constructor that takes a WorkareaCanvas and creates places
	 * information about that canvas on to this JPanel.
	 * 
	 * @param canvas
	 */
	public ObjectProperties(WorkareaCanvas canvas)
	{
		if ( canvas != null )
		{
			canvasViewed = canvas;

			this.setLayout(new GridBagLayout());
			GridBagConstraints d = new GridBagConstraints();

			d.fill = GridBagConstraints.BOTH;
			// d.ipady = 0; // reset to default
			// d.ipadx = 0; // reset to default
			d.weighty = 1.0; // request any extra vertical space
			d.weightx = 1.0; // request any extra horizontal space
			d.anchor = GridBagConstraints.NORTH; // location
			d.insets = new Insets(10, 10, 10, 10); // padding
			// d.gridwidth = 1; // 2 row wide
			// d.gridheight = 1; // 2 columns wide
			d.gridy = 0; // row
			d.gridx = 0; // column

			this.add(new CanvasPropertiesView(canvas), d);
		}
		else
		{
			this.removeAll();
		}
	}


	/**
	 * A constructor for the class that takes the given Object and places
	 * information about that object on this JPanel. The information depends on
	 * what kind of class the given object is.
	 * 
	 * @param object
	 */
	public ObjectProperties(Object object)
	{
		if ( object != null )
		{
			objectViewed = object;

			this.setLayout(new GridBagLayout());
			GridBagConstraints d = new GridBagConstraints();

			d.fill = GridBagConstraints.BOTH;
			// d.ipady = 0; // reset to default
			// d.ipadx = 0; // reset to default
			d.weighty = 1.0; // request any extra vertical space
			d.weightx = 1.0; // request any extra horizontal space
			d.anchor = GridBagConstraints.NORTH; // location
			d.insets = new Insets(10, 10, 10, 10); // padding
			// d.gridwidth = 1; // 2 row wide
			// d.gridheight = 1; // 2 columns wide
			d.gridy = 0; // row
			d.gridx = 0; // column


			if ( object instanceof Clients )
			{
				// Adds the client properties to this JPanel.
				this.add(new ClientsPropertiesView(object), d);
			}
			else if ( object instanceof Servers )
			{
				// Adds the server properties to this JPanel.
				this.add(new ServersPropertiesView(object), d);
			}
			else if ( object instanceof ExternalHardware )
			{
				// Adds the peripheral properties to this JPanel.
				this.add(new PeripheralsPropertiesView(object), d);
			}
			else if ( object instanceof Infrastructure )
			{
				// Adds the infrastructure properties to this JPanel.
				this.add(new InfrastructuresPropertiesView(object), d);
			}
		}
		else
		{
			this.removeAll();
		}

	}


	/**
	 * Gets the {@link Object} viewed.
	 */
	public Object getObjectViewed()
	{
		return objectViewed;
	}



	/**
	 * Gets the {@link WorkareaCanvas} viewed.
	 */
	public WorkareaCanvas getCanvasViewed()
	{
		return canvasViewed;
	}



	/**
	 * Creates a JPanel with two buttons that are listened for by the given actionlisteners.
	 */
	public static JPanel createButtons(ActionListener lis)
	{
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.TRAILING));

		Button reset = new Button(PrimeMain.texts.getString("reset"));
		reset.addActionListener(lis);
		reset.setActionCommand(PrimeMain.texts.getString("reset"));

		Button save = new Button(PrimeMain.texts.getString("save"));
		save.addActionListener(lis);
		save.setActionCommand(PrimeMain.texts.getString("save"));

		buttons.add(reset);
		buttons.add(save);

		return buttons;
	}
}
