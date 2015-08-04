package simpletech.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import simpletech.creativetab.CreativeTabsSimpleTech;
import simpletech.reference.Names;
import simpletech.reference.Values;
import simpletech.tileentity.TileEntityPlanter;

public class BlockPlanter extends BlockContainerSimpleTech
{
    public BlockPlanter(Material material)
    {
        super(material);
        setBlockName(Names.PLANTER);
        blockIconName = "planter_side";
        iconFrontName = "planter_front";
        iconTopName = "planter_top";
        guiID = Values.GUI_PLANTER_ID;
        setCreativeTab(CreativeTabsSimpleTech.tabsSimpleTechBlocks);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityPlanter();
    }
}