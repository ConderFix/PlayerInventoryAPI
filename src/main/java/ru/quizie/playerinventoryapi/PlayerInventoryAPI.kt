package ru.quizie.playerinventoryapi

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.PlayerInventory
import org.bukkit.persistence.PersistentDataType
import java.util.*

class PlayerInventoryAPI : InventoryModifier, InventoryChecker {

    /**
     * Проверяет, содержит ли инвентарь указанное количество материала
     *
     * @param inventory Инвентарь игрока
     * @param material Материал для поиска
     * @param amount Количество материала
     * @return true, если в инвентаре есть указанное количество материала, иначе false
     */
    override fun hasMaterial(inventory: PlayerInventory?, material: Material?, amount: Int): Boolean {
        Objects.requireNonNull(inventory, "Inventory cannot be null")
        Objects.requireNonNull(material, "Material cannot be null")
        require(amount > 0) { "Amount cannot be less than 0" }

        val contents = inventory!!.contents
        var amountStack = 0

        for (item in contents) {
            if (isItemValid(item) && item!!.type == material) {
                amountStack += item.amount
                if (amountStack >= amount) {
                    return true
                }
            }
        }

        return false
    }

    /**
     * Проверяет, содержит ли инвентарь хотя бы один предмет указанного материала
     *
     * @param inventory Инвентарь игрока
     * @param material Материал для поиска
     * @return true, если в инвентаре есть предмет с указанным материалом, иначе false
     */
    override fun hasMaterial(inventory: PlayerInventory?, material: Material?): Boolean {
        Objects.requireNonNull(inventory, "Inventory cannot be null")
        Objects.requireNonNull(material, "Material cannot be null")

        return inventory!!.contains(material!!)
    }

    /**
     * Проверяет, содержит ли инвентарь указанное количество предметов, аналогичных переданному
     *
     * @param inventory Инвентарь игрока
     * @param stack Предмет, который надо проверить
     * @param amount Количество предмета
     * @return true, если в инвентаре есть указанное количество аналогичных предметов, иначе false
     */
    override fun hasItemStack(inventory: PlayerInventory?, stack: ItemStack?, amount: Int): Boolean {
        Objects.requireNonNull(inventory, "Inventory cannot be null")
        Objects.requireNonNull(stack, "ItemStack cannot be null")
        require(amount > 0) { "Amount cannot be less than 0" }

        val contents = inventory!!.contents
        var amountStack = 0

        for (item in contents) {
            if (isItemValid(item) && item!!.isSimilar(stack)) {
                amountStack += item.amount
                if (amountStack >= amount) {
                    return true
                }
            }
        }

        return false
    }

    /**
     * Проверяет, содержит ли инвентарь хотя бы один предмет, аналогичный переданному
     *
     * @param inventory Инвентарь игрока
     * @param stack Предмет, который надо проверить
     * @return true, если в инвентаре есть аналогичный предмет, иначе false
     */
    override fun hasItemStack(inventory: PlayerInventory?, stack: ItemStack?): Boolean {
        Objects.requireNonNull(inventory, "Inventory cannot be null")
        Objects.requireNonNull(stack, "ItemStack cannot be null")

        return inventory!!.contains(stack)
    }

    override fun <T, Z> hasItemNamespacedKey(inventory: PlayerInventory?, key: NamespacedKey, persistentDataType: PersistentDataType<T, Z>, amount: Int): Boolean {
        Objects.requireNonNull(inventory, "Inventory cannot be null")
        Objects.requireNonNull(key, "NamespacedKey cannot be null")
        require(amount > 0) { "Amount cannot be less than 0" }

        val contents = inventory!!.contents
        var amountStack = 0

        for (item in contents) {
            if (isItemValid(item) && item!!.itemMeta != null && item.itemMeta.persistentDataContainer.has(key, persistentDataType)) {
                amountStack += item.amount
                if (amountStack >= amount) {
                    return true
                }
            }
        }

        return false
    }

    override fun <T, Z> hasItemNamespacedKey(inventory: PlayerInventory?, key: NamespacedKey, persistentDataType: PersistentDataType<T, Z>): Boolean {
        Objects.requireNonNull(inventory, "Inventory cannot be null")
        Objects.requireNonNull(key, "NamespacedKey cannot be null")

        val contents = inventory!!.contents

        for (item in contents) {
            if (isItemValid(item) && item!!.itemMeta != null && item.itemMeta.persistentDataContainer.has(key, persistentDataType)) {
                return true
            }
        }

        return false
    }

    /**
     * Удаляет указанное количество материала из инвентаря игрока
     *
     * @param inventory Инвентарь игрока
     * @param material Удаляемый материал
     * @param amount Количество удаляемого предмета
     */
    override fun removeMaterial(inventory: PlayerInventory?, material: Material?, amount: Int) {
        var amount = amount
        Objects.requireNonNull(inventory, "Inventory cannot be null")
        Objects.requireNonNull(material, "Material cannot be null")
        require(amount > 0) { "Amount cannot be less than 0" }

        for (item in inventory!!.contents) {
            if (isItemValid(item) && item!!.type == material) {
                val stackAmount = item.amount

                if (stackAmount >= amount) {
                    item.amount = stackAmount - amount
                } else {
                    amount -= stackAmount
                    item.amount = 0
                }
            }
        }
    }

    /**
     * Удаляет указанное количество предмета из инвентаря игрока
     *
     * @param inventory Инвентарь игрока
     * @param stack Удаляемый предмет
     * @param amount Количество удаляемого предмета
     */
    override fun removeItemStack(inventory: PlayerInventory?, stack: ItemStack?, amount: Int) {
        var amount = amount
        Objects.requireNonNull(inventory, "Inventory cannot be null")
        Objects.requireNonNull(stack, "ItemStack cannot be null")
        require(amount > 0) { "Amount cannot be less than 0" }

        for (item in inventory!!.contents) {
            if (isItemValid(item) && item!!.isSimilar(stack)) {
                val stackAmount = item.amount

                if (stackAmount >= amount) {
                    item.amount = stackAmount - amount
                } else {
                    amount -= stackAmount
                    item.amount = 0
                }
            }
        }
    }

    override fun <T, Z> removeItemNamespacedKey(inventory: PlayerInventory?, key: NamespacedKey, persistentDataType: PersistentDataType<T, Z>, amount: Int) {
        var amount = amount
        Objects.requireNonNull(inventory, "Inventory cannot be null")
        Objects.requireNonNull(key, "NamespacedKey cannot be null")
        Objects.requireNonNull(persistentDataType, "NamespacedKey cannot be null")
        require(amount > 0) { "Amount cannot be less than 0" }

        for (item in inventory!!.contents) {
            if (isItemValid(item) && item!!.itemMeta != null && item.itemMeta.persistentDataContainer.has(key, persistentDataType)) {
                val stackAmount = item.amount

                if (stackAmount >= amount) {
                    item.amount = stackAmount - amount
                } else {
                    amount -= stackAmount
                    item.amount = 0
                }
            }
        }
    }

    override fun addSafeItemStack(player: Player, item: ItemStack?) {
        Objects.requireNonNull(item, "ItemStack cannot be null")

        player.inventory.addItem(item!!).forEach { slot: Int, itemStack: ItemStack -> player.world.dropItem(player.location, itemStack) }
    }


    companion object {
        private fun isItemValid(item: ItemStack?): Boolean {
            return item != null && !item.type.isAir
        }
    }
}