package simpletech.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import simpletech.creativetab.CreativeTabsSimpleTech;
import simpletech.reference.Materials;
import simpletech.reference.Names;
import net.minecraft.client.renderer.texture.IIconRegister;

public class ItemTitaniumChestplate extends ItemTitaniumArmour
{
    public ItemTitaniumChestplate(ArmorMaterial armorMaterial, int renderIndex, int armorType)
    {
        super(Materials.Armour.TITANIUM, 6, 1);
        maxStackSize = 1;
        setUnlocalizedName(Names.TITANIUM_CHESTPLATE);
        setCreativeTab(CreativeTabsSimpleTech.tabsSimpleTechCombat);
        setTextureName(Names.Textures.titanium_chestplate);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iIconRegister)
    {
        itemIcon = iIconRegister.registerIcon(Names.Textures.titanium_chestplate);
    }
}