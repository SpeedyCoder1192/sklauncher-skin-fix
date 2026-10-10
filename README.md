# Server Skins First

Makes SKlauncher show the skins your server sends.

SKlauncher puts everyone's SKlauncher skin on them, even when the server sends a different one. So if your server uses FabricTailor or SkinsRestorer, SKlauncher players never see those skins. You run `/skin`, it says it worked, and nothing changes.

With this mod installed:

- if the server sends a skin for a player, you see that one
- if it doesn't, you see their SKlauncher skin like before

It's client-side only and has no settings. The server doesn't need it. Other players only need it if they also use SKlauncher. On any other launcher it does nothing.

Works with Microsoft and SKlauncher accounts.

## Versions

Fabric only.

| File | Minecraft |
|---|---|
| `+1.20.4` | 1.20.3 to 1.21.8 |
| `+1.21.11` | 1.21.9 to 1.21.11 |
| `+26.2` | 26.1 to 26.2 |
| `+26.3` | 26.3 |

1.20.2 and older don't need it, SKlauncher doesn't replace server skins there.

## How it works

SKlauncher's game agent edits the code where Minecraft reads the skin a server sent and swaps in the SKlauncher one. This mod hooks the start of that code and uses the server's skin before the edit is reached. SKlauncher's own files aren't changed, so updating it doesn't break anything.

Not affiliated with SKlauncher.

## Building

`./gradlew buildAll`, jars go to `build/libs/`. `./release.sh` uploads to Modrinth and GitHub.
