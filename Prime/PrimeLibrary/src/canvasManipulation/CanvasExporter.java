/**
 * 
 */
package canvasManipulation;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import widgets.WorkareaCanvas;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author krichard (extended by Bahram Malaekeh)
 * 
 */
public class CanvasExporter
{
	/**
	 * Creates an instance of a SceneExporter object.
	 * 
	 * @return A new instance of a SceneExporter.
	 */
	private CanvasExporter()
	{
	}


	/**
	 * Takes the Scene and writes an image file according to the constraints defined by the caller. This returns a
	 * BufferedImage of the Scene even if the file can not be written.
	 * 
	 * @param scene
	 *            The Scene to be exported as an image.
	 * @param file
	 *            The file used to store the exported image. If null, then it is assumed that the raw image is to be
	 *            returned only and not written to a file.
	 * @param SceneExporter
	 *            .ImageType The image type to be exported for the image map.
	 * @param SceneExporter
	 *            .ZoomType Defines the strategy by which to set the exporting scale factor. Note that certain
	 *            parameters are nullified by the choice of ZoomType. For instance, if ZoomType.CUSTOM_SIZE is not
	 *            chosen, then the width and height parameters are not used.
	 * @param visibleAreaOnly
	 *            Eliminates all zoom features. If true, the exported image will be a created from the visible area of
	 *            the scene.
	 * @param selectedOnly
	 *            Create an image including only the objects selected on the scene. Note that this feature requires that
	 *            the scene is an instance of an ObjectScene since it is the implementation that allows for object
	 *            selection.
	 * @param quality
	 *            And integer value between 0-100. This is for JPG images only. Parameter is not used if an image type
	 *            other than jpg is selected.
	 * @param width
	 *            Directly sets the horizontal dimension of the exported image. This is only used when the zoomType is
	 *            ZoomType.CUSTOM_SIZE
	 * @param height
	 *            Directly sets the vertical dimension of the exported image. This is only used when the zoomType is
	 *            ZoomType.CUSTOM_SIZE.
	 * @return image The raw image that was written to the file.
	 * @throws java.io.IOException
	 *             If for some reason the file cannot be written, an IOExeption will be thrown.
	 */
	public static BufferedImage createImage(WorkareaCanvas canvas, File file, ImageType imageType, ZoomType zoomType,
			boolean visibleAreaOnly, boolean selectedOnly, int quality, int width, int height) throws IOException
	{

		if ( canvas == null )
		{
			return null;
		}


		Canvas2Image c2i = new Canvas2Image(canvas.getScene(), file);
		BufferedImage image = c2i.createImage(imageType, zoomType, visibleAreaOnly, selectedOnly, quality, canvas,
				false);

		return image;
	}

	/**
	 * This enumeration holds the possible image formats that the SceneExporter can export to.
	 */
	public enum ImageType
	{
		/**
		 * Use this in the SceneExporter to set the export file type to JPG.
		 */
		JPG,
		/**
		 * Use this in the SceneExporter to set the export file type to PNG.
		 */
		PNG
	}

	/**
	 * This enumeration holds the zooming capabilities that the SceneExporter can export according to.
	 */
	public enum ZoomType
	{

		/**
		 * Used to set the horizontal and vertical sizes directly.
		 */
		CUSTOM_SIZE,
		/**
		 * Used when the objects in the scene are to be exported into an image with the identical dimensions of the
		 * scene's visual window.
		 */
		FIT_IN_WINDOW,
		/**
		 * Used when the objects in the scene are to be exported into an image scaled the same as the scene.
		 */
		CURRENT_ZOOM_LEVEL,
		/**
		 * Used to export an image of the scene according to its boundary dimensions.
		 */
		ACTUAL_SIZE
	}



