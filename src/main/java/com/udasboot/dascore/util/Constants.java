package com.udasboot.dascore.util;

import java.util.HashMap;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;

public class Constants {
	
	private String MOD_ID;
	public static HashMap<String, String> CONSTANTS = new HashMap<String, String>();
	public static final AbstractBlock.Properties DEFAULT_BLOCK_PROPS = AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).strength(50f, 1200f)
			.harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).harvestLevel(4)
			.requiresCorrectToolForDrops();
	
	public Constants(String modId) {
		this.MOD_ID = modId;
		CONSTANTS.put("modid", this.MOD_ID);
	}
	
	public void addConstant(String identifier, String value) {
		CONSTANTS.put(identifier, value);
	}
	
	public String getConstant(String identifier) {
		return CONSTANTS.get(identifier);
	}
	
	public ResourceLocation genResourceLocation(String loc) {
		return new ResourceLocation(MOD_ID, loc);
	}

}
