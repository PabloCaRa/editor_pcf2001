package com.pcfutbolmania.pcf2001.view.stadium;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import org.apache.commons.lang3.StringUtils;

import com.pcfutbolmania.pcf2001.exception.stadium.StadiumImageDeleteException;
import com.pcfutbolmania.pcf2001.exception.stadium.StadiumImageSaveException;
import com.pcfutbolmania.pcf2001.model.pak.Country;
import com.pcfutbolmania.pcf2001.model.stadium.Stadium;
import com.pcfutbolmania.pcf2001.model.team.Team;
import com.pcfutbolmania.pcf2001.service.fdi.HeaderService;
import com.pcfutbolmania.pcf2001.service.fdi.stadium.StadiumService;
import com.pcfutbolmania.pcf2001.service.pak.CountryService;
import com.pcfutbolmania.pcf2001.view.common.EntityTeamsPanel;

public class StadiumInfo extends JDialog {

	private static final long serialVersionUID = -4797869970887236645L;

	private StadiumService stadiumService;
	private HeaderService headerService;
	private CountryService countryService;

	private Map<Integer, Stadium> stadiums;
	private Map<Integer, Country> countries;

	private Stadium stadium;

	private JTextField txtStadiumName;

	private JComboBox<String> cbStadiumCountry;

	private JSpinner spnStadiumLength;

	private JSpinner spnStadiumWidth;

	private JSpinner spnStadiumSittingCapacity;

	private JSpinner spnStadiumStandingCapacity;

	private JSpinner spnStadiumConstructionYear;

	private JLabel lblStadiumImage;

	private JButton btnStadiumDeleteImage;

