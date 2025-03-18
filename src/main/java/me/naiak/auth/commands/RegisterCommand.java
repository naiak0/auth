package me.naiak.auth.commands;

import me.naiak.auth.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

public class RegisterCommand implements CommandExecutor {
    Plugin plugin = JavaPlugin.getPlugin(Main.class);
    FileConfiguration config = plugin.getConfig();
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {
            // TODO: zooptymalizuj kod do minimum, aby byl jak najszybszy
            if (strings.length == 2) {
                if (strings[0].equals(strings[1])) {
                    String password = strings[1];
                    UUID uuid = Objects.requireNonNull(((Player) commandSender).getPlayer()).getUniqueId();
                    config.addDefault(uuid.toString(), password);
                    config.options().copyDefaults(true);
                    plugin.saveConfig();
                    Main.grantAccess(uuid);
                    Main.awaitingLogin.remove(uuid);
                }
            }
        }
        return false;
    }
}
