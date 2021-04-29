package com.udasboot.bootcore.tileentity;

import net.minecraft.tileentity.TileEntityType;

public abstract class AbstractGeneratorTileEntity extends AbstractMachineTileEntity {

	public AbstractGeneratorTileEntity(TileEntityType<?> tileEntityType, int slots) {
		super(tileEntityType, slots);
	}
	
	@Override
	public void tick() {
		updateExData();
	}
}
