/**
 * 
 */
package graphics.GUI.standardObjectEdit;


import exceptions.ObjectNotFoundException;
import graphics.PrimeMain1;

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
					.getClassType(), PrimeMain1.objectlist);
		}
		catch ( ObjectNotFoundException ex )
		{
			System.out.println(PrimeMain1.texts
					.getString("standardObjectsNotFoundInArrayMsg")
					+ " - " + button.getName());
			ex.printStackTrace();
		}

		// Changes the object currently viewed
		PrimeMain1.stdObjView.getSplitView().getHardStdObjView()
				.changeObjectView(newObject);
	}
}
