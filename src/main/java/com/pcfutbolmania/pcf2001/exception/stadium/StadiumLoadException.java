package com.pcfutbolmania.pcf2001.exception.stadium;

import com.pcfutbolmania.pcf2001.exception.EpcfException;

public class StadiumLoadException extends EpcfException {

	private static final long serialVersionUID = -2465159680483052860L;

	public StadiumLoadException(Exception exception) {
		setMessage("Error loading stadiums");
		setException(exception);
	}

}
