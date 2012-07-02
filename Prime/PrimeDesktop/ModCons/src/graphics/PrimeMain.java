/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010 Bahram Malaekeh
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package graphics;


import graphics.GUI.customNetworks.NetworkRulesFrame;
import graphics.GUI.customOSviews.osSelectionOverView;
import graphics.GUI.ghostGlass.GhostGlassPane;
import graphics.GUI.menues.GenericPrimeMenuBar;
import graphics.GUI.menues.GenericPrimeToolbar;
import graphics.GUI.messageArea.MessageTabbed;
import graphics.GUI.objectView.ObjectView;
import graphics.GUI.programGUI.PrimeSplashScreen;
import graphics.GUI.programGUI.TipOfDay;
import graphics.GUI.properties.PropertiesArea;
import graphics.GUI.rackOverview.RackOverview;
import graphics.GUI.selectArea.TabbedSelection;
import graphics.GUI.standardObjectEdit.StandardObjects;
import graphics.GUI.statusArea.PrimeStatusBar;
import graphics.GUI.userGroups.NetworkGroupsDialog;
import graphics.GUI.visualObjectCustomization.VisualCustomFrame;
import graphics.GUI.workareaCanvas.WorkareaTabbed;
import graphics.services.PrimeService;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ToolTipManager;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import logistical.LibraryLogging;
import managment.CanvasManagment;
import managment.CreateObjects;
import managment.DesktopCanvasManagment;
import managment.DesktopCustomLoggerFormatter;
import managment.DesktopFileManagment;
import managment.MakeStandardInternalComponents;
import managment.MakeStandardSoftware;
import managment.Settings;
import objects.Object;
import objects.rackUnits.Rack;
import objects.softwareObjects.OperatingSystem;

import org.jdesktop.swingx.JXMultiSplitPane;
import org.jdesktop.swingx.MultiSplitLayout;
import org.jdesktop.swingx.MultiSplitLayout.Node;

import plugins.pluginManagement.PluginMain;
import plugins.pluginManagement.StopPluginsJob;
import widgetManipulation.NetworkRules;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;


/**
 * Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.7 01/12/2011
 */
@SuppressWarnings("serial")
public class PrimeMain extends JFrame
{
	// The log for the the GUI part of the program
	public static Logger guiLog = Logger.getLogger("GUILogging");

	public static Logger ioLog = Logger.getLogger("IOLogging");

	public static Logger desktopProcLog = Logger
			.getLogger("desktopProcLogging");

	// Daemon services running
	private static PrimeService services;

	// The locale texts
	public static ResourceBundle texts;

	// Variables to place the height and width of the main screen.
	public static int width, height;

	// Main window panel setup
	private static JPanel toolbarPanel, selectionPanel;

	// The tab selection area where one can open projects and select new objects
	public static TabbedSelection tabSelection;

	// The JPanel where the properties of any selected object or network
	// overview is shown.
	public static JPanel propertiesPanel;

	// The JPanel that will show information about the objects and the network.
	public static JPanel messagesPanel;

	// The JPanel that will hold all the canvas.
	private static JPanel workareaPanel;

	public static WorkareaTabbed workTab;

	static JXMultiSplitPane multiSplitPane = new JXMultiSplitPane();

	// A simple border that is gray
	Border grayline = BorderFactory.createLineBorder(Color.GRAY);

	// An array that points to all the currently open canvases in the workarea.
	public static WorkareaCanvas[] canvases = new WorkareaCanvas[3];


	// A pointer to the currently open canvas that is displayed in the workarea.
	public static WorkareaCanvas currentCanvas = null;

	// An ImageIcon array that contains all the icons used in the system.
	public static ArrayList<ImageIcon> images = new ArrayList<ImageIcon>(120);

	// A pointer to where all standard internal components are kept.
	public static MakeStandardInternalComponents standard_internal_components;

	// A pointer to where all standard softwares are kept.
	public static MakeStandardSoftware standard_software;

	// This array contains the systems standard, unchangeable OperatingSystem.
	public static OperatingSystem[] system_standard_OS;

