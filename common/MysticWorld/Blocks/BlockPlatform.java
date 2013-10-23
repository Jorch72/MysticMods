package mysticworld.blocks;

import java.util.Random;

import mysticworld.MysticWorld;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPlatform extends Block {
	public BlockPlatform(int id) {
		super(id, Material.rock);
		this.setBlockUnbreakable();
		this.setResistance(6000000.0F);
		this.setTickRandomly(true);
		setCreativeTab(MysticWorld.MysticWorldTab);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.15F, 1.0F);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
		super.onEntityWalking(world, x, y, z, entity);
	}

	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		int block = BlockHandler.pillar.blockID;
		double posX = x;
		double posY = y;
		double posZ = z;
		if (this.isTowersNearby(world, x, y, z)) {
			MysticWorld.proxy.fireFX(world, posX + rand.nextDouble(), posY + 0.20, posZ + rand.nextDouble(), 1.0F);
			if (rand.nextInt(3) == 0) {
				world.spawnParticle("enchantmenttable", x + 0.5D, y + 1.0D, z + 0.5D, x - 2 - x + rand.nextFloat() - 0.5D, y + 2 - y - rand.nextFloat() - 1.0F, z - 2 - z + rand.nextFloat() - 0.5D);
				world.spawnParticle("enchantmenttable", x + 0.5D, y + 1.0D, z + 0.5D, x - 2 - x + rand.nextFloat() - 0.5D, y + 2 - y - rand.nextFloat() - 1.0F, z + 2 - z + rand.nextFloat() - 0.5D);
				world.spawnParticle("enchantmenttable", x + 0.5D, y + 1.0D, z + 0.5D, x + 2 - x + rand.nextFloat() - 0.5D, y + 2 - y - rand.nextFloat() - 1.0F, z + 2 - z + rand.nextFloat() - 0.5D);
				world.spawnParticle("enchantmenttable", x + 0.5D, y + 1.0D, z + 0.5D, x + 2 - x + rand.nextFloat() - 0.5D, y + 2 - y - rand.nextFloat() - 1.0F, z - 2 - z + rand.nextFloat() - 0.5D);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon("MysticMods" + ":" + "Platform");
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int tickRate(World par1World) {
		return 30;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
	}

	private boolean isTowersNearby(World world, int x, int y, int z) {
		int pillar = BlockHandler.pillar.blockID;
		int insert = BlockHandler.pillarInsert.blockID;
		if (world.getBlockId(x - 2, y, z - 2) == pillar && world.getBlockId(x - 2, y, z + 2) == pillar && world.getBlockId(x + 2, y, z + 2) == pillar && world.getBlockId(x + 2, y, z - 2) == pillar
				&& world.getBlockId(x - 2, y + 1, z - 2) == insert && world.getBlockId(x - 2, y + 1, z + 2) == insert && world.getBlockId(x + 2, y + 1, z + 2) == insert
				&& world.getBlockId(x + 2, y + 1, z - 2) == insert && world.getBlockId(x - 2, y + 2, z - 2) == pillar && world.getBlockId(x - 2, y + 2, z + 2) == pillar
				&& world.getBlockId(x + 2, y + 2, z + 2) == pillar && world.getBlockId(x + 2, y + 2, z - 2) == pillar) {
			return true;
		}
		return false;
	}
}
