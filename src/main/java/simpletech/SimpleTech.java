package simpletech;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.ShapedOreRecipe;
import simpletech.block.*;
import simpletech.creativetab.CreativeTabsSimpleTech;
import simpletech.handler.ConfigurationHandler;
import simpletech.handler.EventsHandler;
import simpletech.packet.PacketHandler;
import simpletech.helper.LogHelper;
import simpletech.item.*;
import simpletech.proxy.ClientProxy;
import simpletech.proxy.CommonProxy;
import simpletech.reference.Materials;
import simpletech.reference.Names;
import simpletech.reference.Reference;
import simpletech.tileentity.*;

@Mod(modid = SimpleTech.MOD_ID, name = SimpleTech.MOD_NAME, version = SimpleTech.VERSION, guiFactory = SimpleTech.GUI_FACTORY_CLASS)
public class SimpleTech
{
	public static final String MOD_ID = "simpletech";
	public static final String MOD_NAME = "RPG Skills";
	public static final String VERSION = "1.7.10-1.0";
	public static final String CONFIG_NAME = MOD_NAME;
	public static final String CLIENT_PROXY_CLASS = MOD_ID + ".proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = MOD_ID + ".proxy.CommonProxy";
	public static final String GUI_FACTORY_CLASS = MOD_ID + ".client.GuiFactory";

    @Instance(Reference.MODID)
    private static SimpleTech instance;

    @SidedProxy(clientSide = SimpleTech.CLIENT_PROXY_CLASS, serverSide = SimpleTech.SERVER_PROXY_CLASS, modId = SimpleTech.MOD_ID)
    public static CommonProxy commonProxy;

    public static final PacketHandler packetHandler = new PacketHandler();

