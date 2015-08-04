package simpletech.item;

import simpletech.reference.Names;
import simpletech.reference.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class ItemNugget extends ItemJATAWithMetadata
{
    public IIcon[] icons = new IIcon[11];
    private String[] names = {"aluminum", "bronze", "chromium", "cobalt", "copper", "impuretitanium", "iron", "lead", "silver",
            "tin", "titanium"};

    public ItemNugget()
    {
        super();
        setUnlocalizedName(Names.NUGGET);
    }

    @Override
    public void registerIcons(IIconRegister register)
    {
        for (int i = 0; i < icons.length; i++)
        {
            icons[i] = register.registerIcon(Reference.MODID + ":" + Names.NUGGET + "_" + names[i]);
        }
    }

    @Override
    public IIcon getIconFromDamage(int meta)
    {
        if (meta > icons.length)
            meta = 0;
        return icons[meta];
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
        for (int i = 0; i < icons.length; i++)
            list.add(new ItemStack(item, 1, i));
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        switch (stack.getItemDamage())
        {
            case 0:
                return getUnlocalizedName() + "_aluminum";
            case 1:
                return getUnlocalizedName() + "_bronze";
            case 2:
                return getUnlocalizedName() + "_chromium";
            case 3:
                return getUnlocalizedName() + "_cobalt";
            case 4:
                return getUnlocalizedName() + "_copper";
            case 5:
                return getUnlocalizedName() + "_impuretitanium";
            case 6:
                return getUnlocalizedName() + "_iron";
            case 7:
                return getUnlocalizedName() + "_lead";
            case 8:
                return getUnlocalizedName() + "_silver";
            case 9:
                return getUnlocalizedName() + "_tin";
            case 10:
                return getUnlocalizedName() + "_titanium";
            default:
                return getUnlocalizedName();
        }
    }
}