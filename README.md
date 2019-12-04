# MSEssentials
The first rocks.milspecsg.essentials plugin for Velocity!

[![Discord](https://img.shields.io/discord/619325430431875072?style=for-the-badge)](https://discord.gg/6cXUEQJ)

# Status
Please note, this plugin is still under heavy development, and is planned to be released ASAP.
If you have any requests, please submit them as an Issue and describe what you'd like to see implemented!

## Planned Features
player /msg

cross-server teleportation (just like /tpa)

## Current Features
Proxy-wide chat with luckperms support (Chat Color, Name Color, Prefix)

Nicknames

Proxy-wide staff chat

Chat Filter

Welcome messaging

stafflist

discord intergration (Still in dev, missing topic updater and webhook)


| Command        | Argument       | Permission|
| ------------- |:----------------------------------:| -----:|
| /ban          | [name] [reason]| msessentials.command.admin.ban|
| /kick      | [name] [reason]      |   msessentials.command.admin.kick |
| /broadcast| [message]      |    msessentials.command.admin.broadcast |
|/staffchat | | msessentials.command.admin.staffchat|
|/google|[search entry]|msessentials.command.google|
|/sendgoogle|[player] [search entry]|msessentials.command.admin.sendgoogle|
|/nick|[nickname]|msessentials.command.nickname, msessentials.command.nickname.color, msessentials.command.nickname.magic|
|/playerinfo|[playername]|msessentials.command.admin.playerinfo|
|/socialspy|none|msessentials.socialspy or msessentials.socialspy.onjoin|
|/message|[name] [message] | msessentials.command.message|
|/reply|[message]|msessentials.command.message|

Luckperms Support
-
For setting name color
    
    /lp user/group (name/groupname) meta set name-color (Color code "&#")
    
    ex: /lp user stg_allen meta set name-color &4    <- Set's my name color to red

For setting Chat Color

    /lp user/group (name/groupname) meta set chat-color (Color code "&#")
    
    ex: /lp user STG_Allen meta set chat-color &4   <- Set's my chat color to red
