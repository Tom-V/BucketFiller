package net.minecraft.src;

import java.io.File;
import net.minecraft.src.buildcraft.core.CoreProxy;
import net.minecraft.src.buildcraft.pigalot.GuiHandler;
import net.minecraft.src.buildcraft.pigalot.BlockBucketFiller;
import net.minecraft.src.buildcraft.pigalot.ItemBucketFiller;
import net.minecraft.src.buildcraft.pigalot.TileBucketFiller;
import net.minecraft.src.buildcraft.pigalot.TileSelfPoweredFiller;
import net.minecraft.src.buildcraft.pigalot.TileWaterGenerator;
import net.minecraft.src.forestry.api.ItemInterface;
import net.minecraft.src.forge.Configuration;
import net.minecraft.src.forge.MinecraftForge;
import net.minecraft.src.forge.NetworkMod;
import net.minecraft.src.forge.Property;
import net.minecraft.src.ic2.api.Items;

/**
 * 
 * @author Pigalot
 */
public class mod_jBuildCraft_BucketFiller extends NetworkMod {

	public static mod_jBuildCraft_BucketFiller instance;

	public static Block blockBucketFiller;

	public static boolean ic2IsInstalled = false;
	public static Item ic2CellEmpty;
	public static Item ic2CellLava;
	public static Item ic2CellWater;
	public static boolean forestryInstalled = false;

	public static Configuration BucketFillerConfiguration;

	public static int emptyBucketEnergy;
	public static int fillBucketEnergy;
	public static int fillCellEnergy;
	public static int fillForestryEnergy;
    public static int waterGeneratorEnergy;
    
    public static int bucketFillerGuiId;
    
