package simpletech.helper;

import simpletech.reference.Reference;
import net.minecraft.util.ResourceLocation;

/**
 * @author pahimar
 */
public class ResourceLocationHelper
{
    public static ResourceLocation getResourceLocation(String path)
    {
        return new ResourceLocation(Reference.MODID.toLowerCase(), path);
    }
}