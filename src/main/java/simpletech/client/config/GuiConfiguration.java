package simpletech.client.config;

import cpw.mods.fml.client.config.GuiConfig;
import simpletech.handler.ConfigurationHandler;
import simpletech.reference.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class GuiConfiguration extends GuiConfig
{
    public GuiConfiguration(GuiScreen guiScreen)
    {
        super(guiScreen, new ConfigElement(ConfigurationHandler.configuration.getCategory(Configuration.CATEGORY_GENERAL))
                .getChildElements(), Reference.MODID, false, false, GuiConfig.getAbridgedConfigPath(Reference.CONFIG_NAME));
    }
}