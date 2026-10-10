# Server Skins First

Lets the server decide your skin when you play through SKlauncher.

SKlauncher puts the skin from your SKlauncher account on every player, and it does that
even when the server sends its own skin. So skin mods and plugins like FabricTailor and
SkinsRestorer just don't show up for SKlauncher users: you set a skin with `/skin`, the
server says it worked, and nothing changes. This mod fixes that, for Microsoft and
SKlauncher accounts alike.

## What it does

- When the server sends a skin for a player, you see that skin.
- When it doesn't, you still see the player's SKlauncher skin, same as before.

That's it. It's client-side only, there's nothing to configure, and it only changes which
skin your own game picks. Servers don't need it, and other players don't need it to see
your server skin unless they're on SKlauncher too.

## Who needs it

Anyone who plays through SKlauncher on a server that sets skins, and server owners who
want their SKlauncher players to see those skins. On other launchers the mod does nothing.

## Versions

| Download | Minecraft |
|---|---|
| `+1.20.4` | 1.20.3 – 1.21.8 |
| `+1.21.11` | 1.21.9 – 1.21.11 |
| `+26.2` | 26.1 – 26.2 |
| `+26.3` | 26.3 |

Fabric only. You don't need it on 1.20.2 or older: SKlauncher's skin replacement doesn't
apply there, so server skins already show up.

## How it works

SKlauncher's game agent edits the part of Minecraft's skin loader that reads the skin a
server sent, and swaps in the SKlauncher skin. This mod hooks the start of that same code
and uses the server's skin before SKlauncher's edit is reached. SKlauncher's files aren't
touched, so its updates don't undo anything.

Not affiliated with SKlauncher.

## Building

```
./gradlew buildAll
```

Jars end up in `build/libs/`. `./release.sh` uploads to Modrinth and GitHub.
