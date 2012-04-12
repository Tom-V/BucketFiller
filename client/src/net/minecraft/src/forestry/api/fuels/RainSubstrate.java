package net.minecraft.src.forestry.api.fuels;

import net.minecraft.src.ItemStack;

public class RainSubstrate {
	/**
	 * Rain substrate capable of activating the rainmaker.
	 */
	public ItemStack item;
	/**
	 * Duration of the rain shower triggered by this substrate in Minecraft ticks.
	 */
	public int duration;
	/**
	 * Speed of activation sequence triggered.
	 */
	public float speed;

	public RainSubstrate(ItemStack item, int duration, float speed) {
		this.item = item;
		this.duration = duration;
		this.speed = speed;
	}
}
