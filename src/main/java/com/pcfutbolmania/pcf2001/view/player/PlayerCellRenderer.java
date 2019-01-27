package com.pcfutbolmania.pcf2001.view.player;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.pcfutbolmania.pcf2001.model.player.Player;

public class PlayerCellRenderer implements ListCellRenderer<Player> {

	protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

	@Override
	public Component getListCellRendererComponent(JList<? extends Player> list, Player value, int index,
			boolean isSelected, boolean cellHasFocus) {
		JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected,
				cellHasFocus);
		renderer.setText(value.getName());
		return renderer;
	}

}
