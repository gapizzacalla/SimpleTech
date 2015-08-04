package simpletech.block;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import simpletech.SimpleTech;
import simpletech.reference.Names;
import simpletech.reference.Reference;
import simpletech.reference.Values;
import simpletech.tileentity.TileEntityGrinder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class BlockGrinder extends BlockContainerSimpleTech
{
    private final boolean isActive;
    private static boolean keepInventory;

    public BlockGrinder(boolean isActive)
    {
        super(Material.rock);
        setHardness(3.0F);
        setResistance(5.0F);
        this.isActive = isActive;
        guiID = Values.GUI_GRINDER_ID;
        blockIconName = "grinder_side";
        iconFrontName = isActive ? "grinder_front_idle" : "grinder_front_active";
        iconTopName = "grinder_top";
    }

    public static void updateBlockState(boolean active, World worldObj, int xCoord, int yCoord, int zCoord)
    {
        int metadata = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        TileEntity tileEntity = worldObj.getTileEntity(xCoord, yCoord, zCoord);
        keepInventory = true;
        if (active)
            worldObj.setBlock(xCoord, yCoord, zCoord, SimpleTech.Blocks.grinder_active);
        else
            worldObj.setBlock(xCoord, yCoord, zCoord, SimpleTech.Blocks.grinder_idle);
        keepInventory = false;
        worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, metadata, 2);
        if (tileEntity != null)
        {
            tileEntity.validate();
            worldObj.setTileEntity(xCoord, yCoord, zCoord, tileEntity);
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i)
    {
        return new TileEntityGrinder();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(Names.RESOURCE_PREFIX + blockIconName);
        iconFront = iconRegister.registerIcon(Names.RESOURCE_PREFIX + (isActive ? "grinder_front_idle" : "grinder_front_active"));
        iconTop = iconRegister.registerIcon(Names.RESOURCE_PREFIX + iconTopName);
    }
}