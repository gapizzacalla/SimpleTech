package simpletech.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor;
import simpletech.SimpleTech;
import simpletech.reference.Names;

public class ItemTitaniumArmour extends ItemArmor implements ISpecialArmor
{
    public ItemTitaniumArmour(ArmorMaterial armorMaterial, int renderIndex, int armorType)
    {
        super(armorMaterial, renderIndex, armorType);
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase entityLivingBase, ItemStack itemStack, DamageSource damageSource,
                                         double damage, int slot)
    {
        return null;
    }

    @Override
    public int getArmorDisplay(EntityPlayer entityPlayer, ItemStack itemStack, int slot)
    {
        return 5;
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot)
    {

    }

    @Override
    public String getArmorTexture(ItemStack armor, Entity entity, int slot, String string)
    {
        if (armor.getItem() == SimpleTech.Items.titanium_leggings)
        {
            return Names.Textures.titanium_layer_2;
        } else
        {
            return Names.Textures.titanium_layer_1;
        }
    }

    @Override
    public boolean getIsRepairable(ItemStack armor, ItemStack itemStack)
    {
        return itemStack == new ItemStack(SimpleTech.Items.ingot, 1, 9);
    }
}