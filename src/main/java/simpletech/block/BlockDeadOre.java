package simpletech.block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import simpletech.SimpleTech;
import simpletech.reference.Names;
import simpletech.tileentity.TileEntityDeadOre;

import java.util.Random;

public class BlockDeadOre extends BlockSimpleTech implements ITileEntityProvider
{
    public BlockDeadOre(Material material)
    {
        super(material);
        maxMetadata = 10;
        icons = new IIcon[maxMetadata];
        names = Names.ORE_NAMES;
        setBlockName(Names.DEAD_ORE_NAME);
        setHardness(3.0F);
        setResistance(5.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityDeadOre();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        return false;
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 0;
    }

    public void updateBlock(World world, int x, int y, int z, int metadata)
    {
        world.setBlock(x, y, z, Blocks.air, 0, 2);
        world.setBlock(x, y, z, SimpleTech.Blocks.block_ore, metadata, 2);
    }
}