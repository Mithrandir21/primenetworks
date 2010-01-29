/**
 * 
 */
package graphics.GUI.properties;


import graphics.GraphicalFunctions;
import graphics.PrimeMain1;
import graphics.GUI.properties.objectTypes.ClientsPropertiesView;
import graphics.GUI.properties.objectTypes.GeneralPropertiesView;
import graphics.GUI.properties.objectTypes.InfrastructuresPropertiesView;
import graphics.GUI.properties.objectTypes.PeripheralsPropertiesView;
import graphics.GUI.properties.objectTypes.ServersPropertiesView;
import graphics.GUI.workareaCanvas.WorkareaCanvas;

import java.awt.Button;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import managment.CanvasManagment;
import managment.FileManagment;
import managment.NetworkManagment;
import objects.Clients;
import objects.Object;
import objects.Servers;
import widgetManipulation.WidgetObject;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class ObjectProperties extends JPanel implements ActionListener
{
	private Object objectViewed = null;

	private WorkareaCanvas canvasViewed = null;

	/**
	 * A class constructor that takes a WorkareaCanvas and creates places information about that canvas on to this
	 * JPanel.
	 * 
	 * @param canvas
	 */
	public ObjectProperties(WorkareaCanvas canvas)
	{
		if ( canvas != null )
		{
			canvasViewed = canvas;

			this.setLayout(new SpringLayout());

			GeneralPropertiesView.getGeneralCanvasProperties(this, canvas);

			JPanel buttons = createButtons();

			this.add(buttons);


			// Lay out the panel.
			graphics.GraphicalFunctions.make1xGrid(this, this.getComponentCount(), // rows
					// ,
					// cols
					6, 6, // initX, initY
					6, 6); // xPad, yPad
		}
		else
		{
			this.removeAll();
		}
	}



	/**
	 * A constructor for the class that takes the given Object and places information about that object on this JPanel.
	 * The information depends on what kind of class the given object is.
	 * 
	 * @param object
	 */
	public ObjectProperties(Object object)
	{
		if ( object != null )
		{
			objectViewed = object;

			this.setLayout(new SpringLayout());

			showStandardProperties(object);

			if ( object instanceof objects.clientObjects.Desktop || object instanceof objects.clientObjects.Laptop )
			{
				showDesktopProperties(object);
			}
			else if ( object instanceof objects.serverObjects.HTTPServer
					|| object instanceof objects.serverObjects.BackupServer
					|| object instanceof objects.serverObjects.MailServer
					|| object instanceof objects.serverObjects.FirewallServer
					|| object instanceof objects.serverObjects.ProxyServer )
			{
				showServerProperties(object);
			}
			else if ( object instanceof objects.hardwareObjects.HDD )
			{
			}
			else if ( object instanceof objects.peripheralObjects.Scanner )
			{
				showPeripheralProperties(object);
			}
			else if ( object instanceof objects.infrastructureObjects.Hub
					|| object instanceof objects.infrastructureObjects.Switch
					|| object instanceof objects.infrastructureObjects.Router )
			{
				showInfrastructurProperties(object);
			}


			JPanel buttons = createButtons();

			this.add(buttons);

			// this.setComponentZOrder(buttons, 0);

			// Lay out the panel.
			graphics.GraphicalFunctions.make1xGrid(this, this.getComponentCount(), // rows
					// ,
					// cols
					6, 6, // initX, initY
					6, 6); // xPad, yPad
		}
		else
		{
			this.removeAll();
		}

	}


	/**
	 * Adds the standard properties which normally is name and description.
	 */
	private void showStandardProperties(Object object)
	{
		GeneralPropertiesView.getGeneralObjectProperties(this, object);
	}



	/**
	 * Adds the desktop properties to this JPanel.
	 */
	private void showDesktopProperties(Object object)
	{
		ClientsPropertiesView.getClientsPropertiesView(this, object);
	}

	/**
	 * Adds the server properties to this JPanel.
	 */
	private void showServerProperties(Object object)
	{
		ServersPropertiesView.getServersPropertiesView(this, object);
	}

	/**
	 * Adds the peripheral properties to this JPanel.
	 */
	private void showPeripheralProperties(Object object)
	{
		PeripheralsPropertiesView.getPeripheralsPropertiesView(this, object);
	}

	/**
	 * Adds the infrastructure properties to this JPanel.
	 */
	private void showInfrastructurProperties(Object object)
	{
		InfrastructuresPropertiesView.getInfrastructuresPropertiesView(this, object);
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
	 * Creates a JPanel with two buttons that are listened for by actionlisteners.
	 */
	private JPanel createButtons()
	{
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.TRAILING));

		Button save = new Button("Save");
		save.addActionListener(this);
		save.setActionCommand("save");


		buttons.add(save);

		return buttons;
	}


	/**
	 * Javadoc-TODO - Description
	 * 
	 */
	public void saveAction()
	{
		Component[] comp = this.getComponents();

		// The IP range for a WorkareaCanvas
		String IPrangeStart = null;
		String IPrangeEnd = null;

		// The IP for a WidgetObject
		String widgetIP = null;

		WidgetObject widObj = null;

		if ( objectViewed != null )
		{
			// Gets the WidgetObject so that the IP address can be added
			widObj = CanvasManagment.findWidgetObject(objectViewed, PrimeMain1.currentCanvas);
		}



		for ( int i = 0; i < comp.length; i++ )
		{
			String compName = comp[i].getName();

			if ( compName != null )
			{
				if ( comp[i] instanceof JComboBox )
				{
					if ( compName.equals("Client Rates") )
					{
						Clients clientObj = (Clients) objectViewed;

						JComboBox rates = (JComboBox) comp[i];

						int theRate = Integer.parseInt(rates.getSelectedItem().toString());

						clientObj.setClientRate(theRate);
					}
					else if ( compName.equals("Netmask") )
					{
						// Casts the component to a JComboBox
						JComboBox netmask = (JComboBox) comp[i];

						// Casts the selected index to a String
						String selected = netmask.getSelectedItem().toString();

						// If the selected index is not ""
						if ( !(selected.equals("")) )
						{
							canvasViewed.getNetworkInfo().setNetmask(selected);
						}
					}
				}
				else if ( comp[i] instanceof JTextField )
				{
					if ( compName.equals("Name_Canvas") )
					{
						JTextField field = (JTextField) comp[i];

						String canvasName = field.getText();
						if ( !(canvasName.equals("")) )
						{
							// If the name of the currently selected WorkareaCanvas is not the same as the
							// name of the name in the canvasName field.
							if ( !(canvasViewed.getCanvasName().equals(canvasName)) )
							{
								// No canvas was found with the name
								if ( !(FileManagment.fileWorkareaCanvasExist(canvasViewed, canvasName)) )
								{
									PrimeMain1.workTab.updateCanvasName(canvasViewed, canvasName);
								}
								else
								{
									JOptionPane.showMessageDialog(null, "There already exist a Network with the name "
											+ "\"" + canvasName + "\".", "Error", JOptionPane.ERROR_MESSAGE);

									field.setText(canvasViewed.getCanvasName());
								}
							}
						}
					}
					else if ( compName.equals("IP range start") )
					{
						// Casts the component to a JTextField
						JTextField field = (JTextField) comp[i];

						// If the text in the JTextField is not ""
						if ( !(field.getText().equals("")) )
						{
							// Sets the local IPrangeStart string that might be used later
							IPrangeStart = new String(field.getText());
						}
					}
					else if ( compName.equals("IP range end") )
					{
						// Casts the component to a JTextField
						JTextField field = (JTextField) comp[i];

						// If the text in the JTextField is not ""
						if ( !(field.getText().equals("")) )
						{
							// Sets the local IPrangeEnd string that might be used later
							IPrangeEnd = new String(field.getText());
						}
					}
					else if ( compName.equals("Name Object") )
					{
						// Gets the JTextField component
						JTextField field = (JTextField) comp[i];

						// Gets the text inside that field
						String objName = field.getText();

						// If the text is not blank
						if ( !(objName.equals("")) )
						{
							// Updates the name of the LabelWidget on the
							// scene
							objectViewed = GraphicalFunctions.updateWidgetObjectCanvasName(objectViewed, objName);

							// Sets the name of the object
							objectViewed.setObjectName(objName);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "You must specify a name for this Object.", "Error",
									JOptionPane.ERROR_MESSAGE);

							// Focuses on the JTextField
							comp[i].requestFocusInWindow();
						}
					}
					else if ( compName.equals("Object IP") )
					{
						// Gets the JTextField component
						JTextField field = (JTextField) comp[i];

						// Gets the text inside that field
						String IP = field.getText();

						// If the text in the JTextField is not ""
						if ( !(IP.equals("")) )
						{
							widgetIP = IP;
						}
					}
					else if ( compName.equals("supConInt") )
					{
						// FIXME - ObjectProperties - supConInt
					}
					else if ( compName.equals("supRemoteAccProto") )
					{
						// FIXME - ObjectProperties - supRemoteAccProto
					}
					else if ( compName.equals("Main SW Name") )
					{
						// FIXME - ObjectProperties - Main SW Name
					}
				}
				else if ( comp[i] instanceof JCheckBox )
				{
					if ( compName.equals("supOnSiteAccess") )
					{
						JCheckBox box = (JCheckBox) comp[i];

						Servers server = (Servers) objectViewed;

						server.setSupportsOnSiteAccess(box.isSelected());
					}
					else if ( compName.equals("supRemoteAccess") )
					{
						JCheckBox box = (JCheckBox) comp[i];

						Servers server = (Servers) objectViewed;

						server.setSupportsRemoteAccess(box.isSelected());
					}
				}
			}
		}


		// Testing and setting of the IP range
		// If none of the strings a null
		if ( !(IPrangeStart == null) )
		{
			if ( !(IPrangeEnd == null) )
			{
				// Checks whether or not the range between the two is valid
				try
				{
					if ( NetworkManagment.processRange(IPrangeStart, IPrangeEnd) )
					{
						canvasViewed.getNetworkInfo().setIpRangeFrom(IPrangeStart);
						canvasViewed.getNetworkInfo().setIpRangeTo(IPrangeEnd);
					}
				}
				catch ( Exception exp )
				{
					String output = exp.getMessage();

					JOptionPane.showMessageDialog(null, output, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "You must set the end of the IP range.", "Error",
						JOptionPane.ERROR_MESSAGE);

			}
		}
		else
		{
			if ( !(IPrangeEnd == null) )
			{
				JOptionPane.showMessageDialog(null, "You must set the start of the IP range.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}


		// Setting the IP of the WidgetObject
		if ( widObj != null && widgetIP != null )
		{
			// True if the IP was valid and set
			boolean set = widObj.getWidgetNetworkInfo().setIp(widgetIP);

			// If the IP was not set
			if ( !set )
			{
				JOptionPane.showMessageDialog(null, "The IP, " + widgetIP + ", was not valid.", "Error",
						JOptionPane.ERROR_MESSAGE);

				// Updates the WidgetObject properties area
				PrimeMain1.updatePropertiesObjectArea(objectViewed, true);
			}
		}



		if ( objectViewed == null )
		{
			PrimeMain1.updatePropertiesCanvasArea(true);
		}


	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals("save") )
		{
			saveAction();
		}
	}
}
