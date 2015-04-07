package revolution.revelation.machine.inventory;

import revolution.revelation.machine.recipes.basicGrinderRecipes;
import revolution.revelation.machine.tileentity.TileEntitybasicGrinder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerbasicGrinder extends Container {
	private final IInventory tilebasicGrinder;
	private final int sizeInventory;
	private int field_178152_f;
	private int field_178153_g;
	private int field_178154_h;
	private int field_178155_i;
	
	public ContainerbasicGrinder(InventoryPlayer p_i45794_1_, IInventory grinderInv)
	{
		this.tilebasicGrinder = grinderInv;
		sizeInventory = tilebasicGrinder.getSizeInventory();
		this.addSlotToContainer(new Slot(tilebasicGrinder, 0, 56, 17));
		this.addSlotToContainer(new SlotbasicGrinderFuel(tilebasicGrinder, 1, 56, 53));
		this.addSlotToContainer(new SlotFurnaceOutput(p_i45794_1_.player, tilebasicGrinder, 2, 116, 35));
		int i;
		
		for (i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(p_i45794_1_, j + i * 9 + 9, 8 + j * 18, 84 + i *18));
			}
		}
		
		for (i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(p_i45794_1_, i, 8+ i * 18, 142));
		}
	}
	
	public void addCraftingToCrafters(ICrafting listener)
	{
		super.addCraftingToCrafters(listener);
		listener.func_175173_a(this, this.tilebasicGrinder);
	}
	
	public void detectAndSendChanged()
	{
		super.detectAndSendChanges();
		
		for (int i = 0; i < this.crafters.size(); ++i)
		{
			ICrafting icrafting = (ICrafting)this.crafters.get(i);
			
			if (this.field_178152_f != this.tilebasicGrinder.getField(2))
			{
				icrafting.sendProgressBarUpdate(this, 2, this.tilebasicGrinder.getField(2));
			}
			if (this.field_178154_h != this.tilebasicGrinder.getField(0))
			{
				icrafting.sendProgressBarUpdate(this, 0, this.tilebasicGrinder.getField(0));
			}
			if (this.field_178155_i != this.tilebasicGrinder.getField(1))
			{
				icrafting.sendProgressBarUpdate(this, 1, this.tilebasicGrinder.getField(1));
			}
			if (this.field_178153_g != this.tilebasicGrinder.getField(3))
			{
				icrafting.sendProgressBarUpdate(this, 3, this.tilebasicGrinder.getField(3));
			}
		}
		field_178152_f = tilebasicGrinder.getField(2);
		field_178154_h = tilebasicGrinder.getField(0);
		field_178155_i = tilebasicGrinder.getField(1);
		field_178153_g = tilebasicGrinder.getField(3);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data)
	{
		this.tilebasicGrinder.setField(id, data);
	}

	
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.tilebasicGrinder.isUseableByPlayer(playerIn);
	}
	
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(index);
		
		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if (index == 2)
			{
				if (!this.mergeItemStack(itemstack1, 3, 39, true))
				{
					return null;
				}
				
				slot.onSlotChange(itemstack1, itemstack);
			}
			else if (index != 1 && index != 0)
			{
				if (basicGrinderRecipes.instance().getGrindingResult(itemstack1) != null)
				{
					if (!this.mergeItemStack(itemstack1, 0, 1, false))
					{
						return null;
					}
				}
				else if (TileEntitybasicGrinder.isItemFuel(itemstack1))
				{
					if (!this.mergeItemStack(itemstack1, 1, 2, false))
					{
						return null;
					}
				}
				else if (index >= 3 && index < 30)
				{
					if (!this.mergeItemStack(itemstack1, 30, 39, false))
					{
						return null;
					}
				}
				else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(itemstack1, 3, 39, false))
			{
				return null;
			}
			
			if (itemstack1.stackSize == 0)
			{
				slot.putStack((ItemStack)null);
			}
			else
			{
				slot.onSlotChanged();
			}
			
			if (itemstack1.stackSize == itemstack.stackSize)
			{
				return null;
			}
			
			slot.onPickupFromSlot(playerIn, itemstack1);
		}
		
		return itemstack;
	}

}
