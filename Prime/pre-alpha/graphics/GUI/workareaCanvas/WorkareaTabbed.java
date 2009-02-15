/**
 * 
 */
package graphics.GUI.workareaCanvas;


import graphics.ImageLocator;
import graphics.PrimeMain1;

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

	/**
	 * A constructor for this class that add a changeListener that will call
	 * doRepaint on the WorkareaCanvas when any change occurs.
	 */
	public WorkareaTabbed()
	{
		createNewCanvasTab("AA");
		createNewCanvasTab("BB");
		createNewCanvasTab("CC");

		// PrimeMain1.updatePropertiesArea();


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

				// Repaints the entire canvas.(Avoids NullPointerExeption errors
				// from swing).
				currentCanvas.doRepaint();

				// Sets the current working canvas to the canvas that is
				// actually shown in the
				// JTabbedPane scrollPane.
				PrimeMain1.currentCanvas = currentCanvas;

				PrimeMain1.updatePropertiesCanvasArea();
			}
		});



	}



	/**
	 * The function creates a new WorkareaSceneScroll and places that component
	 * within a new tab with the given name. The tab is then added to this
	 * JTabbedPane.
	 * 
	 * @param name
	 *            The name of the tab that is to contain the new
	 *            WorkareaSceneScroll.
	 */
	public void createNewCanvasTab(String name)
	{
		WorkareaSceneScroll canvasScroll = new WorkareaSceneScroll(name);

		// Creates the image for the tab
		ImageIcon icon = ImageLocator.getImageIconObject("java");

		int width = (int) (PrimeMain1.width * 0.60);
		int height = (int) (PrimeMain1.width * 0.60);


		this.setPreferredSize(new Dimension(width, height));

		this.addTab(name, icon, canvasScroll);
	}
}
