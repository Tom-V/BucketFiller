package net.minecraft.src.forestry.api.apiculture;

import net.minecraft.src.forestry.api.INBTagable;

/**
 * Only the default implementation is supported.
 * @author SirSengir
 *
 */
public interface IGenome extends INBTagable {

	IChromosome[] getChromosomes();

	IAllele getActiveAllele(int chromosome);
	IAllele getInactiveAllele(int chromosome);

	IAlleleSpecies getPrimary();
	IAlleleSpecies getSecondary();

	float getSpeed();

	int getLifespan();
	int getFertility();

	EnumTolerance getToleranceTemp();

	boolean getNocturnal();

	EnumTolerance getToleranceHumid();

	boolean getTolerantFlyer();
	boolean getCaveDwelling();

	IFlowerProvider getFlowerProvider();
	int getFlowering();

	int[] getTerritory();

	IAlleleEffect getEffect();

}
