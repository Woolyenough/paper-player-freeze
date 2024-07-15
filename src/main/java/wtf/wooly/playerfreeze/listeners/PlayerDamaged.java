package wtf.wooly.playerfreeze.listeners;

import wtf.wooly.playerfreeze.PlayerFreeze;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.List;
import java.util.Map;

public class PlayerDamaged implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if(e.getEntity().getType() != EntityType.PLAYER) return;

        if (!PlayerFreeze.protLevel.equalsIgnoreCase("invincible")) return;
        if (!PlayerFreeze.frozenPlayers.contains(e.getEntity().getUniqueId())) return;

        e.setCancelled(true);
    }
    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
        if(e.getEntity().getType() != EntityType.PLAYER) return;
        if(e.getDamager().getType() != EntityType.PLAYER) return;

        Player player = (Player) e.getEntity();

        if(!PlayerFreeze.frozenPlayers.contains(player.getUniqueId())) return;

        Player damager = (Player) e.getDamager();

        if (!List.of("pvp","invincible").contains(PlayerFreeze.protLevel)) return;

        e.setCancelled(true);

        PlayerFreeze plugin = PlayerFreeze.getPlugin();
        damager.sendMessage(PlayerFreeze.formatMsg(plugin.getConfig().getString("cant_damage"), Map.of("[username]", player.getName())));
    }
}