	// This array contains the systems custom OperatingSystems, the ones created
	// by Users.
	public static ArrayList<OperatingSystem> system_custom_OS = new ArrayList<OperatingSystem>();

	// The variable for the objects that are in view.
	public static ArrayList<ObjectView> objView = new ArrayList<ObjectView>(1);

	// The variable for the racks that are in view.
	public static ArrayList<RackOverview> rackView = new ArrayList<RackOverview>(
			1);

	// The variable for the view of the standard object view.
	public static StandardObjects stdObjView;

	// The variable for the view of the visual editing JDialog.
	public static VisualCustomFrame vcf;

	// The frame that shows the network rules
	public static NetworkRulesFrame rulesFrame;

	// The frame for the Operating System selection
	public static osSelectionOverView osSelect;

	// The JDialog that shows the networks groups
	public static NetworkGroupsDialog groupsDialog;

	// The arraylist of the systems standard Objects
	public static ArrayList<Object> objectlist = new ArrayList<Object>();

	// The HashMap that contains object ImageIcons
	public static HashMap<Class, ImageIcon> objectImageIcons = new HashMap<Class, ImageIcon>();

	// The "Copy widget" widget holder
	public static WidgetObject copyWidget = null;

	// The "Cut widget" widget holder
	public static WidgetObject cutWidget = null;

	// The standard rules object
	public static NetworkRules standardRules = null;

	// The GhostGlass that will show the moving device(for D'n'D).
	public static GhostGlassPane glassPane;

	// Get the current screen size
	public static Dimension scrnsize = Toolkit.getDefaultToolkit()
			.getScreenSize();

	// The listening agent that will listen to agent coms
	public static PluginMain pluginMan = new PluginMain();



