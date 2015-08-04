package simpletech.client.renderer;

import simpletech.reference.Names;
import simpletech.tileentity.TileEntityItemPipe;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

public class ItemPipeRenderer extends TileEntitySpecialRenderer
{
    float pixel = 1F / 16F;
    float texturePixel = 1F / 32F;
    boolean drawInside = true;

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double translationX, double translationY, double translationZ, float f)
    {
        TileEntityItemPipe pipe = (TileEntityItemPipe) tileEntity;
        GL11.glTranslated(translationX, translationY, translationZ);
        GL11.glDisable(GL11.GL_LIGHTING);
        bindTexture(Names.Textures.item_pipe);
        if (!pipe.onlyOneOpposite(pipe.directions))
        {
            drawCore(tileEntity);
            for (int i = 0; i < pipe.directions.length; i++)
            {
                if (pipe.directions[i] != null)
                {
                    drawConnector(pipe.directions[i]);
                }
            }
        } else
        {
            for (int i = 0; i < pipe.directions.length; i++)
            {
                if (pipe.directions[i] != null)
                {
                    drawStraight(pipe.directions[i]);
                    break;
                }
            }
        }
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glTranslated(-translationX, -translationY, -translationZ);
    }

    public void drawStraight(ForgeDirection direction)
    {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        {
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            if (direction.equals(ForgeDirection.NORTH) || direction.equals(ForgeDirection.SOUTH))
            {
                GL11.glRotatef(90, 1, 0, 0);
            } else if (direction.equals(ForgeDirection.EAST) || direction.equals(ForgeDirection.WEST))
            {
                GL11.glRotatef(90, 0, 0, 1);
            }
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

            tessellator.addVertexWithUV(1 - 10 * pixel / 2, 0, 1 - 10 * pixel / 2, 11 * texturePixel, 6 * texturePixel);
            tessellator.addVertexWithUV(1 - 10 * pixel / 2, 1, 1 - 10 * pixel / 2, 26 * texturePixel, 6 * texturePixel);
            tessellator.addVertexWithUV(10 * pixel / 2, 1, 1 - 10 * pixel / 2, 26 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(10 * pixel / 2, 0, 1 - 10 * pixel / 2, 11 * texturePixel, 0 * texturePixel);

            tessellator.addVertexWithUV(10 * pixel / 2, 0, 10 * pixel / 2, 11 * texturePixel, 6 * texturePixel);
            tessellator.addVertexWithUV(10 * pixel / 2, 1, 10 * pixel / 2, 26 * texturePixel, 6 * texturePixel);
            tessellator.addVertexWithUV(1 - 10 * pixel / 2, 1, 10 * pixel / 2, 26 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(1 - 10 * pixel / 2, 0, 10 * pixel / 2, 11 * texturePixel, 0 * texturePixel);

            tessellator.addVertexWithUV(10 * pixel / 2, 0, 1 - 10 * pixel / 2, 11 * texturePixel, 6 * texturePixel);
            tessellator.addVertexWithUV(10 * pixel / 2, 1, 1 - 10 * pixel / 2, 26 * texturePixel, 6 * texturePixel);
            tessellator.addVertexWithUV(10 * pixel / 2, 1, 10 * pixel / 2, 11 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(10 * pixel / 2, 0, 10 * pixel / 2, 26 * texturePixel, 0 * texturePixel);

            tessellator.addVertexWithUV(1 - 10 * pixel / 2, 0, 10 * pixel / 2, 11 * texturePixel, 6 * texturePixel);
            tessellator.addVertexWithUV(1 - 10 * pixel / 2, 1, 10 * pixel / 2, 26 * texturePixel, 6 * texturePixel);
            tessellator.addVertexWithUV(1 - 10 * pixel / 2, 1, 1 - 10 * pixel / 2, 26 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(1 - 10 * pixel / 2, 0, 1 - 10 * pixel / 2, 11 * texturePixel, 0 * texturePixel);

            if (drawInside)
            {
                tessellator.addVertexWithUV(10 * pixel / 2, 0, 1 - 10 * pixel / 2, 11 * texturePixel, 0 * texturePixel);
                tessellator.addVertexWithUV(10 * pixel / 2, 1, 1 - 10 * pixel / 2, 26 * texturePixel, 0 * texturePixel);
                tessellator.addVertexWithUV(1 - 10 * pixel / 2, 1, 1 - 10 * pixel / 2, 26 * texturePixel, 6 * texturePixel);
                tessellator.addVertexWithUV(1 - 10 * pixel / 2, 0, 1 - 10 * pixel / 2, 11 * texturePixel, 6 * texturePixel);

                tessellator.addVertexWithUV(1 - 10 * pixel / 2, 0, 10 * pixel / 2, 11 * texturePixel, 0 * texturePixel);
                tessellator.addVertexWithUV(1 - 10 * pixel / 2, 1, 10 * pixel / 2, 26 * texturePixel, 0 * texturePixel);
                tessellator.addVertexWithUV(10 * pixel / 2, 1, 10 * pixel / 2, 26 * texturePixel, 6 * texturePixel);
                tessellator.addVertexWithUV(10 * pixel / 2, 0, 10 * pixel / 2, 11 * texturePixel, 6 * texturePixel);

                tessellator.addVertexWithUV(10 * pixel / 2, 0, 10 * pixel / 2, 26 * texturePixel, 0 * texturePixel);
                tessellator.addVertexWithUV(10 * pixel / 2, 1, 10 * pixel / 2, 11 * texturePixel, 0 * texturePixel);
                tessellator.addVertexWithUV(10 * pixel / 2, 1, 1 - 10 * pixel / 2, 26 * texturePixel, 6 * texturePixel);
                tessellator.addVertexWithUV(10 * pixel / 2, 0, 1 - 10 * pixel / 2, 11 * texturePixel, 6 * texturePixel);

                tessellator.addVertexWithUV(1 - 10 * pixel / 2, 0, 1 - 10 * pixel / 2, 11 * texturePixel, 0 * texturePixel);
                tessellator.addVertexWithUV(1 - 10 * pixel / 2, 1, 1 - 10 * pixel / 2, 26 * texturePixel, 0 * texturePixel);
                tessellator.addVertexWithUV(1 - 10 * pixel / 2, 1, 10 * pixel / 2, 26 * texturePixel, 6 * texturePixel);
                tessellator.addVertexWithUV(1 - 10 * pixel / 2, 0, 10 * pixel / 2, 11 * texturePixel, 6 * texturePixel);
            }
        }

        tessellator.draw();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        if (direction.equals(ForgeDirection.NORTH) || direction.equals(ForgeDirection.SOUTH))
        {
            GL11.glRotatef(-90, 1, 0, 0);
        } else if (direction.equals(ForgeDirection.EAST) || direction.equals(ForgeDirection.WEST))
        {
            GL11.glRotatef(-90, 0, 0, 1);
        }
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
    }

    public void drawConnector(ForgeDirection direction)
    {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        {
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            if (direction.equals(ForgeDirection.UP))
            {

            } else if (direction.equals(ForgeDirection.DOWN))
            {
                GL11.glRotatef(180, 1, 0, 0);
            } else if (direction.equals(ForgeDirection.NORTH))
            {
                GL11.glRotatef(270, 1, 0, 0);
            } else if (direction.equals(ForgeDirection.SOUTH))
            {
                GL11.glRotatef(90, 1, 0, 0);
            } else if (direction.equals(ForgeDirection.EAST))
            {
                GL11.glRotatef(270, 0, 0, 1);
            } else if (direction.equals(ForgeDirection.WEST))
            {
                GL11.glRotatef(90, 0, 0, 1);
            }
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

            tessellator
                    .addVertexWithUV(1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 6 * texturePixel, 6 *
                            texturePixel);
            tessellator.addVertexWithUV(1 - 10 * pixel / 2, 1, 1 - 10 * pixel / 2, 10 * texturePixel, 6 * texturePixel);
            tessellator.addVertexWithUV(10 * pixel / 2, 1, 1 - 10 * pixel / 2, 10 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(10 * pixel / 2, 1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 6 * texturePixel, 0 *
                    texturePixel);

            tessellator.addVertexWithUV(10 * pixel / 2, 1 - 10 * pixel / 2, 10 * pixel / 2, 6 * texturePixel, 6 * texturePixel);
            tessellator.addVertexWithUV(10 * pixel / 2, 1, 10 * pixel / 2, 10 * texturePixel, 6 * texturePixel);
            tessellator.addVertexWithUV(1 - 10 * pixel / 2, 1, 10 * pixel / 2, 10 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 10 * pixel / 2, 6 * texturePixel, 0 *
                    texturePixel);

            tessellator.addVertexWithUV(10 * pixel / 2, 1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 6 * texturePixel, 6 *
                    texturePixel);
            tessellator.addVertexWithUV(10 * pixel / 2, 1, 1 - 10 * pixel / 2, 10 * texturePixel, 6 * texturePixel);
            tessellator.addVertexWithUV(10 * pixel / 2, 1, 10 * pixel / 2, 10 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(10 * pixel / 2, 1 - 10 * pixel / 2, 10 * pixel / 2, 6 * texturePixel, 0 * texturePixel);

            tessellator.addVertexWithUV(1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 10 * pixel / 2, 6 * texturePixel, 6 *
                    texturePixel);
            tessellator.addVertexWithUV(1 - 10 * pixel / 2, 1, 10 * pixel / 2, 10 * texturePixel, 6 * texturePixel);
            tessellator.addVertexWithUV(1 - 10 * pixel / 2, 1, 1 - 10 * pixel / 2, 10 * texturePixel, 0 * texturePixel);
            tessellator
                    .addVertexWithUV(1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 6 * texturePixel, 0 *
                            texturePixel);

            if (drawInside)
            {
                tessellator
                        .addVertexWithUV(10 * pixel / 2, 1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 6 * texturePixel, 0 *
                                texturePixel);
                tessellator.addVertexWithUV(10 * pixel / 2, 1, 1 - 10 * pixel / 2, 10 * texturePixel, 0 * texturePixel);
                tessellator.addVertexWithUV(1 - 10 * pixel / 2, 1, 1 - 10 * pixel / 2, 10 * texturePixel, 6 * texturePixel);
                tessellator.addVertexWithUV(1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 6 * texturePixel,
                        6 * texturePixel);

                tessellator
                        .addVertexWithUV(1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 10 * pixel / 2, 6 * texturePixel, 0 *
                                texturePixel);
                tessellator.addVertexWithUV(1 - 10 * pixel / 2, 1, 10 * pixel / 2, 10 * texturePixel, 0 * texturePixel);
                tessellator.addVertexWithUV(10 * pixel / 2, 1, 10 * pixel / 2, 10 * texturePixel, 6 * texturePixel);
                tessellator.addVertexWithUV(10 * pixel / 2, 1 - 10 * pixel / 2, 10 * pixel / 2, 6 * texturePixel, 6 *
                        texturePixel);

                tessellator.addVertexWithUV(10 * pixel / 2, 1 - 10 * pixel / 2, 10 * pixel / 2, 6 * texturePixel, 0 *
                        texturePixel);
                tessellator.addVertexWithUV(10 * pixel / 2, 1, 10 * pixel / 2, 10 * texturePixel, 0 * texturePixel);
                tessellator.addVertexWithUV(10 * pixel / 2, 1, 1 - 10 * pixel / 2, 10 * texturePixel, 6 * texturePixel);
                tessellator
                        .addVertexWithUV(10 * pixel / 2, 1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 6 * texturePixel, 6 *
                                texturePixel);

                tessellator.addVertexWithUV(1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 6 * texturePixel,
                        0 * texturePixel);
                tessellator.addVertexWithUV(1 - 10 * pixel / 2, 1, 1 - 10 * pixel / 2, 10 * texturePixel, 0 * texturePixel);
                tessellator.addVertexWithUV(1 - 10 * pixel / 2, 1, 10 * pixel / 2, 10 * texturePixel, 6 * texturePixel);
                tessellator
                        .addVertexWithUV(1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 10 * pixel / 2, 6 * texturePixel, 6 *
                                texturePixel);
            }
        }

        tessellator.draw();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        if (direction.equals(ForgeDirection.UP))
        {
        } else if (direction.equals(ForgeDirection.DOWN))
        {
            GL11.glRotatef(-180, 1, 0, 0);
        } else if (direction.equals(ForgeDirection.NORTH))
        {
            GL11.glRotatef(-270, 1, 0, 0);
        } else if (direction.equals(ForgeDirection.SOUTH))
        {
            GL11.glRotatef(-90, 1, 0, 0);
        } else if (direction.equals(ForgeDirection.EAST))
        {
            GL11.glRotatef(-270, 0, 0, 1);
        } else if (direction.equals(ForgeDirection.WEST))
        {
            GL11.glRotatef(-90, 0, 0, 1);
        }
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
    }

    public void drawCore(TileEntity tileEntity)
    {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        {
            /** Front */
            tessellator.addVertexWithUV(1 - 10 * pixel / 2, 10 * pixel / 2, 1 - 10 * pixel / 2, 6 * texturePixel, 6 *
                    texturePixel);
            tessellator
                    .addVertexWithUV(1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 6 * texturePixel, 0 *
                            texturePixel);
            tessellator.addVertexWithUV(10 * pixel / 2, 1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 0 * texturePixel, 0 *
                    texturePixel);
            tessellator.addVertexWithUV(10 * pixel / 2, 10 * pixel / 2, 1 - 10 * pixel / 2, 0 * texturePixel, 6 * texturePixel);
            /** Back */
            tessellator.addVertexWithUV(10 * pixel / 2, 10 * pixel / 2, 10 * pixel / 2, 6 * texturePixel, 6 * texturePixel);
            tessellator.addVertexWithUV(10 * pixel / 2, 1 - 10 * pixel / 2, 10 * pixel / 2, 6 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 10 * pixel / 2, 0 * texturePixel, 0 *
                    texturePixel);
            tessellator.addVertexWithUV(1 - 10 * pixel / 2, 10 * pixel / 2, 10 * pixel / 2, 0 * texturePixel, 6 * texturePixel);
            /** Left */
            tessellator.addVertexWithUV(1 - 10 * pixel / 2, 10 * pixel / 2, 10 * pixel / 2, 6 * texturePixel, 6 * texturePixel);
            tessellator.addVertexWithUV(1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 10 * pixel / 2, 6 * texturePixel, 0 *
                    texturePixel);
            tessellator
                    .addVertexWithUV(1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 0 * texturePixel, 0 *
                            texturePixel);
            tessellator.addVertexWithUV(1 - 10 * pixel / 2, 10 * pixel / 2, 1 - 10 * pixel / 2, 0 * texturePixel, 6 *
                    texturePixel);
            /** Right */
            tessellator.addVertexWithUV(10 * pixel / 2, 10 * pixel / 2, 1 - 10 * pixel / 2, 6 * texturePixel, 6 * texturePixel);
            tessellator.addVertexWithUV(10 * pixel / 2, 1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 6 * texturePixel, 0 *
                    texturePixel);
            tessellator.addVertexWithUV(10 * pixel / 2, 1 - 10 * pixel / 2, 10 * pixel / 2, 0 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(10 * pixel / 2, 10 * pixel / 2, 10 * pixel / 2, 0 * texturePixel, 6 * texturePixel);
            /** Top */
            tessellator
                    .addVertexWithUV(1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 6 * texturePixel, 6 *
                            texturePixel);
            tessellator.addVertexWithUV(1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 10 * pixel / 2, 6 * texturePixel, 0 *
                    texturePixel);
            tessellator.addVertexWithUV(10 * pixel / 2, 1 - 10 * pixel / 2, 10 * pixel / 2, 0 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(10 * pixel / 2, 1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 0 * texturePixel, 6 *
                    texturePixel);
            /** Bottom */
            tessellator.addVertexWithUV(10 * pixel / 2, 10 * pixel / 2, 1 - 10 * pixel / 2, 6 * texturePixel, 6 * texturePixel);
            tessellator.addVertexWithUV(10 * pixel / 2, 10 * pixel / 2, 10 * pixel / 2, 6 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(1 - 10 * pixel / 2, 10 * pixel / 2, 10 * pixel / 2, 0 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(1 - 10 * pixel / 2, 10 * pixel / 2, 1 - 10 * pixel / 2, 0 * texturePixel, 6 *
                    texturePixel);

            if (drawInside)
            {
                /** Front */
                tessellator.addVertexWithUV(10 * pixel / 2, 10 * pixel / 2, 1 - 10 * pixel / 2, 0 * texturePixel, 6 *
                        texturePixel);
                tessellator
                        .addVertexWithUV(10 * pixel / 2, 1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 0 * texturePixel, 0 *
                                texturePixel);
                tessellator.addVertexWithUV(1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 6 * texturePixel,
                        0 * texturePixel);
                tessellator
                        .addVertexWithUV(1 - 10 * pixel / 2, 10 * pixel / 2, 1 - 10 * pixel / 2, 6 * texturePixel, 6 *
                                texturePixel);
                /** Back */
                tessellator.addVertexWithUV(1 - 10 * pixel / 2, 10 * pixel / 2, 10 * pixel / 2, 0 * texturePixel, 6 *
                        texturePixel);
                tessellator
                        .addVertexWithUV(1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 10 * pixel / 2, 0 * texturePixel, 0 *
                                texturePixel);
                tessellator.addVertexWithUV(10 * pixel / 2, 1 - 10 * pixel / 2, 10 * pixel / 2, 6 * texturePixel, 0 *
                        texturePixel);
                tessellator.addVertexWithUV(10 * pixel / 2, 10 * pixel / 2, 10 * pixel / 2, 6 * texturePixel, 6 * texturePixel);
                /** Left */
                tessellator
                        .addVertexWithUV(1 - 10 * pixel / 2, 10 * pixel / 2, 1 - 10 * pixel / 2, 0 * texturePixel, 6 *
                                texturePixel);
                tessellator.addVertexWithUV(1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 0 * texturePixel,
                        0 * texturePixel);
                tessellator
                        .addVertexWithUV(1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 10 * pixel / 2, 6 * texturePixel, 0 *
                                texturePixel);
                tessellator.addVertexWithUV(1 - 10 * pixel / 2, 10 * pixel / 2, 10 * pixel / 2, 6 * texturePixel, 6 *
                        texturePixel);
                /** Right */
                tessellator.addVertexWithUV(10 * pixel / 2, 10 * pixel / 2, 10 * pixel / 2, 0 * texturePixel, 6 * texturePixel);
                tessellator.addVertexWithUV(10 * pixel / 2, 1 - 10 * pixel / 2, 10 * pixel / 2, 0 * texturePixel, 0 *
                        texturePixel);
                tessellator
                        .addVertexWithUV(10 * pixel / 2, 1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 6 * texturePixel, 0 *
                                texturePixel);
                tessellator.addVertexWithUV(10 * pixel / 2, 10 * pixel / 2, 1 - 10 * pixel / 2, 6 * texturePixel, 6 *
                        texturePixel);
                /** Top */
                tessellator
                        .addVertexWithUV(10 * pixel / 2, 1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 0 * texturePixel, 6 *
                                texturePixel);
                tessellator.addVertexWithUV(10 * pixel / 2, 1 - 10 * pixel / 2, 10 * pixel / 2, 0 * texturePixel, 0 *
                        texturePixel);
                tessellator
                        .addVertexWithUV(1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 10 * pixel / 2, 6 * texturePixel, 0 *
                                texturePixel);
                tessellator.addVertexWithUV(1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 1 - 10 * pixel / 2, 6 * texturePixel,
                        6 * texturePixel);
                /** Bottom */
                tessellator
                        .addVertexWithUV(1 - 10 * pixel / 2, 10 * pixel / 2, 1 - 10 * pixel / 2, 0 * texturePixel, 6 *
                                texturePixel);
                tessellator.addVertexWithUV(1 - 10 * pixel / 2, 10 * pixel / 2, 10 * pixel / 2, 0 * texturePixel, 0 *
                        texturePixel);
                tessellator.addVertexWithUV(10 * pixel / 2, 10 * pixel / 2, 10 * pixel / 2, 6 * texturePixel, 0 * texturePixel);
                tessellator.addVertexWithUV(10 * pixel / 2, 10 * pixel / 2, 1 - 10 * pixel / 2, 6 * texturePixel, 6 *
                        texturePixel);
            }
        }
        tessellator.draw();
    }
}