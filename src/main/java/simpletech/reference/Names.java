package simpletech.reference;

import simpletech.helper.ResourceLocationHelper;
import net.minecraft.util.ResourceLocation;

public final class Names
{
    public static final String RESOURCE_PREFIX = Reference.MODID.toLowerCase() + ":";
    /**
     * Blocks: General
     */
    public static final String ITEM_PIPE = "itempipe";
    public static final String LAND_MINE = "landmine";
    /**
     * Blocks: Ore
     */
    public static final String[] ORE_NAMES = {
            "aluminum", "chromium", "cobalt", "copper", "impuretitanium", "lead", "silver", "tin", "titanium", "uranium"
    };

    public static final String ORE_NAME = "ore";
    public static final String DEAD_ORE_NAME = "deadore";
    /**
     * Blocks: Ingot Blocks
     */
    public static final String INGOT_BLOCK = "ingotblock";
    public static final String[] INGOT_BLOCK_SUBNAMES = {"aluminum", "bronze", "chromium", "cobalt", "copper", "impuretitanium",
            "lead", "silver", "tin", "titanium"};
    /**
     * Blocks: Machines
     */
    public static final String ALLOY_FURNACE = "alloyfurnace";
    public static final String ALLOY_FURNACE_IDLE = "alloyfurnaceidle";
    public static final String ALLOY_FURNACE_ACTIVE = "alloyfurnaceactive";
    public static final String GRINDER = "grinder";
    public static final String GRINDER_IDLE = "grinderidle";
    public static final String GRINDER_ACTIVE = "grinderactive";
    public static final String PLANTER = "planter";
    /**
     * Items
     */
    public static final String DUST = "dust";
    public static final String TINY_DUST = "tinydust";
    public static final String NUGGET = "nugget";
    public static final String INGOT = "ingot";
    public static final String UPGRADE = "upgrade";
    public static final String BACKPACK = "backpack";
    public static final String BAG = "bag";
    /**
     * Items: Tools
     */
    public static final String BRONZE_SWORD = "bronzesword";
    public static final String BRONZE_PICKAXE = "bronzepickaxe";
    public static final String BRONZE_AXE = "bronzeaxe";
    public static final String BRONZE_SHOVEL = "bronzeshovel";
    public static final String BRONZE_HOE = "bronzehoe";
    public static final String COBALT_SWORD = "cobaltsword";
    public static final String COBALT_PICKAXE = "cobaltpickaxe";
    public static final String COBALT_AXE = "cobaltaxe";
    public static final String COBALT_SHOVEL = "cobaltshovel";
    public static final String COBALT_HOE = "cobalthoe";
    /**
     * Items: Armour
     */
    public static final String BRONZE_HELMET = "bronzehelmet";
    public static final String BRONZE_CHESTPLATE = "bronzechestplate";
    public static final String BRONZE_LEGGINGS = "bronzeleggings";
    public static final String BRONZE_BOOTS = "bronzeboots";
    public static final String TITANIUM_HELMET = "titaniumhelmet";
    public static final String TITANIUM_CHESTPLATE = "titaniumchestplate";
    public static final String TITANIUM_LEGGINGS = "titaniumleggings";
    public static final String TITANIUM_BOOTS = "titaniumboots";
    /**
     * TileEntities
     */
    public static final String TILE_PLANTER = "tileplanter";
    public static final String TILE_ALLOY_FURNACE = "tilealloyfurnace";
    public static final String TILE_GRINDER = "tilegrinder";
    public static final String TILE_PIPE = "tilepipe";
    public static final String TILE_DEAD_ORE = "tiledeadore";
    /**
     * Containers
     */
    public static final String CONTAINER_JATA = "container.simpletech";
    public static final String CONTAINER_PLANTER = CONTAINER_JATA + ":" + PLANTER;
    public static final String CONTAINER_ALLOY_FURNACE = CONTAINER_JATA + ":" + ALLOY_FURNACE;
    public static final String CONTAINER_GRINDER = CONTAINER_JATA + ":" + GRINDER;
    /**
     * NBT
     */
    public static final String UUID_MOST_SIG = "UUIDMostSig";
    public static final String UUID_LEAST_SIG = "UUIDLeastSig";
    /**
     * Categories
     */
    public static final String CATEGORY_ORES = "ores";
    public static final String PACKET_CHANNEL = Reference.MODID;

    public static final class Keys
    {
        public static final String CATEGORY = "keys.simpletech.category";
        public static final String CHARGE = "keys.simpletech.charge";
        public static final String DISCHARGE = "keys.simpletech.discharge";
    }

    public static final class Textures
    {
        /**
         * Armour
         */
        public static final String bronze_axe = Names.RESOURCE_PREFIX + "bronze_axe";
        public static final String bronze_boots = Names.RESOURCE_PREFIX + "bronze_boots";
        public static final String bronze_chestplate = Names.RESOURCE_PREFIX + "bronze_chestplate";
        public static final String bronze_helmet = Names.RESOURCE_PREFIX + "bronze_helmet";
        public static final String bronze_hoe = Names.RESOURCE_PREFIX + "bronze_hoe";
        public static final String bronze_leggings = Names.RESOURCE_PREFIX + "bronze_leggings";
        public static final String bronze_pickaxe = Names.RESOURCE_PREFIX + "bronze_pickaxe";
        public static final String bronze_shovel = Names.RESOURCE_PREFIX + "bronze_shovel";
        public static final String bronze_sword = Names.RESOURCE_PREFIX + "bronze_sword";
        public static final String titanium_boots = Names.RESOURCE_PREFIX + "titanium_boots";
        public static final String titanium_chestplate = Names.RESOURCE_PREFIX + "titanium_chestplate";
        public static final String titanium_helmet = Names.RESOURCE_PREFIX + "titanium_helmet";
        public static final String titanium_leggings = Names.RESOURCE_PREFIX + "titanium_leggings";
        /**
         * GUI's
         */
        public static final ResourceLocation gui_alloy_furnace = ResourceLocationHelper.getResourceLocation("textures/gui/container/alloy_furnace.png");
        public static final ResourceLocation gui_grinder = ResourceLocationHelper.getResourceLocation("textures/gui/container/grinder.png");
        public static final ResourceLocation gui_bag = ResourceLocationHelper.getResourceLocation("textures/gui/container/bag.png");
        public static final ResourceLocation gui_planter = ResourceLocationHelper.getResourceLocation("textures/gui/container/planter.png");
        public static final ResourceLocation planter_front = ResourceLocationHelper.getResourceLocation("textures/blocks/planter_front.png");

        /**
         * Renders
         */
        public static final ResourceLocation item_pipe = new ResourceLocation(Reference.MODID, "textures/models/pipe.png");
        public static final String bronze_layer_1 = Names.RESOURCE_PREFIX + "textures/models/armor/bronze_layer_1.png";
        public static final String bronze_layer_2 = Names.RESOURCE_PREFIX + "textures/models/armor/bronze_layer_2.png";
        public static final String titanium_layer_1 = Names.RESOURCE_PREFIX + "textures/models/armor/titanium_layer_1.png";
        public static final String titanium_layer_2 = Names.RESOURCE_PREFIX + "textures/models/armor/titanium_layer_2.png";
    }
}