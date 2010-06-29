/**
 * 
 */
package graphics.GUI.menues;


import graphics.ImageLocator;
import graphics.PrimeMain;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import actions.canvasActions.ActionDeleteAllConnections;
import actions.canvasActions.ActionDeleteAllRooms;
import actions.canvasActions.ActionDeleteAllWidgets;
import actions.canvasActions.ActionDeleteEverything;
import actions.systemActions.ActionAbout;
import actions.systemActions.ActionCopy;
import actions.systemActions.ActionCut;
import actions.systemActions.ActionExitSystem;
import actions.systemActions.ActionExportCanvasAsImage;
import actions.systemActions.ActionExportNetwork;
import actions.systemActions.ActionExportStandardObjects;
import actions.systemActions.ActionImportNetwork;
import actions.systemActions.ActionImportStandardObjects;
import actions.systemActions.ActionNew;
import actions.systemActions.ActionOpenVisualEdit;
import actions.systemActions.ActionOpenfile;
import actions.systemActions.ActionPaste;
import actions.systemActions.ActionRedo;
import actions.systemActions.ActionSave;
import actions.systemActions.ActionSaveAll;
import actions.systemActions.ActionUndo;
import actions.systemActions.ActionUpdate;
import actions.toolbar.ActionAllMessageView;
import actions.toolbar.ActionConnectionMessage;
import actions.toolbar.ActionHardwareMessage;
import actions.toolbar.ActionNetworkMessage;
import actions.toolbar.ActionSettings;
import actions.toolbar.ActionSoftwareMessage;


