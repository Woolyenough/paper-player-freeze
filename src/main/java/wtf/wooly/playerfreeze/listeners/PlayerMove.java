package wtf.wooly.playerfreeze.listeners;

import wtf.wooly.playerfreeze.PlayerFreeze;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Map;

import static wtf.wooly.playerfreeze.PlayerFreeze.formatMsg;

public class PlayerMove implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();

        if (!PlayerFreeze.frozenPlayers.contains(player.getUniqueId())) return;

        Location originalLocation = e.getFrom();
        Location newLocation = e.getTo();

        if(originalLocation.getX() != newLocation.getX() || originalLocation.getZ() != newLocation.getZ()) {
            player.teleport(new Location(originalLocation.getWorld(), originalLocation.getX(), newLocation.getY(), originalLocation.getZ(), newLocation.getYaw(), newLocation.getPitch()));
            PlayerFreeze plugin = PlayerFreeze.getPlugin();
            player.sendMessage(formatMsg(plugin.getConfig().getString("currently_frozen"), Map.of()));
        }
    }
}
