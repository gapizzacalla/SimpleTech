package simpletech.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import simpletech.SimpleTech;
import simpletech.creativetab.CreativeTabsSimpleTech;
import simpletech.reference.Materials;
import simpletech.reference.Names;

public class ItemBronzeSword extends ItemSword
{
    public ItemBronzeSword(ToolMaterial toolMaterial)
    {
        super(Materials.Tool.BRONZE);
        maxStackSize = 1;
        setUnlocalizedName(Names.BRONZE_SWORD);
        setCreativeTab(CreativeTabsSimpleTech.tabsSimpleTechCombat);
        setTextureName(Names.Textures.bronze_sword);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iIconRegister)
    {
        itemIcon = iIconRegister.registerIcon(Names.Textures.bronze_sword);
    }

    @Override
    public boolean getIsRepairable(ItemStack armor, ItemStack itemStack)
    {
        return itemStack == new ItemStack(SimpleTech.Items.ingot, 1, 1);
    }
}