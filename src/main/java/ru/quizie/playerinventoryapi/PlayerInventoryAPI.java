package ru.quizie.playerinventoryapi;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class PlayerInventoryAPI {

    /**
     * Проверяет, содержит ли инвентарь указанное количество материала
     *
     * @param inventory Инвентарь игрока
     * @param material Материал для поиска
     * @param amount Количество материала
     * @return true, если в инвентаре есть указанное количество материала, иначе false
     */
    public static boolean hasMaterial(PlayerInventory inventory, Material material, int amount) {
        if (amount <= 0 || inventory == null) return false;

        final ItemStack[] contents = inventory.getContents();
        int amountStack = 0;

        for (ItemStack item : contents) {
            if (isItemValid(item) && item.getType() == material) {
                amountStack += item.getAmount();
                if (amountStack >= amount) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Проверяет, содержит ли инвентарь хотя бы один предмет указанного материала
     *
     * @param inventory Инвентарь игрока
     * @param material Материал для поиска
     * @return true, если в инвентаре есть предмет с указанным материалом, иначе false
     */
    public static boolean hasMaterial(PlayerInventory inventory, Material material) {
        return inventory.contains(material);
    }

    /**
     * Проверяет, содержит ли инвентарь указанное количество предметов, аналогичных переданному
     *
     * @param inventory Инвентарь игрока
     * @param stack Предмет, который надо проверить
     * @param amount Количество предмета
     * @return true, если в инвентаре есть указанное количество аналогичных предметов, иначе false
     */
    public static boolean hasItemStack(PlayerInventory inventory, ItemStack stack, int amount) {
        if (amount <= 0) return false;

        final ItemStack[] contents = inventory.getContents();
        int amountStack = 0;

        for (ItemStack item : contents) {
            if (isItemValid(item) && item.isSimilar(stack)) {
                amountStack += item.getAmount();
                if (amountStack >= amount) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Проверяет, содержит ли инвентарь хотя бы один предмет, аналогичный переданному
     *
     * @param inventory Инвентарь игрока
     * @param stack Предмет, который надо проверить
     * @return true, если в инвентаре есть аналогичный предмет, иначе false
     */
    public static boolean hasItemStack(PlayerInventory inventory, ItemStack stack) {
        return inventory.contains(stack);
    }

    /**
     * Удаляет указанное количество материала из инвентаря игрока
     *
     * @param inventory Инвентарь игрока
     * @param material Удаляемый материал
     * @param amount Количество удаляемого предмета
     */
    public static void removeMaterial(PlayerInventory inventory, Material material, int amount) {
        if (amount <= 0) return;

        for (ItemStack item : inventory.getContents()) {
            if (isItemValid(item) && item.getType() == material) {
                final int stackAmount = item.getAmount();

                if (stackAmount >= amount) {
                    item.setAmount(stackAmount - amount);
                } else {
                    amount -= stackAmount;
                    item.setAmount(0);
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
    public static void removeItemStack(PlayerInventory inventory, ItemStack stack, int amount) {
        for (ItemStack item : inventory.getContents()) {
            if (isItemValid(item) && item.isSimilar(stack)) {
                final int stackAmount = item.getAmount();

                if (stackAmount >= amount) {
                    item.setAmount(stackAmount - amount);
                } else {
                    amount -= stackAmount;
                    item.setAmount(0);
                }
            }
        }
    }

    private static boolean isItemValid(ItemStack item) {
        return item != null && !item.getType().isAir();
    }
}