package revolution.revelation.foundation.creativetools;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class silversSword extends ItemSword{
	public silversSword(ToolMaterial material) {
		super(material);
		
	}

	public boolean hasEffect(ItemStack stack)
	{
		return true;
	}
}
