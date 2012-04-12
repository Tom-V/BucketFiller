package net.minecraft.src.forestry.api.apiculture;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraft.src.forestry.api.INBTagable;

public interface IBee extends INBTagable {

	boolean analyze();
	void age();
	void mate(IBee drone);
	int[] doEffect(int[] throttle, World world, int biomeid, int x, int y, int z);

	boolean isAlive();
	boolean hasEffect();
	boolean isSecret();
	/**
	 * @return true if the bee may spawn offspring
	 */
	boolean canSpawn();
	int isWorking(World world, int biomeid, int x, int y, int z);
	boolean hasFlower(World world, int biomeid, int x, int y, int z);

	ArrayList<Integer> getSuitableBiomeIds();

	ItemStack[] getProduceList();
	ItemStack[] getSpecialtyList();
	ItemStack[] produceStacks(World world, int biomeid, int x, int y, int z);
	IBee spawnPrincess(World world, int biomeid, int x, int y, int z);
	IBee[] spawnDrones(World world, int biomeid, int x, int y, int z);
	void plantFlowerRandom(World world, int biomeid, int x, int y, int z);

	int getMeta();
	String getDisplayName();
	int getHealth();
	int getMaxHealth();
	void addTooltip(List list);
	boolean isAnalyzed();
	IGenome getGenome();

}
