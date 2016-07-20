package tinker_io.items;

import java.awt.Color;
import java.util.List;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraft.client.resources.I18n;
import tinker_io.handler.CrushedOreColorList;
import tinker_io.main.Main;

public class CrushedOre extends Item implements IItemColor{
	public CrushedOre(String unlocalizedName){
		super();
		setUnlocalizedName(unlocalizedName);
		setCreativeTab(Main.TinkerIOTabs);
	}
	
	public CrushedOre(){}
	
	/*private ModelResourceLocation setLocation(String filename){
		return new ModelResourceLocation(Main.MODID + ":" + this.getUnlocalizedName().substring(5)+ "_" + filename, "inventory");
	}*/
	
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> list, boolean par4) {
		if (itemStack.getTagCompound() != null) {
            String oreDic = itemStack.getTagCompound().getString("oreDic");
            list.add(TextFormatting.RED + "oreDic : "+oreDic);   
        }
		
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack)
    {
		String name = I18n.format(this.getUnlocalizedNameInefficiently(stack) + ".name", new Object[0]).trim();
		
		NBTTagCompound nbt = stack.getTagCompound();
		if(nbt != null){
			String oreDicName = nbt.getString("oreDic");
			List<ItemStack> oreList = OreDictionary.getOres(oreDicName);
			if(!oreList.isEmpty()){
				ItemStack oreItem = oreList.get(0);
				String oreName = oreItem.getDisplayName();
				name = name + " (" + oreName + ")";
			}
		}
		return name;
    }
	
	@Override
	public int getColorFromItemstack(ItemStack stack, int renderLayer) {
		CrushedOreColorList colorlist = new CrushedOreColorList();
	    switch (renderLayer) {
	    	case 0: return Color.WHITE.getRGB();
	    	case 1: {
	    		return colorlist.getColor(stack);
	    	}
	    	default: {
	    		// oops! should never get here.
	    		return Color.black.getRGB();
	    	}
	    }
	}
}