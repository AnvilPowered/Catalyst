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

package rocks.milspecsg.msessentials.velocity.listeners;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.proxy.ProxyServer;
import rocks.milspecsg.msessentials.velocity.events.ProxyStaffChatEvent;
import rocks.milspecsg.msessentials.velocity.messages.PluginMessages;
import rocks.milspecsg.msessentials.velocity.utils.PluginPermissions;

public class ProxyStaffChatListener {

    @Inject
    private ProxyServer proxyServer;

    @Inject
    private PluginMessages pluginmessages;

    @Subscribe
    public void staffChatEvent(ProxyStaffChatEvent event) {
        proxyServer.getAllPlayers().stream().filter(target -> target
                .hasPermission(PluginPermissions.STAFFCHAT))
                .forEach(target -> target.sendMessage(pluginmessages.staffChatMessageFormatted(event.getMessage(), event.getSender().getUsername())));
    }
}
