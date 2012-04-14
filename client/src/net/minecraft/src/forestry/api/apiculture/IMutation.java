package net.minecraft.src.forestry.api.apiculture;

import net.minecraft.src.World;

/**
 * Bees can be seeded either as hive drops or as mutation results.
 * 
 * Add mutations to BeeManager.beeMutations
 * 
 * @author SirSengir
 */
public interface IMutation {

	/**
	 * @return first of the alleles implementing IAlleleSpecies required for this mutation.
	 */
	IAllele getAllele0();
	/**
	 * @return second of the alleles implementing IAlleleSpecies required for this mutation.
	 */
	IAllele getAllele1();

	/**
	 * @return Array of {@link IAllele} representing the full default genome of the mutated side. The array _must_ implement this format:
	 *
	 * 		Chromosome				Must implement		Customizable		Note
	 *      -----------				---------------		-------------		------------
	 * 	0 : SPECIES					IAlleleSpecies		X
	 *	1 : SPEED					AlleleFloat
	 *	2 : LIFESPAN				AlleleInteger
	 *	3 : FERTILITY				AlleleInteger
	 *	4 : TEMPERATURE_TOLERANCE	AlleleTolerance
	 *	5 : NOCTURNAL				AlleleBoolean
	 *	6 : (HUMIDITY)				(AlleleHumidity)						Not used. Anything passed into here will be nulled.
	 *	7 : HUMIDITY_TOLERANCE		AlleleTolerance
	 *	8 : TOLERANT_FLYER			AlleleBoolean
	 *	9 : CAVE_DWELLING			AlleleBoolean
	 *	10: FLOWER_PROVIDER			IAlleleFlowers		X
	 *	11: FLOWERING				AlleleInteger
	 *	12: TERRITORY				AlleleArea
	 *	13: EFFECT					IAlleleEffect		X
	 *
	 *  Make sure to return a proper array. Returning an allele of the wrong type will cause cast errors on runtime.
	 *
	 *  Alleles marked as customizable can be populated with your own custom alleles. Make sure to register them correctly in alleleList!
<<<<<<< HEAD
	 *  
	 *  Other alleles must be populated with any matching pre-defined allele. Retrieve those via BeeManager.getAllele
=======
	 *
	 *  Other alleles must be populated with any matching pre-defined allele.
>>>>>>> master
	 *
	 */
	IAllele[] getTemplate();

	/**
	 * This is used in spawning offspring.
	 * @param world
	 * @param biomeid
	 * @param x
	 * @param y
	 * @param z
	 * @param allele0
	 * @param allele1
	 * @return	Chance to fire based on actual location of apiary.
	 */
	int getChance(World world, int biomeid, int x, int y, int z, IAllele allele0, IAllele allele1);

	/**
	 * @return Unmodified base chance for mutation to fire.
	 */
	int getBaseChance();

	/**
	 * @param allele
	 * @return true if the passed allele is one of the alleles participating in this mutation.
	 */
	boolean isPartner(IAllele allele);
	/**
	 * @param allele
	 * @return the other allele which was not passed as argument.
	 */
	IAllele getPartner(IAllele allele);

	/**
	 * @return true if the mutation should not be displayed in the beealyzer.
	 */
	boolean isSecret();

}
