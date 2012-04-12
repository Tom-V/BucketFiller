package net.minecraft.src.ic2.api;

import java.lang.reflect.Array;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

/**
 * Provides access to Compressor, Extractor and Macerator recipes, as well as charge-aware recipes
 * and the Recycler blacklist.
 * 
 * The recipes are only valid after IC2 has been loaded and are metadata and stack size sensitive,
 * for example you can create a recipe to compress 3 wooden planks into 2 sticks.
 */
public final class Ic2Recipes {
	/**
	 * Add a charge-aware shaped crafting recipe.
	 */
	public static void addCraftingRecipe(ItemStack result, Object... args) {
		try {
			Class.forName(getPackage() + ".common.AdvRecipe").getMethod("addAndRegister", ItemStack.class, Array.newInstance(Object.class, 0).getClass()).invoke(null, result, args);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Add a charge-aware shapeless crafting recipe.
	 */
	public static void addShapelessCraftingRecipe(ItemStack result, Object... args) {
		try {
			Class.forName(getPackage() + ".common.AdvShapelessRecipe").getMethod("addAndRegister", ItemStack.class, Array.newInstance(Object.class, 0).getClass()).invoke(null, result, args);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Retrieve the registered Compressor recipes.
	 * 
	 * @return Recipe list as a list of map entries, the key is the input and the value is the output
	 */
	public static List<Map.Entry<ItemStack, ItemStack> > getCompressorRecipes() {
		try {
			return (List<Map.Entry<ItemStack, ItemStack> >) Class.forName(getPackage() + ".common.TileEntityCompressor").getField("recipes").get(null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Add a Compressor recipe.
	 * 
	 * @param input Input
	 * @param output Output
	 */
	public static void addCompressorRecipe(ItemStack input, ItemStack output) {
		getCompressorRecipes().add(new AbstractMap.SimpleEntry<ItemStack, ItemStack>(input, output));
	}
	
	
	/**
	 * Get the Compressor output for an input item.
	 *
	 * @param input input item
	 * @param adjustInput remove the processing requirements from input
	 * @return Output item as an independent stack
	 */
	public static ItemStack getCompressorOutputFor(ItemStack input, boolean adjustInput) {
		return getOutputFor(input, adjustInput, getCompressorRecipes());
	}
	
	/**
	 * Retrieve the registered Extractor recipes.
	 * 
	 * @return Recipe list as a list of map entries, the key is the input and the value is the output
	 */
	public static List<Map.Entry<ItemStack, ItemStack> > getExtractorRecipes() {
		try {
			return (List<Map.Entry<ItemStack, ItemStack> >) Class.forName(getPackage() + ".common.TileEntityExtractor").getField("recipes").get(null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Add a Extractor recipe.
	 * 
	 * @param input Input
	 * @param output Output
	 */
	public static void addExtractorRecipe(ItemStack input, ItemStack output) {
		getExtractorRecipes().add(new AbstractMap.SimpleEntry<ItemStack, ItemStack>(input, output));
	}
	
	
	/**
	 * Get the Extractor output for an input item.
	 *
	 * @param input input item
	 * @param adjustInput remove the processing requirements from input
	 * @return Output item as an independent stack
	 */
	public static ItemStack getExtractorOutputFor(ItemStack input, boolean adjustInput) {
		return getOutputFor(input, adjustInput, getExtractorRecipes());
	}
	
	/**
	 * Retrieve the registered Macerator recipes.
	 * 
	 * @return Recipe list as a list of map entries, the key is the input and the value is the output
	 */
	public static List<Map.Entry<ItemStack, ItemStack> > getMaceratorRecipes() {
		try {
			return (List<Map.Entry<ItemStack, ItemStack> >) Class.forName(getPackage() + ".common.TileEntityMacerator").getField("recipes").get(null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Add a Macerator recipe.
	 * 
	 * @param input Input
	 * @param output Output
	 */
	public static void addMaceratorRecipe(ItemStack input, ItemStack output) {
		getMaceratorRecipes().add(new AbstractMap.SimpleEntry<ItemStack, ItemStack>(input, output));
	}
	
	
	/**
	 * Get the Macerator output for an input item.
	 *
	 * @param input input item
	 * @param adjustInput remove the processing requirements from input
	 * @return Output item as an independent stack
	 */
	public static ItemStack getMaceratorOutputFor(ItemStack input, boolean adjustInput) {
		return getOutputFor(input, adjustInput, getMaceratorRecipes());
	}
	
	
	private static ItemStack getOutputFor(ItemStack input, boolean adjustInput, List<Map.Entry<ItemStack, ItemStack> > recipeList) {
		assert input != null;
		
		for (Map.Entry<ItemStack, ItemStack> entry: recipeList) {
			if (entry.getKey().isItemEqual(input) && input.stackSize >= entry.getKey().stackSize) {
				if (adjustInput) input.stackSize -= entry.getKey().stackSize;
				
				return entry.getValue().copy();
			}
		}
		
		return null;
	}
	
	
	/**
	 * Retrieve the registered Recycler blacklist items.
	 * 
	 * @return Blacklist
	 */
	public static List<ItemStack> getRecyclerBlacklist() {
		try {
			return (List<ItemStack>) Class.forName(getPackage() + ".common.TileEntityRecycler").getField("blacklist").get(null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Add an item to the Recycler blacklist.
	 * 
	 * @param newBlacklistedItem item to add
	 */
	public static void addRecyclerBlacklistItem(ItemStack newBlacklistedItem) {
		getRecyclerBlacklist().add(newBlacklistedItem);
	}
	
	/**
	 * Add an item to the Recycler blacklist.
	 * 
	 * @param newBlacklistedItem item to add
	 */
	public static void addRecyclerBlacklistItem(Item newBlacklistedItem) {
		addRecyclerBlacklistItem(new ItemStack(newBlacklistedItem));
	}
	
	/**
	 * Add a block to the Recycler blacklist.
	 * 
	 * @param newBlacklistedBlock block to add
	 */
	public static void addRecyclerBlacklistItem(Block newBlacklistedBlock) {
		addRecyclerBlacklistItem(new ItemStack(newBlacklistedBlock));
	}
	
	
	/**
	 * Determine if an item is in the Recycler blacklist.
	 * 
	 * @param itemStack item to check
	 * @return Whether the item is blacklisted or not
	 */
	public static boolean isRecyclerInputBlacklisted(ItemStack itemStack) {
		for (ItemStack blackItem: getRecyclerBlacklist()) {
			if (itemStack.isItemEqual(blackItem)) return true;
		}
		
		return false;
	}
	
	/**
	 * Get the base IC2 package name, used internally.
	 * 
	 * @return IC2 package name, if unable to be determined defaults to ic2
	 */
	private static String getPackage() {
		Package pkg = Ic2Recipes.class.getPackage();
		if (pkg != null) return pkg.getName().substring(0, pkg.getName().lastIndexOf('.'));
		else return "ic2";
	}
}

