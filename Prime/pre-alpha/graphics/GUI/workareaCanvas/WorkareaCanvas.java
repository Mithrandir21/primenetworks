/**
 * 
 */
package graphics.GUI.workareaCanvas;


import graphics.ImageLocator;
import graphics.PrimeMain1;
import graphics.GUI.selectArea.CreateObjectDragged;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

import objects.Object;

import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.SelectProvider;
import org.netbeans.api.visual.action.TextFieldInplaceEditor;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Widget;

import servers.HTTPServer;
import widgetManipulation.WidgetObject;




/**
 * Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */

public class WorkareaCanvas extends JPanel implements DropTargetListener, ActionListener
{

	private TransferHandler TransHandler = new WidgetTransferHandler();

	private DropTarget dt;


	public WorkareaCanvas()
	{

		// Creating the actual view
		PrimeMain1.myView = PrimeMain1.scene.createView();

		PrimeMain1.myView.setTransferHandler(TransHandler);

		dt = new DropTarget(PrimeMain1.myView, this);


		createPopupMenu();


		PrimeMain1.scene.getActions().addAction(
				ActionFactory.createSelectAction(new CreateProvider()));

		// Adds the zoom feature to the scene.
		PrimeMain1.scene.getActions().addAction(ActionFactory.createZoomAction());

		// This is the main layer of the scene where the widgets, objects, are
		// placed.
		PrimeMain1.mainLayer = new LayerWidget(PrimeMain1.scene);
		PrimeMain1.scene.addChild(PrimeMain1.mainLayer);

		// This is the interaction layer
		PrimeMain1.interactionLayer = new LayerWidget(PrimeMain1.scene);
		PrimeMain1.scene.addChild(PrimeMain1.interactionLayer);

		// This is the connection layer where all the connections between the
		// objects are shown.
		PrimeMain1.connectionLayer = new LayerWidget(PrimeMain1.scene);
		PrimeMain1.scene.addChild(PrimeMain1.connectionLayer);

	}



	private class CreateProvider implements SelectProvider
	{

		public boolean isAimingAllowed(Widget arg0, Point arg1, boolean arg2)
		{
			return false;
		}

		public boolean isSelectionAllowed(Widget arg0, Point arg1, boolean arg2)
		{
			return true;
		}

		public void select(Widget relatedWidget, Point localLocation, boolean invertSelection)
		{
			WidgetObject widget = new WidgetObject(PrimeMain1.scene, new HTTPServer(
					"newComponent2", "Desc", "1", "2", "3"), ImageLocator.createImage(this
					.getClass().getResource("images/objectImages/print-server.png")));
			
			widget.setPreferredLocation(relatedWidget.convertLocalToScene(localLocation));
			
			widget.getActions().addAction(
					ActionFactory.createExtendedConnectAction(PrimeMain1.interactionLayer,
							new SceneConnectProvider()));
			
			widget.getActions().addAction(
					ActionFactory.createAlignWithMoveAction(PrimeMain1.mainLayer,
							PrimeMain1.interactionLayer, null));
			
			widget.getActions().addAction(
					ActionFactory.createInplaceEditorAction(new LabelTextFieldEditor()));
			
			widget.setLabel("Biatch");
			
			cleanUp();
			
			PrimeMain1.mainLayer.addChild(widget);
			PrimeMain1.numberOfWidgetsOnTheScene++;
		}

	}


	// ------------------ TRANSFER METHODES -----------------------

	@Override
	public void dragEnter(DropTargetDragEvent dtde)
	{
		// System.out.println("Drag Enter");
	}



	@Override
	public void dragExit(DropTargetEvent dte)
	{
		// System.out.println("Drag Exit");
	}



	@Override
	public void dragOver(DropTargetDragEvent dtde)
	{
		// System.out.println("Drag Over");
	}



	@Override
	public void drop(DropTargetDropEvent dtde)
	{
		// System.out.println("Drag Drop");
		Transferable tr = dtde.getTransferable();
		DataFlavor f[] = tr.getTransferDataFlavors();
		WidgetObject newObject = null;


		try
		{
			newObject = (WidgetObject) tr.getTransferData(new DataFlavor(WidgetObject.class,
					"Widget Object"));


			Dimension objectSize = newObject.getImageDimension();

			Point objectPoint = dtde.getLocation();

			dtde = null;


			int height = objectPoint.x - (objectSize.height / 2);

			int width = objectPoint.y - (objectSize.width / 2);

			objectPoint.setLocation(height, width);


			// objectPoint.setLocation(objectPoint);


			// LabelWidget i = new LabelWidget(scene, "This is it");


			addWidgetObject(newObject, objectPoint);


			cleanUp();
		}
		catch ( UnsupportedFlavorException e )
		{
			System.out.println("WorkareaCanvas - UnsupportedFlavorException");
		}
		catch ( IOException e )
		{
			System.out.println("WorkareaCanvas - IOException");
		}

	}



