package com.pcfutbolmania.pcf2001.view.team;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import com.pcfutbolmania.pcf2001.helper.CurrencyConverterHelper;
import com.pcfutbolmania.pcf2001.model.pak.Country;
import com.pcfutbolmania.pcf2001.model.search.TeamFilter;
import com.pcfutbolmania.pcf2001.model.team.Team;
import com.pcfutbolmania.pcf2001.service.fdi.team.TeamSearchService;
import com.pcfutbolmania.pcf2001.service.pak.CountryService;

public class TeamSearch extends JDialog {

	private static final long serialVersionUID = 1164736268890967516L;

	private TeamSearchService teamSearchService;
	private CountryService countryService;

	private Map<Integer, Team> teams;
	private Map<Integer, Country> countries;

	private JTextField txtTeamSearchName;
	private JList<Team> lstSearchStadiumResults;

	private JComboBox<String> cbTeamSearchCountry;

	private JSpinner spnTeamSearchFoundationYearMin;

	private JSpinner spnTeamSearchFoundationYearMax;

	private JSpinner spnTeamSearchSupportersMin;

	private JSpinner spnTeamSearchSupportersMax;

	private JSpinner spnTeamSearchBudgetMin;

	private JSpinner spnTeamSearchBudgetMax;

	/**
	 * Create the dialog.
	 */
	public TeamSearch(Map<Integer, Team> teams, Map<Integer, Country> countries) {
		setModalityType(ModalityType.APPLICATION_MODAL);

		teamSearchService = new TeamSearchService();
		countryService = new CountryService();

		this.teams = teams;
		this.countries = countries;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				formWindowOpened();
			}
		});
		setResizable(false);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setTitle("Buscar equipos");
		setBounds(100, 100, 525, 435);
		getContentPane().setLayout(null);

		JPanel pnlTeamSearchName = new JPanel();
		pnlTeamSearchName.setBounds(10, 10, 200, 65);
		pnlTeamSearchName
				.setBorder(new TitledBorder(null, "Nombre", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		txtTeamSearchName = new JTextField();
		txtTeamSearchName.setBounds(15, 25, 170, 20);
		txtTeamSearchName.setToolTipText("Nombre corto");
		txtTeamSearchName.setColumns(10);
		pnlTeamSearchName.setLayout(null);
		pnlTeamSearchName.add(txtTeamSearchName);
		getContentPane().add(pnlTeamSearchName);

		JPanel pnlTeamSearchCountry = new JPanel();
		pnlTeamSearchCountry.setLayout(null);
		pnlTeamSearchCountry.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pa\u00EDs",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlTeamSearchCountry.setBounds(10, 75, 200, 65);
		getContentPane().add(pnlTeamSearchCountry);

		cbTeamSearchCountry = new JComboBox<>();
		cbTeamSearchCountry.setBounds(15, 25, 170, 20);
		pnlTeamSearchCountry.add(cbTeamSearchCountry);

		JPanel pnlTeamSearchFoundationYear = new JPanel();
		pnlTeamSearchFoundationYear.setLayout(null);
		pnlTeamSearchFoundationYear.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"A\u00F1o fundaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlTeamSearchFoundationYear.setBounds(210, 10, 100, 130);
		getContentPane().add(pnlTeamSearchFoundationYear);

		spnTeamSearchFoundationYearMin = new JSpinner();
		spnTeamSearchFoundationYearMin.setModel(new SpinnerNumberModel(0, 0, 3000, 1));
		spnTeamSearchFoundationYearMin.setBounds(25, 35, 50, 20);
		pnlTeamSearchFoundationYear.add(spnTeamSearchFoundationYearMin);

		spnTeamSearchFoundationYearMax = new JSpinner();
		spnTeamSearchFoundationYearMax.setModel(new SpinnerNumberModel(3000, 0, 3000, 1));
		spnTeamSearchFoundationYearMax.setBounds(25, 90, 50, 20);
		pnlTeamSearchFoundationYear.add(spnTeamSearchFoundationYearMax);

		JLabel lblTeamSearchFoundationYearMin = new JLabel("Mínimo");
		lblTeamSearchFoundationYearMin.setLabelFor(spnTeamSearchFoundationYearMin);
		lblTeamSearchFoundationYearMin.setBounds(25, 20, 50, 15);
		pnlTeamSearchFoundationYear.add(lblTeamSearchFoundationYearMin);

		JLabel lblTeamSearchFoundationYearMax = new JLabel("Máximo");
		lblTeamSearchFoundationYearMax.setLabelFor(spnTeamSearchFoundationYearMax);
		lblTeamSearchFoundationYearMax.setBounds(25, 75, 50, 15);
		pnlTeamSearchFoundationYear.add(lblTeamSearchFoundationYearMax);

		JPanel pnlTeamSearchSupporters = new JPanel();
		pnlTeamSearchSupporters.setLayout(null);
		pnlTeamSearchSupporters.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Socios",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlTeamSearchSupporters.setBounds(310, 10, 100, 130);
		getContentPane().add(pnlTeamSearchSupporters);

		spnTeamSearchSupportersMin = new JSpinner();
		spnTeamSearchSupportersMin.setModel(new SpinnerNumberModel(0, 0, 300000, 1));
		spnTeamSearchSupportersMin.setBounds(15, 35, 70, 20);
		pnlTeamSearchSupporters.add(spnTeamSearchSupportersMin);

		spnTeamSearchSupportersMax = new JSpinner();
		spnTeamSearchSupportersMax.setModel(new SpinnerNumberModel(300000, 0, 300000, 1));
		spnTeamSearchSupportersMax.setBounds(15, 90, 70, 20);
		pnlTeamSearchSupporters.add(spnTeamSearchSupportersMax);

		JLabel lblTeamSearchSupportersMin = new JLabel("Mínimo");
		lblTeamSearchSupportersMin.setLabelFor(spnTeamSearchSupportersMin);
		lblTeamSearchSupportersMin.setBounds(15, 20, 50, 15);
		pnlTeamSearchSupporters.add(lblTeamSearchSupportersMin);

		JLabel lblTeamSearchSupportersMax = new JLabel("Máximo");
		lblTeamSearchSupportersMax.setLabelFor(spnTeamSearchSupportersMax);
		lblTeamSearchSupportersMax.setBounds(15, 75, 50, 15);
		pnlTeamSearchSupporters.add(lblTeamSearchSupportersMax);

		JPanel pnlTeamSearchBudget = new JPanel();
		pnlTeamSearchBudget.setToolTipText("Millones de euros");
		pnlTeamSearchBudget.setLayout(null);
		pnlTeamSearchBudget.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Presupuesto \u20AC",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlTeamSearchBudget.setBounds(410, 10, 100, 130);
		getContentPane().add(pnlTeamSearchBudget);

		spnTeamSearchBudgetMin = new JSpinner();
		spnTeamSearchBudgetMin.setModel(new SpinnerNumberModel(0, 0, 2000, 1));
		spnTeamSearchBudgetMin.setBounds(25, 35, 50, 20);
		pnlTeamSearchBudget.add(spnTeamSearchBudgetMin);

		spnTeamSearchBudgetMax = new JSpinner();
		spnTeamSearchBudgetMax.setModel(new SpinnerNumberModel(2000, 0, 2000, 1));
		spnTeamSearchBudgetMax.setBounds(25, 90, 50, 20);
		pnlTeamSearchBudget.add(spnTeamSearchBudgetMax);

		JLabel lblTeamSearchBudgetMin = new JLabel("Mínimo");
		lblTeamSearchBudgetMin.setLabelFor(spnTeamSearchBudgetMin);
		lblTeamSearchBudgetMin.setBounds(25, 20, 50, 15);
		pnlTeamSearchBudget.add(lblTeamSearchBudgetMin);

		JLabel lblTeamSearchBudgetMax = new JLabel("Máximo");
		lblTeamSearchBudgetMax.setLabelFor(spnTeamSearchBudgetMax);
		lblTeamSearchBudgetMax.setBounds(25, 75, 50, 15);
		pnlTeamSearchBudget.add(lblTeamSearchBudgetMax);

		JPanel pnlTeamSearchResults = new JPanel();
		pnlTeamSearchResults.setLayout(null);
		pnlTeamSearchResults
				.setBorder(new TitledBorder(null, "Resultados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTeamSearchResults.setBounds(10, 140, 500, 220);
		getContentPane().add(pnlTeamSearchResults);

		JScrollPane scpTeamSearchResults = new JScrollPane();
		scpTeamSearchResults.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scpTeamSearchResults.setBounds(10, 15, 480, 195);
		pnlTeamSearchResults.add(scpTeamSearchResults);

		lstSearchStadiumResults = new JList<>();
		lstSearchStadiumResults.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstSearchStadiumResults.setCellRenderer(new TeamCellRenderer());
		scpTeamSearchResults.setViewportView(lstSearchStadiumResults);

		JButton btnTeamSearchSearch = new JButton("Buscar");
		btnTeamSearchSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnTeamSearchSearchActionPerformed();
			}
		});
		btnTeamSearchSearch.setBounds(430, 370, 80, 25);
		getContentPane().add(btnTeamSearchSearch);

		JButton btnTeamSearchBack = new JButton("Volver");
		btnTeamSearchBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnTeamSearchBackActionPerformed();
			}
		});
		btnTeamSearchBack.setBounds(330, 370, 85, 25);
		getContentPane().add(btnTeamSearchBack);
	}

	private void formWindowOpened() {
		List<String> listCountries = countryService.loadCountryNames(countries);
		listCountries.add(0, StringUtils.EMPTY);

		cbTeamSearchCountry
				.setModel(new DefaultComboBoxModel<>(listCountries.toArray(new String[listCountries.size()])));

		btnTeamSearchSearchActionPerformed();
	}

	private void btnTeamSearchBackActionPerformed() {
		this.dispose();
	}

	private void btnTeamSearchSearchActionPerformed() {
		TeamFilter filter = new TeamFilter();

		filter.setName(txtTeamSearchName.getText());
		if (cbTeamSearchCountry.getSelectedIndex() > 0) {
			filter.setCountryId(cbTeamSearchCountry.getSelectedIndex() - 1);
		}

		filter.setFoundationYearMin((int) spnTeamSearchFoundationYearMin.getValue());
		filter.setFoundationYearMax((int) spnTeamSearchFoundationYearMax.getValue());

		filter.setSupportersMin((int) spnTeamSearchSupportersMin.getValue());
		filter.setSupportersMax((int) spnTeamSearchSupportersMax.getValue());

		filter.setBudgetMin(CurrencyConverterHelper.convertEurosToPesetas((int) spnTeamSearchBudgetMin.getValue()));
		filter.setBudgetMax(CurrencyConverterHelper.convertEurosToPesetas((int) spnTeamSearchBudgetMax.getValue()));

		List<Team> filteredTeams = teamSearchService.searchTeams(filter, teams);

		DefaultListModel<Team> model = new DefaultListModel<>();
		filteredTeams.stream().forEach(team -> {
			model.addElement(team);
		});
		lstSearchStadiumResults.setModel(model);
	}
}
