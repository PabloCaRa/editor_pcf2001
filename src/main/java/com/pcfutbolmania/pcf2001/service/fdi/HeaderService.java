package com.pcfutbolmania.pcf2001.service.fdi;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.TreeMap;

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

}
