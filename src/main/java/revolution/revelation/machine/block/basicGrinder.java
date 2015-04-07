package revolution.revelation.machine.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import revolution.revelation.core.RevelationMod;
import revolution.revelation.foundation.init.FoundationBlocks;
import revolution.revelation.machine.init.MachineBlocks;
import revolution.revelation.machine.tileentity.TileEntitybasicGrinder;

public class basicGrinder extends BlockContainer{
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	private static boolean keepInventory;
	private static boolean hasTileEntity;
	
	public basicGrinder() {
		super(Material.iron);
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(MachineBlocks.basicGrinder);
	}
	
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		this.setDefaultFacing(worldIn, pos, state);
	}
	
	private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
	{
		if(!worldIn.isRemote)
		{
			Block block = worldIn.getBlockState(pos.north()).getBlock();
			Block block1 = worldIn.getBlockState(pos.south()).getBlock();
			Block block2 = worldIn.getBlockState(pos.west()).getBlock();
			Block block3 = worldIn.getBlockState(pos.east()).getBlock();
			EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
			
			if (enumfacing == EnumFacing.NORTH && block.isFullBlock() && !block1.isFullBlock())
			{
				enumfacing = EnumFacing.SOUTH;
			}
			else if (enumfacing == EnumFacing.SOUTH && block.isFullBlock() && !block1.isFullBlock())
			{
				enumfacing = EnumFacing.NORTH;
			}
			else if (enumfacing == EnumFacing.WEST && block.isFullBlock() && !block1.isFullBlock())
			{
				enumfacing = EnumFacing.EAST;
			}
			else if (enumfacing == EnumFacing.EAST && block.isFullBlock() && !block1.isFullBlock())
			{
				enumfacing = EnumFacing.WEST;
			}
			
			worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntitybasicGrinder();
	}
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		 if (!worldIn.isRemote)
	        {
	            playerIn.openGui(RevelationMod.instance, RevelationMod.GUI_ENUM.BASICGRINDER.ordinal(), worldIn, pos.getX(), pos.getY(), pos.getZ());
	        }
		 return true;
	}
	
	public static void setState(boolean active, World worldIn, BlockPos pos)
	{
		IBlockState iblockstate = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		keepInventory = true;
		
		if (active)
		{
			worldIn.setBlockState(pos, MachineBlocks.basicGrinder.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
		}
		else
		{
			worldIn.setBlockState(pos, MachineBlocks.basicGrinder.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
		}
		
		keepInventory = false;
		
		if (tileentity != null)
		{
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}
	
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase player, ItemStack stack)
	{
		worldIn.setBlockState(pos, state.withProperty(FACING, player.getHorizontalFacing().getOpposite()), 2);
		
		if (stack.hasDisplayName())
		{
			TileEntity tileentity = worldIn.getTileEntity(pos);
			
			if (tileentity instanceof TileEntitybasicGrinder)
			{
				((TileEntitybasicGrinder)tileentity).setCustomInventoryName(stack.getDisplayName());
			}
		}
	}

	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		if(!keepInventory)
		{
			TileEntity tileentity = worldIn.getTileEntity(pos);
			
			if (tileentity instanceof TileEntitybasicGrinder)
			{
				InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntitybasicGrinder)tileentity);
				worldIn.updateComparatorOutputLevel(pos, this);
			}
		}
		
		super.breakBlock(worldIn, pos, state);
	}
	
	public boolean hasComparatorInputOverride()
	{
		return true;
	}
	
	public int getComparatorInputOverride(World worldIn, BlockPos pos)
	{
		return Container.calcRedstone(worldIn.getTileEntity(pos));
	}
	
	@SideOnly(Side.CLIENT)
	public Item getItem(World worldIn, BlockPos pos)
	{
		return Item.getItemFromBlock(MachineBlocks.basicGrinder);
	}
	
	public int getRenderType()
	{
		return 3;
	}
	
	@SideOnly(Side.CLIENT)
	public IBlockState getStateForEntityRender(IBlockState state)
	{
		return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
	}
	
	public IBlockState getStateFromMeta(int meta)
	{
		EnumFacing enumfacing = EnumFacing.getFront(meta);
		
		if (enumfacing.getAxis() == EnumFacing.Axis.Y)
		{
			enumfacing = EnumFacing.NORTH;
		}
		
		return this.getDefaultState().withProperty(FACING, enumfacing);
	}
	
	public int getMetaFromState(IBlockState state)
	{
		return ((EnumFacing)state.getValue(FACING)).getIndex();
	}
	
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] {FACING});
	}
	
	@SideOnly(Side.CLIENT)
    static final class SwitchEnumFacing
        {
            static final int[] FACING_LOOKUP = new int[EnumFacing.values().length];

            static
            {
                try
                {
                    FACING_LOOKUP[EnumFacing.WEST.ordinal()] = 1;
                }
                catch (NoSuchFieldError var4)
                {
                    ;
                }

                try
                {
                    FACING_LOOKUP[EnumFacing.EAST.ordinal()] = 2;
                }
                catch (NoSuchFieldError var3)
                {
                    ;
                }

                try
                {
                    FACING_LOOKUP[EnumFacing.NORTH.ordinal()] = 3;
                }
                catch (NoSuchFieldError var2)
                {
                    ;
                }

                try
                {
                    FACING_LOOKUP[EnumFacing.SOUTH.ordinal()] = 4;
                }
                catch (NoSuchFieldError var1)
                {
                    ;
                }
            }
        }
}
