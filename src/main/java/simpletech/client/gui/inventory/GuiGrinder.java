package simpletech.client.gui.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import simpletech.inventory.ContainerGrinder;
import simpletech.reference.Names;
import simpletech.tileentity.TileEntityGrinder;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiGrinder extends GuiContainer
{
    public TileEntityGrinder grinder;

    public GuiGrinder(InventoryPlayer inventoryPlayer, TileEntityGrinder tileEntity)
    {
        super(new ContainerGrinder(inventoryPlayer, tileEntity));
        grinder = tileEntity;
        xSize = 176;
        ySize = 166;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of
     * the items)
     */
    public void drawGuiContainerForegroundLayer(int i, int j)
    {
        fontRendererObj.drawString(StatCollector.translateToLocal(Names.CONTAINER_GRINDER), 8, 6, 4210752);
        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the
     * items)
     */
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(Names.Textures.gui_grinder);
        int k = (width - xSize) / 2;
        int l = (height - ySize) / 2;
        drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
        int i1;
        if (grinder.isBurning())
        {
            i1 = grinder.getBurnTimeRemainingScaled((int) grinder.burnTimeScaled);
            drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        }
        i1 = grinder.getCookProgressScaled((int) grinder.progressScaled);
        drawTexturedModalRect(k + 80, l + 34, 176, 14, i1 + 1, 16);
    }
}