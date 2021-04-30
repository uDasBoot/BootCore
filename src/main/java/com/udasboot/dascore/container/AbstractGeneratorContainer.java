package com.udasboot.dascore.container;

import java.util.Objects;

import com.udasboot.dascore.block.AbstractGeneratorBlock;
import com.udasboot.dascore.tileentity.AbstractGeneratorTileEntity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;

public abstract class AbstractGeneratorContainer extends Container {

	public final AbstractGeneratorTileEntity te;
	public final IWorldPosCallable canInteractWithCallable;
	public final IIntArray data;
	public final int invSize;
	public final AbstractGeneratorBlock parentBlock;

	protected <T extends AbstractGeneratorTileEntity> AbstractGeneratorContainer(ContainerType<? extends AbstractGeneratorContainer> type, int windowId,
			PlayerInventory playerInventory, T te, IIntArray data, Block block) {
		super(type, windowId);
		this.te = (AbstractGeneratorTileEntity) te;
		this.canInteractWithCallable = IWorldPosCallable.create(te.getLevel(), te.getBlockPos());
		this.data = data;
		this.invSize = te.slots;
		this.parentBlock = (AbstractGeneratorBlock) block;
		
		addInvSlots();
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 9; col++) {
				int index = col + row * 9 + 9;
				this.addSlot(new Slot(playerInventory, index, 8 + col * 18, 166 - (4 - row) * 18 - 10));
			}
		}

		for (int col = 0; col < 9; col++) {
			this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
		}
		this.addDataSlots(data);
	}
	
	protected static AbstractGeneratorTileEntity getTileEntity(final PlayerInventory playerInventory,
			final PacketBuffer data) {
		Objects.requireNonNull(playerInventory, "Player Inventory cannot be null!");
		Objects.requireNonNull(data, "Packet Buffer cannot be null!");
		final TileEntity te = playerInventory.player.level.getBlockEntity(data.readBlockPos());
		if (te instanceof AbstractGeneratorTileEntity) {
			return (AbstractGeneratorTileEntity) te;
		}
		throw new IllegalStateException("Tile Entity is not correct!");
	}
	
	@Override
	public boolean stillValid(PlayerEntity playerIn) {
		// TODO Auto-generated method stub
		return stillValid(canInteractWithCallable, playerIn, parentBlock);
	}

	@Override
	public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index < invSize) {
				if (!this.moveItemStackTo(itemstack1, invSize, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(itemstack1, 0, invSize, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
		}

		return itemstack;
	}
	
	public int getEnergy() {
		return this.data.get(0);
	}
	
	public int getMaxEnergy() {
		return this.data.get(1);
	}
	
	public int getProgress() {
		return this.data.get(2);
	}
	
	public int getTotalProgress() {
		return this.data.get(3);
	}
	
	public double getProgressPercent() {
		return (double) this.getProgress() / (double) this.getTotalProgress();
	}
	
	public double getEnergyPercent() {
		return (double) this.getEnergy() / (double) this.getMaxEnergy();
	}
	
	public int getExtractionRate() {
		return this.data.get(4);
	}
	
	public int getGenerationRate() {
		return this.data.get(5);
	}
	
	public int getEx3() {
		return this.data.get(6);
	}
	
	public int getEx4() {
		return this.data.get(7);
	}
	
	public boolean isGenerating() {
		return this.getProgress() > 0;
	}
	
	public boolean getEx6() {
		return (this.data.get(9) == 1) ? true: false;
	}

	public abstract void addInvSlots();

}
