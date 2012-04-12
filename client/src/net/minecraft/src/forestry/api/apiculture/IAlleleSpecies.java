package net.minecraft.src.forestry.api.apiculture;

import java.util.HashMap;

import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public interface IAlleleSpecies extends IAllele {

	/**
	 * @return Short, human-readable identifier used in tooltips and beealyzer.
	 */
	String getName();

	/**
	 * @return Color to use for the bee's border
	 */
	int getPrimaryColor();
	/**
	 * @return Anything other than 0xffffff will look strange!
	 */
	int getSecondaryColor();

	/**
	 * @return Preferred temperature
	 */
	EnumTemperature getTemperature();
	/**
	 * @return Preferred humidity
	 */
	EnumHumidity getHumidity();
	/**
	 * @return true if the species icon should have a glowing effect.
	 */
	boolean hasEffect();
	/**
	 * @return true if the species should not be displayed in NEI or creative inventory.
	 */
	boolean isSecret();
	/**
	 * @return true to have the species count against the species total.
	 */
	boolean isCounted();

	/// Products, Chance
	HashMap<ItemStack, Integer> getProducts();
	/// Specialty, Chance
	HashMap<ItemStack, Integer> getSpecialty();
	/// Only jubilant bees give their specialty product
	boolean isJubilant(World world, int biomeid, int x, int y, int z);

	/**
	 * Binomial name of the species sans "Apis". Returning "humboldti" will have the species flavour name be "Apis humboldti". Feel free to use fun names or return null.
	 * @return flavour text
	 */
	String getBinomial();
}
