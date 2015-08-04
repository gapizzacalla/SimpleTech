package simpletech.packet;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import simpletech.SimpleTech;
import net.minecraft.entity.player.EntityPlayer;

public class OpenGuiPacket implements IMessage
{
    private byte id;

    public OpenGuiPacket() {}

    public OpenGuiPacket(byte id)
    {
        this.id = id;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        id = buf.readByte();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeByte(id);
    }

    public static class Handler extends AbstractServerPacketHandler<OpenGuiPacket>
    {
        @Override
        public IMessage handleClient(EntityPlayer player, OpenGuiPacket message, MessageContext context)
        {
            return null;
        }

        @Override
        public IMessage handleServer(EntityPlayer player, OpenGuiPacket message, MessageContext context)
        {
            player.openGui(SimpleTech.instance(), message.id, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
            return null;
        }
    }
}
