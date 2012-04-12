package net.minecraft.src.forestry.api.recipes;

import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class ItemGenericCrate extends Item {

	/**
	 * ItemStack representing the contents of this crate.
	 */
	public ItemStack contained;

	public ItemGenericCrate(int i) {
		this(i, null);
	}

	public ItemGenericCrate(int i, ItemStack contained) {
		super(i);
		this.contained = contained;
		maxStackSize = 64;
	}
}