	@Override
	public void dropActionChanged(DropTargetDragEvent dtde)
	{
		// System.out.println("Drag DropActionChanged");
	}



	private void addWidgetObject(WidgetObject newObject, Point objectPoint)
	{
		newObject.setPreferredLocation(newObject.convertLocalToScene(objectPoint));
		newObject.getActions().addAction(
				ActionFactory.createExtendedConnectAction(PrimeMain1.interactionLayer,
						new SceneConnectProvider()));
		newObject.getActions().addAction(
				ActionFactory.createAlignWithMoveAction(PrimeMain1.mainLayer,
						PrimeMain1.interactionLayer, null));

		int n = JOptionPane.showConfirmDialog(this, "Would you like to add a new "
				+ newObject.getObject().getName() + "?", "An Inane Question",
				JOptionPane.YES_NO_OPTION);

		if ( n == 0 )
		{
			PrimeMain1.mainLayer.addChild(newObject);
			PrimeMain1.numberOfWidgetsOnTheScene++;
		}
	}


	private void cleanUp()
	{
		PrimeMain1.scene.revalidate();
		PrimeMain1.scene.repaint();

		PrimeMain1.myView.revalidate();
		PrimeMain1.myView.repaint();

		PrimeMain1.mainLayer.revalidate();
		PrimeMain1.mainLayer.repaint();

		PrimeMain1.interactionLayer.revalidate();
		PrimeMain1.interactionLayer.repaint();

		PrimeMain1.connectionLayer.revalidate();
		PrimeMain1.connectionLayer.repaint();
	}






	private class LabelTextFieldEditor implements TextFieldInplaceEditor
	{
		@Override
		public String getText(Widget widget)
		{
			return ((WidgetObject) widget).getLabel();
		}

		@Override
		public boolean isEnabled(Widget widget)
		{
			return true;
		}

		@Override
		public void setText(Widget widget, String text)
		{
			((WidgetObject) widget).setLabel(text);
		}
	}



	public void createPopupMenu()
	{
		CanvasMenu.createPopupMenu(this);
	}


	public void actionPerformed(ActionEvent e)
	{
		JMenuItem action = (JMenuItem) e.getSource();

		String actionName = "";


		if ( action.getName() != null )
		{
			actionName = action.getName();
		}


		if ( !actionName.equals("") )
		{
			if ( actionName.equals("CreateNewST_Desktop_Item") )
			{
				System.out.println("CreateNewST_Desktop_Item");
			}
			else if ( actionName.equals("CreateNewST_Laptop_Item") )
			{
				System.out.println("CreateNewST_Laptop_Item");
			}
			else if ( actionName.equals("CreateNewST_HTTPServer_Item") )
			{
				System.out.println("CreateNewST_HTTPServer_Item");
			}
			else if ( actionName.equals("CreateNewST_MailServer_Item") )
			{
				System.out.println("CreateNewST_MailServer_Item");
			}
			else if ( actionName.equals("CreateNewST_BackupServer_Item") )
			{
				System.out.println("CreateNewST_BackupServer_Item");
			}
			else if ( actionName.equals("CreateNewST_FirewallServer_Item") )
			{
				System.out.println("CreateNewST_FirewallServer_Item");
			}
			else if ( actionName.equals("CreateNewST_Hub_Item") )
			{
				System.out.println("CreateNewST_Hub_Item");
			}
			else if ( actionName.equals("CreateNewST_Switch_Item") )
			{
				System.out.println("CreateNewST_Switch_Item");
			}
			else if ( actionName.equals("CreateNewST_Router_Item") )
			{
				System.out.println("CreateNewST_Router_Item");
			}
			else if ( actionName.equals("CreateNewST_WirelessRouter_Item") )
			{
				System.out.println("CreateNewST_WirelessRouter_Item");
			}
			else if ( actionName.equals("CreateNewST_Scanner_Item") )
			{
				System.out.println("CreateNewST_Scanner_Item");
			}
		}
	}
}
