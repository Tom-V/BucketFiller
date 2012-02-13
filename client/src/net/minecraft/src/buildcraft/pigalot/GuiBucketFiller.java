package net.minecraft.src.buildcraft.pigalot;

import net.minecraft.src.Block;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.forge.ITextureProvider;
import net.minecraft.src.forge.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Pigalot
 */
public class GuiBucketFiller extends GuiContainer{

    
    private TileBucketFiller tileBucketFiller;

    public GuiBucketFiller(InventoryPlayer inventoryplayer, TileBucketFiller tileBucketFiller)
    {
        super(new ContainerBucketFiller(inventoryplayer, tileBucketFiller));
        this.tileBucketFiller = tileBucketFiller;
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        int i2 = mc.renderEngine.getTexture("/net/minecraft/src/buildcraft/pigalot/gui/bucket_filler_gui.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(i2);
        int j1 = (width - xSize) / 2;
        int k = (height - ySize) / 2;
        drawTexturedModalRect(j1, k, 0, 0, xSize, ySize);
        if(tileBucketFiller.getScaledQuantity(58) > 0){
            displayGauge(j1, k, 19, 123,tileBucketFiller.getScaledQuantity(58),tileBucketFiller.getLiquidId());
        }
        int i1 = tileBucketFiller.getCookProgressScaled(24);
        drawTexturedModalRect(j1 + 76, k + 41, 176, 61, i1 + 1, 76);
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer()
    {
        fontRenderer.drawString("Bucket Filler", 60, 6, 0x404040);
        fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 0x404040);
    }
    
    private void displayGauge(int j, int k, int line, int col, int squaled,
			int liquidId) {
        Object o = null;
        int liquidImgIndex = 0;

        if (liquidId < Block.blocksList.length) {
            o = Block.blocksList[liquidId];
            liquidImgIndex = Block.blocksList[liquidId].blockIndexInTexture;
        } else {
            o = Item.itemsList[liquidId];
            liquidImgIndex = Item.itemsList[liquidId].getIconFromDamage(0);
        }

        if (o instanceof ITextureProvider) {
            MinecraftForgeClient.bindTexture(((ITextureProvider) o)
                                .getTextureFile());
        } else {
            MinecraftForgeClient.bindTexture("/terrain.png");
        }

        int imgLine = liquidImgIndex / 16;
        int imgColumn = liquidImgIndex - imgLine * 16;

        int start = 0;

        while (true) {
            int x = 0;

            if (squaled > 16) {
                x = 16;
                squaled -= 16;
            } else {
                x = squaled;
                squaled = 0;
            }

            drawTexturedModalRect(j + col, k + line + 58 - x - start, imgColumn * 16, imgLine * 16, 16, 16 - (16 - x));
            start = start + 16;

            if (x == 0 || squaled == 0) {
                break;
            }
        }

        int i = mc.renderEngine
                        .getTexture("/net/minecraft/src/buildcraft/pigalot/gui/bucket_filler_gui.png");

        mc.renderEngine.bindTexture(i);
        drawTexturedModalRect(j + col, k + line, 176, 0, 16, 60);
    }
}
