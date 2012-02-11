/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.minecraft.src.buildcraft.pigalot;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.src.buildcraft.api.APIProxy;

/**
 *
 * @author Administrator
 */
public class PigalotProxie {
    
    public static void displayGUIBucketFiller(EntityPlayer entityplayer,
			TileBucketFiller tile) {
		if (!APIProxy.isClient(APIProxy.getWorld())) {
			ModLoader.getMinecraftInstance().displayGuiScreen(
					new GuiBucketFiller(entityplayer.inventory, tile));
		}
	}
    
}