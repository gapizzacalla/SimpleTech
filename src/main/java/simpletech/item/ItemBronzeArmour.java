package simpletech.item;

import simpletech.SimpleTech;
import simpletech.reference.Names;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor;

public class ItemBronzeArmour extends ItemArmor implements ISpecialArmor
{
    public ItemBronzeArmour(ArmorMaterial armorMaterial, int renderIndex, int armorType)
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
        return 3;
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot)
    {

    }

    @Override
    public String getArmorTexture(ItemStack armor, Entity entity, int slot, String string)
    {
        if (armor.getItem() == SimpleTech.Items.bronze_leggings)
        {
            return Names.Textures.bronze_layer_2;
        } else
        {
            return Names.Textures.bronze_layer_1;
        }
    }

    @Override
    public boolean getIsRepairable(ItemStack armor, ItemStack itemStack)
    {
        return itemStack == new ItemStack(SimpleTech.Items.ingot, 1, 1);
    }
}