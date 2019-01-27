package com.pcfutbolmania.pcf2001.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.pcfutbolmania.pcf2001.exception.EpcfException;

public class PcfFileHelper {

	public static void replaceTempFile(String sourceFile, String targetFile) {
		try {
			Files.move(Paths.get(sourceFile), Paths.get(targetFile), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new EpcfException("Error moving file ".concat(sourceFile).concat(" to ").concat(targetFile), e);
		}
	}

}
