package simpletech.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import simpletech.creativetab.CreativeTabsSimpleTech;
import simpletech.reference.Materials;
import simpletech.reference.Names;
import net.minecraft.client.renderer.texture.IIconRegister;

public class ItemBronzeLeggings extends ItemBronzeArmour
{
    public ItemBronzeLeggings(ArmorMaterial armorMaterial, int renderIndex, int armorType)
    {
        super(Materials.Armour.BRONZE, 5, 2);
        maxStackSize = 1;
        setUnlocalizedName(Names.BRONZE_LEGGINGS);
        setCreativeTab(CreativeTabsSimpleTech.tabsSimpleTechCombat);
        setTextureName(Names.Textures.bronze_leggings);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iIconRegister)
    {
        itemIcon = iIconRegister.registerIcon(Names.Textures.bronze_leggings);
    }
}