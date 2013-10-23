package mysticworld.items;

import mysticworld.MysticWorld;
import mysticworld.entity.EntityChargeFire;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

public class ItemStaffFire extends ItemStaff {
	public ItemStaffFire(int id) {
		super(id);
	}

	public boolean itemInteractionForEntity(ItemStack itemStack, EntityLiving entityLiving) {
		if (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			entityLiving.setFire(5);
			entityLiving.attackEntityFrom(DamageSource.magic, 7);
			itemStack.damageItem(1, entityLiving);
			return true;
		}
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
		if (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, entityPlayer, true);
			if (movingobjectposition == null) {
				return itemStack;
			} else {
				if (movingobjectposition.typeOfHit == EnumMovingObjectType.TILE) {
					int i = movingobjectposition.blockX;
					int j = movingobjectposition.blockY;
					int k = movingobjectposition.blockZ;
					if (!world.canMineBlock(entityPlayer, i, j, k)) {
						return itemStack;
					}
					if (!entityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, itemStack)) {
						return itemStack;
					} else {
						ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(world.getBlockId(i, j, k), 1, world.getBlockMetadata(i, j, k)));
						if (!world.isRemote && !(result == null)) {
							int id = result.itemID;
							int meta = result.getItemDamage();
							world.playSoundEffect(i + 0.5D, j + 0.5D, k + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
							world.setBlock(i, j, k, 0);
							world.spawnEntityInWorld(new EntityItem(world, i, j, k, new ItemStack(id, 1, meta)));
							itemStack.damageItem(1, entityPlayer);
						}
					}
				}
			}
		} else {
			world.playAuxSFXAtEntity((EntityPlayer) null, 1009, (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ, 0);
			if (!world.isRemote) {
				world.spawnEntityInWorld(new EntityChargeFire(world, entityPlayer));
				itemStack.damageItem(1, entityPlayer);
			}
			return itemStack;
		}
		return itemStack;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack itemStack, EntityPlayer entityPlayer, Entity entity) {
		entity.setFire(5);
		entity.attackEntityFrom(DamageSource.magic, 7);
		itemStack.damageItem(1, entityPlayer);
		return true;
	}

	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5) {
		EntityPlayer player = (EntityPlayer) entity;
		ItemStack currentItem = player.inventory.getCurrentItem();
		if (!world.isRemote) {
			if (currentItem != null) {
				if (currentItem.itemID == itemStack.itemID) {
					player.addPotionEffect(new PotionEffect(Potion.fireResistance.getId(), 20, 0));
					MysticWorld.proxy.fireFX(world, (player.posX - 0.5D) + rand.nextDouble(), player.posY, (player.posZ - 0.5D) + rand.nextDouble(), 1.0F);
				}
			}
		}
	}
}
