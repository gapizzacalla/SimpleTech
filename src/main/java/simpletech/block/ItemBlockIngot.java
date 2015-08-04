package simpletech.block;

import simpletech.reference.Names;
import net.minecraft.block.Block;

public class ItemBlockIngot extends ItemBlockSimpleTech
{
    public ItemBlockIngot(Block block)
    {
        super(block);
        names = Names.INGOT_BLOCK_SUBNAMES;
    }
}
