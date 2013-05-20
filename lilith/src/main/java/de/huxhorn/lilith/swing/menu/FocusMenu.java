/*
 * Lilith - a log event viewer.
 * Copyright (C) 2007-2013 Joern Huxhorn
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

import de.huxhorn.lilith.data.eventsource.EventWrapper;
import de.huxhorn.lilith.data.logging.LoggingEvent;
import de.huxhorn.lilith.swing.ViewContainer;
import de.huxhorn.lilith.swing.actions.EventWrapperRelated;
import de.huxhorn.lilith.swing.actions.FocusCallLocationAction;
import de.huxhorn.lilith.swing.actions.FocusFormattedMessageAction;
import de.huxhorn.lilith.swing.actions.FocusMessagePatternAction;
import de.huxhorn.lilith.swing.actions.LoggingFilterBaseAction;
import de.huxhorn.lilith.swing.actions.ViewContainerRelated;

import javax.swing.*;

public class FocusMenu
	extends JMenu
	implements ViewContainerRelated, EventWrapperRelated
{
	private static final long serialVersionUID = 2301518754828320721L;

	private EventWrapper eventWrapper;

	private FocusMessagePatternAction messagePatternAction;
	private FocusFormattedMessageAction formattedMessageAction;
	private FocusCallLocationAction callLocationAction;
	private JMenuItem messagePatternItem;
	private JMenuItem formattedMessageItem;
	private JMenuItem callLocationItem;
	private FocusMDCMenu mdcMenu;
	private FocusMarkerMenu markerMenu;
	private FocusLoggerMenu loggerMenu;

	public FocusMenu() {
		super("Focus...");
		createUI();
		setViewContainer(null);
		setEventWrapper(null);
	}

	private void createUI()
	{
		messagePatternAction = new FocusMessagePatternAction();
		formattedMessageAction=new FocusFormattedMessageAction();
		callLocationAction=new FocusCallLocationAction();
		messagePatternItem = new JMenuItem(messagePatternAction);
		formattedMessageItem = new JMenuItem(formattedMessageAction);
		callLocationItem = new JMenuItem(callLocationAction);
		mdcMenu = new FocusMDCMenu();
		markerMenu = new FocusMarkerMenu();
		loggerMenu = new FocusLoggerMenu();
	}

	public void setEventWrapper(EventWrapper eventWrapper)
	{
		this.eventWrapper = eventWrapper;
		messagePatternAction.setEventWrapper(eventWrapper);
		formattedMessageAction.setEventWrapper(eventWrapper);
		callLocationAction.setEventWrapper(eventWrapper);
		mdcMenu.setEventWrapper(eventWrapper);
		markerMenu.setEventWrapper(eventWrapper);
		loggerMenu.setEventWrapper(eventWrapper);
		updateState();
	}

	public void setViewContainer(ViewContainer viewContainer)
	{
		messagePatternAction.setViewContainer(viewContainer);
		formattedMessageAction.setViewContainer(viewContainer);
		callLocationAction.setViewContainer(viewContainer);
		mdcMenu.setViewContainer(viewContainer);
		markerMenu.setViewContainer(viewContainer);
		loggerMenu.setViewContainer(viewContainer);
		updateState();
	}

	private void updateState()
	{
		EventWrapper wrapper = this.eventWrapper;
		removeAll();

		LoggingEvent loggingEvent = LoggingFilterBaseAction.resolveLoggingEvent(wrapper);
		if(loggingEvent != null)
		{
			add(messagePatternItem);
			add(formattedMessageItem);
			addSeparator();
			add(callLocationItem);
			addSeparator();
			add(mdcMenu);
			add(markerMenu);
			addSeparator();
			add(loggerMenu);
			setEnabled(
					messagePatternItem.isEnabled() ||
					formattedMessageItem.isEnabled() ||
					callLocationItem.isEnabled() ||
					mdcMenu.isEnabled() ||
					markerMenu.isEnabled() ||
					loggerMenu.isEnabled()
				);
			return;
		}
		setEnabled(false);
	}
}