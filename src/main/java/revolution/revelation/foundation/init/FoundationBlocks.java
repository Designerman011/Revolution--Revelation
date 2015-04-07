package revolution.revelation.foundation.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import revolution.revelation.core.Reference;
import revolution.revelation.foundation.blocks.oreAluminum;
import revolution.revelation.foundation.blocks.oreCopper;
import revolution.revelation.foundation.blocks.oreLead;
import revolution.revelation.foundation.blocks.oreSilver;
import revolution.revelation.foundation.blocks.oreTin;
import revolution.revelation.foundation.blocks.playerTeleposer;
import revolution.revelation.machine.block.basicGrinder;

public class FoundationBlocks {
	
	//Sounds
	private static final Block.SoundType soundTypeStone = new Block.SoundType("stone", 1.0F, 1.0F);
	private static final Block.SoundType soundTypeGravel = new Block.SoundType("gravel", 1.0F, 1.0F);
	private static final Block.SoundType soundTypeCloth = new Block.SoundType("cloth", 1.0F, 1.0F);
    private static final Block.SoundType soundTypeMetal = new Block.SoundType("stone", 1.0F, 1.5F);
	
	//Ores
    public static Block oreAluminum; //ND TC
	public static Block oreCopper; //ND TC
	public static Block oreLead; //ND TC
	public static Block oreSilver; //ND TC
	public static Block oreTin; //ND TC
	
	//Other
	public static Block playerTeleposer;
	
	
	
	public static void init()
	{
		//Ores
		oreAluminum = new oreAluminum(Material.rock).setUnlocalizedName("oreAluminum").setStepSound(soundTypeStone);
		oreCopper = new oreCopper(Material.rock).setUnlocalizedName("oreCopper").setStepSound(soundTypeStone);
		oreLead = new oreLead(Material.rock).setUnlocalizedName("oreLead").setStepSound(soundTypeStone);
		oreSilver = new oreSilver(Material.rock).setUnlocalizedName("oreSilver").setStepSound(soundTypeStone);
		oreTin = new oreTin(Material.rock).setUnlocalizedName("oreTin").setStepSound(soundTypeStone);
		
		//Other
		playerTeleposer = new playerTeleposer(Material.rock).setUnlocalizedName("playerTeleposer").setStepSound(soundTypeStone);
		

	}
	
	public static void register()
	{
		//Ores
		GameRegistry.registerBlock(oreAluminum, oreAluminum.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(oreCopper, oreCopper.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(oreLead, oreLead.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(oreSilver, oreSilver.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(oreTin, oreTin.getUnlocalizedName().substring(5));
		
		//Other
		GameRegistry.registerBlock(playerTeleposer, playerTeleposer.getUnlocalizedName().substring(5));

	}
	
	public static void registerRenders()
	{
		//Ores
		registerRender(oreAluminum);
		registerRender(oreCopper);
		registerRender(oreLead);
		registerRender(oreSilver);
		registerRender(oreTin);
		
		//Other
		registerRender(playerTeleposer);

	}

	public static void registerRender(Block block)
	{
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
	
}
