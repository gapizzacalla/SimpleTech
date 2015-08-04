package simpletech.inventory;

import simpletech.SimpleTech;
import simpletech.tileentity.TileEntityAlloyFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotAlloyFurnace extends Slot
{
    public SlotAlloyFurnace(IInventory inventory, int id, int x, int y)
    {
        super(inventory, id, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack)
    {
        if (getSlotIndex() == 0)
            return true;
        else if (getSlotIndex() == 1)
            return true;
        else if (getSlotIndex() == 2)
            return false;
        else if (getSlotIndex() == 3 && TileEntityAlloyFurnace.isItemFuel(itemStack))
            return true;
        else if (getSlotIndex() == 4 && (itemStack.isItemEqual(new ItemStack(SimpleTech.Items.upgrade)) && itemStack.getItemDamage() == 0))
            return true;
        else
            return false;
    }
}