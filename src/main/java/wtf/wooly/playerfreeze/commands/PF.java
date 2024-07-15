package wtf.wooly.playerfreeze.commands;

import wtf.wooly.playerfreeze.PlayerFreeze;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

import org.jetbrains.annotations.NotNull;

public class PF implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission(PlayerFreeze.permAdmin)) return true;
        PlayerFreeze plugin = PlayerFreeze.getPlugin();
        plugin.reloadConfig();
        if(!List.of("none", "pvp", "invincible").contains(plugin.getConfig().getString("protection_level"))){
            plugin.getLogger().severe("Invalid option for `protection_level` in config.yml. Setting it to `invincible` as default.");
            plugin.getConfig().set("protection_level", "invincible");
            plugin.saveConfig();
        }
        sender.sendMessage("Reloaded config.");
        return true;
    }
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender.hasPermission(PlayerFreeze.permAdmin) && args.length == 1)
            return List.of("reload");
        return List.of();
    }
}
