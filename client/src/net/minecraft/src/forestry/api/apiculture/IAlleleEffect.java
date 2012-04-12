package net.minecraft.src.forestry.api.apiculture;

import net.minecraft.src.World;

public interface IAlleleEffect extends IAllele {
	/**
	 * @return Number of ticks to let pass between calls to doEffect
	 */
	int getThrottle();
	/**
	 * @return true if this effect can combine with the effect on other allele (i.e. run before or after). combination can only occur if both effects are combinable.
	 */
	boolean isCombinable();
	/**
	 * Called by apiaries to cause an effect in the world.
	 * @param genome Genome of the bee queen causing this effect
	 * @param world
	 * @param biomeid
	 * @param x
	 * @param y
	 * @param z
	 */
	void doEffect(IGenome genome, World world, int biomeid, int x, int y, int z);
	/**
	 * @return Short, human-readable identifier used in the beealyzer.
	 */
	String getIdentifier();
}
