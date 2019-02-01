package com.pcfutbolmania.pcf2001.view.stadium;

import java.awt.Color;
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

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.apache.commons.lang3.StringUtils;

import com.pcfutbolmania.pcf2001.model.pak.Country;
import com.pcfutbolmania.pcf2001.model.search.StadiumFilter;
import com.pcfutbolmania.pcf2001.model.stadium.Stadium;
import com.pcfutbolmania.pcf2001.model.team.Team;
import com.pcfutbolmania.pcf2001.service.fdi.stadium.StadiumSearchService;
import com.pcfutbolmania.pcf2001.service.fdi.team.TeamSearchService;
import com.pcfutbolmania.pcf2001.service.pak.CountryService;
import com.pcfutbolmania.pcf2001.view.common.SearchResultsCellRenderer;
import com.pcfutbolmania.pcf2001.view.common.SearchTeamPanel;

public class StadiumSearch extends JDialog {

	private static final long serialVersionUID = -6726605397277576365L;

	private StadiumSearchService stadiumSearchService;
	private TeamSearchService teamSearchService;
	private CountryService countryService;

	private Map<Integer, Stadium> stadiums;
	private Map<Integer, Country> countries;
	private Map<Integer, Team> teams;

	private JTextField txtSearchStadiumName;

	private JComboBox<String> cbCountries;

	private JSpinner spnSearchStadiumWidthMin;

	private JSpinner spnSearchStadiumWidthMax;

	private JSpinner spnSearchStadiumLengthMin;

	private JSpinner spnSearchStadiumLengthMax;

	private JSpinner spnSearchStadiumConstructionYearMin;

	private JSpinner spnSearchStadiumConstructionYearMax;

	private JSpinner spnSearchStadiumSittingCapacityMin;

	private JSpinner spnSearchStadiumSittingCapacityMax;

	private JSpinner spnSearchStadiumStandingCapacityMin;

	private JSpinner spnSearchStadiumStandingCapacityMax;

	private SearchTeamPanel pnlSearchTeamPanel;

	private JList<Stadium> lstSearchStadiumResults;

