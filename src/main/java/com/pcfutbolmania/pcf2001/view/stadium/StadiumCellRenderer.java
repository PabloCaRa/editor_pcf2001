package com.pcfutbolmania.pcf2001.view.stadium;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.pcfutbolmania.pcf2001.model.stadium.Stadium;

public class StadiumCellRenderer implements ListCellRenderer<Stadium> {

	protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

	@Override
	public Component getListCellRendererComponent(JList<? extends Stadium> list, Stadium value, int index,
			boolean isSelected, boolean cellHasFocus) {
		JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected,
				cellHasFocus);
		renderer.setText(value.getName());
		return renderer;
	}

}
