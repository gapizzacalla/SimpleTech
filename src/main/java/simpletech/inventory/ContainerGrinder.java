package simpletech.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import simpletech.SimpleTech;
import simpletech.item.crafting.GrinderRecipes;
import simpletech.tileentity.TileEntityGrinder;

public class ContainerGrinder extends Container
{
    private TileEntityGrinder tileEntityGrinder;
    private int lastCookTime, lastBurnTime, lastItemBurnTime;

    public ContainerGrinder(InventoryPlayer inventoryPlayer, TileEntityGrinder tileEntity)
    {
        tileEntityGrinder = tileEntity;
        /** Input */
        addSlotToContainer(new SlotGrinder(tileEntity, 0, 56, 17));
        /** Fuel */
        addSlotToContainer(new SlotGrinder(tileEntity, 1, 56, 53));
        /** Output 1 */
        addSlotToContainer(new SlotFurnace(inventoryPlayer.player, tileEntity, 2, 116, 35));
        /** Output 2 */
        addSlotToContainer(new SlotFurnace(inventoryPlayer.player, tileEntity, 3, 148, 35));
        /** Upgrade Slot 1 */
        addSlotToContainer(new SlotGrinder(tileEntity, 4, 21, 17));
        /** Upgrade Slot 2 */
        addSlotToContainer(new SlotGrinder(tileEntity, 5, 21, 35));
        /** Upgrade Slot 3 */
        addSlotToContainer(new SlotGrinder(tileEntity, 6, 21, 53));
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
        return tileEntityGrinder.isUseableByPlayer(entityPlayer);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotID)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot) inventorySlots.get(slotID);
        if (slot != null && slot.getHasStack())
        {
            ItemStack resultStack = slot.getStack();
            itemstack = resultStack.copy();
            if (slotID == 2 || slotID == 3)
            {
                if (!this.mergeItemStack(resultStack, 7, 43, false))
                {
                    return null;
                }
                slot.onSlotChange(resultStack, itemstack);
            } else if (slotID != 1 && slotID != 0)
            {
                if (GrinderRecipes.recipes().getResult(resultStack) != null)
                {
                    if (!this.mergeItemStack(resultStack, 0, 1, false))
                    {
                        return null;
                    }
                } else if (TileEntityGrinder.isItemFuel(resultStack))
                {
                    if (!this.mergeItemStack(resultStack, 1, 2, false))
                    {
                        return null;
                    } else if (resultStack.getItem() == SimpleTech.Items.upgrade)
                    {
                        if (resultStack.getItemDamage() == 0 && !this.mergeItemStack(resultStack, 4, 4, false))
                        {
                            return null;
                        }
                    }
                } else if (slotID >= 7 && slotID < 34)
                {
                    if (!this.mergeItemStack(resultStack, 34, 43, false))
                        return null;
                } else if (slotID >= 34 && slotID < 43)
                {
                    if (!this.mergeItemStack(resultStack, 7, 34, false))
                        return null;
                }
            } else if (!this.mergeItemStack(resultStack, 7, 43, false))
            {
                return null;
            }
            if (resultStack.stackSize == 0)
            {
                slot.putStack((ItemStack) null);
            } else
            {
                slot.onSlotChanged();
            }
            if (resultStack.stackSize == itemstack.stackSize)
            {
                return null;
            }
            slot.onPickupFromSlot(entityPlayer, resultStack);
        }
        return itemstack;
    }

    @Override
    public void addCraftingToCrafters(ICrafting iCrafting)
    {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, tileEntityGrinder.cookTime);
        iCrafting.sendProgressBarUpdate(this, 1, tileEntityGrinder.burnTime);
        iCrafting.sendProgressBarUpdate(this, 2, tileEntityGrinder.currentItemBurnTime);
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int i, int j)
    {
        if (i == 0)
            tileEntityGrinder.cookTime = j;
        if (i == 1)
            tileEntityGrinder.burnTime = j;
        if (i == 2)
            tileEntityGrinder.currentItemBurnTime = j;
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
        for (int i = 0; i < crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting) crafters.get(i);
            if (lastCookTime != tileEntityGrinder.cookTime)
                icrafting.sendProgressBarUpdate(this, 0, tileEntityGrinder.cookTime);
            if (lastBurnTime != tileEntityGrinder.burnTime)
                icrafting.sendProgressBarUpdate(this, 1, tileEntityGrinder.burnTime);
            if (lastItemBurnTime != tileEntityGrinder.currentItemBurnTime)
                icrafting.sendProgressBarUpdate(this, 2, tileEntityGrinder.currentItemBurnTime);
        }
        lastCookTime = tileEntityGrinder.cookTime;
        lastBurnTime = tileEntityGrinder.burnTime;
        lastItemBurnTime = tileEntityGrinder.currentItemBurnTime;
    }
}