/**
 * 
 */
package graphics.GUI.standardObjectEdit.StandardViews;


import graphics.PrimeMain1;
import graphics.GUI.objectView.ObjectView;
import graphics.GUI.objectView.Hardware.NewComponent.NewViews.MotherboardNewView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import managment.ComponentsManagment;
import objects.Hardware;
import objects.Object;
import objects.hardwareObjects.Motherboard;
import widgets.WorkareaCanvas;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class HardwareMouseListener extends MouseAdapter implements ActionListener
{
	// This is the main Object
	private Object mainObject;

	// The Object that is to right clicked on
	private Object hardwareObject;

	// The panel where the Popup menu will be shown
	private JPanel panel;


	/**
	 * Javadoc-TODO - Description NEEDED!
	 */
	public HardwareMouseListener(JPanel hardwarePanel, Object mainObj, Object hardObj)
	{
		panel = hardwarePanel;
		mainObject = mainObj;
		hardwareObject = hardObj;
	}




	/**
	 * Creates and pops up the JMenu coordinates gotten from the MouseEvent. This menu is shown when right clicked on a
	 * Hardware Object in the HardwareView of an Object on the {@link WorkareaCanvas}.
	 * 
	 * @param e
	 */
	private void myPopupEvent(MouseEvent e)
	{
		int x = e.getX();
		int y = e.getY();

		JPopupMenu itemPopup = new JPopupMenu();


		JMenuItem deleteHardware = new JMenuItem("Delete Hardware");
		deleteHardware.setActionCommand("Delete Hardware");
		deleteHardware.addActionListener(this);


		JMenuItem editHardware = new JMenuItem("Edit Hardware");
		editHardware.setActionCommand("Edit Hardware");
		editHardware.addActionListener(this);



		itemPopup.add(deleteHardware);
		itemPopup.add(editHardware);

		itemPopup.show(panel, x, y);
	}


	/**
	 * MUST HAVE BOTH THESE METHODES BECAUSE THE POPUPTRIGGERS ARE DIFFERENT IN DIFFERENT OPERATING SYSTEMS.
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent e)
	{
		if ( e.isPopupTrigger() )
		{
			myPopupEvent(e);
		}
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
	 */
	public void mouseReleased(MouseEvent e)
	{
		if ( e.isPopupTrigger() )
		{
			myPopupEvent(e);
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
		if ( e.getActionCommand().equals("Delete Hardware") )
		{
			// If the Hardware Object selected is not a Motherboard
			if ( !(hardwareObject instanceof Motherboard) )
			{
				// Asks the user to confirm the deletion
				int answer = JOptionPane.showConfirmDialog(panel, "Are you sure you wish to remove this component?",
						"Confirm", JOptionPane.YES_NO_OPTION);

				// If the user confirms the deletion
				if ( answer == 0 )
				{
					// Will remove the first variable from the list of components that will be returned and set as the
					// components for the main object.
					mainObject.setAllComponents(ComponentsManagment.removeComponent(hardwareObject, mainObject
							.getComponents(), mainObject.getComponents().length));

					// Updates the views of the object to correctly show the current info.
					ObjectView view = PrimeMain1.getObjectView(mainObject);
					if ( view != null )
					{
						view.updateViewInfo();
					}
				}
			}
			else
			{
				// Asks the user to confirm the deletion
				int answer = JOptionPane.showConfirmDialog(panel,
						"A computer must have a Motherboard. Do you wish to delete this Motherboard "
								+ "and install a standard Motherboard?", "Confirm", JOptionPane.YES_NO_OPTION);

				if ( answer == 0 )
				{
					// Creates a new Motherboard object
					Motherboard mbObj = PrimeMain1.standard_internal_components.getSt_MB();

					// Creates a new motherboard editor where the user can save a new Motherboard
					new MotherboardNewView(mainObject, mbObj);
				}
			}
		}
		else if ( e.getActionCommand().equals("Edit Hardware") )
		{
			// Gets the Object View which will contain the hardware editor view of each hardware object
			ObjectView objView = PrimeMain1.objView.get(0);

			// Call for a new Hardware editor to be create and then sets the tab focus to the hardware object the user
			// selected to be edited.
			objView.getObjectView().getHardwareEditor().createNewHardwareEditor(mainObject).setTabFocus(
					(Hardware) hardwareObject);
		}
	}

}
