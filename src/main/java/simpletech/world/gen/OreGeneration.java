package simpletech.world.gen;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.Random;

public class OreGeneration implements IWorldGenerator
{
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
                         IChunkProvider chunkProvider)
    {
        switch (world.provider.dimensionId)
        {
            case -1:
                generateNether(world, random, chunkX * 16, chunkZ * 16);
                break;
            case 0:
                generateSurface(world, random, chunkX * 16, chunkZ * 16);
                break;
            case 1:
                generateEnd(world, random, chunkX * 16, chunkZ * 16);
                break;
        }
    }

    private void generateEnd(World world, Random rand, int chunkX, int chunkZ)
    {
        for (int i = 0; i < 10; i++)
        {
            int XCoord = chunkX + rand.nextInt(16);
            int YCoord = rand.nextInt(128);
            int ZCoord = chunkZ + rand.nextInt(16);
            /*
            (new WorldGenMinable(ModBlocks.titanium_ore, rand.nextInt(2), 4, Blocks.end_stone)).generate(world, rand, XCoord,
                    YCoord,
                    ZCoord);*/
        }
    }

    private void generateSurface(World world, Random rand, int chunkX, int chunkZ)
    {
        for (int i = 0; i < 10; i++)
        {
            int XCoord = chunkX + rand.nextInt(16);
            int YCoord = rand.nextInt(64);
            int ZCoord = chunkZ + rand.nextInt(16);
            /*
            (new WorldGenMinable(ModBlocks.silver_ore, rand.nextInt(3), 6, Blocks.stone))
                    .generate(world, rand, XCoord, YCoord, ZCoord);
            (new WorldGenMinable(ModBlocks.aluminum_ore, rand.nextInt(3), 6, Blocks.stone)).generate(world, rand, XCoord, YCoord,
                    ZCoord);
            (new WorldGenMinable(ModBlocks.tin_ore, rand.nextInt(3), 6, Blocks.stone)).generate(world, rand, XCoord, YCoord,
                    ZCoord);
            (new WorldGenMinable(ModBlocks.copper_ore, rand.nextInt(3), 6, Blocks.stone))
                    .generate(world, rand, XCoord, YCoord, ZCoord);*/
        }

        for (int i = 0; i < 10; i++)
        {
            int XCoord = chunkX + rand.nextInt(16);
            int YCoord = rand.nextInt(30);
            int ZCoord = chunkZ + rand.nextInt(16);
            /*
            (new WorldGenMinable(ModBlocks.chromium_ore, rand.nextInt(3), 5, Blocks.stone)).generate(world, rand, XCoord, YCoord,
                    ZCoord);
            (new WorldGenMinable(ModBlocks.lead_ore, rand.nextInt(3), 5, Blocks.stone)).generate(world, rand, XCoord, YCoord,
                    ZCoord);*/
        }

        for (int i = 0; i < 10; i++)
        {
            int XCoord = chunkX + rand.nextInt(16);
            int YCoord = rand.nextInt(15);
            int ZCoord = chunkZ + rand.nextInt(16);

            //(new WorldGenMinable(ModBlocks.uranium_ore, 3)).generate(world, rand, XCoord, YCoord, ZCoord);
        }
    }

    private void generateNether(World world, Random rand, int chunkX, int chunkZ)
    {
        for (int i = 0; i < 10; i++)
        {
            int XCoord = chunkX + rand.nextInt(16);
            int YCoord = rand.nextInt(128);
            int ZCoord = chunkZ + rand.nextInt(16);
            /*
            (new WorldGenMinable(ModBlocks.cobalt_ore, rand.nextInt(3), 2, Blocks.netherrack)).generate(world, rand, XCoord,
                    YCoord,
                    ZCoord);
            (new WorldGenMinable(ModBlocks.impure_titanium_ore, rand.nextInt(3), 4, Blocks.netherrack)).generate(world, rand,
                    XCoord,
                    YCoord, ZCoord);*/
        }
    }
}