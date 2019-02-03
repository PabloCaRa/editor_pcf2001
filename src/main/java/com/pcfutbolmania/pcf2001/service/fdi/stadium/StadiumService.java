package com.pcfutbolmania.pcf2001.service.fdi.stadium;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.pcfutbolmania.pcf2001.Config;
import com.pcfutbolmania.pcf2001.exception.EpcfException;
import com.pcfutbolmania.pcf2001.exception.stadium.StadiumImageDeleteException;
import com.pcfutbolmania.pcf2001.exception.stadium.StadiumImageSaveException;
import com.pcfutbolmania.pcf2001.exception.stadium.StadiumLoadException;
import com.pcfutbolmania.pcf2001.exception.stadium.StadiumSaveException;
import com.pcfutbolmania.pcf2001.helper.PcfStringHelper;
import com.pcfutbolmania.pcf2001.model.Entity;
import com.pcfutbolmania.pcf2001.model.Header;
import com.pcfutbolmania.pcf2001.model.stadium.Stadium;
import com.pcfutbolmania.pcf2001.service.fdi.AbstractEntityService;
import com.pcfutbolmania.pcf2001.service.fdi.HeaderService;

public class StadiumService extends AbstractEntityService {

	private static final String STADIUM_IMAGE_EXTENSION = ".jpg";

	private HeaderService headerService;

	public StadiumService() {
		this.headerService = new HeaderService();
	}

	@Override
	protected Map<Integer, Stadium> load(RandomAccessFile file, Map<Integer, Header> headers) throws EpcfException {
		Map<Integer, Stadium> stadiums = new TreeMap<>();

		headers.values().stream().forEach(header -> stadiums.put(header.getId(), loadStadium(file, header)));

		return stadiums;
	}

	private Stadium loadStadium(RandomAccessFile file, Header header) throws EpcfException {
		Stadium stadium = new Stadium();

		try {
			file.seek(header.getInit());
			stadium.setHeader(header);
			stadium.setNameLength(Short.reverseBytes(file.readShort()));
			stadium.setName(PcfStringHelper.read(stadium.getNameLength(), file));
			stadium.setWidth(file.readByte());
			stadium.setLength(file.readByte());
			stadium.setUnknown(file.readByte());
			stadium.setCountryId(file.readByte());
			stadium.setConstructionYear(Short.reverseBytes(file.readShort()));
			stadium.setSittingCapacity(Integer.reverseBytes(file.readInt()));
			stadium.setStandingCapacity(Integer.reverseBytes(file.readInt()));
			stadium.setTeams(new ArrayList<>());
		} catch (IOException e) {
			throw new StadiumLoadException(e);
		}

		return stadium;
	}

	@Override
	protected void save(RandomAccessFile file, Map<Integer, ? extends Entity> entities) throws EpcfException {
		entities.values().stream().forEach(entity -> {
			Stadium stadium = (Stadium) entity;
			saveStadium(file, stadium);
		});
	}

	private void saveStadium(RandomAccessFile file, Stadium stadium) {
		try {
			file.writeShort(Short.reverseBytes(stadium.getNameLength()));
			PcfStringHelper.write(stadium.getName(), stadium.getNameLength(), file);

			file.write(stadium.getWidth());
			file.write(stadium.getLength());
			file.write(stadium.getUnknown());
			file.write(stadium.getCountryId());
			file.writeShort(Short.reverseBytes(stadium.getConstructionYear()));
			file.writeInt(Integer.reverseBytes(stadium.getSittingCapacity()));
			file.writeInt(Integer.reverseBytes(stadium.getStandingCapacity()));

		} catch (IOException e) {
			throw new StadiumSaveException(e, stadium.getHeader().getId());
		}
	}

	public String loadImage(int stadiumId) {
		String imagePath = StringUtils.EMPTY;
		String path = getFilePath(stadiumId);

		if (Files.exists(Paths.get(path), LinkOption.NOFOLLOW_LINKS)) {
			imagePath = path;
		}

		return imagePath;
	}

	public void saveImage(File image, int stadiumId) throws StadiumImageSaveException {
		String path = getFilePath(stadiumId);
		try {
			Files.copy(image.toPath(), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new StadiumImageSaveException(e);
		}
	}

	public void deleteImage(int stadiumId) throws StadiumImageDeleteException {
		String path = getFilePath(stadiumId);
		try {
			Files.delete(Paths.get(path));
		} catch (IOException e) {
			throw new StadiumImageDeleteException(e);
		}
	}

	public void initilizeHeader(Map<Integer, Stadium> stadiums, Stadium stadium) {
		Header header = new Header();
		header.setId(headerService.getIdToCreateEntity(stadiums));
		stadium.setHeader(header);
	}

	private String getFilePath(int stadiumId) {
		String path = Config.getInstance().getProperty(Config.STADIUM_IMAGES_FILE);
		DecimalFormat formatter = new DecimalFormat("0000");

		StringBuilder sb = new StringBuilder(path);
		sb.append(formatter.format(stadiumId));
		sb.append(STADIUM_IMAGE_EXTENSION);

		return sb.toString();
	}

}
