package revolution.revelation.foundation.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class playerTeleposer extends Block{

	public playerTeleposer(Material materialIn) {
		super(materialIn);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.1F, 1.0F);
	}
	
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		System.out.println("Test");
		EntityPlayer player = (EntityPlayer)entity;
		if (player.isSneaking())
		{
			double posX = player.getHeldItem().getTagCompound().getInteger("posX");
			double posY = player.getHeldItem().getTagCompound().getInteger("posY");
			double posZ = player.getHeldItem().getTagCompound().getInteger("posZ");
			
			player.posX = posX;
			player.posY = posY;
			player.posZ = posZ;
			System.out.println("TPing");
		}
	}
	
	public boolean isPassable(World world, BlockPos pos)
	{
		return true;
	}
	
	public boolean isOpaqueCube()
    {
        return false;
    }

	public boolean isFullCube()
	{
		return false;
	}

}
