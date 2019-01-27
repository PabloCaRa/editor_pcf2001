package com.pcfutbolmania.pcf2001.view.player;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.apache.commons.lang3.StringUtils;

import com.pcfutbolmania.pcf2001.model.pak.Country;
import com.pcfutbolmania.pcf2001.model.player.Demarcation;
import com.pcfutbolmania.pcf2001.model.player.Player;
import com.pcfutbolmania.pcf2001.model.player.Position;
import com.pcfutbolmania.pcf2001.model.search.PlayerFilter;
import com.pcfutbolmania.pcf2001.model.team.Team;
import com.pcfutbolmania.pcf2001.service.fdi.player.PlayerSearchService;
import com.pcfutbolmania.pcf2001.service.fdi.team.TeamSearchService;
import com.pcfutbolmania.pcf2001.service.pak.CountryService;
import com.pcfutbolmania.pcf2001.view.common.SearchTeamPanel;

public class PlayerSearch extends JDialog {

	private static final long serialVersionUID = 7764653362534697757L;

	private CountryService countryService;
	private PlayerSearchService playerSearchService;
	private TeamSearchService teamSearchService;

	private Map<Integer, Country> countries;
	private Map<Integer, Player> players;

	private JTextField txtShortName;
	private JTextField txtName;

	private JComboBox<String> cbBirthCountry;
	private JComboBox<String> cbNationality;

	private JSpinner spnBirthYearMin;
	private JSpinner spnBirthYearMax;

	private JComboBox<String> cbDemarcation;
	private JComboBox<String> cbPosition;

	private JSpinner spnMinHeight;
	private JSpinner spnMaxHeight;

	private JSpinner spnMinWeight;
	private JSpinner spnMaxWeight;

	private JComboBox<String> cbFoot;

	private JSpinner spnMinSpeed;
	private JSpinner spnMaxSpeed;

	private JSpinner spnMinStamina;
	private JSpinner spnMaxStamina;

	private JSpinner spnMinAggressiveness;
	private JSpinner spnMaxAggressiveness;

	private JSpinner spnMinQuality;
	private JSpinner spnMaxQuality;

	private JSpinner spnMinGoalkeeper;
	private JSpinner spnMaxGoalkeeper;

	private JSpinner spnMinTackling;
	private JSpinner spnMaxTackling;

	private JSpinner spnMinPass;
	private JSpinner spnMaxPass;

	private JSpinner spnMinDribbling;
	private JSpinner spnMaxDribbling;

	private JSpinner spnMinShot;
	private JSpinner spnMaxShot;

	private JSpinner spnMinHeadshot;
	private JSpinner spnMaxHeadshot;

	private JSpinner spnMinPenalty;
	private JSpinner spnMaxPenalty;

	private JSpinner spnMinLeftCorner;
	private JSpinner spnMaxLeftCorner;

	private JSpinner spnMinRightCorner;
	private JSpinner spnMaxRightCorner;

	private JSpinner spnMinLeftFreeKick;
	private JSpinner spnMaxLeftFreeKick;

	private JSpinner spnMinRightFreeKick;
	private JSpinner spnMaxRightFreeKick;

	private JList<Player> lstResults;

	private SearchTeamPanel pnlSearchTeamPanel;

