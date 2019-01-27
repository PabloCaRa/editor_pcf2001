package com.pcfutbolmania.pcf2001.exception.player;

import com.pcfutbolmania.pcf2001.exception.EpcfException;

public class PlayerSaveException extends EpcfException {

	private static final long serialVersionUID = 892403997633610900L;

	public PlayerSaveException(Exception exception, int playerId) {
		setMessage("Error saving player " + playerId);
		setException(exception);
	}

}
