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


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.swing.JOptionPane;

import org.netbeans.api.visual.model.ObjectScene;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

import widgets.WorkareaCanvas;
import canvasManipulation.CanvasExporter.ImageType;
import canvasManipulation.CanvasExporter.ZoomType;


/**
 * This class contains two specific functions that exports the {@link Scene} of
 * any {@link WorkareaCanvas} to a PNG or JPG file.
 * 
 * @author krichard (extended by Bahram Malaekeh)
 */
public class Canvas2Image
{

	private final File file;

	private final Scene scene;

	private Set<?> selectedObjects;

	private int imageHeight;

	private int imageWidth;

	private double scale;


	/**
	 * Constructor for this class.
	 * 
	 * @param scene
	 *            The scene that is to be written out as an Image.
	 * @param file
	 *            The {@link File} the scene will be written out to.
	 */
	public Canvas2Image(Scene scene, File file)
	{
		this.scene = scene;
		this.file = file;
	}


	/**
	 * Takes the Scene and writes an image file according to the constraints
	 * defined by the caller. This returns a BufferedImage of the Scene even if
	 * the file can not be written.
	 * 
	 * @param visibleAreaOnly
	 *            Eliminates all zoom features. If true, the exported image will
	 *            be a created from the visible area of the scene.
	 * @param selectedOnly
	 *            Create an image including only the objects selected on the
	 *            scene. Note that this feature requires that the scene is an
	 *            instance of an ObjectScene since it is the implementation that
	 *            allows for object selection.
	 * @param quality
	 *            And integer value between 0-100. This is for JPG images only.
	 *            Parameter is not used if an image type other than jpg is
	 *            selected.
	 * @param width
	 *            Directly sets the horizontal dimension of the exported image.
	 *            This is only used when the zoomType is ZoomType.CUSTOM_SIZE
	 * @param height
	 *            Directly sets the vertical dimension of the exported image.
	 *            This is only used when the zoomType is ZoomType.CUSTOM_SIZE.
	 * @param createImageMap
	 *            If true, the necessary steps are taken to setup the sequential
	 *            call to getSceneImageMapCoordinates.
	 * @return image The raw image that was written to the file.
	 * @throws java.io.IOException
	 *             If for some reason the file cannot be written, an IOExeption
	 *             will be thrown.
	 */
	public BufferedImage createImage(ImageType imageType, ZoomType zoomType,
			boolean visibleAreaOnly, boolean selectedOnly, int quality,
			int width, int height, boolean createImageMap) throws IOException
	{
		double _scale = scene.getZoomFactor();

		Rectangle sceneRec = scene.getPreferredBounds();
		Rectangle viewRect = scene.getView().getVisibleRect();

		BufferedImage bufferedImage;
		Graphics2D g;
		ArrayList<Widget> hiddenWidgets = new ArrayList<Widget>();

		int _imageWidth = sceneRec.width;
		int _imageHeight = sceneRec.height;

		Set<?> _selectedObjects = null;

		if ( selectedOnly )
		{
			// in order to use getSelectedObject the scene must be an
			// ObjectScene
			if ( scene instanceof ObjectScene )
			{

				ObjectScene gScene = (ObjectScene) scene;
				// hide unselected widget
				HashSet<Object> invisible = new HashSet<Object>();
				invisible.addAll(gScene.getObjects());
				_selectedObjects = gScene.getSelectedObjects();
				invisible.removeAll(_selectedObjects);

				for ( Object o : invisible )
				{
					Widget widget = gScene.findWidget(o);
					if ( widget != null && widget.isVisible() )
					{
						widget.setVisible(false);
						hiddenWidgets.add(widget);
					}
				}
			}
		}

		if ( visibleAreaOnly )
		{
			_imageWidth = viewRect.width;
			_imageHeight = viewRect.height;
		}
		else
		{
			switch ( zoomType )
			{
			case CUSTOM_SIZE:
				_imageWidth = width;
				_imageHeight = height;
				_scale = Math.min((double) width / (double) sceneRec.width,
						(double) height / (double) sceneRec.height);
				break;
			case FIT_IN_WINDOW:
				_scale = Math.min((double) viewRect.width
						/ (double) sceneRec.width, (double) viewRect.height
						/ (double) sceneRec.height);
				_imageWidth = (int) ((double) sceneRec.width * _scale);
				_imageHeight = (int) ((double) sceneRec.height * _scale);
				break;
			case CURRENT_ZOOM_LEVEL:
				_imageWidth = (int) (sceneRec.width * scene.getZoomFactor());
				_imageHeight = (int) (sceneRec.height * scene.getZoomFactor());
				break;
			case ACTUAL_SIZE:
				_imageWidth = sceneRec.width;
				_imageHeight = sceneRec.height;
				_scale = 1.0;
				break;
			}
		}

		// Note that the field variable are being set to method local variable.
		// This
		// is for the call to getSceneImageMapCoordinates that will come since
		// createImageMap is true.
		if ( createImageMap )
		{
			this.selectedObjects = _selectedObjects;
			this.imageHeight = _imageHeight;
			this.imageWidth = _imageWidth;
			this.scale = _scale;
		}


		bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		g = bufferedImage.createGraphics();
		g.translate(0, 0);
		g.scale(_scale, _scale);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		scene.paint(g);

		// restore widget visibility
		for ( Widget w : hiddenWidgets )
		{
			w.setVisible(true);
		}

		if ( file != null )
		{
			FileImageOutputStream fo = new FileImageOutputStream(file);

			if ( imageType == ImageType.JPG )
			{
				Iterator<?> iter = ImageIO.getImageWritersByFormatName("jpg");
				ImageWriter writer = (ImageWriter) iter.next();

				ImageWriteParam iwp = writer.getDefaultWriteParam();
				iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
				if ( quality > 100 )
				{
					quality = 100;
				}
				if ( quality < 0 )
				{
					quality = 0;
				}
				iwp.setCompressionQuality(quality / 100);
				writer.setOutput(fo);
				IIOImage image = new IIOImage(bufferedImage, null, null);
				writer.write(null, image, iwp);

				writer.dispose();
			}
			else
			{
				ImageIO.write(bufferedImage, "" + imageType, fo);
			}

			fo.flush();
			fo.close();
		}

		return bufferedImage;

	}




