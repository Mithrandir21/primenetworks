/**
 * 
 */
package graphics.GUI.properties.objectTypes;


import graphics.PrimeMain;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import logistical.checkLogic;
import managment.CanvasManagment;
import objects.Object;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;
import actions.canvasActions.ActionChangeWidgetObjectName;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public abstract class AbstractObjectPropertiesView extends JPanel
{
	public static JTextField nameField = new JTextField();

	public static JComboBox subConCombo = new JComboBox();

	public static JTextField numConField = new JTextField();

	public static JTextField numbJumpsField = new JTextField();

	public static JTextField IPfield = new JTextField();

	public static boolean errorDuringSaving = false;


	public static GridBagConstraints d;

	public static Object objectViewed;


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param obj
	 */
	public AbstractObjectPropertiesView(Object obj)
	{
		if ( obj != null )
		{
			objectViewed = obj;
			this.setLayout(new GridBagLayout());
			d = new GridBagConstraints();

			d.fill = GridBagConstraints.HORIZONTAL;
			// d.ipady = 0; // reset to default
			// d.ipadx = 0; // reset to default
			// d.weighty = 1.0; // request any extra vertical space
			d.weightx = 0.8; // request any extra horizontal space
			d.anchor = GridBagConstraints.NORTH; // location
			d.insets = new Insets(0, 0, 5, 0); // padding
			// d.gridwidth = 1; // 2 row wide
			// d.gridheight = 1; // 2 columns wide
			d.gridy = 0; // row
			d.gridx = 0; // column


			this.add(getAbstractCanvasPropertiesView(obj), d);
		}
	}



	/**
	 * Examines the given object. Adds the information about the object name and
	 * different information about the object to the given JPanel.
	 */
	public JPanel getAbstractCanvasPropertiesView(Object obj)
	{
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints panelCons = new GridBagConstraints();

		panelCons.fill = GridBagConstraints.HORIZONTAL;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		// d.weighty = 1.0; // request any extra vertical space
		panelCons.weightx = 0.8; // request any extra horizontal space
		panelCons.anchor = GridBagConstraints.NORTH; // location
		panelCons.insets = new Insets(0, 0, 5, 0); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		panelCons.gridy = 0; // row
		panelCons.gridx = 0; // column



		// Name
		JLabel nameLabel = new JLabel(PrimeMain.texts
				.getString("propGeneralViewObjectNameLabel"),
				SwingConstants.TRAILING);
		nameLabel.setToolTipText(PrimeMain.texts
				.getString("propGeneralViewObjectNameTip"));
		panel.add(nameLabel, panelCons);

		nameField.setText(obj.getObjectName());
		nameField.setName("Name Object");
		panelCons.insets = new Insets(0, 0, 10, 0); // padding
		panelCons.gridy++; // row
		panel.add(nameField, panelCons);


		// Supported connection interfaces
		JLabel supConIntLabel = new JLabel(PrimeMain.texts
				.getString("propGeneralViewSupConIntLabel"),
				SwingConstants.TRAILING);
		supConIntLabel.setToolTipText(PrimeMain.texts
				.getString("propGeneralViewSupConIntTip"));
		panelCons.insets = new Insets(0, 0, 5, 0); // padding
		panelCons.gridy++; // row
		panel.add(supConIntLabel, panelCons);


		subConCombo.setModel(new DefaultComboBoxModel(obj
				.getSupportedConnectionInterfaces()));
		subConCombo.setEditable(false);
		subConCombo.setName("supConInt");
		panelCons.insets = new Insets(0, 0, 10, 0); // padding
		panelCons.gridy++; // row
		panel.add(subConCombo, panelCons);



		// Number of components
		JLabel numConLabel = new JLabel(PrimeMain.texts
				.getString("propGeneralViewNumConDevicesLabel"),
				SwingConstants.TRAILING);
		numConLabel.setToolTipText(PrimeMain.texts
				.getString("propGeneralViewNumConDevicesTip"));
		panelCons.insets = new Insets(0, 0, 5, 0); // padding
		panelCons.gridy++; // row
		panel.add(numConLabel, panelCons);

		numConField.setText(Integer
				.toString((obj.getNumberOfConnectedDevices())));
		numConField.setEditable(false);
		numConField.setName("numCon");
		panelCons.insets = new Insets(0, 0, 10, 0); // padding
		panelCons.gridy++; // row
		panel.add(numConField, panelCons);


		// Number of nodes
		JLabel numbJumpsLabel = new JLabel(PrimeMain.texts
				.getString("propGeneralViewNumJumpsLabel"),
				SwingConstants.TRAILING);
		numbJumpsLabel.setToolTipText(PrimeMain.texts
				.getString("propGeneralViewNumJumpsTip"));
		panelCons.insets = new Insets(0, 0, 5, 0); // padding
		panelCons.gridy++; // row
		panel.add(numbJumpsLabel, panelCons);

		numbJumpsField.setText("0");
		numbJumpsField.setEditable(false);
		numbJumpsField.setName("numbJumps");
		panelCons.insets = new Insets(0, 0, 10, 0); // padding
		panelCons.gridy++; // row
		panel.add(numbJumpsField, panelCons);



		// Gets the WidgetObject so that the IP address can be added
		WidgetObject widObj = CanvasManagment.findWidgetObject(obj,
				PrimeMain.currentCanvas);

		// If not WidgetObject is found
		if ( widObj != null )
		{
			// The IP of the object
			JLabel IPLabel = new JLabel(PrimeMain.texts
					.getString("propGeneralViewDeviceIPLabel"),
					SwingConstants.TRAILING);
			IPLabel.setToolTipText(PrimeMain.texts
					.getString("propGeneralViewDeviceIPTip"));
			panelCons.insets = new Insets(0, 0, 5, 0); // padding
			panelCons.gridy++; // row
			panel.add(IPLabel, panelCons);


			IPfield.setName("Object IP");
			String ipFrom = widObj.getWidgetNetworkInfo().getIp();
			if ( !(ipFrom == null) )
			{
				IPfield.setText(ipFrom);
			}

			panelCons.insets = new Insets(0, 0, 10, 0); // padding
			panelCons.gridy++; // row
			panel.add(IPfield, panelCons);
		}


		return panel;
	}


	/**
	 * Resets all general fields to the info gotten from the {@link Object} viewed.
	 * It also resets the errorDuringSaving to false.
	 */
	public void resetGeneralFields()
	{
		nameField.setText(objectViewed.getObjectName());

		subConCombo.setSelectedIndex(0);

		numConField.setText(Integer.toString((objectViewed
				.getNumberOfConnectedDevices())));

		numbJumpsField.setText("0");

		// Gets the WidgetObject so that the IP address can be added
		WidgetObject widObj = CanvasManagment.findWidgetObject(objectViewed,
				PrimeMain.currentCanvas);
		IPfield.setText(widObj.getWidgetNetworkInfo().getIp());

		errorDuringSaving = false;
	}


	/**
	 * Javadoc-TODO - Description
	 */
	public void generalSaveAction()
	{
		if ( objectViewed != null )
		{
			// Gets the text inside that field
			String objName = nameField.getText();

			// If the text is validated
			if ( checkLogic.validateName(objName) )
			{
				WidgetObject foundWidget = CanvasManagment
						.findWidgetObjectByObjectName(objName,
								PrimeMain.currentCanvas);

				if ( foundWidget == null )
				{
					// Finds the WidgetObject with the given Object
					WidgetObject widgetObj = CanvasManagment.findWidgetObject(
							objectViewed, PrimeMain.canvases);

					// Creates an action
					ActionChangeWidgetObjectName changeNameAction = new ActionChangeWidgetObjectName(
							PrimeMain.texts
									.getString("actionChangeWidgetNameDescriptionText"),
							widgetObj, objName);

					// Finds the workareacanvas
					WorkareaCanvas tempCanvas = CanvasManagment.findCanvas(
							widgetObj.getScene(), PrimeMain.canvases);

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
					// If the WidgetObject found contains a different Object
					if ( !(foundWidget.getObject().equals(objectViewed)) )
					{
						JOptionPane.showMessageDialog(null, PrimeMain.texts
								.getString("objectNameAlreadyExistsMsg"),
								PrimeMain.texts.getString("error"),
								JOptionPane.ERROR_MESSAGE);

						// Focuses on the JTextField
						nameField.requestFocusInWindow();

						errorDuringSaving = true;
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, PrimeMain.texts
						.getString("actionChangeWidgetNameInvalidNameText"),
						PrimeMain.texts.getString("error"),
						JOptionPane.ERROR_MESSAGE);

				// Focuses on the JTextField
				nameField.requestFocusInWindow();

				errorDuringSaving = true;
			}


			WidgetObject widObj = null;

			if ( objectViewed != null )
			{
				// Gets the WidgetObject so that the IP address can be added
				widObj = CanvasManagment.findWidgetObject(objectViewed,
						PrimeMain.currentCanvas);
			}

			if ( widObj != null )
			{
				// Gets the text inside that field
				String IP = IPfield.getText();

				if ( !(widObj.getWidgetNetworkInfo().setIp(IP)) )
				{
					JOptionPane.showMessageDialog(null, PrimeMain.texts
							.getString("saveNetworkNotValidIPerrorMsg"),
							PrimeMain.texts.getString("error"),
							JOptionPane.ERROR_MESSAGE);

					// Updates the WidgetObject properties area
					PrimeMain.updatePropertiesObjectArea(objectViewed, true);

					errorDuringSaving = true;
				}
			}
		}
	}
}
