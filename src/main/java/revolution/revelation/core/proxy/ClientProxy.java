package revolution.revelation.core.proxy;

import revolution.revelation.foundation.init.FoundationBlocks;
import revolution.revelation.foundation.init.FoundationItems;
import revolution.revelation.machine.init.MachineBlocks;

public class ClientProxy extends CommonProxy{
	@Override
	public void registerRenders(){
		FoundationBlocks.registerRenders();
		FoundationItems.registerRenders();
		MachineBlocks.registerRenders();
	}
}
