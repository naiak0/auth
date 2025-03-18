package me.naiak.auth.events;

import me.naiak.auth.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class PlayerListener implements Listener {
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e) {
        Plugin plugin = JavaPlugin.getPlugin(Main.class);
        Player player = e.getPlayer();
        FileConfiguration config = plugin.getConfig();
        UUID uuid = player.getUniqueId();
        Bukkit.getLogger().info("Offline UUID: " + player.getUniqueId());
        player.sendMessage("§eYou are not registered! Use §f/register <password>");
        int taskId = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            player.kick(Component.text("§cYou took too long to log in!"));
        }, 40 * 20L);
    }
}
