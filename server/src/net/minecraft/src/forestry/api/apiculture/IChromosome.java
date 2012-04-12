package net.minecraft.src.forestry.api.apiculture;

import net.minecraft.src.forestry.api.INBTagable;

public interface IChromosome extends INBTagable {

	IAllele getPrimaryAllele();
	IAllele getSecondaryAllele();
	IAllele getInactiveAllele();
	IAllele getActiveAllele();

}
