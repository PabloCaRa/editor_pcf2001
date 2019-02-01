package com.pcfutbolmania.pcf2001.exception.stadium;

import com.pcfutbolmania.pcf2001.exception.EpcfException;

public class StadiumImageSaveException extends EpcfException {

	private static final long serialVersionUID = 7045862013959787395L;

	public StadiumImageSaveException(Exception exception) {
		setMessage("Error saving stadium image");
		setException(exception);
	}

}
