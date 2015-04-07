package revolution.revelation.machine.init;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import revolution.revelation.core.Reference;
import revolution.revelation.machine.block.basicGrinder;

public class MachineBlocks {
	
	//Sounds
	private static final Block.SoundType soundTypeStone = new Block.SoundType("stone", 1.0F, 1.0F);
	private static final Block.SoundType soundTypeGravel = new Block.SoundType("gravel", 1.0F, 1.0F);
	private static final Block.SoundType soundTypeCloth = new Block.SoundType("cloth", 1.0F, 1.0F);
    private static final Block.SoundType soundTypeMetal = new Block.SoundType("stone", 1.0F, 1.5F);
    
    //Grinder
    public static Block basicGrinder;
    
    public static void init()
    {
    	//Grinder
    	basicGrinder = new basicGrinder().setUnlocalizedName("basicGrinder").setStepSound(soundTypeMetal);
    }
    
    public static void register()
    {
    	GameRegistry.registerBlock(basicGrinder, basicGrinder.getUnlocalizedName().substring(5));
    }
    
    public static void registerRenders()
    {
    	registerRender(basicGrinder);
    }
    
    public static void registerRender(Block block)
    {
    	Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }

}
