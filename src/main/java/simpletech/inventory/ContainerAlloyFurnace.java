package simpletech.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import simpletech.tileentity.TileEntityAlloyFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

public class ContainerAlloyFurnace extends Container
{
    public int lastBurnTime, lastCookTime, lastItemBurnTime, INPUT1 = 0, INPUT2 = 1, OUTPUT = 2, FUEL = 3;
    private TileEntityAlloyFurnace tileEntityAlloyFurnace;

    public ContainerAlloyFurnace(InventoryPlayer inventoryPlayer, TileEntityAlloyFurnace tileEntity)
    {
        tileEntityAlloyFurnace = tileEntity;
        /** Input Slot 1 */
        addSlotToContainer(new SlotAlloyFurnace(tileEntity, INPUT1, 56, 17));
        /** Input Slot 2 */
        addSlotToContainer(new SlotAlloyFurnace(tileEntity, INPUT2, 56, 53));
        /** Output Slot */
        addSlotToContainer(new SlotFurnace(inventoryPlayer.player, tileEntity, OUTPUT, 116, 35));
        /** Fuel Slot */
        addSlotToContainer(new SlotAlloyFurnace(tileEntity, FUEL, 152, 62));
        /** Upgrade Slot 1 */
        addSlotToContainer(new SlotAlloyFurnace(tileEntity, 4, 21, 17));
        /** Upgrade Slot 2 */
        addSlotToContainer(new SlotAlloyFurnace(tileEntity, 5, 21, 35));
        /** Upgrade Slot 3 */
        addSlotToContainer(new SlotAlloyFurnace(tileEntity, 6, 21, 53));
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
        return tileEntityAlloyFurnace.isUseableByPlayer(entityPlayer);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or
     * you will crash when someone does that.
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
    {
        return null;
    }

    @Override
    public void addCraftingToCrafters(ICrafting iCrafting)
    {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, tileEntityAlloyFurnace.cookTime);
        iCrafting.sendProgressBarUpdate(this, 1, tileEntityAlloyFurnace.cookTime);
        iCrafting.sendProgressBarUpdate(this, 2, tileEntityAlloyFurnace.burnTime);
        iCrafting.sendProgressBarUpdate(this, 3, tileEntityAlloyFurnace.currentItemBurnTime);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int slot, int newValue)
    {
        if (slot == 0)
            tileEntityAlloyFurnace.cookTime = newValue;
        if (slot == 2)
            tileEntityAlloyFurnace.burnTime = newValue;
        if (slot == 3)
            tileEntityAlloyFurnace.currentItemBurnTime = newValue;
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
        for (int i = 0; i < crafters.size(); i++)
        {
            ICrafting icrafting = (ICrafting) crafters.get(i);
            if (lastCookTime != tileEntityAlloyFurnace.cookTime)
                icrafting.sendProgressBarUpdate(this, 0, tileEntityAlloyFurnace.cookTime);
            if (lastBurnTime != tileEntityAlloyFurnace.burnTime)
                icrafting.sendProgressBarUpdate(this, 2, tileEntityAlloyFurnace.burnTime);
            if (lastItemBurnTime != tileEntityAlloyFurnace.currentItemBurnTime)
                icrafting.sendProgressBarUpdate(this, 3, tileEntityAlloyFurnace.currentItemBurnTime);
        }
        lastCookTime = tileEntityAlloyFurnace.cookTime;
        lastBurnTime = tileEntityAlloyFurnace.burnTime;
        lastItemBurnTime = tileEntityAlloyFurnace.currentItemBurnTime;
    }
}