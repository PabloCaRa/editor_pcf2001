package com.pcfutbolmania.pcf2001.model.player;

import java.util.HashMap;
import java.util.Map;

public enum HairColor {

	// @formatter:off
	NONE,
	BLOND,
	HAIRLESS,
	DARK,
	HOARY,
	REDHEAD,
	BROWN;
	// @formatter:on

	private static final Map<Integer, HairColor> HAIR_COLORS = new HashMap<>();

	static {
		HAIR_COLORS.put(NONE.ordinal(), NONE);
		HAIR_COLORS.put(BLOND.ordinal(), BLOND);
		HAIR_COLORS.put(HAIRLESS.ordinal(), HAIRLESS);
		HAIR_COLORS.put(DARK.ordinal(), DARK);
		HAIR_COLORS.put(HOARY.ordinal(), HOARY);
		HAIR_COLORS.put(REDHEAD.ordinal(), REDHEAD);
		HAIR_COLORS.put(BROWN.ordinal(), BROWN);
	}

	public static HairColor valueOf(int id) {
		return HAIR_COLORS.get(id) == null ? HAIRLESS : HAIR_COLORS.get(id);
	}

}
