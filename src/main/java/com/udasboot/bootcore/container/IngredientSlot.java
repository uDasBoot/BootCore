package com.udasboot.bootcore.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class IngredientSlot extends Slot{

	public IngredientSlot(IInventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
	}
	
	@Override
	public boolean mayPlace(ItemStack itemStack) {
		return true;
	}
	
}
