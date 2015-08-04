package simpletech.item;

import simpletech.creativetab.CreativeTabsSimpleTech;
import simpletech.reference.Names;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemJATAWithMetadata extends Item
{
    public ItemJATAWithMetadata()
    {
        super();
        maxStackSize = 64;
        setCreativeTab(CreativeTabsSimpleTech.tabsSimpleTechItems);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Names.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", Names.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
