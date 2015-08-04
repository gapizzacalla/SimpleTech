package simpletech.creativetab;

import simpletech.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;

public class CreativeTabsSimpleTech
{
    public static CreativeTabs tabsSimpleTechBlocks = new CreativeTabBlocks(CreativeTabs.getNextID(), Reference.MODID);
    public static CreativeTabs tabsSimpleTechItems = new CreativeTabItems(CreativeTabs.getNextID(), Reference.MODID);
    public static CreativeTabs tabsSimpleTechCombat = new CreativeTabCombat(CreativeTabs.getNextID(), Reference.MODID);
    public static CreativeTabs tabsSimpleTechTools = new CreativeTabTools(CreativeTabs.getNextID(), Reference.MODID);
}