    /**
     * Put Network Handling, Mod Configurations, Register Blocks/Items
     */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
        Blocks.registerBlocks();
        Items.registerItems();
        Items.registerRenders();
        LogHelper.info("PreInitialization Completed");
    }

    /**
     * Put GUI, TileEntity, Crafting, Events
     */
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        PacketHandler.registerPackets();
        ClientProxy.registerProxies();
        CommonProxy.registerWorldGeneration();
        MinecraftForge.EVENT_BUS.register(new EventsHandler());
        OreDictionary.registerOreDictionary();
        Blocks.registerTileEntities();
        Blocks.registerRecipes();
        Items.registerRecipes();
        LogHelper.info("Initialization Completed");
    }

    /**
     * Interact with other mods
     */
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        LogHelper.info("PostInitialization Completed");
    }

    @GameRegistry.ObjectHolder(Reference.MODID)
    public static class Blocks
    {
        public static Block block_ore = new BlockOre(Material.rock);
        public static BlockDeadOre block_dead_ore = new BlockDeadOre(Material.rock);
        public static Block block_ingot = new BlockIngot(Material.rock);
        public static Block alloy_furnace_idle = new BlockAlloyFurnace(false).setBlockName(Names.ALLOY_FURNACE_IDLE).setCreativeTab(CreativeTabsSimpleTech.tabsSimpleTechBlocks);
        public static Block alloy_furnace_active = new BlockAlloyFurnace(true).setBlockName(Names.ALLOY_FURNACE_ACTIVE);
        public static Block grinder_idle = new BlockGrinder(false).setBlockName(Names.GRINDER_IDLE).setCreativeTab(CreativeTabsSimpleTech.tabsSimpleTechBlocks);
        public static Block grinder_active = new BlockGrinder(true).setBlockName(Names.GRINDER_ACTIVE);
        public static Block planter = new BlockPlanter(Material.rock);
        //public static Block item_pipe = new BlockItemPipe();

        public static void registerBlocks()
        {
            GameRegistry.registerBlock(block_ore, ItemBlockOre.class, Names.ORE_NAME);
            GameRegistry.registerBlock(block_dead_ore, ItemBlockDeadOre.class, Names.DEAD_ORE_NAME);
            GameRegistry.registerBlock(block_ingot, ItemBlockIngot.class, Names.INGOT_BLOCK);
            GameRegistry.registerBlock(alloy_furnace_idle, Names.ALLOY_FURNACE_IDLE);
            GameRegistry.registerBlock(alloy_furnace_active, Names.ALLOY_FURNACE_ACTIVE);
            GameRegistry.registerBlock(grinder_idle, Names.GRINDER_IDLE);
            GameRegistry.registerBlock(grinder_active, Names.GRINDER_ACTIVE);
            GameRegistry.registerBlock(planter, Names.PLANTER);
            //GameRegistry.registerBlock(item_pipe, Names.ITEM_PIPE);
        }

        public static void registerTileEntities()
        {
            GameRegistry.registerTileEntity(TileEntityAlloyFurnace.class, Reference.MODID + Names.TILE_ALLOY_FURNACE);
            GameRegistry.registerTileEntity(TileEntityGrinder.class, Reference.MODID + Names.TILE_GRINDER);
            GameRegistry.registerTileEntity(TileEntityPlanter.class, Reference.MODID + Names.TILE_PLANTER);
            GameRegistry.registerTileEntity(TileEntityItemPipe.class, Reference.MODID + Names.TILE_PIPE);
            GameRegistry.registerTileEntity(TileEntityDeadOre.class, Reference.MODID + Names.TILE_DEAD_ORE);
        }

        public static void registerRecipes()
        {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(block_ingot, 1, 0), "III", "III", "III", 'I', "ingotAluminum"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(block_ingot, 1, 1), "III", "III", "III", 'I', "ingotBronze"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(block_ingot, 1, 2), "III", "III", "III", 'I', "ingotChromium"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(block_ingot, 1, 3), "III", "III", "III", 'I', "ingotCobalt"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(block_ingot, 1, 4), "III", "III", "III", 'I', "ingotCopper"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(block_ingot, 1, 5), "III", "III", "III", 'I', "ingotImpureTitanium"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(block_ingot, 1, 6), "III", "III", "III", 'I', "ingotLead"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(block_ingot, 1, 7), "III", "III", "III", 'I', "ingotSilver"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(block_ingot, 1, 8), "III", "III", "III", 'I', "ingotTin"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(block_ingot, 1, 9), "III", "III", "III", 'I', "ingotTitanium"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(alloy_furnace_idle),
                            "CCC", "CFC", "BBB",
                            'C', "cobblestone", 'F', new ItemStack(net.minecraft.init.Blocks.furnace, 1), 'B', new ItemStack(net.minecraft.init.Blocks.brick_block))
            );
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(grinder_idle),
                            "CPC", "C C", "SFS",
                            'C', "cobblestone", 'F', new ItemStack(net.minecraft.init.Blocks.furnace, 1), 'S', "stone", 'P', new ItemStack(net.minecraft.init.Blocks.piston))
            );
        }
    }

    @GameRegistry.ObjectHolder(Reference.MODID)
    public static class Items
    {
        public static Item dust = new ItemDust();
        public static Item nugget = new ItemNugget();
        public static Item ingot = new ItemIngot();
        public static Item upgrade = new ItemUpgrade();
        //public static Item backpack = new ItemBackpack(Materials.Armour.BACKPACK, Values.backpackRenderIndex, 1);
        public static Item bronze_sword = new ItemBronzeSword(Materials.Tool.BRONZE);
        public static Item bronze_pickaxe = new ItemBronzePickaxe(Materials.Tool.BRONZE);
        public static Item bronze_axe = new ItemBronzeAxe(Materials.Tool.BRONZE);
        public static Item bronze_shovel = new ItemBronzeShovel(Materials.Tool.BRONZE);
        public static Item bronze_hoe = new ItemBronzeHoe(Materials.Tool.BRONZE);
        public static Item cobalt_sword = new ItemCobaltSword(Materials.Tool.COBALT);
        public static Item cobalt_pickaxe = new ItemCobaltPickaxe(Materials.Tool.COBALT);
        public static Item cobalt_axe = new ItemCobaltAxe(Materials.Tool.COBALT);
        public static Item cobalt_shovel = new ItemCobaltShovel(Materials.Tool.COBALT);
        public static Item cobalt_hoe = new ItemCobaltHoe(Materials.Tool.COBALT);
        public static Item bronze_helmet = new ItemBronzeHelmet(Materials.Armour.BRONZE, 5, 0);
        public static Item bronze_chestplate = new ItemBronzeChestplate(Materials.Armour.BRONZE, 5, 1);
        public static Item bronze_leggings = new ItemBronzeLeggings(Materials.Armour.BRONZE, 5, 2);
        public static Item bronze_boots = new ItemBronzeBoots(Materials.Armour.BRONZE, 5, 3);
        public static Item titanium_helmet = new ItemTitaniumHelmet(Materials.Armour.TITANIUM, 6, 0);
        public static Item titanium_chestplate = new ItemTitaniumChestplate(Materials.Armour.TITANIUM, 6, 1);
        public static Item titanium_leggings = new ItemTitaniumLeggings(Materials.Armour.TITANIUM, 6, 2);
        public static Item titanium_boots = new ItemTitaniumBoots(Materials.Armour.TITANIUM, 6, 3);

        public static void registerItems()
        {
            /** General */
            GameRegistry.registerItem(dust, Names.DUST);
            GameRegistry.registerItem(nugget, Names.NUGGET);
            GameRegistry.registerItem(ingot, Names.INGOT);
            GameRegistry.registerItem(upgrade, Names.UPGRADE);
            //GameRegistry.registerItem(backpack, Names.BACKPACK);
            /** Tools */
            GameRegistry.registerItem(bronze_sword, Names.BRONZE_SWORD);
            GameRegistry.registerItem(bronze_pickaxe, Names.BRONZE_PICKAXE);
            GameRegistry.registerItem(bronze_axe, Names.BRONZE_AXE);
            GameRegistry.registerItem(bronze_shovel, Names.BRONZE_SHOVEL);
            GameRegistry.registerItem(bronze_hoe, Names.BRONZE_HOE);
            GameRegistry.registerItem(cobalt_sword, Names.COBALT_SWORD);
            GameRegistry.registerItem(cobalt_pickaxe, Names.COBALT_PICKAXE);
            GameRegistry.registerItem(cobalt_axe, Names.COBALT_AXE);
            GameRegistry.registerItem(cobalt_shovel, Names.COBALT_SHOVEL);
            GameRegistry.registerItem(cobalt_hoe, Names.COBALT_HOE);
            /** Armour */
            GameRegistry.registerItem(bronze_helmet, Names.BRONZE_HELMET);
            GameRegistry.registerItem(bronze_chestplate, Names.BRONZE_CHESTPLATE);
            GameRegistry.registerItem(bronze_leggings, Names.BRONZE_LEGGINGS);
            GameRegistry.registerItem(bronze_boots, Names.BRONZE_BOOTS);
            GameRegistry.registerItem(titanium_helmet, Names.TITANIUM_HELMET);
            GameRegistry.registerItem(titanium_chestplate, Names.TITANIUM_CHESTPLATE);
            GameRegistry.registerItem(titanium_leggings, Names.TITANIUM_LEGGINGS);
            GameRegistry.registerItem(titanium_boots, Names.TITANIUM_BOOTS);
        }

        public static void registerRecipes()
        {
            /** Shaped Recipes */
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(bronze_sword), " B ", " B ", " S ", 'B', new ItemStack(ingot,
                    1, 1),
                    'S', "stickWood"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(bronze_pickaxe), "BBB", " S ", " S ", 'B',
                    new ItemStack(ingot, 1, 1), 'S', "stickWood"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(bronze_axe), "BB ", "BS ", " S ", 'B', new ItemStack(ingot, 1,
                    1),
                    'S', "stickWood"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(bronze_shovel), "B", "S", "S", 'B', new ItemStack(ingot, 1, 1)
                    , 'S',
                    "stickWood"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(bronze_hoe), "BB ", " S ", " S ", 'B', new ItemStack(ingot, 1,
                    1),
                    'S', "stickWood"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(bronze_helmet), "BBB", "B B", 'B', new ItemStack(ingot, 1, 1)));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(bronze_chestplate), "B B", "BBB", "BBB", 'B', new ItemStack
                    (ingot, 1,
                            1)));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(bronze_leggings), "BBB", "B B", "B B", 'B',
                    new ItemStack(ingot, 1, 1)));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(bronze_boots), "B B", "B B", 'B', new ItemStack(ingot, 1, 1)));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(titanium_helmet), "TTT", "T T", 'T', new ItemStack(ingot, 1,
                    9)));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(titanium_chestplate), "T T", "TTT", "TTT", 'T', new ItemStack
                    (ingot,
                            1, 9)));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(titanium_leggings), "TTT", "T T", "T T", 'T', new ItemStack
                    (ingot, 1,
                            9)));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(titanium_boots), "T T", "T T", 'T', new ItemStack(ingot, 1, 9)));
            /** Nuggets to Ingots */
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ingot, 1, 0), "NNN", "NNN", "NNN", 'N', new ItemStack(nugget,
                    1, 0)));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ingot, 1, 1), "NNN", "NNN", "NNN", 'N', new ItemStack(nugget,
                    1, 1)));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ingot, 1, 2), "NNN", "NNN", "NNN", 'N', new ItemStack(nugget,
                    1, 2)));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ingot, 1, 3), "NNN", "NNN", "NNN", 'N', new ItemStack(nugget,
                    1, 3)));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ingot, 1, 4), "NNN", "NNN", "NNN", 'N', new ItemStack(nugget,
                    1, 4)));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(net.minecraft.init.Items.gold_ingot, 1), "NNN", "NNN", "NNN", 'N', new ItemStack(
                    net.minecraft.init.Items.gold_nugget, 1)));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ingot, 1, 5), "NNN", "NNN", "NNN", 'N', new ItemStack(nugget,
                    1, 5)));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(net.minecraft.init.Items.iron_ingot, 1), "NNN", "NNN", "NNN", 'N', new ItemStack
                    (nugget,
                            1, 6)));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ingot, 1, 6), "NNN", "NNN", "NNN", 'N', new ItemStack(nugget,
                    1, 7)));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ingot, 1, 7), "NNN", "NNN", "NNN", 'N', new ItemStack(nugget,
                    1, 8)));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ingot, 1, 8), "NNN", "NNN", "NNN", 'N', new ItemStack(nugget,
                    1, 9)));
            GameRegistry
                    .addRecipe(new ShapedOreRecipe(new ItemStack(ingot, 1, 9), "NNN", "NNN", "NNN", 'N', new ItemStack(nugget, 1,
                            10)));
            /** Shapeless Recipes */
            /** Furnace Recipes */
            /** Dusts to Ingots */
            GameRegistry.addSmelting(new ItemStack(dust, 1, 0), new ItemStack(ingot, 1, 0), 0.2F);
            GameRegistry.addSmelting(new ItemStack(dust, 1, 1), new ItemStack(ingot, 1, 1), 0.2F);
            GameRegistry.addSmelting(new ItemStack(dust, 1, 2), new ItemStack(ingot, 1, 2), 0.2F);
            GameRegistry.addSmelting(new ItemStack(dust, 1, 3), new ItemStack(ingot, 1, 3), 0.2F);
            GameRegistry.addSmelting(new ItemStack(dust, 1, 4), new ItemStack(ingot, 1, 4), 0.2F);
            GameRegistry.addSmelting(new ItemStack(dust, 1, 5), new ItemStack(net.minecraft.init.Items.gold_ingot, 1), 0.2F);
            GameRegistry.addSmelting(new ItemStack(dust, 1, 6), new ItemStack(ingot, 1, 5), 0.2F);
            GameRegistry.addSmelting(new ItemStack(dust, 1, 7), new ItemStack(net.minecraft.init.Items.iron_ingot, 1), 0.2F);
            GameRegistry.addSmelting(new ItemStack(dust, 1, 8), new ItemStack(ingot, 1, 6), 0.2F);
            GameRegistry.addSmelting(new ItemStack(dust, 1, 9), new ItemStack(ingot, 1, 7), 0.2F);
            GameRegistry.addSmelting(new ItemStack(dust, 1, 10), new ItemStack(ingot, 1, 8), 0.2F);
        }

        public static void registerRenders()
        {
            RenderingRegistry.addNewArmourRendererPrefix("bronze");
            RenderingRegistry.addNewArmourRendererPrefix("titanium");
        }
    }

    public static class OreDictionary
    {
        public static void registerOreDictionary()
        {
            /** Ore */
            net.minecraftforge.oredict.OreDictionary.registerOre("oreAluminum", new ItemStack(Blocks.block_ore, 1, 0));
            net.minecraftforge.oredict.OreDictionary.registerOre("oreChromite", new ItemStack(Blocks.block_ore, 1, 1));
            net.minecraftforge.oredict.OreDictionary.registerOre("oreCobalt", new ItemStack(Blocks.block_ore, 1, 2));
            net.minecraftforge.oredict.OreDictionary.registerOre("oreCopper", new ItemStack(Blocks.block_ore, 1, 3));
            net.minecraftforge.oredict.OreDictionary.registerOre("oreImpureTitanium", new ItemStack(Blocks.block_ore, 1, 4));
            net.minecraftforge.oredict.OreDictionary.registerOre("oreLead", new ItemStack(Blocks.block_ore, 1, 5));
            net.minecraftforge.oredict.OreDictionary.registerOre("oreSilver", new ItemStack(Blocks.block_ore, 1, 6));
            net.minecraftforge.oredict.OreDictionary.registerOre("oreTin", new ItemStack(Blocks.block_ore, 1, 7));
            net.minecraftforge.oredict.OreDictionary.registerOre("oreTitanium", new ItemStack(Blocks.block_ore, 1, 8));
            net.minecraftforge.oredict.OreDictionary.registerOre("oreUranium", new ItemStack(Blocks.block_ore, 1, 9));
            /** Dust */
            net.minecraftforge.oredict.OreDictionary.registerOre("dustAluminum", new ItemStack(SimpleTech.Items.dust, 1, 0));
            net.minecraftforge.oredict.OreDictionary.registerOre("dustBronze", new ItemStack(SimpleTech.Items.dust, 1, 1));
            net.minecraftforge.oredict.OreDictionary.registerOre("dustChromium", new ItemStack(SimpleTech.Items.dust, 1, 2));
            net.minecraftforge.oredict.OreDictionary.registerOre("dustCobalt", new ItemStack(SimpleTech.Items.dust, 1, 3));
            net.minecraftforge.oredict.OreDictionary.registerOre("dustCopper", new ItemStack(SimpleTech.Items.dust, 1, 4));
            net.minecraftforge.oredict.OreDictionary.registerOre("dustGold", new ItemStack(SimpleTech.Items.dust, 1, 5));
            net.minecraftforge.oredict.OreDictionary.registerOre("dustImpureTitanium", new ItemStack(SimpleTech.Items.dust, 1, 6));
            net.minecraftforge.oredict.OreDictionary.registerOre("dustIron", new ItemStack(SimpleTech.Items.dust, 1, 7));
            net.minecraftforge.oredict.OreDictionary.registerOre("dustSilver", new ItemStack(SimpleTech.Items.dust, 1, 8));
            net.minecraftforge.oredict.OreDictionary.registerOre("dustLead", new ItemStack(SimpleTech.Items.dust, 1, 9));
            net.minecraftforge.oredict.OreDictionary.registerOre("dustTin", new ItemStack(SimpleTech.Items.dust, 1, 10));
            net.minecraftforge.oredict.OreDictionary.registerOre("dustTitanium", new ItemStack(SimpleTech.Items.dust, 1, 11));
            /** Ingots */
            net.minecraftforge.oredict.OreDictionary.registerOre("ingotAluminum", new ItemStack(SimpleTech.Items.ingot, 1, 0));
            net.minecraftforge.oredict.OreDictionary.registerOre("ingotBronze", new ItemStack(SimpleTech.Items.ingot, 1, 1));
            net.minecraftforge.oredict.OreDictionary.registerOre("ingotChromium", new ItemStack(SimpleTech.Items.ingot, 1, 2));
            net.minecraftforge.oredict.OreDictionary.registerOre("ingotCobalt", new ItemStack(SimpleTech.Items.ingot, 1, 3));
            net.minecraftforge.oredict.OreDictionary.registerOre("ingotCopper", new ItemStack(SimpleTech.Items.ingot, 1, 4));
            net.minecraftforge.oredict.OreDictionary.registerOre("ingotImpureTitanium", new ItemStack(SimpleTech.Items.ingot, 1, 5));
            net.minecraftforge.oredict.OreDictionary.registerOre("ingotLead", new ItemStack(SimpleTech.Items.ingot, 1, 6));
            net.minecraftforge.oredict.OreDictionary.registerOre("ingotSilver", new ItemStack(SimpleTech.Items.ingot, 1, 7));
            net.minecraftforge.oredict.OreDictionary.registerOre("ingotTin", new ItemStack(SimpleTech.Items.ingot, 1, 8));
            net.minecraftforge.oredict.OreDictionary.registerOre("ingotTitanium", new ItemStack(SimpleTech.Items.ingot, 1, 9));
            net.minecraftforge.oredict.OreDictionary.registerOre("ingotUranium", new ItemStack(SimpleTech.Items.ingot, 1, 10));
        }
    }

	public static SimpleTech instance() { return SimpleTech.instance; }
}