package ru.quizie.playerinventoryapi;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public interface InventoryChecker {
    boolean hasMaterial(PlayerInventory inventory, Material material, int amount);
    boolean hasMaterial(PlayerInventory inventory, Material material);
    boolean hasItemStack(PlayerInventory inventory, ItemStack stack, int amount);
    boolean hasItemStack(PlayerInventory inventory, ItemStack stack);
}
