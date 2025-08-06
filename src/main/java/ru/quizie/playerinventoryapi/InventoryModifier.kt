package ru.quizie.playerinventoryapi;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public interface InventoryModifier {
    void removeMaterial(PlayerInventory inventory, Material material, int amount);
    void removeItemStack(PlayerInventory inventory, ItemStack stack, int amount);
}
