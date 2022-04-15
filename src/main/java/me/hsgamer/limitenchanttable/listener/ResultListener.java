package me.hsgamer.limitenchanttable.listener;

import com.destroystokyo.paper.event.inventory.PrepareResultEvent;
import me.hsgamer.limitenchanttable.LimitEnchantTable;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;

public class ResultListener extends BaseListener {
    public ResultListener(LimitEnchantTable instance) {
        super(instance);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPrepareResult(PrepareResultEvent event) {
        ItemStack item = event.getResult();
        if (item != null && item.getType() == Material.ENCHANTING_TABLE) {
            event.setResult(null);
        }
    }
}
