package com.udasboot.dascore.block;

import java.util.List;

import com.udasboot.dascore.util.ToolTipsHandler;

import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class GenericModBlockItem extends BlockItem {

	public ToolTipsHandler toolTip;

	public GenericModBlockItem(Block block, ToolTipsHandler toolTip) {
		super(block, new Item.Properties());
		this.toolTip = toolTip;
	}

	public GenericModBlockItem(Block block) {
		this(block, ToolTipsHandler.NONE);
	}
	
	public GenericModBlockItem(Block block, Item.Properties props, ToolTipsHandler toolTip) {
		super(block, props);
		this.toolTip = toolTip;
	}

	public GenericModBlockItem(Block block, Item.Properties props) {
		this(block, props, ToolTipsHandler.NONE);
	}

	@Override
	public void appendHoverText(ItemStack itemStack, World worldIn, List<ITextComponent> textComponents,
			ITooltipFlag tooltipFlag) {
		if (this.toolTip != ToolTipsHandler.NONE && worldIn != null) {
			if (!Screen.hasShiftDown()) {
				textComponents.add(new TranslationTextComponent(
						"\u00A77Press <\u00A74" + "Shift" +"\u00A77> for more"));
			} else {
				textComponents.add(this.toolTip.getToolTip());
			}
		}
		super.appendHoverText(itemStack, worldIn, textComponents, tooltipFlag);
	}

}
