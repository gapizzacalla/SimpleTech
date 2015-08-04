package simpletech.block;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import simpletech.SimpleTech;
import simpletech.reference.Names;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;

import java.util.Random;

/**
 * Metadata Values:
 * 0 = Aluminum
 * 1 = Chromium
 * 2 = Cobalt
 * 3 = Copper
 * 4 = Impuretitanium
 * 5 = Lead
 * 6 = Silver
 * 7 = Tin
 * 8 = Titanium
 * 9 = Uranium
 */
public class BlockOre extends BlockSimpleTech
{
    public BlockOre(Material material)
    {
        super(material);
        maxMetadata = 10;
        icons = new IIcon[maxMetadata];
        names = Names.ORE_NAMES;
        setBlockName(Names.ORE_NAME);
        setHardness(3.0F);
        setResistance(5.0F);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
    {
        dropBlockAsItem(world, x, y, z, new ItemStack(SimpleTech.Blocks.block_ore, 1, metadata));
        world.setBlock(x, y, z, SimpleTech.Blocks.block_dead_ore, metadata, 2);
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random)
    {
        return super.quantityDropped(meta, fortune, random);
    }

    @Override
    protected void dropBlockAsItem(World world, int x, int y, int z, ItemStack itemStack)
    {
        if (!world.isRemote && world.getGameRules().getGameRuleBooleanValue("doTileDrops") && !world.restoringBlockSnapshots) // do not drop items while restoring blockstates, prevents item dupe
        {
            if (captureDrops.get())
            {
                capturedDrops.get().add(itemStack);
                return;
            }
            float f = 0.7F;
            double d0 = 0.5D;
            double d1 = 0.5D;
            double d2 = 0.5D;
            EntityItem entityitem = new EntityItem(world, (double) x + d0, (double) y + d1, (double) z + d2, itemStack);
            entityitem.delayBeforeCanPickup = 10;
            world.spawnEntityInWorld(entityitem);
        }
    }
}