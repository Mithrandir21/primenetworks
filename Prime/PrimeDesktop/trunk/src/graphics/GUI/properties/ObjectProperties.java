/**
 * 
 */
package graphics.GUI.properties;


import graphics.PrimeMain1;
import graphics.GUI.properties.objectTypes.ClientsPropertiesView;
import graphics.GUI.properties.objectTypes.GeneralPropertiesView;
import graphics.GUI.properties.objectTypes.InfrastructuresPropertiesView;
import graphics.GUI.properties.objectTypes.PeripheralsPropertiesView;
import graphics.GUI.properties.objectTypes.ServersPropertiesView;

import java.awt.Button;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logistical.checkLogic;
import managment.CanvasManagment;
import managment.DesktopFileManagment;
import managment.NetworkManagment;
import objects.Clients;
import objects.ExternalHardware;
import objects.Infrastructure;
import objects.Object;
import objects.Servers;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;
import actions.canvasActions.ActionChangeWidgetObjectName;


/**
 * This class will display the properties of any selected {@link WidgetObject} or {@link WorkareaCanvas}.
 * 
 * @author Bahram Malaekeh
 */
public class ObjectProperties extends JPanel implements ActionListener
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
			// d.anchor = GridBagConstraints.CENTER; // location
			d.insets = new Insets(10, 10, 10, 10); // padding
			// d.gridwidth = 1; // 2 row wide
			// d.gridheight = 1; // 2 columns wide
			d.gridy = 0; // row
			d.gridx = 0; // column

			this.add(GeneralPropertiesView.getGeneralCanvasProperties(canvas),
					d);

			JPanel buttons = createButtons();

			d.gridy++; // row
			this.add(buttons, d);
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
			// d.weighty = 1.0; // request any extra vertical space
			d.weightx = 1.0; // request any extra horizontal space
			// d.anchor = GridBagConstraints.CENTER; // location
			d.insets = new Insets(10, 10, 10, 10); // padding
			// d.gridwidth = 1; // 2 row wide
			// d.gridheight = 1; // 2 columns wide
			d.gridy = 0; // row
			d.gridx = 0; // column

			this.add(getStandardProperties(object), d);


			if ( object instanceof Clients )
			{
				d.gridy++; // row
				this.add(showDesktopProperties(object), d);
			}
			else if ( object instanceof Servers )
			{
				this.add(showServerProperties(object), d);
			}
			else if ( object instanceof ExternalHardware )
			{
				this.add(showPeripheralProperties(object), d);
			}
			else if ( object instanceof Infrastructure )
			{
				this.add(showInfrastructurProperties(object), d);
			}



			d.weighty = 1.0; // request any extra vertical space
			d.gridy++; // row
			this.add(createButtons(), d);
		}
		else
		{
			this.removeAll();
		}

	}


	/**
	 * Adds the standard properties which normally is name and description.
	 */
	private JPanel getStandardProperties(Object object)
	{
		return GeneralPropertiesView.getGeneralObjectProperties(object);
	}



	/**
	 * Adds the desktop properties to this JPanel.
	 */
	private JPanel showDesktopProperties(Object object)
	{
		return ClientsPropertiesView.getClientsPropertiesView(object);
	}

	/**
	 * Adds the server properties to this JPanel.
	 */
	private JPanel showServerProperties(Object object)
	{
		return ServersPropertiesView.getServersPropertiesView(object);
	}

	/**
	 * Adds the peripheral properties to this JPanel.
	 */
	private JPanel showPeripheralProperties(Object object)
	{
		return PeripheralsPropertiesView.getPeripheralsPropertiesView(object);
	}

	/**
	 * Adds the infrastructure properties to this JPanel.
	 */
	private JPanel showInfrastructurProperties(Object object)
	{
		return InfrastructuresPropertiesView
				.getInfrastructuresPropertiesView(object);
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
	 * Creates a JPanel with two buttons that are listened for by
	 * actionlisteners.
	 */
	private JPanel createButtons()
	{
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.TRAILING));

		Button save = new Button(PrimeMain1.texts.getString("save"));
		save.addActionListener(this);
		save.setActionCommand(PrimeMain1.texts.getString("save"));


		buttons.add(save);

		return buttons;
	}


	/**
	 * Javadoc-TODO - Description
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
			widObj = CanvasManagment.findWidgetObject(objectViewed,
					PrimeMain1.currentCanvas);
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

						int theRate = Integer.parseInt(rates.getSelectedItem()
								.toString());

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
							// If the name of the currently selected
							// WorkareaCanvas is not the same as the
							// name of the name in the canvasName field.
							if ( !(canvasViewed.getCanvasName()
									.equals(canvasName)) )
							{
								// No canvas was found with the name
								if ( !(DesktopFileManagment
										.fileWorkareaCanvasExist(canvasName)) )
								{
									PrimeMain1.workTab.updateCanvasName(
											canvasViewed, canvasName);
								}
								else
								{
									JOptionPane
											.showMessageDialog(null,
													"There already exist a Network with the name "
															+ "\"" + canvasName
															+ "\".", "Error",
													JOptionPane.ERROR_MESSAGE);

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
							// Sets the local IPrangeStart string that might be
							// used later
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
							// Sets the local IPrangeEnd string that might be
							// used later
							IPrangeEnd = new String(field.getText());
						}
					}
					else if ( compName.equals("Name Object") )
					{
						// Gets the JTextField component
						JTextField field = (JTextField) comp[i];

						// Gets the text inside that field
						String objName = field.getText();

						// If the text is validated
						if ( checkLogic.validateName(objName) )
						{
							WidgetObject foundWidget = CanvasManagment
									.findWidgetObjectByObjectName(objName,
											PrimeMain1.currentCanvas);

							if ( foundWidget == null )
							{
								// Finds the WidgetObject with the given Object
								WidgetObject widgetObj = CanvasManagment
										.findWidgetObject(objectViewed,
												PrimeMain1.canvases);

								// Creates an action
								ActionChangeWidgetObjectName changeNameAction = new ActionChangeWidgetObjectName(
										PrimeMain1.texts
												.getString("actionChangeWidgetNameDescriptionText"),
										widgetObj, objName);

								// Finds the workareacanvas
								WorkareaCanvas tempCanvas = CanvasManagment
										.findCanvas(widgetObj.getScene(),
												PrimeMain1.canvases);

								if ( tempCanvas != null )
								{
									// Performs the action
									changeNameAction.performAction(true);
								}


								// // Updates the name of the LabelWidget on the
								// // scene
								// objectViewed = GraphicalFunctions
								// .updateWidgetObjectCanvasName(
								// objectViewed, objName);
								//
								// // Sets the name of the object
								// objectViewed.setObjectName(objName);
							}
							else
							{
								// If the WidgetObject found contains a
								// different Object
								if ( !(foundWidget.getObject()
										.equals(objectViewed)) )
								{
									JOptionPane
											.showMessageDialog(
													null,
													"There already exists a device in this network with the same name.",
													"Error",
													JOptionPane.ERROR_MESSAGE);

									// Sets the name back to the original name
									field.setText(objectViewed.getObjectName());

									// Focuses on the JTextField
									field.requestFocusInWindow();
								}
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null,
									"The name provided, " + objName
											+ ", was not a valid name.",
									"Error", JOptionPane.ERROR_MESSAGE);

							// Focuses on the JTextField
							field.requestFocusInWindow();
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
					if ( NetworkManagment
							.processRange(IPrangeStart, IPrangeEnd) )
					{
						canvasViewed.getNetworkInfo().setIpRangeFrom(
								IPrangeStart);
						canvasViewed.getNetworkInfo().setIpRangeTo(IPrangeEnd);
					}
				}
				catch ( Exception exp )
				{
					String output = exp.getMessage();

					JOptionPane.showMessageDialog(null, output, "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"You must set the end of the IP range.", "Error",
						JOptionPane.ERROR_MESSAGE);

			}
		}
		else
		{
			if ( !(IPrangeEnd == null) )
			{
				JOptionPane.showMessageDialog(null,
						"You must set the start of the IP range.", "Error",
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
				JOptionPane.showMessageDialog(null, "The IP, " + widgetIP
						+ ", was not valid.", "Error",
						JOptionPane.ERROR_MESSAGE);

				// Updates the WidgetObject properties area
				PrimeMain1.updatePropertiesObjectArea(objectViewed, true);
			}
		}



		if ( objectViewed == null )
		{
			PrimeMain1.updatePropertiesCanvasArea(true);
		}

		PrimeMain1.currentCanvas.cleanUp();
	}


	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals(PrimeMain1.texts.getString("save")) )
		{
			saveAction();
		}
	}
}
