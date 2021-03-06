package com.pcfutbolmania.pcf2001.view.common;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.apache.commons.lang3.StringUtils;

import com.pcfutbolmania.pcf2001.model.Entity;
import com.pcfutbolmania.pcf2001.model.coach.Coach;

public class SearchResultsCellRenderer implements ListCellRenderer<Entity> {

	protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

	@Override
	public Component getListCellRendererComponent(JList<? extends Entity> list, Entity value, int index,
			boolean isSelected, boolean cellHasFocus) {
		JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected,
				cellHasFocus);
		renderer.setText(value.getName());
		if (StringUtils.isBlank(renderer.getText()) && value instanceof Coach) {
			Coach coach = (Coach) value;
			renderer.setText(coach.getShortName());
		}

		return renderer;
	}

}
