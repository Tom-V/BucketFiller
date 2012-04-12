package net.minecraft.src.forestry.api.fuels;

import java.util.HashMap;


public class FuelManager {
	/**
	 * Add new fuels for the fermenter here (i.e. fertilizer)
	 */
	public static HashMap<Integer, FermenterFuel> fermenterFuel = new HashMap<Integer, FermenterFuel>();
	/**
	 * Add new resources for the moistener here (i.e. wheat)
	 */
	public static HashMap<Integer, MoistenerFuel> moistenerResource = new HashMap<Integer, MoistenerFuel>();
	/**
	 * Add new substrates for the rainmaker here
	 */
	public static HashMap<Integer, RainSubstrate> rainSubstrate = new HashMap<Integer, RainSubstrate>();
	/**
	 * Add new fuels for EngineBronze (= biogas engine) here
	 */
	public static HashMap<Integer, EngineBronzeFuel> bronzeEngineFuel = new HashMap<Integer, EngineBronzeFuel>();
	/**
	 * Add new fuels for EngineCopper (= peat-fired engine) here
	 */
	public static HashMap<Integer, EngineCopperFuel> copperEngineFuel = new HashMap<Integer, EngineCopperFuel>();

}
