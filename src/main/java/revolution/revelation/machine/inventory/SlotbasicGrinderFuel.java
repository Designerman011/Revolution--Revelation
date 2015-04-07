package revolution.revelation.machine.inventory;

import revolution.revelation.machine.tileentity.TileEntitybasicGrinder;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotbasicGrinderFuel extends Slot {
	
	public SlotbasicGrinderFuel(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
	{
		super(inventoryIn, slotIndex, xPosition, yPosition);
	}
	
	public boolean isItemValid(ItemStack stack)
	{
		return TileEntitybasicGrinder.isItemFuel(stack);
	}
	
	public int getItemStackLimit(ItemStack stack)
	{
		return super.getItemStackLimit(stack);
	}

}
