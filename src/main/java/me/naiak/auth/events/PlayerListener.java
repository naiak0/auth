package me.naiak.auth.events;

import me.naiak.auth.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class PlayerListener implements Listener {
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e) {
        // TODO: zooptymalizuj kod do minimum, aby byl jak najszybszy
        Plugin plugin = JavaPlugin.getPlugin(Main.class);
        Player player = e.getPlayer();
        FileConfiguration config = plugin.getConfig();
        UUID uuid = player.getUniqueId();
        Bukkit.getLogger().info("Offline UUID: " + player.getUniqueId());
        Main.awaitingLogin.add(uuid);
        if (config.contains(uuid.toString())) {
            Main.registeredPlayers.add(uuid);
            player.sendMessage("§cUse /login <password>");
        } else {
            player.sendMessage("§eYou are not registered! Use §f/register <password>");
        }
        int taskId = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            if (Main.awaitingLogin.contains(uuid)) {
                player.kick(Component.text("§cYou took too long to log in!"));
            }
        }, 40 * 20L);
    }

    @EventHandler
    public void OnPlayerMove(PlayerMoveEvent e) {
        // TODO: zooptymalizuj kod do minimum, aby byl jak najszybszy
        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();
        if (Main.awaitingLogin.contains(uuid)) {
            e.setCancelled(true);
            if (Main.isRegistered(uuid)) {
                player.sendMessage("§cYou must log in first! Use /login <password>");
            } else {
                player.sendMessage("§cYou must register first! Use /register <password> <password>");
            }
        }
    }
}
