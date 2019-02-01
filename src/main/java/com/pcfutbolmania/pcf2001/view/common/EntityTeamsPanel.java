package com.pcfutbolmania.pcf2001.view.common;

import java.awt.Color;
import java.awt.Container;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.pcfutbolmania.pcf2001.model.team.Team;

public class EntityTeamsPanel extends JPanel {

	private static final long serialVersionUID = -1798801474612013324L;

	private Map<Integer, Team> teams;
	private List<Integer> teamIds;

	public EntityTeamsPanel(int x, int y, int width, int height, Container contentPane, Map<Integer, Team> teams,
			List<Integer> teamIds) {

		this.teams = teams;
		this.teamIds = teamIds;

		this.setBounds(x, y, width, height);
		this.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Equipos", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(this);
		formWindowOpened();
	}

	private void formWindowOpened() {

		JScrollPane scpStadiumTeams = new JScrollPane();
		scpStadiumTeams.setBounds(10, 20, this.getBounds().width - 20, this.getBounds().height - 30);
		scpStadiumTeams.setViewportBorder(null);
		scpStadiumTeams.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scpStadiumTeams.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scpStadiumTeams.setBorder(null);

		JList<Team> lstStadiumTeams = new JList<>();
		lstStadiumTeams.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstStadiumTeams.setCellRenderer(new SearchResultsCellRenderer());
		scpStadiumTeams.setViewportView(lstStadiumTeams);

		this.setLayout(null);
		this.add(scpStadiumTeams);

		DefaultListModel<Team> model = new DefaultListModel<>();
		teamIds.stream().forEach(teamId -> {
			model.addElement(teams.get(teamId));
		});
		lstStadiumTeams.setModel(model);
	}

}
