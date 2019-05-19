package com.pcfutbolmania.pcf2001.view.coach;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import org.apache.commons.lang3.StringUtils;

import com.pcfutbolmania.pcf2001.model.coach.Coach;
import com.pcfutbolmania.pcf2001.model.search.CoachFilter;
import com.pcfutbolmania.pcf2001.model.team.Team;
import com.pcfutbolmania.pcf2001.service.fdi.coach.CoachSearchService;
import com.pcfutbolmania.pcf2001.service.fdi.team.TeamSearchService;
import com.pcfutbolmania.pcf2001.view.common.SearchResultsCellRenderer;
import com.pcfutbolmania.pcf2001.view.common.SearchTeamPanel;

public class CoachSearch extends JDialog {

	private static final long serialVersionUID = -7853642051786357420L;

	private TeamSearchService teamSearchService;
	private CoachSearchService coachSearchService;

	private Map<Integer, Coach> coaches;
	private Map<Integer, Team> teams;

	private JTextField txtSearchCoachName;
	private JList<Coach> lstSearchCoachResults;

	private SearchTeamPanel pnlSearchTeamPanel;

	private JPanel pnlSearchCoachResults;

	/**
	 * Create the dialog.
	 */
	public CoachSearch(Map<Integer, Coach> coaches, Map<Integer, Team> teams) {

		teamSearchService = new TeamSearchService();
		coachSearchService = new CoachSearchService();

		this.coaches = coaches;
		this.teams = teams;

		setModalityType(ModalityType.APPLICATION_MODAL);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				formWindowOpened();
			}
		});

		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setResizable(false);
		setTitle("Buscar entrenadores");
		setBounds(400, 400, 625, 370);
		getContentPane().setLayout(null);

		JPanel pnlSearchCoachName = new JPanel();
		pnlSearchCoachName
				.setBorder(new TitledBorder(null, "Nombre", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlSearchCoachName.setBounds(10, 10, 300, 65);
		getContentPane().add(pnlSearchCoachName);
		pnlSearchCoachName.setLayout(null);

		txtSearchCoachName = new JTextField();
		txtSearchCoachName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnSearchCoachSearchActionPerformed();
				}
			}
		});
		txtSearchCoachName.setBounds(15, 25, 270, 20);
		pnlSearchCoachName.add(txtSearchCoachName);
		txtSearchCoachName.setColumns(10);

		pnlSearchCoachResults = new JPanel();
		pnlSearchCoachResults
				.setBorder(new TitledBorder(null, "Resultados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlSearchCoachResults.setBounds(310, 10, 300, 285);
		getContentPane().add(pnlSearchCoachResults);
		pnlSearchCoachResults.setLayout(null);

		JScrollPane scpSearchCoachResults = new JScrollPane();
		scpSearchCoachResults.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scpSearchCoachResults.setBounds(10, 15, 280, 260);
		pnlSearchCoachResults.add(scpSearchCoachResults);

		lstSearchCoachResults = new JList<>();
		lstSearchCoachResults.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lstSearchCoachResultsMouseClicked(e);
			}
		});
		lstSearchCoachResults.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstSearchCoachResults.setBounds(0, 0, 1, 1);
		lstSearchCoachResults.setCellRenderer(new SearchResultsCellRenderer());
		scpSearchCoachResults.setViewportView(lstSearchCoachResults);

		pnlSearchTeamPanel = new SearchTeamPanel(10, 75, 300, 220, getContentPane(), teams, teamSearchService, false);
		getContentPane().add(pnlSearchTeamPanel);

		JButton btnSearchCoachSearch = new JButton("Buscar");
		btnSearchCoachSearch.setBounds(430, 305, 80, 25);
		getContentPane().add(btnSearchCoachSearch);

		JButton btnSearchCoachBack = new JButton("Volver");
		btnSearchCoachBack.setBounds(530, 305, 80, 25);
		getContentPane().add(btnSearchCoachBack);
		btnSearchCoachBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSearchCoachBackActionPerformed();
			}
		});
		btnSearchCoachSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSearchCoachSearchActionPerformed();
			}

		});
	}

	private void formWindowOpened() {
		btnSearchCoachSearchActionPerformed();
	}

	private void btnSearchCoachBackActionPerformed() {
		this.dispose();
	}

	private void btnSearchCoachSearchActionPerformed() {

		CoachFilter filter = new CoachFilter();

		filter.setName(txtSearchCoachName.getText());
		filter.setFree(pnlSearchTeamPanel.getChkSearchPlayerNoTeam().isSelected());
		if (!pnlSearchTeamPanel.getLstTeams().isSelectionEmpty()) {
			filter.setTeamId(pnlSearchTeamPanel.getLstTeams().getSelectedValue().getHeader().getId());
		}

		List<Coach> filteredCoaches = coachSearchService.searchCoaches(filter, coaches);

		DefaultListModel<Coach> model = new DefaultListModel<>();
		filteredCoaches.stream().forEach(filteredCoach -> {
			model.addElement(filteredCoach);
		});
		lstSearchCoachResults.setModel(model);

		StringBuilder sb = new StringBuilder();
		sb.append(String.valueOf(filteredCoaches.size()));
		sb.append(StringUtils.SPACE);
		sb.append(filteredCoaches.size() == 1 ? "entrenador encontrado" : "entrenadores encontrados");
		pnlSearchCoachResults
				.setBorder(new TitledBorder(null, sb.toString(), TitledBorder.LEADING, TitledBorder.TOP, null, null));
	}

	private void lstSearchCoachResultsMouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
			CoachInfo coachInfo = new CoachInfo(lstSearchCoachResults.getSelectedValue(), coaches, teams, false);
			coachInfo.setLocationRelativeTo(null);
			coachInfo.setVisible(true);
		}
	}
}
