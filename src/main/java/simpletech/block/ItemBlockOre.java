package simpletech.block;

import simpletech.reference.Names;
import net.minecraft.block.Block;

public class ItemBlockOre extends ItemBlockSimpleTech
{
    public ItemBlockOre(Block block)
    {
        super(block);
        names = Names.ORE_NAMES;
    }
}
