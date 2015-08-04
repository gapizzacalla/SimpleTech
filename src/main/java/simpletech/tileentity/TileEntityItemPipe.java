package simpletech.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityItemPipe extends TileEntity
{
    /**
     * UP, DOWN, NORTH, SOUTH, EAST, WEST
     */
    public ForgeDirection[] directions = new ForgeDirection[6];

    public TileEntityItemPipe()
    {

    }

    @Override
    public void updateEntity()
    {
        updateConnections();
    }

    public void updateConnections()
    {
        if (isNormalTileEntity(xCoord, yCoord + 1, zCoord))
        {
            directions[0] = ForgeDirection.UP;
        } else if (isSpecialTileEntity(xCoord, yCoord + 1, zCoord))
        {
            directions[0] = ForgeDirection.UP;
        } else
            directions[0] = null;
        if (isNormalTileEntity(xCoord, yCoord - 1, zCoord))
        {
            directions[1] = ForgeDirection.DOWN;
        } else if (isSpecialTileEntity(xCoord, yCoord - 1, zCoord))
        {
            directions[1] = ForgeDirection.DOWN;
        } else
            directions[1] = null;
        if (isNormalTileEntity(xCoord, yCoord, zCoord - 1))
        {
            directions[2] = ForgeDirection.NORTH;
        } else if (isSpecialTileEntity(xCoord, yCoord, zCoord - 1))
        {
            directions[2] = ForgeDirection.NORTH;
        } else
            directions[2] = null;
        if (isNormalTileEntity(xCoord, yCoord, zCoord + 1))
        {
            directions[3] = ForgeDirection.SOUTH;
        } else if (isSpecialTileEntity(xCoord, yCoord, zCoord + 1))
        {
            directions[3] = ForgeDirection.SOUTH;
        } else
            directions[3] = null;
        if (isNormalTileEntity(xCoord + 1, yCoord, zCoord))
        {
            directions[4] = ForgeDirection.EAST;
        } else if (isSpecialTileEntity(xCoord + 1, yCoord, zCoord))
        {
            directions[4] = ForgeDirection.EAST;
        } else
            directions[4] = null;
        if (isNormalTileEntity(xCoord - 1, yCoord, zCoord))
        {
            directions[5] = ForgeDirection.WEST;
        } else if (isSpecialTileEntity(xCoord - 1, yCoord, zCoord))
        {
            directions[5] = ForgeDirection.WEST;
        } else
            directions[5] = null;
    }

    public boolean isNormalTileEntity(int x, int y, int z)
    {
        return worldObj.getTileEntity(x, y, z) instanceof TileEntity;
    }

    public boolean isSpecialTileEntity(int x, int y, int z)
    {
        return worldObj.getTileEntity(x, y, z) instanceof TileEntityChest
                || worldObj.getTileEntity(x, y, z) instanceof TileEntityEnderChest;
    }

    public boolean onlyOneOpposite(ForgeDirection[] directions)
    {
        ForgeDirection mainDirection = null;
        boolean isOpposite = false;
        for (int i = 0; i < directions.length; i++)
        {
            if (mainDirection == null && directions[i] != null)
            {
                mainDirection = directions[i];
            }
            if (directions[i] != null && mainDirection != directions[i])
            {
                isOpposite(mainDirection, directions[i]);
                if (!isOpposite(mainDirection, directions[i]))
                {
                    return false;
                } else
                {
                    isOpposite = true;
                }
            }
        }
        return isOpposite;
    }

    public boolean isOpposite(ForgeDirection firstDirection, ForgeDirection secondDirection)
    {
        if (firstDirection.equals(ForgeDirection.UP) && secondDirection.equals(ForgeDirection.DOWN)
                || (firstDirection.equals(ForgeDirection.DOWN) && secondDirection.equals(ForgeDirection.UP)))
        {
            return true;
        }
        if (firstDirection.equals(ForgeDirection.NORTH) && secondDirection.equals(ForgeDirection.SOUTH)
                || (firstDirection.equals(ForgeDirection.SOUTH) && secondDirection.equals(ForgeDirection.NORTH)))
        {
            return true;
        }
        if (firstDirection.equals(ForgeDirection.WEST) && secondDirection.equals(ForgeDirection.EAST)
                || (firstDirection.equals(ForgeDirection.EAST) && secondDirection.equals(ForgeDirection.WEST)))
        {
            return true;
        }
        return false;
    }
}