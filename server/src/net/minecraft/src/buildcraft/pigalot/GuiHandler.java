package net.minecraft.src.buildcraft.pigalot;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraft.src.mod_jBuildCraft_BucketFiller;
import net.minecraft.src.forge.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {

		if (!world.blockExists(x, y, z))
			return null;

		TileEntity tile = world.getBlockTileEntity(x, y, z);

		if (ID == mod_jBuildCraft_BucketFiller.bucketFillerGuiId) {

			if (tile instanceof TileBucketFiller) {
				return new ContainerBucketFiller(player.inventory,
						(TileBucketFiller) tile);
			}

			return null;
		} else {
			return null;
		}
	}
}
