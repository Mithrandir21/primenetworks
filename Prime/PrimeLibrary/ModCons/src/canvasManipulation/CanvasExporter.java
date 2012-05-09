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
package canvasManipulation;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import widgets.WorkareaCanvas;


/**
 * This is a convenience class for exporting a Scene as an image file (PNG and JPG).
 * 
 * @author krichard (extended by Bahram Malaekeh)
 */
public final class CanvasExporter
{
	/**
	 * Takes the Scene and writes an image file according to the constraints defined by the caller. This returns a
	 * BufferedImage of the Scene even if the file can not be written.
	 * 
	 * @param file
	 *            The file used to store the exported image. If null, then it is assumed that the raw image is to be
	 *            returned only and not written to a file.
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
	public static BufferedImage createImage(WorkareaCanvas canvas, File file,
			ImageType imageType, ZoomType zoomType, boolean visibleAreaOnly,
			boolean selectedOnly, int quality, int width, int height)
			throws IOException
	{

		if ( canvas == null )
		{
			return null;
		}


		Canvas2Image c2i = new Canvas2Image(canvas.getScene(), file);
		BufferedImage image = c2i.createImage(imageType, zoomType,
				visibleAreaOnly, selectedOnly, quality, canvas, false);

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