    @Override
    public void modsLoaded () {	
        super.modsLoaded();
        BuildCraftCore.initialize();
        BucketFillerConfiguration = new Configuration(new File(CoreProxy.getBuildCraftBase(), "config/bucketfiller.cfg"));
        BucketFillerConfiguration.load();
        Property BucketFillerId = BucketFillerConfiguration.getOrCreateIntProperty("BucketFiller.id", Configuration.CATEGORY_BLOCK, 175);
        Property emptyBucketEnergyProperty = BucketFillerConfiguration.getOrCreateIntProperty("EmptyBucketEnergy", Configuration.CATEGORY_GENERAL, 20);
        Property fillBucketEnergyProperty = BucketFillerConfiguration.getOrCreateIntProperty("FillBucketEnergy", Configuration.CATEGORY_GENERAL, 25);
        Property fillCellEnergyProperty = BucketFillerConfiguration.getOrCreateIntProperty("FillCellEnergy", Configuration.CATEGORY_GENERAL, 30);
        Property fillForestryEnergyProperty = BucketFillerConfiguration.getOrCreateIntProperty("FillForestryEnergy", Configuration.CATEGORY_GENERAL, 30);
        Property waterGeneratorEnergyProperty = BucketFillerConfiguration.getOrCreateIntProperty("WaterGeneratorEnergy", Configuration.CATEGORY_GENERAL, 7);
        Property BucketFillerGuiIdProperty = BucketFillerConfiguration.getOrCreateIntProperty("BucketFillerGuiId", Configuration.CATEGORY_GENERAL, 103);
        BucketFillerConfiguration.save();
        
        blockBucketFiller = new BlockBucketFiller(Integer.parseInt(BucketFillerId.value)).setBlockName("BucketFiller");
        ModLoader.registerBlock(blockBucketFiller);
                       
        ModLoader.registerTileEntity(TileBucketFiller.class,
				"net.minecraft.src.buildcraft.pigalot.TileBucketFiller");
        
        ModLoader.registerTileEntity(TileSelfPoweredFiller.class,
				"net.minecraft.src.buildcraft.pigalot.TileSelfPoweredFiller");
        
        ModLoader.registerTileEntity(TileWaterGenerator.class,
				"net.minecraft.src.buildcraft.pigalot.TileWaterGenerator");
        
        Item.itemsList[Integer.parseInt(BucketFillerId.value)] = new ItemBucketFiller(Integer.parseInt(BucketFillerId.value)-256, blockBucketFiller).setItemName("BucketFiller");
        
        emptyBucketEnergy = Integer.parseInt(emptyBucketEnergyProperty.value);
        fillBucketEnergy = Integer.parseInt(fillBucketEnergyProperty.value);
        fillCellEnergy = Integer.parseInt(fillCellEnergyProperty.value);
		fillForestryEnergy = Integer.parseInt(fillForestryEnergyProperty.value);
        waterGeneratorEnergy = Integer.parseInt(waterGeneratorEnergyProperty.value);
        
        bucketFillerGuiId = Integer.parseInt(BucketFillerGuiIdProperty.value);
        if(bucketFillerGuiId > 127)
        {
        	bucketFillerGuiId = 103;
        }
        
        MinecraftForge.setGuiHandler(this, new GuiHandler());
        
        if(fillCellEnergy > (fillBucketEnergy *4)) {
            fillCellEnergy = (fillBucketEnergy *4);
        }
        
        if(emptyBucketEnergy > (fillBucketEnergy *4)) {
            emptyBucketEnergy = (fillBucketEnergy *4);
        }
        
        if(emptyBucketEnergy < 1) {
            emptyBucketEnergy = 1;
        }
        
        if(fillBucketEnergy < 1) {
            fillBucketEnergy = 1;
        }
        
        if(fillCellEnergy < 1) {
            fillCellEnergy = 1;
        }
        
        if(waterGeneratorEnergy < 1) {
            waterGeneratorEnergy = 1;
        }
        
        CraftingManager craftingmanager = CraftingManager.getInstance();
        craftingmanager.addRecipe(
            new ItemStack(blockBucketFiller , 1, 0),
            new Object[] { " P ", "GTG", " p ", 
                Character.valueOf('P'),	BuildCraftTransport.pipeLiquidsWood,
                Character.valueOf('T'), BuildCraftFactory.tankBlock,
                Character.valueOf('G'), BuildCraftCore.stoneGearItem,
                Character.valueOf('p'), BuildCraftTransport.pipeLiquidsGold});
        
        craftingmanager.addRecipe(
            new ItemStack(blockBucketFiller , 1, 2),
            new Object[] { "BBB", "GPG", "BBB", 
                Character.valueOf('B'),	Item.bucketWater,
                Character.valueOf('P'), BuildCraftFactory.pumpBlock,
                Character.valueOf('G'), BuildCraftCore.diamondGearItem});
        
        if(fillBucketEnergy > 35) {
            //Iron
            craftingmanager.addRecipe(
            new ItemStack (blockBucketFiller, 1, 1),
            new Object[] { "GEG", "EFE", "GEG",
                Character.valueOf('G'), BuildCraftCore.diamondGearItem,
                Character.valueOf('E'),	new ItemStack(BuildCraftEnergy.engineBlock, 1, 2),
                Character.valueOf('F'),	blockBucketFiller});
        } else if(fillBucketEnergy > 10) {
            //Stone
            craftingmanager.addRecipe(
            new ItemStack (blockBucketFiller, 1, 1),
            new Object[] { "GEG", "EFE", "GEG",
                Character.valueOf('G'), BuildCraftCore.goldGearItem,
                Character.valueOf('E'),	new ItemStack(BuildCraftEnergy.engineBlock, 1, 1),
                Character.valueOf('F'),	blockBucketFiller});
        } else {
            //wood
            craftingmanager.addRecipe(
            new ItemStack (blockBucketFiller, 1, 1),
            new Object[] { "GEG", "EFE", "GEG",
                Character.valueOf('G'), BuildCraftCore.ironGearItem,
                Character.valueOf('E'),	new ItemStack(BuildCraftEnergy.engineBlock, 1, 0),
                Character.valueOf('F'),	blockBucketFiller});
        }
        /*craftingmanager.addRecipe(
            new ItemStack(blockBucketFiller , 1, 0),
            new Object[] { "PP ", 
                Character.valueOf('P'),	Block.dirt,});
        craftingmanager.addRecipe(
            new ItemStack(Item.bucketEmpty , 1, 0),
            new Object[] { "P  ","P  ", 
                Character.valueOf('P'),	Block.dirt,});*/
        
        //Pipes
        
        //IC2
        try{
			ic2CellEmpty = getIC2Item("cell");
			ic2CellLava = getIC2Item("lavaCell");
			ic2CellWater = getIC2Item("waterCell");

			ic2IsInstalled = true;
            ModLoader.getLogger().fine("IC2 loaded in BucketFiller");
            
        }
        catch(Exception e){
            ic2IsInstalled = false;
            ModLoader.getLogger().throwing("mod_jBuildCraft_BucketFiller", "modsLoaded",e);
        } 
                
		// Forestry
		ItemStack wrench = ItemInterface.getItem("wrench");//wrench will hopefully never change
		if (wrench == null) {
			ModLoader.getLogger().fine("[BucketFiller] Disabled Forestry-integration");
			forestryInstalled = false;
		} else {
			ModLoader.getLogger().fine("[BucketFiller] Enabled Forestry-integration");
			forestryInstalled = true;

		}
        instance = this;
    }
    
	private Item getIC2Item(String itemName) throws Exception {
		ItemStack stack = Items.getItem(itemName);
		if (stack == null) {
			throw new Exception("[BucketFiller] Couldn't find IC2-item: " + itemName);
		}
		return stack.getItem();
	}

    @Override
    public String getVersion() {
        return "2.2.14";
    }
    
    public String getPriorities() {
        return "after:mod_BuildCraftCore;after:mod_BuildCraftFactory;after:mod_BuildCraftTransport;after:mod_BuildCraftEnergy";
    }

    @Override
    public void load() {
        return;
    }

	@Override
	public boolean clientSideRequired() {
		return true;
	}

	@Override
	public boolean serverSideRequired() {
		return false;
	}

}
