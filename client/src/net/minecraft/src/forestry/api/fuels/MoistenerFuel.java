package net.minecraft.src.forestry.api.fuels;

import net.minecraft.src.ItemStack;

public class MoistenerFuel {
	/**
	 * The item to use
	 */
	public final ItemStack item;
	/**
	 * The item that leaves the moistener's working slot (i.e. mouldy wheat, decayed wheat, mulch)
	 */
	public final ItemStack product;
	/**
	 * How much this item contributes to the final product of the moistener (i.e. mycelium)
	 */
	public final int moistenerValue;
	/**
	 * What stage this product represents. Resources with lower stage value will be consumed first.
	 */
	public final int stage;

	public MoistenerFuel(ItemStack item, ItemStack product, int stage, int moistenerValue) {
		this.item = item;
		this.product = product;
		this.stage = stage;
		this.moistenerValue = moistenerValue;
	}
}
