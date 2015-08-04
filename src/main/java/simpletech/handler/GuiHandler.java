package simpletech.handler;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import simpletech.SimpleTech;
import simpletech.client.gui.inventory.GuiAlloyFurnace;
import simpletech.client.gui.inventory.GuiGrinder;
import simpletech.client.gui.inventory.GuiPlanter;
import simpletech.inventory.ContainerAlloyFurnace;
import simpletech.inventory.ContainerGrinder;
import simpletech.inventory.ContainerPlanter;
import simpletech.reference.Values;
import simpletech.tileentity.TileEntityAlloyFurnace;
import simpletech.tileentity.TileEntityGrinder;
import simpletech.tileentity.TileEntityPlanter;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler
{
    public GuiHandler()
    {

    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (ID)
        {
            case Values.GUI_PLANTER_ID:
                TileEntityPlanter tileEntityPlanter = (TileEntityPlanter) world.getTileEntity(x, y, z);
                if (tileEntityPlanter != null)
                    return new ContainerPlanter(player.inventory, tileEntityPlanter);
                break;
            case Values.GUI_ALLOY_FURNACE_ID:
                TileEntityAlloyFurnace tileEntityAlloyFurnace = (TileEntityAlloyFurnace) world.getTileEntity(x, y, z);
                if (tileEntityAlloyFurnace != null)
                    return new ContainerAlloyFurnace(player.inventory, tileEntityAlloyFurnace);
                break;
            case Values.GUI_GRINDER_ID:
                TileEntityGrinder tileEntityGrinder = (TileEntityGrinder) world.getTileEntity(x, y, z);
                if (tileEntityGrinder != null)
                    return new ContainerGrinder(player.inventory, tileEntityGrinder);
                break;
            case Values.GUI_BAG_ID:
                return new ContainerWorkbench(player.inventory, world, x, y, z);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (ID)
        {
            case Values.GUI_PLANTER_ID:
                TileEntityPlanter tileEntityPlanter = (TileEntityPlanter) world.getTileEntity(x, y, z);
                if (tileEntityPlanter != null)
                    return new GuiPlanter(player.inventory, tileEntityPlanter);
                break;
            case Values.GUI_ALLOY_FURNACE_ID:
                TileEntityAlloyFurnace tileEntityAlloyFurnace = (TileEntityAlloyFurnace) world.getTileEntity(x, y, z);
                if (tileEntityAlloyFurnace != null)
                    return new GuiAlloyFurnace(player.inventory, tileEntityAlloyFurnace);
                break;
            case Values.GUI_GRINDER_ID:
                TileEntityGrinder tileEntityGrinder = (TileEntityGrinder) world.getTileEntity(x, y, z);
                if (tileEntityGrinder != null)
                    return new GuiGrinder(player.inventory, tileEntityGrinder);
                break;
            case Values.GUI_BAG_ID:
                return new GuiCrafting(player.inventory, world, x, y, z);
        }
        return null;
    }
}