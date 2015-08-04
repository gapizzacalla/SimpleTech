package simpletech.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import simpletech.reference.Names;
import simpletech.reference.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemUpgrade extends ItemJATAWithMetadata
{
    public IIcon[] icons = new IIcon[1];
    private String[] names = {"speed"};

    public ItemUpgrade()
    {
        super();
        maxStackSize = 4;
        setUnlocalizedName(Names.UPGRADE);
        setHasSubtypes(true);
    }

    @Override
    public void registerIcons(IIconRegister register)
    {
        for (int i = 0; i < icons.length; i++)
        {
            icons[i] = register.registerIcon(Reference.MODID + ":" + Names.UPGRADE + "_" + names[i]);
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
                return getUnlocalizedName() + "_speed";
            default:
                return getUnlocalizedName();
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean bool)
    {
        list.add(EnumChatFormatting.GREEN + "<Press Shift>");
        switch (itemStack.getItemDamage())
        {
            case 0:
                if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
                {
                    list.remove(EnumChatFormatting.GREEN + "<Press Shift>");
                    list.add(EnumChatFormatting.GRAY + "Increase speed of machines");
                    list.add(EnumChatFormatting.GRAY + "by 15% per upgrade!");
                }
                break;
            case 1:
                break;
            case 2:
                break;
        }
    }
}
