package com.udasboot.dascore.block;

import com.udasboot.dascore.util.Constants;

import net.minecraft.block.Block;

public abstract class AbstractModBlock extends Block{
	
	public AbstractModBlock(Properties props) {
		super(props);
	}

	public AbstractModBlock() {
		this(Constants.DEFAULT_BLOCK_PROPS);
	}
	
}
