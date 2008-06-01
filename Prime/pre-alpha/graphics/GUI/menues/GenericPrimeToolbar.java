/**
 * 
 */
package graphics.GUI.menues;

import java.awt.BorderLayout;
import javax.swing.*;
import actions.*;

/**
 * The GenericPrimeToolbar represents a generic toolbar for the program.
 * This is where the buttons at the top of the program screen are created and
 * added.
 * 
 * GenericPrimeToolbar is an extention of the JMenuBar class.
 *
 * @author Bahram Malaekeh
 * 
 */
@SuppressWarnings("serial")
public class GenericPrimeToolbar extends JMenuBar
{
	
	/**
	 * Constructor for the GenericPrimeToolbar class.
	 * Here the different parts of the toolbar are initiated.
	 */
	public GenericPrimeToolbar()
	{
		initFileToolBar();
		
		this.add(new JToolBar.Separator());
		
		initEditToolBar();
	}

	
	
	private JToolBar fileToolBar,editToolBar;
	
	
	// SETUP OF THE TOOLBAR
	
	/**
	 * This function creates the File toolbar. 
	 * 
	 * TODO - Get it working with the panel.
	 * 
	 */
	private void initFileToolBar()
	{
		fileToolBar = new JToolBar();
		fileToolBar.setFloatable(false);
		
		ActionNew newFile = new ActionNew("New",
				createImageIcon("images/buttonIcons/new.jpg"));
		
		ActionOpenfile openFile = new ActionOpenfile("Open File",
				createImageIcon("images/buttonIcons/open.jpg"));
		
		ActionSave save = new ActionSave("Save",
				createImageIcon("images/buttonIcons/save.jpg"));
		
		ActionSaveAll saveAll = new ActionSaveAll("Save All",
				createImageIcon("images/buttonIcons/save_all.jpg"));
		
		
		fileToolBar.add(newFile);
		fileToolBar.add(openFile);
		fileToolBar.add(save);
		fileToolBar.add(saveAll);

		
		
		this.add(fileToolBar, BorderLayout.WEST);

	}
	
	
	
	
	/**
	 * This function creates the File toolbar. 
	 * 
	 * TODO - Get it working with the panel.
	 * 
	 */
	private void initEditToolBar()
	{
		editToolBar = new JToolBar("Editing");
		editToolBar.setFloatable(false);
		
		ActionCut cut = new ActionCut("Cut",
				createImageIcon("images/buttonIcons/cut.jpg"));
		
		ActionCopy openFile = new ActionCopy("Copy",
				createImageIcon("images/buttonIcons/copy.jpg"));
		
		ActionPaste save = new ActionPaste("Paste",
				createImageIcon("images/buttonIcons/paste.jpg"));
		
		
		editToolBar.add(cut);
		editToolBar.add(openFile);
		editToolBar.add(save);
		
		this.add(editToolBar);
		
	}

	
	
	
	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIcon(String path) 
	{
	    java.net.URL imgURL = getClass().getResource(path);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL);
	    } else {
	        System.err.println("Couldn't find file: " + path);
	        return null;
	    }
	}

}