package simpletech.block;

import simpletech.creativetab.CreativeTabsSimpleTech;
import simpletech.reference.Names;
import simpletech.tileentity.TileEntityItemPipe;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockItemPipe extends BlockContainer
{
    public BlockItemPipe()
    {
        super(Material.ground);
        float pixel = 1F / 16F;
        setCreativeTab(CreativeTabsSimpleTech.tabsSimpleTechBlocks);
        setBlockName(Names.ITEM_PIPE);
        // setBlockTextureName();
        setBlockBounds(10 * pixel / 2, 10 * pixel / 2, 10 * pixel / 2, 1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 1 - 10 * pixel /
                2);
        useNeighborBrightness = true;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        TileEntityItemPipe pipe = (TileEntityItemPipe) world.getTileEntity(x, y, z);
        float pixel = 1F / 16F;

        if (pipe != null)
        {
            float minX = 10 * pixel / 2 - (pipe.directions[5] != null ? (10 * pixel / 2) : 0);
            float minY = 10 * pixel / 2 - (pipe.directions[1] != null ? (10 * pixel / 2) : 0);
            float minZ = 10 * pixel / 2 - (pipe.directions[2] != null ? (10 * pixel / 2) : 0);
            float maxX = 1 - 10 * pixel / 2 + (pipe.directions[4] != null ? (10 * pixel / 2) : 0);
            float maxY = 1 - 10 * pixel / 2 + (pipe.directions[0] != null ? (10 * pixel / 2) : 0);
            float maxZ = 1 - 10 * pixel / 2 + (pipe.directions[3] != null ? (10 * pixel / 2) : 0);
            setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
        }
        return AxisAlignedBB.getBoundingBox(x + minX, y + minY, z + minZ, x + maxX, y + maxY, z + maxZ);
    }

    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
    {
        TileEntityItemPipe pipe = (TileEntityItemPipe) world.getTileEntity(x, y, z);
        float pixel = 1F / 16F;

        if (pipe != null)
        {
            float minX = 10 * pixel / 2 - (pipe.directions[5] != null ? (10 * pixel / 2) : 0);
            float minY = 10 * pixel / 2 - (pipe.directions[1] != null ? (10 * pixel / 2) : 0);
            float minZ = 10 * pixel / 2 - (pipe.directions[2] != null ? (10 * pixel / 2) : 0);
            float maxX = 1 - 10 * pixel / 2 + (pipe.directions[4] != null ? (10 * pixel / 2) : 0);
            float maxY = 1 - 10 * pixel / 2 + (pipe.directions[0] != null ? (10 * pixel / 2) : 0);
            float maxZ = 1 - 10 * pixel / 2 + (pipe.directions[3] != null ? (10 * pixel / 2) : 0);
            setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
        }
        return AxisAlignedBB.getBoundingBox(x + minX, y + minY, z + minZ, x + maxX, y + maxY, z + maxZ);
    }

    public int getRenderType()
    {
        return -1;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public TileEntity createNewTileEntity(World world, int i)
    {
        return new TileEntityItemPipe();
    }
}