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

				coach.setHiddenInfoLength(Short.reverseBytes(file.readShort()));
				coach.setHiddenInfo(PcfStringHelper.read(coach.getHiddenInfoLength(), file));

				coach.setPlaySistemLength(Short.reverseBytes(file.readShort()));
				coach.setPlaySistem(PcfStringHelper.read(coach.getPlaySistemLength(), file));

				coach.setPalmaresLength(Short.reverseBytes(file.readShort()));
				coach.setPalmares(PcfStringHelper.read(coach.getPalmaresLength(), file));

				coach.setAnecdoteLength(Short.reverseBytes(file.readShort()));
				coach.setAnecdote(PcfStringHelper.read(coach.getAnecdoteLength(), file));

				coach.setLastSeasonLength(Short.reverseBytes(file.readShort()));
				coach.setLastSeason(PcfStringHelper.read(coach.getLastSeasonLength(), file));

				coach.setCoachTrajectoryLength(Short.reverseBytes(file.readShort()));
				coach.setCoachTrajectory(PcfStringHelper.read(coach.getCoachTrajectoryLength(), file));

				coach.setWasPlayer(file.read());

				coach.setPlayerTrajectoryLength(Short.reverseBytes(file.readShort()));
				coach.setPlayerTrajectory(PcfStringHelper.read(coach.getPlayerTrajectoryLength(), file));

				if (coach.getWasPlayer() == 3) {
					coach.setDeclarationsLength(Short.reverseBytes(file.readShort()));
					coach.setDeclarations(PcfStringHelper.read(coach.getDeclarationsLength(), file));
				}

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

				file.writeShort(Short.reverseBytes(coach.getHiddenInfoLength()));
				PcfStringHelper.write(coach.getHiddenInfo(), coach.getHiddenInfoLength(), file);

				file.writeShort(Short.reverseBytes(coach.getPlaySistemLength()));
				PcfStringHelper.write(coach.getPlaySistem(), coach.getPlaySistemLength(), file);

				file.writeShort(Short.reverseBytes(coach.getPalmaresLength()));
				PcfStringHelper.write(coach.getPalmares(), coach.getPalmaresLength(), file);

				file.writeShort(Short.reverseBytes(coach.getAnecdoteLength()));
				PcfStringHelper.write(coach.getAnecdote(), coach.getAnecdoteLength(), file);

				file.writeShort(Short.reverseBytes(coach.getLastSeasonLength()));
				PcfStringHelper.write(coach.getLastSeason(), coach.getLastSeasonLength(), file);

				file.writeShort(Short.reverseBytes(coach.getCoachTrajectoryLength()));
				PcfStringHelper.write(coach.getCoachTrajectory(), coach.getCoachTrajectoryLength(), file);

				file.write(coach.getWasPlayer());

				file.writeShort(Short.reverseBytes(coach.getPlayerTrajectoryLength()));
				PcfStringHelper.write(coach.getPlayerTrajectory(), coach.getPlayerTrajectoryLength(), file);

				if (coach.getWasPlayer() == 3) {
					file.writeShort(Short.reverseBytes(coach.getDeclarationsLength()));
					PcfStringHelper.write(coach.getDeclarations(), coach.getDeclarationsLength(), file);
				}

			}

		} catch (IOException e) {
			throw new CoachSaveException(e, coach.getHeader().getId());
		}
	}

}
