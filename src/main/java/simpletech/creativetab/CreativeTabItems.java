package simpletech.creativetab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import simpletech.SimpleTech;
import simpletech.reference.Reference;

public class CreativeTabItems extends CreativeTabs
{
    public CreativeTabItems(int tabIndexID, String tabLabel)
    {
        super(tabIndexID, tabLabel);
    }

    @SideOnly(Side.CLIENT)
    @Override
    /**The itemID for the item to be displayed on the tab*/
    public Item getTabIconItem()
    {
        return new ItemStack(SimpleTech.Items.ingot).getItem();
    }

    @Override
    public String getTranslatedTabLabel()
    {
        return Reference.MODNAME + " Items";
    }
}