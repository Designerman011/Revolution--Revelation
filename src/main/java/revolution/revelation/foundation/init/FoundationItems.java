package revolution.revelation.foundation.init;

import revolution.revelation.core.Reference;
import revolution.revelation.foundation.creativetools.silversSword;
import revolution.revelation.foundation.items.dustAluminum;
import revolution.revelation.foundation.items.dustBrass;
import revolution.revelation.foundation.items.dustBronze;
import revolution.revelation.foundation.items.dustCopper;
import revolution.revelation.foundation.items.dustLead;
import revolution.revelation.foundation.items.dustPureBrass;
import revolution.revelation.foundation.items.dustPureBronze;
import revolution.revelation.foundation.items.dustPureSteel;
import revolution.revelation.foundation.items.dustSilver;
import revolution.revelation.foundation.items.dustSteel;
import revolution.revelation.foundation.items.dustTin;
import revolution.revelation.foundation.items.ingotAluminum;
import revolution.revelation.foundation.items.ingotBrass;
import revolution.revelation.foundation.items.ingotBronze;
import revolution.revelation.foundation.items.ingotCopper;
import revolution.revelation.foundation.items.ingotLead;
import revolution.revelation.foundation.items.ingotSilver;
import revolution.revelation.foundation.items.ingotSteel;
import revolution.revelation.foundation.items.ingotTin;
import revolution.revelation.foundation.items.locationMarker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FoundationItems {
	/*
	 * Aluminum Brass = 3 aluminum, 1 copper
	 */
	//Ingots
	public static Item ingotAluminum; //ND T
	public static Item ingotBrass; //ND T
	public static Item ingotBronze; //ND T
	public static Item ingotCopper; //ND T
	public static Item ingotLead; //ND T
	public static Item ingotSilver; //ND T
	public static Item ingotSteel; //ND T
	public static Item ingotTin; //ND T
	
	//Dust
	public static Item dustAluminum; //ND T
	public static Item dustBrass; //ND T
	public static Item dustBronze; //ND T
	public static Item dustCopper; //ND T
	public static Item dustLead; //ND T
	public static Item dustPureBrass; //ND T
	public static Item dustPureBronze; //ND T
	public static Item dustPureSteel; //ND T
	public static Item dustSilver; //ND T
	public static Item dustSteel; //ND T
	public static Item dustTin; //ND T
	
	//Other
	public static Item locationMarker;
	
	//Creative Tools
	public static Item silversSword;
	
	//Tool Materials (Name, Mining Level, Durability, Mining efficiency, Weapon Damage + 4, Enchantibility)
	public static final Item.ToolMaterial creativeToolMaterial = EnumHelper.addToolMaterial("creativeToolMaterial", 100, 999999999, 500.0F, 50000.0F, 8000);

	public static void init()
	{
		//Ingots
		ingotAluminum = new ingotAluminum().setUnlocalizedName("ingotAluminum");
		ingotBrass = new ingotBrass().setUnlocalizedName("ingotBrass");
		ingotBronze = new ingotBronze().setUnlocalizedName("ingotBronze");
		ingotCopper = new ingotCopper().setUnlocalizedName("ingotCopper");
		ingotLead = new ingotLead().setUnlocalizedName("ingotLead");
		ingotSilver = new ingotSilver().setUnlocalizedName("ingotSilver");
		ingotSteel = new ingotSteel().setUnlocalizedName("ingotSteel");
		ingotTin = new ingotTin().setUnlocalizedName("ingotTin");
		
		//Dust
		dustAluminum = new dustAluminum().setUnlocalizedName("dustAluminum");
		dustBrass = new dustBrass().setUnlocalizedName("dustBrass");
		dustBronze = new dustBronze().setUnlocalizedName("dustBronze");
		dustCopper = new dustCopper().setUnlocalizedName("dustCopper");
		dustLead = new dustLead().setUnlocalizedName("dustLead");
		dustPureBrass = new dustPureBrass().setUnlocalizedName("dustPureBrass");
		dustPureBronze = new dustPureBronze().setUnlocalizedName("dustPureBronze");
		dustPureSteel = new dustPureSteel().setUnlocalizedName("dustPureSteel");
		dustSilver = new dustSilver().setUnlocalizedName("dustSilver");
		dustSteel = new dustSteel().setUnlocalizedName("dustSteel");
		dustTin = new dustTin().setUnlocalizedName("dustTin");
		
		//Other
		locationMarker = new locationMarker().setUnlocalizedName("locationMarker");
		
		//Creative Tools
		silversSword = new silversSword(creativeToolMaterial).setUnlocalizedName("silversSword");
	}
	
	public static void register()
	{
		//Ingots
		GameRegistry.registerItem(ingotAluminum, ingotAluminum.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(ingotBrass, ingotBrass.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(ingotBronze, ingotBronze.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(ingotCopper, ingotCopper.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(ingotLead, ingotLead.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(ingotSilver, ingotSilver.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(ingotSteel, ingotSteel.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(ingotTin, ingotTin.getUnlocalizedName().substring(5));
		
		//Dust
		GameRegistry.registerItem(dustAluminum, dustAluminum.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(dustBrass, dustBrass.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(dustBronze, dustBronze.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(dustCopper, dustCopper.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(dustLead, dustLead.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(dustPureBrass, dustPureBrass.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(dustPureBronze, dustPureBronze.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(dustPureSteel, dustPureSteel.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(dustSilver, dustSilver.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(dustSteel, dustSteel.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(dustTin, dustTin.getUnlocalizedName().substring(5));
		
		//Other
		GameRegistry.registerItem(locationMarker, locationMarker.getUnlocalizedName().substring(5));
		
		//Creative Tools
		GameRegistry.registerItem(silversSword, silversSword.getUnlocalizedName().substring(5));
	}
	
	public static void registerRenders()
	{
		//Ingots
		registerRender(ingotAluminum);
		registerRender(ingotBrass);
		registerRender(ingotBronze);
		registerRender(ingotCopper);
		registerRender(ingotLead);
		registerRender(ingotSilver);
		registerRender(ingotSteel);
		registerRender(ingotTin);
		
		//Dust
		registerRender(dustAluminum);
		registerRender(dustBrass);
		registerRender(dustBronze);
		registerRender(dustCopper);
		registerRender(dustLead);
		registerRender(dustPureBrass);
		registerRender(dustPureBronze);
		registerRender(dustPureSteel);
		registerRender(dustSilver);
		registerRender(dustSteel);
		registerRender(dustTin);
		
		//Other
		registerRender(locationMarker);
		
		//Creative Tools
		registerRender(silversSword);
	}
	
	public static void registerRender(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
	
}
