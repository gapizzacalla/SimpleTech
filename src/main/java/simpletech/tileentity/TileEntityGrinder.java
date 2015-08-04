package simpletech.tileentity;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import simpletech.SimpleTech;
import simpletech.block.BlockGrinder;
import simpletech.item.crafting.GrinderRecipes;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;

public class TileEntityGrinder extends TileEntity implements ISidedInventory
{
    private String localizedName;
    /**
     * The ItemStacks that hold the items currently being used in the furnace.
     */
    private ItemStack[] slots = new ItemStack[7];
    private static final int[] slotInput = new int[]{0}, slotOutput = new int[]{2, 3}, slotFuel = new int[]{1}, slotUpgrade = new int[]{4, 5, 6};
    private static int rSize, rSize2;

    /**
     * @burnTime The number of ticks that the furnace will keep burning.
     * @cookTime The number of ticks that the current item has been cooking for.
     * @currentItemBurnTime The number of ticks that a fresh copy of the currently-burning item would
     * keep the furnace burning for.
     */
    public int burnTime, cookTime, currentItemBurnTime;
    public static double speed = 200, burnTimeScaled = 12, progressScaled = 24;

    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        switch (side)
        {
            case 0:
                return slotOutput;
            case 1:
                return slotInput;
            default:
                return slotFuel;
        }
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemStack, int side)
    {
        return isItemValidForSlot(slot, itemStack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack itemStack, int side)
    {
        if (side != 0)
            return true;
        if (slot != 0)
            return true;
        if (itemStack.getItem() == Items.bucket)
            return true;
        else
            return false;
    }

    @Override
    public int getSizeInventory()
    {
        return slots.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex)
    {
        return slots[slotIndex];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount)
    {
        ItemStack itemStack = getStackInSlot(slot);
        if (itemStack != null)
        {
            if (itemStack.stackSize <= amount)
            {
                setInventorySlotContents(slot, null);
            } else
            {
                itemStack = itemStack.splitStack(amount);
                markDirty();
            }
        }
        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        ItemStack itemStack = getStackInSlot(slot);
        setInventorySlotContents(slot, null);
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack)
    {
        slots[slot] = itemStack;
        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit())
            itemStack.stackSize = getInventoryStackLimit();
    }

    @Override
    public String getInventoryName()
    {
        return hasCustomInventoryName() ? localizedName : "container.grinder";
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return localizedName != null && localizedName.length() > 0;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer)
    {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false : entityPlayer.getDistanceSq((double) xCoord + 0.5D,
                (double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory()
    {

    }

    @Override
    public void closeInventory()
    {

    }

    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack)
    {
        if (slotIndex == 0 && !isItemFuel(itemStack))
            return true;
        else if (slotIndex == 1 && isItemFuel(itemStack))
            return true;
        else if (slotIndex == 2)
            return false;
        else if (slotIndex == 3)
            return false;
        else if (slotIndex == 4 && (itemStack.isItemEqual(new ItemStack(SimpleTech.Items.upgrade)) && itemStack.getItemDamage() == 0))
            return true;
        else if (slotIndex == 5 && (itemStack.isItemEqual(new ItemStack(SimpleTech.Items.upgrade)) && itemStack.getItemDamage() == 1))
            return true;
        else if (slotIndex == 6 && (itemStack.isItemEqual(new ItemStack(SimpleTech.Items.upgrade)) && itemStack.getItemDamage() == 2))
            return true;
        else
            return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTag)
    {
        super.readFromNBT(nbtTag);
        NBTTagList nbtTagList = nbtTag.getTagList("GrinderItems", 10);
        slots = new ItemStack[getSizeInventory()];
        for (int i = 0; i < nbtTagList.tagCount(); ++i)
        {
            NBTTagCompound nbtTagCompound2 = (NBTTagCompound) nbtTagList.getCompoundTagAt(i);
            byte b0 = nbtTagCompound2.getByte("GrinderSlot");
            if (b0 >= 0 && b0 < slots.length)
                slots[b0] = ItemStack.loadItemStackFromNBT(nbtTagCompound2);
        }
        burnTime = nbtTag.getShort("GrinderburnTime");
        cookTime = nbtTag.getShort("GrindercookTime");
        currentItemBurnTime = getItemBurnTime(slots[1]);
        if (nbtTag.hasKey("GrinderCustomName"))
            localizedName = nbtTag.getString("GrinderCustomName");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTag)
    {
        super.writeToNBT(nbtTag);
        nbtTag.setShort("GrinderburnTime", (short) burnTime);
        nbtTag.setShort("GrindercookTime", (short) cookTime);
        NBTTagList nbtTagList = new NBTTagList();
        for (int i = 0; i < slots.length; ++i)
        {
            if (slots[i] != null)
            {
                NBTTagCompound nbtTagCompound2 = new NBTTagCompound();
                nbtTagCompound2.setByte("GrinderSlot", (byte) i);
                slots[i].writeToNBT(nbtTagCompound2);
                nbtTagList.appendTag(nbtTagCompound2);
            }
        }
        nbtTag.setTag("GrinderItems", nbtTagList);
        if (hasCustomInventoryName())
            nbtTag.setString("GrinderCustomName", localizedName);
    }

    public void setGuiDisplayName(String displayName)
    {
        localizedName = displayName;
    }

    private static int getItemBurnTime(ItemStack itemStack)
    {
        if (itemStack != null)
        {
            Item item = itemStack.getItem();
            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
            {
                Block block = Block.getBlockFromItem(item);
                if (block == Blocks.wooden_slab)
                    return 150;
                if (block.getMaterial() == Material.wood)
                    return 300;
                if (block == Blocks.coal_block)
                    return 16000;
            }
            if (item instanceof ItemTool && ((ItemTool) item).getToolMaterialName().equals("WOOD"))
                return 200;
            if (item instanceof ItemSword && ((ItemSword) item).getToolMaterialName().equals("WOOD"))
                return 200;
            if (item instanceof ItemHoe && ((ItemHoe) item).getToolMaterialName().equals("WOOD"))
                return 200;
            if (item == Item.getItemFromBlock(Blocks.sapling))
                return 100;
            if (item == Items.stick)
                return 100;
            if (item == Items.coal)
                return 1600;
            if (item == Items.blaze_rod)
                return 2400;
            if (item == Items.lava_bucket)
                return 20000;
            return GameRegistry.getFuelValue(itemStack);
        }
        return 0;
    }

    public static boolean isItemFuel(ItemStack itemStack)
    {
        return getItemBurnTime(itemStack) > 0 ? true : false;
    }

    public boolean isBurning()
    {
        return burnTime > 0;
    }

    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int i)
    {
        return cookTime * i / (int) speed;
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int i)
    {
        if (currentItemBurnTime == 0)
            currentItemBurnTime = 200;
        return burnTime * i / currentItemBurnTime;
    }

    public void updateEntity()
    {
        boolean flag = burnTime > 0;
        boolean flag1 = false;
        if (burnTime > 0)
            --burnTime;
        if (!worldObj.isRemote)
        {
            if (burnTime != 0 || slots[1] != null && slots[0] != null)
            {
                if (burnTime == 0 && canMake())
                {
                    currentItemBurnTime = burnTime = getItemBurnTime(slots[1]);
                    if (burnTime > 0)
                    {
                        flag1 = true;
                        if (slots[1] != null)
                        {
                            --slots[1].stackSize;

                            if (slots[1].stackSize == 0)
                                slots[1] = slots[1].getItem().getContainerItem(slots[1]);
                        }
                    }
                }
                if (isBurning() && canMake())
                {
                    checkUpgrades();
                    cookTime++;
                    if (cookTime >= (int) speed)
                    {
                        cookTime = 0;
                        make();
                        flag1 = true;
                    }
                } else
                    cookTime = 0;
            }
            if (flag != burnTime > 0)
            {
                flag1 = true;
                BlockGrinder.updateBlockState(burnTime > 0, worldObj, xCoord, yCoord, zCoord);
            }
        }
        if (flag1)
            markDirty();
    }

    private boolean canMake()
    {
        if (slots[0] == null)
            return false;
        else
        {
            ArrayList<ItemStack> outputs = getOutputStack();
            if (outputs == null)
                return false;
            else
            {
                if (outputs.get(0) != null && outputs.get(1) == null)
                {
                    ItemStack output = outputs.get(0);
                    if (slots[2] == null) return true;
                    rSize = slots[2].stackSize + output.stackSize;
                    if (rSize <= getInventoryStackLimit() && rSize <= slots[2].getMaxStackSize())
                    {
                        if (!compareItems(slots[2], output) && (slots[3] == null || (compareItems(slots[3], output)) &&
                                slots[3].stackSize < 64))
                            return true;
                        if (!compareItems(slots[2], output)) return false;
                        if (rSize <= getInventoryStackLimit() && rSize <= slots[2].getMaxStackSize()) return true;
                    }
                    if (slots[3] == null) return true;
                    rSize2 = slots[3].stackSize + output.stackSize;
                    if (rSize2 <= getInventoryStackLimit() && rSize2 <= slots[3].getMaxStackSize())
                    {
                        if (!compareItems(slots[3], output)) return false;
                        if (rSize2 <= getInventoryStackLimit() && rSize2 <= slots[3].getMaxStackSize()) return true;
                    }
                    return false;
                } else if (outputs.get(0) != null && outputs.get(1) != null)
                {
                    ItemStack output1 = outputs.get(0);
                    ItemStack output2 = outputs.get(1);
                    if (slots[2] == null && slots[3] == null) return true;
                    if (slots[2] == null && slots[3] != null && compareItems(slots[3], output2)) return true;
                    if (slots[3] == null && slots[2] != null && compareItems(slots[2], output1)) return true;
                    if (!compareItems(slots[2], output1) || !compareItems(slots[3], output2)) return false;
                    int rSize1 = slots[2].stackSize + output1.stackSize;
                    int rSize2 = slots[3].stackSize + output2.stackSize;
                    return (rSize1 <= getInventoryStackLimit() && rSize1 <= slots[2].getMaxStackSize()) && (rSize2 <=
                            getInventoryStackLimit() && rSize2 <= slots[3].getMaxStackSize());
                } else
                    return false;
            }
        }
    }

    public void make()
    {
        if (canMake())
        {
            ArrayList<ItemStack> outputs = getOutputStack();
            ItemStack output1 = outputs.get(0);
            ItemStack output2 = outputs.get(1);
            if (output1 != null && output2 == null)
            {
                if (slots[2] == null)
                    setInventorySlotContents(2, output1.copy());
                else if (compareItems(slots[2], output1) && rSize <= getInventoryStackLimit())
                    slots[2].stackSize += output1.stackSize;
                else if (slots[3] == null)
                    setInventorySlotContents(3, output1.copy());
                else if (compareItems(slots[3], output1) && rSize2 <= getInventoryStackLimit())
                    slots[3].stackSize += output1.stackSize;
            } else if (output1 != null && output2 != null)
            {
                if (slots[2] == null && slots[3] == null)
                {
                    setInventorySlotContents(2, output1.copy());
                    setInventorySlotContents(3, output2.copy());
                } else if (slots[2] == null && slots[3] != null && compareItems(slots[3], output2))
                {
                    setInventorySlotContents(2, output1.copy());
                    slots[3].stackSize += output2.stackSize;
                } else if (slots[3] == null && slots[2] != null && compareItems(slots[2], output1))
                {
                    setInventorySlotContents(3, output2.copy());
                    slots[2].stackSize += output1.stackSize;
                } else if (compareItems(slots[2], output1) && compareItems(slots[3], output2))
                {
                    slots[2].stackSize += output1.stackSize;
                    slots[3].stackSize += output2.stackSize;
                }
            }
            decrStackSize(0, getDecrSize(0));
        }
    }

    private int getDecrSize(int slot)
    {
        return GrinderRecipes.recipes().getRecipeInputSize(slots[slot]);
    }

    private boolean compareItems(ItemStack itemStack1, ItemStack itemStack2)
    {
        if (itemStack1 == null || itemStack2 == null)
            return false;
        if (itemStack1.isItemEqual(itemStack2))
            return true;
        return false;
    }

    private boolean compareItems(Item item, Item item2)
    {
        if (item == null || item2 == null)
            return false;
        if (item == item2)
            return true;
        return false;
    }

    private ArrayList<ItemStack> getOutputStack()
    {
        ArrayList<ItemStack> outputs = GrinderRecipes.recipes().getResult(slots[0]);
        if (outputs != null)
            return outputs;
        else
            return null;
    }

    private void checkUpgrades()
    {
        if (slots[4] == null)
            speed = 200;
        else if (slots[4].isItemEqual(new ItemStack(SimpleTech.Items.upgrade, 1, 0)))
        {
            switch (slots[4].stackSize)
            {
                case 1:
                    speed = (1 - (15 / 100)) * 200;
                    break;
                case 2:
                    speed = (0.9 - (15 / 100)) * 200;
                    break;
                case 3:
                    speed = (0.8 - (15 / 100)) * 200;
                    break;
                case 4:
                    speed = (0.7 - (15 / 100)) * 200;
                    break;
                default:
                    speed = 200;
            }
        }
    }
}