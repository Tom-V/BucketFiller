package net.minecraft.src.forestry.api.apiculture;

import net.minecraft.src.World;

public interface IBreedingManager {
	IBreedingTracker getTracker(World world);
}
