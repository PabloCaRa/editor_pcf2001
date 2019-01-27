package com.pcfutbolmania.pcf2001.exception.coach;

import com.pcfutbolmania.pcf2001.exception.EpcfException;

public class CoachLoadException extends EpcfException {

	private static final long serialVersionUID = -5813751377916436150L;

	public CoachLoadException(Exception exception) {
		setMessage("Error loading coaches");
		setException(exception);
	}

}
