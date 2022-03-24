package me.hsgamer.limitenchanttable.command;

import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.limitenchanttable.LimitEnchantTable;
import me.hsgamer.limitenchanttable.Permissions;
import me.hsgamer.limitenchanttable.config.MessageConfig;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GiveCommand extends Command {
    private final LimitEnchantTable instance;

    public GiveCommand(LimitEnchantTable instance) {
        super("giveenchantingtable", "Give an enchanting table to a player", "/giveenchantingtable <player> [amount] [limit]", Arrays.asList("ge", "giveet"));
        setPermission(Permissions.GIVE.getName());
        this.instance = instance;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!testPermission(sender)) {
            return false;
        }
        if (args.length < 1) {
            MessageUtils.sendMessage(sender, getUsage());
            return false;
        }
        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            MessageUtils.sendMessage(sender, MessageConfig.NO_PLAYER.getValue());
            return false;
        }

        int amount = 1;
        if (args.length > 1) {
            try {
                amount = Integer.parseInt(args[1]);
            } catch (NumberFormatException ignored) {
                // IGNORED
            }
        }

        int limit = instance.getLimitKey().getDefaultValue();
        if (args.length > 2) {
            try {
                limit = Integer.parseInt(args[2]);
            } catch (NumberFormatException ignored) {
                // IGNORED
            }
        }

        ItemStack item = new ItemStack(Material.ENCHANTING_TABLE, amount);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        instance.getLimitKey().set(meta, limit);
        item.setItemMeta(meta);

        player.getInventory().addItem(item);
        MessageUtils.sendMessage(sender, MessageConfig.GIVE_SUCCESS.getValue().replace("{player}", player.getName()));
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        if (args.length == 1) {
            return Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName).filter(player -> args[0].isBlank() || player.toLowerCase().startsWith(args[0].toLowerCase())).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }
}
