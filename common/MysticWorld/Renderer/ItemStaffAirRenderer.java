package mysticworld.renderer;

import mysticworld.model.ModelStaff;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

public class ItemStaffAirRenderer implements IItemRenderer {
	private final ModelStaff model;

	public ItemStaffAirRenderer() {
		this.model = new ModelStaff();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch (type) {
		case ENTITY:
			return true;
		case EQUIPPED:
			return true;
		case EQUIPPED_FIRST_PERSON:
			return true;
		case INVENTORY:
			return true;
		default:
			return false;
		}
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch (type) {
		case ENTITY:
			renderModel(0.0F, 1.2F, 0.0F, 1.0F);
			break;
		case INVENTORY:
			renderModel(0.5F, 0.95F, 0.5F, 1.0F);
			break;
		case EQUIPPED_FIRST_PERSON:
			renderModel(1.0F, 1.2f, 0.0f, 1.0f);
			break;
		case EQUIPPED:
			renderModelEquipped(0.0F, 0.9f, 0.0f, 1.0f);
			break;
		default:
			break;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		switch (type) {
		case ENTITY:
			return true;
		case EQUIPPED:
			return false;
		case INVENTORY:
			return true;
		case EQUIPPED_FIRST_PERSON:
			return false;
		default:
			return false;
		}
	}

	private void renderModel(float x, float y, float z, float scale) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glScalef(scale, scale, scale);
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 0.0F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation("mysticmods:textures" + "/models/StaffAir.png"));
		model.renderModel(0.0625f);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

	private void renderModelEquipped(float x, float y, float z, float scale) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glScalef(scale, scale, scale);
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation("mysticmods:textures" + "/models/StaffAir.png"));
		model.renderModel(0.0625f);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
}
