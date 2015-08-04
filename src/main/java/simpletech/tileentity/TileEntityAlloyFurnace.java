package simpletech.tileentity;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import simpletech.SimpleTech;
import simpletech.block.BlockAlloyFurnace;
import simpletech.item.crafting.AlloyFurnaceRecipes;
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

public class TileEntityAlloyFurnace extends TileEntity implements ISidedInventory
{
    private String localizedName;

    /**
     * The ItemStacks that hold the items currently being used in the furnace.
     */
    public static ItemStack[] slots = new ItemStack[7];
    private static final int[] slotInput = new int[]{0, 1};
    private static final int[] slotOutput = new int[]{2};
    private static final int[] slotFuel = new int[]{3};
    private static final int[] slotUpgrade = new int[]{4, 5, 6};

    /**
     * The number of ticks that the furnace will keep burning.
     */
    public int burnTime;
    /**
     * The number of ticks that the current item has been cooking for.
     */
    public int cookTime;
    /**
     * The number of ticks that a fresh copy of the currently-burning item would
     * keep the furnace burning for.
     */
    public int currentItemBurnTime;

    public static double speed = 200;
    public static double burnTimeScaled = 12;
    public static double progressScaled = 40;

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
    public boolean canInsertItem(int slot, ItemStack itemStack, int j)
    {
        return isItemValidForSlot(slot, itemStack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack itemStack, int side)
    {
        if (side != 0)
            return true;
        if (slot != 0 || slot != 1)
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
				this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
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
		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
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
    public boolean isItemValidForSlot(int slot, ItemStack itemStack)
    {
        if (slot == 0)
            return true;
        else if (slot == 1)
            return true;
        else if (slot == 2)
            return false;
        else if (slot == 3 && TileEntityAlloyFurnace.isItemFuel(itemStack))
            return true;
        else if (slot == 4 && (itemStack.isItemEqual(new ItemStack(SimpleTech.Items.upgrade)) && itemStack.getItemDamage() == 0))
            return true;
        else
            return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTag)
    {
        super.readFromNBT(nbtTag);
        NBTTagList nbtTagList = nbtTag.getTagList("AlloyFurnaceItems", 10);
        slots = new ItemStack[getSizeInventory()];
        for (int i = 0; i < nbtTagList.tagCount(); ++i)
        {
            NBTTagCompound nbtTagCompound2 = (NBTTagCompound) nbtTagList.getCompoundTagAt(i);
            byte b0 = nbtTagCompound2.getByte("AlloyFurnaceSlot");
            if (b0 >= 0 && b0 < slots.length)
                slots[b0] = ItemStack.loadItemStackFromNBT(nbtTagCompound2);
        }
        burnTime = nbtTag.getShort("AlloyFurnaceburnTime");
        cookTime = nbtTag.getShort("AlloyFurnacecookTime");
        currentItemBurnTime = getItemBurnTime(slots[1]);
        if (nbtTag.hasKey("AlloyFurnaceCustomName"))
            localizedName = nbtTag.getString("AlloyFurnaceCustomName");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTag)
    {
        super.writeToNBT(nbtTag);
        nbtTag.setShort("AlloyFurnaceburnTime", (short) burnTime);
        nbtTag.setShort("AlloyFurnacecookTime", (short) cookTime);
        NBTTagList nbtTagList = new NBTTagList();
        for (int i = 0; i < slots.length; ++i)
        {
            if (slots[i] != null)
            {
                NBTTagCompound nbtTagCompound2 = new NBTTagCompound();
                nbtTagCompound2.setByte("AlloyFurnaceSlot", (byte) i);
                slots[i].writeToNBT(nbtTagCompound2);
                nbtTagList.appendTag(nbtTagCompound2);
            }
        }
        nbtTag.setTag("AlloyFurnaceItems", nbtTagList);
        if (hasCustomInventoryName())
            nbtTag.setString("AlloyFurnaceCustomName", localizedName);
    }

    public void setGuiDisplayName(String displayName)
    {
        localizedName = displayName;
    }

    private static int getItemBurnTime(ItemStack itemStack)
    {
        if (itemStack == null)
            return 0;
        else
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
            if (burnTime != 0 || slots[3] != null && slots[0] != null && slots[1] != null)
            {
                if (burnTime == 0 && canMake())
                {
                    currentItemBurnTime = burnTime = getItemBurnTime(slots[3]);
                    if (burnTime > 0)
                    {
                        flag1 = true;
                        if (slots[3] != null)
                        {
                            --slots[3].stackSize;
                            if (slots[3].stackSize == 0)
                                slots[3] = slots[3].getItem().getContainerItem(slots[3]);
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
                BlockAlloyFurnace.updateBlockState(burnTime > 0, worldObj, xCoord, yCoord, zCoord);
            }
        }
        if (flag1)
		{
			markDirty();
			this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
		}
    }

    private boolean canMake()
    {
        if (slots[0] == null || slots[1] == null)
            return false;
        else
        {
            ItemStack output = getOutputStack();
            if (output == null)
                return false;
            else
            {
                if (compareItems(slots[0], slots[1]))
                    return false;
                if (slots[0].stackSize - getDecrSize(0) < 0 || slots[1].stackSize - getDecrSize(1) < 0) return false;
                if (slots[2] == null) return true;
                if (!compareItems(slots[2], output)) return false;
                int result = slots[2].stackSize + output.stackSize;
                return result <= getInventoryStackLimit() && result <= slots[2].getMaxStackSize();
            }
        }
    }

    public void make()
    {
        if (canMake())
        {
            ItemStack output = getOutputStack();
            if (slots[2] == null)
                setInventorySlotContents(2, output.copy());
            else if (compareItems(slots[2].getItem(), output.getItem()))
                slots[2].stackSize += output.stackSize;

            decrStackSize(0, getDecrSize(0));
            decrStackSize(1, getDecrSize(1));
        }
    }

    public static ItemStack getOutputStack()
    {
        ArrayList<ItemStack> inputs1 = new ArrayList<ItemStack>();
        inputs1.add(slots[0]);
        inputs1.add(slots[1]);
        ItemStack output1 = AlloyFurnaceRecipes.recipes().getResult(inputs1);

        ArrayList<ItemStack> inputs2 = new ArrayList<ItemStack>();
        inputs2.add(slots[1]);
        inputs2.add(slots[0]);
        ItemStack output2 = AlloyFurnaceRecipes.recipes().getResult(inputs2);

        if (output1 != null && output2 != null)
            return output1;
        else
            return null;
    }

    private int getDecrSize(int slot)
    {
        ArrayList<ItemStack> inputs = new ArrayList<ItemStack>();
        inputs.add(slots[0]);
        inputs.add(slots[1]);
        int[] sizes = AlloyFurnaceRecipes.recipes().getRecipeInputSizes(inputs);
        return sizes[slot];
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

    private void checkUpgrades()
    {
        /*
        if (slots[4] == null)
            speed = 200;
        else if (slots[4].isItemEqual(new ItemStack(ModItems.upgrade, 1, 0)))
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
        }*/
    }
}