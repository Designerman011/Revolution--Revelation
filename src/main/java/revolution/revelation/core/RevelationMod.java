package revolution.revelation.core;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import revolution.revelation.core.proxy.CommonProxy;
import revolution.revelation.core.world.RevelationWorldGenerator;
import revolution.revelation.foundation.init.FoundationBlocks;
import revolution.revelation.foundation.init.FoundationItems;
import revolution.revelation.machine.init.MachineBlocks;
import revolution.revelation.machine.tileentity.TileEntitybasicGrinder;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class RevelationMod {
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	private static Map classToNameMap = Maps.newHashMap();
	private static Map nameToClassMap = Maps.newHashMap();

	@Instance(Reference.MOD_ID)
	public static RevelationMod instance;
	  
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		//Register Tile Entity
		TileEntity.addMapping(TileEntitybasicGrinder.class, "basicGrinder");
		//Register Blocks and Items
		FoundationBlocks.init();
		FoundationBlocks.register();
		FoundationItems.init();
		FoundationItems.register();
		MachineBlocks.init();
		MachineBlocks.register();
		//Register World Generator
		RevelationWorldGenerator.MainRegistry();
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(RevelationMod.instance, new GuiHandler());
		proxy.registerRenders();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
	
	public enum GUI_ENUM
	{
		BASICGRINDER
	}

}
