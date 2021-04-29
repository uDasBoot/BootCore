package com.udasboot.bootcore.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

public abstract class AbstractGeneratorTileEntity extends AbstractMachineTileEntity {

	private NonNullList<Direction> DIRECTIONS = NonNullList.of(Direction.UP, Direction.DOWN, Direction.NORTH,
			Direction.EAST, Direction.SOUTH, Direction.WEST);
	public int generationRate;
	public int extractionRate;

	public AbstractGeneratorTileEntity(TileEntityType<?> tileEntityType, int slots) {
		super(tileEntityType, slots);
		this.maxEnergy = 400000;
		this.generationRate = 60;
		this.extractionRate = 200;
	}

	@Override
	public void tick() {
		updateExData();
		if(this.isGenerating()) {
			if(this.canGenerate()) {
				this.energy += this.generationRate;
				this.progressTime++;
				if (this.progressTime > this.totalProgressTime) {
					this.progressTime = 0;
				}
			}
		}
		for (Direction d : DIRECTIONS) {
			BlockPos pos = this.getBlockPos().relative(d);
			TileEntity te = this.level.getBlockEntity(pos);
			if (te != null && te instanceof AbstractMachineTileEntity && !(te instanceof AbstractGeneratorTileEntity)) {
				AbstractMachineTileEntity te2 = (AbstractMachineTileEntity) te;
				if (te2.receiveEnergy(this.getExtractionRate(), true) != 0 && this.energy > 0) {
					te2.receiveEnergy(this.getExtractionRate(), false);
					this.energy -= this.getExtractionRate();
				}
			}
		}
	}
	
	public boolean canGenerate() {
		return (this.energy + this.generationRate) < this.maxEnergy;
	}
	
	@Override
	public void updateExData() {
		super.updateExData();
		this.dataAccess.set(4, this.generationRate);
		this.dataAccess.set(5, this.getExtractionRate());
	}

	public void setGenerationRate(int rate) {
		this.generationRate = rate;
	}
	
	public void setExtractRate(int rate) {
		this.extractionRate = rate;
	}
	
	public int getExtractionRate() {
		return (this.energy > this.extractionRate) ? this.extractionRate : this.energy;
	}
	
	public int getGenerationRate() {
		return this.dataAccess.get(4);
	}
	
	public boolean isGenerating() {
		return this.dataAccess.get(2) > 0;
	}
}
