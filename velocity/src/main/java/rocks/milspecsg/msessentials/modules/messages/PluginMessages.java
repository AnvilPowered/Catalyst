/*
 *     MSEssentials - MilSpecSG
 *     Copyright (C) 2020 STG_Allen
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package rocks.milspecsg.msessentials.modules.messages;

import net.kyori.text.TextComponent;
import net.kyori.text.event.ClickEvent;
import net.kyori.text.event.HoverEvent;
import net.kyori.text.serializer.legacy.LegacyComponentSerializer;
import rocks.milspecsg.msessentials.MSEssentialsPluginInfo;

public class PluginMessages {

    public TextComponent broadcastPrefix = TextComponent.builder()
            .append(legacyColor("&a[Broadcast] "))
            .build();

    public TextComponent notEnoughArgs = TextComponent.builder()
            .append(MSEssentialsPluginInfo.pluginPrefix)
            .append(legacyColor("&4not enough arguments!"))
            .build();

    public TextComponent noPermission = TextComponent.builder()
            .append(MSEssentialsPluginInfo.pluginPrefix)
            .append(legacyColor("&4insufficient permissions!"))
            .build();

    public TextComponent noNickColorPermission = TextComponent.builder()
            .append(MSEssentialsPluginInfo.pluginPrefix)
            .append(legacyColor("&4You do not have permissions for a colored nickname!"))
            .build();

    public TextComponent currentServer(String name, String serverName) {
        return TextComponent.builder()
                .append(MSEssentialsPluginInfo.pluginPrefix)
                .append(legacyColor(name + " is connected to " + "&a" + serverName))
                .build();
    }

    public TextComponent currentlyMuted = TextComponent.builder()
            .append(MSEssentialsPluginInfo.pluginPrefix)
            .append(legacyColor("&4You are muted!"))
            .build();

    public TextComponent muteExempt = TextComponent.builder()
            .append(MSEssentialsPluginInfo.pluginPrefix)
            .append(legacyColor("&eThis user is exempt from being muted."))
            .build();

    public TextComponent banExempt = TextComponent.builder()
            .append(MSEssentialsPluginInfo.pluginPrefix)
            .append(legacyColor("&eThis user is exempt from being banned."))
            .build();

    public TextComponent kickExempt = TextComponent.builder()
            .append(MSEssentialsPluginInfo.pluginPrefix)
            .append(legacyColor("&eThis user is exempt from being kicked"))
            .build();

    public TextComponent socialSpyToggle(boolean b) {
        if (b) {
            return TextComponent.builder()
                    .append(MSEssentialsPluginInfo.pluginPrefix)
                    .append(legacyColor("&aSocialSpy enabled."))
                    .build();
        } else {
            return TextComponent.builder()
                    .append(MSEssentialsPluginInfo.pluginPrefix)
                    .append(legacyColor("&aSocialSpy disabled."))
                    .build();
        }
    }

    public TextComponent staffChatToggle(boolean b) {
        if (b) {
            return TextComponent.builder()
                    .append(MSEssentialsPluginInfo.pluginPrefix)
                    .append(legacyColor("StaffChat &aenabled."))
                    .build();
        } else {
            return TextComponent.builder()
                    .append(MSEssentialsPluginInfo.pluginPrefix)
                    .append(legacyColor("StaffChat &4disabled."))
                    .build();
        }
    }

    public TextComponent staffChatMessageFormatted(TextComponent message, String username) {
        return TextComponent.builder()
                .append(legacyColor("&b[STAFF]&5 " + username + " : ").append(message))
                .build();
    }

    public TextComponent staffChatMessageFormattedConsole(TextComponent message) {
        return TextComponent.builder()
                .append(legacyColor("&b[STAFF]&5 CONSOLE : ").append(message))
                .build();
    }

    public TextComponent teleportRequestSent(String targetName) {
        return TextComponent.builder()
                .append(legacyColor("&aRequested to teleport to "))
                .append(legacyColor("&e").append(TextComponent.of(targetName)))
                .build();
    }

    public TextComponent teleportRequestRecieved(String requesterName) {
        return TextComponent.builder()
                .append(legacyColor("&a" + requesterName + " requests to teleport to you."))
                .hoverEvent(HoverEvent.showText(TextComponent.of("Click here to accept")))
                .clickEvent(ClickEvent.runCommand("/tpaccept"))
                .build();
    }

    public TextComponent teleportToSelf() {
        return TextComponent.builder()
                .append(legacyColor("&4You cannot teleport to yourself!"))
                .build();
    }

    public TextComponent incompatibleServerVersion = TextComponent.builder()
            .append(MSEssentialsPluginInfo.pluginPrefix)
            .append(legacyColor("&eThe server you are attempting to connect to is running a different Minecraft version!"))
            .build();

    public TextComponent existingSwear (String existing) {
        return TextComponent.builder()
                .append(MSEssentialsPluginInfo.pluginPrefix)
                .append(legacyColor("&athe swear &e" + existing + "&a is already in the swears list."))
                .build();
    }

    public TextComponent existingException (String existing) {
        return TextComponent.builder()
                .append(MSEssentialsPluginInfo.pluginPrefix)
                .append(legacyColor("&athe exception &e" + existing + "&a is already in the exceptions list."))
                .build();
    }

    public TextComponent missingSwear (String missing) {
        return TextComponent.builder()
                .append(MSEssentialsPluginInfo.pluginPrefix)
                .append(legacyColor("&e" + missing + " &ais not in the swears list."))
                .build();
    }

    public TextComponent missingException (String missing) {
        return TextComponent.builder()
                .append(MSEssentialsPluginInfo.pluginPrefix)
                .append(legacyColor("&e" + missing + " &ais not in the exceptions list."))
                .build();
    }

    public TextComponent addSwear (String add) {
        return TextComponent.builder()
                .append(MSEssentialsPluginInfo.pluginPrefix)
                .append(legacyColor("&e" + add + " &awas added to the swears list."))
                .build();
    }

    public TextComponent addException (String add) {
        return TextComponent.builder()
                .append(MSEssentialsPluginInfo.pluginPrefix)
                .append(legacyColor("&e" + add + " &awas added to the exceptions list."))
                .build();
    }

    public TextComponent removeSwear (String remove) {
        return TextComponent.builder()
                .append(MSEssentialsPluginInfo.pluginPrefix)
                .append(legacyColor("&e" + remove + " &awas removed from the swears list."))
                .build();
    }

    public TextComponent removeException (String remove) {
        return TextComponent.builder()
                .append(MSEssentialsPluginInfo.pluginPrefix)
                .append(legacyColor("&e" + remove + " &awas removed from the exceptions list."))
                .build();
    }

    public TextComponent legacyColor(String text) {
        return LegacyComponentSerializer.legacy().deserialize(text, '&');
    }

    public String removeColor (String in) {
      return in.replace("&0", "").replaceAll("&1", "").replaceAll("&2", "")
                .replaceAll("&3", "").replaceAll("&4", "").replaceAll("&5", "")
                .replaceAll("&6", "").replaceAll("&7", "").replaceAll("&8", "")
                .replaceAll("&a", "").replaceAll("&b", "").replaceAll("&c", "")
                .replaceAll("&d", "").replaceAll("&e", "").replaceAll("&f", "")
                .replaceAll("&k", "").replaceAll("&l", "").replaceAll("&m", "")
                .replaceAll("&n", "").replaceAll("&o", "").replaceAll("&r", "")
                .replaceAll("&k", "").replaceAll("&9", "");
    }
}
