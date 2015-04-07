package revolution.revelation.machine.recipes;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import revolution.revelation.foundation.init.FoundationBlocks;
import revolution.revelation.foundation.init.FoundationItems;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class basicGrinderRecipes {
	private static final basicGrinderRecipes basicGrinderBase = new basicGrinderRecipes();

	private Map grindingList = Maps.newHashMap();
	
	public static basicGrinderRecipes instance()
	{
		return basicGrinderBase;
	}
	
	private basicGrinderRecipes()
	{
		this.addGrindingRecipeForBlock(FoundationBlocks.oreCopper, new ItemStack(FoundationItems.dustCopper, 2));
	}
	
	public void addGrindingRecipeForBlock(Block input, ItemStack stack)
	{
		this.addGrinding(Item.getItemFromBlock(input), stack);
	}
	
	public void addGrinding(Item input, ItemStack stack)
	{
		this.addGrindingRecipe(new ItemStack(input, 1, 32767), stack);
	}
	
	public void addGrindingRecipe(ItemStack input, ItemStack stack)
	{
		this.grindingList.put(input, stack);
	}
	
	public ItemStack getGrindingResult(ItemStack stack)
	{
		Iterator iterator = this.grindingList.entrySet().iterator();
		Entry entry;
		
		do
		{
			if (!iterator.hasNext())
			{
				return null;
			}
			
			entry = (Entry)iterator.next();
		}
		while (!this.compareItemStacks(stack, (ItemStack)entry.getKey()));
		
		return (ItemStack)entry.getValue();
	}
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public Map getbasicGrindingList()
	{
		return this.grindingList;
	}

}
