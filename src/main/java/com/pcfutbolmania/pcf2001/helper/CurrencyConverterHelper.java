package com.pcfutbolmania.pcf2001.helper;

public class CurrencyConverterHelper {

	public static Integer convertEurosToPesetas(Integer euros) {
		Double result = euros * 166.386;
		return result.intValue();
	}

	public static Integer convertPesetasToEuros(Integer pesetas) {
		Double result = pesetas * 0.006;
		return result.intValue();
	}
}
