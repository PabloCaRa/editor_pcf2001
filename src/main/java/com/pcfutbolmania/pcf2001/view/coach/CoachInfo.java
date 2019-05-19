package com.pcfutbolmania.pcf2001.view.coach;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.apache.commons.lang3.StringUtils;

import com.pcfutbolmania.pcf2001.model.coach.Coach;
import com.pcfutbolmania.pcf2001.model.team.Team;
import com.pcfutbolmania.pcf2001.service.fdi.HeaderService;
import com.pcfutbolmania.pcf2001.service.fdi.coach.CoachService;
import com.pcfutbolmania.pcf2001.view.common.EntityTeamsPanel;

public class CoachInfo extends JDialog {

	private static final long serialVersionUID = -6045353900569338778L;

	private CoachService coachService;
	private HeaderService headerService;

	private Map<Integer, Coach> coaches;
	private Map<Integer, Team> teams;

	private Coach coach;

	private boolean createCoach;

	private JTextField txtCoachShortName;
	private JTextField txtCoachName;

	/**
	 * Create the dialog.
	 */
	public CoachInfo(Coach coach, Map<Integer, Coach> coaches, Map<Integer, Team> teams, boolean createCoach) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CoachInfo.class.getResource("/images/icons/team.png")));
		coachService = new CoachService();
		headerService = new HeaderService();

		this.coaches = coaches;
		this.teams = teams;

		this.coach = coach;
		this.createCoach = createCoach;

		setModalityType(ModalityType.APPLICATION_MODAL);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				formWindowOpened();
			}
		});
		setBounds(100, 100, 255, 300);
		getContentPane().setLayout(null);

		JPanel pnlCoachData = new JPanel();
		pnlCoachData.setLayout(null);
		pnlCoachData.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlCoachData.setBounds(10, 10, 220, 140);
		getContentPane().add(pnlCoachData);

		txtCoachShortName = new JTextField();
		txtCoachShortName.setToolTipText("Nombre corto");
		txtCoachShortName.setColumns(10);
		txtCoachShortName.setBounds(15, 40, 190, 20);
		pnlCoachData.add(txtCoachShortName);

		JLabel lblCoachShortName = new JLabel("Nombre corto:");
		lblCoachShortName.setLabelFor(txtCoachShortName);
		lblCoachShortName.setBounds(15, 25, 90, 15);
		pnlCoachData.add(lblCoachShortName);

		txtCoachName = new JTextField();
		txtCoachName.setToolTipText("Nombre corto");
		txtCoachName.setColumns(10);
		txtCoachName.setBounds(15, 95, 190, 20);
		pnlCoachData.add(txtCoachName);

		JLabel lblCoachName = new JLabel("Nombre largo:");
		lblCoachName.setLabelFor(txtCoachName);
		lblCoachName.setBounds(15, 80, 90, 15);
		pnlCoachData.add(lblCoachName);

		JButton btnCoachCancel = new JButton("Cancelar");
		btnCoachCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnCoachCancelActionPerformed();
			}
		});
		btnCoachCancel.setBounds(150, 225, 80, 25);
		getContentPane().add(btnCoachCancel);

		JButton btnCoachAccept = new JButton("Aceptar");
		btnCoachAccept.setBounds(50, 225, 80, 25);
		getContentPane().add(btnCoachAccept);

		if (!createCoach) {
			EntityTeamsPanel pnlCoachTeams = new EntityTeamsPanel(10, 150, 220, 65, getContentPane(), teams,
					coach.getTeams());
			getContentPane().add(pnlCoachTeams);
		}
	}

	private void formWindowOpened() {
		if (createCoach) {
			headerService.initializeHeader(coaches, coach);
		} else {
			txtCoachShortName.setText(coach.getShortName());
			txtCoachName.setEnabled(!coach.isBasic());
			if (!coach.isBasic()) {
				txtCoachName.setText(coach.getName());
			}
		}

		this.setTitle(
				coach.getHeader().getId() + " - " + StringUtils.defaultString(coach.getName(), "Nuevo entrenador"));
	}

	private void btnCoachCancelActionPerformed() {
		this.dispose();
	}
}
