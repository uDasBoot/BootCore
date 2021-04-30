package com.udasboot.dascore.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public class FuelSlot extends Slot {

	private Ingredient fuels = Ingredient.EMPTY;

	public FuelSlot(Ingredient fuels, IInventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
		this.fuels = fuels;
	}

	@Override
	public boolean mayPlace(ItemStack itemStack) {
		if (isFuel(itemStack)) {
			return true;
		}
		return false;
	}

	private boolean isFuel(ItemStack itemStack) {
		Item check = itemStack.getItem();
		for (ItemStack itemStack1 : fuels.getItems()) {
			Item item = itemStack1.getItem();
			if(item == check) {
				return true;
			}
		}
		return false;
	}

}
