package com.udasboot.bootcore.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class ResultSlot extends Slot {

	public ResultSlot(IInventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
	}

	@Override
	public boolean mayPlace(ItemStack itemStack) {
		return false;
	}
}
