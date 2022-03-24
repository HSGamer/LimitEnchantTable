package me.hsgamer.limitenchanttable;

import me.hsgamer.hscore.bukkit.baseplugin.BasePlugin;
import me.hsgamer.hscore.bukkit.key.PluginKeyPair;
import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.limitenchanttable.command.GiveCommand;
import me.hsgamer.limitenchanttable.config.MainConfig;
import me.hsgamer.limitenchanttable.config.MessageConfig;
import me.hsgamer.limitenchanttable.listener.BreakListener;
import me.hsgamer.limitenchanttable.listener.EnchantListener;
import me.hsgamer.limitenchanttable.listener.PlaceListener;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;

public final class LimitEnchantTable extends BasePlugin {
    private final MainConfig mainConfig = new MainConfig(this);
    private final MessageConfig messageConfig = new MessageConfig(this);
    private PluginKeyPair<Integer> limitKey;

    @Override
    public void load() {
        MessageUtils.setPrefix(MessageConfig.PREFIX::getValue);
        mainConfig.setup();
        messageConfig.setup();

        limitKey = new PluginKeyPair<>(
                new NamespacedKey(this, "limit"),
                PersistentDataType.INTEGER,
                MainConfig.ENCHANT_LIMIT.getValue()
        );
    }

    @Override
    public void enable() {
        registerListener(new BreakListener(this));
        registerListener(new EnchantListener(this));
        registerListener(new PlaceListener(this));

        registerCommand(new GiveCommand(this));
    }

    public PluginKeyPair<Integer> getLimitKey() {
        return limitKey;
    }

    public MainConfig getMainConfig() {
        return mainConfig;
    }

    public MessageConfig getMessageConfig() {
        return messageConfig;
    }
}
