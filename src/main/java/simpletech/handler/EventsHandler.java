package simpletech.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.lwjgl.input.Keyboard;
import simpletech.SimpleTech;

public class EventsHandler
{
    @SubscribeEvent
    public void onItemRightClick(PlayerInteractEvent event)
    {
        ItemStack heldStack = event.entityPlayer.getHeldItem();
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
        {
            if (heldStack != null && heldStack.isItemEqual(new ItemStack(SimpleTech.Items.cobalt_sword)))
            {
                Minecraft.getMinecraft().thePlayer.setCurrentItemOrArmor(0, new ItemStack(SimpleTech.Items.cobalt_pickaxe));
                return;
            }
            if (heldStack != null && heldStack.isItemEqual(new ItemStack(SimpleTech.Items.cobalt_pickaxe)))
            {
                Minecraft.getMinecraft().thePlayer.setCurrentItemOrArmor(0, new ItemStack(SimpleTech.Items.cobalt_sword));
                return;
            } else if (heldStack != null && heldStack.isItemEqual(new ItemStack(SimpleTech.Items.cobalt_axe)))
            {
                Minecraft.getMinecraft().thePlayer.setCurrentItemOrArmor(0, new ItemStack(SimpleTech.Items.cobalt_shovel));
                return;
            } else if (heldStack != null && heldStack.isItemEqual(new ItemStack(SimpleTech.Items.cobalt_shovel)))
            {
                Minecraft.getMinecraft().thePlayer.setCurrentItemOrArmor(0, new ItemStack(SimpleTech.Items.cobalt_hoe));
                return;
            } else if (heldStack != null && heldStack.isItemEqual(new ItemStack(SimpleTech.Items.cobalt_hoe)))
            {
                Minecraft.getMinecraft().thePlayer.setCurrentItemOrArmor(0, new ItemStack(SimpleTech.Items.cobalt_sword));
                return;
            }
        }
    }

    @SubscribeEvent
    public void addTooltip(ItemTooltipEvent event)
    {
        if (event.itemStack.getItem() instanceof Item)
        {
            String[] ores = {"Coal Ore", "Diamond Ore", "Emerald Ore", "Gold Ore", "Iron Ore", "Lapis Lazuli Ore", "Nether Quartz Ore",
                    "Redstone Ore", "Aluminum Ore", "Chromium Ore", "Cobalt Ore", "Copper Ore", "Impure Titanium Ore",
                    "Lead Ore", "Silver Ore", "Tin Ore", "Titanium Ore"
            };
            for (int i = 0; i < ores.length; i++)
            {
                if (event.itemStack.getDisplayName().equals(ores[i]))
                {
                    event.toolTip.add(EnumChatFormatting.WHITE + "Produces: " + EnumChatFormatting.GRAY + event.itemStack
                            .getDisplayName().replaceAll("Ore", ""));
                    event.toolTip.add(EnumChatFormatting.GREEN + "**Processed in Grinder**");
                }
            }
            if (event.itemStack.getDisplayName().equals("Uranium Ore") && event.itemStack.getUnlocalizedName().contains("simpletech"))
            {
                event.toolTip.add(EnumChatFormatting.WHITE + "Produces: " + EnumChatFormatting.GRAY + event.itemStack
                        .getDisplayName().replaceAll("Ore", ""));
                event.toolTip.add(EnumChatFormatting.GREEN + "**Processed in Chromium Furnace**");
            }
        }
    }
}
