/**
 * 
 */
package graphics;

import java.awt.*;

import javax.swing.*;

import org.netbeans.api.visual.widget.*;
import org.netbeans.api.visual.action.*;
import org.netbeans.api.visual.anchor.*;
import org.netbeans.api.visual.border.BorderFactory;

import servers.HTTPServer;
import widgetManipulation.WidgetObject;




/**
 * Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */

public class WorkareaCanvas 
{
	private Scene scene;
	
	public JComponent myView;
	
	private LayerWidget mainLayer;
	
	private LayerWidget interactionLayer;
	
	private LayerWidget connectionLayer;
	
	
	public WorkareaCanvas()
	{
		// The scene, canvas, where all the components of the system will be shown.
		scene = PrimeMain1.scene;
			
		
		// Creating the actual view
		myView = scene.createView();
		
		
		scene.getActions().addAction(ActionFactory.createSelectAction(new CreateProvider()));
		
		// Adds the zoom feature to the scene.
		scene.getActions().addAction(ActionFactory.createZoomAction());
		
		// This is the main layer of the scene where the widgets, objects, are placed.
		mainLayer = new LayerWidget(scene);
		scene.addChild(mainLayer);
		
		// This is the interaction layer
		interactionLayer = new LayerWidget(scene);
		scene.addChild(interactionLayer);
		
		// This is the connection layer where all the connections between the objects are shown.
		connectionLayer = new LayerWidget(scene);
		scene.addChild(connectionLayer);
		
	}
	
	
	
	private class CreateProvider implements SelectProvider
	{

		public boolean isAimingAllowed(Widget arg0, Point arg1, boolean arg2) 
		{
			// TODO Auto-generated method stub
			return false;
		}

		public boolean isSelectionAllowed(Widget arg0, Point arg1, boolean arg2) 
		{
			// TODO Auto-generated method stub
			return true;
		}

		public void select(Widget relatedWidget, Point localLocation, boolean invertSelection) 
		{
			//Widget widget = new ImageWidget(scene, createImage("images/java.jpg"));
			Widget widget = new WidgetObject(scene,new HTTPServer("newComponent2","Desc","1","2","3"), createImage("images/objectImages/print-server.png"));
			widget.setPreferredLocation(relatedWidget.convertLocalToScene(localLocation));
			widget.getActions().addAction(ActionFactory.createExtendedConnectAction(interactionLayer,new SceneConnectProvider()));
			//widget.setBorder(BorderFactory.createRoundedBorder(10, 10, Color.yellow, Color.black));
			widget.getActions().addAction(ActionFactory.createAlignWithMoveAction(mainLayer, interactionLayer, null));
			mainLayer.addChild(widget);
		}

	}
	
	
	private class SceneConnectProvider implements ConnectProvider
	{

		public void createConnection(Widget sourceWidget, Widget targetWidget) 
		{
			ConnectionWidget connection = new ConnectionWidget(scene);
			connection.setTargetAnchorShape(AnchorShape.TRIANGLE_FILLED);
			connection.setSourceAnchor(AnchorFactory.createRectangularAnchor(sourceWidget));
			connection.setTargetAnchor(AnchorFactory.createRectangularAnchor(targetWidget));
			connectionLayer.addChild(connection);
			
			
		}

		public boolean hasCustomTargetWidgetResolver(Scene scene) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean isSourceWidget(Widget arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		public ConnectorState isTargetWidget(Widget sourceWidget, Widget targetWidget) 
		{
			if(sourceWidget != targetWidget && targetWidget instanceof WidgetObject)
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
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	
	
	/** Returns an Image, or null if the path was invalid. */
	protected Image createImage(String path) 
	{
	    java.net.URL imgURL = getClass().getResource(path);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL).getImage();
	    } else {
	        System.err.println("Couldn't find file: " + path);
	        return null;
	    }
	}
}
