package com.pcfutbolmania.pcf2001.model.player;

import java.util.HashMap;
import java.util.Map;

public enum SkinColor {

	// @formatter:off
	NONE,
	WHITE,
	BLACK,
	MULATTO,
	ORIENTAL;
	// @formatter:on

	private static final Map<Integer, SkinColor> SKIN_COLORS = new HashMap<>();

	static {
		SKIN_COLORS.put(NONE.ordinal(), NONE);
		SKIN_COLORS.put(WHITE.ordinal(), WHITE);
		SKIN_COLORS.put(BLACK.ordinal(), BLACK);
		SKIN_COLORS.put(MULATTO.ordinal(), MULATTO);
		SKIN_COLORS.put(ORIENTAL.ordinal(), ORIENTAL);
	}

	public static SkinColor valueOf(int id) {
		return SKIN_COLORS.get(id) == null ? WHITE : SKIN_COLORS.get(id);
	}

}