	/**
	 * Takes the Scene and writes an image file according to the constraints
	 * defined by the caller. This returns a BufferedImage of the {@link Scene} even if the file can not be written. This function
	 * creates the image for
	 * the entire {@link Scene}.
	 * 
	 * @param visibleAreaOnly
	 *            Eliminates all zoom features. If true, the exported image will
	 *            be a created from the visible area of the scene.
	 * @param selectedOnly
	 *            Create an image including only the objects selected on the
	 *            scene. Note that this feature requires that the scene is an
	 *            instance of an ObjectScene since it is the implementation that
	 *            allows for object selection.
	 * @param quality
	 *            And integer value between 0-100. This is for JPG images only.
	 *            Parameter is not used if an image type other than jpg is
	 *            selected.
	 * @param canvas
	 *            The {@link WorkareaCanvas} that is to exported.
	 * @param createImageMap
	 *            If true, the necessary steps are taken to setup the sequential
	 *            call to getSceneImageMapCoordinates.
	 * @return image The raw image that was written to the file.
	 * @throws java.io.IOException
	 *             If for some reason the file cannot be written, an IOExeption
	 *             will be thrown.
	 */
	public BufferedImage createImage(ImageType imageType, ZoomType zoomType,
			boolean visibleAreaOnly, boolean selectedOnly, int quality,
			WorkareaCanvas canvas, boolean createImageMap) throws IOException
	{
		// Creates a rectangle with the starting point and the dimensions of the
		// scene seleceted for export
		Rectangle rectangle = canvas.getScene().getClientArea();


		double _scale = scene.getZoomFactor();

		Rectangle sceneRec = scene.getPreferredBounds();
		Rectangle viewRect = scene.getView().getVisibleRect();

		BufferedImage bufferedImage;
		Graphics2D g;
		ArrayList<Widget> hiddenWidgets = new ArrayList<Widget>();

		int _imageWidth = sceneRec.width;
		int _imageHeight = sceneRec.height;

		Set<?> _selectedObjects = null;

		if ( selectedOnly )
		{
			// in order to use getSelectedObject the scene must be an
			// ObjectScene
			if ( scene instanceof ObjectScene )
			{

				ObjectScene gScene = (ObjectScene) scene;
				// hide unselected widget
				HashSet<Object> invisible = new HashSet<Object>();
				invisible.addAll(gScene.getObjects());
				_selectedObjects = gScene.getSelectedObjects();
				invisible.removeAll(_selectedObjects);

				for ( Object o : invisible )
				{
					Widget widget = gScene.findWidget(o);
					if ( widget != null && widget.isVisible() )
					{
						widget.setVisible(false);
						hiddenWidgets.add(widget);
					}
				}
			}
		}

		if ( visibleAreaOnly )
		{
			_imageWidth = viewRect.width;
			_imageHeight = viewRect.height;
		}
		else
		{
			switch ( zoomType )
			{
			case CUSTOM_SIZE:
				_imageWidth = rectangle.width;
				_imageHeight = rectangle.height;
				_scale = Math.min((double) rectangle.width
						/ (double) sceneRec.width, (double) rectangle.height
						/ (double) sceneRec.height);
				break;
			case FIT_IN_WINDOW:
				_scale = Math.min((double) viewRect.width
						/ (double) sceneRec.width, (double) viewRect.height
						/ (double) sceneRec.height);
				_imageWidth = (int) ((double) sceneRec.width * _scale);
				_imageHeight = (int) ((double) sceneRec.height * _scale);
				break;
			case CURRENT_ZOOM_LEVEL:
				_imageWidth = (int) (sceneRec.width * scene.getZoomFactor());
				_imageHeight = (int) (sceneRec.height * scene.getZoomFactor());
				break;
			case ACTUAL_SIZE:
				_imageWidth = sceneRec.width;
				_imageHeight = sceneRec.height;
				_scale = 1.0;
				break;
			}
		}

		// Note that the field variable are being set to method local variable.
		// This
		// is for the call to getSceneImageMapCoordinates that will come since
		// createImageMap is true.
		if ( createImageMap )
		{
			this.selectedObjects = _selectedObjects;
			this.imageHeight = _imageHeight;
			this.imageWidth = _imageWidth;
			this.scale = _scale;
		}


		bufferedImage = new BufferedImage(rectangle.width, rectangle.height,
				BufferedImage.TYPE_INT_RGB);
		g = bufferedImage.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.scale(_scale, _scale);
		g.setColor(Color.WHITE);
		g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		scene.paint(g);


		// restore widget visibility
		for ( Widget w : hiddenWidgets )
		{
			w.setVisible(true);
		}

		if ( file != null )
		{
			boolean save = true;

			if ( file.exists() )
			{
				int answer = JOptionPane.showConfirmDialog(null,
						"This file exists. Do you wish to overwrite the file?",
						"Confirm", JOptionPane.YES_NO_OPTION);

				// The user has not pressed YES
				if ( answer != 0 )
				{
					save = false;
				}
			}

			if ( save )
			{
				FileImageOutputStream fo = new FileImageOutputStream(file);

				if ( imageType == ImageType.JPG )
				{
					Iterator<?> iter = ImageIO
							.getImageWritersByFormatName("jpg");
					ImageWriter writer = (ImageWriter) iter.next();

					ImageWriteParam iwp = writer.getDefaultWriteParam();
					iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
					if ( quality > 100 )
					{
						quality = 100;
					}
					if ( quality < 0 )
					{
						quality = 0;
					}
					iwp.setCompressionQuality(quality / 100);
					writer.setOutput(fo);
					IIOImage image = new IIOImage(bufferedImage, null, null);
					writer.write(null, image, iwp);

					writer.dispose();
				}
				else
				{
					ImageIO.write(bufferedImage, "" + imageType, fo);
				}

				fo.flush();
				fo.close();
			}
		}

		return bufferedImage;

	}
}
