package net.minecraft.src.forestry.api;

/**
 * Plugins get loaded at the beginning of Forestry's ModsLoaded() if isAvailable() returns true.
 * @author SirSengir
 */
public interface IPlugin {
	public boolean isAvailable();
	/**
	 * Called during ModsLoaded() if isAvailable() returns true.
	 */
	public void initialize();
}
