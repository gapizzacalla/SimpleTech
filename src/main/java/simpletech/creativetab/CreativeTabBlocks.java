package simpletech.creativetab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import simpletech.SimpleTech;
import simpletech.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabBlocks extends CreativeTabs
{
    public CreativeTabBlocks(int tabIndexID, String tabLabel)
    {
        super(tabIndexID, tabLabel);
    }

    @SideOnly(Side.CLIENT)
    @Override
    /**The itemID for the item to be displayed on the tab*/
    public Item getTabIconItem()
    {
        return Item.getItemFromBlock(SimpleTech.Blocks.block_ingot);
    }

    @Override
    public String getTranslatedTabLabel()
    {
        return Reference.MODNAME + " Blocks";
    }
}