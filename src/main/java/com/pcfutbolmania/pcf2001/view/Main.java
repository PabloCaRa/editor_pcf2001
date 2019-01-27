package com.pcfutbolmania.pcf2001.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import com.pcfutbolmania.pcf2001.controller.MainController;
import com.pcfutbolmania.pcf2001.exception.EpcfException;
import com.pcfutbolmania.pcf2001.model.coach.Coach;
import com.pcfutbolmania.pcf2001.model.pak.Country;
import com.pcfutbolmania.pcf2001.model.player.Player;
import com.pcfutbolmania.pcf2001.model.stadium.Stadium;
import com.pcfutbolmania.pcf2001.model.team.Team;
import com.pcfutbolmania.pcf2001.view.coach.CoachSearch;
import com.pcfutbolmania.pcf2001.view.player.PlayerSearch;
import com.pcfutbolmania.pcf2001.view.stadium.StadiumSearch;
import com.pcfutbolmania.pcf2001.view.utils.CountryWindow;

public class Main {

	private JFrame frmEpcf;
	private MainController mainController;

	private Map<Integer, Player> players;
	private Map<Integer, Stadium> stadiums;
	private Map<Integer, Coach> coaches;
	private Map<Integer, Team> teams;
	private Map<Integer, Country> countries;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Main window = new Main();
					window.frmEpcf.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {

		LookAndFeelInfo lf[] = UIManager.getInstalledLookAndFeels();

		Class<?> lfClass = null;

		try {
			lfClass = Class.forName(lf[3].getClassName());
			LookAndFeel lfObject = null;
			lfObject = (LookAndFeel) lfClass.newInstance();
			UIManager.setLookAndFeel(lfObject);
		} catch (InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException
				| ClassNotFoundException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}

		mainController = new MainController();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEpcf = new JFrame();
		frmEpcf.setTitle("EPCF 2001");
		frmEpcf.setResizable(false);
		frmEpcf.setBounds(100, 100, 450, 300);
		frmEpcf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmEpcf.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("Archivo");
		mnFile.setMnemonic('A');
		menuBar.add(mnFile);

		JMenuItem mntmLoad = new JMenuItem("Cargar todo");
		mntmLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					ExecutorService executorService = Executors.newFixedThreadPool(5);

					executorService.submit(new Runnable() {
						@Override
						public void run() {
							System.out.println("Inicio carga estadios " + Calendar.getInstance().getTime());
							stadiums = mainController.loadStadiums();
							System.out.println("Fin carga estadios " + Calendar.getInstance().getTime());
						}
					});

					executorService.submit(new Runnable() {
						@Override
						public void run() {
							System.out.println("Inicio carga entrenadores " + Calendar.getInstance().getTime());
							coaches = mainController.loadCoaches();
							System.out.println("Fin carga entrenadores " + Calendar.getInstance().getTime());
						}
					});

					executorService.submit(new Runnable() {
						@Override
						public void run() {
							System.out.println("Inicio carga jugadores " + Calendar.getInstance().getTime());
							players = mainController.loadPlayers();
							System.out.println("Fin carga jugadores " + Calendar.getInstance().getTime());
						}
					});

					executorService.submit(new Runnable() {
						@Override
						public void run() {
							System.out.println("Inicio carga equipos " + Calendar.getInstance().getTime());
							teams = mainController.loadTeams();
							System.out.println("Fin carga equipos " + Calendar.getInstance().getTime());
						}
					});

					executorService.submit(new Runnable() {
						@Override
						public void run() {
							System.out.println("Inicio carga textos " + Calendar.getInstance().getTime());
							countries = mainController.loadCountries();
							System.out.println("Fin carga textos " + Calendar.getInstance().getTime());
						}
					});

					executorService.shutdown();

					executorService.awaitTermination(30, TimeUnit.SECONDS);

				} catch (EpcfException | InterruptedException exception) {
					JOptionPane.showMessageDialog(frmEpcf, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

				System.out.println("Inicio seteo equipo " + Calendar.getInstance().getTime());
				mainController.setTeams(teams, coaches, stadiums, players);
				System.out.println("Fin seteo equipo " + Calendar.getInstance().getTime());

				JOptionPane.showMessageDialog(frmEpcf, "Los archivos se han cargado correctamente", "Información",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnFile.add(mntmLoad);

		JMenuItem mntmSave = new JMenuItem("Guardar todo");
		mntmSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					ExecutorService executorService = Executors.newFixedThreadPool(4);

					executorService.submit(new Runnable() {
						@Override
						public void run() {
							System.out.println("Inicio guardado entrenadores " + Calendar.getInstance().getTime());
							mainController.saveCoaches(coaches);
							System.out.println("Fin guardado entrenadores " + Calendar.getInstance().getTime());
						}
					});

					executorService.submit(new Runnable() {
						@Override
						public void run() {
							System.out.println("Inicio guardado estadios " + Calendar.getInstance().getTime());
							mainController.saveStadiums(stadiums);
							System.out.println("Fin guardado estadios " + Calendar.getInstance().getTime());
						}
					});

					executorService.submit(new Runnable() {
						@Override
						public void run() {
							System.out.println("Inicio guardado equipos " + Calendar.getInstance().getTime());
							mainController.saveTeams(teams);
							System.out.println("Fin guardado equipos " + Calendar.getInstance().getTime());
						}
					});

					executorService.submit(new Runnable() {
						@Override
						public void run() {
							System.out.println("Inicio guardado jugadores " + Calendar.getInstance().getTime());
							mainController.savePlayers(players);
							System.out.println("Fin guardado jugadores " + Calendar.getInstance().getTime());
						}
					});

					executorService.shutdown();

					executorService.awaitTermination(30, TimeUnit.SECONDS);
				} catch (EpcfException | InterruptedException e) {
					JOptionPane.showMessageDialog(frmEpcf, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

				JOptionPane.showMessageDialog(frmEpcf, "Los archivos se han guardado correctamente", "Información",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnFile.add(mntmSave);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);

		JMenuItem mntmBackup = new JMenuItem("Hacer backup");
		mnFile.add(mntmBackup);

		JMenuItem mntmRestoreBackup = new JMenuItem("Recuperar backup");
		mnFile.add(mntmRestoreBackup);

		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);

		JMenuItem mntmExit = new JMenuItem("Salir");
		mntmExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);

		JMenu mnSearch = new JMenu("Buscar");
		mnSearch.setMnemonic('B');
		menuBar.add(mnSearch);

		JMenuItem mntmSearchTeam = new JMenuItem("Equipo");
		mnSearch.add(mntmSearchTeam);

		JMenuItem mntmSearchPlayer = new JMenuItem("Jugador");
		mntmSearchPlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PlayerSearch playerSearch = new PlayerSearch(countries, players, teams);
				playerSearch.setLocationRelativeTo(null);
				playerSearch.setVisible(true);
			}
		});
		mnSearch.add(mntmSearchPlayer);

		JMenuItem mntmSearchCoach = new JMenuItem("Entrenador");
		mntmSearchCoach.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CoachSearch coachSearch = new CoachSearch(coaches, teams);
				coachSearch.setLocationRelativeTo(null);
				coachSearch.setVisible(true);
			}
		});
		mnSearch.add(mntmSearchCoach);

		JMenuItem mntmSearchStadium = new JMenuItem("Estadio");
		mntmSearchStadium.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StadiumSearch stadiumSearch = new StadiumSearch(stadiums, teams, countries);
				stadiumSearch.setLocationRelativeTo(null);
				stadiumSearch.setVisible(true);
			}
		});
		mnSearch.add(mntmSearchStadium);

		JMenu mnuUtils = new JMenu("Utilidades");
		menuBar.add(mnuUtils);

		JMenuItem mntmCountries = new JMenuItem("Paises");
		mntmCountries.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CountryWindow countryWindow = new CountryWindow(countries);
				countryWindow.setLocationRelativeTo(null);
				countryWindow.setVisible(true);
			}
		});
		mnuUtils.add(mntmCountries);
	}

}
