package simpletech.client.gui.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import simpletech.inventory.ContainerAlloyFurnace;
import simpletech.reference.Names;
import simpletech.tileentity.TileEntityAlloyFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiAlloyFurnace extends GuiContainer
{
    public TileEntityAlloyFurnace alloyFurnace;

    public GuiAlloyFurnace(InventoryPlayer inventoryPlayer, TileEntityAlloyFurnace tileEntity)
    {
        super(new ContainerAlloyFurnace(inventoryPlayer, tileEntity));
        alloyFurnace = tileEntity;
        xSize = 176;
        ySize = 166;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of
     * the items)
     */
    public void drawGuiContainerForegroundLayer(int i, int j)
    {
        fontRendererObj.drawString(StatCollector.translateToLocal(Names.CONTAINER_ALLOY_FURNACE), 8, 6, 0x404040);
        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 0x404040);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the
     * items)
     */
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(Names.Textures.gui_alloy_furnace);
        int k = (width - xSize) / 2;
        int l = (height - ySize) / 2;
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        int i1;
        if (alloyFurnace.isBurning())
        {
            i1 = alloyFurnace.getBurnTimeRemainingScaled((int) alloyFurnace.burnTimeScaled);
            drawTexturedModalRect(k + 152, l + 46 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        }
        i1 = alloyFurnace.getCookProgressScaled((int) alloyFurnace.progressScaled);
        drawTexturedModalRect(k + 62, l + 35, 176, 14, i1 + 1, 16);
    }
    /*
    @Override
    public void initGui()
    {
        super.initGui();
        buttonList.clear();
        buttonList.add(new GuiButton(0, guiLeft + 100, guiTop + 14, 60, 20, "Configure"));
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        if (button.id == 0)
            System.out.println("Clicked!");
    }
    */
}
