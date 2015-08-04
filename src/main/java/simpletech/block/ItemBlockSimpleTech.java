package simpletech.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockSimpleTech extends ItemBlock
{
    public String[] names;
    public ItemBlockSimpleTech(Block block)
    {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return getUnlocalizedName() + "_" + names[itemStack.getItemDamage()];
    }

    @Override
    public int getMetadata(int metadata)
    {
        return metadata;
    }
}
