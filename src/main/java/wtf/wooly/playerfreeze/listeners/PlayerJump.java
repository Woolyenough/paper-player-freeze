package wtf.wooly.playerfreeze.listeners;

import wtf.wooly.playerfreeze.PlayerFreeze;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Map;

public class PlayerJump implements Listener {
    @EventHandler
    public void onPlayerJump(PlayerJumpEvent e) {
        Player player = e.getPlayer();
        if (PlayerFreeze.frozenPlayers.contains(player.getUniqueId())) {
            e.setCancelled(true);
            PlayerFreeze plugin = PlayerFreeze.getPlugin();
            player.sendMessage(PlayerFreeze.formatMsg(plugin.getConfig().getString("currently_frozen"), Map.of()));
        }
    }
}
