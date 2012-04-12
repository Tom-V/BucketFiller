package net.minecraft.src.forestry.api.apiculture;

import java.util.ArrayList;

public enum EnumHumidity {
	ARID,
	NORMAL,
	DAMP;

	public static ArrayList<Integer> aridBiomeIds = new ArrayList<Integer>();
	public static ArrayList<Integer> dampBiomeIds = new ArrayList<Integer>();
	public static ArrayList<Integer> normalBiomeIds = new ArrayList<Integer>();

	public static ArrayList<Integer> getBiomeIds(EnumHumidity humidity) {
		switch(humidity) {
		case ARID:
			return aridBiomeIds;
		case DAMP:
			return dampBiomeIds;
		case NORMAL:
			return normalBiomeIds;
		default:
			return null;
		}
	}
}
