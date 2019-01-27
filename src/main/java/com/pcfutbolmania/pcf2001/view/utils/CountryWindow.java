package com.pcfutbolmania.pcf2001.view.utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.lang3.StringUtils;

import com.pcfutbolmania.pcf2001.model.pak.Country;
import com.pcfutbolmania.pcf2001.service.pak.CountryService;

public class CountryWindow extends JDialog {

	private static final long serialVersionUID = -6424326960843569980L;

	private final JPanel contentPanel = new JPanel();

	private JTextField txtPais;
	private JComboBox<String> cbCountries;
	private JButton btnSave;
	private JRadioButton rdbtnEdit;
	private JRadioButton rdbtnCreate;

	private CountryService countryService;

	private Map<Integer, Country> countries;
	private final Map<Integer, Country> nonModifiedCountries;
	private JTextField txtId;

	/**
	 * Create the dialog.
	 */
	public CountryWindow(Map<Integer, Country> countries) {
		this.countries = countries;
		nonModifiedCountries = new TreeMap<>(countries);

		countryService = new CountryService();

		setModalityType(ModalityType.APPLICATION_MODAL);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);

		setTitle("Países");
		setBounds(100, 100, 270, 360);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Acci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 10, 235, 50);
			contentPanel.add(panel);
			panel.setLayout(null);

			rdbtnEdit = new JRadioButton("Editar");
			rdbtnEdit.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent arg0) {
					if (rdbtnEdit.isSelected()) {
						cbCountries.setEnabled(true);
						txtId.setText(StringUtils.EMPTY);
					}
				}
			});
			rdbtnEdit.setBounds(35, 20, 70, 20);
			panel.add(rdbtnEdit);

			rdbtnCreate = new JRadioButton("Crear");
			rdbtnCreate.setSelected(true);
			rdbtnCreate.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent arg0) {
					if (rdbtnCreate.isSelected()) {
						cbCountries.setEnabled(false);
						cbCountries.setSelectedIndex(-1);
						txtId.setText(String.valueOf(countries.size()));
						txtPais.setText(StringUtils.EMPTY);
					}
				}
			});
			rdbtnCreate.setBounds(140, 20, 70, 20);
			panel.add(rdbtnCreate);

			ButtonGroup actionsButtonGroup = new ButtonGroup();
			actionsButtonGroup.add(rdbtnEdit);
			actionsButtonGroup.add(rdbtnCreate);
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pa\u00EDses",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(10, 70, 235, 65);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				cbCountries = new JComboBox<>();
				cbCountries.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent arg0) {
						if (rdbtnEdit.isSelected()) {
							txtPais.setText((String) cbCountries.getSelectedItem());
							txtId.setText(String.valueOf(cbCountries.getSelectedIndex()));
						}

					}
				});
				loadCountriesCombo();
				cbCountries.setEnabled(false);
				cbCountries.setBounds(10, 25, 215, 20);
				cbCountries.setSelectedIndex(-1);
				panel.add(cbCountries);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pa\u00EDs",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(10, 140, 235, 145);
			contentPanel.add(panel);
			panel.setLayout(null);

			txtPais = new JTextField();
			txtPais.setBounds(10, 80, 215, 20);
			panel.add(txtPais);
			txtPais.setColumns(10);
			{
				btnSave = new JButton("Guardar");
				btnSave.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if (StringUtils.isBlank(txtPais.getText())) {
							JOptionPane.showMessageDialog(null, "Es necesario introducir un nombre de país", "Error",
									JOptionPane.ERROR_MESSAGE);
						} else {
							Country country = new Country();
							country.setId(Integer.parseInt(txtId.getText()));
							country.setNameLength((short) txtPais.getText().length());
							country.setName(StringUtils.upperCase(txtPais.getText()));
							countries.put(country.getId(), country);
							loadCountriesCombo();
							cbCountries.setSelectedIndex(-1);
							txtPais.setText(StringUtils.EMPTY);
							txtId.setText(String.valueOf(countries.size()));

						}

					}
				});
				btnSave.setBounds(135, 110, 90, 25);
				panel.add(btnSave);
			}
			{
				JLabel lblNombre = new JLabel("Nombre");
				lblNombre.setBounds(10, 65, 45, 15);
				panel.add(lblNombre);
			}
			{
				txtId = new JTextField();
				txtId.setEditable(false);
				txtId.setBounds(10, 35, 30, 20);
				panel.add(txtId);
				txtId.setColumns(10);
				txtId.setText(String.valueOf(countries.size()));
			}
			{
				JLabel lblId = new JLabel("Id");
				lblId.setLabelFor(txtId);
				lblId.setBounds(10, 20, 20, 15);
				panel.add(lblId);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAccept = new JButton("Aceptar");
				btnAccept.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						btnAcceptActionPerformed();
					}

				});
				btnAccept.setActionCommand("OK");
				buttonPane.add(btnAccept);
				getRootPane().setDefaultButton(btnAccept);
			}
			{
				JButton btnCancel = new JButton("Cancelar");
				btnCancel.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						btnCancelActionPerformed();
					}
				});
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}

	}

	private void loadCountriesCombo() {
		List<String> listCountries = countryService.loadCountryNames(countries);

		cbCountries.setModel(new DefaultComboBoxModel<>(listCountries.toArray(new String[listCountries.size()])));
	}

	private void btnAcceptActionPerformed() {
		countryService.save(countries);
		this.dispose();
	}

	private void btnCancelActionPerformed() {
		countries.clear();
		countries.putAll(nonModifiedCountries);
		this.dispose();
	}

}
