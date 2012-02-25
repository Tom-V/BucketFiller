package net.minecraft.src;

import java.io.File;
import net.minecraft.src.buildcraft.core.CoreProxy;
import net.minecraft.src.buildcraft.pigalot.*;
import net.minecraft.src.forge.Configuration;
import net.minecraft.src.forge.MinecraftForgeClient;
import net.minecraft.src.forge.Property;

/**
 *
 * @author Pigalot
 */
public class mod_jBuildCraft_BucketFiller extends BaseModMp {
    
    public static mod_jBuildCraft_BucketFiller instance;
    
    public static Block blockBucketFiller;
    
    public static boolean ic2IsInstalled = false;
    public static Item ic2CellEmpty;
    public static Item ic2CellLava;
    public static Item ic2CellWater;
    public static Item ic2CellCoal;
    public static Item ic2CellCoalRef;
    public static Item ic2PartIndustrialDiamond;
    
    public static Configuration BucketFillerConfiguration;
    
    public static int emptyBucketEnergy;
    public static int fillBucketEnergy;
    public static int fillCellEnergy;
    public static int waterGeneratorEnergy;
    
    @Override
    public void ModsLoaded() {	
        super.ModsLoaded();
        BuildCraftCore.initialize();
        
        MinecraftForgeClient.preloadTexture("/net/minecraft/src/buildcraft/pigalot/gui/block_textures.png");
        
        BucketFillerConfiguration = new Configuration(new File(CoreProxy.getBuildCraftBase(), "config/bucketfiller.cfg"));
        BucketFillerConfiguration.load();
        
        Property BucketFillerId = BucketFillerConfiguration.getOrCreateIntProperty("BucketFiller.id",Configuration.BLOCK_PROPERTY, 175);
        Property ic2Diamonds = BucketFillerConfiguration.getOrCreateBooleanProperty("UseIC2Diamonds", Configuration.GENERAL_PROPERTY, false);
        Property emptyBucketEnergyProperty = BucketFillerConfiguration.getOrCreateIntProperty("EmptyBucketEnergy",Configuration.GENERAL_PROPERTY, 20);
        Property fillBucketEnergyProperty = BucketFillerConfiguration.getOrCreateIntProperty("FillBucketEnergy",Configuration.GENERAL_PROPERTY, 25);
        Property fillCellEnergyProperty = BucketFillerConfiguration.getOrCreateIntProperty("FillCellEnergy",Configuration.GENERAL_PROPERTY, 30);
        Property waterGeneratorEnergyProperty = BucketFillerConfiguration.getOrCreateIntProperty("WaterGeneratorEnergy",Configuration.GENERAL_PROPERTY, 7);
        BucketFillerConfiguration.save();
        
        blockBucketFiller = new BlockBucketFiller(Integer.parseInt(BucketFillerId.value)).setBlockName("BucketFiller");
        ModLoader.RegisterBlock(blockBucketFiller);
        
        
        ModLoader.RegisterTileEntity(TileBucketFiller.class,
				"net.minecraft.src.buildcraft.pigalot.TileBucketFiller");
        
        ModLoader.RegisterTileEntity(TileSelfPoweredFiller.class,
				"net.minecraft.src.buildcraft.pigalot.TileSelfPoweredFiller");
        
        ModLoader.RegisterTileEntity(TileWaterGenerator.class,
				"net.minecraft.src.buildcraft.pigalot.TileWaterGenerator");
        
        ModLoader.AddLocalization("tile.BucketFiller.BucketFiller.name", "Bucket Filler");
        ModLoader.AddLocalization("tile.BucketFiller.SelfPoweredBucketFiller.name", "Self Powered Bucket Filler");
        ModLoader.AddLocalization("tile.BucketFiller.WaterGenerator.name", "Water Generator");
        
        Item.itemsList[Integer.parseInt(BucketFillerId.value)] = new ItemBucketFiller(Integer.parseInt(BucketFillerId.value)-256, blockBucketFiller).setItemName("BucketFiller");
        
        emptyBucketEnergy = Integer.parseInt(emptyBucketEnergyProperty.value);
        fillBucketEnergy = Integer.parseInt(fillBucketEnergyProperty.value);
        fillCellEnergy = Integer.parseInt(fillCellEnergyProperty.value);
        waterGeneratorEnergy = Integer.parseInt(waterGeneratorEnergyProperty.value);
        
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
        
        ModLoaderMp.RegisterGUI(this,100);
        
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
            new ItemStack (blockBucketFiller, 1, 1),
            new Object[] { "P ", 
                Character.valueOf('P'),	blockBucketFiller,});
        craftingmanager.addRecipe(
            new ItemStack (blockBucketFiller, 1, 2),
            new Object[] { "P ", 
                Character.valueOf('P'),	BuildCraftFactory.pumpBlock,});
        craftingmanager.addRecipe(
            new ItemStack(Item.bucketEmpty , 1, 0),
            new Object[] { "P  ","P  ", 
                Character.valueOf('P'),	Block.dirt,});*/
        
        //Pipes
        
        //IC2
        try{
            ic2CellEmpty = (Item) Class.forName("mod_IC2").getField("itemCellEmpty").get(null);
            ic2CellLava = (Item) Class.forName("mod_IC2").getField("itemCellLava").get(null);
            ic2CellWater = (Item) Class.forName("mod_IC2").getField("itemCellWater").get(null);
            ic2CellCoalRef = (Item) Class.forName("mod_IC2").getField("itemCellCoal").get(null);
            ic2CellCoal = (Item) Class.forName("mod_IC2").getField("itemCellCoalRef").get(null);
            ic2PartIndustrialDiamond = (Item) Class.forName("mod_IC2").getField("itemPartIndustrialDiamond").get(null);
            if(Boolean.parseBoolean(ic2Diamonds.value)) {
                craftingmanager.addRecipe(
                        new ItemStack(BuildCraftCore.diamondGearItem), 
                        new Object[] {" I ", "IGI", " I ", Character.valueOf('I'), ic2PartIndustrialDiamond,Character.valueOf('G'), BuildCraftCore.goldGearItem });
            }
            ic2IsInstalled = true;
            //System.out.println("IC2 Worked lol");
        }
        catch(ClassNotFoundException e){
            ic2IsInstalled = false;
        }catch(NoSuchFieldException e){
        	ic2IsInstalled = false;
        }catch(SecurityException e){
        	ic2IsInstalled = false;
        }catch(IllegalArgumentException e){
        	ic2IsInstalled = false;
        }catch(IllegalAccessException e){
        	ic2IsInstalled = false;
        } 
        
       // if(!ic2IsInstalled)
            //System.out.println("I haz sad");
        
        instance = this;
    }
    
    @Override
    public String getVersion() {
        return "2.2.12";
    }
    
    @Override
    public GuiScreen HandleGUI(int i) {
        TileBucketFiller tile = new TileBucketFiller();
	switch (i) {
            case 100:
                return new GuiBucketFiller(ModLoader.getMinecraftInstance().thePlayer.inventory, tile);
            default:
                return null;
        }
    }

    @Override
    public String getPriorities() {
        return "after:mod_BuildCraftCore;after:mod_BuildCraftFactory;after:mod_BuildCraftTransport";
    }

    @Override
    public void load() {
    }
}
