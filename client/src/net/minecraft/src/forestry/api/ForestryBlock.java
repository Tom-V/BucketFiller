package net.minecraft.src.forestry.api;

import net.minecraft.src.Block;

/**
 * Allows direct access to Forestry's blocks. Will be populated during BaseMod.load().
 *
 * All of this stuff is metadata sensitive which is not reflected here!
 *
 * Make sure to only reference it in ModsLoaded() or later.
 *
 * @author SirSengir
 *
 */
public class ForestryBlock {

	/**
	 * 0 - Humus
	 * 1 - Bog Earth
	 */
	public static Block soil;
	/**
	 * 0 - Apatite Ore
	 * 1 - Copper Ore
	 * 2 - Tin Ore
	 */
	public static Block resources;
	/**
	 * 0 - Legacy
	 * 1 - Forest Hive
	 * 2 - Meadows Hive
	 */
	public static Block beehives;
	/**
	 * Legacy
	 */
	public static Block oreTin;
	/**
	 * All planter type machines
	 */
	public static Block planter;
	/**
	 * 0 - Oak saplings
	 * 1 - Rubber Tree Saplings
	 * 2 - Mushrooms
	 */
	public static Block firsapling;

	public static Block saplings;

	/**
	 * All harvester type machines
	 */
	public static Block harvester;
	/**
	 * 0 - Biogas Engine
	 * 1 - Peat-fired Engine
	 * 2 - Electrical Engine
	 */
	public static Block engine;
	/**
	 * Any machine not covered by other machine blocks
	 */
	public static Block machine;
	/**
	 * 0 - Forester
	 * 1 - Rainmaker
	 * 2 - Automatic Treetap
	 */
	public static Block mill;

}
