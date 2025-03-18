package me.naiak.auth;

import org.bukkit.plugin.java.JavaPlugin;
import me.naiak.auth.commands.HelloCommand;

import java.util.Objects;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        super.onEnable();
        loadcommands();
    }

    void loadcommands() {
        Objects.requireNonNull(getCommand("hello")).setExecutor(new HelloCommand());
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
