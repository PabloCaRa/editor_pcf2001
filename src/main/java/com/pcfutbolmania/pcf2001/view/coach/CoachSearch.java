package com.pcfutbolmania.pcf2001.view.coach;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

import com.pcfutbolmania.pcf2001.model.coach.Coach;
import com.pcfutbolmania.pcf2001.model.search.CoachFilter;
import com.pcfutbolmania.pcf2001.model.team.Team;
import com.pcfutbolmania.pcf2001.service.fdi.coach.CoachSearchService;
import com.pcfutbolmania.pcf2001.service.fdi.team.TeamSearchService;
import com.pcfutbolmania.pcf2001.view.common.SearchTeamPanel;

public class CoachSearch extends JDialog {

	private static final long serialVersionUID = -7853642051786357420L;

	private TeamSearchService teamSearchService;
	private CoachSearchService coachSearchService;

	private Map<Integer, Coach> coaches;

	private JTextField txtSearchCoachName;
	private JList<Coach> lstSearchCoachResults;

	private SearchTeamPanel pnlSearchTeamPanel;

	/**
	 * Create the dialog.
	 */
	public CoachSearch(Map<Integer, Coach> coaches, Map<Integer, Team> teams) {

		teamSearchService = new TeamSearchService();
		coachSearchService = new CoachSearchService();

		this.coaches = coaches;

		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setResizable(false);
		setTitle("Buscar entrenadores");
		setBounds(400, 400, 645, 340);
		getContentPane().setLayout(null);

		JPanel pnlSearchCoachName = new JPanel();
		pnlSearchCoachName
				.setBorder(new TitledBorder(null, "Nombre", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlSearchCoachName.setBounds(10, 10, 390, 75);
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
		txtSearchCoachName.setBounds(20, 30, 355, 20);
		pnlSearchCoachName.add(txtSearchCoachName);
		txtSearchCoachName.setColumns(10);

		JPanel pnlSearchCoachActions = new JPanel();
		pnlSearchCoachActions.setLayout(null);
		pnlSearchCoachActions
				.setBorder(new TitledBorder(null, "Acciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlSearchCoachActions.setBounds(400, 10, 230, 75);
		getContentPane().add(pnlSearchCoachActions);

		JButton btnSearchCoachSearch = new JButton("Buscar");
		btnSearchCoachSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSearchCoachSearchActionPerformed();
			}

		});
		btnSearchCoachSearch.setBounds(20, 30, 85, 25);
		pnlSearchCoachActions.add(btnSearchCoachSearch);

		JButton btnSearchCoachBack = new JButton("Volver");
		btnSearchCoachBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSearchCoachBackActionPerformed();
			}
		});
		btnSearchCoachBack.setBounds(125, 30, 85, 25);
		pnlSearchCoachActions.add(btnSearchCoachBack);

		JPanel pnlSearchCoachResults = new JPanel();
		pnlSearchCoachResults
				.setBorder(new TitledBorder(null, "Resultados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlSearchCoachResults.setBounds(320, 85, 310, 220);
		getContentPane().add(pnlSearchCoachResults);
		pnlSearchCoachResults.setLayout(null);

		JScrollPane scpSearchCoachResults = new JScrollPane();
		scpSearchCoachResults.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scpSearchCoachResults.setBounds(10, 15, 290, 195);
		pnlSearchCoachResults.add(scpSearchCoachResults);

		lstSearchCoachResults = new JList<>();
		lstSearchCoachResults.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstSearchCoachResults.setBounds(0, 0, 1, 1);
		lstSearchCoachResults.setCellRenderer(new CoachCellRenderer());
		scpSearchCoachResults.setViewportView(lstSearchCoachResults);

		pnlSearchTeamPanel = new SearchTeamPanel(10, 85, 310, 220, getContentPane(), teams, teamSearchService);
		getContentPane().add(pnlSearchTeamPanel);
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
	}
}
