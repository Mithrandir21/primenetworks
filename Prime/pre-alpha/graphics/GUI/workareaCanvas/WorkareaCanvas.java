/**
 * 
 */
package graphics.GUI.workareaCanvas;


import graphics.PrimeMain1;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.ConnectProvider;
import org.netbeans.api.visual.action.ConnectorState;
import org.netbeans.api.visual.action.SelectProvider;
import org.netbeans.api.visual.action.TextFieldInplaceEditor;
import org.netbeans.api.visual.anchor.AnchorFactory;
import org.netbeans.api.visual.anchor.AnchorShape;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

import servers.HTTPServer;
import widgetManipulation.WidgetObject;




/**
 * Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */

public class WorkareaCanvas extends JComponent implements DropTargetListener
{
	private Scene scene;

	public JComponent myView;

	private LayerWidget mainLayer;

	private LayerWidget interactionLayer;

	private LayerWidget connectionLayer;


	private TransferHandler TransHandler = new WidgetTransferHandler();

	private DropTarget dt;


	public WorkareaCanvas()
	{
		// The scene, canvas, where all the components of the system will be
		// shown.
		scene = graphics.PrimeMain1.scene;


		// Creating the actual view
		myView = scene.createView();

		myView.setTransferHandler(TransHandler);


		dt = new DropTarget(myView, this);


		scene.getActions().addAction(ActionFactory.createSelectAction(new CreateProvider()));

		// Adds the zoom feature to the scene.
		scene.getActions().addAction(ActionFactory.createZoomAction());

		// This is the main layer of the scene where the widgets, objects, are
		// placed.
		mainLayer = new LayerWidget(scene);
		scene.addChild(mainLayer);

		// This is the interaction layer
		interactionLayer = new LayerWidget(scene);
		scene.addChild(interactionLayer);

		// This is the connection layer where all the connections between the
		// objects are shown.
		connectionLayer = new LayerWidget(scene);
		scene.addChild(connectionLayer);




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
			// Widget widget = new ImageWidget(scene,
			// createImage("images/java.jpg"));
			WidgetObject widget = new WidgetObject(scene, new HTTPServer("newComponent2", "Desc", "1", "2", "3"), createImage("images/objectImages/print-server.png"));
			widget.setPreferredLocation(relatedWidget.convertLocalToScene(localLocation));
			widget.getActions().addAction(ActionFactory.createExtendedConnectAction(interactionLayer, new SceneConnectProvider()));
			// widget.setBorder(BorderFactory.createRoundedBorder(10, 10,
			// Color.yellow, Color.black));
			widget.getActions().addAction(ActionFactory.createAlignWithMoveAction(mainLayer, interactionLayer, null));
			widget.getActions().addAction(ActionFactory.createInplaceEditorAction(new LabelTextFieldEditor()));
			widget.setLabel("Biatch");
			mainLayer.addChild(widget);
			scene.repaint();
			PrimeMain1.numberOfWidgetsOnTheScene++;
		}

	}


	private class SceneConnectProvider implements ConnectProvider
	{

		public void createConnection(Widget sourceWidget, Widget targetWidget)
		{
			ConnectionWidget connection = new ConnectionWidget(scene);
			connection.setTargetAnchorShape(AnchorShape.NONE);
			connection.setSourceAnchor(AnchorFactory.createRectangularAnchor(sourceWidget));
			connection.setTargetAnchor(AnchorFactory.createRectangularAnchor(targetWidget));
			connectionLayer.addChild(connection);
		}

		public boolean hasCustomTargetWidgetResolver(Scene scene)
		{
			return false;
		}

		public boolean isSourceWidget(Widget sourceWidget)
		{
			if ( sourceWidget instanceof WidgetObject )
			{
				return true;
			}

			return false;
		}

		public ConnectorState isTargetWidget(Widget sourceWidget, Widget targetWidget)
		{
			if ( sourceWidget != targetWidget && targetWidget instanceof WidgetObject )
			{
				return ConnectorState.ACCEPT;
			}
			else
			{
				return ConnectorState.REJECT_AND_STOP;
			}
		}

		public Widget resolveTargetWidget(Scene sourceWidget, Point sceneLocation)
		{
			return null;
		}
	}



	/** Returns an Image, or null if the path was invalid. */
	protected Image createImage(String path)
	{
		java.net.URL imgURL = getClass().getResource(path);
		if ( imgURL != null )
		{
			return new ImageIcon(imgURL).getImage();
		}
		else
		{
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}



	// ------------------ TRANSFER METHODES -----------------------

	@Override
	public void dragEnter(DropTargetDragEvent dtde)
	{
		System.out.println("Drag Enter");
	}



	@Override
	public void dragExit(DropTargetEvent dte)
	{
		System.out.println("Drag Exit");
	}



	@Override
	public void dragOver(DropTargetDragEvent dtde)
	{
		// System.out.println("Drag Over");
	}



	@Override
	public void drop(DropTargetDropEvent dtde)
	{
		System.out.println("Drag Drop");
		Transferable tr = dtde.getTransferable();
		DataFlavor f[] = tr.getTransferDataFlavors();
		System.out.println(f[0]);
		WidgetObject newObject = null;
		try
		{
			newObject = (WidgetObject) tr.getTransferData(new DataFlavor(WidgetObject.class, "Widget Object"));
		}
		catch ( UnsupportedFlavorException e )
		{
			System.out.println("WorkareaCanvas - UnsupportedFlavorException");
		}
		catch ( IOException e )
		{
			System.out.println("WorkareaCanvas - IOException");
		}

//		System.out.println(newObject.getObject().getName());


		Dimension objectSize = newObject.getImageDimension();

		Point objectPoint = dtde.getLocation();


		int height = objectPoint.x - (objectSize.height / 2);

		int width = objectPoint.y - (objectSize.width / 2);

		objectPoint.setLocation(height, width);


		objectPoint.setLocation(objectPoint);

		addWidgetObject(newObject, objectPoint);
		
		scene.repaint();
	}



	@Override
	public void dropActionChanged(DropTargetDragEvent dtde)
	{
		System.out.println("Drag DropActionChanged");
	}



	private void addWidgetObject(WidgetObject newObject, Point objectPoint)
	{

		newObject.setPreferredLocation(objectPoint);
		newObject.getActions().addAction(ActionFactory.createExtendedConnectAction(interactionLayer, new SceneConnectProvider()));
		newObject.getActions().addAction(ActionFactory.createAlignWithMoveAction(mainLayer, interactionLayer, null));
		mainLayer.addChild(newObject);
		PrimeMain1.numberOfWidgetsOnTheScene++;
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
}
