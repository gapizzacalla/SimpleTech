package simpletech.item.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import simpletech.SimpleTech;

import java.util.*;
import java.util.Map.Entry;

public class GrinderRecipes
{
    private static final GrinderRecipes base = new GrinderRecipes();

    /**
     * The list of recipe results.
     */
    private Map<ItemStack, List<ItemStack>> recipeList = new HashMap<ItemStack, List<ItemStack>>();
    /**
     * The list of experience gained per recipe.
     */
    private Map<List<ItemStack>, Float> experienceList = new HashMap<List<ItemStack>, Float>();

    /**
     * Used to call methods addRecipe and getResult.
     */
    public static GrinderRecipes recipes()
    {
        return base;
    }

    private GrinderRecipes()
    {
        addRecipe(new ItemStack(Blocks.cobblestone, 1), makeRecipeOutput(new ItemStack(Blocks.sand), null), 0.1F);
        addRecipe(new ItemStack(Blocks.gravel, 1), makeRecipeOutput(new ItemStack(Items.flint, 2), null), 0.1F);
        addRecipe(new ItemStack(Blocks.sandstone, 1), makeRecipeOutput(new ItemStack(Blocks.sand, 4), null), 0.2F);
        addRecipe(new ItemStack(Blocks.wool, 1), makeRecipeOutput(new ItemStack(Items.string, 4), null), 0.2F);
        addRecipe(new ItemStack(Blocks.brick_block, 1), makeRecipeOutput(new ItemStack(Items.brick, 4), null), 0.2F);
        addRecipe(new ItemStack(Blocks.stonebrick, 1), makeRecipeOutput(new ItemStack(Blocks.cobblestone, 4), null), 0.2F);
        addRecipe(new ItemStack(Blocks.nether_brick, 1), makeRecipeOutput(new ItemStack(Items.netherbrick, 4), null), 0.2F);
        addRecipe(new ItemStack(Blocks.stone_slab, 1), makeRecipeOutput(new ItemStack(Blocks.cobblestone), null), 0.1F);
        addRecipe(new ItemStack(Blocks.brick_stairs, 1), makeRecipeOutput(new ItemStack(Blocks.brick_block, 3), null), 0.25F);
        addRecipe(new ItemStack(Blocks.stone_stairs, 1), makeRecipeOutput(new ItemStack(Blocks.cobblestone, 3), null), 0.25F);
        addRecipe(new ItemStack(Blocks.nether_brick_stairs, 1), makeRecipeOutput(new ItemStack(Items.netherbrick, 5), null), 0.5F);
        addRecipe(new ItemStack(Blocks.quartz_stairs, 1), makeRecipeOutput(new ItemStack(Items.quartz, 5), null), 0.5F);
        addRecipe(new ItemStack(Blocks.sandstone_stairs, 1), makeRecipeOutput(new ItemStack(Blocks.sand, 5), null), 0.5F);
        addRecipe(new ItemStack(Blocks.stone_brick_stairs, 1), makeRecipeOutput(new ItemStack(Blocks.cobblestone, 5), null), 0.5F);
        addRecipe(new ItemStack(Blocks.nether_brick_fence, 1), makeRecipeOutput(new ItemStack(Items.netherbrick, 3), null), 0.3F);
        addRecipe(new ItemStack(Blocks.iron_ore, 1), makeRecipeOutput(new ItemStack(SimpleTech.Items.dust, 2, 7), null), 0.7F);
        addRecipe(new ItemStack(Blocks.gold_ore, 1), makeRecipeOutput(new ItemStack(SimpleTech.Items.dust, 2, 5), null), 1.0F);
        addRecipe(new ItemStack(Blocks.diamond_ore, 1), makeRecipeOutput(new ItemStack(Items.diamond, 2), null), 1.0F);
        addRecipe(new ItemStack(Blocks.emerald_ore, 1), makeRecipeOutput(new ItemStack(Items.emerald, 2), null), 1.0F);
        addRecipe(new ItemStack(Blocks.coal_ore, 1), makeRecipeOutput(new ItemStack(Items.coal, 2), null), 0.1F);
        addRecipe(new ItemStack(Blocks.redstone_ore, 1), makeRecipeOutput(new ItemStack(Items.redstone, 5), null), 0.7F);
        addRecipe(new ItemStack(Blocks.lapis_ore, 1), makeRecipeOutput(new ItemStack(Items.dye, 2, 4), null), 0.2F);
        addRecipe(new ItemStack(Blocks.quartz_ore, 1), makeRecipeOutput(new ItemStack(Items.quartz, 2), null), 0.2F);

        addRecipe(new ItemStack(SimpleTech.Blocks.block_ore, 1, 0), makeRecipeOutput(new ItemStack(SimpleTech.Items.dust, 2, 0), null), 0.2F);
        addRecipe(new ItemStack(SimpleTech.Blocks.block_ore, 1, 1), makeRecipeOutput(new ItemStack(SimpleTech.Items.dust, 2, 2), null), 0.2F);
        addRecipe(new ItemStack(SimpleTech.Blocks.block_ore, 1, 2), makeRecipeOutput(new ItemStack(SimpleTech.Items.dust, 2, 3), null), 0.2F);
        addRecipe(new ItemStack(SimpleTech.Blocks.block_ore, 1, 3), makeRecipeOutput(new ItemStack(SimpleTech.Items.dust, 2, 4), null), 0.2F);
        addRecipe(new ItemStack(SimpleTech.Blocks.block_ore, 1, 4), makeRecipeOutput(new ItemStack(SimpleTech.Items.dust, 2, 6), null), 0.2F);
        addRecipe(new ItemStack(SimpleTech.Blocks.block_ore, 1, 5), makeRecipeOutput(new ItemStack(SimpleTech.Items.dust, 2, 8), null), 0.2F);
        addRecipe(new ItemStack(SimpleTech.Blocks.block_ore, 1, 6), makeRecipeOutput(new ItemStack(SimpleTech.Items.dust, 2, 9), null), 0.2F);
        addRecipe(new ItemStack(SimpleTech.Blocks.block_ore, 1, 7), makeRecipeOutput(new ItemStack(SimpleTech.Items.dust, 2, 10), null), 0.2F);
        addRecipe(new ItemStack(SimpleTech.Blocks.block_ore, 1, 8), makeRecipeOutput(new ItemStack(SimpleTech.Items.dust, 2, 11), null), 0.2F);
    }

