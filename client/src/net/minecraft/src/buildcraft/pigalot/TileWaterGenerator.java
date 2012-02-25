package net.minecraft.src.buildcraft.pigalot;

import net.minecraft.src.Block;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.buildcraft.api.*;
import net.minecraft.src.buildcraft.core.IMachine;
import net.minecraft.src.buildcraft.factory.TileMachine;
import net.minecraft.src.mod_jBuildCraft_BucketFiller;

/**
 *
 * @author Pigalot
 */
public class TileWaterGenerator  extends TileMachine
    implements IMachine, IPowerReceptor{
    
    protected PowerProvider powerProvider;
    
    public static int MAX_LIQUID = BuildCraftAPI.BUCKET_VOLUME * 5;
    public static int MAX_ENERGY = mod_jBuildCraft_BucketFiller.waterGeneratorEnergy * 5;
    
    SafeTimeTracker updateNetworkTime = new SafeTimeTracker();
    public @TileNetworkData int liquidquantity = 0;
    public int liquidId = Block.waterStill.blockID;
    
    public TileWaterGenerator() {
        powerProvider = PowerFramework.currentFramework.createPowerProvider();
	powerProvider.configure(0, 1, mod_jBuildCraft_BucketFiller.waterGeneratorEnergy, 10, MAX_ENERGY);
    }
    
    @Override
    public void writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setInteger("liquidquantity", liquidquantity);
        PowerFramework.currentFramework.savePowerProvider(this, nbttagcompound);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        liquidquantity = nbttagcompound.getInteger("liquidquantity");
        PowerFramework.currentFramework.loadPowerProvider(this, nbttagcompound);
        powerProvider.configure(0, 1, MAX_ENERGY, 10, MAX_ENERGY);
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public boolean manageLiquids() {
        return true;
    }

    @Override
    public boolean manageSolids() {
        return false;
    }

    @Override
    public void setPowerProvider(PowerProvider provider) {
        powerProvider = provider;
    }

    @Override
    public PowerProvider getPowerProvider() {
        return powerProvider;
    }

    @Override
    public void updateEntity() {
        if (!APIProxy.isClient(worldObj)) {
            if ((APIProxy.isServerSide()) && (updateNetworkTime.markTimeIfDelay(worldObj, 20L))) {
                sendNetworkUpdate();
            }
        }
        float energyUsed = powerProvider.useEnergy(mod_jBuildCraft_BucketFiller.waterGeneratorEnergy, mod_jBuildCraft_BucketFiller.waterGeneratorEnergy *5, true);
        if(liquidquantity < MAX_LIQUID){
            if(energyUsed >= mod_jBuildCraft_BucketFiller.waterGeneratorEnergy) {
                liquidquantity += BuildCraftAPI.BUCKET_VOLUME * (int)(energyUsed / mod_jBuildCraft_BucketFiller.waterGeneratorEnergy);
                if(liquidquantity > MAX_LIQUID) {
                   liquidquantity = MAX_LIQUID;
                }
            }
        }
        
        if (liquidquantity >= 0) {
            for (int i = 0; i < 6; ++i) {
                Position p = new Position(xCoord, yCoord, zCoord,
                                Orientations.values()[i]);
                p.moveForwards(1);

                TileEntity tile = worldObj.getBlockTileEntity((int) p.x, (int) p.y,
                                (int) p.z);

                if (tile instanceof ILiquidContainer) {
                    liquidquantity -= ((ILiquidContainer) tile).fill(p.orientation.reverse(), liquidquantity, liquidId, true);

                    if (liquidquantity <= 0) {
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void doWork() {
    }

	@Override
	public boolean allowActions() {
		return true;
	}
}
