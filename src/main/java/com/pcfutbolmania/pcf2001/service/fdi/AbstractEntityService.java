package com.pcfutbolmania.pcf2001.service.fdi;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.TreeMap;

import com.pcfutbolmania.pcf2001.exception.EpcfException;
import com.pcfutbolmania.pcf2001.model.Entity;
import com.pcfutbolmania.pcf2001.model.Header;

public abstract class AbstractEntityService {

	private static final String TEMP_SUFFIX = ".tmp";

	private HeaderService headerService;

	public AbstractEntityService() {
		this.headerService = new HeaderService();
	}

	public Map<Integer, ? extends Entity> loadEntity(String filePath) throws EpcfException {
		Map<Integer, ? extends Entity> entities = new TreeMap<>();
		RandomAccessFile file = null;

		try {
			file = new RandomAccessFile(filePath, "r");
			Map<Integer, Header> headers = headerService.load(file);
			entities = load(file, headers);
		} catch (IOException e) {
			throw new EpcfException("Error reading the file " + filePath, e);
		} finally {
			try {
				file.close();
			} catch (IOException e) {
				throw new EpcfException("Error closing the file " + filePath, e);
			}
		}

		return entities;
	}

	protected abstract Map<Integer, ? extends Entity> load(RandomAccessFile file, Map<Integer, Header> headers)
			throws EpcfException;

	public boolean saveEntity(String filePath, Map<Integer, ? extends Entity> entities) throws EpcfException {
		RandomAccessFile file = null;

		StringBuilder sb = new StringBuilder();
		sb.append(filePath);
		sb.append(TEMP_SUFFIX);

		try {
			file = new RandomAccessFile(sb.toString(), "rw");
			headerService.save(file, entities);
			save(file, entities);
		} catch (IOException | RuntimeException e) {
			e.printStackTrace();
			throw new EpcfException("Error saving file " + filePath, e);
		} finally {
			try {
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new EpcfException("Error closing file " + filePath, e);
			}
		}

		return true;
	}

	protected abstract void save(RandomAccessFile file, Map<Integer, ? extends Entity> entities) throws EpcfException;

	public HeaderService getHeaderService() {
		return headerService;
	}

	public void setHeaderService(HeaderService headerService) {
		this.headerService = headerService;
	}

}
