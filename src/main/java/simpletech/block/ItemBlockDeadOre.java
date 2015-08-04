package simpletech.block;

import net.minecraft.block.Block;
import simpletech.reference.Names;

public class ItemBlockDeadOre extends ItemBlockSimpleTech
{
    public ItemBlockDeadOre(Block block)
    {
        super(block);
        names = Names.ORE_NAMES;
    }
}