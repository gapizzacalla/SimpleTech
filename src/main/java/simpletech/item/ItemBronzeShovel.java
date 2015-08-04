package simpletech.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import simpletech.SimpleTech;
import simpletech.creativetab.CreativeTabsSimpleTech;
import simpletech.reference.Materials;
import simpletech.reference.Names;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

public class ItemBronzeShovel extends ItemSpade
{
    public ItemBronzeShovel(ToolMaterial toolMaterial)
    {
        super(Materials.Tool.BRONZE);
        maxStackSize = 1;
        setUnlocalizedName(Names.BRONZE_SHOVEL);
        setCreativeTab(CreativeTabsSimpleTech.tabsSimpleTechTools);
        setTextureName(Names.Textures.bronze_shovel);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iIconRegister)
    {
        itemIcon = iIconRegister.registerIcon(Names.Textures.bronze_shovel);
    }

    @Override
    public boolean getIsRepairable(ItemStack armor, ItemStack itemStack)
    {
        return itemStack == new ItemStack(SimpleTech.Items.ingot, 1, 1);
    }
}