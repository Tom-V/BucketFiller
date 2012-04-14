package net.minecraft.src.forestry.api.apiculture;

import net.minecraft.src.forestry.api.INBTagable;

/**
 * Other implementations than Forestry's default one are not supported.
 * @author SirSengir
 */
public interface IChromosome extends INBTagable {

	IAllele getPrimaryAllele();
	IAllele getSecondaryAllele();
	IAllele getInactiveAllele();
	IAllele getActiveAllele();

}
