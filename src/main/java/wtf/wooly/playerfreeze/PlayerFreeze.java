package wtf.wooly.playerfreeze;

import wtf.wooly.playerfreeze.listeners.*;
import wtf.wooly.playerfreeze.commands.*;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public final class PlayerFreeze extends JavaPlugin {
    private static PlayerFreeze instance;
    public static Set<UUID> frozenPlayers = new HashSet<>();
    public static final String permUse = "pf.use";
    public static final String permImmune = "pf.immune";
    public static final String permNotify = "pf.notify";
    public static final String permAdmin = "pf.admin";
    public static String protLevel = "invincible";
    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        getCommand("freeze").setExecutor(new Freeze());
        getCommand("player-freeze").setExecutor(new PF());
        getServer().getPluginManager().registerEvents(new PlayerDamaged(), this);
        getServer().getPluginManager().registerEvents(new PlayerJump(), this);
        getServer().getPluginManager().registerEvents(new PlayerMove(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);

        getLogger().info("PlayerFreeze is enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static PlayerFreeze getPlugin() {
        return instance;
    }
    public static Component formatMsg(String message, Map<String, String> placeholders) {
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            message = message.replace(entry.getKey(), entry.getValue());
        }
        return MiniMessage.miniMessage().deserialize(message);
    }
}