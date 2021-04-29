package com.udasboot.bootcore.block;

import com.udasboot.bootcore.tileentity.AbstractGeneratorTileEntity;
import com.udasboot.bootcore.util.Constants;

public abstract class AbstractGeneratorBlock extends AbstractMachineBlock{

	public AbstractGeneratorBlock(Class<? extends AbstractGeneratorTileEntity> tileEntityClass) {
		this(Constants.DEFAULT_PROPS, tileEntityClass);
	}
	
	public AbstractGeneratorBlock(Properties props, Class<? extends AbstractGeneratorTileEntity> tileEntityClass) {
		super(props, tileEntityClass);
	}
	
}
