/**
 * 
 */
package graphics.GUI.workareaCanvas;


import graphics.ImageLocator;
import graphics.PrimeMain1;
import graphics.GUI.properties.PropertiesArea;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class WorkareaTabbed extends JTabbedPane
{

	public WorkareaTabbed()
	{
		createNewCanvasTab("AA");
		createNewCanvasTab("BB");
		createNewCanvasTab("CC");
		
//		PrimeMain1.updatePropertiesArea();
		

		addChangeListener(new ChangeListener()
		{
			// This method is called whenever the selected tab changes
			public void stateChanged(ChangeEvent evt)
			{
				// Gets the JTabbedPane that the event comes from.
				JTabbedPane pane = (JTabbedPane) evt.getSource();

				// Gets the scrollPane that the JTabbedPane contains.
				WorkareaSceneScroll currentScrollPane = (WorkareaSceneScroll) pane
						.getSelectedComponent();

				// Gets the workareaCanvas that the scrollPane contains.
				WorkareaCanvas currentCanvas = currentScrollPane.getCanvas();

				// Repaints the entier canvas.(Avoids NullPointerExeption errors from swing).
				currentCanvas.doRepaint();

				// Sets the current working canvas to the canvas that is actually shown in the 
				// JTabbedPane scrollPane.
				PrimeMain1.currentCanvas = currentCanvas;
				
				PrimeMain1.updatePropertiesArea();
			}
		});
		
		
		
	}



	public void createNewCanvasTab(String name)
	{
		WorkareaSceneScroll canvasScroll = new WorkareaSceneScroll(name);

		// Creates the image for the tab
		ImageIcon icon = ImageLocator.getImageIconObject("java");

		int width = (int) (PrimeMain1.width*0.60);
		int height = (int) (PrimeMain1.width*0.60);
		
		
		this.setPreferredSize(new Dimension(width,height));

		addTab(name, icon, canvasScroll);
	}



}