/**
 * This class creates the a generic {@link JMenuBar JMenuBar} for the main
 * windows of the program. This JMenuBar will include the menues "File", "Edit",
 * "View", "Tools" and "Help". (More menues should be added at a later point.)
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class GenericPrimeMenuBar extends JMenuBar
{



	ImageIcon icon = ImageLocator.getImageIconObject("java");

	// The menu buttons at the top of the screen
	private JMenu file, edit, view, tools, help;


	/**
	 * This function class all the functions that initiate the creation of the
	 * different menues.
	 */
	public GenericPrimeMenuBar()
	{
		initMenuFile();

		initMenuEdit();

		initMenuView();

		initMenuTools();

		initMenuHelp();
	}




	// SETUP FOR THE MENUES

	/**
	 * This function creates the JMenu "File". It adds the actions "New",
	 * "Open", "Save", "Save All" and "Exit". (More actions will be added at a
	 * later stage.)
	 */
	private void initMenuFile()
	{
		// File menu
		file = new JMenu(PrimeMain.texts.getString("fileLabel"));
		file.setMnemonic('F');

		// Adds different items to the "File" menu

		JMenuItem newFile = new JMenuItem(new ActionNew(PrimeMain.texts
				.getString("newLabel")));
		newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				KeyEvent.CTRL_MASK));
		newFile.setIcon(null);
		file.add(newFile);

		JMenuItem openFile = new JMenuItem(new ActionOpenfile(PrimeMain.texts
				.getString("openFileLabel")));
		openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				KeyEvent.CTRL_MASK));
		openFile.setIcon(null);
		openFile.setEnabled(false);
		file.add(openFile);

		JMenuItem saveFile = new JMenuItem(new ActionSave(PrimeMain.texts
				.getString("saveLabel")));
		saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				KeyEvent.CTRL_MASK));
		saveFile.setIcon(null);
		file.add(saveFile);

		JMenuItem saveAll = new JMenuItem(new ActionSaveAll(PrimeMain.texts
				.getString("saveAllLabel")));
		saveAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				KeyEvent.CTRL_MASK));
		saveAll.setIcon(null);
		file.add(saveAll);

		// Adds a separator to the menu
		file.addSeparator();

		JMenu importSubMenu = new JMenu(PrimeMain.texts
				.getString("importSubMenuLabel"));


		JMenuItem importNetwork = new JMenuItem(new ActionImportNetwork(
				PrimeMain.texts.getString("importNetworkLabel")));
		importSubMenu.add(importNetwork);

		
		JMenuItem importStandardObjectsList = new JMenuItem(
				new ActionImportStandardObjects(PrimeMain.texts
						.getString("importStandardObjectsList")));
		importSubMenu.add(importStandardObjectsList);


		file.add(importSubMenu);

		// Adds a separator to the menu
		file.addSeparator();

		JMenuItem exit = new JMenuItem(new ActionExitSystem(PrimeMain.texts
				.getString("exitLabel")));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				KeyEvent.CTRL_MASK));
		exit.setIcon(null);
		file.add(exit);


		this.add(file);
	}

	/**
	 * This function creates the JMenu "File". It adds the actions "Undo",
	 * "Cut", "Copy" and "Paste". (More actions will be added at a later stage.)
	 */
	private void initMenuEdit()
	{
		// Edit menu
		edit = new JMenu(PrimeMain.texts.getString("editLabel"));
		edit.setMnemonic('E');

		// Adds different items to the "Edit" menu
		JMenuItem undo = new JMenuItem(new ActionUndo(PrimeMain.texts
				.getString("undoLabel")));
		undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
				KeyEvent.CTRL_MASK));
		undo.setIcon(null);
		edit.add(undo);

		JMenuItem redo = new JMenuItem(new ActionRedo(PrimeMain.texts
				.getString("redoLabel")));
		redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
				KeyEvent.CTRL_MASK));
		redo.setIcon(null);
		edit.add(redo);

		// Adds a separator to the menu
		edit.addSeparator();

		JMenuItem cut = new JMenuItem(new ActionCut(PrimeMain.texts
				.getString("cutLabel")));
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				KeyEvent.CTRL_MASK));
		cut.setIcon(null);
		edit.add(cut);

		JMenuItem copy = new JMenuItem(new ActionCopy(PrimeMain.texts
				.getString("copyLabel")));
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				KeyEvent.CTRL_MASK));
		copy.setIcon(null);
		edit.add(copy);

		JMenuItem paste = new JMenuItem(new ActionPaste(PrimeMain.texts
				.getString("pasteLabel")));
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				KeyEvent.CTRL_MASK));
		paste.setIcon(null);
		edit.add(paste);


		JMenu submenu = new JMenu(PrimeMain.texts
				.getString("deleteSubMenuLabel"));

		JMenuItem removeAllWidgets = new JMenuItem(new ActionDeleteAllWidgets(
				PrimeMain.texts.getString("deleteAllWidgetsLabel")));
		removeAllWidgets.setIcon(null);
		submenu.add(removeAllWidgets);


		JMenuItem removeAllConnections = new JMenuItem(new ActionDeleteAllConnections(
				PrimeMain.texts.getString("deleteAllConnectionsLabel")));
		removeAllConnections.setIcon(null);
		submenu.add(removeAllConnections);
		
		
		JMenuItem removeAllRooms = new JMenuItem(new ActionDeleteAllRooms(
				PrimeMain.texts.getString("deleteAllRoomsLabel")));
		removeAllRooms.setIcon(null);
		submenu.add(removeAllRooms);
		
		
		// Adds a separator to the submenu
		submenu.addSeparator();
		
		
		JMenuItem removeEverything = new JMenuItem(new ActionDeleteEverything(
				PrimeMain.texts.getString("deleteEverythingLabel")));
		removeEverything.setIcon(null);
		submenu.add(removeEverything);

		// Adds a separator to the menu
		edit.addSeparator();

		edit.add(submenu);


		this.add(edit);
	}


	/**
	 * This function creates the JMenu "Views". (More actions will be added at a
	 * later stage.)
	 */
	private void initMenuView()
	{
		// View menu
		view = new JMenu(PrimeMain.texts.getString("viewLabel"));
		view.setMnemonic('V');


		JMenu submenu = new JMenu(PrimeMain.texts
				.getString("messagesSupermenuLabel"));


		JMenuItem allViews = new JMenuItem(new ActionAllMessageView(
				PrimeMain.texts.getString("messagesSupermenuAllMessages")));
		allViews.setIcon(null);
		submenu.add(allViews);


		// Adds a separator to the menu
		submenu.addSeparator();

		JMenuItem netMsg = new JMenuItem(new ActionNetworkMessage(
				PrimeMain.texts.getString("messagesSupermenuNetworkMessages")));
		netMsg.setIcon(null);
		submenu.add(netMsg);


		JMenuItem conMsg = new JMenuItem(new ActionConnectionMessage(
				PrimeMain.texts
						.getString("messagesSupermenuConnectionMessages")));
		conMsg.setIcon(null);
		submenu.add(conMsg);


		JMenuItem softMsg = new JMenuItem(
				new ActionSoftwareMessage(PrimeMain.texts
						.getString("messagesSupermenuSoftwareMessages")));
		softMsg.setIcon(null);
		submenu.add(softMsg);


		JMenuItem hardMsg = new JMenuItem(
				new ActionHardwareMessage(PrimeMain.texts
						.getString("messagesSupermenuHardwareMessages")));
		hardMsg.setIcon(null);
		submenu.add(hardMsg);



		JMenuItem update = new JMenuItem(new ActionUpdate(PrimeMain.texts
				.getString("actionUpdateNetworkLabel")));
		update.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,
				KeyEvent.CTRL_MASK));
		update.setIcon(null);
		submenu.add(update);



		view.add(submenu);

		// Adds a separator to the menu
		view.addSeparator();

		view.add(update);



		this.add(view);
	}



	/**
	 * This function creates the JMenu "Tools". (More actions will be added at a
	 * later stage.)
	 */
	private void initMenuTools()
	{
		// Tools menu
		tools = new JMenu(PrimeMain.texts.getString("toolsLabel"));
		tools.setMnemonic('T');


		JMenuItem openVisualEdit = new JMenuItem(new ActionOpenVisualEdit(
				PrimeMain.texts.getString("visualEditMenuLabel")));
		openVisualEdit.setIcon(null);
		tools.add(openVisualEdit);


		// Adds a separator to the menu
		tools.addSeparator();

		JMenu exportSubMenu = new JMenu(PrimeMain.texts
				.getString("exportSubMenuLabel"));
		
		
		JMenuItem exportNetwork = new JMenuItem(new ActionExportNetwork(
				PrimeMain.texts.getString("exportCanvasMenuLabel")));
		exportSubMenu.add(exportNetwork);
		
		

		JMenuItem exportNetworkAsImage = new JMenuItem(
				new ActionExportCanvasAsImage(PrimeMain.texts
						.getString("exportCanvasAsImageMenuLabel")));
		exportSubMenu.add(exportNetworkAsImage);
		


		JMenuItem exportStandardObjectsList = new JMenuItem(
				new ActionExportStandardObjects(PrimeMain.texts
						.getString("exportStandardObjectsListMenuLabel")));
		exportSubMenu.add(exportStandardObjectsList);




		tools.add(exportSubMenu);
		
		// Adds a separator to the menu
		tools.addSeparator();


		JMenuItem openSettings = new JMenuItem(new ActionSettings(
				PrimeMain.texts.getString("settingsMenuLabel")));
		openSettings.setIcon(null);
		tools.add(openSettings);






		this.add(tools);
	}


	/**
	 * This function creates the JMenu "Help". (More actions will be added at a
	 * later stage.)
	 */
	private void initMenuHelp()
	{
		// Help menu
		help = new JMenu(PrimeMain.texts.getString("helpLabel"));
		help.setMnemonic('H');

		JMenuItem about = new JMenuItem(new ActionAbout(PrimeMain.texts
				.getString("aboutMenuLabel")));
		about.setIcon(null);
		help.add(about);

		this.add(help);
	}



	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIcon(String path)
	{
		java.net.URL imgURL = getClass().getResource(path);
		if ( imgURL != null )
		{
			return new ImageIcon(imgURL);
		}
		else
		{
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
}
