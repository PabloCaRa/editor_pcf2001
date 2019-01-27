package com.pcfutbolmania.pcf2001.model.player;

import java.util.HashMap;
import java.util.Map;

public enum Demarcation {

	// @formatter:off
	GOALKEEPER,
	DEFENSE,
	MIDFIELER,
	FORWARD;
	// @formatter:on

	private static final Map<Integer, Demarcation> DEMARCATIONS = new HashMap<>();

	static {
		DEMARCATIONS.put(GOALKEEPER.ordinal(), GOALKEEPER);
		DEMARCATIONS.put(DEFENSE.ordinal(), DEFENSE);
		DEMARCATIONS.put(MIDFIELER.ordinal(), MIDFIELER);
		DEMARCATIONS.put(FORWARD.ordinal(), FORWARD);
	}

	public static Demarcation valueOf(int id) {
		return DEMARCATIONS.get(id);
	}
}
