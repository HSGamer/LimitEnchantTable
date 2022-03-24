package me.hsgamer.limitenchanttable.listener;

import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.limitenchanttable.LimitEnchantTable;
import me.hsgamer.limitenchanttable.config.MessageConfig;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlaceListener extends BaseListener {
    public PlaceListener(LimitEnchantTable instance) {
        super(instance);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        fetchEnchantingTable(event.getBlockPlaced(), enchantingTable -> {
            ItemStack item = event.getItemInHand();
            if (item.getType() != Material.ENCHANTING_TABLE) return false;
            ItemMeta meta = item.getItemMeta();

            int limit = limitKey.get(meta);
            if (limit <= 0) {
                MessageUtils.sendMessage(event.getPlayer(), MessageConfig.NO_LIMIT.getValue());
            } else {
                MessageUtils.sendMessage(
                        event.getPlayer(),
                        MessageConfig.LIMIT.getValue().replace("{limit}", Integer.toString(limit))
                );
            }

            limitKey.copy(meta, enchantingTable);
            return true;
        });
    }
}
