package revolution.revelation.core.world;

import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RevelationWorldGenerator {
	public static void MainRegistry(){
		registerWorldGen(new WorldOreGen(), 1);
	}
	
	public static void registerWorldGen(IWorldGenerator iGenerator, int weightedProbability){
		GameRegistry.registerWorldGenerator(iGenerator, weightedProbability);
	}
}
