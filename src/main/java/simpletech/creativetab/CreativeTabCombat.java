package simpletech.creativetab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import simpletech.SimpleTech;
import simpletech.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabCombat extends CreativeTabs
{
    public CreativeTabCombat(int tabIndexID, String tabLabel)
    {
        super(tabIndexID, tabLabel);
    }

    @SideOnly(Side.CLIENT)
    @Override
    /**The itemID for the item to be displayed on the tab*/
    public Item getTabIconItem()
    {
        return SimpleTech.Items.bronze_sword;
    }

    @Override
    public String getTranslatedTabLabel()
    {
        return Reference.MODNAME + " Combat";
    }
}