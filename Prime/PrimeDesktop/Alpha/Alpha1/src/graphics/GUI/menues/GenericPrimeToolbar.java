/**
 * 
 */
package graphics.GUI.menues;


import graphics.ImageLocator;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import actions.ActionCopy;
import actions.ActionCut;
import actions.ActionPaste;
import actions.systemActions.ActionConnection;
import actions.systemActions.ActionExport;
import actions.systemActions.ActionNew;
import actions.systemActions.ActionObjectEditing;
import actions.systemActions.ActionOpenfile;
import actions.systemActions.ActionRedo;
import actions.systemActions.ActionRoom;
import actions.systemActions.ActionSave;
import actions.systemActions.ActionSaveAll;
import actions.systemActions.ActionUndo;
import actions.systemActions.ActionUpdate;


/**
 * The GenericPrimeToolbar represents a generic toolbar for the program. This is
 * where the buttons at the top of the program screen are created and added.
 * GenericPrimeToolbar is an extention of the JMenuBar class.
 * 
 * @author Bahram Malaekeh
 */
@SuppressWarnings("serial")
public class GenericPrimeToolbar extends JMenuBar
{
	// Temp imageIcon that will hold the icon of the created button.
	private ImageIcon tempIcon = null;


	/**
	 * Constructor for the GenericPrimeToolbar class. Here the different parts
	 * of the toolbar are initiated.
	 */
	public GenericPrimeToolbar()
	{
		initFileToolBar();

		this.add(new JToolBar.Separator());

		initEditToolBar();

		this.add(new JToolBar.Separator());

		initStepsActions();

		this.add(new JToolBar.Separator());

		initCanvasActions();

		this.add(new JToolBar.Separator());

		initNetworkAction();

		this.add(new JToolBar.Separator());

		initEditingAction();
	}



	private JToolBar fileToolBar, editToolBar, stepsToolBar, canvasToolBar,
			networkToolBar, editingToolBar;


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

		this.add(editToolBar, BorderLayout.WEST);
		tempIcon = null;
	}



	/**
	 * This function creates the Steps functions JToolbar.
	 */
	private void initStepsActions()
	{
		stepsToolBar = new JToolBar("Steps");
		stepsToolBar.setFloatable(false);

		tempIcon = ImageLocator.getImageIconObject("Undo");
		ActionUndo undo = new ActionUndo("Undo", tempIcon);


		tempIcon = ImageLocator.getImageIconObject("Redo");
		ActionRedo redo = new ActionRedo("Redo", tempIcon);


		stepsToolBar.add(undo);
		stepsToolBar.add(redo);


		this.add(stepsToolBar, BorderLayout.WEST);
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
		JButton updateButton = new JButton(update);

		tempIcon = ImageLocator.getImageIconObject("Export");
		ActionExport export = new ActionExport("Export", tempIcon);
		JButton exportButton = new JButton(export);

		networkToolBar.add(updateButton);
		networkToolBar.add(exportButton);


		this.add(networkToolBar, BorderLayout.WEST);
		tempIcon = null;
	}


	/**
	 * This function creates the Canvas functions JToolbar.
	 */
	private void initCanvasActions()
	{
		canvasToolBar = new JToolBar("Canvas");
		canvasToolBar.setFloatable(false);


		tempIcon = ImageLocator.getImageIconObject("Connection");
		ActionConnection con = new ActionConnection("Connect", tempIcon);
		JToggleButton connectionButton = new JToggleButton(con);

		tempIcon = ImageLocator.getImageIconObject("Room");
		ActionRoom room = new ActionRoom("Room", tempIcon);
		JToggleButton roomButton = new JToggleButton(room);


		canvasToolBar.add(connectionButton);
		canvasToolBar.add(roomButton);


		this.add(canvasToolBar, BorderLayout.WEST);
		tempIcon = null;
	}


	/**
	 * This function creates the Editing functions JToolbar.
	 */
	private void initEditingAction()
	{
		editingToolBar = new JToolBar("Editing");
		editingToolBar.setFloatable(false);

		tempIcon = ImageLocator.getImageIconObject("Objects");
		ActionObjectEditing editing = new ActionObjectEditing("Objects",
				tempIcon);
		JButton editingButton = new JButton(editing);


		editingToolBar.add(editingButton);

		this.add(editingToolBar, BorderLayout.WEST);
		tempIcon = null;
	}
}