package com.udasboot.bootcore.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.energy.IEnergyStorage;

public abstract class AbstractMachineTileEntity extends LockableTileEntity
		implements IEnergyStorage, ITickableTileEntity {

	public int slots;

	protected NonNullList<ItemStack> items;
	protected int energy;
	protected int maxEnergy;
	protected int progressTime;
	protected int totalProgressTime;
	protected int ex1, ex2, ex3, ex4;
	protected boolean ex5, ex6;

	public final IIntArray dataAccess = new IIntArray() {
		public int get(int index) {
			switch (index) {
			case 0:
				return AbstractMachineTileEntity.this.energy;
			case 1:
				return AbstractMachineTileEntity.this.maxEnergy;
			case 2:
				return AbstractMachineTileEntity.this.progressTime;
			case 3:
				return AbstractMachineTileEntity.this.totalProgressTime;
			case 4:
				return AbstractMachineTileEntity.this.ex1;
			case 5:
				return AbstractMachineTileEntity.this.ex2;
			case 6:
				return AbstractMachineTileEntity.this.ex3;
			case 7:
				return AbstractMachineTileEntity.this.ex4;
			case 8:
				return AbstractMachineTileEntity.this.ex5 ? 1 : 0;
			case 9:
				return AbstractMachineTileEntity.this.ex6 ? 1 : 0;
			default:
				return 0;
			}
		}

		public void set(int index, int value) {
			switch (index) {
			case 0:
				AbstractMachineTileEntity.this.energy = value;
				break;
			case 1:
				AbstractMachineTileEntity.this.maxEnergy = value;
			case 2:
				AbstractMachineTileEntity.this.progressTime = value;
			case 3:
				AbstractMachineTileEntity.this.totalProgressTime = value;
			case 4:
				AbstractMachineTileEntity.this.ex1 = value;
			case 5:
				AbstractMachineTileEntity.this.ex2 = value;
			case 6:
				AbstractMachineTileEntity.this.ex3 = value;
			case 7:
				AbstractMachineTileEntity.this.ex4 = value;
			case 8:
				AbstractMachineTileEntity.this.ex5 = (value == 1);
			case 9:
				AbstractMachineTileEntity.this.ex6 = (value == 1);
			}
		}

		public int getCount() {
			return 10;
		}
	};

	public AbstractMachineTileEntity(TileEntityType<?> tileEntityType, int slots) {
		super(tileEntityType);
		this.slots = slots;
		items = NonNullList.withSize(this.slots, ItemStack.EMPTY);
		this.maxEnergy = 4000;
		this.totalProgressTime = 200;
	}

	@Override
	public void tick() {
		updateExData();
	}

	public void updateExData() {
	}

	@Override
	public int getContainerSize() {
		return slots;
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemStack : items) {
			if (!itemStack.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ItemStack getItem(int index) {
		return items.get(index);
	}

	@Override
	public ItemStack removeItem(int index, int amount) {
		return ItemStackHelper.removeItem(items, index, amount);
	}

	@Override
	public ItemStack removeItemNoUpdate(int index) {
		return ItemStackHelper.takeItem(items, index);
	}

	@Override
	public void setItem(int index, ItemStack itemStack) {
		this.items.set(index, itemStack);
		if (itemStack.getCount() > this.getMaxStackSize()) {
			itemStack.setCount(this.getMaxStackSize());
		}
	}

	@Override
	public boolean stillValid(PlayerEntity playerIn) {
		if (this.level.getBlockEntity(this.worldPosition) != this) {
			return false;
		} else {
			return playerIn.distanceToSqr((double) this.worldPosition.getX() + 0.5D,
					(double) this.worldPosition.getY() + 0.5D, (double) this.worldPosition.getZ() + 0.5D) <= 64.0D;
		}
	}

	@Override
	public void clearContent() {
		this.items.clear();
	}

	@Override
	protected abstract ITextComponent getDefaultName();

	@Override
	protected abstract Container createMenu(int windowId, PlayerInventory playerInventory);

	@Override
	public CompoundNBT save(CompoundNBT compound) {
		return super.save(compound);
	}

	@Override
	public void load(BlockState state, CompoundNBT compound) {
		super.load(state, compound);
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		int energyRecieved = ((this.energy + maxReceive) > this.maxEnergy) ? this.maxEnergy : maxReceive;
		if (!simulate) {
			this.energy += energyRecieved;
		}
		return energyRecieved;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		int energyExtracted = (this.energy < maxExtract) ? this.energy : maxExtract;
		if (!simulate) {
			this.energy -= energyExtracted;
		}
		return energyExtracted;
	}

	@Override
	public int getEnergyStored() {
		return this.energy;
	}

	@Override
	public int getMaxEnergyStored() {
		return this.maxEnergy;
	}

	@Override
	public boolean canExtract() {
		return this.energy > 0;
	}

	@Override
	public boolean canReceive() {
		return this.energy < this.maxEnergy;
	}

}
