/**
 * 
 */
package graphics.GUI.properties.objectTypes;


import graphics.GraphicalFunctions;
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

import managment.DesktopFileManagment;
import managment.NetworkManagment;
import objects.Object;
import widgets.WorkareaCanvas;


/**
 * This class creates a properties JPanel containing general information about a
 * given {@link WorkareaCanvas} or given {@link Object}.
 * 
 * @author Bahram Malaekeh
 */
public class AbstractCanvasPropertiesView extends JPanel
{
	/**
	 * 
	 */
	public static JTextField nameField = new JTextField();

	/**
	 * 
	 */
	public static JTextField objectCountField = new JTextField();

	/**
	 * 
	 */
	public static JComboBox netmaskCombo = new JComboBox();

	/**
	 * 
	 */
	public static JTextField IPrangeStartField = new JTextField();

	/**
	 * 
	 */
	public static JTextField IPrangeEndField = new JTextField();

	/**
	 * 
	 */
	public static boolean errorDuringSaving = false;

	/**
	 * 
	 */
	public static GridBagConstraints d;


	public static WorkareaCanvas canvasViewed;



	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param canvas
	 */
	public AbstractCanvasPropertiesView(WorkareaCanvas canvas)
	{
		if ( canvas != null )
		{
			canvasViewed = canvas;

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


			this.add(getGeneralCanvasProperties(canvas), d);
		}
	}


