package me.hsgamer.limitenchanttable.listener;

import me.hsgamer.limitenchanttable.LimitEnchantTable;
import org.bukkit.Material;
import org.bukkit.block.EnchantingTable;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BreakListener extends BaseListener {
    public BreakListener(LimitEnchantTable instance) {
        super(instance);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBreak(BlockDropItemEvent event) {
        if (!(event.getBlockState() instanceof EnchantingTable)) return;
        EnchantingTable enchantingTable = (EnchantingTable) event.getBlockState();

        ItemStack itemStack = null;
        List<Item> duplicated = new ArrayList<>();
        for (Item item : event.getItems()) {
            if (item.getItemStack().getType() == Material.ENCHANTING_TABLE) {
                if (itemStack == null) {
                    itemStack = item.getItemStack();
                } else {
                    duplicated.add(item);
                }
            }
        }
        if (itemStack == null) return;
        event.getItems().removeAll(duplicated);

        ItemMeta itemMeta = itemStack.getItemMeta();
        limitKey.copy(enchantingTable, itemMeta);
        itemStack.setItemMeta(itemMeta);
    }
}
