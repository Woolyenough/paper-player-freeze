## Commands:
- `/freeze <player>` - freeze a player
- `/player-freeze reload` (alias: `/pf`) - reload config

## Permissions:
- `pf.use` - run `/freeze` command
- `pf.immune` - immune to being frozen
- `pf.notify` - see the frozen broadcast
- `pf.admin` - permission to run `/pf reload` command

<details><summary><b>Default config.yml</b></summary>

```yaml
# All messages follow the MiniMessage format. More info: https://docs.advntr.dev/minimessage/format.html

# Message to send the player when they try to move while frozen
currently_frozen: "You are frozen!"

# Message to send the player when they are first frozen/unfrozen
# Placeholders: [username], [invoker]
frozen: "You have been frozen by [invoker]! Please await further instructions."
unfrozen: "You have been unfrozen by [invoker]!"

# Message to send the player running the command
# Placeholders: [username], [invoker]
frozen_invoker: "You have frozen [username]!"
unfrozen_invoker: "You have unfrozen [username]!"

# Message to send to all players
# Placeholders: [username], [invoker]
frozen_broadcast: "[username] was frozen by [invoker]!"
unfrozen_broadcast: "[username] was unfrozen by [invoker]!"

# Message to send if the player is immune to being frozen
# Placeholders: [username], [invoker]
player_is_immune: "[username] is immune to being frozen!"

# Message to send to players who try to damage a frozen player
# Placeholders: [username]
cant_damage: "You can't damage [username] because they are frozen!"

# Player not found
# Placeholder: [entered]
player_not_found: "Player [entered] not found."

# Message to send if command was ran incorrectly
incorrect_usage: "Usage: /freeze <player>"

# How protected should frozen players be?
# Options:
#    "none" - takes damage as usual
#    "pvp" - protected from just other players
#    "invincible" - protected from all damage
protection_level: "invincible"

# If a player logs out when frozen, should they remain frozen when they log back in?
persist_across_relog: true

# Commands to run when a player logs out while frozen
# Placeholder: [username]
log_out_commands:
  - "ban [username] Logging out while frozen"
  - "say [username] logged out while frozen!"
```
</br></details>