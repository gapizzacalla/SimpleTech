package simpletech.item;

import simpletech.creativetab.CreativeTabsSimpleTech;
import simpletech.inventory.ContainerBackpack;
import simpletech.reference.Names;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class ItemBackpack extends ItemArmor
{
    public ItemBackpack(ArmorMaterial material, int renderIndex, int armourType)
    {
        super(material, renderIndex, armourType);
        setMaxStackSize(1);
        setUnlocalizedName(Names.BACKPACK);
        setHasSubtypes(true);
        setCreativeTab(CreativeTabsSimpleTech.tabsSimpleTechItems);
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

    @Override
    public String getArmorTexture(ItemStack itemStack, Entity entity, int slot, String type)
    {
        return null;
    }

    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
    {
        if (world.isRemote)
            return;
        if (player.openContainer == null || player.openContainer instanceof ContainerPlayer)
            return;
        if (!(itemStack.getItem() instanceof ItemBackpack))
            return;
        if (ContainerBackpack.class.isAssignableFrom(player.openContainer.getClass()))
        {
            ContainerBackpack backpack = (ContainerBackpack) player.openContainer;
            if (backpack.updateState)
            {
                backpack.writeToNBT(itemStack);
                backpack.updateState = false;
            }
        }
    }
}
