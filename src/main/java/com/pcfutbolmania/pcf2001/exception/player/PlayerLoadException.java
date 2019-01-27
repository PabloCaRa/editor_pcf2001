package com.pcfutbolmania.pcf2001.exception.player;

import com.pcfutbolmania.pcf2001.exception.EpcfException;

public class PlayerLoadException extends EpcfException {

	private static final long serialVersionUID = -2043588283326152255L;

	public PlayerLoadException(Exception exception) {
		setMessage("Error loading players");
		setException(exception);
	}

}
