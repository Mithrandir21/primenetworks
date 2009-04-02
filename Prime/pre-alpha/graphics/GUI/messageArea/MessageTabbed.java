/**
 * 
 */
package graphics.GUI.messageArea;


import graphics.ImageLocator;
import graphics.GUI.messageArea.ConnectionTab.ConnectionMessages;
import graphics.GUI.messageArea.ConnectionTab.ConnectionProcessing;
import graphics.GUI.messageArea.HardwareTab.HardwareMessages;
import graphics.GUI.messageArea.HardwareTab.HardwareProcessing;
import graphics.GUI.messageArea.NetworkTab.NetworkMessages;
import graphics.GUI.messageArea.NetworkTab.NetworkProcessing;
import graphics.GUI.messageArea.SoftwareTab.SoftwareMessages;
import graphics.GUI.messageArea.SoftwareTab.SoftwareProcessing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import logistical.cleanup;
import managment.ArrayManagment;
import objects.Clients;
import objects.ExternalHardware;
import objects.Infrastructure;
import objects.Object;
import objects.Servers;
import connections.Connection;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class MessageTabbed extends JTabbedPane implements ActionListener
{
	private ArrayList<JScrollPane> contents = new ArrayList<JScrollPane>();

	private boolean showNetworkTab = true;

	private boolean showConnectionTab = true;

	private boolean showSoftwareTab = true;

	private boolean showHardwareTab = true;


	// No Need for constructor

	/**
	 * Processes all the objects given in the Objects array. The method separates the different object classes and then
	 * passes the different arrays with object to methods that will create multidimensional String arrays with messages
	 * to the user about the given objects.
	 * 
	 * @param objects
	 */
	public void processAllObjects(Object[] objects)
	{
		Object[] conObj = new Object[objects.length];

		Object[] computerObj = new Object[objects.length];

		Object[] peripheralObj = new Object[objects.length];

		Object[] infraObj = new Object[objects.length];


		for ( int i = 0; i < conObj.length; i++ )
		{
			if ( objects[i] instanceof Connection )
			{
				conObj[i] = objects[i];
			}
		}



		for ( int i = 0; i < computerObj.length; i++ )
		{
			if ( objects[i] instanceof Clients )
			{
				computerObj[i] = objects[i];
			}
			else if ( objects[i] instanceof Servers )
			{
				computerObj[i] = objects[i];
			}
		}


		for ( int i = 0; i < peripheralObj.length; i++ )
		{
			if ( objects[i] instanceof ExternalHardware )
			{
				peripheralObj[i] = objects[i];
			}
		}



		for ( int i = 0; i < infraObj.length; i++ )
		{
			if ( objects[i] instanceof Infrastructure )
			{
				infraObj[i] = objects[i];
			}
		}



		// Removes all the null pointers in the arrays.
		conObj = cleanup.cleanObjectArray(conObj);

		computerObj = cleanup.cleanObjectArray(computerObj);

		peripheralObj = cleanup.cleanObjectArray(peripheralObj);

		infraObj = cleanup.cleanObjectArray(infraObj);


		addHardwareTab(computerObj);

		addConnectionTab(conObj);

		addSoftwareTab(computerObj);

		// FIXME - Add peripheral Processing
	}



	/**
	 * This method will examine a network. Creates and adds at tab to this JTabbedPane component. The tab will contain
	 * possible JScrollPane with a JTable depending on whether or not the given objects creates any messages that they
	 * user needs to know about the objects.
	 * 
	 * @param objects
	 */
	public void addNetworkTab(Object[] objects)
	{
		String[][] data = null;

		for ( int i = 0; i < objects.length; i++ )
		{
			data = NetworkProcessing.processNetwork(data, objects[i], true, true, true);
		}

		if ( data != null )
		{
			if ( data[0][0] != null )
			{
				removeTab("Network");
				data = ArrayManagment.removeEmptyIndexes(data);
				addNewMessageTab("Network", new NetworkMessages(objects, data));
			}
		}
	}


	/**
	 * This method will examine an array of connections. Creates and adds at tab to this JTabbedPane component. The tab
	 * will contain possible JScrollPane with a JTable depending on whether or not the given objects creates any
	 * messages that they user needs to know about the objects.
	 * 
	 * @param objects
	 */
	public void addConnectionTab(Object[] objects)
	{
		String[][] data = null;

		for ( int i = 0; i < objects.length; i++ )
		{
			data = ConnectionProcessing.processConnections(data, objects[i], true, true, true);
		}

		if ( data != null )
		{
			if ( data[0][0] != null )
			{
				removeTab("Connections");
				data = ArrayManagment.removeEmptyIndexes(data);
				addNewMessageTab("Connections", new ConnectionMessages(objects, data));
			}
		}
	}


	/**
	 * This method will examine software in objects. Creates and adds at tab to this JTabbedPane component. The tab will
	 * contain possible JScrollPane with a JTable depending on whether or not the given objects creates any messages
	 * that they user needs to know about the objects.
	 * 
	 * @param objects
	 */
	public void addSoftwareTab(Object[] objects)
	{
		String[][] data = null;

		for ( int i = 0; i < objects.length; i++ )
		{
			data = SoftwareProcessing.processSoftware(data, objects[i], true, true, true);
		}

		if ( data != null )
		{
			if ( data[0][0] != null )
			{
				removeTab("Software");
				data = ArrayManagment.removeEmptyIndexes(data);
				addNewMessageTab("Software", new SoftwareMessages(objects, data));
			}
		}
	}


	/**
	 * This method will examine an array of objects for hardware. Creates and adds at tab to this JTabbedPane component.
	 * The tab will contain possible JScrollPane with a JTable depending on whether or not the given objects creates any
	 * messages that they user needs to know about the objects.
	 * 
	 * @param objects
	 */
	public void addHardwareTab(Object[] objects)
	{
		String[][] data = null;

		for ( int i = 0; i < objects.length; i++ )
		{
			data = HardwareProcessing.processHardware(data, objects[i], true, true, true);
		}

		if ( data != null )
		{
			if ( data[0][0] != null )
			{
				removeTab("Hardware");
				data = ArrayManagment.removeEmptyIndexes(data);
				addNewMessageTab("Hardware", new HardwareMessages(objects, data));
			}
		}
	}



	/**
	 * This method removes the tab with the given name from this class, which is a JTabbedPane.
	 * 
	 * @param title
	 *            The title of the Tab that is to be removed.
	 */
	private void removeTab(String title)
	{
		// The total count of the tabs on the screen
		int tabCount = this.getTabCount();

		// The index of the tab we are looking for
		int tabIndex = this.getTabCount();

		// Searching for the tab with the given title
		for ( int i = 0; i < tabCount; i++ )
		{
			if ( this.getTitleAt(i).equals(title) )
			{
				tabIndex = i;
			}
		}

		// If the tabindex is anything else then what it was in the beginning
		if ( tabIndex != tabCount )
		{
			this.remove(tabIndex);
		}
	}


	/**
	 * This method creates a custom Tab with an close button.
	 * 
	 * @param labelText
	 *            The text that will be shown inside the tab.
	 * @param content
	 *            The content that will be shown within the tab.
	 */
	public void addNewMessageTab(String labelText, JScrollPane content)
	{
		// The ImageIcon that will be shown and will remove a tab if pressed
		ImageIcon closeXIcon = ImageLocator.getImageIconObject("Close");

		// The dimensions of the new button.
		Dimension closeButtonSize = new Dimension(closeXIcon.getIconWidth()+2, closeXIcon.getIconHeight()+2);

		// The actual panel that will be the component panel which the JLabel
		// and the button
		// will be placed inside.
		JPanel tab = new JPanel();

		// The Panel is not opaque
		tab.setOpaque(false);

		// The JLabel that will show the name of the tab
		JLabel tabLabel = new JLabel(labelText);

		// The button that will be represented by the ImageIcon
		JButton tabCloseButton = new JButton(closeXIcon);
		tabCloseButton.setName(labelText);
		tabCloseButton.setPreferredSize(closeButtonSize);
		tabCloseButton.addActionListener(this);

		// Adds the label and then the button to the panel.
		tab.add(tabLabel, BorderLayout.WEST);
		tab.add(tabCloseButton, BorderLayout.EAST);

		if ( content != null )
		{
			// Sets the name of the JScrollPane parameter
			content.setName(labelText);
			// Adds the JScrollPane to the list of contents of the different
			// tabs
			contents.add(content);
		}

		// Add the tab to the tabbed pane.
		this.addTab(labelText, content);

		// Instead of using a String/Icon combination for the tab,
		// use our panel instead.
		this.setTabComponentAt(this.getTabCount() - 1, tab);
	}


	// GETTERS

	/**
	 * Gets the boolean that will tell whether or not the Network tab should be shown.
	 * 
	 * @return the showNetworkTab
	 */
	public boolean getShowNetworkTab()
	{
		return showNetworkTab;
	}



	/**
	 * Gets the boolean that will tell whether or not the Connection tab should be shown.
	 * 
	 * @return the showConnectionTab
	 */
	public boolean getShowConnectionTab()
	{
		return showConnectionTab;
	}



	/**
	 * Gets the boolean that will tell whether or not the Software tab should be shown.
	 * 
	 * @return the showSoftwareTab
	 */
	public boolean getShowSoftwareTab()
	{
		return showSoftwareTab;
	}



	/**
	 * Gets the boolean that will tell whether or not the Hardware tab should be shown.
	 * 
	 * @return the showHardwareTab
	 */
	public boolean getShowHardwareTab()
	{
		return showHardwareTab;
	}



	// SETTERS

	/**
	 * Sets the boolean that will tell whether or not the Network tab should be shown.
	 * 
	 * @param showNetworkTab
	 *            the showNetworkTab to set
	 */
	public void setShowNetworkTab(boolean showNetworkTab)
	{
		this.showNetworkTab = showNetworkTab;
	}



	/**
	 * Sets the boolean that will tell whether or not the Connection tab should be shown.
	 * 
	 * @param showConnectionTab
	 *            the showConnectionTab to set
	 */
	public void setShowConnectionTab(boolean showConnectionTab)
	{
		this.showConnectionTab = showConnectionTab;
	}



	/**
	 * Sets the boolean that will tell whether or not the Software tab should be shown.
	 * 
	 * @param showSoftwareTab
	 *            the showSoftwareTab to set
	 */
	public void setShowSoftwareTab(boolean showSoftwareTab)
	{
		this.showSoftwareTab = showSoftwareTab;
	}



	/**
	 * Sets the boolean that will tell whether or not the Hardware tab should be shown.
	 * 
	 * @param showHardwareTab
	 *            the showHardwareTab to set
	 */
	public void setShowHardwareTab(boolean showHardwareTab)
	{
		this.showHardwareTab = showHardwareTab;
	}





	public void createInitialTabs()
	{
		addNewMessageTab("Network", new JScrollPane());

		addNewMessageTab("Connections", new JScrollPane());

		addNewMessageTab("Software", new JScrollPane());

		addNewMessageTab("Hardware", new JScrollPane());
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{

		if ( e.getSource() instanceof JButton )
		{
			// Since there is no other components in this class that can call
			// the actionperformed method
			// then the created JButton, we cast the source of the event as a
			// JButton.
			JButton button = (JButton) e.getSource();

			String contentName = button.getName();
			JScrollPane test = null;

			// This is the current number of tabs
			int arraySize = contents.size();

			// Goes through the list of tab contents until it fins one that
			// matches the given button name.
			for ( int i = 0; i < arraySize; i++ )
			{
				test = contents.get(i);

				String tabName = test.getName();

				// If the name of the button and the name of the content match,
				// the button to close that
				// tab with the given content has been pressed and the tab is
				// removed.
				if ( tabName != null && tabName.equals(contentName) )
				{
					if ( tabName.equals("Network") )
					{
						showNetworkTab = false;
					}
					else if ( tabName.equals("Connections") )
					{
						showConnectionTab = false;
					}
					else if ( tabName.equals("Software") )
					{
						showSoftwareTab = false;
					}
					else if ( tabName.equals("Hardware") )
					{
						showHardwareTab = false;
					}

					this.remove(test);
					contents.remove(i);
					return;
				}

				test = null;
			}
		}
	}
}
