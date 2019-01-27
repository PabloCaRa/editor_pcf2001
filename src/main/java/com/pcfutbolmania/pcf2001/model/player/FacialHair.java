package com.pcfutbolmania.pcf2001.model.player;

import java.util.HashMap;
import java.util.Map;

public enum FacialHair {

	// @formatter:off
	NONE,
	NOTHING,
	MUSTACHE,
	BILLY_GOAT_BEARD,
	BEARD;
	// @formatter:on

	private static final Map<Integer, FacialHair> FACIAL_HAIRS = new HashMap<>();

	static {
		FACIAL_HAIRS.put(NONE.ordinal(), NONE);
		FACIAL_HAIRS.put(NOTHING.ordinal(), NOTHING);
		FACIAL_HAIRS.put(MUSTACHE.ordinal(), MUSTACHE);
		FACIAL_HAIRS.put(BILLY_GOAT_BEARD.ordinal(), BILLY_GOAT_BEARD);
		FACIAL_HAIRS.put(BEARD.ordinal(), BEARD);
	}

	public static FacialHair valueOf(int id) {
		return FACIAL_HAIRS.get(id) == null ? NOTHING : FACIAL_HAIRS.get(id);
	}
}
