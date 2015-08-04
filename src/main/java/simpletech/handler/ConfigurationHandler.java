package simpletech.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import simpletech.reference.Reference;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigurationHandler
{
    public static Configuration configuration;

    public static void init(File file)
    {
        if (configuration == null)
        {
            configuration = new Configuration(file);
            loadConfiguration();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.modID.equalsIgnoreCase(Reference.MODID))
        {
            loadConfiguration();
        }
    }

    private static void loadConfiguration()
    {
        if (configuration.hasChanged())
        {
            configuration.save();
        }
    }
}