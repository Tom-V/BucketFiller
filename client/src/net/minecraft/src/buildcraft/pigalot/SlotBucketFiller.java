package net.minecraft.src.buildcraft.pigalot;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;

/**
 *
 * @author Pigalot
 */
public class SlotBucketFiller extends Slot{
    
    public SlotBucketFiller(EntityPlayer entityplayer, IInventory iinventory, int i, int j, int k)
    {
        super(iinventory, i, j, k);
    }
    
    @Override
    public boolean isItemValid(ItemStack itemstack)
    {
        return false;
    }
    
}
