/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.minecraft.src.buildcraft.pigalot;

import net.minecraft.src.BlockContainer;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import net.minecraft.src.buildcraft.core.Utils;
import net.minecraft.src.TileEntity;
import net.minecraft.src.forge.ITextureProvider;

/**
 *
 * @author Administrator
 */
public class BlockBucketFiller extends BlockContainer implements ITextureProvider {

    public BlockBucketFiller(int i) {
	super(i, Material.rock);
			
	setHardness(1F);
	// TODO Auto-generated constructor stub
    }
    
    @Override
    public TileEntity getBlockEntity(int md) {
        switch(md) {
            case 0: return new TileBucketFiller();
            case 1: return new TileSelfPoweredFiller();
            case 2: return new TileWaterGenerator();
        } return null;
    }
    
    @Override
    public void onBlockRemoval(World world, int i, int j, int k) {
        Utils.preDestroyBlock(world, i, j, k);
        super.onBlockRemoval(world, i, j, k);
    }
    
    @Override
    public boolean blockActivated(World world, int i, int j, int k,
			EntityPlayer entityplayer){
        switch(world.getBlockMetadata(i,j,k)) {
            case 0:
                TileBucketFiller tile = (TileBucketFiller) world.getBlockTileEntity(i, j, k);
                PigalotProxie.displayGUIBucketFiller(entityplayer, tile);
                break;
            case 1:
                tile = (TileSelfPoweredFiller) world.getBlockTileEntity(i, j, k);
                PigalotProxie.displayGUIBucketFiller(entityplayer, tile);
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public String getTextureFile() {
        return "/net/minecraft/src/buildcraft/pigalot/gui/block_textures.png";
    }
    
    @Override
    public int getBlockTextureFromSideAndMetadata(int i, int j) {
        if(j==0) {
             switch (i) {
             case 0:
                     return 0;
             case 1:
                     return 1; 
             default:
                     return 2;	 
             }
        } else if (j==1) {
            switch (i) {
             case 0:
                     return 3;
             case 1:
                     return 4; 
             default:
                     return 5;	 
             }
        } else {
            switch (i) {
             case 0:
                     return 6;
             case 1:
                     return 7; 
             default:
                     return 8;	 
             }
        }
     }

    @Override
    public TileEntity getBlockEntity() {
        return null;
    }
    
    @Override
    protected int damageDropped(int i) {
        return i;
    }
    
}
