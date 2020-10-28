# Lootstrap

[![GitHub release](https://img.shields.io/github/release/haykam821/Lootstrap.svg?style=popout&label=github)](https://github.com/haykam821/Lootstrap/releases/latest)
[![CurseForge](https://img.shields.io/static/v1?style=popout&label=curseforge&message=project&color=6441A4)](https://www.curseforge.com/minecraft/mc-mods/lootstrap)
[![Discord](https://img.shields.io/static/v1?style=popout&label=chat&message=discord&color=7289DA)](https://discord.gg/x7JrD6x)

Allows a player to recieve loot when they join the server.

Lootstrap requires the [Fabric modloader](https://fabricmc.net/use/).

## Installation

1. Download Lootstrap from [CurseForge](https://www.curseforge.com/minecraft/mc-mods/lootstrap/files) or [GitHub](https://github.com/haykam821/Lootstrap/releases).
2. Place the downloaded file in your `mods` folder.

## Usage

In a datapack, override the following loot tables:

* `lootstrap:first_join` - Gives the loot table to a player when they first join a server.
* `lootstrap:rejoin` - Gives the loot table to a player when they join a server after the first time.
* `lootstrap:join` - Gives the loot table to a player when they join a server, no matter which time.
* `lootstrap:join_index_<n>` - Gives the loot table when they join for the `n`th time.