	/**
	 * Examines the given WorkareaCanvas and adds the canvas name and the number
	 * of objects on the given JPanel.
	 */
	public static JPanel getGeneralCanvasProperties(WorkareaCanvas canvas)
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
				.getString("propGeneralViewCanvasNameLabel"),
				SwingConstants.TRAILING);
		nameLabel.setToolTipText(PrimeMain.texts
				.getString("propGeneralViewCanvasNameTip"));
		panel.add(nameLabel, panelCons);


		nameField.setText(canvas.getCanvasName());
		// nameLabel.setLabelFor(nameField);
		nameField.setName("Name_Canvas");
		panelCons.insets = new Insets(0, 0, 10, 0); // padding
		panelCons.gridy++; // row
		panel.add(nameField, panelCons);



		// The number of objects on the network map.
		JLabel objectCountLabel = new JLabel(PrimeMain.texts
				.getString("propGeneralViewObjectsCountLabel"),
				SwingConstants.TRAILING);
		objectCountLabel.setToolTipText(PrimeMain.texts
				.getString("propGeneralViewObjectsCountTip"));
		panelCons.insets = new Insets(0, 0, 5, 0); // padding
		panelCons.gridy++; // row
		panel.add(objectCountLabel, panelCons);

		objectCountField.setText("" + canvas.getNumberOfWidgetsOnTheScene());
		objectCountField.setEditable(false);
		panelCons.insets = new Insets(0, 0, 10, 0); // padding
		panelCons.gridy++; // row
		panel.add(objectCountField, panelCons);


		// The IP Mask of the Network
		JLabel netmaskLabel = new JLabel(PrimeMain.texts
				.getString("propGeneralViewNetmaskLabel"),
				SwingConstants.TRAILING);
		netmaskLabel.setToolTipText(PrimeMain.texts
				.getString("propGeneralViewNetmaskTip"));
		panelCons.insets = new Insets(0, 0, 5, 0); // padding
		panelCons.gridy++; // row
		panel.add(netmaskLabel, panelCons);


		netmaskCombo.setName("Netmask");
		String[] netmasks = new String[] { "", "255.255.255.0", "255.255.0.0",
				"255.0.0.0" };
		netmaskCombo.setModel(new DefaultComboBoxModel(netmasks));
		netmaskCombo.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				netmasks, canvas.getNetworkInfo().getNetmask()));
		panelCons.insets = new Insets(0, 0, 10, 0); // padding
		panelCons.gridy++; // row
		panel.add(netmaskCombo, panelCons);


		// The start of the IP range
		JLabel IPrangeStartLabel = new JLabel(PrimeMain.texts
				.getString("propGeneralViewIPStartsLabel"),
				SwingConstants.TRAILING);
		IPrangeStartLabel.setToolTipText(PrimeMain.texts
				.getString("propGeneralViewIPStartsTip"));
		panelCons.insets = new Insets(0, 0, 5, 0); // padding
		panelCons.gridy++; // row
		panel.add(IPrangeStartLabel, panelCons);


		IPrangeStartField.setName("IP range start");
		String ipFrom = canvas.getNetworkInfo().getIpRangeFrom();
		if ( !(ipFrom == null) )
		{
			IPrangeStartField.setText(ipFrom);
		}

		panelCons.insets = new Insets(0, 0, 10, 0); // padding
		panelCons.gridy++; // row
		panel.add(IPrangeStartField, panelCons);


		// The start of the IP range
		JLabel IPrangeEndLabel = new JLabel(PrimeMain.texts
				.getString("propGeneralViewIPendsLabel"),
				SwingConstants.TRAILING);
		IPrangeEndLabel.setToolTipText(PrimeMain.texts
				.getString("propGeneralViewIPendsTip"));
		panelCons.insets = new Insets(0, 0, 5, 0); // padding
		panelCons.gridy++; // row
		panel.add(IPrangeEndLabel, panelCons);


		IPrangeEndField.setName("IP range end");
		String ipTo = canvas.getNetworkInfo().getIpRangeTo();
		if ( !(ipTo == null) )
		{
			IPrangeEndField.setText(ipTo);
		}

		panelCons.insets = new Insets(0, 0, 10, 0); // padding
		panelCons.weighty = 1.0; // request any extra vertical space
		panelCons.gridy++; // row
		panel.add(IPrangeEndField, panelCons);


		return panel;
	}



	/**
	 * Resets all general fields to the info gotten from the {@link WorkareaCanvas} viewed.
	 * It also resets the errorDuringSaving to false.
	 */
	public void resetGeneralFields()
	{
		nameField.setText(canvasViewed.getCanvasName());

		objectCountField.setText(""
				+ canvasViewed.getNumberOfWidgetsOnTheScene());


		String[] netmasks = new String[] { "", "255.255.255.0", "255.255.0.0",
				"255.0.0.0" };
		netmaskCombo.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				netmasks, canvasViewed.getNetworkInfo().getNetmask()));

		IPrangeStartField.setText(canvasViewed.getNetworkInfo()
				.getIpRangeFrom());

		IPrangeEndField.setText(canvasViewed.getNetworkInfo().getIpRangeTo());

		errorDuringSaving = false;
	}


	/**
	 * Javadoc-TODO - Description
	 */
	public void generalSaveAction()
	{
		if ( canvasViewed != null )
		{

			// The netmask
			if ( netmaskCombo.getSelectedIndex() != -1 )
			{
				// Casts the selected index to a String
				String selected = netmaskCombo.getSelectedItem().toString();

				// If the selected index is not ""
				if ( !(selected.equals("")) )
				{
					// If the string gotten is not valid
					if ( !(canvasViewed.getNetworkInfo().setNetmask(selected)) )
					{
						JOptionPane.showMessageDialog(null, PrimeMain.texts
								.getString("netmaskNotValidMsg")
								+ "\"" + selected + "\".", PrimeMain.texts
								.getString("error"), JOptionPane.ERROR_MESSAGE);

						errorDuringSaving = true;
					}
				}
			}

			// The Canvas name
			String canvasName = nameField.getText();
			if ( !(canvasName.equals("")) )
			{
				// If the name of the currently selected
				// WorkareaCanvas is not the same as the
				// name of the name in the canvasName field.
				if ( !(canvasViewed.getCanvasName().equals(canvasName)) )
				{
					// No canvas was found with the name
					if ( !(DesktopFileManagment
							.fileWorkareaCanvasExist(canvasName)) )
					{
						PrimeMain.workTab.updateCanvasName(canvasViewed,
								canvasName);
					}
					else
					{
						JOptionPane.showMessageDialog(null, PrimeMain.texts
								.getString("canvasExistWithNameMsg")
								+ "\"" + canvasName + "\".", PrimeMain.texts
								.getString("error"), JOptionPane.ERROR_MESSAGE);

						errorDuringSaving = true;
					}
				}
			}


			// The IP range for a WorkareaCanvas
			String IPrangeStart = null;
			String IPrangeEnd = null;

			// If the text in the JTextField is not ""
			if ( !(IPrangeStartField.getText().equals("")) )
			{
				// Sets the local IPrangeStart string that might be
				// used later
				IPrangeStart = IPrangeStartField.getText();
			}


			// If the text in the JTextField is not ""
			if ( !(IPrangeEndField.getText().equals("")) )
			{
				// Sets the local IPrangeEnd string that might be
				// used later
				IPrangeEnd = IPrangeEndField.getText();
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
						if ( NetworkManagment.processRange(IPrangeStart,
								IPrangeEnd) )
						{
							if ( !(canvasViewed.getNetworkInfo()
									.setIpRangeFrom(IPrangeStart)) )
							{
								errorDuringSaving = true;
							}
							if ( !(canvasViewed.getNetworkInfo()
									.setIpRangeTo(IPrangeEnd)) )
							{
								errorDuringSaving = true;
							}
						}
					}
					catch ( Exception exp )
					{
						String output = exp.getMessage();

						JOptionPane.showMessageDialog(null, output,
								PrimeMain.texts.getString("error"),
								JOptionPane.ERROR_MESSAGE);

						errorDuringSaving = true;
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, PrimeMain.texts
							.getString("ipMustHaveEndRange"), PrimeMain.texts
							.getString("error"), JOptionPane.ERROR_MESSAGE);

					errorDuringSaving = true;

				}
			}
			else
			{
				if ( !(IPrangeEnd == null) )
				{
					JOptionPane.showMessageDialog(null, PrimeMain.texts
							.getString("ipMustHaveStartRange"),
							PrimeMain.texts.getString("error"),
							JOptionPane.ERROR_MESSAGE);

					errorDuringSaving = true;
				}
			}
		}
	}
}
