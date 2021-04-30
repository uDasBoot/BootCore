package com.udasboot.dascore.util;

import net.minecraft.util.text.TranslationTextComponent;

public class ToolTipsHandler {
	
	public static final ToolTipsHandler NONE = generateToolTip("");
	public static final ToolTipsHandler ERROR = generateToolTip("Error loading tooltip");
	
	private TranslationTextComponent toolTip;
	
	public ToolTipsHandler(TranslationTextComponent toolTip) {
		this.toolTip = toolTip;
	}
	
	public static ToolTipsHandler generateToolTip(String toolTip) {
		return new ToolTipsHandler(new TranslationTextComponent("/u00A77" + toolTip));
	}
	
	public static ToolTipsHandler generateToolTip(TranslationTextComponent toolTip) {
		return new ToolTipsHandler(toolTip);
	}
	
	public TranslationTextComponent getToolTip() {
		return this.toolTip;
	}
}
