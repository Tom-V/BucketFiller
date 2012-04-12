package net.minecraft.src.forestry.api.apiculture;

import net.minecraft.src.World;

public interface IHiveDrop {
	/**
	 * See {@link IMutation} for template format.
	 * @return
	 */
	public IAllele[] getTemplate();
	/**
	 * Chance to drop. Default drops have 80 (= 80 %).
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public int getChance(World world, int x, int y, int z);
}
