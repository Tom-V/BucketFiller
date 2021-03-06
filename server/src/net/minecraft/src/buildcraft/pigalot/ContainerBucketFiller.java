/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.minecraft.src.buildcraft.pigalot;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ICrafting;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.Slot;
import net.minecraft.src.buildcraft.core.BuildCraftContainer;

/**
 *
 * @author Administrator
 */
public class ContainerBucketFiller extends BuildCraftContainer{
    
    private TileBucketFiller tileBucketFiller;
    
    public ContainerBucketFiller(InventoryPlayer inventoryplayer, TileBucketFiller tileBucketFiller)
    {
        super(tileBucketFiller.getSizeInventory());
        this.tileBucketFiller = tileBucketFiller;
        addSlot(new Slot(tileBucketFiller, 0, 56, 35));
        addSlot(new SlotBucketFiller(inventoryplayer.player, tileBucketFiller, 1, 116, 35));
        for(int i = 0; i < 3; i++)
        {
            for(int k = 0; k < 9; k++)
            {
                addSlot(new Slot(inventoryplayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
            }

        }

        for(int j = 0; j < 9; j++)
        {
            addSlot(new Slot(inventoryplayer, j, 8 + j * 18, 142));
        }

    }
    
    public boolean isUsableByPlayer(EntityPlayer entityplayer)
    {
        return tileBucketFiller.isUseableByPlayer(entityplayer);
    }
    
    @Override
    public void updateCraftingResults() {
        super.updateCraftingResults();

        for(int i = 0; i < crafters.size(); i++) {
        	tileBucketFiller.sendGUINetworkData (this, (ICrafting)crafters.get(i));
          
      }
    }  

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return isUsableByPlayer(entityplayer);
	}  
}
