package com.pcfutbolmania.pcf2001.exception.stadium;

import com.pcfutbolmania.pcf2001.exception.EpcfException;

public class StadiumImageDeleteException extends EpcfException {

	private static final long serialVersionUID = 7045862013959787395L;

	public StadiumImageDeleteException(Exception exception) {
		setMessage("Error deleting stadium image");
		setException(exception);
	}

}
