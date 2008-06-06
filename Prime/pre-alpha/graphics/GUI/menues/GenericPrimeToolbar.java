/**
 * 
 */
package graphics.GUI.menues;

import graphics.ImageLocator;

import java.awt.BorderLayout;
import javax.swing.*;
import actions.*;
import graphics.PrimeMain1;

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
	// Temp imageIcon that will hold the icon of the created button.
	private ImageIcon tempIcon = null;
	
	
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
		
		
		tempIcon = ImageLocator.createImageIcon(this.getClass().getResource("images/buttonIcons/new.jpg"));
		ActionNew newFile = new ActionNew("New",tempIcon);
		
		tempIcon = ImageLocator.createImageIcon(this.getClass().getResource("images/buttonIcons/open.jpg"));
		ActionOpenfile openFile = new ActionOpenfile("Open File",tempIcon);
		
		tempIcon = ImageLocator.createImageIcon(this.getClass().getResource("images/buttonIcons/save.jpg"));
		ActionSave save = new ActionSave("Save",tempIcon);
		
		tempIcon = ImageLocator.createImageIcon(this.getClass().getResource("images/buttonIcons/save_all.jpg"));
		ActionSaveAll saveAll = new ActionSaveAll("Save All",tempIcon);
		
		
		fileToolBar.add(newFile);
		fileToolBar.add(openFile);
		fileToolBar.add(save);
		fileToolBar.add(saveAll);

		
		
		this.add(fileToolBar, BorderLayout.WEST);
		tempIcon = null;
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
		
		
		tempIcon = ImageLocator.getImageIconObject("Cut");
		ActionCut cut = new ActionCut("Cut",tempIcon);

		tempIcon = ImageLocator.createImageIcon(this.getClass().getResource("images/buttonIcons/copy.jpg"));
		ActionCopy openFile = new ActionCopy("Copy",tempIcon);

		tempIcon = ImageLocator.createImageIcon(this.getClass().getResource("images/buttonIcons/paste.jpg"));
		ActionPaste save = new ActionPaste("Paste",tempIcon);
		
		
		editToolBar.add(cut);
		editToolBar.add(openFile);
		editToolBar.add(save);
		
		this.add(editToolBar);
		tempIcon = null;
	}
}