package net.minecraft.src.forestry.api;

import net.minecraft.src.NBTTagCompound;

public interface INBTagable {
	void readFromNBT(NBTTagCompound nbttagcompound);
	void writeToNBT(NBTTagCompound nbttagcompound);
}
