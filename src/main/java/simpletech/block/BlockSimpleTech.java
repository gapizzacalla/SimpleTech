package simpletech.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import simpletech.creativetab.CreativeTabsSimpleTech;
import simpletech.reference.Names;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

public class BlockSimpleTech extends Block
{
    public int maxMetadata;
    public String[] names;
    public IIcon[] icons;

    public BlockSimpleTech()
    {
        this(Material.rock);
        setCreativeTab(CreativeTabsSimpleTech.tabsSimpleTechBlocks);
        setStepSound(soundTypeStone);
    }

    public BlockSimpleTech(Material material)
    {
        super(material);
        setCreativeTab(CreativeTabsSimpleTech.tabsSimpleTechBlocks);
        setStepSound(getStepSoundType(material));
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Names.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        for (int i = 0; i < icons.length; i++)
        {
            icons[i] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()) + "_" +
                    names[i]));
        }
    }

    @Override
    public IIcon getIcon(int side, int metadata)
    {
        if (metadata < 0 || metadata >= icons.length)
            metadata = 0;
        return icons[metadata];
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
        for (int i = 0; i < maxMetadata; i++)
            list.add(new ItemStack(item, 1, i));
    }

    @Override
    public int damageDropped(int metadata)
    {
        return metadata;
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
        return metadata;
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    protected SoundType getStepSoundType(Material material)
    {
        if (material == Material.cloth)
            return soundTypeCloth;
        else if (material == Material.iron)
            return soundTypeMetal;
        else if (material == Material.rock)
            return soundTypeStone;
        else
            return soundTypeStone;
    }
}