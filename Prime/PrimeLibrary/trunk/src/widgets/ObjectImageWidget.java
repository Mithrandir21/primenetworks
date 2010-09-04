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


import java.awt.Image;
import java.awt.Point;

import objects.Object;
import objects.softwareObjects.OperatingSystem;

import org.netbeans.api.visual.layout.LayoutFactory;
import org.netbeans.api.visual.model.ObjectState;
import org.netbeans.api.visual.widget.ImageWidget;
import org.netbeans.api.visual.widget.Scene;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class ObjectImageWidget extends ImageWidget
{
	/**
	 * Contains an image representing the {@link OperatingSystem} installed on
	 * the {@link Object}.
	 */
	private ImageWidget OSimageWidget;


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param scene
	 * @param image
	 */
	public ObjectImageWidget(Scene scene, Image image)
	{
		super(scene, image);

		this.setLayout(LayoutFactory.createAbsoluteLayout());

		OSimageWidget = new ImageWidget(scene);
		if ( image != null )
		{
			// OSimageWidget.setBorder(BorderFactory.createLineBorder());
			OSimageWidget.setPreferredLocation(new Point(
					image.getWidth(null) - 20, image.getHeight(null) - 20));
		}
		this.addChild(OSimageWidget);

		this.setState(ObjectState.createNormal());
	}




	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param imageWidget
	 *            the imageWidget to set
	 */
	public void setOSImageWidget(Image osImage)
	{
		if ( osImage != null )
		{
			this.OSimageWidget.setImage(osImage);
		}
		else
		{
			this.OSimageWidget.setImage(null);
		}
	}




	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the imageWidget
	 */
	public ImageWidget getOSImageWidget()
	{
		return OSimageWidget;
	}
}
