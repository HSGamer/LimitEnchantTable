package me.hsgamer.limitenchanttable.config;

import me.hsgamer.hscore.bukkit.config.BukkitConfig;
import me.hsgamer.hscore.config.PathableConfig;
import me.hsgamer.hscore.config.path.impl.IntegerConfigPath;
import org.bukkit.plugin.Plugin;

public class MainConfig extends PathableConfig {
    public static final IntegerConfigPath ENCHANT_LIMIT = new IntegerConfigPath("enchant-limit", 20);

    public MainConfig(Plugin plugin) {
        super(new BukkitConfig(plugin, "config.yml"));
    }
}
