package net.minecraft.src.forestry.api.apiculture;

import net.minecraft.src.World;

public interface IBreedingManager {
	/**
	 * @param world
	 * @return {@link IBreedingTracker} associated with the passed world.
	 */
	IBreedingTracker getTracker(World world);
}
