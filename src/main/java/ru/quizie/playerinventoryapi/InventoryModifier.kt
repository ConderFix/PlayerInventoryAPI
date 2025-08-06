package ru.quizie.playerinventoryapi

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.PlayerInventory
import org.bukkit.persistence.PersistentDataType

interface InventoryModifier {
    fun removeMaterial(inventory: PlayerInventory?, material: Material?, amount: Int)
    fun removeItemStack(inventory: PlayerInventory?, stack: ItemStack?, amount: Int)
    fun <T, Z> removeItemNamespacedKey(inventory: PlayerInventory?, key: NamespacedKey, persistentDataType: PersistentDataType<T, Z>, amount: Int)
    fun addSafeItemStack(player: Player, item: ItemStack?)
}
