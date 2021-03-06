/*
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

package org.anvilpowered.catalyst.velocity.service;

import com.google.inject.Inject;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.TextComponent;
import org.anvilpowered.catalyst.api.service.BroadcastService;

public class VelocityBroadcastService implements BroadcastService<TextComponent> {

    @Inject
    private ProxyServer proxyServer;

    @Override
    public void broadcast(TextComponent message) {
        if (message.toString().length() == 0) return;
        proxyServer.getAllPlayers().forEach(p -> p.sendMessage(Identity.nil(), message));
    }
}
