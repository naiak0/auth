package me.naiak.auth;

import me.naiak.auth.commands.LoginCommand;
import me.naiak.auth.commands.RegisterCommand;
import me.naiak.auth.events.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import me.naiak.auth.commands.HelloCommand;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Main extends JavaPlugin {

    public static ArrayList<UUID> registeredPlayers = new ArrayList<>();
    public static ArrayList<UUID> awaitingLogin = new ArrayList<>();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        super.onEnable();
        loadcommands();
        this.saveDefaultConfig();
    }      // TODO: ogarnij te public statici i array listy aby bylo jak najmniej

    void loadcommands() {
        Objects.requireNonNull(getCommand("hello")).setExecutor(new HelloCommand());
        Objects.requireNonNull(getCommand("register")).setExecutor(new RegisterCommand());
        Objects.requireNonNull(getCommand("login")).setExecutor(new LoginCommand());
    }

    public static void grantAccess(UUID uuid) {
        registeredPlayers.add(uuid);
    }

    public static boolean isRegistered(UUID uuid) {
        return registeredPlayers.contains(uuid);
    }

    public static boolean getAwaitingLogin(UUID uuid) {
        return registeredPlayers.contains(uuid);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
