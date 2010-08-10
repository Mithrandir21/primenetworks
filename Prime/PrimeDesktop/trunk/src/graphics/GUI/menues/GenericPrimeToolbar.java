/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010  Bahram Malaekeh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package graphics.GUI.menues;


import graphics.ImageLocator;
import graphics.PrimeMain;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import actions.systemActions.ActionConnection;
import actions.systemActions.ActionCopy;
import actions.systemActions.ActionCut;
import actions.systemActions.ActionExportCanvasAsImage;
import actions.systemActions.ActionFullscreen;
import actions.systemActions.ActionNew;
import actions.systemActions.ActionObjectEditing;
import actions.systemActions.ActionOpenCustomOS;
import actions.systemActions.ActionOpenStandardRules;
import actions.systemActions.ActionOpenfile;
import actions.systemActions.ActionPaste;
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
public class GenericPrimeToolbar extends JToolBar
{
	// Temp imageIcon that will hold the icon of the created button.
	private ImageIcon tempIcon = null;

	/**
	 * Constructor for the GenericPrimeToolbar class. Here the different parts
	 * of the toolbar are initiated.
	 */
	public GenericPrimeToolbar()
	{
		this.setFloatable(false);

		initFileToolBar();

		Dimension size = new Dimension(5, 5);

		this.add(new JToolBar.Separator(size), BorderLayout.WEST);

		initEditToolBar();

		this.add(new JToolBar.Separator(size), BorderLayout.WEST);

		initStepsActions();

		this.add(new JToolBar.Separator(size), BorderLayout.WEST);

		initCanvasActions();

		this.add(new JToolBar.Separator(size), BorderLayout.WEST);

		initNetworkAction();

		this.add(new JToolBar.Separator(size), BorderLayout.WEST);

		initEditingAction();
	}


	private JToolBar fileToolBar, editToolBar, stepsToolBar, networkToolBar,
			editingToolBar;

	public static JToolBar canvasToolBar;


	// SETUP OF THE TOOLBAR

	/**
	 * This function creates the File JToolbar.
	 */
	private void initFileToolBar()
	{
		fileToolBar = new JToolBar();
		fileToolBar.setFloatable(false);


		tempIcon = ImageLocator.getImageIconObject("New");
		ActionNew newFile = new ActionNew(
				PrimeMain.texts.getString("newLabel"), tempIcon);

		tempIcon = ImageLocator.getImageIconObject("Open");
		ActionOpenfile openFile = new ActionOpenfile(PrimeMain.texts
				.getString("openFileLabel"), tempIcon);
		openFile.setEnabled(false);

		tempIcon = ImageLocator.getImageIconObject("Save");
		ActionSave save = new ActionSave(
				PrimeMain.texts.getString("saveLabel"), tempIcon);

		tempIcon = ImageLocator.getImageIconObject("Save_all");
		ActionSaveAll saveAll = new ActionSaveAll(PrimeMain.texts
				.getString("saveAllLabel"), tempIcon);


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
		editToolBar = new JToolBar();
		editToolBar.setFloatable(false);


		tempIcon = ImageLocator.getImageIconObject("Cut");
		ActionCut cut = new ActionCut(PrimeMain.texts.getString("cutLabel"),
				tempIcon);

		tempIcon = ImageLocator.getImageIconObject("Copy");
		ActionCopy openFile = new ActionCopy(PrimeMain.texts
				.getString("copyLabel"), tempIcon);

		tempIcon = ImageLocator.getImageIconObject("Paste");
		ActionPaste save = new ActionPaste(PrimeMain.texts
				.getString("pasteLabel"), tempIcon);


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
		stepsToolBar = new JToolBar();
		stepsToolBar.setFloatable(false);

		tempIcon = ImageLocator.getImageIconObject("Undo");
		ActionUndo undo = new ActionUndo(
				PrimeMain.texts.getString("undoLabel"), tempIcon);


		tempIcon = ImageLocator.getImageIconObject("Redo");
		ActionRedo redo = new ActionRedo(
				PrimeMain.texts.getString("redoLabel"), tempIcon);



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
		networkToolBar = new JToolBar();
		networkToolBar.setFloatable(false);

		tempIcon = ImageLocator.getImageIconObject("Update");
		ActionUpdate update = new ActionUpdate(PrimeMain.texts
				.getString("updateButtonLabel"), tempIcon);
		JButton updateButton = new JButton(update);

		tempIcon = ImageLocator.getImageIconObject("Export");
		ActionExportCanvasAsImage export = new ActionExportCanvasAsImage(
				PrimeMain.texts.getString("exportButtonLabel"), tempIcon);
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
		canvasToolBar = new JToolBar();
		canvasToolBar.setFloatable(false);


		tempIcon = ImageLocator.getImageIconObject("Connection");
		ActionConnection con = new ActionConnection(PrimeMain.texts
				.getString("connectToggleButtonLabel"), tempIcon);
		JToggleButton connectionButton = new JToggleButton(con);

		tempIcon = ImageLocator.getImageIconObject("Room");
		ActionRoom room = new ActionRoom(PrimeMain.texts
				.getString("roomToggleButtonLabel"), tempIcon);
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
		editingToolBar = new JToolBar();
		editingToolBar.setFloatable(false);



		tempIcon = ImageLocator.getImageIconObject("OperatingSystem-Button");
		ActionOpenCustomOS osList = new ActionOpenCustomOS(PrimeMain.texts
				.getString("systemOSlist"), tempIcon);
		JButton osListButton = new JButton(osList);


		tempIcon = ImageLocator.getImageIconObject("Rules");
		ActionOpenStandardRules rules = new ActionOpenStandardRules(
				PrimeMain.texts
						.getString("openStandardNetworkRulesToolbarLabel"),
				tempIcon);
		JButton rulesButton = new JButton(rules);


		tempIcon = ImageLocator.getImageIconObject("Objects");
		ActionObjectEditing editing = new ActionObjectEditing(PrimeMain.texts
				.getString("standardObjectsLabel"), tempIcon);
		JButton editingButton = new JButton(editing);


		ActionFullscreen fullscreen = new ActionFullscreen(PrimeMain.texts
				.getString("actionFullscreenText"));
		JButton fullscreenButton = new JButton(fullscreen);
		fullscreenButton.setVisible(false);

		editingToolBar.add(osListButton);
		editingToolBar.add(rulesButton);
		editingToolBar.add(editingButton);
		editingToolBar.add(fullscreenButton);

		this.add(editingToolBar, BorderLayout.WEST);
		tempIcon = null;
	}


	/**
	 * TODO - Description
	 */
	public static void untoggleAllOthersButtons(Class<?> actionClass)
	{
		if ( actionClass.equals(ActionConnection.class) )
		{
			for ( int i = 0; i < canvasToolBar.getComponentCount(); i++ )
			{
				if ( canvasToolBar.getComponent(i) instanceof JToggleButton )
				{
					JToggleButton button = (JToggleButton) canvasToolBar
							.getComponent(i);

					if ( button.getAction().getClass().equals(ActionRoom.class) )
					{
						button.setSelected(false);
					}
				}
			}
		}
		else if ( actionClass.equals(ActionRoom.class) )
		{

			for ( int i = 0; i < canvasToolBar.getComponentCount(); i++ )
			{
				if ( canvasToolBar.getComponent(i) instanceof JToggleButton )
				{
					JToggleButton button = (JToggleButton) canvasToolBar
							.getComponent(i);

					if ( button.getAction().getClass().equals(
							ActionConnection.class) )
					{
						button.setSelected(false);
					}
				}
			}
		}
	}
}