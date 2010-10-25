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
package graphics.GUI.statusArea;


import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.JXStatusBar;


/**
 * Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class PrimeStatusBar extends JXStatusBar
{

	public PrimeStatusBar()
	{

		JLabel statusLabel = new JLabel("Ready");
		JXStatusBar.Constraint c1 = new JXStatusBar.Constraint();
		c1.setFixedWidth(100);
		this.add(statusLabel, c1);

		this.add(new JSeparator(SwingConstants.VERTICAL));

		// FIXME - DO something with the JProgressBar
		JXStatusBar.Constraint c2 = new JXStatusBar.Constraint(
				JXStatusBar.Constraint.ResizeBehavior.FILL);
		JProgressBar pbar = new JProgressBar();

		this.add(pbar, c2);
	}
}
