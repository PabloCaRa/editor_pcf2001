package com.pcfutbolmania.pcf2001.exception.pak;

import com.pcfutbolmania.pcf2001.exception.EpcfException;

public class CountryLoadException extends EpcfException {

	private static final long serialVersionUID = -2854241794655100271L;

	public CountryLoadException(Exception exception) {
		setMessage("Error loading countries");
		setException(exception);
	}

	public CountryLoadException(Exception exception, String message) {
		setMessage(message);
		setException(exception);
	}
}
