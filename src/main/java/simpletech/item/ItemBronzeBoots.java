package simpletech.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import simpletech.creativetab.CreativeTabsSimpleTech;
import simpletech.reference.Materials;
import simpletech.reference.Names;
import net.minecraft.client.renderer.texture.IIconRegister;

public class ItemBronzeBoots extends ItemBronzeArmour
{
    public ItemBronzeBoots(ArmorMaterial armorMaterial, int renderIndex, int armorType)
    {
        super(Materials.Armour.BRONZE, 5, 3);
        maxStackSize = 1;
        setUnlocalizedName(Names.BRONZE_BOOTS);
        setCreativeTab(CreativeTabsSimpleTech.tabsSimpleTechCombat);
        setTextureName(Names.Textures.bronze_boots);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iIconRegister)
    {
        itemIcon = iIconRegister.registerIcon(Names.Textures.bronze_boots);
    }
}