package revolution.revelation.foundation.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class locationMarker extends Item
{
	//WIP
	
	//When Right Clicked, adds NBT storing location clicked at and changes Name to aqua color
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		if (!playerIn.isSneaking())
		{
			if(stack.getTagCompound() == null)
			{
				stack.setTagCompound(new NBTTagCompound());
			}
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setInteger("dim", playerIn.dimension);
			nbt.setInteger("posX", pos.getX());
			nbt.setInteger("posY", pos.getY() + 1);
			nbt.setInteger("posZ", pos.getZ());
			stack.getTagCompound().setTag("coords", nbt);
			stack.setStackDisplayName(EnumChatFormatting.AQUA + "Location Marker");
		}
        return false;
    }
	
	//Clears NBT on Sneak Click
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World worldIn, EntityPlayer playerIn)
    {
		if (playerIn.isSneaking())
		{
			if (stack.getTagCompound() != null)
			{
				stack.getTagCompound().removeTag("coords");
				stack.clearCustomName();
			}
		}
        return stack;
    }
	
	//Adds tooltip displaying stored coord data
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) 
	{
		if (stack.getTagCompound() != null)
		{
			if (stack.getTagCompound().hasKey("coords"))
			{
				NBTTagCompound nbt = (NBTTagCompound)stack.getTagCompound().getTag("coords");
				int dim = nbt.getInteger("dim");
				int posX = nbt.getInteger("posX");
				int posY = nbt.getInteger("posY");
				int posZ = nbt.getInteger("posZ");
				tooltip.add("Dim: " + dim + " X: " + posX + " Y: " + posY + " Z: " + posZ);
			}
		}
	}

	//Makes item have enchanted effect when NBT data is stored in it
	@Override
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
		if (stack.getTagCompound() != null)
		{
			return stack.getTagCompound().hasKey("coords");
		}
        return false;
    }
}
