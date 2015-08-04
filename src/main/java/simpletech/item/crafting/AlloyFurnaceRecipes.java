package simpletech.item.crafting;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import simpletech.SimpleTech;

import java.util.*;
import java.util.Map.Entry;

public class AlloyFurnaceRecipes
{
    private static final AlloyFurnaceRecipes base = new AlloyFurnaceRecipes();

    /**
     * The list of recipe results.
     */
    private Map<List<ItemStack>, ItemStack> recipeList = new HashMap<List<ItemStack>, ItemStack>();
    /**
     * The list of experience gained per recipe.
     */
    private Map<ItemStack, Float> experienceList = new HashMap<ItemStack, Float>();

    /**
     * Used to call methods addRecipe and getResult.
     */
    public static AlloyFurnaceRecipes recipes()
    {
        return base;
    }

    private AlloyFurnaceRecipes()
    {
        addRecipe(makeRecipeList(new ItemStack(SimpleTech.Items.ingot, 3, 4), new ItemStack(SimpleTech.Items.ingot, 1, 8)), new ItemStack
                (SimpleTech.Items.ingot, 1, 1), 0.2F);
        addRecipe(makeRecipeList(new ItemStack(SimpleTech.Items.ingot, 2, 5), new ItemStack(Items.iron_ingot, 1)), new ItemStack
                (SimpleTech.Items.ingot, 1, 9), 0.2F);
    }

    public ArrayList<ArrayList<ItemStack>> makeRecipeList(ItemStack input, ItemStack input2)
    {
        ArrayList<ArrayList<ItemStack>> recipe = new ArrayList<ArrayList<ItemStack>>();
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        ArrayList<ItemStack> list2 = new ArrayList<ItemStack>();

        list.add(input);
        list.add(input2);

        list2.add(input2);
        list2.add(input);

        recipe.add(list);
        recipe.add(list2);

        return recipe;
    }

    public void addRecipe(ArrayList<ArrayList<ItemStack>> recipes, ItemStack output, float xp)
    {
        recipeList.put(recipes.get(0), output);
        recipeList.put(recipes.get(1), output);
        experienceList.put(output, Float.valueOf(xp));
    }

    /**
     * Returns the result of an item.
     */
    public ItemStack getResult(ArrayList<ItemStack> inputs)
    {
        Iterator iterator = recipeList.entrySet().iterator();
        Entry entry;
        do
        {
            if (!iterator.hasNext())
                return null;
            entry = (Entry) iterator.next();
        } while (!compareLists(inputs, (ArrayList<ItemStack>) entry.getKey()));
        ItemStack output = (ItemStack) entry.getValue();
        return output;
    }

    /**
     * Returns an array with the stack sizes of the inputs
     */
    public int[] getRecipeInputSizes(ArrayList<ItemStack> inputs)
    {
        Iterator iterator = recipeList.entrySet().iterator();
        Entry entry;
        do
        {
            if (!iterator.hasNext())
                return null;
            entry = (Entry) iterator.next();
        } while (!compareLists(inputs, (ArrayList<ItemStack>) entry.getKey()));
        int[] sizes = {((ArrayList<ItemStack>) entry.getKey()).get(0).stackSize, ((ArrayList<ItemStack>) entry.getKey()).get(1)
                .stackSize};
        return sizes;
    }

    /**
     * Compares metadata values of the inputs and the key.
     */
    private boolean compareLists(ArrayList<ItemStack> inputs, ArrayList<ItemStack> key)
    {
        if (inputs.get(0) == null || inputs.get(1) == null || key.get(0) == null || key.get(1) == null)
            return false;
        else
            return inputs.get(0).getItemDamage() == key.get(0).getItemDamage() && inputs.get(1).getItemDamage() == key.get(1)
                    .getItemDamage();
    }
}