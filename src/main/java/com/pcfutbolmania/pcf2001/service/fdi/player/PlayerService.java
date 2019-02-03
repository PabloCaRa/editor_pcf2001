package com.pcfutbolmania.pcf2001.service.fdi.player;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.pcfutbolmania.pcf2001.exception.EpcfException;
import com.pcfutbolmania.pcf2001.exception.player.PlayerLoadException;
import com.pcfutbolmania.pcf2001.exception.player.PlayerSaveException;
import com.pcfutbolmania.pcf2001.helper.PcfByteHelper;
import com.pcfutbolmania.pcf2001.helper.PcfStringHelper;
import com.pcfutbolmania.pcf2001.model.Entity;
import com.pcfutbolmania.pcf2001.model.Header;
import com.pcfutbolmania.pcf2001.model.player.Demarcation;
import com.pcfutbolmania.pcf2001.model.player.FacialHair;
import com.pcfutbolmania.pcf2001.model.player.HairColor;
import com.pcfutbolmania.pcf2001.model.player.HairType;
import com.pcfutbolmania.pcf2001.model.player.Parameters;
import com.pcfutbolmania.pcf2001.model.player.Player;
import com.pcfutbolmania.pcf2001.model.player.Position;
import com.pcfutbolmania.pcf2001.model.player.SkinColor;
import com.pcfutbolmania.pcf2001.service.fdi.AbstractEntityService;

public class PlayerService extends AbstractEntityService {

	@Override
	protected Map<Integer, ? extends Entity> load(RandomAccessFile file, Map<Integer, Header> headers)
			throws EpcfException {
		Map<Integer, Player> players = new TreeMap<>();

		headers.values().stream().forEach(header -> players.put(header.getId(), loadPlayer(file, header)));

		return players;
	}

	private Player loadPlayer(RandomAccessFile file, Header header) {
		Player player = new Player();

		try {
			file.seek(header.getInit());
			player.setHeader(header);

			player.setLengthSum(Short.reverseBytes(file.readShort()));
			file.skipBytes(2);

			player.setBasic(file.readBoolean());
			player.setPhotoId(Short.reverseBytes(file.readShort()));
			player.setNumber(file.read());

			player.setShortNameLength(Short.reverseBytes(file.readShort()));
			player.setShortName(PcfStringHelper.read(player.getShortNameLength(), file));

			player.setNameLength(Short.reverseBytes(file.readShort()));
			player.setName(PcfStringHelper.read(player.getNameLength(), file));

			player.setGenericNumber(file.read());
			player.setUnknown(file.read());

			List<Position> positions = new ArrayList<>();
			for (int i = 0; i < 6; i++) {
				positions.add(Position.valueOf(file.read()));
			}
			player.setPositions(positions);

			player.setNationality(file.read());
			player.setSkinColor(SkinColor.valueOf(file.read()));

			player.setHairColor(HairColor.valueOf(file.read()));
			player.setDemarcation(Demarcation.valueOf(file.read()));
			player.setHairType(HairType.valueOf(file.read()));
			player.setFacialHair(FacialHair.valueOf(file.read()));
			player.setNationalized(file.readBoolean());

			player.setBirthDay(file.read());
			player.setBirthMonth(file.read());
			player.setBirthYear(Short.reverseBytes(file.readShort()));

			player.setHeight(file.read());
			player.setWeight(file.read());

			if (!player.isBasic()) {
				player.setBirthCountry(file.read());

				player.setBirthCityLength(Short.reverseBytes(file.readShort()));
				player.setBirthCity(PcfStringHelper.read(player.getBirthCityLength(), file));

				player.setFormerTeamLength(Short.reverseBytes(file.readShort()));
				player.setFormerTeam(PcfStringHelper.read(player.getFormerTeamLength(), file));

				player.setInternationalTimesLength(Short.reverseBytes(file.readShort()));
				player.setInternationalTimes(PcfStringHelper.read(player.getInternationalTimesLength(), file));

				long ddbbLength = header.getEnd() - file.getFilePointer() - 16;
				byte[] b = new byte[(int) ddbbLength];
				file.read(b, 0, (int) ddbbLength);
				player.setDdbb(b);
			}

			player.setParameters(readParameters(file));
			player.setTeamsRegistered(new ArrayList<>());
			player.setTeamsUnregistered(new ArrayList<>());

		} catch (IOException e) {
			throw new PlayerLoadException(e);
		}

		return player;
	}

