package com.pcfutbolmania.pcf2001.exception.stadium;

import com.pcfutbolmania.pcf2001.exception.EpcfException;

public class StadiumSaveException extends EpcfException {

	private static final long serialVersionUID = 1049379967913846108L;

	public StadiumSaveException(Exception exception, Integer stadiumId) {
		setMessage("Error saving stadium " + stadiumId.toString());
		setException(exception);
	}

}
