package simpletech.creativetab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import simpletech.SimpleTech;
import simpletech.reference.Reference;

public class CreativeTabTools extends CreativeTabs
{
    public CreativeTabTools(int tabIndexID, String tabLabel)
    {
        super(tabIndexID, tabLabel);
    }

    @SideOnly(Side.CLIENT)
    @Override
    /**The itemID for the item to be displayed on the tab*/
    public Item getTabIconItem()
    {
        return SimpleTech.Items.bronze_pickaxe;
    }

    @Override
    public String getTranslatedTabLabel()
    {
        return Reference.MODNAME + " Tools";
    }
}