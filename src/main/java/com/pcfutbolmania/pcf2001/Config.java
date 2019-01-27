package com.pcfutbolmania.pcf2001;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

	private static final String CONFIG_FILE = "config.properties";

	public static final String MAIN_FOLDER = "main_folder";
	public static final String PLAYERS_FILE = "players_file";
	public static final String TEAMS_FILE = "teams_file";
	public static final String COACHES_FILE = "coaches_file";
	public static final String STADIUMS_FILE = "stadiums_file";
	public static final String COUNTRIES_FILE = "countries_file";

	private static Config instance = null;
	private static Properties props;

	public Config() {
		props = load();
	}

	public static Config getInstance() {
		if (instance == null) {
			instance = new Config();
		}
		return instance;
	}

	public String getProperty(String key) {
		return props.getProperty(key);
	}

	private Properties load() {

		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties fileProps = new Properties();
		Config.props = new Properties();

		try (InputStream resourceStream = loader.getResourceAsStream(CONFIG_FILE)) {
			fileProps.load(resourceStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		StringBuilder sb = new StringBuilder();
		sb.append(System.getenv(fileProps.getProperty("options.folder.programFiles")));
		sb.append(File.separator).append(fileProps.getProperty("options.folder.dinamic"));
		sb.append(File.separator).append(fileProps.getProperty("options.folder.pcf2001"));
		sb.append(File.separator);

		Config.props.put(MAIN_FOLDER, sb.toString());

		sb.append(fileProps.getProperty("options.folder.dbdat")).append(File.separator);
		Config.props.put(STADIUMS_FILE, sb.toString().concat(fileProps.getProperty("options.file.stadiums")));
		Config.props.put(COACHES_FILE, sb.toString().concat(fileProps.getProperty("options.file.coaches")));
		Config.props.put(TEAMS_FILE, sb.toString().concat(fileProps.getProperty("options.file.teams")));
		Config.props.put(PLAYERS_FILE, sb.toString().concat(fileProps.getProperty("options.file.players")));
		Config.props.put(COUNTRIES_FILE, sb.toString().concat(fileProps.getProperty("options.folder.texts")
				.concat(File.separator).concat(fileProps.getProperty("options.file.countries"))));

		return props;
	}
}
