package net.minecraft.src.forestry.api.apiculture;

/**
 * Can be used to garner information on bee breeding and to register new bees.
 * See {@link IBreedingManager}
 *  @author SirSengir
 */
public interface IBreedingTracker {

	void registerQueen(IBee bee);
	int getQueenCount();
	void registerPrincess(IBee bee);
	int getPrincessCount();
	void registerDrone(IBee bee);
	int getDroneCount();

	int getSpeciesBred();
	void registerMutation(IMutation mutation);
	boolean isDiscovered(IMutation mutation);
	boolean isDiscovered(IAlleleSpecies species);

}