    public ArrayList<ItemStack> makeRecipeOutput(ItemStack output1, ItemStack output2)
    {
        ArrayList<ItemStack> outputs = new ArrayList<ItemStack>();
        outputs.add(output1);
        outputs.add(output2);
        return outputs;
    }

    public void addRecipe(ItemStack input, ArrayList<ItemStack> outputs, float xp)
    {
        recipeList.put(input, outputs);
        experienceList.put(outputs, xp);
    }

    /**
     * Returns the result of an item.
     */
    public ArrayList<ItemStack> getResult(ItemStack itemStack)
    {
        Iterator iterator = recipeList.entrySet().iterator();
        Entry entry;
        do
        {
            if (!iterator.hasNext())
                return null;
            entry = (Entry) iterator.next();
        } while (!compareLists(itemStack, (ItemStack) entry.getKey()));
        ArrayList<ItemStack> outputs = (ArrayList<ItemStack>) entry.getValue();
        return outputs;
    }

    public int getRecipeInputSize(ItemStack itemStack)
    {
        Iterator iterator = recipeList.entrySet().iterator();
        Entry entry;
        do
        {
            if (!iterator.hasNext())
                return 0;
            entry = (Entry) iterator.next();
        } while (!compareLists(itemStack, (ItemStack) entry.getKey()));
        int size = ((ItemStack) entry.getKey()).stackSize;
        return size;
    }

    /**
     * Compares metadata values of the inputs and the key.
     */
    private boolean compareLists(ItemStack input, ItemStack key)
    {
        return key.getItem() == input.getItem() && (key.getItemDamage() == 32767 || key.getItemDamage() == input.getItemDamage());
    }

    public Map getRecipeList()
    {
        return recipeList;
    }
}