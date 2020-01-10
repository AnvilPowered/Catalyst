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

package rocks.milspecsg.msessentials.listeners;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyPingEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.ServerPing;
import net.kyori.text.TextComponent;
import rocks.milspecsg.msessentials.api.config.ConfigKeys;
import rocks.milspecsg.msrepository.api.config.ConfigurationService;

import java.util.ArrayList;
import java.util.List;

public class PingEventListener {

    @Inject
    private ProxyServer proxyServer;

    @Inject
    private ConfigurationService configService;

    @Subscribe
    public void onProxyPingEvent(ProxyPingEvent event) {
        ServerPing.Builder builder = ServerPing.builder();
        ServerPing serverPing = event.getPing();

        int playerCount = proxyServer.getPlayerCount();
        serverPing.getFavicon().ifPresent(builder::favicon);
        builder.version(serverPing.getVersion());
        builder.onlinePlayers(playerCount);
        builder.description(TextComponent.of(configService.getConfigString(ConfigKeys.MOTD)));
        builder.maximumPlayers(proxyServer.getConfiguration().getShowMaxPlayers());

        event.setPing(builder.build());
    }
}