	/**
	 * Create the dialog.
	 */
	public StadiumSearch(Map<Integer, Stadium> stadiums, Map<Integer, Team> teams, Map<Integer, Country> countries) {
		setModalityType(ModalityType.APPLICATION_MODAL);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				formWindowOpened();
			}
		});
		setTitle("Buscar estadios");

		stadiumSearchService = new StadiumSearchService();
		teamSearchService = new TeamSearchService();
		countryService = new CountryService();

		this.stadiums = stadiums;
		this.countries = countries;
		this.teams = teams;

		setResizable(false);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setBounds(400, 400, 725, 435);
		getContentPane().setLayout(null);

		JPanel pnlSearchStadiumName = new JPanel();
		pnlSearchStadiumName
				.setBorder(new TitledBorder(null, "Nombre", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlSearchStadiumName.setBounds(10, 10, 200, 65);
		getContentPane().add(pnlSearchStadiumName);
		pnlSearchStadiumName.setLayout(null);

		txtSearchStadiumName = new JTextField();
		txtSearchStadiumName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnSearchStadiumSearchActionPerformed();
				}
			}
		});
		txtSearchStadiumName.setBounds(15, 25, 170, 20);
		pnlSearchStadiumName.add(txtSearchStadiumName);
		txtSearchStadiumName.setColumns(10);

		JPanel pnlSearchStadiumCountry = new JPanel();
		pnlSearchStadiumCountry.setLayout(null);
		pnlSearchStadiumCountry.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pa\u00EDs",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSearchStadiumCountry.setBounds(10, 75, 200, 65);
		getContentPane().add(pnlSearchStadiumCountry);

		cbCountries = new JComboBox<>();
		cbCountries.setBounds(15, 25, 170, 20);
		pnlSearchStadiumCountry.add(cbCountries);

		JPanel pnlSearchStadiumWidth = new JPanel();
		pnlSearchStadiumWidth.setLayout(null);
		pnlSearchStadiumWidth.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ancho",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSearchStadiumWidth.setBounds(410, 10, 100, 130);
		getContentPane().add(pnlSearchStadiumWidth);

		spnSearchStadiumWidthMin = new JSpinner();
		spnSearchStadiumWidthMin.setModel(new SpinnerNumberModel(0, 0, 255, 1));
		spnSearchStadiumWidthMin.setBounds(25, 35, 50, 20);
		pnlSearchStadiumWidth.add(spnSearchStadiumWidthMin);

		spnSearchStadiumWidthMax = new JSpinner();
		spnSearchStadiumWidthMax.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spnSearchStadiumWidthMax.setBounds(25, 90, 50, 20);
		pnlSearchStadiumWidth.add(spnSearchStadiumWidthMax);

		JLabel lblSearchStadiumWidtthMin = new JLabel("Mínimo");
		lblSearchStadiumWidtthMin.setLabelFor(spnSearchStadiumWidthMin);
		lblSearchStadiumWidtthMin.setBounds(25, 20, 50, 15);
		pnlSearchStadiumWidth.add(lblSearchStadiumWidtthMin);

		JLabel lblSearchStadiumWidtthMax = new JLabel("Máximo");
		lblSearchStadiumWidtthMax.setLabelFor(lblSearchStadiumWidtthMax);
		lblSearchStadiumWidtthMax.setBounds(25, 75, 50, 15);
		pnlSearchStadiumWidth.add(lblSearchStadiumWidtthMax);

		JPanel pnlSearchStadiumLength = new JPanel();
		pnlSearchStadiumLength.setLayout(null);
		pnlSearchStadiumLength.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Largo",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSearchStadiumLength.setBounds(310, 10, 100, 130);
		getContentPane().add(pnlSearchStadiumLength);

		spnSearchStadiumLengthMin = new JSpinner();
		spnSearchStadiumLengthMin.setModel(new SpinnerNumberModel(0, 0, 255, 1));
		spnSearchStadiumLengthMin.setBounds(25, 35, 50, 20);
		pnlSearchStadiumLength.add(spnSearchStadiumLengthMin);

		spnSearchStadiumLengthMax = new JSpinner();
		spnSearchStadiumLengthMax.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spnSearchStadiumLengthMax.setBounds(25, 90, 50, 20);
		pnlSearchStadiumLength.add(spnSearchStadiumLengthMax);

		JLabel lblSearchStadiumLengthMin = new JLabel("Mínimo");
		lblSearchStadiumLengthMin.setLabelFor(spnSearchStadiumLengthMin);
		lblSearchStadiumLengthMin.setBounds(25, 20, 50, 15);
		pnlSearchStadiumLength.add(lblSearchStadiumLengthMin);

		JLabel lblSearchStadiumLengthMax = new JLabel("Máximo");
		lblSearchStadiumLengthMax.setLabelFor(spnSearchStadiumLengthMax);
		lblSearchStadiumLengthMax.setBounds(25, 75, 50, 15);
		pnlSearchStadiumLength.add(lblSearchStadiumLengthMax);

		JPanel pnlSearchStadiumConstructionYear = new JPanel();
		pnlSearchStadiumConstructionYear.setLayout(null);
		pnlSearchStadiumConstructionYear.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"A\u00F1o construcci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSearchStadiumConstructionYear.setBounds(210, 10, 100, 130);
		getContentPane().add(pnlSearchStadiumConstructionYear);

		spnSearchStadiumConstructionYearMin = new JSpinner();
		spnSearchStadiumConstructionYearMin.setModel(new SpinnerNumberModel(0, 0, 3000, 1));
		spnSearchStadiumConstructionYearMin.setBounds(25, 35, 50, 20);
		pnlSearchStadiumConstructionYear.add(spnSearchStadiumConstructionYearMin);

		spnSearchStadiumConstructionYearMax = new JSpinner();
		spnSearchStadiumConstructionYearMax.setModel(new SpinnerNumberModel(3000, 0, 3000, 1));
		spnSearchStadiumConstructionYearMax.setBounds(25, 90, 50, 20);
		pnlSearchStadiumConstructionYear.add(spnSearchStadiumConstructionYearMax);

		JLabel lblSearchStadiumConstructionYearMin = new JLabel("Mínimo");
		lblSearchStadiumConstructionYearMin.setLabelFor(spnSearchStadiumConstructionYearMin);
		lblSearchStadiumConstructionYearMin.setBounds(25, 20, 50, 15);
		pnlSearchStadiumConstructionYear.add(lblSearchStadiumConstructionYearMin);

		JLabel lblSearchStadiumConstructionYearMax = new JLabel("Máximo");
		lblSearchStadiumConstructionYearMax.setLabelFor(spnSearchStadiumConstructionYearMax);
		lblSearchStadiumConstructionYearMax.setBounds(25, 75, 50, 15);
		pnlSearchStadiumConstructionYear.add(lblSearchStadiumConstructionYearMax);

		JPanel pnlSearchStadiumSittingCapacity = new JPanel();
		pnlSearchStadiumSittingCapacity.setLayout(null);
		pnlSearchStadiumSittingCapacity.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"P\u00FAblico sentado", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSearchStadiumSittingCapacity.setBounds(610, 10, 100, 130);
		getContentPane().add(pnlSearchStadiumSittingCapacity);

		spnSearchStadiumSittingCapacityMin = new JSpinner();
		spnSearchStadiumSittingCapacityMin.setModel(new SpinnerNumberModel(0, 0, 250000, 1));
		spnSearchStadiumSittingCapacityMin.setBounds(15, 35, 70, 20);
		pnlSearchStadiumSittingCapacity.add(spnSearchStadiumSittingCapacityMin);

		spnSearchStadiumSittingCapacityMax = new JSpinner();
		spnSearchStadiumSittingCapacityMax.setModel(new SpinnerNumberModel(250000, 0, 250000, 1));
		spnSearchStadiumSittingCapacityMax.setBounds(15, 90, 70, 20);
		pnlSearchStadiumSittingCapacity.add(spnSearchStadiumSittingCapacityMax);

		JLabel lblSearchStadiumSittingCapacityMin = new JLabel("Mínimo");
		lblSearchStadiumSittingCapacityMin.setLabelFor(spnSearchStadiumSittingCapacityMin);
		lblSearchStadiumSittingCapacityMin.setBounds(15, 20, 50, 15);
		pnlSearchStadiumSittingCapacity.add(lblSearchStadiumSittingCapacityMin);

		JLabel lblSearchStadiumSittingCapacityMax = new JLabel("Máximo");
		lblSearchStadiumSittingCapacityMax.setLabelFor(spnSearchStadiumSittingCapacityMax);
		lblSearchStadiumSittingCapacityMax.setBounds(15, 75, 45, 15);
		pnlSearchStadiumSittingCapacity.add(lblSearchStadiumSittingCapacityMax);

		JPanel pnlSearchStadiumStandingCapacity = new JPanel();
		pnlSearchStadiumStandingCapacity.setLayout(null);
		pnlSearchStadiumStandingCapacity.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"P\u00FAblico de pie", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSearchStadiumStandingCapacity.setBounds(510, 10, 100, 130);
		getContentPane().add(pnlSearchStadiumStandingCapacity);

		spnSearchStadiumStandingCapacityMin = new JSpinner();
		spnSearchStadiumStandingCapacityMin.setModel(new SpinnerNumberModel(0, 0, 250000, 1));
		spnSearchStadiumStandingCapacityMin.setBounds(15, 35, 70, 20);
		pnlSearchStadiumStandingCapacity.add(spnSearchStadiumStandingCapacityMin);

		spnSearchStadiumStandingCapacityMax = new JSpinner();
		spnSearchStadiumStandingCapacityMax.setModel(new SpinnerNumberModel(250000, 0, 250000, 1));
		spnSearchStadiumStandingCapacityMax.setBounds(15, 90, 70, 20);
		pnlSearchStadiumStandingCapacity.add(spnSearchStadiumStandingCapacityMax);

		JLabel lblSearchStadiumStandingCapacityMin = new JLabel("Mínimo");
		lblSearchStadiumStandingCapacityMin.setLabelFor(spnSearchStadiumStandingCapacityMin);
		lblSearchStadiumStandingCapacityMin.setBounds(15, 20, 50, 15);
		pnlSearchStadiumStandingCapacity.add(lblSearchStadiumStandingCapacityMin);

		JLabel lblSearchStadiumStandingCapacityMax = new JLabel("Máximo");
		lblSearchStadiumStandingCapacityMax.setLabelFor(spnSearchStadiumStandingCapacityMax);
		lblSearchStadiumStandingCapacityMax.setBounds(15, 75, 50, 15);
		pnlSearchStadiumStandingCapacity.add(lblSearchStadiumStandingCapacityMax);

		pnlSearchTeamPanel = new SearchTeamPanel(10, 140, 300, 220, getContentPane(), teams, teamSearchService);
		getContentPane().add(pnlSearchTeamPanel);

		JPanel pnlSearchStadiumResults = new JPanel();
		pnlSearchStadiumResults
				.setBorder(new TitledBorder(null, "Resultados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlSearchStadiumResults.setBounds(310, 140, 400, 220);
		getContentPane().add(pnlSearchStadiumResults);
		pnlSearchStadiumResults.setLayout(null);

		JScrollPane scpSearchStadiumResults = new JScrollPane();
		scpSearchStadiumResults.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scpSearchStadiumResults.setBounds(10, 15, 380, 195);
		pnlSearchStadiumResults.add(scpSearchStadiumResults);

		lstSearchStadiumResults = new JList<>();
		lstSearchStadiumResults.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lstSearchStadiumResultsMouseClicked(e);
			}
		});
		lstSearchStadiumResults.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstSearchStadiumResults.setCellRenderer(new SearchResultsCellRenderer());
		scpSearchStadiumResults.setViewportView(lstSearchStadiumResults);

		JButton btnSearchStadiumSearch = new JButton("Buscar");
		btnSearchStadiumSearch.setBounds(530, 370, 80, 25);
		getContentPane().add(btnSearchStadiumSearch);

		JButton btnSearchStadiumBack = new JButton("Volver");
		btnSearchStadiumBack.setBounds(630, 370, 80, 25);
		getContentPane().add(btnSearchStadiumBack);
		btnSearchStadiumBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSearchStadiumBackActionPerformed();
			}
		});
		btnSearchStadiumSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSearchStadiumSearchActionPerformed();
			}
		});
	}

	private void formWindowOpened() {
		List<String> listCountries = countryService.loadCountryNames(countries);
		listCountries.add(0, StringUtils.EMPTY);

		cbCountries.setModel(new DefaultComboBoxModel<>(listCountries.toArray(new String[listCountries.size()])));

		btnSearchStadiumSearchActionPerformed();
	}

	private void btnSearchStadiumBackActionPerformed() {
		this.dispose();
	}

	private void btnSearchStadiumSearchActionPerformed() {

		StadiumFilter filter = new StadiumFilter();

		filter.setName(txtSearchStadiumName.getText());

		if (cbCountries.getSelectedIndex() > 0) {
			filter.setCountry(cbCountries.getSelectedIndex() - 1);
		}

		filter.setConstructionYearMin((int) spnSearchStadiumConstructionYearMin.getValue());
		filter.setConstructionYearMax((int) spnSearchStadiumConstructionYearMax.getValue());

		filter.setWidthMin((int) spnSearchStadiumWidthMin.getValue());
		filter.setWidthMax((int) spnSearchStadiumWidthMax.getValue());

		filter.setLengthMin((int) spnSearchStadiumLengthMin.getValue());
		filter.setLengthMax((int) spnSearchStadiumLengthMax.getValue());

		filter.setSittingCapacityMin((int) spnSearchStadiumSittingCapacityMin.getValue());
		filter.setSittingCapacityMax((int) spnSearchStadiumSittingCapacityMax.getValue());

		filter.setStandingCapacityMin((int) spnSearchStadiumStandingCapacityMin.getValue());
		filter.setStandingCapacityMax((int) spnSearchStadiumStandingCapacityMax.getValue());

		filter.setFree(pnlSearchTeamPanel.getChkSearchPlayerNoTeam().isSelected());
		if (!pnlSearchTeamPanel.getLstTeams().isSelectionEmpty()) {
			filter.setTeamId(pnlSearchTeamPanel.getLstTeams().getSelectedValue().getHeader().getId());
		}

		List<Stadium> filteredStadiums = stadiumSearchService.searchStadiums(filter, stadiums);

		DefaultListModel<Stadium> model = new DefaultListModel<>();
		filteredStadiums.stream().forEach(stadium -> {
			model.addElement(stadium);
		});
		lstSearchStadiumResults.setModel(model);

	}

	private void lstSearchStadiumResultsMouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1
				&& !lstSearchStadiumResults.isSelectionEmpty()) {
			StadiumInfo stadiumInfo = new StadiumInfo(lstSearchStadiumResults.getSelectedValue(), stadiums, countries,
					teams);
			stadiumInfo.setLocationRelativeTo(null);
			stadiumInfo.setVisible(true);
		}
	}
}
