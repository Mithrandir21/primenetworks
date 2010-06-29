/**
 * 
 */
package graphics.GUI.standardObjectEdit;


import exceptions.ObjectNotFoundException;
import graphics.PrimeMain;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import managment.ArrayManagment;
import objects.Object;
import widgets.WidgetIcon;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class StandardViewMouseListener extends MouseAdapter
{
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e)
	{
		WidgetIcon button = (WidgetIcon) e.getSource();

		Object newObject = null;

		try
		{
			// Gets the object in the given ArrayList with the given class
			newObject = ArrayManagment.getSpesificComponent(button
					.getClassType(), PrimeMain.objectlist);
		}
		catch ( ObjectNotFoundException ex )
		{
			System.out.println(PrimeMain.texts
					.getString("standardObjectsNotFoundInArrayMsg")
					+ " - " + button.getName());
			ex.printStackTrace();
		}

		// Changes the object currently viewed
		PrimeMain.stdObjView.getSplitView().getHardStdObjView()
				.changeObjectView(newObject);
	}
}