	// Constructor
	public PrimeMain()
	{
		super("PrimeDesktop");

		setupLoggers();



		// try
		// {
		// // UIManager
		// //
		// .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		// //
		// UIManager.setLookAndFeel("javax.swing.plaf.multi.MultiLookAndFeel");
		// //
		// UIManager.setLookAndFeel("javax.swing.plaf.synth.SynthLookAndFeel");
		// //
		// UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		// // UIManager
		// //
		// .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		// // UIManager
		// // .setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		// }
		// catch ( ClassNotFoundException e )
		// {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// catch ( InstantiationException e )
		// {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// catch ( IllegalAccessException e )
		// {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// catch ( UnsupportedLookAndFeelException e )
		// {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		// Attempts to retrieve the users previous settings
		DesktopFileManagment.openSettings();

		// Sets the Locale for the application
		texts = ResourceBundle.getBundle("SystemTexts", new Locale(
				Settings.primeLocale.toString()));

		// Creates internal components(Motherboard, HDD, RAM, etc).
		standard_internal_components = new MakeStandardInternalComponents();

		// Creates standard software (Antivirus, Firewall, etc).
		standard_software = new MakeStandardSoftware();

		// Creates the standard system Operative systems
		system_standard_OS = MakeStandardSoftware.getSystemStandardOSs();


		glassPane = new GhostGlassPane();

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();


		width = ((int) scrnsize.getWidth()) - 200;

		height = ((int) scrnsize.getHeight()) - 200;


		if ( Settings.showSplash )
		{
			// Application splash screen
			new PrimeSplashScreen(3000);
		}

		// Sets the programs tooltip delay
		ToolTipManager.sharedInstance().setDismissDelay(10000);


		// STANDARD RULES
		if ( DesktopFileManagment.ruleFileExists() )
		{
			// Reads in the standard rules
			DesktopFileManagment.openStandardRules();
		}
		else
		{
			// Sets a new standard rules object with the serial -1
			standardRules = new NetworkRules();

			// Saves the standard rules
			DesktopFileManagment.saveStandardRules();
		}


		// Loads the custom OperatingSystems the user has added
		DesktopFileManagment.loadCustomOS();


		// OBJECTS LIST
		// If a file exist with the System Objects
		if ( DesktopFileManagment.objectsFileExists() )
		{
			// Reads in the Objects
			DesktopFileManagment.openObjectsFile();
		}
		else
		{
			// Creates new Standard Object
			CreateObjects.createStandardObject();

			// Creates a new Objects list file
			DesktopFileManagment.saveObjectsFile();
		}


		// Creates the system icons and places them in the ImageIcon array.
		MakeSystemImageIcons.getImageIcons(Settings.originalImages);

		// Resets the setting for original images
		Settings.originalImages = false;



		// SETUP FOR THE OEVERALL GUI
		// Get the content pane for this object
		Container c = this.getContentPane();


		/*
		 * SETUP FOR THE toolbarPanel PANEL. This panel will contain all the
		 * toolbar buttons and icons.
		 */
		// Creates a new panel with a GridBagLayout.
		toolbarPanel = new JPanel(new BorderLayout());

		// toolbarPanel.setSize(11, 6);

		// Constraints for the window
		GridBagConstraints conToolbarPanel = new GridBagConstraints();

		// Sets the constraints to fill both vertical and horizontal
		conToolbarPanel.fill = GridBagConstraints.BOTH;

		// Sets the border around the panel
		// toolbarPanel.setBorder(grayline);

		toolbarPanel.add(new GenericPrimeToolbar());


		// toolbarPanel.add(new JLabel("Toolbar"));

		/*
		 * SETUP FOR THE selectionPanel PANEL. This panel will contain all the
		 * selectable icons that can be places within the workarea, like client,
		 * server or printer icons. This will also contain the Project selection
		 * tab, where a user can pick different projects to work on in the
		 * workarea.
		 */
		// Creates a new panel with a GridBagLayout.
		selectionPanel = new JPanel(new GridLayout());

		// Constraints for the window
		GridBagConstraints conSelectionPanel = new GridBagConstraints();

		// Sets the constraints to fill both vertical and horizontal
		conSelectionPanel.fill = GridBagConstraints.BOTH;

		// Sets the border around the panel
		// selectionPanel.setBorder(grayline);

		// selectionPanel.add(new JLabel("Selection"));

		tabSelection = new TabbedSelection();
		selectionPanel.add(tabSelection);



		/*
		 * SETUP FOR THE propertiesPanel PANEL. This panel will contain some, if
		 * not all, of the properties of the selected object.
		 */
		// Creates a new panel with a GridBagLayout.
		propertiesPanel = new JPanel(new BorderLayout());

		// Constraints for the window
		GridBagConstraints conPropertiesPanel = new GridBagConstraints();

		// Sets the constraints to fill both vertical and horizontal
		conPropertiesPanel.fill = GridBagConstraints.BOTH;

		// Sets the border around the panel
		// propertiesPanel.setBorder(grayline);

		propertiesPanel.add(new PropertiesArea());

		// propertiesPanel.setPreferredSize(new Dimension(200,300));

		/*
		 * SETUP FOR THE messagesPanel PANEL. This panel will contain the
		 * errors, warnings, and suggestions tabs that will give information to
		 * the user about different aspects of their network setup.
		 */
		// Creates a new panel with a GridBagLayout.
		// messagesPanel = new JPanel(new GridLayout(0,1));
		messagesPanel = new JPanel(new BorderLayout());

		// Constraints for the window
		GridBagConstraints conMessagesPanel = new GridBagConstraints();

		// Sets the constraints to fill both vertical and horizontal
		conMessagesPanel.fill = GridBagConstraints.BOTH;

		// Sets the border around the panel
		// messagesPanel.setBorder(grayline);

		MessageTabbed msgTabs = new MessageTabbed();
		messagesPanel.add(msgTabs, BorderLayout.CENTER);
		msgTabs.createInitialTabs();
		// messagesPanel.add(new JLabel("Messages"));


		/*
		 * SETUP FOR THE workareaPanel PANEL. This panel will contain the actual
		 * work area where all of the icons and connection will be shown. This
		 * is where the "map" of the network will be.
		 */
		// Creates a new panel with a GridBagLayout.
		workareaPanel = new JPanel(new BorderLayout());

		// // Constraints for the window
		// GridBagConstraints conWorkareaPanel = new GridBagConstraints();

		// Sets the constraints to fill both vertical and horizontal
		// conWorkareaPanel.fill = GridBagConstraints.BOTH;

		// Sets the border around the panel
		// workareaPanel.setBorder(grayline);

		workTab = new WorkareaTabbed();

		// workareaPanel.add(new WorkareaCanvas());
		workareaPanel.add(workTab);




		this.setJMenuBar(new GenericPrimeMenuBar());

		this.add(new PrimeStatusBar(), BorderLayout.SOUTH);

		makeLayoutModel();
		// LoadLayoutModel();


		multiSplitPane.setDividerSize(5);

		multiSplitPane.setContinuousLayout(true);


		multiSplitPane.add(toolbarPanel, "toolbarLeaf");
		multiSplitPane.add(selectionPanel, "selectionLeaf");
		multiSplitPane.add(workareaPanel, "workareaLeaf");
		multiSplitPane.add(propertiesPanel, "propertiesLeaf");
		multiSplitPane.add(messagesPanel, "messagesLeaf");


		// Adding everything to the contentpane of the JFrame
		c.add(multiSplitPane, BorderLayout.CENTER);


		// GhostGlass
		this.setGlassPane(glassPane);



		this.setSize(width, height);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setVisible(true);

		if ( Settings.showTOTD )
		{
			new TipOfDay();
		}
	}

