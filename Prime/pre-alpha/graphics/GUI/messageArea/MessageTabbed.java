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
 * 
 */
public class MessageTabbed extends JTabbedPane implements ActionListener
{
	private ArrayList<JScrollPane> contents = new ArrayList<JScrollPane>();

	// No Need for constructor

	/**
	 * Processes all the objects given in the Objects array.
	 * The method separates the different object classes and then
	 * passes the different arrays with object to methods that
	 * will create multidimentional String arrays with messages
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
		
		
		for( int i = 0; i < conObj.length; i++ )
		{
			if( objects[i] instanceof Connection )
			{
				conObj[i] = objects[i];
			}
		}
		
		
		
		for( int i = 0; i < computerObj.length; i++ )
		{
			if( objects[i] instanceof Clients )
			{
				computerObj[i] = objects[i];
			}
			else if( objects[i] instanceof Servers )
			{
				computerObj[i] = objects[i];
			}
		}
		
		
		for( int i = 0; i < peripheralObj.length; i++ )
		{
			if( objects[i] instanceof ExternalHardware )
			{
				peripheralObj[i] = objects[i];
			}
		}
		
		
		
		for( int i = 0; i < infraObj.length; i++ )
		{
			if( objects[i] instanceof Infrastructure )
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
		
	}
	
	
	
	/**
	 * This method will examine a network.
	 * 
	 * Creates and adds at tab to this JTabbedPane component.
	 * The tab will contain possible JScrollPane with a JTable
	 * depending on whether or not the given objects creates any
	 * messages that they user needs to know about the objects.
	 * 
	 * @param objects
	 */
	public void addNetworkTab(Object[] objects)
	{
		removeTab("Network");
		
		
		String[][] data = null;

		for ( int i = 0; i < objects.length; i++ )
		{
			data = NetworkProcessing.processNetwork(data, objects[i], true,
					true, true);
		}
		
		if ( data != null )
		{
			if ( data[0][0] != null )
			{
				data = ArrayManagment.removeEmptyIndexes(data);
				addNewMessageTab("Network", new NetworkMessages(objects, data));
			}
		}
	}


	/**
	 * This method will examine an array of connections.
	 * 
	 * Creates and adds at tab to this JTabbedPane component.
	 * The tab will contain possible JScrollPane with a JTable
	 * depending on whether or not the given objects creates any
	 * messages that they user needs to know about the objects.
	 * 
	 * @param objects
	 */
	public void addConnectionTab(Object[] objects)
	{
		removeTab("Connections");
		

		String[][] data = null;

		for ( int i = 0; i < objects.length; i++ )
		{
			data = ConnectionProcessing.processConnections(data, objects[i],
					true, true, true);
		}
		
		if ( data != null )
		{
			if ( data[0][0] != null )
			{
				data = ArrayManagment.removeEmptyIndexes(data);
				addNewMessageTab("Connections", new ConnectionMessages(objects,
						data));
			}
		}
	}


	/**
	 * This method will examine software in objects.
	 * 
	 * Creates and adds at tab to this JTabbedPane component.
	 * The tab will contain possible JScrollPane with a JTable
	 * depending on whether or not the given objects creates any
	 * messages that they user needs to know about the objects.
	 * 
	 * @param objects
	 */
	public void addSoftwareTab(Object[] objects)
	{
		removeTab("Software");
		

		String[][] data = null;

		for ( int i = 0; i < objects.length; i++ )
		{
			data = SoftwareProcessing.processSoftware(data, objects[i], true,
					true, true);
		}
		
		if ( data != null )
		{
			if ( data[0][0] != null )
			{
				data = ArrayManagment.removeEmptyIndexes(data);
				addNewMessageTab("Software",
						new SoftwareMessages(objects, data));
			}
		}
	}


	/**
	 * This method will examine an array of objects for hardware.
	 * 
	 * Creates and adds at tab to this JTabbedPane component.
	 * The tab will contain possible JScrollPane with a JTable
	 * depending on whether or not the given objects creates any
	 * messages that they user needs to know about the objects.
	 * 
	 * @param objects
	 */
	public void addHardwareTab(Object[] objects)
	{
		removeTab("Hardware");
		
		
		String[][] data = null;

		for ( int i = 0; i < objects.length; i++ )
		{
			data = HardwareProcessing.processHardware(data, objects[i], true,
					true, true);
		}
		
		if ( data != null )
		{
			if ( data[0][0] != null )
			{
				data = ArrayManagment.removeEmptyIndexes(data);
				addNewMessageTab("Hardware",
						new HardwareMessages(objects, data));
			}
		}
	}



	/**
	 * This method removes the tab with the given name from this
	 * class, which is a JTabbedPane.
	 * 
	 * @param title
	 * 			The title of the Tab that is to be removed.
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
	 * 			The text that will be shown inside the tab.
	 * @param content
	 * 			The content that will be shown within the tab.
	 */
	public void addNewMessageTab(String labelText, JScrollPane content)
	{
		ImageIcon closeXIcon = ImageLocator.getImageIconObject("Close");
		Dimension closeButtonSize = new Dimension(
				closeXIcon.getIconWidth() + 5, closeXIcon.getIconHeight() + 5);

		JPanel tab = new JPanel();
		tab.setOpaque(false);


		JLabel tabLabel = new JLabel(labelText);


		JButton tabCloseButton = new JButton(closeXIcon);
		tabCloseButton.setName(labelText);
		tabCloseButton.setPreferredSize(closeButtonSize);
		tabCloseButton.addActionListener(this);

		tab.add(tabLabel, BorderLayout.WEST);
		tab.add(tabCloseButton, BorderLayout.EAST);

		content.setName(labelText);
		contents.add(content);

		// Add the tab to the tabbed pane.

		this.addTab(labelText, content);

		// Instead of using a String/Icon combination for the tab,
		// use our panel instead.

		this.setTabComponentAt(this.getTabCount() - 1, tab);
	}


	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton button = (JButton) e.getSource();

		String contentName = button.getName();
		JScrollPane test = null;

		int arraySize = contents.size();


		for ( int i = 0; i < arraySize; i++ )
		{
			test = contents.get(i);
			
			if ( test.getName() != null && test.getName().equals(contentName) )
			{
				this.remove(test);
				contents.remove(i);
				return;
			}

			test = null;
		}
	}
}
