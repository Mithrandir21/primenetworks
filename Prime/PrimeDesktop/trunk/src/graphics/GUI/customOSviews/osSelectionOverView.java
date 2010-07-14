/**
 * 
 */
package graphics.GUI.customOSviews;


import graphics.GraphicalFunctions;
import graphics.PrimeMain;
import graphics.GUI.objectView.ObjectView;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import managment.SoftwareManagment;
import objects.Object;
import objects.softwareObjects.OperatingSystem;

import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class osSelectionOverView extends JDialog implements ActionListener
{
	// The main object
	private Object mainObj;

	// The operating system to be added to the main object.
	private OperatingSystem mainOs;


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param obj
	 */
	public osSelectionOverView(Object obj)
	{
		this.setTitle(PrimeMain.texts.getString("selectOSdialogTitle"));

		mainObj = obj;

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();

		// Set size for the settings JFrame
		Dimension size = new Dimension(800, 560);

		int initYLocation = (scrnsize.height - size.height) / 3;
		int initXLocation = (scrnsize.width - size.width) / 2;


		this.setLayout(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();

		d.fill = GridBagConstraints.BOTH;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		d.weighty = 1.0; // request any extra vertical space
		d.weightx = 1.0; // request any extra horizontal space
		d.anchor = GridBagConstraints.NORTH; // location
		// d.insets = new Insets(0, 0, 5, 0); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		d.gridy = 0; // row
		d.gridx = 0; // column


		JScrollPane scroll = new JScrollPane();
		// Increases how far the scroll bar scrolls on one step of a mouse wheel
		scroll.getVerticalScrollBar().setUnitIncrement(30);
		scroll.setViewportView(getStandardOS());

		this.add(scroll, d);


		this.setPreferredSize(size);
		this.setLocation(initXLocation, initYLocation);
		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setVisible(true);
	}



	/**
	 * TODO - Description
	 * 
	 */
	private JXTaskPaneContainer getStandardOS()
	{
		JXTaskPaneContainer tpc = new JXTaskPaneContainer();


		if ( PrimeMain.system_standard_OS != null )
		{
			for ( int i = 0; i < PrimeMain.system_standard_OS.length; i++ )
			{
				if ( PrimeMain.system_standard_OS[i] != null )
				{
					if ( i == 0 )
					{
						tpc.add(getOSpane(PrimeMain.system_standard_OS[i],
								false, true));
					}
					else
					{
						tpc.add(getOSpane(PrimeMain.system_standard_OS[i],
								true, false));
					}
				}
			}
		}

		return tpc;
	}



	/**
	 * TODO - Description
	 * 
	 */
	private JXTaskPane getOSpane(OperatingSystem os, boolean collapsed,
			boolean editable)
	{
		JXTaskPane pane = new JXTaskPane();

		pane.setTitle(os.getObjectName());
		pane.setSpecial(true);
		pane.setCollapsed(collapsed);


		pane.setLayout(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();

		d.fill = GridBagConstraints.NONE;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		// d.weighty = 1.0; // request any extra vertical space
		// d.weightx = 1.0; // request any extra horizontal space
		d.anchor = GridBagConstraints.NORTHWEST; // location
		d.insets = new Insets(10, 10, 10, 10); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		d.gridy = 0; // row
		d.gridx = 0; // column



		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridBagLayout());
		GridBagConstraints conPanel1 = new GridBagConstraints();

		conPanel1.fill = GridBagConstraints.NONE;
		// conPanel1.ipady = 0; // reset to default
		// conPanel1.ipadx = 0; // reset to default
		// conPanel1.weighty = 1.0; // request any extra vertical space
		// conPanel1.weightx = 1.0; // request any extra horizontal space
		conPanel1.anchor = GridBagConstraints.NORTHWEST; // location
		conPanel1.insets = new Insets(10, 10, 10, 10); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		conPanel1.gridy = 0; // row
		conPanel1.gridx = 0; // column


		// The supported file system.
		JLabel fsLabel = new JLabel(PrimeMain.texts
				.getString("osViewSupFSLabel"));
		fsLabel.setToolTipText(PrimeMain.texts.getString("osViewSupFSTip"));

		panel1.add(fsLabel, conPanel1);


		String[] listData = GraphicalFunctions.getAllFs();
		JList supportedFS = new JList(listData);
		JScrollPane listPane = new JScrollPane(supportedFS);
		listPane.setMaximumSize(new Dimension(160, 60));
		listPane.setPreferredSize(new Dimension(160, 60));
		listPane.setEnabled(false);
		supportedFS.setEnabled(false);

		if ( os.getSupportedFS() != null )
		{
			if ( os.getSupportedFS().length > 0 )
			{
				listPane.setViewportView(GraphicalFunctions.getIndexInJList(
						supportedFS, listData, os.getSupportedFS()));
			}
		}

		conPanel1.gridx = 1; // column
		panel1.add(listPane, conPanel1);



		// Whether or not the OS supports encrypted file system
		JCheckBox encryptedFileSystem = new JCheckBox(PrimeMain.texts
				.getString("osViewSupEnctyptedFSLabel"));
		encryptedFileSystem.setToolTipText(PrimeMain.texts
				.getString("osViewSupEnctyptedFSTip"));
		encryptedFileSystem.setSelected(os.isEncryptedFileSystem());

		conPanel1.gridx = 2; // column
		panel1.add(encryptedFileSystem, conPanel1);



		// Whether or not the OS has a GUI
		JCheckBox hasGUI = new JCheckBox(PrimeMain.texts
				.getString("osViewSupEnctyptedFSLabel"));
		hasGUI.setToolTipText(PrimeMain.texts
				.getString("osViewSupEnctyptedFSTip"));
		hasGUI.setSelected(os.isHasGUI());

		conPanel1.gridx = 3; // column
		panel1.add(hasGUI, conPanel1);


		pane.add(panel1, d);




		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridBagLayout());
		GridBagConstraints conPanel2 = new GridBagConstraints();

		conPanel2.fill = GridBagConstraints.NONE;
		// conPanel2.ipady = 0; // reset to default
		// conPanel2.ipadx = 0; // reset to default
		// conPanel2.weighty = 1.0; // request any extra vertical space
		// conPanel2.weightx = 1.0; // request any extra horizontal space
		conPanel2.anchor = GridBagConstraints.NORTHWEST; // location
		conPanel2.insets = new Insets(10, 10, 10, 10); // padding
		// conPanel2.gridwidth = 1; // 2 row wide
		// conPanel2.gridheight = 1; // 2 columns wide
		conPanel2.gridy = 0; // row
		conPanel2.gridx = 0; // column

		// The 64 bit check box
		JCheckBox is64bit = new JCheckBox(PrimeMain.texts
				.getString("osViewSupEnctyptedFSLabel"));
		is64bit.setToolTipText(PrimeMain.texts
				.getString("osViewSupEnctyptedFSTip"));
		is64bit.setSelected(os.isIs64bit());

		conPanel2.gridx = 0; // column
		panel2.add(is64bit, conPanel2);


		d.gridy = 1;
		pane.add(panel2, d);





		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridBagLayout());
		GridBagConstraints conPanel3 = new GridBagConstraints();

		conPanel3.fill = GridBagConstraints.NONE;
		// conPanel3.ipady = 0; // reset to default
		// conPanel3.ipadx = 0; // reset to default
		// conPanel3.weighty = 1.0; // request any extra vertical space
		// conPanel3.weightx = 1.0; // request any extra horizontal space
		conPanel3.anchor = GridBagConstraints.NORTHWEST; // location
		conPanel3.insets = new Insets(10, 10, 10, 10); // padding
		// conPanel3.gridwidth = 1; // 2 row wide
		// conPanel3.gridheight = 1; // 2 columns wide
		conPanel3.gridy = 0; // row
		conPanel3.gridx = 0; // column


		// Desc
		JLabel descLabel = new JLabel(PrimeMain.texts
				.getString("swTabSWdescriptionLabel"));

		conPanel3.gridx = 0; // column
		panel3.add(descLabel, conPanel3);


		// Description
		JScrollPane descScroll = new JScrollPane();
		JTextArea desc = new JTextArea(5, 60);
		desc.setName("Description");
		desc.setText(os.getDescription());
		desc.setFont(descLabel.getFont());
		descScroll.setViewportView(desc);


		conPanel3.weighty = 1.0; // request any extra vertical space
		conPanel3.weightx = 1.0; // request any extra horizontal space
		conPanel3.gridy = 1; // row
		conPanel3.gridx = 0; // column
		panel3.add(descScroll, conPanel3);



		d.weighty = 1.0; // request any extra vertical space
		d.weightx = 1.0; // request any extra horizontal space
		d.gridy = 2;
		pane.add(panel3, d);




		JPanel panel4 = new JPanel();
		panel4.setLayout(new GridBagLayout());
		GridBagConstraints conPanel4 = new GridBagConstraints();

		conPanel4.fill = GridBagConstraints.NONE;
		// conPanel4.ipady = 0; // reset to default
		// conPanel4.ipadx = 0; // reset to default
		// conPanel4.weighty = 1.0; // request any extra vertical space
		// conPanel4.weightx = 1.0; // request any extra horizontal space
		conPanel4.anchor = GridBagConstraints.SOUTHEAST; // location
		conPanel4.insets = new Insets(10, 10, 10, 10); // padding
		// conPanel4.gridwidth = 1; // 2 row wide
		// conPanel4.gridheight = 1; // 2 columns wide
		conPanel4.gridy = 0; // row
		conPanel4.gridx = 0; // column


		if ( editable )
		{
			JButton edit = new JButton("Edit");
			edit.setActionCommand("Edit" + os.getObjectName());
			edit.addActionListener(this);

			panel4.add(edit, d);
			conPanel4.gridx = 1;
		}



		JButton install = new JButton(PrimeMain.texts.getString("install")
				+ " " + os.getObjectName());
		install.setActionCommand(os.getObjectName());
		install.addActionListener(this);

		d.anchor = GridBagConstraints.SOUTHEAST; // location
		d.insets = new Insets(10, 10, 10, 10); // padding
		d.gridy = 3;
		panel4.add(install, d);

		return pane;
	}



	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getSource() instanceof JButton )
		{
			for ( int i = 0; i < PrimeMain.system_standard_OS.length; i++ )
			{
				if ( e.getActionCommand().equals(
						PrimeMain.system_standard_OS[i].getObjectName()) )
				{
					OperatingSystem os = PrimeMain.system_standard_OS[i];

					// Sets an array with the newly added software object
					mainObj.setSoftware(SoftwareManagment.addSoftware(os,
							mainObj));


					// Updates the views of the object to correctly show the
					// current info.
					ObjectView view = PrimeMain.getObjectView(mainObj);
					if ( view != null )
					{
						view.updateViewInfo();
					}


					// Closes the JFrame.
					this.dispose();
				}
			}
		}
		else if ( e.getActionCommand().equals("cancel") )
		{
			this.dispose();
		}
	}
}
