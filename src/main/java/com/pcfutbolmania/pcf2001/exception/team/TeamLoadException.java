package com.pcfutbolmania.pcf2001.exception.team;

import com.pcfutbolmania.pcf2001.exception.EpcfException;

public class TeamLoadException extends EpcfException {

	private static final long serialVersionUID = 6429422728515679583L;

	public TeamLoadException(Exception exception) {
		setMessage("Error loading teams");
		setException(exception);
	}
}
