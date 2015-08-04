package simpletech.inventory;

import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;

import static simpletech.tileentity.TileEntityPlanter.isItemFuel;

public class SlotPlanter extends Slot
{
    public SlotPlanter(IInventory inventory, int ID, int x, int y)
    {
        super(inventory, ID, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack)
    {
        switch (getSlotIndex())
        {
            case 0:
                return isItemFuel(itemStack);
            default:
                if (itemStack != null)
                {
                    Item item = itemStack.getItem();
                    if (item instanceof ItemSeeds || item instanceof ItemSeedFood || item == (Item.getItemFromBlock(Blocks.sapling))) return true;
                }
                return false;
        }
    }
}