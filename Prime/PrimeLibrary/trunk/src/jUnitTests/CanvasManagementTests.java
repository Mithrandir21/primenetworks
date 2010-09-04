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
package jUnitTests;


import java.awt.Point;

import junit.framework.Assert;
import managment.CanvasManagment;
import objects.Object;

import org.junit.Test;

import widgetManipulation.Actions.WorkareaCanvasActions;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class CanvasManagementTests
{
	/**
	 * Test method for
	 * {@link managment.CanvasManagment#findCanvas(java.lang.String, widgets.WorkareaCanvas[])}
	 * .
	 */
	@Test
	public void testFindCanvasStringWorkareaCanvasArray()
	{
		WorkareaCanvas canvas1 = new WorkareaCanvas("Canvas1");
		WorkareaCanvas canvas2 = new WorkareaCanvas("Canvas2");
		WorkareaCanvas canvas3 = new WorkareaCanvas("Canvas3");
		WorkareaCanvas canvas4 = new WorkareaCanvas("Canvas4");
		WorkareaCanvas canvas5 = new WorkareaCanvas("Canvas5");
		WorkareaCanvas canvas6 = new WorkareaCanvas("Canvas6");

		WorkareaCanvas[] canvases = { canvas1, canvas2, canvas3, canvas4,
				canvas5, canvas6 };


		WorkareaCanvas found = CanvasManagment.findCanvas("Canvas1", canvases);

		Assert.assertNotNull(found);

		Assert.assertEquals("Canvas1", found.getCanvasName());



		found = CanvasManagment.findCanvas("Empty", canvases);

		Assert.assertNull(found);
	}

	/**
	 * Test method for
	 * {@link managment.CanvasManagment#findCanvas(org.netbeans.api.visual.widget.Scene, widgets.WorkareaCanvas[])}
	 * .
	 */
	@Test
	public void testFindCanvasSceneWorkareaCanvasArray()
	{
		WorkareaCanvas canvas1 = new WorkareaCanvas("Canvas1");
		WorkareaCanvas canvas2 = new WorkareaCanvas("Canvas2");
		WorkareaCanvas canvas3 = new WorkareaCanvas("Canvas3");
		WorkareaCanvas canvas4 = new WorkareaCanvas("Canvas4");
		WorkareaCanvas canvas5 = new WorkareaCanvas("Canvas5");
		WorkareaCanvas canvas6 = new WorkareaCanvas("Canvas6");

		WorkareaCanvas canvas7 = new WorkareaCanvas("Canvas7");

		WorkareaCanvas[] canvases = { canvas1, canvas2, canvas3, canvas4,
				canvas5, canvas6 };


		WorkareaCanvas found = CanvasManagment.findCanvas(canvas1.getScene(),
				canvases);

		Assert.assertNotNull(found);

		Assert.assertEquals("Canvas1", found.getCanvasName());



		found = CanvasManagment.findCanvas(canvas7.getScene(), canvases);

		Assert.assertNull(found);
	}

	/**
	 * Test method for
	 * {@link managment.CanvasManagment#findCanvas(objects.Object, widgets.WorkareaCanvas[])}
	 * .
	 */
	@Test
	public void testFindCanvasObjectWorkareaCanvasArray()
	{
		Object[] objs = ObjectCreationClass.ObjectCreationClass();
		Object toBeAdded = objs[0];


		WorkareaCanvas canvas1 = new WorkareaCanvas("Canvas1");

		// Creates a new WidgetObject that will be added to the scene
		WidgetObject newWidgetObject = new WidgetObject(canvas1.getScene(),
				toBeAdded, null);

		// Add the (possibly modified) object to the given canvas
		WorkareaCanvasActions.addWidgetToCanvas(newWidgetObject,
				new Point(0, 0), canvas1, false);


		WorkareaCanvas canvas2 = new WorkareaCanvas("Canvas2");
		WorkareaCanvas canvas3 = new WorkareaCanvas("Canvas3");
		WorkareaCanvas canvas4 = new WorkareaCanvas("Canvas4");
		WorkareaCanvas canvas5 = new WorkareaCanvas("Canvas5");
		WorkareaCanvas canvas6 = new WorkareaCanvas("Canvas6");

		WorkareaCanvas[] canvases = { canvas1, canvas2, canvas3, canvas4,
				canvas5, canvas6 };


		WorkareaCanvas found = CanvasManagment.findCanvas(toBeAdded, canvases);

		Assert.assertNotNull(found);
		Assert.assertEquals("Canvas1", found.getCanvasName());



		found = CanvasManagment.findCanvas(objs[5], canvases);

		Assert.assertNull(found);
	}

	/**
	 * Test method for
	 * {@link managment.CanvasManagment#findWidgetObject(objects.Object, widgets.WorkareaCanvas)}
	 * .
	 */
	@Test
	public void testFindWidgetObjectObjectWorkareaCanvas()
	{
		Object[] objs = ObjectCreationClass.ObjectCreationClass();
		Object toBeAdded = objs[0];


		WorkareaCanvas canvas1 = new WorkareaCanvas("Canvas1");

		// Creates a new WidgetObject that will be added to the scene
		WidgetObject newWidgetObject = new WidgetObject(canvas1.getScene(),
				toBeAdded, null);

		// Add the (possibly modified) object to the given canvas
		WorkareaCanvasActions.addWidgetToCanvas(newWidgetObject,
				new Point(0, 0), canvas1, false);


		WidgetObject found = CanvasManagment.findWidgetObject(toBeAdded,
				canvas1);

		Assert.assertNotNull(found);
		Assert.assertEquals("Desktop", found.getObject().getObjectName());



		found = CanvasManagment.findWidgetObject(objs[6], canvas1);

		Assert.assertNull(found);
	}

	/**
	 * Test method for
	 * {@link managment.CanvasManagment#findWidgetObjectByObjectName(java.lang.String, widgets.WorkareaCanvas)}
	 * .
	 */
	@Test
	public void testFindWidgetObjectByObjectName()
	{
		Object[] objs = ObjectCreationClass.ObjectCreationClass();
		Object toBeAdded = objs[0];


		WorkareaCanvas canvas1 = new WorkareaCanvas("Canvas1");

		// Creates a new WidgetObject that will be added to the scene
		WidgetObject newWidgetObject = new WidgetObject(canvas1.getScene(),
				toBeAdded, null);

		// Add the (possibly modified) object to the given canvas
		WorkareaCanvasActions.addWidgetToCanvas(newWidgetObject,
				new Point(0, 0), canvas1, false);


		WidgetObject found = CanvasManagment.findWidgetObjectByObjectName(
				"Desktop", canvas1);

		Assert.assertNotNull(found);
		Assert.assertEquals("Desktop", found.getObject().getObjectName());



		found = CanvasManagment.findWidgetObjectByObjectName(
				objs[6].getObjectName(), canvas1);

		Assert.assertNull(found);
	}

	/**
	 * Test method for
	 * {@link managment.CanvasManagment#findWidgetObject(objects.Object, widgets.WorkareaCanvas[])}
	 * .
	 */
	@Test
	public void testFindWidgetObjectObjectWorkareaCanvasArray()
	{
		Object[] objs = ObjectCreationClass.ObjectCreationClass();
		Object toBeAdded = objs[0];


		WorkareaCanvas canvas1 = new WorkareaCanvas("Canvas1");

		// Creates a new WidgetObject that will be added to the scene
		WidgetObject newWidgetObject = new WidgetObject(canvas1.getScene(),
				toBeAdded, null);

		// Add the (possibly modified) object to the given canvas
		WorkareaCanvasActions.addWidgetToCanvas(newWidgetObject,
				new Point(0, 0), canvas1, false);


		WidgetObject found = CanvasManagment.findWidgetObject(toBeAdded,
				canvas1);

		Assert.assertNotNull(found);
		Assert.assertEquals("Desktop", found.getObject().getObjectName());



		found = CanvasManagment.findWidgetObject(objs[6], canvas1);

		Assert.assertNull(found);
	}

	/**
	 * Test method for
	 * {@link managment.CanvasManagment#canvasExists(widgets.WorkareaCanvas, widgets.WorkareaCanvas[])}
	 * .
	 */
	@Test
	public void testCanvasExistsWorkareaCanvasWorkareaCanvasArray()
	{
		WorkareaCanvas canvas1 = new WorkareaCanvas("Canvas1");
		WorkareaCanvas canvas2 = new WorkareaCanvas("Canvas2");
		WorkareaCanvas canvas3 = new WorkareaCanvas("Canvas3");
		WorkareaCanvas canvas4 = new WorkareaCanvas("Canvas4");
		WorkareaCanvas canvas5 = new WorkareaCanvas("Canvas5");
		WorkareaCanvas canvas6 = new WorkareaCanvas("Canvas6");

		WorkareaCanvas canvas7 = new WorkareaCanvas("Canvas7");

		WorkareaCanvas[] canvases = { canvas1, canvas2, canvas3, canvas4,
				canvas5, canvas6 };


		boolean found = CanvasManagment.canvasExists(canvas1, canvases);

		Assert.assertTrue(found);



		found = CanvasManagment.canvasExists(canvas7, canvases);

		Assert.assertTrue(!found);
	}

	/**
	 * Test method for
	 * {@link managment.CanvasManagment#canvasExists(java.lang.String, widgets.WorkareaCanvas[])}
	 * .
	 */
	@Test
	public void testCanvasExistsStringWorkareaCanvasArray()
	{
		WorkareaCanvas canvas1 = new WorkareaCanvas("Canvas1");
		WorkareaCanvas canvas2 = new WorkareaCanvas("Canvas2");
		WorkareaCanvas canvas3 = new WorkareaCanvas("Canvas3");
		WorkareaCanvas canvas4 = new WorkareaCanvas("Canvas4");
		WorkareaCanvas canvas5 = new WorkareaCanvas("Canvas5");
		WorkareaCanvas canvas6 = new WorkareaCanvas("Canvas6");

		WorkareaCanvas canvas7 = new WorkareaCanvas("Canvas7");

		WorkareaCanvas[] canvases = { canvas1, canvas2, canvas3, canvas4,
				canvas5, canvas6 };


		boolean found = CanvasManagment.canvasExists(canvas1.getCanvasName(),
				canvases);

		Assert.assertTrue(found);



		found = CanvasManagment.canvasExists(canvas7.getCanvasName(), canvases);

		Assert.assertTrue(!found);
	}

	/**
	 * Test method for
	 * {@link managment.CanvasManagment#canvasesHaveChanged(widgets.WorkareaCanvas[])}
	 * .
	 */
	@Test
	public void testCanvasesHaveChanged()
	{
		WorkareaCanvas canvas1 = new WorkareaCanvas("Canvas1");
		WorkareaCanvas canvas2 = new WorkareaCanvas("Canvas2");
		WorkareaCanvas canvas3 = new WorkareaCanvas("Canvas3");
		WorkareaCanvas canvas4 = new WorkareaCanvas("Canvas4");
		WorkareaCanvas canvas5 = new WorkareaCanvas("Canvas5");
		WorkareaCanvas canvas6 = new WorkareaCanvas("Canvas6");

		WorkareaCanvas[] canvases = { canvas1, canvas2, canvas3, canvas4,
				canvas5, canvas6 };


		WorkareaCanvas[] found = CanvasManagment.canvasesHaveChanged(canvases);

		Assert.assertNull(found);

		canvas1.setChanged(true);
		canvas2.setChanged(true);


		found = CanvasManagment.canvasesHaveChanged(canvases);

		Assert.assertNotNull(found);
		Assert.assertEquals(2, found.length);
	}

	/**
	 * Test method for
	 * {@link managment.CanvasManagment#canvasHasChanged(widgets.WorkareaCanvas)}
	 * .
	 */
	@Test
	public void testCanvasHasChanged()
	{
		WorkareaCanvas canvas1 = new WorkareaCanvas("Canvas1");


		boolean found = CanvasManagment.canvasHasChanged(canvas1);

		Assert.assertTrue(!found);

		canvas1.setChanged(true);


		found = CanvasManagment.canvasHasChanged(canvas1);

		Assert.assertTrue(found);
	}
}