	/**
	 * The programs Main function.
	 */
	public static void main(String[] args)
	{
		PrimeMain temp = new PrimeMain();

		PluginMain.startPluginQueue();

		// FIXME - MUST ACTIVE BEFORE RELEASE
		// new PrimeDesktopUpdateService();

		// services = new PrimeService();

		temp.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent ev)
			{
				exitProcess();
			}
		});
	}


	/**
	 */
	private static void SaveLayoutModel()
	{
		try
		{
			XMLEncoder output = new XMLEncoder(new BufferedOutputStream(
					new FileOutputStream("PrimeLayoutModel.xml")));

			System.out.println(multiSplitPane.getMultiSplitLayout().getModel()
					.toString());

			output.writeObject(multiSplitPane.getMultiSplitLayout().getModel());
			output.close();
			// JOptionPane.showMessageDialog(null, "Saved model.");
		}
		catch ( FileNotFoundException output )
		{
			JOptionPane.showMessageDialog(null, "Error saving layout model.");
		}
	}


	/**
	 */
	private void makeLayoutModel()
	{
		String layoutDef = "(COLUMN (LEAF name=toolbarLeaf) (ROW (LEAF name=selectionLeaf weight=0.2) "
				+ "(COLUMN weight=0.8(ROW (LEAF name=workareaLeaf weight=0.6) "
				+ "(LEAF name=propertiesLeaf weight=0.2) )"
				+ "(LEAF name=messagesLeaf weight=1)  ) ) )";

		Node modelRoot = MultiSplitLayout.parseModel(layoutDef);
		multiSplitPane.getMultiSplitLayout().setModel(modelRoot);
		multiSplitPane.setPreferredSize(modelRoot.getBounds().getSize());
	}



	public static void fullscreen()
	{
		String layoutDef = "";

		if ( selectionPanel.isVisible() )
		{
			selectionPanel.setVisible(false);
			messagesPanel.setVisible(false);
			propertiesPanel.setVisible(false);
			layoutDef = "(COLUMN (LEAF name=toolbarLeaf) (ROW (LEAF name=selectionLeaf weight=0.0) "
					+ "(COLUMN weight=1.0(ROW (LEAF name=workareaLeaf weight=1.0) "
					+ "(LEAF name=propertiesLeaf weight=0.0) )"
					+ "(LEAF name=messagesLeaf weight=0.0)  ) ) )";
		}
		else
		{
			selectionPanel.setVisible(true);
			messagesPanel.setVisible(true);
			propertiesPanel.setVisible(true);
			layoutDef = "(COLUMN (LEAF name=toolbarLeaf) (ROW (LEAF name=selectionLeaf weight=0.2) "
					+ "(COLUMN weight=0.8(ROW (LEAF name=workareaLeaf weight=0.6) "
					+ "(LEAF name=propertiesLeaf weight=0.2) )"
					+ "(LEAF name=messagesLeaf weight=1)  ) ) )";
		}

		// JOptionPane.showMessageDialog(null,"Error Loading layout Model.");
		Node modelRoot = MultiSplitLayout.parseModel(layoutDef);
		multiSplitPane.getMultiSplitLayout().setModel(modelRoot);
		multiSplitPane.setPreferredSize(modelRoot.getBounds().getSize());
	}


	/**
	 */
	private void LoadLayoutModel()
	{
		try
		{
			XMLDecoder d = new XMLDecoder(new BufferedInputStream(
					new FileInputStream("PrimeLayoutModel.xml")));


			Node modelRoot = (Node) (d.readObject());
			System.out.println(modelRoot.toString());
			multiSplitPane.getMultiSplitLayout().setModel(modelRoot);
			d.close();
			JOptionPane.showMessageDialog(null, "Loaded model.");
		}
		catch ( Exception exc )
		{
			JOptionPane.showMessageDialog(null, "Error Loading layout Model.");
			makeLayoutModel();
		}
	}


	/**
	 * This method calls on functions that update the programs Object selection,
	 * which shows available {@link WorkareaCanvas WorkareaCanvases}.
	 */
	public static void updateObjectSelectionArea()
	{
		tabSelection.updateObjectArea();
		tabSelection.validate();
		tabSelection.repaint();
	}


	/**
	 * This method calls on functions that update the programs JTree, which
	 * shows available {@link WorkareaCanvas WorkareaCanvases}.
	 */
	public static void updateNetworkSelectionArea()
	{
		tabSelection.getPrimeTree().createTree();
		tabSelection.validate();
		tabSelection.repaint();
	}


	/**
	 * Updates the properties panel with information from the currently selected
	 * canvas.
	 * 
	 * @param override
	 *            Whether or not the method should update the the properties
	 *            area even if the {@link WorkareaCanvas} being shown has not be
	 *            altered.
	 */
	public static void updatePropertiesCanvasArea(boolean override)
	{
		PropertiesArea temp = (PropertiesArea) propertiesPanel.getComponent(0);

		// If the method is to update the properties area regardless of
		// whether or not the currently showing object is the same
		// WorkareaCanvas
		if ( override )
		{
			temp.newObjectSelectedPropertiesTab(currentCanvas);
		}
		else
		{
			// If the WorkareaCanvas shown in the properties area is not the
			// same as the current WorkareaCanvas
			if ( !(temp.isGivenCanvasCurrent(currentCanvas)) )
			{
				temp.newObjectSelectedPropertiesTab(currentCanvas);
			}
		}
	}


	/**
	 * Updates the properties panel with information from the given object.
	 * 
	 * @param obj
	 * @param override
	 *            Whether or not the method should update the the properties
	 *            area even if the {@link WorkareaCanvas} being shown has not be
	 *            altered.
	 */
	public static void updatePropertiesObjectArea(Object obj, boolean override)
	{
		PropertiesArea temp = (PropertiesArea) propertiesPanel.getComponent(0);

		// If the method is to update the properties area regardless of whether
		// or not the currently showing object is the same Object
		if ( override )
		{
			temp.newObjectSelectedPropertiesTab(obj);
		}
		else
		{
			// If the WorkareaCanvas shown in the properties area is not the
			// same as the current WorkareaCanvas
			if ( !(temp.isGivenObjectCurrent(obj)) )
			{
				temp.newObjectSelectedPropertiesTab(obj);
			}
		}
	}



	public static void clearPropertiesArea()
	{
		updatePropertiesObjectArea(null, true);
	}



	/**
	 * This method runs all update function. These include functions to update
	 * the canvas, properties panel and messages panel.
	 */
	public static void updateCanvasAndObjectInfo()
	{
		runCanvasObjectCheck();
	}



	/**
	 * This method runs clean up function on all open {@link WorkareaCanvas}.
	 */
	public static void runAllCanvasUpdate()
	{
		for ( int i = 0; i < PrimeMain.canvases.length; i++ )
		{
			DesktopCanvasManagment.canvasCleanUp(PrimeMain.canvases[i]);
		}
	}


	/**
	 * Runs a check on all the objects on the current canvas and passed that
	 * information over to the JTable that shows different errors, warnings and
	 * notices.
	 */
	public static void runCanvasObjectCheck()
	{
		Object[] children = new Object[0];
		// If there is no canvas open
		if ( currentCanvas != null )
		{
			children = currentCanvas.getObjectsOnTheScene();
		}

		MessageTabbed msgTab = (MessageTabbed) messagesPanel.getComponent(0);

		msgTab.processAllObjects(children);
	}



	/**
	 * Searches the object views that exist to find the given object as one of
	 * the views main object. If found that view is returned.
	 */
	public static ObjectView getObjectView(Object obj)
	{
		Iterator<ObjectView> i = objView.iterator();
		while ( i.hasNext() )
		{
			ObjectView objViewTest = i.next();

			if ( obj.equals(objViewTest.getObject()) )
			{
				return objViewTest;
			}

		}

		return null;
	}



	/**
	 * Searches the racks views that exist to find the given rack as one of the
	 * views main rack. If found that view is returned.
	 */
	public static RackOverview getRackView(Rack rackObj)
	{
		Iterator<RackOverview> i = rackView.iterator();
		while ( i.hasNext() )
		{
			RackOverview rackViewTest = i.next();

			if ( rackObj.equals(rackViewTest.getRackObject()) )
			{
				return rackViewTest;
			}

		}

		return null;
	}



	/**
	 * Adds a view to the arraylist of views.
	 */
	public static void addObjectView(ObjectView view)
	{
		objView.add(view);
	}


	/**
	 * Removes the view, if it exist, that has as its main object the given
	 * object.
	 */
	public static void removeObjectView(Object obj)
	{
		ObjectView view = getObjectView(obj);

		if ( view != null )
		{
			objView.remove(view);
		}
	}



	/**
	 * Adds a view to the arraylist of views.
	 */
	public static void addRackView(RackOverview view)
	{
		rackView.add(view);
	}


	/**
	 * Removes the view, if it exist, that has as its main object the given
	 * object.
	 */
	public static void removeRackView(Rack obj)
	{
		RackOverview view = getRackView(obj);

		if ( view != null )
		{
			rackView.remove(view);
		}
	}


	/**
	 * Sets the actual JPanel that contains the WorkareaTabbed where all the
	 * systems {@link WorkareaCanvas WorkareaCanvases} area shown.
	 */
	public static void setWorkareaPanel(JPanel workareaPanel)
	{
		PrimeMain.workareaPanel = workareaPanel;
	}



	/**
	 * Gets the actual JPanel that contains the WorkareaTabbed where all the
	 * systems {@link WorkareaCanvas WorkareaCanvases} area shown.
	 * 
	 * @return workareaPanel the workareaPanel to get
	 */
	public static JPanel getWorkareaPanel()
	{
		return workareaPanel;
	}


	/**
	 * Gets the WorkareaTabbed which contains the tabs with
	 * {@link WorkareaCanvas} inside.
	 * 
	 * @return the workareaPanel
	 */
	public static WorkareaTabbed getWorkarea()
	{
		return workTab;
	}




	/**
	 * This function is the systems Exit process. This will be called if a user
	 * wants to close the program. It will check the {@link WorkareaCanvas
	 * WorkareaCanvases} in the system to check if any are altered and ask the
	 * user if they wish to save those altered {@link WorkareaCanvas
	 * WorkareaCanvases}.
	 */
	public static void exitProcess()
	{
		System.out.println("Quit - " + new Date());
		// services.stopAll();

		// Saves the settings the user has in his current session
		DesktopFileManagment.saveSettings();

		// Saves the systems Objects list
		DesktopFileManagment.saveObjectsFile();

		// Saves the standard rules
		DesktopFileManagment.saveStandardRules();

		DesktopFileManagment.saveCustomOS();

		WorkareaCanvas[] changes = CanvasManagment
				.canvasesHaveChanged(canvases);


		// SaveLayoutModel();


		// There were some canvases that were changed
		if ( changes != null )
		{
			// The options the user will be presented with.
			String[] options = { texts.getString("save"),
					texts.getString("dontSave"), texts.getString("cancel") };


			// Asks the user whether or not to save
			int answer = JOptionPane.showOptionDialog(null, PrimeMain.texts
					.getString("removeTabRemoveWithoutSavingQuestion"),
					PrimeMain.texts.getString("save"),
					JOptionPane.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE,
					null, options, null);

			// Save
			if ( answer == 0 )
			{
				DesktopFileManagment.saveCanvases(changes);

				// Stops the plugin queue from receiving new plugins
				stopPluginQueue();
				endingLoggin();
				System.exit(0);
			}
			// Dont save
			else if ( answer == 1 )
			{
				// Stops the plugin queue from receiving new plugins
				stopPluginQueue();
				endingLoggin();
				System.exit(0);
			}
		}
		else
		{
			// Stops the plugin queue from receiving new plugins
			stopPluginQueue();
			endingLoggin();
			System.exit(0);
		}
	}

	/**
	 * This function attempts to stop the queue of plugins. It stops the queue
	 * from receiving new plugin jobs. The plugin manager will automatically
	 * finish the remaining plugins.
	 */
	private static void stopPluginQueue()
	{
		Thread t = new Thread(new StopPluginsJob());

		synchronized (t)
		{
			try
			{
				t.start();

				t.wait();
			}
			catch ( InterruptedException e )
			{
				e.printStackTrace();
			}
		}
	}



	/**
	 * This function initiates the different system loggers including files and
	 * formatters.
	 */
	private static void setupLoggers()
	{
		try
		{
			DesktopCustomLoggerFormatter formatter = new DesktopCustomLoggerFormatter();

			FileHandler guiFH = new FileHandler("guiLog.log", true);
			guiFH.setFormatter(formatter);
			guiLog.addHandler(guiFH);
			guiLog.setUseParentHandlers(false);
			guiLog.setLevel(Level.ALL);


			guiLog.finest("");
			guiLog.finest("");
			guiLog.finest("");
			guiLog.info("--------STARTING GUI LOGGING--------");


			FileHandler ioFH = new FileHandler("ioLog.log", true);
			ioFH.setFormatter(formatter);
			ioLog.addHandler(ioFH);
			ioLog.setUseParentHandlers(false);
			ioLog.setLevel(Level.ALL);


			ioLog.finest("");
			ioLog.finest("");
			ioLog.finest("");
			ioLog.info("--------STARTING IO LOGGING--------");


			FileHandler deskProcFH = new FileHandler("dekstopProcLog.log", true);
			deskProcFH.setFormatter(formatter);
			desktopProcLog.addHandler(deskProcFH);
			desktopProcLog.setUseParentHandlers(false);
			desktopProcLog.setLevel(Level.ALL);


			desktopProcLog.finest("");
			desktopProcLog.finest("");
			desktopProcLog.finest("");
			desktopProcLog
					.info("--------STARTING DEKSTOP PROCESSING LOGGING--------");


			FileHandler libFH = new FileHandler("libraryLog.log", true);
			libFH.setFormatter(formatter);
			LibraryLogging.libraryLog.addHandler(libFH);
			LibraryLogging.libraryLog.setUseParentHandlers(false);
			LibraryLogging.libraryLog.setLevel(Level.ALL);


			LibraryLogging.libraryLog.finest("");
			LibraryLogging.libraryLog.finest("");
			LibraryLogging.libraryLog.finest("");
			LibraryLogging.libraryLog
					.info("--------STARTING LIBRARY LOGGING--------");
		}
		catch ( SecurityException e )
		{
			e.printStackTrace();
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}


	/**
	 * This function writes and ending to the loggings.
	 */
	private static void endingLoggin()
	{
		guiLog.info("--------ENDING GUI LOGGING--------");
		ioLog.info("--------ENDING IO LOGGING--------");
		desktopProcLog
				.info("--------ENDING DEKSTOP PROCESSING LOGGING--------");
		LibraryLogging.libraryLog
				.info("--------ENDING LIBRARY LOGGING--------");
	}
}
