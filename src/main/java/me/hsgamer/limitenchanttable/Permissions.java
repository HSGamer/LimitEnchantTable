package me.hsgamer.limitenchanttable;

import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;

public final class Permissions {
    public static final Permission GIVE = new Permission("limitenchanttable.give");

    static {
        Bukkit.getPluginManager().addPermission(GIVE);
    }

    private Permissions() {
        // EMPTY
    }
}
