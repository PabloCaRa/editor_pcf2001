package com.pcfutbolmania.pcf2001.service.fdi.coach;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import com.pcfutbolmania.pcf2001.exception.EpcfException;
import com.pcfutbolmania.pcf2001.exception.coach.CoachLoadException;
import com.pcfutbolmania.pcf2001.exception.coach.CoachSaveException;
import com.pcfutbolmania.pcf2001.helper.PcfByteHelper;
import com.pcfutbolmania.pcf2001.helper.PcfStringHelper;
import com.pcfutbolmania.pcf2001.model.Entity;
import com.pcfutbolmania.pcf2001.model.Header;
import com.pcfutbolmania.pcf2001.model.coach.Coach;
import com.pcfutbolmania.pcf2001.service.fdi.AbstractEntityService;

public class CoachService extends AbstractEntityService {

	@Override
	protected Map<Integer, Coach> load(RandomAccessFile file, Map<Integer, Header> headers) throws EpcfException {
		Map<Integer, Coach> coaches = new TreeMap<>();

		headers.values().stream().forEach(header -> coaches.put(header.getId(), loadCoach(file, header)));

		return coaches;
	}

	private Coach loadCoach(RandomAccessFile file, Header header) {

		Coach coach = new Coach();

		try {
			file.seek(header.getInit());
			coach.setHeader(header);
			coach.setLengthSum(Short.reverseBytes(file.readShort()));
			file.skipBytes(2);
			coach.setBasic(file.readBoolean());
			coach.setPhotoId(Short.reverseBytes(file.readShort()));
			coach.setShortNameLength(Short.reverseBytes(file.readShort()));
			coach.setShortName(PcfStringHelper.read(coach.getShortNameLength(), file));

			if (!coach.isBasic()) {
				coach.setNameLength(Short.reverseBytes(file.readShort()));
				coach.setName(PcfStringHelper.read(coach.getNameLength(), file));

				Integer length = coach.getHeader().getLength() - coach.getNameLength() - coach.getShortNameLength()
						- 11;

				byte[] ddbb = new byte[length];
				file.read(ddbb, 0, length);
				coach.setDdbb(ddbb);
			}

			coach.setTeams(new ArrayList<>());
		} catch (IOException e) {
			throw new CoachLoadException(e);
		}

		return coach;
	}

	@Override
	protected void save(RandomAccessFile file, Map<Integer, ? extends Entity> entities) throws EpcfException {
		entities.values().stream().forEach(entity -> {
			Coach coach = (Coach) entity;
			saveCoach(file, coach);
		});
	}

	private void saveCoach(RandomAccessFile file, Coach coach) {
		try {
			file.writeShort(Short.reverseBytes(coach.getLengthSum()));
			file.writeShort(Short.reverseBytes(Entity.ENTITY_START_CONTENT));
			file.writeBoolean(coach.isBasic());
			PcfByteHelper.writeUnsignedShort(coach.getPhotoId(), file);

			file.writeShort(Short.reverseBytes(coach.getShortNameLength()));
			PcfStringHelper.write(coach.getShortName(), coach.getShortNameLength(), file);

			if (!coach.isBasic()) {
				file.writeShort(Short.reverseBytes(coach.getNameLength()));
				PcfStringHelper.write(coach.getName(), coach.getNameLength(), file);
				file.write(coach.getDdbb());
			}

		} catch (IOException e) {
			throw new CoachSaveException(e, coach.getHeader().getId());
		}
	}

}
