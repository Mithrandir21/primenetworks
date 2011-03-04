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
package widgets;



import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

import objects.Object;
import objects.softwareObjects.OperatingSystem;

import org.netbeans.api.visual.laf.LookFeel;
import org.netbeans.api.visual.layout.LayoutFactory;
import org.netbeans.api.visual.model.ObjectState;
import org.netbeans.api.visual.widget.ImageWidget;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.modules.visual.util.GeomUtil;

import widgetManipulation.WidgetNetworkInfo;


/**
 * This class represents an {@link Object} in the form of a {@link Widget}. The
 * class is made {@link Transferable} to enable drag and drop feature for the
 * {@link WorkareaCanvas}. It is this class, and not the {@link Object} class
 * itself, that contains the information about the objects network
 * configuration, ie. {@link WidgetNetworkInfo}. The Widgets children are a
 * {@link ImageWidget}, that will be present an image representing the
 * {@link Object} type, and a {@link LabelWidget} that will display the name of
 * the {@link Object}.
 * 
 * @author Bahram Malaekeh
 */
public class WidgetObject extends Widget implements Transferable
{
	// The object that the widget represents.
	private Object object = null;

	// The information regarding the widgets in the network
	private WidgetNetworkInfo widNetInfo = new WidgetNetworkInfo();

	private static final DataFlavor[] flavors = new DataFlavor[1];


	/**
	 * Contains an image representing the {@link Object} type.
	 */
	private ObjectImageWidget imageWidget;

	/**
	 * Contains the name of the {@link Object}.
	 */
	private LabelWidget labelWidget;

	/**
	 * Contains the IP of the {@link Object}.
	 */
	private LabelWidget ipLabelWidget;


	/**
	 * A constructor for this class. The method sets the layout for the
	 * placement of children, {@link ImageWidget} and {@link LabelWidget}.
	 * 
	 * @param scene
	 *            The {@link Scene} where the {@link Object} is to be placed.
	 * @param obj
	 *            The {@link Object} this class represents.
	 * @param objImg
	 *            The {@link Image} that will represent the type of {@link
	 *            Object.
	 */
	public WidgetObject(Scene scene, Object obj, Image objImg)
	{
		super(scene);
		this.object = obj;

		LookFeel lookFeel = getScene().getLookFeel();

		this.setLayout(LayoutFactory.createVerticalFlowLayout(
				LayoutFactory.SerialAlignment.CENTER, -lookFeel.getMargin() + 1));

		this.labelWidget = new LabelWidget(scene);
		// labelWidget.setFont(scene.getDefaultFont().deriveFont(14.0f));
		this.addChild(this.labelWidget);

		this.imageWidget = new ObjectImageWidget(scene, objImg);
		this.addChild(this.imageWidget);

		this.ipLabelWidget = new LabelWidget(scene);
		this.ipLabelWidget.setLabel("");
		this.addChild(this.ipLabelWidget);


		this.setState(ObjectState.createNormal());

		this.setFlavor();
	}



	// GETTERS
	/**
	 * @return The {@link Object} represented by this class.
	 */
	public Object getObject()
	{
		return this.object;
	}



	/**
	 * @return The information regarding the widget in a network.
	 */
	public WidgetNetworkInfo getWidgetNetworkInfo()
	{
		return this.widNetInfo;
	}



	/**
	 * @return The image representing the {@link Object}.
	 */
	public Image getImage()
	{
		return this.imageWidget.getImage();
	}



	/**
	 * Returns a label.
	 * 
	 * @return the label
	 */
	public String getLabel()
	{
		return this.labelWidget.getLabel();
	}



	/**
	 * @return The {@link ImageWidget} that contains the {@link Image} that
	 *         represents the type {@link Object} represented by this class.
	 */
	public ImageWidget getImageWidget()
	{
		return this.imageWidget;
	}



	/**
	 * @return The {@link LabelWidget} that contains the name that
	 *         {@link Object} represented by this class.
	 */
	public LabelWidget getLabelWidget()
	{
		return this.labelWidget;
	}



	/**
	 * @return The {@link LabelWidget} that contains the IP of the
	 *         {@link Object} represented by this class.
	 */
	public LabelWidget getIPlabelWidget()
	{
		return this.ipLabelWidget;
	}



	// SETTERS
	/**
	 * Sets the {@link Object} represented by this class.
	 */
	public void setObject(Object object)
	{
		this.object = object;
	}


	/**
	 * Sets the {@link WidgetNetworkInfo} of this Object.
	 * 
	 * @param netInfo
	 */
	public void setWidgetNetworkInfo(WidgetNetworkInfo netInfo)
	{
		this.widNetInfo = netInfo;
	}


	/**
	 * Sets a label.
	 * 
	 * @param label
	 *            the label
	 */
	public final void setLabel(String label)
	{
		this.labelWidget.setLabel(label);
	}



	/**
	 * @return The {@link ImageWidget} that contains the {@link Image} that
	 *         represents the type {@link Object} represented by this class.
	 */
	public void setWidgetImage(ImageIcon icon)
	{
		this.object.setVisualImage(icon);
		this.imageWidget.setImage(icon.getImage());
	}



	/**
	 * Sets the {@link OperatingSystem} icon for the widget.
	 */
	public void setOSimage(Image osImage)
	{
		if ( osImage != null && this.imageWidget != null )
		{
			this.imageWidget.setOSImageWidget(osImage);
		}
	}



	/**
	 * Removes the {@link OperatingSystem} icon for the widget.
	 */
	public void removeOSimage()
	{
		if ( this.imageWidget != null )
		{
			this.imageWidget.setOSImageWidget(null);
		}
	}

	// TRANSFERABLE IMPLEMENTATION
	public WidgetObject getTransferData(DataFlavor flavor)
	{
		// System.out.println("WidgetCanvas - getTransferData");
		if ( isDataFlavorSupported(flavor) )
		{
			return this;
		}
		return null;
	}



	public DataFlavor[] getTransferDataFlavors()
	{
		// System.out.println("WidgetCanvas - getTransferDataFlavors");
		return flavors;
	}



	public boolean isDataFlavorSupported(DataFlavor flavor)
	{
		return flavors[0].equals(flavor);
	}




	private DataFlavor[] setFlavor()
	{
		// System.out.println("WidgetCanvas - setFlavor");
		if ( flavors[0] == null )
		{
			flavors[0] = new DataFlavor(WidgetObject.class, "Widget Object");
		}
		// System.out.println("WidgetCanvas - " + flavors[0]);
		return flavors;
	}




	// DIMENSIONS METHODS
	/**
	 * @return A new Dimension with the size of the image.
	 */
	public Dimension getImageDimension()
	{
		return new Dimension(this.imageWidget.getImage().getHeight(null),
				this.imageWidget.getImage().getWidth(null));
	}



	/**
	 * @return A new Dimension with the size of widgets label,
	 *         {@link LabelWidget}.
	 */
	public Dimension getLabelDimension()
	{
		Graphics2D gr = getScene().getGraphics();
		FontMetrics fontMetrics = gr.getFontMetrics();
		Rectangle2D stringBounds = fontMetrics.getStringBounds(
				this.object.getObjectName(), gr);
		Rectangle rectangle = GeomUtil.roundRectangle(stringBounds);


		return new Dimension(rectangle.width, rectangle.height);
	}
}
