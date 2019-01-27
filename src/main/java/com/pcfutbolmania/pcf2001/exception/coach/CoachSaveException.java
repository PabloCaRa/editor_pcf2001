package com.pcfutbolmania.pcf2001.exception.coach;

import com.pcfutbolmania.pcf2001.exception.EpcfException;

public class CoachSaveException extends EpcfException {

	private static final long serialVersionUID = -727186091892959729L;

	public CoachSaveException(Exception exception, Integer coachId) {
		setMessage("Error saving coach " + coachId.toString());
		setException(exception);
	}
}
