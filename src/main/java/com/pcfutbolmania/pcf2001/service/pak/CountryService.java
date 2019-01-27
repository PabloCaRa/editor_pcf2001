package com.pcfutbolmania.pcf2001.service.pak;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.pcfutbolmania.pcf2001.Config;
import com.pcfutbolmania.pcf2001.exception.pak.CountryLoadException;
import com.pcfutbolmania.pcf2001.exception.pak.CountrySaveException;
import com.pcfutbolmania.pcf2001.helper.PcfFileHelper;
import com.pcfutbolmania.pcf2001.helper.PcfStringHelper;
import com.pcfutbolmania.pcf2001.model.pak.Country;

public class CountryService {

	private static final int INIT_FILE_CONTENT = 1414286660;
	private static final int INIT_FILE_LENGTH = 4;
	private static final String TEMP_SUFFIX = ".tmp";

	public Map<Integer, Country> load(String filePath) {
		Map<Integer, Country> countries = new TreeMap<>();
		Country country = null;
		RandomAccessFile file = null;
		int records = 0;

		try {
			file = new RandomAccessFile(filePath, "r");
		} catch (FileNotFoundException e) {
			try {
				Files.createDirectories(Paths.get(filePath.substring(0, filePath.lastIndexOf(File.separator))));
				Files.copy(getClass().getResourceAsStream("/textos/paises.22"), Paths.get(filePath));
				file = new RandomAccessFile(filePath, "r");
			} catch (IOException e1) {
				throw new CountryLoadException(e1, "Error creating directory for countries file");
			}
		}

		try {
			file.skipBytes(INIT_FILE_LENGTH);
			file.readInt(); // total file length
			records = Integer.reverseBytes(file.readInt());

			for (int i = 0; i < records; i++) {
				country = new Country();
				country.setId(i);
				country.setNameLength(Short.reverseBytes(file.readShort()));
				country.setName(PcfStringHelper.read(country.getNameLength(), file));
				countries.put(country.getId(), country);
			}
		} catch (IOException e) {
			throw new CountryLoadException(e);
		} finally {
			try {
				file.close();
			} catch (IOException e) {
				throw new CountryLoadException(e, "Error closing file");
			}
		}

		return countries;

	}

	public void save(Map<Integer, Country> countries) {
		RandomAccessFile file = null;

		String filePath = Config.getInstance().getProperty(Config.COUNTRIES_FILE);

		try {
			file = new RandomAccessFile(filePath.concat(TEMP_SUFFIX), "rw");

			int records = countries.values().size();
			int fileLength = countries.values().stream().mapToInt(country -> country.getNameLength()).sum() + records;

			file.writeInt(Integer.reverseBytes(INIT_FILE_CONTENT));
			file.writeInt(Integer.reverseBytes(fileLength));
			file.writeInt(Integer.reverseBytes(records));

			for (int i = 0; i < records; i++) {
				Country country = countries.get(i);
				file.writeShort(Short.reverseBytes(country.getNameLength()));
				PcfStringHelper.write(country.getName(), country.getNameLength(), file);
			}

		} catch (IOException e) {
			throw new CountrySaveException(e);
		} finally {
			try {
				file.close();
			} catch (IOException e) {
				throw new CountrySaveException(e, "Error closing file");
			}
		}

		PcfFileHelper.replaceTempFile(filePath.concat(TEMP_SUFFIX), filePath);
	}

	public List<String> loadCountryNames(Map<Integer, Country> mapCountries) {
		return mapCountries.values().stream().map(country -> country.getName()).collect(Collectors.toList());
	}
}
