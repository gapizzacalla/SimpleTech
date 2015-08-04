package simpletech.tileentity;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityPlanter extends TileEntity implements ISidedInventory
{
    private static String localizedName;
    private static final int[] slotFuel = new int[]{0}, slotInputs = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    private ItemStack[] slots = new ItemStack[10];
    public int capacity = 50000, fuel = 0, cost = 1000, ticks = 0;

    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        switch (side)
        {
            case 0:
                return slotFuel;
            default:
                return slotInputs;
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
        return false;
    }

    @Override
    public int getSizeInventory()
    {
        return slots.length;
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return slots[index];
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
        markDirty();
    }

    @Override
    public String getInventoryName()
    {
        return hasCustomInventoryName() ? localizedName : "container.planter";
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
        switch (slot)
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

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        this.fuel = nbtTagCompound.getInteger("fuel");
        this.ticks = nbtTagCompound.getInteger("ticks");
        NBTTagList nbtTagList = nbtTagCompound.getTagList("items", 10);
        this.slots = new ItemStack[getSizeInventory()];
        for (int i = 0; i < nbtTagList.tagCount(); i++)
        {
            NBTTagCompound nbtTagCompound2 = nbtTagList.getCompoundTagAt(i);
            int item = nbtTagCompound2.getInteger("slot");
            if (item >= 0 && item < this.slots.length)
                this.slots[item] = ItemStack.loadItemStackFromNBT(nbtTagCompound2);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setInteger("fuel", this.fuel);
        nbtTagCompound.setInteger("ticks", this.ticks);
        NBTTagList nbtTagList = new NBTTagList();
        for (int i = 0; i < this.slots.length; i++)
        {
            if (this.slots[i] != null)
            {
                NBTTagCompound nbtTagCompund1 = new NBTTagCompound();
                nbtTagCompund1.setInteger("slot", i);
                this.slots[i].writeToNBT(nbtTagCompund1);
                nbtTagList.appendTag(nbtTagCompund1);
            }
        }
        nbtTagCompound.setTag("items", nbtTagList);
    }

    @Override
    public void updateEntity()
    {
        if (!worldObj.isRemote)
        {
            ticks++;
            updateFuelAmount();
            if (ticks == 40)
            {
                if (!hasRedstoneSignal() && hasFuel())
                    plant();
                ticks = 0;
            }
        }
        markDirty();
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbtTag = new NBTTagCompound();
        writeToNBT(nbtTag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet)
    {
        readFromNBT(packet.func_148857_g());
    }

    public void setGuiDisplayName(String displayName)
    {
        localizedName = displayName;
    }

    public int getBlockMetadata()
    {
        return blockMetadata;
    }

    public static int getItemBurnTime(ItemStack itemStack)
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
        return getItemBurnTime(itemStack) > 0;
    }

    public void setFuelAmount(int amount)
    {
        this.fuel = amount;
    }

    public int getFuelAmount()
    {
        return this.fuel;
    }

    public boolean hasFuel()
    {
        if ((this.fuel - cost) >= 0)
            return true;
        else
            return false;
    }

    public void updateFuelAmount()
    {
        if (this.fuel > capacity)
        {
            this.fuel = capacity;
            return;
        }
        if (this.fuel < 0)
        {
            this.fuel = 0;
            return;
        }
        ItemStack itemStack = getStackInSlot(0);
        if (itemStack != null && isItemFuel(itemStack))
        {
            int burnAmount = getItemBurnTime(itemStack);
            int newFuel = this.fuel + burnAmount;
            if (newFuel <= capacity)
            {
                decrStackSize(0, 1);
                setFuelAmount(this.fuel += burnAmount);
            }
        }
    }

    private boolean hasRedstoneSignal()
    {
        return worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
    }

    private boolean isFarmland(World world, int x, int y, int z)
    {
        return world.getBlock(x, y - 1, z) instanceof BlockFarmland && world.getBlock(x, y, z).isAir(world, x, y, z);
    }

    private boolean isAir(String string)
    {
        if (string.equals(Blocks.air.getUnlocalizedName()))
            return true;
        return false;
    }
    private Block getBlockFromItemStack(ItemStack itemStack)
    {
        if (itemStack != null)
        {
            String name = itemStack.getUnlocalizedName();
            if (name.equals("item.carrots"))
                return Blocks.carrots;
            if (name.equals("item.potato"))
                return Blocks.potatoes;
            if (name.equals("item.seeds"))
                return Blocks.wheat;
        }
        return Blocks.air;
    }

    private void setBlock(World world, int x, int y, int z, int slot)
    {
        ItemStack itemStack = getStackInSlot(slot);
        Block block = getBlockFromItemStack(itemStack);
        if (itemStack != null && !isAir(block.getUnlocalizedName()) && isFarmland(world, x, y, z))
        {
            setFuelAmount(this.fuel - this.cost);
            decrStackSize(slot, 1);
            world.setBlock(x, y, z, block);
        }
    }

    private void plant()
    {
        int[][][] coords = new int[][][] {
                {
                        //Block Metadata = 2
                        {xCoord + 1, yCoord, zCoord - 1}, {xCoord, yCoord, zCoord - 1}, {xCoord - 1, yCoord, zCoord - 1},
                        {xCoord + 1, yCoord, zCoord - 2}, {xCoord, yCoord, zCoord - 2}, {xCoord - 1, yCoord, zCoord - 2},
                        {xCoord + 1, yCoord, zCoord - 3}, {xCoord, yCoord, zCoord - 3}, {xCoord - 1, yCoord, zCoord - 3}},
                {
                        //Block Metadata = 3
                        {xCoord - 1, yCoord, zCoord + 1}, {xCoord, yCoord, zCoord + 1}, {xCoord + 1, yCoord, zCoord + 1},
                        {xCoord - 1, yCoord, zCoord + 2}, {xCoord, yCoord, zCoord + 2}, {xCoord + 1, yCoord, zCoord + 2},
                        {xCoord - 1, yCoord, zCoord + 3}, {xCoord, yCoord, zCoord + 3}, {xCoord + 1, yCoord, zCoord + 3}},
                {
                        //Block Metadata = 4
                        {xCoord - 1, yCoord, zCoord + 1}, {xCoord - 1, yCoord, zCoord}, {xCoord - 1, yCoord, zCoord - 1},
                        {xCoord - 2, yCoord, zCoord + 1}, {xCoord - 2, yCoord, zCoord}, {xCoord - 2, yCoord, zCoord - 1},
                        {xCoord - 3, yCoord, zCoord + 1}, {xCoord - 3, yCoord, zCoord}, {xCoord - 3, yCoord, zCoord - 1}},
                {
                        //Block Metadata = 5
                        {xCoord + 1, yCoord, zCoord - 1}, {xCoord + 1, yCoord, zCoord}, {xCoord + 1, yCoord, zCoord + 1},
                        {xCoord + 2, yCoord, zCoord - 1}, {xCoord + 2, yCoord, zCoord}, {xCoord + 2, yCoord, zCoord + 1},
                        {xCoord + 3, yCoord, zCoord - 1}, {xCoord + 3, yCoord, zCoord}, {xCoord + 3, yCoord, zCoord + 1}
                }
        };
        switch (this.blockMetadata)
        {
            case 2:
                for (int i = 0; i < 9; i++)
                    setBlock(worldObj, coords[0][i][0], coords[0][i][1], coords[0][i][2], i + 1);
                break;
            case 3:
                for (int i = 0; i < 9; i++)
                    setBlock(worldObj, coords[1][i][0], coords[1][i][1], coords[1][i][2], i + 1);
                break;
            case 4:
                for (int i = 0; i < 9; i++)
                    setBlock(worldObj, coords[2][i][0], coords[2][i][1], coords[2][i][2], i + 1);
                break;
            case 5:
                for (int i = 0; i < 9; i++)
                    setBlock(worldObj, coords[3][i][0], coords[3][i][1], coords[3][i][2], i + 1);
                break;
        }
    }
}