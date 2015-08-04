package simpletech.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ContainerBackpack extends Container
{
    InventoryBackpack backpack;
    public boolean updateState;

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return false;
    }

    public void writeToNBT(ItemStack itemStack)
    {
        if (!itemStack.hasTagCompound())
            itemStack.setTagCompound(new NBTTagCompound());
        backpack.writeToNBT(itemStack.getTagCompound());
    }

    public void readFromNBT()
    {

    }
}
