package me.hsgamer.limitenchanttable.config;

import me.hsgamer.hscore.bukkit.config.BukkitConfig;
import me.hsgamer.hscore.config.PathableConfig;
import me.hsgamer.hscore.config.path.impl.StringConfigPath;
import org.bukkit.plugin.Plugin;

public class MessageConfig extends PathableConfig {
    public static final StringConfigPath PREFIX = new StringConfigPath("prefix", "&7[&bLimitEnchantTable&7] &f");
    public static final StringConfigPath GIVE_SUCCESS = new StringConfigPath("give-success", "&aYou have given the item to the player {player}");
    public static final StringConfigPath NO_PLAYER = new StringConfigPath("no-player", "&cNo player found");
    public static final StringConfigPath LIMIT = new StringConfigPath("limit", "&cYou can use the enchanting table &c{limit} &ctimes");
    public static final StringConfigPath NO_LIMIT = new StringConfigPath("no-limit", "&cYou can't use the enchanting table");

    public MessageConfig(Plugin plugin) {
        super(new BukkitConfig(plugin, "messages.yml"));
    }
}
