package wtf.wooly.playerfreeze.listeners;

import wtf.wooly.playerfreeze.PlayerFreeze;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

public class PlayerQuit implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        PlayerFreeze plugin = PlayerFreeze.getPlugin();
        Player player = e.getPlayer();
        if(PlayerFreeze.frozenPlayers.contains(player.getUniqueId())){
            List<String> messages = plugin.getConfig().getStringList("log_out_commands");

            for(String message : messages){
                plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), message.replace("[username]", player.getName()));
            }


            if(!plugin.getConfig().getBoolean("persist_across_relog"))
                PlayerFreeze.frozenPlayers.remove(player.getUniqueId());

        }

    }
}
