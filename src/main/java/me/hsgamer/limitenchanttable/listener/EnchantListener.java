package me.hsgamer.limitenchanttable.listener;

import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.limitenchanttable.LimitEnchantTable;
import me.hsgamer.limitenchanttable.config.MessageConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;

public class EnchantListener extends BaseListener {
    public EnchantListener(LimitEnchantTable instance) {
        super(instance);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPrepare(PrepareItemEnchantEvent event) {
        fetchEnchantingTable(event.getEnchantBlock(), enchantingTable -> {
            int limit = limitKey.get(enchantingTable);
            if (limit <= 0) {
                event.setCancelled(true);
            }
            if (!limitKey.contains(enchantingTable)) {
                limitKey.set(enchantingTable, limit);
                return true;
            }
            return false;
        });
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onEnchant(EnchantItemEvent event) {
        fetchEnchantingTable(event.getEnchantBlock(), enchantingTable -> {
            int limit = limitKey.get(enchantingTable);
            if (limit <= 0) {
                event.setCancelled(true);
                return false;
            } else {
                int newLimit = limit - 1;
                limitKey.set(enchantingTable, newLimit);
                if (newLimit <= 0) {
                    MessageUtils.sendMessage(event.getEnchanter(), MessageConfig.NO_LIMIT.getValue());
                } else {
                    MessageUtils.sendMessage(
                            event.getEnchanter(),
                            MessageConfig.LIMIT.getValue().replace("{limit}", Integer.toString(newLimit))
                    );
                }
                return true;
            }
        });
    }
}
