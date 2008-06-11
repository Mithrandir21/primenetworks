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

	public WorkareaTabbed()
	{
		createNewCanvasTab("AA");
		createNewCanvasTab("BB");

		System.out.println(PrimeMain1.canvases[0].getCanvasName());
		System.out.println(PrimeMain1.canvases[1].getCanvasName());


		addChangeListener(new ChangeListener()
		{
			// This method is called whenever the selected tab changes
			public void stateChanged(ChangeEvent evt)
			{
				JTabbedPane pane = (JTabbedPane) evt.getSource();
				
				pane.revalidate();
				pane.repaint();
				
				WorkareaSceneScroll temp = (WorkareaSceneScroll) pane.getSelectedComponent();

				WorkareaCanvas temp2 = temp.getCanvas();
				
				// Get current tab
				System.out.println(temp2.numberOfWidgetsOnTheScene);
			}
		});
	}



	public void createNewCanvasTab(String name)
	{
		WorkareaSceneScroll canvasScroll = new WorkareaSceneScroll(name);

		// Creates the image for the tab
		ImageIcon icon = ImageLocator.getImageIconObject("java");

		this.setPreferredSize(new Dimension(600, 500));

		addTab("Canvas", icon, canvasScroll);

		setSelectedIndex(this.getTabCount() - 1);
	}



}
