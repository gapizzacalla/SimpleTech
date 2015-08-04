package simpletech.block;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
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
import simpletech.SimpleTech;
import simpletech.reference.Names;
import simpletech.reference.Reference;
import simpletech.reference.Values;
import simpletech.tileentity.TileEntityAlloyFurnace;

import java.util.Random;

public class BlockAlloyFurnace extends BlockContainerSimpleTech
{
    private final boolean isActive;
    private static boolean keepInventory;

    public BlockAlloyFurnace(boolean isActive)
    {
        super(Material.rock);
        setHardness(3.0F);
        setResistance(5.0F);
        this.isActive = isActive;
        guiID = Values.GUI_ALLOY_FURNACE_ID;
        blockIconName = "alloy_furnace_side";
        iconFrontName = isActive ? "alloy_furnace_front_idle" : "alloy_furnace_front_active";
        iconTopName = "alloy_furnace_top";
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityAlloyFurnace();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(Names.RESOURCE_PREFIX + blockIconName);
        iconFront = iconRegister.registerIcon(Names.RESOURCE_PREFIX + (isActive ? "alloy_furnace_front_idle" : "alloy_furnace_front_active"));
        iconTop = iconRegister.registerIcon(Names.RESOURCE_PREFIX + iconTopName);
    }

    /**
     * Update which block ID the furnace is using depending on whether or not it
     * is burning
     */
    public static void updateBlockState(boolean active, World worldObj, int xCoord, int yCoord, int zCoord)
    {
        int metadata = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        TileEntity tileEntity = worldObj.getTileEntity(xCoord, yCoord, zCoord);
        keepInventory = true;
        if (active)
            worldObj.setBlock(xCoord, yCoord, zCoord, SimpleTech.Blocks.alloy_furnace_active);
        else
            worldObj.setBlock(xCoord, yCoord, zCoord, SimpleTech.Blocks.alloy_furnace_idle);
        keepInventory = false;
        worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, metadata, 2);
        if (tileEntity != null)
        {
            tileEntity.validate();
            worldObj.setTileEntity(xCoord, yCoord, zCoord, tileEntity);
        }
    }
}