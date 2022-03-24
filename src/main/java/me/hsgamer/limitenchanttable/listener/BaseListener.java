package me.hsgamer.limitenchanttable.listener;

import me.hsgamer.hscore.bukkit.key.PluginKeyPair;
import me.hsgamer.limitenchanttable.LimitEnchantTable;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.EnchantingTable;
import org.bukkit.event.Listener;

import java.util.function.Predicate;

public class BaseListener implements Listener {
    protected final PluginKeyPair<Integer> limitKey;

    protected BaseListener(LimitEnchantTable instance) {
        this.limitKey = instance.getLimitKey();
    }

    protected void fetchEnchantingTable(Block block, Predicate<EnchantingTable> function) {
        BlockState state = block.getState();
        if (state instanceof EnchantingTable && function.test((EnchantingTable) state)) {
            state.update(true, false);
        }
    }
}
