package wtf.wooly.playerfreeze.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import wtf.wooly.playerfreeze.PlayerFreeze;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static wtf.wooly.playerfreeze.PlayerFreeze.formatMsg;

public class Freeze implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        PlayerFreeze plugin = PlayerFreeze.getPlugin();
        if(!sender.hasPermission(PlayerFreeze.permUse)) {
            return true;
        }
        if(args.length != 1) {
            sender.sendMessage(formatMsg(plugin.getConfig().getString("incorrect_usage"), Map.of()));
            return true;
        }
        Player target = plugin.getServer().getPlayer(args[0]);

        if(target == null) {
            sender.sendMessage(formatMsg(plugin.getConfig().getString("player_not_found"), Map.of("[entered]", args[0])));
            return true;
        }
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("[username]", target.getName());
        placeholders.put("[invoker]", sender.getName());
        if(target.hasPermission(PlayerFreeze.permImmune)) {
            sender.sendMessage(formatMsg(plugin.getConfig().getString("player_is_immune"), placeholders));
            return true;
        }

        if(!PlayerFreeze.frozenPlayers.contains(target.getUniqueId())) {
            target.sendMessage(formatMsg(plugin.getConfig().getString("frozen"), placeholders));
            sender.sendMessage(formatMsg(plugin.getConfig().getString("frozen_invoker"), placeholders));
            plugin.getServer().broadcast(formatMsg(plugin.getConfig().getString("frozen_broadcast"), placeholders), PlayerFreeze.permNotify);

            Location loc = target.getLocation();
            for (int y = loc.getBlockY(); y >= loc.getWorld().getMinHeight(); y--) {
                loc.setY(y);
                if (loc.getBlock().getType() != Material.AIR){
                    loc.setY(y + 1);
                    break;
                }
            }
            target.teleport(loc);
            PlayerFreeze.frozenPlayers.add(target.getUniqueId());
        }else{
            target.sendMessage(formatMsg(plugin.getConfig().getString("unfrozen"), placeholders));
            sender.sendMessage(formatMsg(plugin.getConfig().getString("unfrozen_invoker"), placeholders));
            plugin.getServer().broadcast(formatMsg(plugin.getConfig().getString("unfrozen_broadcast"), placeholders), PlayerFreeze.permNotify);
            PlayerFreeze.frozenPlayers.remove(target.getUniqueId());
        }
        return true;
    }
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender.hasPermission(PlayerFreeze.permUse) && args.length == 1)
            return PlayerFreeze.getPlugin().getServer().getOnlinePlayers().stream().map(Player::getName).toList();
        return List.of();
    }
}
