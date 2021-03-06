/*
 * Lilith - a log event viewer.
 * Copyright (C) 2007-2016 Joern Huxhorn
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
package de.huxhorn.lilith.swing.menu;

import de.huxhorn.lilith.data.access.AccessEvent;
import de.huxhorn.lilith.data.eventsource.EventWrapper;
import de.huxhorn.lilith.swing.actions.BasicFilterAction;
import de.huxhorn.lilith.swing.actions.FocusHttpResponseHeaderAction;
import java.util.Map;

import static de.huxhorn.lilith.swing.actions.AbstractAccessFilterAction.resolveAccessEvent;

class FocusResponseHeaderMenu
		extends AbstractStringStringMapMenu
{
	private static final long serialVersionUID = 863294375257690650L;

	FocusResponseHeaderMenu()
	{
		super("Response Header");
	}

	protected BasicFilterAction createAction(String key, String value)
	{
		return new FocusHttpResponseHeaderAction(key, value);
	}

	@Override
	public void setEventWrapper(EventWrapper eventWrapper)
	{
		AccessEvent accessEvent = resolveAccessEvent(eventWrapper);
		Map<String, String> newMap = null;
		if (accessEvent != null)
		{
			newMap = accessEvent.getResponseHeaders();
		}
		setMap(newMap);
	}
}
