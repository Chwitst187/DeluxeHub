# DeluxeHub

<div align="center">
  <img src="https://i.imgur.com/sJafO6B.png" alt="DeluxeHub Header">
  <p>
    <a href="https://wiki.lewisdev.fun/free-resources/deluxehub">Wiki</a>
    ·
    <a href="https://www.spigotmc.org/resources/49425/">SpigotMC</a>
    ·
    <a href="https://discord.lewisdev.fun">Discord</a>
  </p>

  [![Codacy Badge](https://api.codacy.com/project/badge/Grade/0daefdcd09d14086b2f96934d283371e)](https://www.codacy.com/manual/ItzSave/DeluxeHub?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ItzSave/DeluxeHub&amp;utm_campaign=Badge_Grade)
  [![Stargazers][stars-shield]][stars-url]
  [![Forks][forks-shield]][forks-url]
  [![Issues][issues-shield]][issues-url]
</div>

## Was ist DeluxeHub?

`DeluxeHub` ist ein modularer Hub-Feature-Stack für Spigot/Paper/Folia mit Fokus auf:
- Hub-Join/Spawn-Management
- visuelle Infos (Scoreboard + Tablist)
- Spielerinteraktion (HUB-Items, Menüs, Hologramme)
- Chat-/World-Moderation
- Commands (zentrales `/deluxehub` + Module)
- konfigurierbare Action-Workflows auf Commands/GUI-Items

Das Plugin ist aus `src/main/java` als modulare Architektur aufgebaut: `CommandManager`, `ModuleManager`, `ConfigManager`, `InventoryManager`, `ActionManager`.

## Voraussetzungen und Umgebung

- Spigot/Paper/Folia Server
- Java 21
- Optional: PlaceholderAPI, HeadDatabase, Multiverse-Core, MythicDungeons
- Das Plugin setzt Spigot-Klassen voraus und beendet sich bei reiner Nicht-Spigot-Umgebung

## Build und Installation

1. Plugin bauen:
   - `./gradlew.bat clean build`
2. Die Datei `build/libs/DeluxeHub-<version>-patched.jar` auf den Server kopieren
3. Server neu starten (für Module-/Config-Ladevorgänge empfohlen)
4. Nach dem Start: `plugins/DeluxeHub/` prüfen

## Schnell-Übersicht der Dateien

- `config.yml` – globale Features + Einstellungen
- `commands.yml` – aktivierbare Commands, Aliase, custom commands
- `messages.yml` – alle Textbausteine
- `data.yml` – Laufzeitdaten
- `plugins/DeluxeHub/menus/*.yml` – GUI-Definitionen

## Commands

- Hauptkommando: `/deluxehub` und Alias `/dhub`

### Standard-Befehle

| Command | Usage | Permission |
|---|---|---|
| `help` | `/deluxehub help` | `deluxehub.command.help` |
| `reload` | `/deluxehub reload` | `deluxehub.command.reload` |
| `info` | `/deluxehub info` | `deluxehub.command.help` |
| `open` | `/deluxehub open <menuId>` | `deluxehub.command.openmenu` |
| `scoreboard` | `/deluxehub scoreboard` | `deluxehub.command.scoreboard` |
| `hologram` | `/deluxehub hologram ...` | `deluxehub.command.holograms` |
| `list` | `/deluxehub hologram list` | `deluxehub.command.holograms` |
| `create` | `/deluxehub hologram create <id>` | `deluxehub.command.holograms` |
| `remove` | `/deluxehub hologram remove <id>` | `deluxehub.command.holograms` |
| `move` | `/deluxehub hologram move <id>` | `deluxehub.command.holograms` |
| `setline` | `/deluxehub hologram setline <id> <line> <text>` | `deluxehub.command.holograms` |
| `addline` | `/deluxehub hologram addline <id> <text>` | `deluxehub.command.holograms` |
| `removeline` | `/deluxehub hologram removeline <id> <line>` | `deluxehub.command.holograms` |

### Modul-Commands

`commands.yml` bestimmt diese per Eintrag `commands.*.enabled`.

| Command | Usage | Permission |
|---|---|---|
| `fly` | `/fly [player]` | `deluxehub.command.fly`, `deluxehub.command.fly.others` |
| `clearchat` | `/clearchat [player]` | `deluxehub.command.clearchat` |
| `lockchat` | `/lockchat` | `deluxehub.command.lockchat` |
| `setlobby` | `/setlobby` | `deluxehub.command.setlobby` |
| `lobby` | `/lobby` | keine |
| `vanish` | `/vanish` | `deluxehub.command.vanish` |
| `gamemode` | `/gamemode <0|1|2|3|survival|creative|adventure|spectator> [player]` | `deluxehub.command.gamemode`, `deluxehub.command.gamemode.others` |
| `gmc`, `gms`, `gma`, `gmsp` | `/<alias> [player]` | `deluxehub.command.gamemode`, `deluxehub.command.gamemode.others` |

## Permissions

### Root-Node Gruppen

- `deluxehub.*`
- `deluxehub.command.*`
- `deluxehub.bypass.*`
- `deluxehub.alert.*`
- `deluxehub.item.*`
- `deluxehub.player.*`
- `deluxehub.block.*`

### Command-Rechte

| Permission | Zweck |
|---|---|
| `deluxehub.command.help` | `/deluxehub` Hilfe und `/deluxehub info` |
| `deluxehub.command.reload` | Reload-Funktion |
| `deluxehub.command.scoreboard` | Scoreboard-Toggle |
| `deluxehub.command.openmenu` | Menüs aus `/deluxehub open` |
| `deluxehub.command.holograms` | Hologramm-Befehle |
| `deluxehub.command.gamemode` | Gamemode für dich |
| `deluxehub.command.gamemode.others` | Gamemode für andere |
| `deluxehub.command.clearchat` | Chat clear |
| `deluxehub.command.lockchat` | Chat-Lock |
| `deluxehub.command.fly` | `/fly` für dich |
| `deluxehub.command.fly.others` | `/fly` für andere |
| `deluxehub.command.setlobby` | `/setlobby` |
| `deluxehub.command.vanish` | `/vanish` |

### Bypass- und Alert-Rechte

| Permission | Zweck |
|---|---|
| `deluxehub.bypass.antiswear` | Anti-Swear-Regel umgehen |
| `deluxehub.bypass.commands` | Command-Blocker umgehen |
| `deluxehub.bypass.lockchat` | Chat-Lock umgehen |
| `deluxehub.bypass.antiwdl` | Anti-WDL umgehen |
| `deluxehub.bypass.doublejump` | DoubleJump umgehen |
| `deluxehub.alert.antiswear` | Anti-Swear-Alerts |
| `deluxehub.alert.antiwdl` | AntiWDL-Alerts |
| `deluxehub.alert.updates` | Update-Hinweise |
| `deluxehub.item.drop` | Item-Drop in blockgeschützten Welten |
| `deluxehub.item.pickup` | Item-Pickup in blockgeschützten Welten |
| `deluxehub.player.pvp` | PvP in geschützten Bereichen |
| `deluxehub.block.interact` | Block-Interaktion in geschützten Bereichen |
| `deluxehub.block.break` | Blockabbau in blockgeschützten Bereichen |
| `deluxehub.block.place` | Platzieren von Blöcken |

## Module und Feature-Blöcke

Lade-/Disable-Logik findet über `config.yml` statt.

- `ANTI_WDL` (`anti_wdl.enabled`)
- `DOUBLE_JUMP` (`double_jump.enabled`)
- `LAUNCHPAD` (`launchpad.enabled`)
- `SCOREBOARD` (`scoreboard.enabled`)
- `TABLIST` (`tablist.enabled`)
- `ANNOUNCEMENTS` (`announcements.enabled`)
- `WORLD_PROTECT` (Weltschutz durch world_settings)
- `ANTI_SWEAR` (`anti_swear.enabled`)
- `COMMAND_BLOCK` (`command_block.enabled`)
- `LOBBY`, `HOTBAR_ITEMS`, `VANISH`, `HOLOGRAMS`, `PLAYER_LISTENER`, `PLAYER_OFFHAND_LISTENER`
- `CHAT_LOCK`, `CUSTOM_COMMANDS` (immer aktiv)

## Konfigurationshinweise

### `config.yml` wichtige Blöcke

- `disabled-worlds.invert` + `worlds`: Steuerung pro world
- `scoreboard` + `tablist`: Header/Footer, Refresh, Delay, Linien
- `announcements`: Rotierender Broadcast mit Delay + Mindestanzahl Online-Spieler
- `launchpad`, `double_jump`: Velocity/Power und Aktionen bei Nutzung
- `command_block` und `anti_swear`: geblockte Commands / Wörter
- `chat_locked`: Persistenzstatus
- `world_settings`: Hunger, PvP, Interactions, Wetter, Schadenstypen etc.
- `join_leave_messages`, `join_events`, `join_settings`
- `custom_join_items`, `player_hider`

### `commands.yml`

- `custom_commands` registriert pseudo-native Commands, die über Action-Runs funktionieren
- `commands` steuert Built-In-Command-Module und deren Aliase

### `messages.yml`

- Spieler-Rückmeldungen vollständig zentral und mit Platzhaltern
- Platzhalter in Nachrichten: `%player%`, `%time%`, `%name%`, `%gamemode%`, `%line%` usw.

### `data.yml`

- Laufzeitdaten inkl. `chat_locked`
- Hologramm-Daten werden ebenfalls über diese Ebene verwaltet

## Custom Action Engine (GUI / Custom Commands)

Verfügbare Aktionen (genauer Identifier):

- `[MESSAGE]`
- `[BROADCAST]`
- `[TITLE]`
- `[ACTIONBAR]`
- `[SOUND]`
- `[COMMAND]`
- `[CONSOLE]`
- `[GAMEMODE]`
- `[PROXY]`
- `[EFFECT]`
- `[MENU]`
- `[CLOSE]`
- `[DELAY:<sekunden>]`

Beispiel:

```yaml
actions:
  - '[MESSAGE] &eWillkommen, &b%player%'
  - '[TITLE] &bWillkommen;&fViel Spaß!;1;2;1'
  - '[DELAY:3] [SOUND] ENTITY_PLAYER_LEVELUP'
  - '[CONSOLE] tellraw %player% {"text":"Ready"}'
```

Für Textplatzhalter gilt immer Placeholder-Auflösung:
- `%player%`, `%online%`, `%online_max%`, `%location%`
- plus PlaceholderAPI-Platzhalter, falls aktiv

## Menüs

`plugins/DeluxeHub/menus/` ist der zentrale Ort.

- Jede Datei wird als Menü mit der Dateinamen-ID geladen
- `slots`, `title`, `items`, `refresh` pro Menü
- Item-Level: `slot` oder `slots`, `material`, Lore, Flags, NBT/Skulls (Base64/Username/HeadDatabase)
- Aktionen pro Item über `actions`

Standarddatei: `serverselector.yml`.

## Hooks

- Softdepend: PlaceholderAPI / Multiverse-Core / MythicDungeons
- Optionaler Hook auf HeadDatabase
- `BungeeCord` Plugin Message Channel wird für `[PROXY]` genutzt

## Build-/Betriebsdetails

- Bausystem: Gradle ShadowJar, Java 21 Toolchain
- Artefakt-Namensschema: `${project.name}-${project.version}-patched.jar`
- `update-check` ist standardmäßig aktiv
- API-Version im `plugin.yml`: `1.19`

## Lizenz

This project is licensed under the GNU General Public License v3.0 License - see the [LICENSE](LICENSE) file for details.

<!-- MARKDOWN LINKS & IMAGES -->
[forks-shield]: https://img.shields.io/github/forks/ItzSave/DeluxeHub.svg?style=for-the-badge
[forks-url]: https://github.com/ItzSave/DeluxeHub/network/members
[stars-shield]: https://img.shields.io/github/stars/ItzSave/DeluxeHub.svg?style=for-the-badge
[stars-url]: https://github.com/ItzSave/DeluxeHub/stargazers
[issues-shield]: https://img.shields.io/github/issues/ItzSave/DeluxeHub.svg?style=for-the-badge
[issues-url]: https://github.com/ItzSave/DeluxeHub/issues
