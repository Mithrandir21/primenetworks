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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import managment.CanvasManagment;
import managment.FileManagment;
import widgets.WorkareaCanvas;


/**
 * Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class WorkareaTabbed extends JTabbedPane implements ActionListener
{
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
					// errors from swing).
					currentCanvas.doRepaint();
				}

				// Sets the current working canvas to the canvas that is
				// actually shown in the JTabbedPane scrollPane.
				PrimeMain1.currentCanvas = currentCanvas;

				PrimeMain1.updatePropertiesCanvasArea(false);

				PrimeMain1.runCanvasObjectCheck();
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

		createNewCanvasTab(canvasScroll, -1);
	}



	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param canvas
	 * @param Name
	 */
	public void updateCanvasName(WorkareaCanvas canvas, String Name)
	{
		// Check whether or not there exists a tab with the given canvas name
		if ( existsTabWithGivenName(canvas.getCanvasName()) )
		{
			// This is the current number of tabs
			int arraySize = this.getTabCount();

			// Goes through the list of tab contents until it finds one that matches the given button name.
			for ( int i = 0; i < arraySize; i++ )
			{
				// Gets the tab component for the current tab.
				JPanel tabPanel = (JPanel) this.getTabComponentAt(i);

				// Gets the JLabel that contains the name of the WorkareaCanvas in the tab
				JLabel label = (JLabel) tabPanel.getComponent(0);

				// If the JLabel text is the same as the given string
				if ( label.getText().equals(canvas.getCanvasName()) )
				{
					// Changes the label of the tab
					if ( FileManagment.changeFileName(canvas, Name) )
					{
						label.setText(Name);
					}

					PrimeMain1.updatePropertiesCanvasArea(true);

					return;
				}
			}
		}
	}




	/**
	 * This function creates a new JPanel that will be added as the Tab for the given {@link WorkareaSceneScroll}. There
	 * will be placed an image in the JPanel which will be able to close the tab when pressed.
	 * 
	 * @param canvasScroll
	 *            The {@link WorkareaSceneScroll} that will be placed inside a Tab with a custom Tab fan.
	 * @param tabIndex
	 */
	public void createNewCanvasTab(WorkareaSceneScroll canvasScroll, int tabIndex)
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


		if ( tabIndex == -1 )
		{
			// Add the tab to the tabbed pane.
			this.addTab(name, canvasScroll);

			// Instead of using a String/Icon combination for the tab, use our panel instead.
			this.setTabComponentAt(this.getTabCount() - 1, tab);
		}
		else
		{
			// Add the tab to the tabbed pane.
			this.insertTab(name, null, canvasScroll, null, tabIndex);

			// Instead of using a String/Icon combination for the tab, use our panel instead.
			this.setTabComponentAt(tabIndex, tab);
		}


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
	 * @param canvasName
	 *            The name of {@link WorkareaCanvas} tab to be removed.
	 */
	public int indexOfTabWithCanvas(String canvasName)
	{
		// This is the current number of tabs
		int arraySize = this.getTabCount();

		// Goes through the list of tab contents until it finds one that matches the given button name.
		for ( int i = 0; i < arraySize; i++ )
		{
			// Gets the tab component for the current tab.
			JPanel tabPanel = (JPanel) this.getTabComponentAt(i);

			// Gets the JLabel that contains the name of the WorkareaCanvas in the tab
			JLabel label = (JLabel) tabPanel.getComponent(0);

			// If the JLabel text is the same as the given string
			if ( label.getText().equals(canvasName) )
			{
				// Returns the index of the tab
				return i;
			}
		}


		return -1;
	}



	/**
	 * This function removes the Tab with the given name from this JTabbedPane. It can also check, if the given boolean
	 * is set to true, check the {@link WorkareaCanvas} which is inside the tab. If the {@link WorkareaCanvas} has been
	 * altered and not saved, it will ask the user if they wish to save the {@link WorkareaCanvas}.
	 * 
	 * @param canvasName
	 *            The name of {@link WorkareaCanvas} tab to be removed.
	 */
	public void removeTabWithCanvas(String canvasName, boolean verify)
	{
		// WorkareaSceneScroll test = null;
		//
		// // This is the current number of tabs
		// int arraySize = this.getTabCount();
		//
		// // Goes through the list of tab contents until it finds one that
		// // matches the given button name.
		// for ( int i = 0; i < arraySize; i++ )
		// {
		// System.out.println(this.getComponent(i).getClass());
		// if ( this.getComponent(i) instanceof WorkareaSceneScroll )
		// {
		// test = (WorkareaSceneScroll) this.getComponent(i);
		//
		//
		// String tabName = test.getName();
		// Check whether or not there exists a tab with the given canvas name
		if ( existsTabWithGivenName(canvasName) )
		{
			// This is the current number of tabs
			int arraySize = this.getTabCount();

			// Goes through the list of tab contents until it finds one that matches the given button name.
			for ( int i = 0; i < arraySize; i++ )
			{
				// Gets the tab component for the current tab.
				JPanel tabPanel = (JPanel) this.getTabComponentAt(i);

				// Gets the JLabel that contains the name of the WorkareaCanvas in the tab
				JLabel label = (JLabel) tabPanel.getComponent(0);

				// If the JLabel text is the same as the given string
				if ( label.getText().equals(canvasName) )
				{
					WorkareaSceneScroll test = (WorkareaSceneScroll) this.getComponentAt(i);

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
							this.removeTabAt(i);
							CanvasManagment.removeWorkareaCanvas(test.getCanvas());
							return;
						}
						// Dont save.
						else if ( answer == 1 )
						{
							this.removeTabAt(i);
							CanvasManagment.removeWorkareaCanvas(test.getCanvas());
							return;
						}
						else
						{
							return;
						}
					}
					else
					{
						this.removeTabAt(i);
						CanvasManagment.removeWorkareaCanvas(test.getCanvas());
						return;
					}
				}
			}
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
		// This is the current number of tabs
		int arraySize = this.getTabCount();

		// Goes through the list of tab contents until it finds one that matches the given button name.
		for ( int i = 0; i < arraySize; i++ )
		{
			// Gets the tab component for the current tab.
			JPanel tabPanel = (JPanel) this.getTabComponentAt(i);

			// Gets the JLabel that contains the name of the WorkareaCanvas in the tab
			JLabel label = (JLabel) tabPanel.getComponent(0);

			if ( label.getText() != null && label.getText().equalsIgnoreCase(canvasName) )
			{
				return true;
			}
		}


		return false;
	}
}
