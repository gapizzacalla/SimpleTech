package simpletech.client.gui.inventory;

import simpletech.SimpleTech;
import simpletech.inventory.ContainerPlanter;
import simpletech.reference.Names;
import simpletech.tileentity.TileEntityPlanter;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

public class GuiPlanter extends GuiContainer
{
    private TileEntityPlanter planter;
    private InventoryPlayer inventoryPlayer;

    public GuiPlanter(InventoryPlayer inventoryPlayer, TileEntityPlanter tileEntityPlanter)
    {
        super(new ContainerPlanter(inventoryPlayer, tileEntityPlanter));
        planter = tileEntityPlanter;
        xSize = 176;
        ySize = 166;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        GuiRectangle fuelBar = new GuiRectangle(154, 7, 14, 52);
        fontRendererObj.drawString(StatCollector.translateToLocal(Names.CONTAINER_PLANTER), 8, 6, 4210752);
        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
        if (fuelBar.inRect(this, x, y))
        {
            ArrayList<String> list = new ArrayList<String>();
            list.add("Fuel: " + planter.getFuelAmount() + "/" + planter.capacity);
            drawHoveringText(list, x - guiLeft, y - guiTop, fontRendererObj);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y)
    {
        int metadata = planter.getBlockMetadata();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(Names.Textures.gui_planter);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        int barHeight = planter.getFuelAmount() / 1000;
        if (barHeight > 0)
        {
            int srcX = xSize;
            int srcY = 50 - barHeight;
            drawTexturedModalRect(guiLeft + 154, guiTop + 7 + 50 - barHeight, srcX, srcY, 12, barHeight);
        }
        mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
        drawTexturedModelRectFromIcon(guiLeft + 81, guiTop + 7, SimpleTech.Blocks.planter.getIcon(2, 2), 16, 16);
    }

    protected int getTop()
    {
        return guiTop;
    }

    protected int getLeft()
    {
        return guiLeft;
    }
}