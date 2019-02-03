package com.pcfutbolmania.pcf2001.helper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;

import com.pcfutbolmania.pcf2001.exception.EpcfException;

public class PcfFileHelper {

	public static void replaceTempFile(String sourceFile, String targetFile) {
		try {
			Files.move(Paths.get(sourceFile), Paths.get(targetFile), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new EpcfException("Error moving file ".concat(sourceFile).concat(" to ").concat(targetFile), e);
		}
	}

	public static String validateImage(File file, int width, int height) {

		String validationMessage = StringUtils.EMPTY;

		try {
			BufferedImage image = ImageIO.read(file);

			if (image.getWidth() != width) {
				validationMessage = "Image must be " + width + " pixels wide";
			}

			if (image.getHeight() != height) {
				validationMessage += StringUtils.LF + "Image must be " + height + " pixels high";
			}

		} catch (IOException e) {
			throw new EpcfException("Error validating width and height values", e);
		}

		return validationMessage;
	}

}
