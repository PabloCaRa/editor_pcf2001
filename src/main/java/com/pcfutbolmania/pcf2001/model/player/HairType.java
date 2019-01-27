package com.pcfutbolmania.pcf2001.model.player;

import java.util.HashMap;
import java.util.Map;

public enum HairType {

	// @formatter:off
	NONE,
	HAIRLESS,
	SHORT,
	NORMAL,
	LONG_HAIR,
	HALF_HAIR,
	PIGTAIL,
	STRIPE;
	// @formatter:on

	private static final Map<Integer, HairType> HAIR_TYPES = new HashMap<>();

	static {
		HAIR_TYPES.put(NONE.ordinal(), NONE);
		HAIR_TYPES.put(HAIRLESS.ordinal(), HAIRLESS);
		HAIR_TYPES.put(SHORT.ordinal(), SHORT);
		HAIR_TYPES.put(NORMAL.ordinal(), NORMAL);
		HAIR_TYPES.put(LONG_HAIR.ordinal(), LONG_HAIR);
		HAIR_TYPES.put(HALF_HAIR.ordinal(), HALF_HAIR);
		HAIR_TYPES.put(PIGTAIL.ordinal(), PIGTAIL);
		HAIR_TYPES.put(STRIPE.ordinal(), STRIPE);
	}

	public static HairType valueOf(int id) {
		return HAIR_TYPES.get(id) == null ? HAIRLESS : HAIR_TYPES.get(id);
	}
}
