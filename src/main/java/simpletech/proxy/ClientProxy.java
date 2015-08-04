package simpletech.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import simpletech.client.renderer.ItemPipeRenderer;
import simpletech.handler.KeyHandler;
import simpletech.tileentity.TileEntityItemPipe;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class ClientProxy extends CommonProxy
{
    public static void registerProxies()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityItemPipe.class, new ItemPipeRenderer());
        FMLCommonHandler.instance().bus().register(new KeyHandler());
    }

    @Override
    public EntityPlayer getPlayerEntity(MessageContext context)
    {
        return (context.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(context));
    }
}