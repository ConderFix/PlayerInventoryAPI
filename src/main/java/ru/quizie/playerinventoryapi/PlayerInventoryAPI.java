package ru.quizie.playerinventoryapi;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Objects;

public class PlayerInventoryAPI implements InventoryModifier, InventoryChecker {

    /**
     * Проверяет, содержит ли инвентарь указанное количество материала
     *
     * @param inventory Инвентарь игрока
     * @param material Материал для поиска
     * @param amount Количество материала
     * @return true, если в инвентаре есть указанное количество материала, иначе false
     */
    @Override
    public boolean hasMaterial(PlayerInventory inventory, Material material, int amount) {
        Objects.requireNonNull(inventory, "Inventory cannot be null");
        Objects.requireNonNull(material, "Material cannot be null");
        if (amount <= 0) throw new IllegalArgumentException("Amount cannot be less than 0");

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
    @Override
    public boolean hasMaterial(PlayerInventory inventory, Material material) {
        Objects.requireNonNull(inventory, "Inventory cannot be null");
        Objects.requireNonNull(material, "Material cannot be null");
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
    @Override
    public boolean hasItemStack(PlayerInventory inventory, ItemStack stack, int amount) {
        Objects.requireNonNull(inventory, "Inventory cannot be null");
        Objects.requireNonNull(stack, "ItemStack cannot be null");
        if (amount <= 0) throw new IllegalArgumentException("Amount cannot be less than 0");

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
    @Override
    public boolean hasItemStack(PlayerInventory inventory, ItemStack stack) {
        Objects.requireNonNull(inventory, "Inventory cannot be null");
        Objects.requireNonNull(stack, "ItemStack cannot be null");
        return inventory.contains(stack);
    }

    /**
     * Удаляет указанное количество материала из инвентаря игрока
     *
     * @param inventory Инвентарь игрока
     * @param material Удаляемый материал
     * @param amount Количество удаляемого предмета
     */
    @Override
    public void removeMaterial(PlayerInventory inventory, Material material, int amount) {
        Objects.requireNonNull(inventory, "Inventory cannot be null");
        Objects.requireNonNull(material, "Material cannot be null");
        if (amount <= 0) throw new IllegalArgumentException("Amount cannot be less than 0");

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
    @Override
    public void removeItemStack(PlayerInventory inventory, ItemStack stack, int amount) {
        Objects.requireNonNull(inventory, "Inventory cannot be null");
        Objects.requireNonNull(stack, "ItemStack cannot be null");
        if (amount <= 0) throw new IllegalArgumentException("Amount cannot be less than 0");
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