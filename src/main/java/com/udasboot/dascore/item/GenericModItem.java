package com.udasboot.dascore.item;

import java.util.List;

import com.udasboot.dascore.util.ToolTipsHandler;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class GenericModItem extends Item {
	public ToolTipsHandler toolTip;

	public GenericModItem(ToolTipsHandler toolTip) {
		this(new Item.Properties().tab(ItemGroup.TAB_MISC), toolTip);
	}
	
	public GenericModItem() {
		this(new Item.Properties().tab(ItemGroup.TAB_MISC), ToolTipsHandler.NONE);
	}
	
	public GenericModItem(Item.Properties props, ToolTipsHandler toolTip) {
		super(props);
		this.toolTip = toolTip;
	}

	public GenericModItem(Item.Properties props) {
		this(props, ToolTipsHandler.NONE);
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
