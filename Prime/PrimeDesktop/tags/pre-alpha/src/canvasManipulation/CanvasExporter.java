/**
 * 
 */
package canvasManipulation;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.netbeans.api.visual.widget.Scene;


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
	public static BufferedImage createImage(Scene scene, File file, ImageType imageType, ZoomType zoomType,
			boolean visibleAreaOnly, boolean selectedOnly, int quality, int width, int height) throws IOException
	{

		if ( scene == null )
		{
			return null;
		}

		Canvas2Image c2i = new Canvas2Image(scene, file);
		BufferedImage image = c2i.createImage(imageType, zoomType, visibleAreaOnly, selectedOnly, quality, width,
				height, false);

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
}
