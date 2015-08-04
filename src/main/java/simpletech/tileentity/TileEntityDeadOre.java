package simpletech.tileentity;

import net.minecraft.tileentity.TileEntity;
import simpletech.SimpleTech;

public class TileEntityDeadOre extends TileEntity
{
    private int ticks;
    @Override
    public void updateEntity()
    {
        ticks++;
        if(ticks % 200 == 0)
        {
            worldObj.setBlock(xCoord, yCoord, zCoord, SimpleTech.Blocks.block_ore, getBlockMetadata(), 2);
        }
    }
}