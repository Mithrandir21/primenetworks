/**
 * 
 */
package graphics.GUI.workareaCanvas;


import graphics.ImageLocator;
import graphics.PrimeMain1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import managment.FileManagment;


/**
 * Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class WorkareaTabbed extends JTabbedPane implements ActionListener
{
	private ArrayList<WorkareaSceneScroll> canvases = new ArrayList<WorkareaSceneScroll>();



	/**
	 * A constructor for this class that add a changeListener that will call doRepaint on the WorkareaCanvas when any
	 * change occurs.
	 */
	public WorkareaTabbed()
	{

		addChangeListener(new ChangeListener()
		{
			// This method is called whenever the selected tab changes
			public void stateChanged(ChangeEvent evt)
			{
				// Gets the JTabbedPane that the event comes from.
				JTabbedPane pane = (JTabbedPane) evt.getSource();

				// Gets the scrollPane that the JTabbedPane contains.
				WorkareaSceneScroll currentScrollPane = (WorkareaSceneScroll) pane.getSelectedComponent();

				// Gets the workareaCanvas that the scrollPane contains.
				WorkareaCanvas currentCanvas = null;

				if ( currentScrollPane != null )
				{
					currentCanvas = currentScrollPane.getCanvas();
					// Repaints the entire canvas.(Avoids NullPointerExeption
					// errors
					// from swing).
					currentCanvas.doRepaint();
				}

				// Sets the current working canvas to the canvas that is
				// actually shown in the
				// JTabbedPane scrollPane.
				PrimeMain1.currentCanvas = currentCanvas;

				PrimeMain1.updatePropertiesCanvasArea();
			}
		});


		int width = (int) (PrimeMain1.width * 0.60);
		int height = (int) (PrimeMain1.width * 0.60);
		this.setPreferredSize(new Dimension(width, height));
	}


	/**
	 * The function creates a new WorkareaSceneScroll and places that component within a new tab with the given name.
	 * The tab is then added to this JTabbedPane.
	 * 
	 * @param name
	 *            The name of the tab that is to contain the new WorkareaSceneScroll.
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



	/**
	 * The function creates a new WorkareaSceneScroll and places that component within a new tab with the given name.
	 * The tab is then added to this JTabbedPane.
	 * 
	 * @param canvas
	 *            The canvas that will be placed inside the new WorkareaSceneScroll.
	 */
	public void createNewCanvasTab(WorkareaCanvas canvas)
	{
		WorkareaSceneScroll canvasScroll = new WorkareaSceneScroll(canvas);

		createNewCanvasTab(canvasScroll);
	}




	/**
	 * This function creates a new JPanel that will be added as the Tab for the given {@link WorkareaSceneScroll}. There
	 * will be placed an image in the JPanel which will be able to close the tab when pressed.
	 * 
	 * @param canvasScroll
	 *            The {@link WorkareaSceneScroll} that will be placed inside a Tab with a custom Tab fane.
	 */
	public void createNewCanvasTab(WorkareaSceneScroll canvasScroll)
	{
		// The ImageIcon that will be shown and will remove a tab if pressed
		ImageIcon closeXIcon = ImageLocator.getImageIconObject("Close");

		// The dimensions of the new button.
		Dimension closeButtonSize = new Dimension(closeXIcon.getIconWidth() + 5, closeXIcon.getIconHeight() + 5);

		String name = canvasScroll.getCanvas().getCanvasName();


		// The actual panel that will be the component panel which the JLabel
		// and the button
		// will be placed inside.
		JPanel tab = new JPanel();

		// The Panel is not opaque
		tab.setOpaque(false);

		// The JLabel that will show the name of the tab
		JLabel tabLabel = new JLabel(name);

		// The button that will be represented by the ImageIcon
		JButton tabCloseButton = new JButton(closeXIcon);
		tabCloseButton.setName(name);
		tabCloseButton.setPreferredSize(closeButtonSize);
		tabCloseButton.addActionListener(this);

		// Adds the label and then the button to the panel.
		tab.add(tabLabel, BorderLayout.WEST);
		tab.add(tabCloseButton, BorderLayout.EAST);

		// Sets the name of the JScrollPane parameter
		canvasScroll.setName(name);
		// Adds the JScrollPane to the list of contents of the different
		// tabs
		canvases.add(canvasScroll);

		// Add the tab to the tabbed pane.
		this.addTab(name, canvasScroll);

		// Instead of using a String/Icon combination for the tab,
		// use our panel instead.
		this.setTabComponentAt(this.getTabCount() - 1, tab);


		int width = (int) (PrimeMain1.width * 0.60);
		int height = (int) (PrimeMain1.width * 0.60);
		this.setPreferredSize(new Dimension(width, height));
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

			removeTabWithCanvas(contentName, true);
		}
	}



	/**
	 * This function removes the Tab with the given name from this JTabbedPane. It can also check, if the given boolean
	 * is set to true, check the {@link WorkareaCanvas} which is inside the tab. If the {@link WorkareaCanvas} has been
	 * altered and not saved, it will ask the user if they wish to save the {@link WorkareaCanvas}.
	 * 
	 * @param canvas
	 *            The name of {@link WorkareaCanvas} tad to be removed.
	 */
	public void removeTabWithCanvas(String canvasName, boolean verify)
	{
		WorkareaSceneScroll test = null;

		// This is the current number of tabs
		int arraySize = canvases.size();

		// Goes through the list of tab contents until it finds one that
		// matches the given button name.
		for ( int i = 0; i < arraySize; i++ )
		{
			test = canvases.get(i);

			String tabName = test.getName();

			// If the name of the button and the name of the content match,
			// the button to close that
			// tab with the given content has been pressed and the tab is
			// removed.
			if ( tabName != null && tabName.equals(canvasName) )
			{
				if ( verify )
				{
					// The options the user will be presented with.
					Object[] options = { "Save", "Dont save", "Cancel" };

					// Asks the user whether or not to save
					int answer = JOptionPane.showOptionDialog(null,
							"This canvas has not been saved, do you want to save this canvas?", "Save",
							JOptionPane.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE, null, options, null);

					// Save
					if ( answer == 0 )
					{
						// Saves the canvas to a file.
						FileManagment.saveWorkareaCanvas(test.getCanvas());
						this.remove(test);
						canvases.remove(i);
						return;
					}
					// Dont save.
					else if ( answer == 1 )
					{
						this.remove(test);
						canvases.remove(i);
						return;
					}
					else
					{
						return;
					}
				}
				else
				{
					this.remove(test);
					canvases.remove(i);
					return;
				}
			}

			test = null;
		}
	}




	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param canvasName
	 * @return Returns true if there exists a tab with the given name and false there does not.
	 */
	public boolean existsTabWithGivenName(String canvasName)
	{
		WorkareaSceneScroll test = null;

		// This is the current number of tabs
		int arraySize = canvases.size();

		// Goes through the list of tab contents until it finds one that
		// matches the given button name.
		for ( int i = 0; i < arraySize; i++ )
		{
			test = canvases.get(i);

			String names = test.getName();

			// If the name of the button and the name of the content match,
			// the button to close that
			// tab with the given content has been pressed and the tab is
			// removed.
			if ( names != null && names.equalsIgnoreCase(canvasName) )
			{
				return true;
			}
		}


		return false;
	}
}
