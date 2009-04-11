/**
 * 
 */
package graphics.GUI.messageArea;


import graphics.ImageLocator;
import graphics.PrimeMain1;
import graphics.Settings;
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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	private JPanel netMsgPanel;


	private JPanel conMsgPanel;


	private JPanel softMsgPanel;


	private JPanel hardMsgPanel;


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 */
	public MessageTabbed()
	{
		netMsgPanel = new JPanel(new GridLayout());
		netMsgPanel.setEnabled(false);


		conMsgPanel = new JPanel(new GridLayout());
		conMsgPanel.setEnabled(false);


		softMsgPanel = new JPanel(new GridLayout());
		softMsgPanel.setEnabled(false);


		hardMsgPanel = new JPanel(new GridLayout());
		hardMsgPanel.setEnabled(false);


		int width = (int) (PrimeMain1.width * 0.70);

		int height = (int) (PrimeMain1.height * 0.11);


		this.setMaximumSize(new Dimension(width, height));
		this.setPreferredSize(new Dimension(width, height));
	}


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


		// Processes all the objects and gets the messages
		processHardwareMessage(computerObj);

		processConnectionMessage(conObj);

		processSoftwareMessage(computerObj);

		// FIXME - Add peripheral Processing



		this.revalidate();
		this.repaint();
	}



	/**
	 * This method will examine a network. Creates and adds at tab to this JTabbedPane component. The tab will contain
	 * possible JScrollPane with a JTable depending on whether or not the given objects creates any messages that they
	 * user needs to know about the objects.
	 * 
	 * @param objects
	 */
	public void processNetworkMessage(Object[] objects)
	{
		String[][] data = null;

		for ( int i = 0; i < objects.length; i++ )
		{
			data = NetworkProcessing.processNetwork(data, objects[i], PrimeMain1.currentCanvas,
					Settings.showNetworkErrorMessages, Settings.showNetworkWarningMessages,
					Settings.showNetworkNoticeMessages);
		}

		if ( data != null )
		{
			if ( data[0][0] != null )
			{
				netMsgPanel.removeAll();

				data = ArrayManagment.removeEmptyIndexes(data);

				netMsgPanel.add(new NetworkMessages(objects, data));
			}
			else
			{
				netMsgPanel.removeAll();
			}
		}
		else
		{
			netMsgPanel.removeAll();
		}
	}


	/**
	 * This method will examine an array of connections. Creates and adds at tab to this JTabbedPane component. The tab
	 * will contain possible JScrollPane with a JTable depending on whether or not the given objects creates any
	 * messages that they user needs to know about the objects.
	 * 
	 * @param objects
	 */
	public void processConnectionMessage(Object[] objects)
	{
		String[][] data = null;

		for ( int i = 0; i < objects.length; i++ )
		{
			data = ConnectionProcessing.processConnections(data, objects[i], PrimeMain1.currentCanvas,
					Settings.showConnectionErrorMessages, Settings.showConnectionWarningMessages,
					Settings.showConnectionNoticeMessages);
		}

		if ( data != null )
		{
			if ( data[0][0] != null )
			{
				conMsgPanel.removeAll();

				data = ArrayManagment.removeEmptyIndexes(data);

				conMsgPanel.add(new ConnectionMessages(objects, data));
			}
			else
			{
				conMsgPanel.removeAll();
			}
		}
		else
		{
			conMsgPanel.removeAll();
		}
	}


	/**
	 * This method will examine software in objects. Creates and adds at tab to this JTabbedPane component. The tab will
	 * contain possible JScrollPane with a JTable depending on whether or not the given objects creates any messages
	 * that they user needs to know about the objects.
	 * 
	 * @param objects
	 */
	public void processSoftwareMessage(Object[] objects)
	{
		String[][] data = null;

		for ( int i = 0; i < objects.length; i++ )
		{
			data = SoftwareProcessing.processSoftware(data, objects[i], Settings.showSoftwareErrorMessages,
					Settings.showSoftwareWarningMessages, Settings.showSoftwareNoticeMessages);
		}

		if ( data != null )
		{
			if ( data[0][0] != null )
			{
				softMsgPanel.removeAll();

				data = ArrayManagment.removeEmptyIndexes(data);

				softMsgPanel.add(new SoftwareMessages(objects, data));
			}
			else
			{
				softMsgPanel.removeAll();
			}
		}
		else
		{
			softMsgPanel.removeAll();
		}
	}


	/**
	 * This method will examine an array of objects for hardware. Creates and adds at tab to this JTabbedPane component.
	 * The tab will contain possible JScrollPane with a JTable depending on whether or not the given objects creates any
	 * messages that they user needs to know about the objects.
	 * 
	 * @param objects
	 */
	public void processHardwareMessage(Object[] objects)
	{
		String[][] data = null;

		for ( int i = 0; i < objects.length; i++ )
		{
			data = HardwareProcessing.processHardware(data, objects[i], Settings.showHardwareErrorMessages,
					Settings.showHardwareWarningMessages, Settings.showHardwareNoticeMessages);
		}

		if ( data != null )
		{
			if ( data[0][0] != null )
			{
				hardMsgPanel.removeAll();

				data = ArrayManagment.removeEmptyIndexes(data);

				hardMsgPanel.add(new HardwareMessages(objects, data));
			}
			else
			{
				hardMsgPanel.removeAll();
			}
		}
		else
		{
			hardMsgPanel.removeAll();
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
	public void addNewMessageTab(String labelText, JPanel content)
	{
		// The ImageIcon that will be shown and will remove a tab if pressed
		ImageIcon closeXIcon = ImageLocator.getImageIconObject("Close");

		// The dimensions of the new button.
		Dimension closeButtonSize = new Dimension(closeXIcon.getIconWidth() + 2, closeXIcon.getIconHeight() + 2);

		// The actual panel that will be the component panel which the JLabel and the button
		// will be placed inside.
		JPanel tab = new JPanel();

		// The Panel is not opaque
		tab.setOpaque(false);

		tab.setName(labelText);

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

		// Sets the name of the JPanel parameter
		content.setName(labelText);

		content.setEnabled(true);

		// Add the tab to the JTabbed pane.
		this.addTab(labelText, content);

		// Instead of using a String/Icon combination for the tab,
		// use our panel instead.
		this.setTabComponentAt(this.getTabCount() - 1, tab);
	}





	/**
	 * Javadoc-TODO - Description
	 * 
	 */
	public void createInitialTabs()
	{
		createNetworkMessagePanel();

		createConnectionMessagePanel();

		createSoftwareMessagePanel();

		createHardwareMessagePanel();
	}



	/**
	 * Javadoc-TODO - Description
	 * 
	 */
	public void createNetworkMessagePanel()
	{
		// If the JPanel is not enabled
		if ( !netMsgPanel.isEnabled() )
		{
			addNewMessageTab("Network", netMsgPanel);
		}
		else
		{
			focusOnNetworkTab();
		}
	}


	/**
	 * Javadoc-TODO - Description
	 * 
	 */
	public void createConnectionMessagePanel()
	{
		// If the JPanel is not enabled
		if ( !conMsgPanel.isEnabled() )
		{
			addNewMessageTab("Connection", conMsgPanel);
		}
		else
		{
			focusOnConnectionTab();
		}
	}


	/**
	 * Javadoc-TODO - Description
	 * 
	 */
	public void createSoftwareMessagePanel()
	{
		// If the JPanel is not enabled
		if ( !softMsgPanel.isEnabled() )
		{
			addNewMessageTab("Software", softMsgPanel);
		}
		else
		{
			focusOnSoftwareTab();
		}
	}


	/**
	 * Javadoc-TODO - Description
	 * 
	 */
	public void createHardwareMessagePanel()
	{
		// If the JPanel is not enabled
		if ( !hardMsgPanel.isEnabled() )
		{
			addNewMessageTab("Hardware", hardMsgPanel);
		}
		else
		{
			focusOnHardwareTab();
		}
	}


	/**
	 * Focuses on the Network tab if the tab exists.
	 */
	public void focusOnNetworkTab()
	{
		// Gets the index of the network tab
		int index = getIndexOfTab("Network");

		if ( index != -1 )
		{
			this.setSelectedIndex(index);
		}
	}
	
	/**
	 * Focuses on the Connection tab if the tab exists.
	 */
	public void focusOnConnectionTab()
	{
		// Gets the index of the connection tab
		int index = getIndexOfTab("Connection");

		if ( index != -1 )
		{
			this.setSelectedIndex(index);
		}
	}

	/**
	 * Focuses on the Software tab if the tab exists.
	 */
	public void focusOnSoftwareTab()
	{
		// Gets the index of the software tab
		int index = getIndexOfTab("Software");

		if ( index != -1 )
		{
			this.setSelectedIndex(index);
		}
	}

	/**
	 * Focuses on the Hardware tab if the tab exists.
	 */
	public void focusOnHardwareTab()
	{
		// Gets the index of the hardware tab
		int index = getIndexOfTab("Hardware");

		if ( index != -1 )
		{
			this.setSelectedIndex(index);
		}
	}



	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param name
	 * @return
	 */
	private int getIndexOfTab(String name)
	{
		// This is the current number of tabs
		int arraySize = this.getTabCount();

		// Goes through the list of tab contents until it finds one that matches the given button name.
		for ( int i = 0; i < arraySize; i++ )
		{
			JPanel test = (JPanel) this.getTabComponentAt(i);

			String tabName = test.getName();

			// If the name of the button and the name of the content match, the button to close that
			// tab with the given content has been pressed and the tab is removed.
			if ( tabName != null && tabName.equals(name) )
			{
				return i;
			}
		}

		// If no tab was found with the given name
		return -1;
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
			// Since there is no other components in this class that can call the ActionPerformed method
			// then the created JButton, we cast the source of the event as a JButton.
			JButton button = (JButton) e.getSource();

			String contentName = button.getName();
			JPanel test = null;

			// This is the current number of tabs
			int arraySize = this.getTabCount();

			// Goes through the list of tab contents until it finds one that matches the given button name.
			for ( int i = 0; i < arraySize; i++ )
			{
				test = (JPanel) this.getTabComponentAt(i);

				String tabName = test.getName();

				// If the name of the button and the name of the content match, the button to close that
				// tab with the given content has been pressed and the tab is removed.
				if ( tabName != null && tabName.equals(contentName) )
				{
					if ( tabName.equals("Network") )
					{
						netMsgPanel.setEnabled(false);
					}
					else if ( tabName.equals("Connection") )
					{
						conMsgPanel.setEnabled(false);
					}
					else if ( tabName.equals("Software") )
					{
						softMsgPanel.setEnabled(false);
					}
					else if ( tabName.equals("Hardware") )
					{
						hardMsgPanel.setEnabled(false);
					}

					this.remove(i);
					return;
				}

				test = null;
			}
		}
	}
}
