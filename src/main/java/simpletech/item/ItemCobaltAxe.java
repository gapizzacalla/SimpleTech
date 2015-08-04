package simpletech.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import simpletech.SimpleTech;
import simpletech.creativetab.CreativeTabsSimpleTech;
import simpletech.reference.Names;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

public class ItemCobaltAxe extends ItemAxe
{
    public ItemCobaltAxe(ToolMaterial toolMaterial)
    {
        super(toolMaterial);
        setUnlocalizedName(Names.COBALT_AXE);
        setCreativeTab(CreativeTabsSimpleTech.tabsSimpleTechTools);
        setTextureName(Names.RESOURCE_PREFIX + Names.COBALT_AXE);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iIconRegister)
    {
        itemIcon = iIconRegister.registerIcon(Names.RESOURCE_PREFIX + "cobalt_axe");
    }

    @Override
    public boolean getIsRepairable(ItemStack armor, ItemStack itemStack)
    {
        return itemStack == new ItemStack(SimpleTech.Items.ingot, 1, 3);
    }
}