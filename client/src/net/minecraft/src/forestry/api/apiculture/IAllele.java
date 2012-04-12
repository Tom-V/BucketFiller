package net.minecraft.src.forestry.api.apiculture;

/**
 * Should be extended for different jobs. ISpeciesAllele, IBiomeAllele, etc.
 */
public interface IAllele {
	int getId();
	boolean isDominant();
}
