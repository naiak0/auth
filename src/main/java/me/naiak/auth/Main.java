package me.naiak.auth;

import me.naiak.auth.commands.LoginCommand;
import me.naiak.auth.commands.RegisterCommand;
import me.naiak.auth.events.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import me.naiak.auth.commands.HelloCommand;

import java.util.Objects;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        super.onEnable();
        loadcommands();
        this.saveDefaultConfig();
    }

    void loadcommands() {
        Objects.requireNonNull(getCommand("hello")).setExecutor(new HelloCommand());
        Objects.requireNonNull(getCommand("register")).setExecutor(new RegisterCommand());
        Objects.requireNonNull(getCommand("login")).setExecutor(new LoginCommand());
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
