package simpletech.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import simpletech.SimpleTech;
import simpletech.creativetab.CreativeTabsSimpleTech;
import simpletech.reference.Names;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class ItemCobaltPickaxe extends ItemPickaxe
{
    public ItemCobaltPickaxe(ToolMaterial toolMaterial)
    {
        super(toolMaterial);
        setUnlocalizedName(Names.COBALT_PICKAXE);
        setCreativeTab(CreativeTabsSimpleTech.tabsSimpleTechTools);
        setTextureName(Names.RESOURCE_PREFIX + Names.COBALT_PICKAXE);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iIconRegister)
    {
        itemIcon = iIconRegister.registerIcon(Names.RESOURCE_PREFIX + "cobalt_pickaxe");
    }

    @Override
    public boolean getIsRepairable(ItemStack armor, ItemStack itemStack)
    {
        return itemStack == new ItemStack(SimpleTech.Items.ingot, 1, 3);
    }
}