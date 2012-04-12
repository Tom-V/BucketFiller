package net.minecraft.src.forestry.api.apiculture;


public interface IAlleleFlowers extends IAllele {

	/**
	 * @return FlowerProvider for the bee
	 */
	IFlowerProvider getProvider();

}
