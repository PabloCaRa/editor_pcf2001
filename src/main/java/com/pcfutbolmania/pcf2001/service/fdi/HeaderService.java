package com.pcfutbolmania.pcf2001.service.fdi;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.pcfutbolmania.pcf2001.model.Entity;
import com.pcfutbolmania.pcf2001.model.Header;

public class HeaderService {

	public Map<Integer, Header> load(RandomAccessFile file) throws IOException {
		Map<Integer, Header> headers = null;
		Header header = null;

		file.skipBytes(Header.INIT_FILE_LENGTH);
		int records = Integer.reverseBytes(file.readInt());
		headers = new TreeMap<>();

		for (int i = 0; i < records; i++) {
			header = new Header();
			header.setId(Integer.reverseBytes(file.readInt()));
			file.skipBytes(1);
			header.setInit(Integer.reverseBytes(file.readInt()));
			header.setLength(Integer.reverseBytes(file.readInt()));
			header.setEnd(header.getInit() + header.getLength());
			headers.put(header.getId(), header);
		}

		return headers;
	}

	public void save(RandomAccessFile file, Map<Integer, ? extends Entity> entities)
			throws IOException, RuntimeException {

		file.writeDouble(Header.INIT_FILE_CONTENT);
		file.writeDouble(0);
		file.writeInt(Integer.reverseBytes(entities.values().size()));

		entities.values().stream().forEachOrdered(entity -> {
			Header header = entity.getHeader();
			try {
				file.writeInt(Integer.reverseBytes(header.getId()));
				file.writeByte(0);
				file.writeInt(Integer.reverseBytes(header.getInit()));
				file.writeInt(Integer.reverseBytes(header.getLength()));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}

	public void createEntity(Map<Integer, ? extends Entity> entities, Entity entity) {
		entities.values().parallelStream().forEach(entityToModify -> {
			entityToModify.getHeader().setInit(entityToModify.getHeader().getInit() + Header.HEADER_BYTE_SIZE);
			entityToModify.getHeader()
					.setEnd(entityToModify.getHeader().getId() + entityToModify.getHeader().getLength());
		});
	}

	public void modifyEntity(Map<Integer, ? extends Entity> entities, Entity entity, int sizeDifference) {
		entity.getHeader().setEnd(entity.getHeader().getInit() + entity.getHeader().getLength());

		entities.values().parallelStream()
				.filter(entityToModify -> entityToModify.getHeader().getId() > entity.getHeader().getId())
				.forEach(entityToModify -> {
					entityToModify.getHeader().setInit(entityToModify.getHeader().getInit() + sizeDifference);
					entityToModify.getHeader()
							.setEnd(entityToModify.getHeader().getInit() + entityToModify.getHeader().getLength());
				});

		entities.values().parallelStream().forEach(entityToModify -> {

		});
	}

	public int getIdToCreateEntity(Map<Integer, ? extends Entity> entities) {
		return getLastHeader(entities).getId() + 1;
	}

	public int getLastEndToCreateEntity(Map<Integer, ? extends Entity> entities) {
		return getLastHeader(entities).getEnd();
	}

	private Header getLastHeader(Map<Integer, ? extends Entity> entities) {
		Comparator<Header> comparator = Comparator.comparing(Header::getId);
		List<Header> headers = entities.values().parallelStream().map(Entity::getHeader).collect(Collectors.toList());
		return headers.parallelStream().max(comparator).get();
	}

}
