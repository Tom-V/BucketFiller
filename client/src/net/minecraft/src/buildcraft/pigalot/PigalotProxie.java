package net.minecraft.src.buildcraft.pigalot;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.src.buildcraft.api.APIProxy;

/**
 *
 * @author Pigalot
 */
public class PigalotProxie {
    
    public static void displayGUIBucketFiller(EntityPlayer entityplayer,
			TileBucketFiller tile) {
    	if (!APIProxy.isClient(ModLoader.getMinecraftInstance().theWorld)) {
			ModLoader.getMinecraftInstance().displayGuiScreen(
					new GuiBucketFiller(entityplayer.inventory, tile));
		}
	}
    
}
