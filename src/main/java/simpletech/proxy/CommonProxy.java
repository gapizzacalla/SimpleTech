package simpletech.proxy;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import simpletech.world.gen.OreGeneration;

public class CommonProxy implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}

	public static void registerWorldGeneration()
	{
		GameRegistry.registerWorldGenerator(new OreGeneration(), 0);
	}

	public EntityPlayer getPlayerEntity(MessageContext context)
	{
		return context.getServerHandler().playerEntity;
	}
}