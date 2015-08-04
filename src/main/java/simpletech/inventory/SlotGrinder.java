package simpletech.inventory;

import simpletech.SimpleTech;
import simpletech.tileentity.TileEntityGrinder;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotGrinder extends Slot
{
    public SlotGrinder(IInventory inventory, int id, int x, int y)
    {
        super(inventory, id, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack)
    {
        if (getSlotIndex() == 0 && !TileEntityGrinder.isItemFuel(itemStack))
            return true;
        else if (getSlotIndex() == 1 && TileEntityGrinder.isItemFuel(itemStack))
            return true;
        else if (getSlotIndex() == 2)
            return false;
        else if (getSlotIndex() == 3)
            return false;
        else if (getSlotIndex() == 4 && (itemStack.isItemEqual(new ItemStack(SimpleTech.Items.upgrade)) && itemStack.getItemDamage() == 0))
            return true;
        else if (getSlotIndex() == 5 && (itemStack.isItemEqual(new ItemStack(SimpleTech.Items.upgrade)) && itemStack.getItemDamage() == 1))
            return true;
        else if (getSlotIndex() == 6 && (itemStack.isItemEqual(new ItemStack(SimpleTech.Items.upgrade)) && itemStack.getItemDamage() == 2))
            return true;
        else
            return false;
    }
}
