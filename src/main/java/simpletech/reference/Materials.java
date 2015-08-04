package simpletech.reference;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class Materials
{
    public static class Armour
    {
        public static final ItemArmor.ArmorMaterial BRONZE = EnumHelper.addArmorMaterial("BRONZE", 40, new int[]{6, 12, 10, 6},
                15);
        public static final ItemArmor.ArmorMaterial TITANIUM = EnumHelper.addArmorMaterial("TITANIUM", 40, new int[]{6, 12, 10,
                6}, 15);
        public static final ItemArmor.ArmorMaterial BACKPACK = EnumHelper.addArmorMaterial("BACKPACK", -1, new int[]{0, 0, 0,
                0}, 0);
    }

    public static class Tool
    {
        public static final ToolMaterial BRONZE = EnumHelper.addToolMaterial("BRONZE", 2, 1311, 7.3F, 2.5F, 16);
        public static final ToolMaterial COBALT = EnumHelper.addToolMaterial("BRONZE", 2, 1311, 7.3F, 2.5F, 16);
    }
}