package MysticWorld.Items;

import MysticWorld.MysticWorld;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemArmorMithril extends ItemArmor
{
	int ArmorType;
	
	String ArmorFile_1;
	String ArmorFile_2;

	public ItemArmorMithril(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
			
			super(par1, par2EnumArmorMaterial,par3,par4);
			this.ArmorType=par4;
			this.setCreativeTab(MysticWorld.MysticWorldTab);
			this.ArmorFile_1 = "/assets/MysticTextures/textures/armor/mithril_1.png";
			this.ArmorFile_2 = "/assets/MysticTextures/textures/armor/mithril_2.png";
		}

		@Override
		public void registerIcons(IconRegister iconRegister)
		{
			switch (ArmorType){
			case 0:
		        itemIcon = iconRegister.registerIcon("MysticTextures:MithrilArmor_0");
		        break;
			case 1:
				itemIcon = iconRegister.registerIcon("MysticTextures:MithrilArmor_1");
		        break;
			case 2:
				itemIcon = iconRegister.registerIcon("MysticTextures:MithrilArmor_2");
		        break;
			case 3:
				itemIcon = iconRegister.registerIcon("MysticTextures:MithrilArmor_3");
		        break;
			}
		}
		
		public String getArmorTextureFile(ItemStack par1){
			if(par1.itemID == ItemHandler$1.mithrilHelmet.itemID||par1.itemID == ItemHandler$1.mithrilChestplate.itemID||par1.itemID == ItemHandler$1.mithrilBoots.itemID)
			{
				return ArmorFile_1;
			}
			if(par1.itemID == ItemHandler$1.mithrilLeggings.itemID)
			{
				return ArmorFile_2;
			}
			return ArmorFile_2;
		}
}
