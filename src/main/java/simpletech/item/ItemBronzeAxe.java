package simpletech.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import simpletech.SimpleTech;
import simpletech.creativetab.CreativeTabsSimpleTech;
import simpletech.reference.Materials;
import simpletech.reference.Names;

public class ItemBronzeAxe extends ItemAxe
{
    public ItemBronzeAxe(ToolMaterial toolMaterial)
    {
        super(Materials.Tool.BRONZE);
        maxStackSize = 1;
        setUnlocalizedName(Names.BRONZE_AXE);
        setCreativeTab(CreativeTabsSimpleTech.tabsSimpleTechTools);
        setTextureName(Names.Textures.bronze_axe);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iIconRegister)
    {
        itemIcon = iIconRegister.registerIcon(Names.Textures.bronze_axe);
    }

    @Override
    public boolean getIsRepairable(ItemStack armor, ItemStack itemStack)
    {
        return itemStack == new ItemStack(SimpleTech.Items.ingot, 1, 1);
    }
}