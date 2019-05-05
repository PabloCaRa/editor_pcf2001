package com.pcfutbolmania.pcf2001.view.stadium;

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
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import org.apache.commons.lang3.StringUtils;

import com.pcfutbolmania.pcf2001.exception.stadium.StadiumImageDeleteException;
import com.pcfutbolmania.pcf2001.exception.stadium.StadiumImageSaveException;
import com.pcfutbolmania.pcf2001.helper.PcfFileHelper;
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
	private CountryService countryService;
	private HeaderService headerService;

	private Map<Integer, Stadium> stadiums;
	private Map<Integer, Country> countries;

	private Stadium stadium;
	private boolean createStadium;

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
			Map<Integer, Team> teams, boolean createStadium) {
		setModalityType(ModalityType.APPLICATION_MODAL);

		stadiumService = new StadiumService();
		countryService = new CountryService();
		headerService = new HeaderService();

		this.stadium = stadium;
		this.stadiums = stadiums;
		this.countries = countries;
		this.createStadium = createStadium;

		setIconImage(Toolkit.getDefaultToolkit().getImage(StadiumInfo.class.getResource("/images/icons/stadium.png")));
		setResizable(false);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				formWindowOpened();
			}
		});

		setBounds(100, 100, 675, 405);
		getContentPane().setLayout(null);

		JPanel pnlStadiumImage = new JPanel();
		pnlStadiumImage.setBorder(new TitledBorder(null, "Imagen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlStadiumImage.setBounds(260, 10, 400, 320);
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
		btnStadiumCancel.setBounds(580, 340, 80, 25);
		getContentPane().add(btnStadiumCancel);

		JButton btnStadiumAccept = new JButton("Aceptar");
		btnStadiumAccept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnStadiumAcceptActionPerformed();
			}
		});
		btnStadiumAccept.setBounds(480, 340, 80, 25);
		getContentPane().add(btnStadiumAccept);

		JPanel pnlStadiumData = new JPanel();
		pnlStadiumData.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlStadiumData.setBounds(10, 10, 250, 240);
		getContentPane().add(pnlStadiumData);
		pnlStadiumData.setLayout(null);

		txtStadiumName = new JTextField();
		txtStadiumName.setBounds(25, 45, 200, 20);
		txtStadiumName.setColumns(10);
		pnlStadiumData.add(txtStadiumName);

		JLabel lblStadiumName = new JLabel("Nombre:");
		lblStadiumName.setLabelFor(txtStadiumName);
		lblStadiumName.setBounds(25, 30, 45, 15);
		pnlStadiumData.add(lblStadiumName);

		spnStadiumSittingCapacity = new JSpinner();
		spnStadiumSittingCapacity.setBounds(155, 75, 70, 20);
		pnlStadiumData.add(spnStadiumSittingCapacity);
		spnStadiumSittingCapacity.setModel(new SpinnerNumberModel(0, 0, 250000, 1));

		JLabel lblStadiumSittingCapacity = new JLabel("Aforo sentados:");
		lblStadiumSittingCapacity.setBounds(25, 75, 90, 20);
		pnlStadiumData.add(lblStadiumSittingCapacity);

		spnStadiumStandingCapacity = new JSpinner();
		spnStadiumStandingCapacity.setBounds(155, 105, 70, 20);
		pnlStadiumData.add(spnStadiumStandingCapacity);
		spnStadiumStandingCapacity.setModel(new SpinnerNumberModel(0, 0, 250000, 1));

		JLabel lblStadiumStandingCapacity = new JLabel("Aforo de pie:");
		lblStadiumStandingCapacity.setBounds(25, 105, 90, 20);
		pnlStadiumData.add(lblStadiumStandingCapacity);

		spnStadiumLength = new JSpinner();
		spnStadiumLength.setBounds(175, 135, 50, 20);
		pnlStadiumData.add(spnStadiumLength);
		spnStadiumLength.setModel(new SpinnerNumberModel(0, 0, 255, 1));

		JLabel lblStadiumLength = new JLabel("Largo:");
		lblStadiumLength.setLabelFor(spnStadiumLength);
		lblStadiumLength.setBounds(130, 135, 40, 20);
		pnlStadiumData.add(lblStadiumLength);

		spnStadiumWidth = new JSpinner();
		spnStadiumWidth.setBounds(70, 135, 50, 20);
		pnlStadiumData.add(spnStadiumWidth);

		JLabel lblStadiumWidth = new JLabel("Ancho:");
		lblStadiumWidth.setLabelFor(spnStadiumWidth);
		lblStadiumWidth.setBounds(25, 135, 40, 20);
		pnlStadiumData.add(lblStadiumWidth);

		spnStadiumConstructionYear = new JSpinner();
		spnStadiumConstructionYear.setBounds(175, 165, 50, 20);
		pnlStadiumData.add(spnStadiumConstructionYear);
		spnStadiumConstructionYear.setModel(new SpinnerNumberModel(new Short((short) 0), new Short((short) 0),
				new Short((short) 3000), new Short((short) 1)));

		JLabel lblStadiumConstructionYear = new JLabel("Año de construcción:");
		lblStadiumConstructionYear.setBounds(25, 165, 110, 20);
		pnlStadiumData.add(lblStadiumConstructionYear);

		cbStadiumCountry = new JComboBox<>();
		cbStadiumCountry.setBounds(75, 195, 150, 20);
		pnlStadiumData.add(cbStadiumCountry);

		JLabel lnlStadiumCountry = new JLabel("País:");
		lnlStadiumCountry.setLabelFor(cbStadiumCountry);
		lnlStadiumCountry.setBounds(25, 195, 40, 20);
		pnlStadiumData.add(lnlStadiumCountry);

		if (!createStadium) {
			EntityTeamsPanel pnlStadiumTeams = new EntityTeamsPanel(10, 250, 250, 80, getContentPane(), teams,
					stadium.getTeams());
			getContentPane().add(pnlStadiumTeams);
		}
	}

	private void formWindowOpened() {

		List<String> listCountries = countryService.loadCountryNames(countries);
		listCountries.add(0, StringUtils.EMPTY);

		cbStadiumCountry.setModel(new DefaultComboBoxModel<>(listCountries.toArray(new String[listCountries.size()])));

		if (createStadium) {
			headerService.initializeHeader(stadiums, stadium);
		} else {
			txtStadiumName.setText(stadium.getName());
			cbStadiumCountry.setSelectedIndex(stadium.getCountryId() + 1);
			spnStadiumWidth.setValue(stadium.getWidth());
			spnStadiumLength.setValue(stadium.getLength());
			spnStadiumSittingCapacity.setValue(stadium.getSittingCapacity());
			spnStadiumStandingCapacity.setValue(stadium.getStandingCapacity());
			spnStadiumConstructionYear.setValue(stadium.getConstructionYear());

			loadStadiumImage();
		}

		this.setTitle(
				stadium.getHeader().getId() + " - " + StringUtils.defaultString(stadium.getName(), "Nuevo estadio"));

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

		String validateMessage = validateFields();
		if (StringUtils.isBlank(validateMessage)) {
			int sizeDifference = createStadium ? txtStadiumName.getText().length()
					: txtStadiumName.getText().length() - stadium.getNameLength().intValue();

			stadium.setNameLength(new Integer(txtStadiumName.getText().length()).shortValue());
			stadium.setName(txtStadiumName.getText());

			stadium.setCountryId(cbStadiumCountry.getSelectedIndex() - 1);

			stadium.setWidth((int) spnStadiumWidth.getValue());
			stadium.setLength((int) spnStadiumLength.getValue());
			stadium.setSittingCapacity((int) spnStadiumSittingCapacity.getValue());
			stadium.setStandingCapacity((int) spnStadiumStandingCapacity.getValue());
			stadium.setConstructionYear((short) spnStadiumConstructionYear.getValue());

			stadiums.put(stadium.getHeader().getId(), stadium);

			stadiumService.modifyHeader(stadiums, stadium, createStadium, sizeDifference);

			this.dispose();
		} else {
			JOptionPane.showMessageDialog(this, validateMessage, "Aviso", JOptionPane.WARNING_MESSAGE);
		}

	}

	private void btnStadiumLoadImageActionPerformed() {
		FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Imágenes (.jpg)", "jpg");

		JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
		chooser.setFileFilter(fileFilter);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File selectedFile = chooser.getSelectedFile();
			String validationMessage = PcfFileHelper.validateImage(selectedFile, Stadium.STADIUM_IMAGE_WIDTH,
					Stadium.STADIUM_IMAGE_HEIGHT);
			try {
				if (StringUtils.isBlank(validationMessage)) {
					stadiumService.saveImage(selectedFile, stadium.getHeader().getId());
					loadStadiumImage();
				} else {
					JOptionPane.showMessageDialog(this, validationMessage, "Error", JOptionPane.ERROR_MESSAGE);
				}

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

	private String validateFields() {

		String validateMessage = StringUtils.EMPTY;

		if (StringUtils.isBlank(txtStadiumName.getText())) {
			validateMessage = "El nombre no puede estar vacio";
		}

		if (cbStadiumCountry.getSelectedIndex() == 0) {
			if (StringUtils.isNotBlank(validateMessage)) {
				validateMessage += StringUtils.LF;
			}
			validateMessage += "Es obligatorio seleccionar un país";
		}

		return validateMessage;
	}
}
