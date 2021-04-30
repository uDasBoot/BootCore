package com.udasboot.dascore.block;

import com.udasboot.dascore.tileentity.AbstractGeneratorTileEntity;
import com.udasboot.dascore.util.Constants;

public abstract class AbstractGeneratorBlock extends AbstractMachineBlock {

	public AbstractGeneratorBlock(Class<? extends AbstractGeneratorTileEntity> tileEntityClass) {
		this(Constants.DEFAULT_BLOCK_PROPS, tileEntityClass);
	}

	public AbstractGeneratorBlock(Properties props, Class<? extends AbstractGeneratorTileEntity> tileEntityClass) {
		super(props, tileEntityClass);
	}

}