	/**
	 * Create the dialog.
	 */
	public PlayerSearch(Map<Integer, Country> countries, Map<Integer, Player> players, Map<Integer, Team> teams) {

		countryService = new CountryService();
		playerSearchService = new PlayerSearchService();
		teamSearchService = new TeamSearchService();

		this.countries = countries;
		this.players = players;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				formWindowOpened();
			}
		});

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Buscar jugadores");
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 980, 800);
		getContentPane().setLayout(null);
		{
			JPanel pnlSearchPlayerName = new JPanel();
			pnlSearchPlayerName
					.setBorder(new TitledBorder(null, "Nombre", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			getContentPane().add(pnlSearchPlayerName);
			pnlSearchPlayerName.setLayout(null);
			pnlSearchPlayerName.setBounds(10, 10, 315, 140);

			txtShortName = new JTextField();
			txtShortName.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						btnSearchActionPerformed();
					}
				}
			});
			txtShortName.setToolTipText("Nombre corto");
			txtShortName.setBounds(10, 45, 295, 20);
			pnlSearchPlayerName.add(txtShortName);
			txtShortName.setColumns(10);

			JLabel lblShortName = new JLabel("Nombre corto");
			lblShortName.setLabelFor(txtShortName);
			lblShortName.setBounds(10, 30, 80, 15);
			pnlSearchPlayerName.add(lblShortName);

			txtName = new JTextField();
			txtName.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						btnSearchActionPerformed();
					}
				}
			});
			txtName.setToolTipText("Nombre largo");
			txtName.setColumns(10);
			txtName.setBounds(10, 90, 295, 20);
			pnlSearchPlayerName.add(txtName);

			JLabel lblName = new JLabel("Nombre largo");
			lblName.setLabelFor(txtName);
			lblName.setBounds(10, 75, 80, 15);
			pnlSearchPlayerName.add(lblName);
		}

		JPanel pnlSearchPlayerBirthYear = new JPanel();
		pnlSearchPlayerBirthYear.setBorder(
				new TitledBorder(null, "A\u00F1o de nacimiento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlSearchPlayerBirthYear.setBounds(810, 10, 155, 140);
		getContentPane().add(pnlSearchPlayerBirthYear);
		pnlSearchPlayerBirthYear.setLayout(null);

		spnBirthYearMin = new JSpinner();
		spnBirthYearMin.setBounds(50, 45, 55, 20);
		spnBirthYearMin.setModel(new SpinnerNumberModel(1800, 0, 9999, 1));
		pnlSearchPlayerBirthYear.add(spnBirthYearMin);

		JLabel lblBirthYearMin = new JLabel("Mínimo");
		lblBirthYearMin.setLabelFor(spnBirthYearMin);
		lblBirthYearMin.setBounds(50, 30, 45, 15);
		pnlSearchPlayerBirthYear.add(lblBirthYearMin);

		spnBirthYearMax = new JSpinner();
		spnBirthYearMax.setModel(new SpinnerNumberModel(2100, 0, 9999, 1));
		spnBirthYearMax.setBounds(50, 90, 55, 20);
		pnlSearchPlayerBirthYear.add(spnBirthYearMax);

		JLabel lblBirthYearMax = new JLabel("Máximo");
		lblBirthYearMax.setBounds(50, 75, 45, 15);
		pnlSearchPlayerBirthYear.add(lblBirthYearMax);

		JPanel pnlSearchPlayerDemarcation = new JPanel();
		pnlSearchPlayerDemarcation.setBorder(
				new TitledBorder(null, "Demarcaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlSearchPlayerDemarcation.setBounds(570, 80, 235, 70);
		getContentPane().add(pnlSearchPlayerDemarcation);
		pnlSearchPlayerDemarcation.setLayout(null);

		cbDemarcation = new JComboBox<>();
		cbDemarcation.setModel(
				new DefaultComboBoxModel<>(new String[] { "", "Portero", "Defensa", "Centrocampista", "Delantero" }));
		cbDemarcation.setBounds(10, 30, 215, 20);
		pnlSearchPlayerDemarcation.add(cbDemarcation);

		JPanel pnlSearchPlayerPosition = new JPanel();
		pnlSearchPlayerPosition.setLayout(null);
		pnlSearchPlayerPosition.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Posici\u00F3n",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSearchPlayerPosition.setBounds(570, 10, 235, 70);
		getContentPane().add(pnlSearchPlayerPosition);

		cbPosition = new JComboBox<>();
		cbPosition.setModel(new DefaultComboBoxModel<>(new String[] { "", "Portero", "Lateral derecho",
				"Lateral izquierdo", "Líbero", "Central izquierdo", "Central derecho", "Centrocampista derecho",
				"Interior derecho", "Delantero centro", "Centrocampista izquierdo", "Extremo derecho",
				"Media punta central", "Extremo izquierdo", "Centrocampista defensivo", "Media punta derecho",
				"Media punta izquierdo", "Interior izquierdo" }));
		cbPosition.setBounds(10, 30, 215, 20);
		pnlSearchPlayerPosition.add(cbPosition);

		JPanel pnlSearchPlayerBirthCountry = new JPanel();
		pnlSearchPlayerBirthCountry.setLayout(null);
		pnlSearchPlayerBirthCountry.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Pa\u00EDs natal", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSearchPlayerBirthCountry.setBounds(330, 10, 235, 70);
		getContentPane().add(pnlSearchPlayerBirthCountry);

		cbBirthCountry = new JComboBox<>();
		cbBirthCountry.setBounds(10, 30, 215, 20);
		pnlSearchPlayerBirthCountry.add(cbBirthCountry);

		JPanel pnlSearchPlayerNationality = new JPanel();
		pnlSearchPlayerNationality.setLayout(null);
		pnlSearchPlayerNationality.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Nacionalidad", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSearchPlayerNationality.setBounds(330, 80, 235, 70);
		getContentPane().add(pnlSearchPlayerNationality);

		cbNationality = new JComboBox<>();
		cbNationality.setBounds(10, 30, 215, 20);
		pnlSearchPlayerNationality.add(cbNationality);

		JPanel pnlSearchPlayerHeight = new JPanel();
		pnlSearchPlayerHeight.setLayout(null);
		pnlSearchPlayerHeight.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Altura",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSearchPlayerHeight.setBounds(10, 150, 155, 130);
		getContentPane().add(pnlSearchPlayerHeight);

		spnMinHeight = new JSpinner();
		spnMinHeight.setModel(new SpinnerNumberModel(0, 0, 255, 1));
		spnMinHeight.setBounds(55, 35, 45, 20);
		pnlSearchPlayerHeight.add(spnMinHeight);

		spnMaxHeight = new JSpinner();
		spnMaxHeight.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spnMaxHeight.setBounds(55, 90, 45, 20);
		pnlSearchPlayerHeight.add(spnMaxHeight);

		JLabel lblMinHeight = new JLabel("Mínimo");
		lblMinHeight.setLabelFor(spnMinHeight);
		lblMinHeight.setBounds(55, 20, 45, 15);
		pnlSearchPlayerHeight.add(lblMinHeight);

		JLabel lblMaxHeight = new JLabel("Máximo");
		lblMaxHeight.setLabelFor(lblMaxHeight);
		lblMaxHeight.setBounds(55, 75, 45, 15);
		pnlSearchPlayerHeight.add(lblMaxHeight);

		JPanel pnlSearchPlayerWeight = new JPanel();
		pnlSearchPlayerWeight.setLayout(null);
		pnlSearchPlayerWeight.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Peso",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSearchPlayerWeight.setBounds(170, 150, 155, 130);
		getContentPane().add(pnlSearchPlayerWeight);

		spnMinWeight = new JSpinner();
		spnMinWeight.setModel(new SpinnerNumberModel(0, 0, 255, 1));
		spnMinWeight.setBounds(55, 35, 45, 20);
		pnlSearchPlayerWeight.add(spnMinWeight);

		spnMaxWeight = new JSpinner();
		spnMaxWeight.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spnMaxWeight.setBounds(55, 90, 45, 20);
		pnlSearchPlayerWeight.add(spnMaxWeight);

		JLabel lblMinWeight = new JLabel("Mínimo");
		lblMinWeight.setLabelFor(spnMinWeight);
		lblMinWeight.setBounds(55, 20, 45, 15);
		pnlSearchPlayerWeight.add(lblMinWeight);

		JLabel lblMaxWeight = new JLabel("Máximo");
		lblMaxWeight.setLabelFor(spnMaxWeight);
		lblMaxWeight.setBounds(55, 75, 45, 15);
		pnlSearchPlayerWeight.add(lblMaxWeight);

		JPanel pnlSearchPlayerFoot = new JPanel();
		pnlSearchPlayerFoot.setLayout(null);
		pnlSearchPlayerFoot.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pie",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSearchPlayerFoot.setBounds(330, 150, 155, 130);
		getContentPane().add(pnlSearchPlayerFoot);

		cbFoot = new JComboBox<>();
		cbFoot.setModel(new DefaultComboBoxModel<>(new String[] { "", "Diestro", "Zurdo", "Ambidiestro" }));
		cbFoot.setBounds(10, 55, 135, 20);
		pnlSearchPlayerFoot.add(cbFoot);

		JPanel pnlSearchPlayerSpeed = new JPanel();
		pnlSearchPlayerSpeed.setLayout(null);
		pnlSearchPlayerSpeed.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Velocidad",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSearchPlayerSpeed.setBounds(490, 150, 155, 130);
		getContentPane().add(pnlSearchPlayerSpeed);

		spnMinSpeed = new JSpinner();
		spnMinSpeed.setModel(new SpinnerNumberModel(0, 0, 255, 1));
		spnMinSpeed.setBounds(55, 35, 45, 20);
		pnlSearchPlayerSpeed.add(spnMinSpeed);

		spnMaxSpeed = new JSpinner();
		spnMaxSpeed.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spnMaxSpeed.setBounds(55, 90, 45, 20);
		pnlSearchPlayerSpeed.add(spnMaxSpeed);

		JLabel lblMinSpeed = new JLabel("Mínimo");
		lblMinSpeed.setLabelFor(spnMinSpeed);
		lblMinSpeed.setBounds(55, 20, 45, 15);
		pnlSearchPlayerSpeed.add(lblMinSpeed);

		JLabel lblMaxSpeed = new JLabel("Máximo");
		lblMaxSpeed.setLabelFor(lblMaxSpeed);
		lblMaxSpeed.setBounds(55, 75, 45, 15);
		pnlSearchPlayerSpeed.add(lblMaxSpeed);

		JPanel pnlSearchPlayerStamina = new JPanel();
		pnlSearchPlayerStamina.setLayout(null);
		pnlSearchPlayerStamina.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Resistencia",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSearchPlayerStamina.setBounds(650, 150, 155, 130);
		getContentPane().add(pnlSearchPlayerStamina);

		spnMinStamina = new JSpinner();
		spnMinStamina.setModel(new SpinnerNumberModel(0, 0, 255, 1));
		spnMinStamina.setBounds(55, 35, 45, 20);
		pnlSearchPlayerStamina.add(spnMinStamina);

		spnMaxStamina = new JSpinner();
		spnMaxStamina.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spnMaxStamina.setBounds(55, 90, 45, 20);
		pnlSearchPlayerStamina.add(spnMaxStamina);

		JLabel lblMinStamina = new JLabel("Mínimo");
		lblMinStamina.setLabelFor(spnMinStamina);
		lblMinStamina.setBounds(55, 20, 45, 15);
		pnlSearchPlayerStamina.add(lblMinStamina);

		JLabel lblMaxStamina = new JLabel("Máximo");
		lblMaxStamina.setLabelFor(spnMaxStamina);
		lblMaxStamina.setBounds(55, 75, 45, 15);
		pnlSearchPlayerStamina.add(lblMaxStamina);

		JPanel pnlSearchPlayerAggressiveness = new JPanel();
		pnlSearchPlayerAggressiveness.setLayout(null);
		pnlSearchPlayerAggressiveness.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Agresividad", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSearchPlayerAggressiveness.setBounds(810, 150, 155, 130);
		getContentPane().add(pnlSearchPlayerAggressiveness);

		spnMinAggressiveness = new JSpinner();
		spnMinAggressiveness.setModel(new SpinnerNumberModel(0, 0, 255, 1));
		spnMinAggressiveness.setBounds(55, 35, 45, 20);
		pnlSearchPlayerAggressiveness.add(spnMinAggressiveness);

		spnMaxAggressiveness = new JSpinner();
		spnMaxAggressiveness.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spnMaxAggressiveness.setBounds(55, 90, 45, 20);
		pnlSearchPlayerAggressiveness.add(spnMaxAggressiveness);

		JLabel lblMinAggressiveness = new JLabel("Mínimo");
		lblMinAggressiveness.setLabelFor(spnMinAggressiveness);
		lblMinAggressiveness.setBounds(55, 20, 45, 15);
		pnlSearchPlayerAggressiveness.add(lblMinAggressiveness);

		JLabel lblMaxAggressiveness = new JLabel("Máximo");
		lblMaxAggressiveness.setLabelFor(spnMaxAggressiveness);
		lblMaxAggressiveness.setBounds(55, 75, 45, 15);
		pnlSearchPlayerAggressiveness.add(lblMaxAggressiveness);

		JPanel pnlSearchPlayerQuality = new JPanel();
		pnlSearchPlayerQuality.setLayout(null);
		pnlSearchPlayerQuality.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Calidad",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSearchPlayerQuality.setBounds(10, 280, 155, 130);
		getContentPane().add(pnlSearchPlayerQuality);

		spnMinQuality = new JSpinner();
		spnMinQuality.setModel(new SpinnerNumberModel(0, 0, 255, 1));
		spnMinQuality.setBounds(55, 35, 45, 20);
		pnlSearchPlayerQuality.add(spnMinQuality);

		spnMaxQuality = new JSpinner();
		spnMaxQuality.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spnMaxQuality.setBounds(55, 90, 45, 20);
		pnlSearchPlayerQuality.add(spnMaxQuality);

		JLabel lblMinQuality = new JLabel("Mínimo");
		lblMinQuality.setLabelFor(spnMinQuality);
		lblMinQuality.setBounds(55, 20, 45, 15);
		pnlSearchPlayerQuality.add(lblMinQuality);

		JLabel lblMaxQuality = new JLabel("Máximo");
		lblMaxQuality.setLabelFor(spnMaxQuality);
		lblMaxQuality.setBounds(55, 75, 45, 15);
		pnlSearchPlayerQuality.add(lblMaxQuality);

		JPanel pnlSearchPlayerGoalkeeper = new JPanel();
		pnlSearchPlayerGoalkeeper.setLayout(null);
		pnlSearchPlayerGoalkeeper.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Portero",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSearchPlayerGoalkeeper.setBounds(170, 280, 155, 130);
		getContentPane().add(pnlSearchPlayerGoalkeeper);

		spnMinGoalkeeper = new JSpinner();
		spnMinGoalkeeper.setModel(new SpinnerNumberModel(0, 0, 255, 1));
		spnMinGoalkeeper.setBounds(55, 35, 45, 20);
		pnlSearchPlayerGoalkeeper.add(spnMinGoalkeeper);

		spnMaxGoalkeeper = new JSpinner();
		spnMaxGoalkeeper.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spnMaxGoalkeeper.setBounds(55, 90, 45, 20);
		pnlSearchPlayerGoalkeeper.add(spnMaxGoalkeeper);

		JLabel lblMinGoalkeeper = new JLabel("Mínimo");
		lblMinGoalkeeper.setLabelFor(spnMinGoalkeeper);
		lblMinGoalkeeper.setBounds(55, 20, 45, 15);
		pnlSearchPlayerGoalkeeper.add(lblMinGoalkeeper);

		JLabel lblMaxGoalkeeper = new JLabel("Máximo");
		lblMaxGoalkeeper.setLabelFor(spnMaxGoalkeeper);
		lblMaxGoalkeeper.setBounds(55, 75, 45, 15);
		pnlSearchPlayerGoalkeeper.add(lblMaxGoalkeeper);

		JPanel pnlSearchPlayerTackling = new JPanel();
		pnlSearchPlayerTackling.setLayout(null);
		pnlSearchPlayerTackling.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Entradas",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSearchPlayerTackling.setBounds(330, 280, 155, 130);
		getContentPane().add(pnlSearchPlayerTackling);

		spnMinTackling = new JSpinner();
		spnMinTackling.setModel(new SpinnerNumberModel(0, 0, 255, 1));
		spnMinTackling.setBounds(55, 35, 45, 20);
		pnlSearchPlayerTackling.add(spnMinTackling);

		spnMaxTackling = new JSpinner();
		spnMaxTackling.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spnMaxTackling.setBounds(55, 90, 45, 20);
		pnlSearchPlayerTackling.add(spnMaxTackling);

		JLabel lblMinTackling = new JLabel("Mínimo");
		lblMinTackling.setLabelFor(spnMinTackling);
		lblMinTackling.setBounds(55, 20, 45, 15);
		pnlSearchPlayerTackling.add(lblMinTackling);

		JLabel lblMaxTackling = new JLabel("Máximo");
		lblMaxTackling.setLabelFor(spnMaxTackling);
		lblMaxTackling.setBounds(55, 75, 45, 15);
		pnlSearchPlayerTackling.add(lblMaxTackling);

		JPanel pnlSearchPlayerPass = new JPanel();
		pnlSearchPlayerPass.setLayout(null);
		pnlSearchPlayerPass.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pase",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSearchPlayerPass.setBounds(490, 280, 155, 130);
		getContentPane().add(pnlSearchPlayerPass);

		spnMinPass = new JSpinner();
		spnMinPass.setModel(new SpinnerNumberModel(0, 0, 255, 1));
		spnMinPass.setBounds(55, 35, 45, 20);
		pnlSearchPlayerPass.add(spnMinPass);

		spnMaxPass = new JSpinner();
		spnMaxPass.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spnMaxPass.setBounds(55, 90, 45, 20);
		pnlSearchPlayerPass.add(spnMaxPass);

		JLabel lblMinPass = new JLabel("Mínimo");
		lblMinPass.setLabelFor(spnMinPass);
		lblMinPass.setBounds(55, 20, 45, 15);
		pnlSearchPlayerPass.add(lblMinPass);

		JLabel lblMaxPass = new JLabel("Máximo");
		lblMaxPass.setLabelFor(spnMaxPass);
		lblMaxPass.setBounds(55, 75, 45, 15);
		pnlSearchPlayerPass.add(lblMaxPass);

		JPanel pnlSearchPlayerDribbling = new JPanel();
		pnlSearchPlayerDribbling.setLayout(null);
		pnlSearchPlayerDribbling.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Regate",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSearchPlayerDribbling.setBounds(650, 280, 155, 130);
		getContentPane().add(pnlSearchPlayerDribbling);

		spnMinDribbling = new JSpinner();
		spnMinDribbling.setModel(new SpinnerNumberModel(0, 0, 255, 1));
		spnMinDribbling.setBounds(55, 35, 45, 20);
		pnlSearchPlayerDribbling.add(spnMinDribbling);

		spnMaxDribbling = new JSpinner();
		spnMaxDribbling.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spnMaxDribbling.setBounds(55, 90, 45, 20);
		pnlSearchPlayerDribbling.add(spnMaxDribbling);

		JLabel lblMinDribbling = new JLabel("Mínimo");
		lblMinDribbling.setLabelFor(spnMinDribbling);
		lblMinDribbling.setBounds(55, 20, 45, 15);
		pnlSearchPlayerDribbling.add(lblMinDribbling);

		JLabel lblMaxDribbling = new JLabel("Máximo");
		lblMaxDribbling.setLabelFor(spnMaxDribbling);
		lblMaxDribbling.setBounds(55, 75, 45, 15);
		pnlSearchPlayerDribbling.add(lblMaxDribbling);

		JPanel pnlSearchPlayerShot = new JPanel();
		pnlSearchPlayerShot.setLayout(null);
		pnlSearchPlayerShot.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tiro",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSearchPlayerShot.setBounds(810, 280, 155, 130);
		getContentPane().add(pnlSearchPlayerShot);

		spnMinShot = new JSpinner();
		spnMinShot.setModel(new SpinnerNumberModel(0, 0, 255, 1));
		spnMinShot.setBounds(55, 35, 45, 20);
		pnlSearchPlayerShot.add(spnMinShot);

		spnMaxShot = new JSpinner();
		spnMaxShot.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spnMaxShot.setBounds(55, 90, 45, 20);
		pnlSearchPlayerShot.add(spnMaxShot);

		JLabel lblMinShot = new JLabel("Mínimo");
		lblMinShot.setLabelFor(spnMinShot);
		lblMinShot.setBounds(55, 20, 45, 15);
		pnlSearchPlayerShot.add(lblMinShot);

		JLabel lblMaxShot = new JLabel("Máximo");
		lblMaxShot.setLabelFor(spnMaxShot);
		lblMaxShot.setBounds(55, 75, 45, 15);
		pnlSearchPlayerShot.add(lblMaxShot);

		JPanel pnlSearchPlayerHeadshot = new JPanel();
		pnlSearchPlayerHeadshot.setLayout(null);
		pnlSearchPlayerHeadshot.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Remate",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSearchPlayerHeadshot.setBounds(10, 410, 155, 130);
		getContentPane().add(pnlSearchPlayerHeadshot);

		spnMinHeadshot = new JSpinner();
		spnMinHeadshot.setModel(new SpinnerNumberModel(0, 0, 255, 1));
		spnMinHeadshot.setBounds(55, 35, 45, 20);
		pnlSearchPlayerHeadshot.add(spnMinHeadshot);

		spnMaxHeadshot = new JSpinner();
		spnMaxHeadshot.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spnMaxHeadshot.setBounds(55, 90, 45, 20);
		pnlSearchPlayerHeadshot.add(spnMaxHeadshot);

		JLabel lblMinHeadshot = new JLabel("Mínimo");
		lblMinHeadshot.setLabelFor(spnMinHeadshot);
		lblMinHeadshot.setBounds(55, 20, 45, 15);
		pnlSearchPlayerHeadshot.add(lblMinHeadshot);

		JLabel lblMaxHeadshot = new JLabel("Máximo");
		lblMaxHeadshot.setLabelFor(spnMaxHeadshot);
		lblMaxHeadshot.setBounds(55, 75, 45, 15);
		pnlSearchPlayerHeadshot.add(lblMaxHeadshot);

		JPanel pnlSearchPlayerPenalty = new JPanel();
		pnlSearchPlayerPenalty.setLayout(null);
		pnlSearchPlayerPenalty.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Penalti",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSearchPlayerPenalty.setBounds(170, 410, 155, 130);
		getContentPane().add(pnlSearchPlayerPenalty);

		spnMinPenalty = new JSpinner();
		spnMinPenalty.setModel(new SpinnerNumberModel(0, 0, 255, 1));
		spnMinPenalty.setBounds(55, 35, 45, 20);
		pnlSearchPlayerPenalty.add(spnMinPenalty);

		spnMaxPenalty = new JSpinner();
		spnMaxPenalty.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spnMaxPenalty.setBounds(55, 90, 45, 20);
		pnlSearchPlayerPenalty.add(spnMaxPenalty);

		JLabel lblMinPenalty = new JLabel("Mínimo");
		lblMinPenalty.setLabelFor(spnMinPenalty);
		lblMinPenalty.setBounds(55, 20, 45, 15);
		pnlSearchPlayerPenalty.add(lblMinPenalty);

		JLabel lblMaxPenalty = new JLabel("Máximo");
		lblMaxPenalty.setLabelFor(spnMaxPenalty);
		lblMaxPenalty.setBounds(55, 75, 45, 15);
		pnlSearchPlayerPenalty.add(lblMaxPenalty);

		JPanel pnlSearchPlayerLeftCorner = new JPanel();
		pnlSearchPlayerLeftCorner.setLayout(null);
		pnlSearchPlayerLeftCorner.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Córner izquierdo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSearchPlayerLeftCorner.setBounds(330, 410, 155, 130);
		getContentPane().add(pnlSearchPlayerLeftCorner);

		spnMinLeftCorner = new JSpinner();
		spnMinLeftCorner.setModel(new SpinnerNumberModel(0, 0, 255, 1));
		spnMinLeftCorner.setBounds(55, 35, 45, 20);
		pnlSearchPlayerLeftCorner.add(spnMinLeftCorner);

		spnMaxLeftCorner = new JSpinner();
		spnMaxLeftCorner.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spnMaxLeftCorner.setBounds(55, 90, 45, 20);
		pnlSearchPlayerLeftCorner.add(spnMaxLeftCorner);

		JLabel lblMinLeftCorner = new JLabel("Mínimo");
		lblMinLeftCorner.setLabelFor(spnMinLeftCorner);
		lblMinLeftCorner.setBounds(55, 20, 45, 15);
		pnlSearchPlayerLeftCorner.add(lblMinLeftCorner);

		JLabel lblMaxLeftCorner = new JLabel("Máximo");
		lblMaxLeftCorner.setLabelFor(spnMaxLeftCorner);
		lblMaxLeftCorner.setBounds(55, 75, 45, 15);
		pnlSearchPlayerLeftCorner.add(lblMaxLeftCorner);

		JPanel pnlSearchPlayerRightCorner = new JPanel();
		pnlSearchPlayerRightCorner.setLayout(null);
		pnlSearchPlayerRightCorner.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Córner derecho", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSearchPlayerRightCorner.setBounds(490, 410, 155, 130);
		getContentPane().add(pnlSearchPlayerRightCorner);

		spnMinRightCorner = new JSpinner();
		spnMinRightCorner.setModel(new SpinnerNumberModel(0, 0, 255, 1));
		spnMinRightCorner.setBounds(55, 35, 45, 20);
		pnlSearchPlayerRightCorner.add(spnMinRightCorner);

		spnMaxRightCorner = new JSpinner();
		spnMaxRightCorner.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spnMaxRightCorner.setBounds(55, 90, 45, 20);
		pnlSearchPlayerRightCorner.add(spnMaxRightCorner);

		JLabel lblMinRightCorner = new JLabel("Mínimo");
		lblMinRightCorner.setLabelFor(spnMinRightCorner);
		lblMinRightCorner.setBounds(55, 20, 45, 15);
		pnlSearchPlayerRightCorner.add(lblMinRightCorner);

		JLabel lblMaxRightCorner = new JLabel("Máximo");
		lblMaxRightCorner.setLabelFor(spnMaxRightCorner);
		lblMaxRightCorner.setBounds(55, 75, 45, 15);
		pnlSearchPlayerRightCorner.add(lblMaxRightCorner);

		JPanel pnlSearchPlayerLeftFreeKick = new JPanel();
		pnlSearchPlayerLeftFreeKick.setLayout(null);
		pnlSearchPlayerLeftFreeKick.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Falta izquierda", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSearchPlayerLeftFreeKick.setBounds(650, 410, 155, 130);
		getContentPane().add(pnlSearchPlayerLeftFreeKick);

		spnMinLeftFreeKick = new JSpinner();
		spnMinLeftFreeKick.setModel(new SpinnerNumberModel(0, 0, 255, 1));
		spnMinLeftFreeKick.setBounds(55, 35, 45, 20);
		pnlSearchPlayerLeftFreeKick.add(spnMinLeftFreeKick);

		spnMaxLeftFreeKick = new JSpinner();
		spnMaxLeftFreeKick.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spnMaxLeftFreeKick.setBounds(55, 90, 45, 20);
		pnlSearchPlayerLeftFreeKick.add(spnMaxLeftFreeKick);

		JLabel lblMinLeftFreeKick = new JLabel("Mínimo");
		lblMinLeftFreeKick.setLabelFor(spnMinLeftFreeKick);
		lblMinLeftFreeKick.setBounds(55, 20, 45, 15);
		pnlSearchPlayerLeftFreeKick.add(lblMinLeftFreeKick);

		JLabel lblMaxLeftFreeKick = new JLabel("Máximo");
		lblMaxLeftFreeKick.setLabelFor(spnMaxLeftFreeKick);
		lblMaxLeftFreeKick.setBounds(55, 75, 45, 15);
		pnlSearchPlayerLeftFreeKick.add(lblMaxLeftFreeKick);

		JPanel pnlSearchPlayerRightFreeKick = new JPanel();
		pnlSearchPlayerRightFreeKick.setLayout(null);
		pnlSearchPlayerRightFreeKick.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Falta derecha", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSearchPlayerRightFreeKick.setBounds(810, 410, 155, 130);
		getContentPane().add(pnlSearchPlayerRightFreeKick);

		spnMinRightFreeKick = new JSpinner();
		spnMinRightFreeKick.setModel(new SpinnerNumberModel(0, 0, 255, 1));
		spnMinRightFreeKick.setBounds(55, 35, 45, 20);
		pnlSearchPlayerRightFreeKick.add(spnMinRightFreeKick);

		spnMaxRightFreeKick = new JSpinner();
		spnMaxRightFreeKick.setModel(new SpinnerNumberModel(255, 0, 255, 1));
		spnMaxRightFreeKick.setBounds(55, 90, 45, 20);
		pnlSearchPlayerRightFreeKick.add(spnMaxRightFreeKick);

		JLabel lblMinRightFreeKick = new JLabel("Mínimo");
		lblMinRightFreeKick.setLabelFor(spnMinRightFreeKick);
		lblMinRightFreeKick.setBounds(55, 20, 45, 15);
		pnlSearchPlayerRightFreeKick.add(lblMinRightFreeKick);

		JLabel lblMaxRightFreeKick = new JLabel("Máximo");
		lblMaxRightFreeKick.setLabelFor(spnMaxRightFreeKick);
		lblMaxRightFreeKick.setBounds(55, 75, 45, 15);
		pnlSearchPlayerRightFreeKick.add(lblMaxRightFreeKick);

		JPanel pnlSearchPlayerActions = new JPanel();
		pnlSearchPlayerActions
				.setBorder(new TitledBorder(null, "Acciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlSearchPlayerActions.setBounds(810, 540, 155, 220);
		getContentPane().add(pnlSearchPlayerActions);
		pnlSearchPlayerActions.setLayout(null);

		JButton btnSearch = new JButton("Buscar");
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSearchActionPerformed();
			}
		});
		btnSearch.setBounds(35, 65, 85, 25);
		pnlSearchPlayerActions.add(btnSearch);

		JButton btnBack = new JButton("Volver");
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnBackActionPerformed();
			}
		});
		btnBack.setBounds(35, 135, 85, 25);
		pnlSearchPlayerActions.add(btnBack);

		JScrollPane pnlSearchPlayerResults = new JScrollPane();
		pnlSearchPlayerResults.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pnlSearchPlayerResults.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pnlSearchPlayerResults.setBorder(null);
		pnlSearchPlayerResults.setViewportBorder(
				new TitledBorder(null, "Resultados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlSearchPlayerResults.setBounds(330, 540, 475, 220);
		getContentPane().add(pnlSearchPlayerResults);

		lstResults = new JList<>();
		lstResults.setBorder(new LineBorder(new Color(0, 0, 0)));
		lstResults.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstResults.setCellRenderer(new PlayerCellRenderer());
		pnlSearchPlayerResults.setViewportView(lstResults);

		pnlSearchTeamPanel = new SearchTeamPanel(10, 540, 310, 220, getContentPane(), teams, teamSearchService);
		getContentPane().add(pnlSearchTeamPanel);

	}

	private void formWindowOpened() {
		List<String> listCountries = countryService.loadCountryNames(countries);
		listCountries.add(0, StringUtils.EMPTY);

		cbBirthCountry.setModel(new DefaultComboBoxModel<>(listCountries.toArray(new String[listCountries.size()])));
		cbNationality.setModel(new DefaultComboBoxModel<>(listCountries.toArray(new String[listCountries.size()])));

		btnSearchActionPerformed();
	}

	private void btnBackActionPerformed() {
		this.dispose();
	}

	private void btnSearchActionPerformed() {
		PlayerFilter toSearch = new PlayerFilter();
		toSearch.setName(txtName.getText());
		toSearch.setShortName(txtShortName.getText());

		if (cbBirthCountry.getSelectedIndex() > 0) {
			toSearch.setBirthCountry(cbBirthCountry.getSelectedIndex() - 1);
		}

		if (cbNationality.getSelectedIndex() > 0) {
			toSearch.setNationality(cbNationality.getSelectedIndex() - 1);
		}

		if (cbPosition.getSelectedIndex() > 0) {
			toSearch.setPosition(Position.valueOf(cbPosition.getSelectedIndex()));
		}

		if (cbDemarcation.getSelectedIndex() > 0) {
			toSearch.setDemarcation(Demarcation.valueOf(cbDemarcation.getSelectedIndex() - 1));
		}

		toSearch.setBirthYearMin((int) spnBirthYearMin.getValue());
		toSearch.setBirthYearMax((int) spnBirthYearMax.getValue());

		toSearch.setHeightMin((int) spnMinHeight.getValue());
		toSearch.setHeightMax((int) spnMaxHeight.getValue());

		toSearch.setWeightMin((int) spnMinWeight.getValue());
		toSearch.setWeightMax((int) spnMaxWeight.getValue());

		if (cbFoot.getSelectedIndex() > 0) {
			toSearch.setFoot(cbFoot.getSelectedIndex() - 1);
		}

		toSearch.setSpeedMin((int) spnMinSpeed.getValue());
		toSearch.setSpeedMax((int) spnMaxSpeed.getValue());

		toSearch.setStaminaMin((int) spnMinStamina.getValue());
		toSearch.setStaminaMax((int) spnMaxStamina.getValue());

		toSearch.setAggressivenessMin((int) spnMinAggressiveness.getValue());
		toSearch.setAggressivenessMax((int) spnMaxAggressiveness.getValue());

		toSearch.setQualityMin((int) spnMinQuality.getValue());
		toSearch.setQualityMax((int) spnMaxQuality.getValue());

		toSearch.setGoalkeeperMin((int) spnMinGoalkeeper.getValue());
		toSearch.setGoalkeeperMax((int) spnMaxGoalkeeper.getValue());

		toSearch.setTacklingMin((int) spnMinTackling.getValue());
		toSearch.setTacklingMax((int) spnMaxTackling.getValue());

		toSearch.setPassMin((int) spnMinPass.getValue());
		toSearch.setPassMax((int) spnMaxPass.getValue());

		toSearch.setDribblingMin((int) spnMinDribbling.getValue());
		toSearch.setDribblingMax((int) spnMaxDribbling.getValue());

		toSearch.setShotMin((int) spnMinShot.getValue());
		toSearch.setShotMax((int) spnMaxShot.getValue());

		toSearch.setHeadshotMin((int) spnMinHeadshot.getValue());
		toSearch.setHeadshotMax((int) spnMaxHeadshot.getValue());

		toSearch.setPenaltyMin((int) spnMinPenalty.getValue());
		toSearch.setPenaltyMax((int) spnMaxPenalty.getValue());

		toSearch.setLeftCornerMin((int) spnMinLeftCorner.getValue());
		toSearch.setLeftCornerMax((int) spnMaxLeftCorner.getValue());

		toSearch.setRightCornerMin((int) spnMinRightCorner.getValue());
		toSearch.setRightCornerMax((int) spnMaxRightCorner.getValue());

		toSearch.setLeftFreeKickMin((int) spnMinLeftFreeKick.getValue());
		toSearch.setLeftFreeKickMax((int) spnMaxLeftFreeKick.getValue());

		toSearch.setRightFreeKickMin((int) spnMinRightFreeKick.getValue());
		toSearch.setRightFreeKickMax((int) spnMaxRightFreeKick.getValue());

		if (!pnlSearchTeamPanel.getLstTeams().isSelectionEmpty()) {
			toSearch.setTeamId(pnlSearchTeamPanel.getLstTeams().getSelectedValue().getHeader().getId());
		}

		toSearch.setFree(pnlSearchTeamPanel.getChkSearchPlayerNoTeam().isSelected());

		DefaultListModel<Player> model = new DefaultListModel<>();
		List<Player> filteredPlayers = playerSearchService.searchPlayers(toSearch, players);
		filteredPlayers.stream().forEach(player -> {
			model.addElement(player);
		});
		lstResults.setModel(model);
	}
}
