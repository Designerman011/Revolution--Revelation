package revolution.revelation.machine.tileentity;

import java.util.List;

import revolution.revelation.machine.block.basicGrinder;
import revolution.revelation.machine.inventory.ContainerbasicGrinder;
import revolution.revelation.machine.inventory.SlotbasicGrinderFuel;
import revolution.revelation.machine.recipes.basicGrinderRecipes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.google.common.collect.Lists;

public class TileEntitybasicGrinder extends TileEntityLockable implements IUpdatePlayerListBox, ISidedInventory {

	private ItemStack[] inventory;
	private static final int[] slotsTop = new int[] {0};
	private static final int[] slotsBottom = new int[] {2, 1};
	private static final int[] slotsSides = new int[] {1};
	private int furnaceBurnTime;
	private int furnaceItemBurnTime;
	private int cookTime;
	private int totalCookTime;
	private String grinderCustomName;
	private static List<IFuelHandler> fuelHandlers = Lists.newArrayList();
	
	public TileEntitybasicGrinder()
	{
		inventory = new ItemStack[3];
	}
	
	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return inventory[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (this.inventory[index] != null)
		{
			ItemStack itemstack;
			
			if (this.inventory[index].stackSize <= count)
			{
				itemstack = this.inventory[index];
				this.inventory[index] = null;
				return itemstack;
			}
			else
			{
				itemstack = this.inventory[index].splitStack(count);
				
				if (this.inventory[index].stackSize == 0)
				{
					this.inventory[index] = null;
				}
				
				return itemstack;
			}
		}
		else
		{
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		if (this.inventory[index] != null)
		{
			ItemStack itemstack = this.inventory[index];
			this.inventory[index] = null;
			return itemstack;
		}
		else
		{
		return null;
		}
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		boolean flag = stack != null && stack.isItemEqual(this.inventory[index]) && ItemStack.areItemStacksEqual(stack, this.inventory[index]);
		this.inventory[index] = stack;
		
		if (stack != null && stack.stackSize > this.getInventoryStackLimit())
		{
			stack.stackSize = this.getInventoryStackLimit();
		}
		
		if (index == 0 && !flag)
		{
			this.totalCookTime = this.totalRun(stack);
			this.cookTime = 0;
			this.markDirty();
		}
	}
	
	@Override
	public String getName() {
		return this.hasCustomName() ? this.grinderCustomName : "container.basicgrinder";
 	}
	
	@Override
	public boolean hasCustomName() {
		return this.grinderCustomName != null && this.grinderCustomName.length() > 0;
	}
	
	public void setCustomInventoryName(String basicGrinder)
	{
		this.grinderCustomName = basicGrinder;
	}
	
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		NBTTagList nbttaglist = compound.getTagList("Items", 10);
		this.inventory = new ItemStack[this.getSizeInventory()];
		
		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound.getByte("Slot");
			
			if (b0 >= 0 && b0 < this.inventory.length)
			{
				this.inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound);
			}
		}
		
		this.furnaceBurnTime = compound.getShort("RunTime");
		this.cookTime = compound.getShort("OperateTime");
		this.totalCookTime = compound.getShort("OperateTimeTotal");
		this.furnaceItemBurnTime = getItemRunTime(this.inventory[1]);
		
		if (compound.hasKey("CustomName", 8))
		{
			this.grinderCustomName = compound.getString("CustomName");
		}
	}
	
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setShort("RunTime", (short)this.furnaceBurnTime);
		compound.setShort("OperateTime", (short)this.cookTime);
		compound.setShort("OperateTimeTotal", (short)this.totalCookTime);
		NBTTagList nbttaglist = new NBTTagList();
		
		for (int i = 0; i < this.inventory.length; ++i)
		{
			if (this.inventory[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				this.inventory[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		
		compound.setTag("Items", nbttaglist);
		
		if (this.hasCustomName())
		{
			compound.setString("CustomName", this.grinderCustomName);
		}
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	public boolean isRunning()
	{
		return this.furnaceBurnTime > 0;
	}
	
	
	@SideOnly(Side.CLIENT)
	public static boolean isRunning(IInventory p_174903_0_)
	{
		return p_174903_0_.getField(0) > 0;
	}

	
	public void update() {
		boolean flag = this.isRunning();
		boolean flag1 = false;
		
		if (this.isRunning())
		{
			--this.furnaceBurnTime;
		}
		
		if (!this.worldObj.isRemote)
		{
			if (!this.isRunning() && (this.inventory[1] == null || this.inventory[0] == null))
			{
				if (!this.isRunning() && this.cookTime > 0)
				{
					this.cookTime = MathHelper.clamp_int(this.cookTime - 2, 0, this.totalCookTime);
				}
			}
			else
			{
				if (!this.isRunning() && this.canSmelt())
				{
					this.furnaceItemBurnTime = this.furnaceBurnTime = getItemRunTime(this.inventory[1]);
					
					if (this.isRunning())
					{
						flag1 = true;
								
						if (this.inventory[1] != null)
						{
							--this.inventory[1].stackSize;
							
							if (this.inventory[1].stackSize == 0)
							{
								this.inventory[1] = inventory[1].getItem().getContainerItem(inventory[1]);
							}
						}
					}
				}
				
				if (this.isRunning() && this.canSmelt())
				{
					++this.cookTime;
					
					if (this.cookTime == this.totalCookTime)
					{
						this.cookTime = 0;
						this.totalCookTime = this.totalRun(this.inventory[0]);
						this.smeltItem();
						flag1 = true;
					}
				}
				else
				{
					this.cookTime = 0;
				}
			}
			
			if (flag != this.isRunning())
			{
				flag1 = true;
				basicGrinder.setState(this.isRunning(), this.worldObj, this.pos);
			}
		}
		
		if (flag1)
		{
			this.markDirty();
		}
		return;
	}

	public int totalRun(ItemStack stack)
	{
		return 200;
	}
	
	private boolean canSmelt()
	{
		if (this.inventory[0] == null)
		{
			return false;
		}
		else
		{
			ItemStack itemstack = basicGrinderRecipes.instance().getGrindingResult(this.inventory[0]);
			if (itemstack == null) return false;
			if (this.inventory[2] == null) return true;
			if (!this.inventory[2].isItemEqual(itemstack)) return false;
			int result = inventory[2].stackSize + itemstack.stackSize;
			return result <= getInventoryStackLimit() && result <= this.inventory[2].getMaxStackSize();
		}
	}
	
	public void smeltItem()
	{
		if (this.canSmelt())
		{
			ItemStack itemstack = basicGrinderRecipes.instance().getGrindingResult(this.inventory[0]);
			
			if (this.inventory[2] == null)
			{
				this.inventory[2] = itemstack.copy();
			}
			else if (this.inventory[2].getItem() == itemstack.getItem())
			{
				this.inventory[2].stackSize += itemstack.stackSize;
			}
			
			--this.inventory[0].stackSize;
			
			if (this.inventory[0].stackSize <= 0)
			{
				this.inventory[0] = null;
			}
		}
	}
	
	public static int getItemRunTime(ItemStack stack)
	{
		if (stack != null)
		{
			//Item Run Time : Form Of ( if (item == class.item) return runTime; )
			Item item = stack.getItem();
			//Temp testing fuel... WILL NOT be final fuel for this
			if (item == Items.coal) return 1600;
		}
		else
		{
			return 0;
		}
		return TileEntitybasicGrinder.getFuelValue(stack);
	}
	
	public static void registerFuelHandler(IFuelHandler handler)
	{
		fuelHandlers.add(handler);
	}
	
	public static int getFuelValue(ItemStack itemStack)
	{
		int fuelValue = 0;
		for (IFuelHandler handler : fuelHandlers)
		{
			fuelValue = Math.max(fuelValue, handler.getBurnTime(itemStack));
		}
		return fuelValue;
	}
	
	public static boolean isItemFuel(ItemStack stack)
	{
		return getItemRunTime(stack) > 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return index == 2 ? false : (index != 1 ? true : isItemFuel(stack));
	}
	
	public int[] getSlotsForFace(EnumFacing side)
	{
		return side == EnumFacing.DOWN ? slotsBottom : (side == EnumFacing.UP ? slotsTop : slotsSides);
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
	{
		return this.isItemValidForSlot(index, itemStackIn);
	}
	
	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
	{
		if (direction == EnumFacing.DOWN && index == 1)
		{
			Item item = stack.getItem();
			return false;
		}
		
		return true;
	}
	
	@Override
	public String getGuiID() {
		//Temp testing GUI, need to make new one and designate it
		return "revelation:furnace";
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory,
			EntityPlayer playerIn) {
		return new ContainerbasicGrinder(playerInventory, this);
	}
	
	@Override
	public int getField(int id) {
		switch (id)
		{
		case 0:
			return this.furnaceBurnTime;
		case 1:
			return this.furnaceItemBurnTime;
		case 2:
			return this.cookTime;
		case 3:
			return this.totalCookTime;
		default:
			return 0;	
		}
	}

	@Override
	public void setField(int id, int value) {
		switch (id)
		{
		case 0:
			this.furnaceBurnTime = value;
			break;
		case 1:
			this.furnaceItemBurnTime = value;
			break;
		case 2:
			this.cookTime = value;
			break;
		case 3:
			this.totalCookTime = value;
		}
	}

	@Override
	public int getFieldCount() {
		return 4;
	}
	
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate)
    {
        return (oldState.getBlock() != newSate.getBlock());
    }

	@Override
	public void clear() {
		for (int i = 0; i < this.inventory.length; ++i)
		{
			this.inventory[i] = null;
		}
	}
}