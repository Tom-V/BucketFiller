package net.minecraft.src.buildcraft.pigalot;

import net.minecraft.src.mod_jBuildCraft_BucketFiller;

/**
 *
 * @author Pigalot
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
