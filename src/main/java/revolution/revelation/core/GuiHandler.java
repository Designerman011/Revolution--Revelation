package revolution.revelation.core;

import revolution.revelation.machine.inventory.ContainerbasicGrinder;
import revolution.revelation.machine.tileentity.TileEntitybasicGrinder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiHandler implements IGuiHandler{
	

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
		if (tileEntity != null)
		{
			if (ID == RevelationMod.GUI_ENUM.BASICGRINDER.ordinal())
			{
				return new ContainerbasicGrinder(player.inventory, (IInventory)tileEntity);
			}
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
		if (tileEntity != null)
		{
			if (ID == RevelationMod.GUI_ENUM.BASICGRINDER.ordinal())
			{
				return new ContainerbasicGrinder(player.inventory, (IInventory)tileEntity);
			}
		}
		return null;
	}

}