	// /**
	// * Finds and returns the {@link WidgetObject} that is located at the most eastern edge on a {@link
	// WorkareaCanvas}.
	// *
	// * @param canvas
	// * @return
	// */
	// public static int getBottomWidgetPointOnCanvas(WorkareaCanvas canvas)
	// {
	// // All the widgets
	// WidgetObject objects[] = canvas.getWidgetObjectsOnTheScene();
	//
	// Point bottom = objects[0].getLocation();
	//
	//
	// for ( int i = 0; i < objects.length; i++ )
	// {
	// // The rectangle that represents the WidgetRoom on a WorkareaCanvas
	// Rectangle objectRec = objects[i].getBounds();
	//
	// // The Point at the center of the WidgetRoom
	// Point objectTopLeftPoint = objects[i].getLocation();
	//
	// // Since we are looking for height
	// int height = objectRec.height;
	//
	// // Lowest point on the Y-axis for the WidgetRoom( which is the starting point plus the height )
	// int bottomWidgetYpoint = objectTopLeftPoint.y + height;
	//
	// // This is the position of the Widget bottom border
	// Point widgetBottomPoint = new Point(objectTopLeftPoint.x, bottomWidgetYpoint);
	//
	//
	// // If the WidgetObjects y position is lower then the current lowest y
	// if ( widgetBottomPoint.y > bottom.y )
	// {
	// bottom = widgetBottomPoint;
	// }
	// }
	//
	//
	// WidgetRoom rooms[] = canvas.getNetworkWidgetRooms();
	//
	// for ( int i = 0; i < rooms.length; i++ )
	// {
	// // The rectangle that represents the WidgetRoom on a WorkareaCanvas
	// Rectangle roomRec = rooms[i].getBounds();
	//
	// // The Point at the center of the WidgetRoom
	// Point roomTopLeftPoint = rooms[i].getLocation();
	//
	// // Since we are looking for height
	// int height = roomRec.height;
	//
	// // Lowest point on the Y-axis for the WidgetRoom( which is the starting point plus the height )
	// int bottomWidgetRoomYpoint = roomTopLeftPoint.y + height;
	//
	// // This is the position of the WidgetRooms bottom border
	// Point roomBottomPoint = new Point(roomTopLeftPoint.x, bottomWidgetRoomYpoint + 15);
	//
	//
	// // If the point(in the Y-axis) is lower(higher value) then the currently set lowest(higher value)
	// if ( roomBottomPoint.y > bottom.y )
	// {
	// bottom = roomBottomPoint;
	// }
	// }
	//
	//
	//
	// return bottom.y;
	// }
	//
	//
	// /**
	// * Finds and returns the {@link WidgetObject} that is located at the highest edge on a {@link WorkareaCanvas}.
	// *
	// * @param canvas
	// * @return
	// */
	// public static int getTopWidgetPointOnCanvas(WorkareaCanvas canvas)
	// {
	// // All the widgets
	// WidgetObject objects[] = canvas.getWidgetObjectsOnTheScene();
	//
	// Point top = objects[0].getLocation();
	//
	//
	// for ( int i = 0; i < objects.length; i++ )
	// {
	// // The Point at the center of the WidgetRoom
	// Point widgetTopLeftPoint = objects[i].getLocation();
	//
	// // Highest point on the Y-axis for the WidgetRoom( which is the starting point )
	// int topWidgetYpoint = widgetTopLeftPoint.y;
	//
	// // This is the position of the WidgetRooms highest border
	// Point widgetTopPoint = new Point(widgetTopLeftPoint.x, topWidgetYpoint);
	//
	//
	// // If the WidgetObjects y position is higher then the current highest y
	// if ( widgetTopPoint.y < top.y )
	// {
	// top = widgetTopPoint;
	// }
	// }
	//
	//
	//
	// WidgetRoom rooms[] = canvas.getNetworkWidgetRooms();
	//
	// for ( int i = 0; i < rooms.length; i++ )
	// {
	// // The Point at the center of the WidgetRoom
	// Point roomTopLeftPoint = rooms[i].getLocation();
	//
	// // Highest point on the Y-axis for the WidgetRoom( which is the starting point )
	// int topWidgetRoomYpoint = roomTopLeftPoint.y;
	//
	// // This is the position of the WidgetRooms highest border
	// Point roomTopPoint = new Point(roomTopLeftPoint.x, topWidgetRoomYpoint);
	//
	//
	// // If the point(in the Y-axis) is highest(lower value) then the currently set highest(lower value)
	// if ( roomTopPoint.y < top.y )
	// {
	// top = roomTopPoint;
	// }
	// }
	//
	//
	//
	// return top.y;
	// }
	//
	//
	//
	// /**
	// * Finds and returns the {@link WidgetObject} that is located at the most western edge on a {@link
	// WorkareaCanvas}.
	// *
	// * @param canvas
	// * @return
	// */
	// public static int getWesternWidgetPointOnCanvas(WorkareaCanvas canvas)
	// {
	// // All the widgets
	// WidgetObject objects[] = canvas.getWidgetObjectsOnTheScene();
	//
	// Point west = objects[0].getLocation();
	//
	//
	// for ( int i = 0; i < objects.length; i++ )
	// {
	// // The rectangle that represents the WidgetRoom on a WorkareaCanvas
	// Rectangle widgetRec = objects[i].getBounds();
	//
	// // The Point at the center of the WidgetRoom
	// Point widgetTopLeftPoint = objects[i].getLocation();
	//
	// // Since we are looking for height
	// int width = widgetRec.width;
	//
	// // West point on the X-axis for the WidgetRoom( which is the starting point plus the width )
	// int westWidgetXpoint = widgetTopLeftPoint.x + width;
	//
	// // This is the position of the WidgetRooms west border
	// Point roomWestPoint = new Point(westWidgetXpoint, widgetTopLeftPoint.y);
	//
	//
	// // If the WidgetObjects X position is higher then the current highest X
	// if ( roomWestPoint.x > west.x )
	// {
	// west = roomWestPoint;
	// }
	// }
	//
	//
	// WidgetRoom rooms[] = canvas.getNetworkWidgetRooms();
	//
	// for ( int i = 0; i < rooms.length; i++ )
	// {
	// // The rectangle that represents the WidgetRoom on a WorkareaCanvas
	// Rectangle roomRec = rooms[i].getBounds();
	//
	// // The Point at the center of the WidgetRoom
	// Point roomTopLeftPoint = rooms[i].getLocation();
	//
	// // Since we are looking for height
	// int width = roomRec.width;
	//
	// // West point on the X-axis for the WidgetRoom( which is the starting point plus the width )
	// int westWidgetRoomXpoint = roomTopLeftPoint.x + width;
	//
	// // This is the position of the WidgetRooms west border
	// Point roomWestPoint = new Point(westWidgetRoomXpoint, roomTopLeftPoint.y);
	//
	//
	// // If the point(in the X-axis) is more west then the currently set west
	// if ( roomWestPoint.x > west.x )
	// {
	// west = roomWestPoint;
	// }
	// }
	//
	//
	// return west.x;
	// }
	//
	//
	// /**
	// * Finds and returns the {@link WidgetObject} that is located at the most eastern edge on a {@link
	// WorkareaCanvas}.
	// *
	// * @param canvas
	// * @return
	// */
	// public static int getEsternWidgetPointOnCanvas(WorkareaCanvas canvas)
	// {
	// // All the widgets
	// WidgetObject objects[] = canvas.getWidgetObjectsOnTheScene();
	//
	// Point east = objects[0].getLocation();
	//
	//
	// for ( int i = 0; i < objects.length; i++ )
	// {
	// // The Point at the center of the WidgetRoom
	// Point widgetTopLeftPoint = objects[i].getLocation();
	//
	// // West point on the X-axis for the WidgetRoom( which is the starting point )
	// int eastWidgetXpoint = widgetTopLeftPoint.x;
	//
	// // This is the position of the WidgetRooms west border
	// Point widgetEastPoint = new Point(eastWidgetXpoint, widgetTopLeftPoint.y);
	//
	// // If the WidgetObjects X position is higher then the current highest X
	// if ( widgetEastPoint.x < east.x )
	// {
	// east = widgetEastPoint;
	// }
	// }
	//
	//
	// WidgetRoom rooms[] = canvas.getNetworkWidgetRooms();
	//
	// for ( int i = 0; i < rooms.length; i++ )
	// {
	// // The Point at the center of the WidgetRoom
	// Point roomTopLeftPoint = rooms[i].getLocation();
	//
	// // West point on the X-axis for the WidgetRoom( which is the starting point )
	// int eastWidgetRoomXpoint = roomTopLeftPoint.x;
	//
	// // This is the position of the WidgetRooms west border
	// Point roomEastPoint = new Point(eastWidgetRoomXpoint, roomTopLeftPoint.y);
	//
	//
	// // If the point(in the X-axis) is more east then the currently set west
	// if ( roomEastPoint.x < east.x )
	// {
	// east = roomEastPoint;
	// }
	// }
	//
	//
	// return east.x;
	// }
	//
	//
	//
	// /**
	// * Gets the starting {@link Point} that the first {@link Widget} appears on the {@link WorkareaCanvas}.
	// *
	// * @param canvas
	// * @return Returns a {@link Point} where the first {@link Widget} appears on the {@link WorkareaCanvas}.
	// */
	// public static Point getCanvasStart(WorkareaCanvas canvas)
	// {
	// // Gets the positions of the different widgets at the edges of the WorkareaCanvas
	// int top = getTopWidgetPointOnCanvas(canvas);
	// int left = getEsternWidgetPointOnCanvas(canvas);
	//
	// Point first = new Point(left, top);
	//
	// return first;
	// }
	//
	//
	//
	// /**
	// * Gets the ending {@link Point} that the last {@link Widget} appears on the {@link WorkareaCanvas}.
	// *
	// * @param canvas
	// * @return Returns a {@link Point} where the last {@link Widget} appears on the {@link WorkareaCanvas}.
	// */
	// public static Point getCanvasEnd(WorkareaCanvas canvas)
	// {
	// // Gets the positions of the different widgets at the edges of the WorkareaCanvas
	// int bottom = getBottomWidgetPointOnCanvas(canvas);
	// int right = getWesternWidgetPointOnCanvas(canvas);
	//
	// Point last = new Point(right, bottom);
	//
	// return last;
	// }
}
