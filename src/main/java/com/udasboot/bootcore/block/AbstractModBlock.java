package com.udasboot.bootcore.block;

import com.udasboot.bootcore.util.Constants;

import net.minecraft.block.Block;

public abstract class AbstractModBlock extends Block{
	
	public AbstractModBlock(Properties props) {
		super(props);
	}

	public AbstractModBlock() {
		this(Constants.DEFAULT_PROPS);
	}
	
}
