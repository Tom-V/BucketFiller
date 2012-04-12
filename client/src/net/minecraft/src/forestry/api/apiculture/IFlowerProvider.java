package net.minecraft.src.forestry.api.apiculture;

import net.minecraft.src.World;

public interface IFlowerProvider {
	/**
	 * @param world
	 * @param species Integer representing a species' ordinal matching {@EnumBeeBreed}
	 * @param x
	 * @param y
	 * @param z
	 * @return True if the block at the passed coordinates is a valid flower for the species.
	 */
	public boolean isAcceptedFlower(World world, int species, int x, int y, int z);
	/**
	 * @param world
	 * @param species Integer representing a species' ordinal matching {@EnumBeeBreed}
	 * @param x
	 * @param y
	 * @param z
	 * @return True if a flower was planted.
	 */
	public boolean growFlower(World world, int species, int x, int y, int z);

	/**
	 * @return A descriptive name for the flowers recognized by this provider. Used in tooltips.
	 */
	public String getDescription();
}
