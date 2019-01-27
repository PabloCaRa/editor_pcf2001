package com.pcfutbolmania.pcf2001.exception.team;

import com.pcfutbolmania.pcf2001.exception.EpcfException;

public class TeamSaveException extends EpcfException {

	private static final long serialVersionUID = 4525307975101790466L;

	public TeamSaveException(Exception exception, int teamId) {
		setMessage("Error saving team " + teamId);
		setException(exception);
	}
}
