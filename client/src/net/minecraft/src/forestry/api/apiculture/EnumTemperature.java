package net.minecraft.src.forestry.api.apiculture;

import java.util.ArrayList;

public enum EnumTemperature {
	NONE,
	ICY,
	COLD,
	NORMAL,
	WARM,
	HOT,
	HELLISH;

	public static ArrayList<Integer> icyBiomeIds = new ArrayList<Integer>();
	public static ArrayList<Integer> coldBiomeIds = new ArrayList<Integer>();
	public static ArrayList<Integer> normalBiomeIds = new ArrayList<Integer>();
	public static ArrayList<Integer> warmBiomeIds = new ArrayList<Integer>();
	public static ArrayList<Integer> hotBiomeIds = new ArrayList<Integer>();
	public static ArrayList<Integer> hellishBiomeIds = new ArrayList<Integer>();

	public static ArrayList<Integer> getBiomeIds(EnumTemperature temperature) {

		switch(temperature) {
		case ICY:
			return icyBiomeIds;
		case COLD:
			return coldBiomeIds;
		case NORMAL:
			return normalBiomeIds;
		case WARM:
			return warmBiomeIds;
		case HOT:
			return hotBiomeIds;
		case HELLISH:
			return hellishBiomeIds;
		default:
			return null;
		}

	}
}
