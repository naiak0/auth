package me.naiak.auth.commands;

import me.naiak.auth.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class RegisterCommand implements CommandExecutor {
    Plugin plugin = JavaPlugin.getPlugin(Main.class);

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {
            if (strings.length == 2) {
                if (strings[0].equals(strings[1])) {
                    String password = strings[1];
                    plugin.getLogger().info(password);
                }
            }
        }
        return false;
    }
}
