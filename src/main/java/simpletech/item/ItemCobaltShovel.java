package simpletech.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import simpletech.SimpleTech;
import simpletech.creativetab.CreativeTabsSimpleTech;
import simpletech.reference.Names;

public class ItemCobaltShovel extends ItemSpade
{
    public ItemCobaltShovel(ToolMaterial toolMaterial)
    {
        super(toolMaterial);
        setUnlocalizedName(Names.COBALT_SHOVEL);
        setCreativeTab(CreativeTabsSimpleTech.tabsSimpleTechTools);
        setTextureName(Names.RESOURCE_PREFIX + Names.COBALT_SHOVEL);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iIconRegister)
    {
        itemIcon = iIconRegister.registerIcon(Names.RESOURCE_PREFIX + "cobalt_shovel");
    }

    @Override
    public boolean getIsRepairable(ItemStack armor, ItemStack itemStack)
    {
        return itemStack == new ItemStack(SimpleTech.Items.ingot, 1, 3);
    }
}