	private Parameters readParameters(RandomAccessFile file) throws IOException {
		Parameters parameters = new Parameters();

		parameters.setSpeed(file.readByte());
		parameters.setStamina(file.readByte());
		parameters.setAggressiveness(file.readByte());
		parameters.setQuality(file.readByte());

		parameters.setHeadshot(file.readByte());
		parameters.setDribbling(file.readByte());
		parameters.setPass(file.readByte());
		parameters.setShot(file.readByte());
		parameters.setTackling(file.readByte());
		parameters.setGoalkeeper(file.readByte());

		parameters.setFoot(file.readByte());

		parameters.setPenalty(file.readByte());
		parameters.setLeftCorner(file.readByte());
		parameters.setRightCorner(file.readByte());
		parameters.setLeftFreeKick(file.readByte());
		parameters.setRightFreeKick(file.readByte());

		return parameters;
	}

	@Override
	protected void save(RandomAccessFile file, Map<Integer, ? extends Entity> entities) throws EpcfException {
		entities.values().stream().forEach(entity -> {
			Player player = (Player) entity;
			savePlayer(file, player);
		});
	}

	private void savePlayer(RandomAccessFile file, Player player) {

		try {
			file.writeShort(Short.reverseBytes(player.getLengthSum()));
			file.writeShort(Short.reverseBytes(Player.ENTITY_START_CONTENT));
			file.writeBoolean(player.isBasic());
			PcfByteHelper.writeUnsignedShort(player.getPhotoId(), file);
			file.write(player.getNumber());

			file.writeShort(Short.reverseBytes(player.getShortNameLength()));
			PcfStringHelper.write(player.getShortName(), player.getShortNameLength(), file);

			file.writeShort(Short.reverseBytes(player.getNameLength()));
			PcfStringHelper.write(player.getName(), player.getNameLength(), file);

			file.write(player.getGenericNumber());
			file.write(player.getUnknown());

			player.getPositions().stream().forEach(position -> {
				writePosition(file, position);
			});

			file.write(player.getNationality());
			file.write(player.getSkinColor().ordinal());
			file.write(player.getHairColor().ordinal());
			file.write(player.getDemarcation().ordinal());
			file.write(player.getHairType().ordinal());
			file.write(player.getFacialHair().ordinal());
			file.writeBoolean(player.isNationalized());

			file.write(player.getBirthDay());
			file.write(player.getBirthMonth());
			file.writeShort(Short.reverseBytes(player.getBirthYear()));

			file.write(player.getHeight());
			file.write(player.getWeight());

			if (!player.isBasic()) {
				file.write(player.getBirthCountry());
				file.writeShort(Short.reverseBytes(player.getBirthCityLength()));
				PcfStringHelper.write(player.getBirthCity(), player.getBirthCityLength(), file);

				file.writeShort(Short.reverseBytes(player.getFormerTeamLength()));
				PcfStringHelper.write(player.getFormerTeam(), player.getFormerTeamLength(), file);

				file.writeShort(Short.reverseBytes(player.getInternationalTimesLength()));
				PcfStringHelper.write(player.getInternationalTimes(), player.getInternationalTimesLength(), file);
				file.write(player.getDdbb());
			}

			saveParameters(file, player.getParameters());
		} catch (IOException | RuntimeException e) {
			e.printStackTrace();
			throw new PlayerSaveException(e, player.getHeader().getId());
		}
	}

	private void writePosition(RandomAccessFile file, Position position) throws RuntimeException {
		try {
			file.write(position.ordinal());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void saveParameters(RandomAccessFile file, Parameters parameters) throws IOException {

		file.write(parameters.getSpeed());
		file.write(parameters.getStamina());
		file.write(parameters.getAggressiveness());
		file.write(parameters.getQuality());

		file.write(parameters.getHeadshot());
		file.write(parameters.getDribbling());
		file.write(parameters.getPass());
		file.write(parameters.getShot());
		file.write(parameters.getTackling());
		file.write(parameters.getGoalkeeper());

		file.write(parameters.getFoot());

		file.write(parameters.getPenalty());
		file.write(parameters.getLeftCorner());
		file.write(parameters.getRightCorner());
		file.write(parameters.getLeftFreeKick());
		file.write(parameters.getRightFreeKick());
	}
}
