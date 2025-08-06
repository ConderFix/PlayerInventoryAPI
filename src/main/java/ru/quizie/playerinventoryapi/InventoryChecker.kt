package ru.quizie.playerinventoryapi

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.PlayerInventory
import org.bukkit.persistence.PersistentDataType

interface InventoryChecker {
    fun hasMaterial(inventory: PlayerInventory?, material: Material?, amount: Int): Boolean
    fun hasMaterial(inventory: PlayerInventory?, material: Material?): Boolean
    fun hasItemStack(inventory: PlayerInventory?, stack: ItemStack?, amount: Int): Boolean
    fun hasItemStack(inventory: PlayerInventory?, stack: ItemStack?): Boolean
    fun <T, Z> hasItemNamespacedKey(inventory: PlayerInventory?, key: NamespacedKey, persistentDataType: PersistentDataType<T, Z>, amount: Int): Boolean
    fun <T, Z> hasItemNamespacedKey(inventory: PlayerInventory?, key: NamespacedKey, persistentDataType: PersistentDataType<T, Z>): Boolean
}
