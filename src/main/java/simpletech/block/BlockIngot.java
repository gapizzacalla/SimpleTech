package simpletech.block;

import simpletech.reference.Names;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;

public class BlockIngot extends BlockSimpleTech
{
    public BlockIngot(Material material)
    {
        super(material);
        maxMetadata = 10;
        icons = new IIcon[maxMetadata];
        names = Names.INGOT_BLOCK_SUBNAMES;
        setBlockName(Names.INGOT_BLOCK);
        setHardness(3.0F);
        setResistance(5.0F);
    }
}