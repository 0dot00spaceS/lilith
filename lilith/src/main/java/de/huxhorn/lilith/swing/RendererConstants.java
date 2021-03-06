/*
 * Lilith - a log event viewer.
 * Copyright (C) 2007-2011 Joern Huxhorn
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.huxhorn.lilith.swing;

import de.huxhorn.sulky.io.IOUtilities;
import java.awt.Font;
import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RendererConstants
{
	public static final float SMOOTHING_THRESHOLD = 6.0f;
	public static final String MONOSPACED_FAMILY = "Monospaced";
	private static final String MENSCH_FONT_RESOURCE = "/mensch.ttf";

	public static final Font MENSCH_FONT;

	static
	{
		final Logger logger = LoggerFactory.getLogger(RendererConstants.class);

		InputStream fontStream=RendererConstants.class.getResourceAsStream(MENSCH_FONT_RESOURCE);
		Font font=null;
		if(fontStream != null)
		{
			try
			{
				font=Font.createFont(Font.TRUETYPE_FONT, fontStream);
				if(logger.isInfoEnabled()) logger.info("Created {} font.", MENSCH_FONT_RESOURCE);
				IOUtilities.closeQuietly(fontStream);

			}
			catch(Exception ex)
			{
				if(logger.isWarnEnabled()) logger.warn("Exception while creating font!", ex);
			}
		}
		else
		{
			if(logger.isWarnEnabled()) logger.warn("Could not find resource {}!", MENSCH_FONT_RESOURCE);
		}
		MENSCH_FONT = font;
	}
}
