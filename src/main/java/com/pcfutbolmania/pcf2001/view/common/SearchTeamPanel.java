package com.pcfutbolmania.pcf2001.view.common;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.apache.commons.lang3.StringUtils;

import com.pcfutbolmania.pcf2001.model.team.Team;
import com.pcfutbolmania.pcf2001.service.fdi.team.TeamSearchService;

public class SearchTeamPanel extends JPanel {

	private static final long serialVersionUID = -1798801474612013324L;

	private TeamSearchService teamSearchService;
	private Map<Integer, Team> teams;

	private JList<Team> lstTeams;
	private JTextField txtSearchPlayerTeam;
	private JButton btnSearchTeam;
	private JCheckBox chkSearchPlayerNoTeam;
	private JCheckBox chkSearchPlayerUnregistered;

	private boolean isPlayerSearch;

	public SearchTeamPanel(int x, int y, int width, int height, Container contentPane, Map<Integer, Team> teams,
			TeamSearchService teamSearchService, boolean isPlayerSearch) {

		this.teamSearchService = teamSearchService;
		this.teams = teams;

		this.isPlayerSearch = isPlayerSearch;

		this.setBounds(x, y, width, height);
		this.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Equipo", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(this);
		formWindowOpened();
	}

	private void formWindowOpened() {
		JScrollPane pnlSearchPlayerTeamResults = new JScrollPane();
		pnlSearchPlayerTeamResults.setBounds(10, 85, 280, 120);
		pnlSearchPlayerTeamResults.setViewportBorder(null);
		pnlSearchPlayerTeamResults.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pnlSearchPlayerTeamResults.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pnlSearchPlayerTeamResults.setBorder(null);

		lstTeams = new JList<>();
		lstTeams.setBorder(null);
		lstTeams.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstTeams.setCellRenderer(new SearchResultsCellRenderer());
		lstTeams.setToolTipText("Click con el botón derecho para eliminar la selección");
		pnlSearchPlayerTeamResults.setViewportView(lstTeams);
		lstTeams.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lstTeamsMouseClicked(e);
			}
		});

		txtSearchPlayerTeam = new JTextField();
		txtSearchPlayerTeam.setBounds(10, 30, 180, 20);
		txtSearchPlayerTeam.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnSearchTeamActionPerformed();
				}
			}
		});
		txtSearchPlayerTeam.setColumns(10);

		btnSearchTeam = new JButton("Buscar");
		btnSearchTeam.setBounds(200, 30, 85, 20);
		btnSearchTeam.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSearchTeamActionPerformed();
			}
		});

		chkSearchPlayerNoTeam = new JCheckBox("Sin equipo");
		chkSearchPlayerNoTeam.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				chkSearchPlayerNoTeamItemStateChanged(e);
			}
		});
		chkSearchPlayerNoTeam.setBounds(10, 55, 80, 20);

		chkSearchPlayerUnregistered = new JCheckBox("Fuera de plantilla");
		getChkSearchPlayerUnregistered().addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				chkSearchPlayerUnregisteredItemStateChanged(e);
			}
		});
		getChkSearchPlayerUnregistered().setBounds(90, 55, 120, 20);

		this.setLayout(null);
		this.add(pnlSearchPlayerTeamResults);
		this.add(txtSearchPlayerTeam);
		this.add(btnSearchTeam);
		this.add(chkSearchPlayerNoTeam);
		this.add(chkSearchPlayerUnregistered);

		chkSearchPlayerUnregistered.setVisible(isPlayerSearch);
	}

	private void btnSearchTeamActionPerformed() {
		DefaultListModel<Team> model = new DefaultListModel<>();
		List<Team> filteredTeams = teamSearchService.searchTeams(teams, txtSearchPlayerTeam.getText());
		filteredTeams.stream().forEach(resultTeam -> {
			model.addElement(resultTeam);
		});
		lstTeams.setModel(model);
	}

	private void chkSearchPlayerNoTeamItemStateChanged(ItemEvent e) {

		txtSearchPlayerTeam.setEnabled(!chkSearchPlayerNoTeam.isSelected());
		btnSearchTeam.setEnabled(!chkSearchPlayerNoTeam.isSelected());
		lstTeams.setEnabled(!chkSearchPlayerNoTeam.isSelected());
		chkSearchPlayerUnregistered.setEnabled(!chkSearchPlayerNoTeam.isSelected());

		if (chkSearchPlayerNoTeam.isSelected()) {
			txtSearchPlayerTeam.setText(StringUtils.EMPTY);
			lstTeams.setModel(new DefaultListModel<Team>());
		}
	}

	private void chkSearchPlayerUnregisteredItemStateChanged(ItemEvent e) {
		chkSearchPlayerNoTeam.setEnabled(!chkSearchPlayerUnregistered.isSelected());
	}

	private void lstTeamsMouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1 && (e.getButton() == MouseEvent.BUTTON2 || e.getButton() == MouseEvent.BUTTON3)) {
			lstTeams.clearSelection();
		}
	}

	public JList<Team> getLstTeams() {
		return this.lstTeams;
	}

	public JCheckBox getChkSearchPlayerNoTeam() {
		return this.chkSearchPlayerNoTeam;
	}

	public JCheckBox getChkSearchPlayerUnregistered() {
		return chkSearchPlayerUnregistered;
	}

}