	/**
	 * Create the dialog.
	 */
	public StadiumInfo(Stadium stadium, Map<Integer, Stadium> stadiums, Map<Integer, Country> countries,
			Map<Integer, Team> teams) {
		setModalityType(ModalityType.APPLICATION_MODAL);

		stadiumService = new StadiumService();
		headerService = new HeaderService();
		countryService = new CountryService();

		this.stadium = stadium;
		this.stadiums = stadiums;
		this.countries = countries;

		setIconImage(Toolkit.getDefaultToolkit().getImage(StadiumInfo.class.getResource("/images/icons/stadium.png")));
		setResizable(false);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				formWindowOpened();
			}
		});

		setBounds(100, 100, 725, 405);
		getContentPane().setLayout(null);

		JPanel pnlStadiumName = new JPanel();
		pnlStadiumName.setLayout(null);
		pnlStadiumName.setBorder(new TitledBorder(null, "Nombre", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlStadiumName.setBounds(10, 10, 200, 65);
		getContentPane().add(pnlStadiumName);

		txtStadiumName = new JTextField();
		txtStadiumName.setColumns(10);
		txtStadiumName.setBounds(15, 25, 170, 20);
		pnlStadiumName.add(txtStadiumName);

		JPanel pnlStadiumCountry = new JPanel();
		pnlStadiumCountry.setLayout(null);
		pnlStadiumCountry.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pa\u00EDs",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlStadiumCountry.setBounds(10, 75, 200, 65);
		getContentPane().add(pnlStadiumCountry);

		cbStadiumCountry = new JComboBox<>();
		cbStadiumCountry.setBounds(15, 25, 170, 20);
		pnlStadiumCountry.add(cbStadiumCountry);

		JPanel pnlStadiumLength = new JPanel();
		pnlStadiumLength.setLayout(null);
		pnlStadiumLength.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Largo",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlStadiumLength.setBounds(210, 10, 100, 65);
		getContentPane().add(pnlStadiumLength);

		spnStadiumLength = new JSpinner();
		spnStadiumLength.setModel(new SpinnerNumberModel(0, 0, 255, 1));
		spnStadiumLength.setBounds(25, 25, 50, 20);
		pnlStadiumLength.add(spnStadiumLength);

		JPanel pnlStadiumWidth = new JPanel();
		pnlStadiumWidth.setLayout(null);
		pnlStadiumWidth.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ancho",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlStadiumWidth.setBounds(210, 75, 100, 65);
		getContentPane().add(pnlStadiumWidth);

		spnStadiumWidth = new JSpinner();
		spnStadiumWidth.setBounds(25, 25, 50, 20);
		pnlStadiumWidth.add(spnStadiumWidth);

		JPanel pnlStadiumSittingCapacity = new JPanel();
		pnlStadiumSittingCapacity.setLayout(null);
		pnlStadiumSittingCapacity.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"P\u00FAblico sentado", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlStadiumSittingCapacity.setBounds(10, 140, 100, 65);
		getContentPane().add(pnlStadiumSittingCapacity);

		spnStadiumSittingCapacity = new JSpinner();
		spnStadiumSittingCapacity.setModel(new SpinnerNumberModel(0, 0, 250000, 1));
		spnStadiumSittingCapacity.setBounds(15, 25, 70, 20);
		pnlStadiumSittingCapacity.add(spnStadiumSittingCapacity);

		JPanel pnlStadiumStandingCapacity = new JPanel();
		pnlStadiumStandingCapacity.setLayout(null);
		pnlStadiumStandingCapacity.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"P\u00FAblico de pie", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlStadiumStandingCapacity.setBounds(110, 140, 100, 65);
		getContentPane().add(pnlStadiumStandingCapacity);

		spnStadiumStandingCapacity = new JSpinner();
		spnStadiumStandingCapacity.setModel(new SpinnerNumberModel(0, 0, 250000, 1));
		spnStadiumStandingCapacity.setBounds(15, 25, 70, 20);
		pnlStadiumStandingCapacity.add(spnStadiumStandingCapacity);

		JPanel pnlStadiumConstructionYear = new JPanel();
		pnlStadiumConstructionYear.setLayout(null);
		pnlStadiumConstructionYear.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"A\u00F1o construcci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlStadiumConstructionYear.setBounds(210, 140, 100, 65);
		getContentPane().add(pnlStadiumConstructionYear);

		spnStadiumConstructionYear = new JSpinner();
		spnStadiumConstructionYear.setModel(new SpinnerNumberModel(0, 0, 3000, 1));
		spnStadiumConstructionYear.setBounds(25, 25, 50, 20);
		pnlStadiumConstructionYear.add(spnStadiumConstructionYear);

		JPanel pnlStadiumImage = new JPanel();
		pnlStadiumImage.setBorder(new TitledBorder(null, "Imagen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlStadiumImage.setBounds(310, 10, 400, 320);
		getContentPane().add(pnlStadiumImage);
		pnlStadiumImage.setLayout(null);

		lblStadiumImage = new JLabel("");

		lblStadiumImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblStadiumImage.setBounds(25, 30, 350, 240);
		pnlStadiumImage.add(lblStadiumImage);

		btnStadiumDeleteImage = new JButton("Eliminar");
		btnStadiumDeleteImage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnStadiumDeleteImageActionPerformed();
			}
		});
		btnStadiumDeleteImage.setBounds(210, 280, 80, 25);
		pnlStadiumImage.add(btnStadiumDeleteImage);

		JButton btnStadiumLoadImage = new JButton("Cargar");
		btnStadiumLoadImage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnStadiumLoadImageActionPerformed();
			}

		});
		btnStadiumLoadImage.setBounds(110, 280, 80, 25);
		pnlStadiumImage.add(btnStadiumLoadImage);

		JButton btnStadiumCancel = new JButton("Cancelar");
		btnStadiumCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnStadiumCancelActionPerformed();
			}
		});
		btnStadiumCancel.setBounds(630, 340, 80, 25);
		getContentPane().add(btnStadiumCancel);

		JButton btnStadiumAccept = new JButton("Aceptar");
		btnStadiumAccept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnStadiumAcceptActionPerformed();
			}
		});
		btnStadiumAccept.setBounds(530, 340, 80, 25);
		getContentPane().add(btnStadiumAccept);

		EntityTeamsPanel pnlStadiumTeams = new EntityTeamsPanel(10, 205, 200, 65, getContentPane(), teams,
				stadium.getTeams());
		getContentPane().add(pnlStadiumTeams);
	}

	private void formWindowOpened() {
		this.setTitle(stadium.getHeader().getId() + " - " + stadium.getName());

		List<String> listCountries = countryService.loadCountryNames(countries);
		listCountries.add(0, StringUtils.EMPTY);

		cbStadiumCountry.setModel(new DefaultComboBoxModel<>(listCountries.toArray(new String[listCountries.size()])));

		txtStadiumName.setText(stadium.getName());
		cbStadiumCountry.setSelectedIndex(stadium.getCountryId() + 1);
		spnStadiumWidth.setValue(stadium.getWidth());
		spnStadiumLength.setValue(stadium.getLength());
		spnStadiumSittingCapacity.setValue(stadium.getSittingCapacity());
		spnStadiumStandingCapacity.setValue(stadium.getStandingCapacity());
		spnStadiumConstructionYear.setValue(stadium.getConstructionYear());

		loadStadiumImage();
	}

	private void loadStadiumImage() {
		String imagePath = stadiumService.loadImage(stadium.getHeader().getId());

		if (StringUtils.isNotEmpty(imagePath)) {
			ImageIcon stadiumImage = new ImageIcon(imagePath);
			lblStadiumImage.setIcon(stadiumImage);
			btnStadiumDeleteImage.setEnabled(true);
		} else {
			btnStadiumDeleteImage.setEnabled(false);
		}
	}

	private void btnStadiumCancelActionPerformed() {
		this.dispose();
	}

	private void btnStadiumAcceptActionPerformed() {

		int sizeDifference = txtStadiumName.getText().length() - stadium.getNameLength().intValue();

		stadium.setNameLength(new Integer(txtStadiumName.getText().length()).shortValue());
		stadium.setName(txtStadiumName.getText());

		stadium.setCountryId(cbStadiumCountry.getSelectedIndex() - 1);

		stadium.setWidth((int) spnStadiumWidth.getValue());
		stadium.setLength((int) spnStadiumLength.getValue());
		stadium.setSittingCapacity((int) spnStadiumSittingCapacity.getValue());
		stadium.setStandingCapacity((int) spnStadiumStandingCapacity.getValue());
		stadium.setConstructionYear((short) spnStadiumConstructionYear.getValue());

		stadiums.put(stadium.getHeader().getId(), stadium);

		headerService.modifyHeader(stadiums, sizeDifference, stadium.getHeader().getId());

		this.dispose();
	}

	private void btnStadiumLoadImageActionPerformed() {
		FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Imágenes (.jpg)", "jpg");

		JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
		chooser.setFileFilter(fileFilter);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File selectedFile = chooser.getSelectedFile();
			try {
				stadiumService.saveImage(selectedFile, stadium.getHeader().getId());
				loadStadiumImage();
			} catch (StadiumImageSaveException exception) {
				JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void btnStadiumDeleteImageActionPerformed() {
		try {
			stadiumService.deleteImage(stadium.getHeader().getId());
			lblStadiumImage.setIcon(null);
			btnStadiumDeleteImage.setEnabled(false);
		} catch (StadiumImageDeleteException exception) {
			JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
