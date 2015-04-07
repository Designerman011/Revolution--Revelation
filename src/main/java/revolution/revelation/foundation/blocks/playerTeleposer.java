package revolution.revelation.foundation.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class playerTeleposer extends Block{

	public playerTeleposer(Material materialIn) {
		super(materialIn);
		
	}
	
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		EntityPlayer player = (EntityPlayer)entity;
		if (player.isSneaking())
		{
			int posX = player.getHeldItem().getTagCompound().getInteger("posX");
			int posY = player.getHeldItem().getTagCompound().getInteger("posY");
			int posZ = player.getHeldItem().getTagCompound().getInteger("posZ");
			
			player.posX = posX;
			player.posY = posY;
			player.posZ = posZ;
		}
	}

}
