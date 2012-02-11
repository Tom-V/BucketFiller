/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.minecraft.src.buildcraft.pigalot;

import net.minecraft.src.mod_jBuildCraft_BucketFiller;

/**
 *
 * @author Alex
 */
public class TileSelfPoweredFiller extends TileBucketFiller{
    @Override
    public void updateEntity() {
        powerProvider.energyStored += (mod_jBuildCraft_BucketFiller.fillBucketEnergy / 2);
        if(powerProvider.energyStored > MAX_ENERGY) {
            powerProvider.energyStored = MAX_ENERGY;
        }
        super.updateEntity();
    }
}
