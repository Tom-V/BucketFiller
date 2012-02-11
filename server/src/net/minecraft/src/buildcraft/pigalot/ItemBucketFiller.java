/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.minecraft.src.buildcraft.pigalot;

import net.minecraft.src.Block;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

/**
 *
 * @author Alex
 */
public class ItemBucketFiller extends ItemBlock{
    
    public ItemBucketFiller(int i, Block block) {
                super(i);
                setHasSubtypes(true);
        }
        
    @Override
    public int getMetadata(int i) {
            return i;
    }
    
    @Override
	public String func_35407_a(ItemStack itemstack) {
            String name = "";
            switch(itemstack.getItemDamage()) {
            case 0: {
                    name = "BucketFiller";
                    break;
            }
            case 1: {
                    name = "SelfPoweredBucketFiller"; 
                    break;
            }
            case 2: {
                    name = "WaterGenerator";
                    break;
            }
            default: name = "BucketFiller";
            }
            return getItemName() + "." + name;
	}
}
