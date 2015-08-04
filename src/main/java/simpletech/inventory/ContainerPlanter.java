package simpletech.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import simpletech.tileentity.TileEntityPlanter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerPlanter extends Container
{
    private TileEntityPlanter planter;
    private int oldFuel;

    public ContainerPlanter (InventoryPlayer inventoryPlayer, TileEntityPlanter tileEntityPlanter)
    {
        planter = tileEntityPlanter;
        /** Planter Fuel */
        addSlotToContainer(new SlotPlanter(tileEntityPlanter, 0, 152, 62));
        /** Planter Inputs */
        for (int i = 0; i < 3; i++)
            addSlotToContainer(new SlotPlanter(tileEntityPlanter, i + 1, 62 + i * 18, 26));
        for (int i = 0; i < 3; i++)
            addSlotToContainer(new SlotPlanter(tileEntityPlanter, i + 4, 62 + i * 18, 44));
        for (int i = 0; i < 3; i++)
            addSlotToContainer(new SlotPlanter(tileEntityPlanter, i + 7, 62 + i * 18, 62));
        /** Hot Bar */
        for (int i = 0; i < 9; ++i)
            addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        /** Player Inventory(3 rows of 9 slots) */
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer)
    {
        return planter.isUseableByPlayer(entityPlayer);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotID)
    {
        ItemStack itemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotID);
        if (slot != null && slot.getHasStack())
        {
            ItemStack resultStack = slot.getStack();
            itemStack = resultStack.copy();
            if (slotID >= 0 && slotID <= 9)
            {
                if (!this.mergeItemStack(resultStack, 10, 46, false))
                    return null;
                slot.onSlotChange(resultStack, itemStack);
            }else if (slotID >= 10 && slotID <= 46)
            {
                if ((planter.getItemBurnTime(resultStack) > 0 && !this.mergeItemStack(resultStack, 0, 1, false)) ||
                        planter.getItemBurnTime(itemStack) <= 0 && !this.mergeItemStack(resultStack, 1, 10, false))
                    return null;
                slot.onSlotChange(resultStack, itemStack);
            }else if (!this.mergeItemStack(resultStack, 10, 46, false))
                return null;
            if (resultStack.stackSize == 0)
                slot.putStack((ItemStack) null);
            else
                slot.onSlotChanged();
            if (resultStack.stackSize == itemStack.stackSize)
                return null;
            slot.onPickupFromSlot(player, resultStack);
        }
        return null;
    }

    @Override
    public void addCraftingToCrafters(ICrafting crafting)
    {
        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate(this, 0, planter.fuel);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
        super.updateProgressBar(id, data);
        switch (id)
        {
            case 0:
                planter.fuel = data;
        }
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
        for (int i = 0; i < crafters.size(); i++)
        {
            ICrafting crafting = (ICrafting) crafters.get(i);
            if (oldFuel != planter.fuel)
                crafting.sendProgressBarUpdate(this, 0, planter.fuel);
        }
        oldFuel = planter.fuel;
    }
}
