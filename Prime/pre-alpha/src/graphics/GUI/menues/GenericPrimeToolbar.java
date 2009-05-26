/**
 * 
 */
package graphics.GUI.menues;


import graphics.ImageLocator;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import actions.ActionCopy;
import actions.ActionCut;
import actions.ActionNew;
import actions.ActionOpenfile;
import actions.ActionPaste;
import actions.ActionRoom;
import actions.ActionSave;
import actions.ActionSaveAll;
import actions.ActionUpdate;


/**
 * The GenericPrimeToolbar represents a generic toolbar for the program. This is where the buttons at the top of the
 * program screen are created and added. GenericPrimeToolbar is an extention of the JMenuBar class.
 * 
 * @author Bahram Malaekeh
 */
@SuppressWarnings("serial")
public class GenericPrimeToolbar extends JMenuBar
{
	// Temp imageIcon that will hold the icon of the created button.
	private ImageIcon tempIcon = null;


	/**
	 * Constructor for the GenericPrimeToolbar class. Here the different parts of the toolbar are initiated.
	 */
	public GenericPrimeToolbar()
	{
		initFileToolBar();

		this.add(new JToolBar.Separator());

		initEditToolBar();
		
		this.add(new JToolBar.Separator());
		
		initNetworkAction();
	}



	private JToolBar fileToolBar, editToolBar, networkToolBar;


	// SETUP OF THE TOOLBAR

	/**
	 * This function creates the File JToolbar. 
	 */
	private void initFileToolBar()
	{
		fileToolBar = new JToolBar();
		fileToolBar.setFloatable(false);


		tempIcon = ImageLocator.getImageIconObject("New");
		ActionNew newFile = new ActionNew("New", tempIcon);

		tempIcon = ImageLocator.getImageIconObject("Open");
		ActionOpenfile openFile = new ActionOpenfile("Open File", tempIcon);

		tempIcon = ImageLocator.getImageIconObject("Save");
		ActionSave save = new ActionSave("Save", tempIcon);

		tempIcon = ImageLocator.getImageIconObject("Save_all");
		ActionSaveAll saveAll = new ActionSaveAll("Save All", tempIcon);


		fileToolBar.add(newFile);
		fileToolBar.add(openFile);
		fileToolBar.add(save);
		fileToolBar.add(saveAll);



		this.add(fileToolBar, BorderLayout.WEST);
		tempIcon = null;
	}




	/**
	 * This function creates the File JToolbar.
	 */
	private void initEditToolBar()
	{
		editToolBar = new JToolBar("Editing");
		editToolBar.setFloatable(false);


		tempIcon = ImageLocator.getImageIconObject("Cut");
		ActionCut cut = new ActionCut("Cut", tempIcon);

		tempIcon = ImageLocator.getImageIconObject("Copy");
		ActionCopy openFile = new ActionCopy("Copy", tempIcon);

		tempIcon = ImageLocator.getImageIconObject("Paste");
		ActionPaste save = new ActionPaste("Paste", tempIcon);


		editToolBar.add(cut);
		editToolBar.add(openFile);
		editToolBar.add(save);

		this.add(editToolBar);
		tempIcon = null;
	}
	
	
	
	
	/**
	 * This function creates the Network functions JToolbar.
	 */
	private void initNetworkAction()
	{
		networkToolBar = new JToolBar("Networking");
		networkToolBar.setFloatable(false);
		
		
		tempIcon = ImageLocator.getImageIconObject("Update");
		ActionUpdate update = new ActionUpdate("Update", tempIcon);
		
		tempIcon = ImageLocator.getImageIconObject("Room");
		ActionRoom room = new ActionRoom("Room", tempIcon);
		JToggleButton roomButton = new JToggleButton(room);
		
		networkToolBar.add(update);
		networkToolBar.add(roomButton);
		
		
		this.add(networkToolBar);
		tempIcon = null;
	}
}