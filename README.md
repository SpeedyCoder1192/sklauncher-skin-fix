# Server Skins First

Makes skin changing mods and plugins work on SKlauncher.

SKlauncher puts everyone's SKlauncher skin on them and overrides skins set by things like FabricTailor and SkinsRestorer. You run `/skin`, it says it worked, and nothing changes. This mod fixes that:

- if a player has a skin set by a skin changer, you see that one
- if they don't, you see their SKlauncher skin like before

It's client-side only and has no settings. Servers don't need it. Other players only need it if they also use SKlauncher. On any other launcher it does nothing.

Works with Microsoft and SKlauncher accounts.

## Versions

Fabric only.

| File | Minecraft |
|---|---|
| `+1.20.4` | 1.20.3 to 1.21.8 |
| `+1.21.11` | 1.21.9 to 1.21.11 |
| `+26.2` | 26.1 to 26.2 |
| `+26.3` | 26.3 |

1.20.2 and older don't need it, SKlauncher doesn't replace skins there.

## How it works

Skin changers give a player a skin by attaching it to their profile. SKlauncher's game agent edits the code where Minecraft reads that skin and swaps in the SKlauncher one. This mod hooks the start of that code and uses the attached skin before the edit is reached. SKlauncher's own files aren't changed, so updating it doesn't break anything.

Not affiliated with SKlauncher.

## Building

`./gradlew buildAll`, jars go to `build/libs/`. `./release.sh` uploads to Modrinth and GitHub.
