package revolution.revelation.core;

import revolution.revelation.machine.inventory.ContainerbasicGrinder;
import revolution.revelation.machine.tileentity.TileEntitybasicGrinder;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

@SideOnly(Side.CLIENT)
public class GuibasicGrinder extends GuiContainer{

	private static final ResourceLocation grinderGuiTextures = new ResourceLocation("textures/gui/container/furnace.png");
	private final InventoryPlayer inventoryPlayer;
	private final IInventory tilebasicGrinder;
	
	public GuibasicGrinder(InventoryPlayer invPlayer, IInventory tile) {
		super(new ContainerbasicGrinder(invPlayer, tile));
		inventoryPlayer = invPlayer;
		this.tilebasicGrinder = tile;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		String s = tilebasicGrinder.getDisplayName().getUnformattedText();
		fontRendererObj.drawString(s, xSize/2-fontRendererObj.getStringWidth(s)/2, 6, 4210752);
		fontRendererObj.drawString(inventoryPlayer.getDisplayName().getUnformattedText(), 8, ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(grinderGuiTextures);
		int marginHorizontal = (width - xSize) / 2;
		int marginVertical = (height - ySize) / 2;
		drawTexturedModalRect(marginHorizontal, marginVertical, 0, 0, xSize, ySize);
		
		int progressLevel = getProgressLevel(24);
		drawTexturedModalRect(marginHorizontal + 79, marginVertical + 34, 176, 14, progressLevel + 1, 16);
	}
	
	private int getProgressLevel(int progressIndicatorPixelWidth)
	{
		int ticksGrindingItemSoFar = tilebasicGrinder.getField(2);
		int ticksPerItem = tilebasicGrinder.getField(3);
		return ticksPerItem != 0 && ticksGrindingItemSoFar != 0 ? ticksGrindingItemSoFar * progressIndicatorPixelWidth / ticksPerItem : 0;
	}
	

}
