package com.pcfutbolmania.pcf2001.helper;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

import com.pcfutbolmania.pcf2001.exception.EpcfException;

public class PcfStringHelper {

	/**
	 * Reads the number of bytes passed by parameter to return a string
	 *
	 * @param length
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String read(int length, RandomAccessFile file) throws IOException {

		int[] pcfString = new int[length];

		for (int i = 0; i < length; i++) {
			pcfString[i] = file.read();
		}

		return convertAsciiPCFToString(pcfString);
	}

	/**
	 * Converts an array of bytes into a String
	 *
	 * @param pcfString
	 * @return
	 */
	private static String convertAsciiPCFToString(int[] pcfString) {

		char[] character = new char[pcfString.length];

		int count = 0;

		for (int pcfChar : pcfString) {

			if ((pcfChar >= 0 && pcfChar <= 31) || (pcfChar >= 128 && pcfChar <= 159)) {
				switch (pcfChar % 2) {
				case 0:
					pcfChar += 97;
					break;
				case 1:
					pcfChar += 95;
					break;
				}
			} else if ((pcfChar >= 32 && pcfChar <= 63) || (pcfChar >= 160 && pcfChar <= 191)) {
				switch (pcfChar % 2) {
				case 0:
					pcfChar += 33;
					break;
				case 1:
					pcfChar += 31;
					break;
				}
			} else if ((pcfChar >= 64 && pcfChar <= 95) || (pcfChar >= 192 && pcfChar <= 223)) {
				switch (pcfChar % 2) {
				case 0:
					pcfChar -= 31;
					break;
				case 1:
					pcfChar -= 33;
					break;
				}
			} else if ((pcfChar >= 96 && pcfChar <= 127) || (pcfChar >= 224 && pcfChar <= 255)) {
				switch (pcfChar % 2) {
				case 0:
					pcfChar -= 95;
					break;
				case 1:
					pcfChar -= 97;
					break;
				}
			}

			character[count] = Character.toChars(pcfChar)[0];
			count++;
		}

		return new String(character);

	}

	public static void write(String text, int length, RandomAccessFile file) {

		int[] pcfString = convertStringToPcf(text);

		for (int i = 0; i <= length - 1; i++) {
			try {
				file.writeByte(pcfString[i]);
			} catch (IOException ex) {

			}
		}

	}

	private static int[] convertStringToPcf(String text) {

		byte[] pcf = new byte[text.length()];

		try {
			pcf = text.getBytes("ISO-8859-1");
		} catch (UnsupportedEncodingException ex) {
			throw new EpcfException("Error converting string" + text + " to PCF code", ex);
		}

		int[] textBytes = new int[text.length()];

		for (int i = 0; i <= text.length() - 1; i++) {

			int character = pcf[i];

			character = unsignedByte(character);

			if ((character >= 0 && character <= 31) || (character >= 128 && character <= 159)) {
				switch (character % 2) {
				case 0:
					textBytes[i] = (character + 97);
					break;
				case 1:
					textBytes[i] = (character + 95);
					break;
				}
			}

			if ((character >= 32 && character <= 63) || (character >= 160 && character <= 191)) {
				switch (character % 2) {
				case 0:
					textBytes[i] = (character + 33);
					break;
				case 1:
					textBytes[i] = (character + 31);
					break;
				}
			}

			if ((character >= 64 && character <= 95) || (character >= 192 && character <= 223)) {
				switch (character % 2) {
				case 0:
					textBytes[i] = (character - 31);
					break;
				case 1:
					textBytes[i] = (character - 33);
					break;
				}
			}

			if ((character >= 96 && character <= 127) || (character >= 224 && character <= 255)) {
				switch (character % 2) {
				case 0:
					textBytes[i] = (character - 95);
					break;
				case 1:
					textBytes[i] = (character - 97);
					break;
				}
			}
		}

		return textBytes;
	}

	private static int unsignedByte(int b) {
		return b & 0xFF;
	}

}
