package com.pcfutbolmania.pcf2001.exception.pak;

import com.pcfutbolmania.pcf2001.exception.EpcfException;

public class CountrySaveException extends EpcfException {

	private static final long serialVersionUID = -2854241794655100271L;

	public CountrySaveException(Exception exception) {
		setMessage("Error saving country");
		setException(exception);
	}

	public CountrySaveException(Exception exception, String message) {
		setMessage(message);
		setException(exception);
	}
}
