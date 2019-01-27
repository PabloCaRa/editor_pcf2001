package com.pcfutbolmania.pcf2001.helper;

import java.io.IOException;
import java.io.RandomAccessFile;

public class PcfByteHelper {

	public static int readUnsignedShort(RandomAccessFile file) throws IOException {
		int data16Bits = (file.read() & 0xFF);
		data16Bits += (file.read() & 0xFF) << 8;
		return data16Bits;
	}

	public static void writeUnsignedShort(int data16bits, RandomAccessFile file) throws IOException {
		byte[] codeBytes = new byte[2];
		codeBytes[0] = (byte) (data16bits & 0xFF);
		codeBytes[1] = (byte) ((data16bits & 0xFF00) >> 8);
		file.write(codeBytes);
	}

}
