/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.minecraft.src.buildcraft.pigalot;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;

/**
 *
 * @author Administrator
 */
public class SlotBucketFiller extends Slot{
    
    public SlotBucketFiller(EntityPlayer entityplayer, IInventory iinventory, int i, int j, int k)
    {
        super(iinventory, i, j, k);
    }
    
    public boolean isItemValid(ItemStack itemstack)
    {
        return false;
    }
    
}
