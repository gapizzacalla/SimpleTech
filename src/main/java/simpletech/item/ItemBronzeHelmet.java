package simpletech.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import simpletech.creativetab.CreativeTabsSimpleTech;
import simpletech.reference.Materials;
import simpletech.reference.Names;
import net.minecraft.client.renderer.texture.IIconRegister;

public class ItemBronzeHelmet extends ItemBronzeArmour
{
    public ItemBronzeHelmet(ArmorMaterial armorMaterial, int renderIndex, int armorType)
    {
        super(Materials.Armour.BRONZE, 5, 0);
        maxStackSize = 1;
        setUnlocalizedName(Names.BRONZE_HELMET);
        setCreativeTab(CreativeTabsSimpleTech.tabsSimpleTechCombat);
        setTextureName(Names.Textures.bronze_helmet);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iIconRegister)
    {
        itemIcon = iIconRegister.registerIcon(Names.Textures.bronze_helmet);
    }
}