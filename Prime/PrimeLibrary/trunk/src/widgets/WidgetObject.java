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
package widgets;



import java.awt.Dimension;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import objects.Object;

import org.netbeans.api.visual.laf.LookFeel;
import org.netbeans.api.visual.layout.LayoutFactory;
import org.netbeans.api.visual.model.ObjectState;
import org.netbeans.api.visual.widget.ImageWidget;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

import widgetManipulation.WidgetNetworkInfo;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class WidgetObject extends Widget implements Transferable
{
	// The object that the widget represents.
	private Object object = null;

	// The information regarding the widgets in the network
	private WidgetNetworkInfo widNetInfo = new WidgetNetworkInfo();

	private static final DataFlavor flavors[] = new DataFlavor[1];


	private ImageWidget imageWidget;

	private LabelWidget labelWidget;


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param scene
	 * @param obj
	 * @param objImg
	 */
	public WidgetObject(Scene scene, Object obj, Image objImg)
	{
		super(scene);
		object = obj;

		LookFeel lookFeel = getScene().getLookFeel();

		this.setLayout(LayoutFactory
				.createVerticalFlowLayout(LayoutFactory.SerialAlignment.CENTER,
						-lookFeel.getMargin() + 1));

		labelWidget = new LabelWidget(scene);
		// labelWidget.setFont(scene.getDefaultFont().deriveFont(14.0f));
		this.addChild(labelWidget);

		imageWidget = new ImageWidget(scene);
		imageWidget.setImage(objImg);
		this.addChild(imageWidget);


		this.setState(ObjectState.createNormal());

		this.setFlavor();
	}



	// GETTERS
	/**
	 * @return the object
	 */
	public Object getObject()
	{
		return object;
	}



	/**
	 * @return The information regarding the widget in a network.
	 */
	public WidgetNetworkInfo getWidgetNetworkInfo()
	{
		return widNetInfo;
	}



	/**
	 * @return The image representing the {@link Object}.
	 */
	public Image getImage()
	{
		return imageWidget.getImage();
	}



	/**
	 * @return A new Dimension with the size of the image.
	 */
	public Dimension getImageDimension()
	{
		return new Dimension(imageWidget.getImage().getHeight(null),
				imageWidget.getImage().getWidth(null));
	}


	/**
	 * Returns a label.
	 * 
	 * @return the label
	 */
	public String getLabel()
	{
		return labelWidget.getLabel();
	}



	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the imageWidget
	 */
	public ImageWidget getImageWidget()
	{
		return imageWidget;
	}



	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the labelWidget
	 */
	public LabelWidget getLabelWidget()
	{
		return labelWidget;
	}



	// SETTERS
	/**
	 * @param object
	 *            the object to set
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
		widNetInfo = netInfo;
	}


	/**
	 * Sets a label.
	 * 
	 * @param label
	 *            the label
	 */
	public final void setLabel(String label)
	{
		labelWidget.setLabel(label);
	}



	// TRANSFERABLE IMPLEMENTATION
	public WidgetObject getTransferData(DataFlavor flavor)
			throws UnsupportedFlavorException, IOException
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
}
