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

package rocks.milspecsg.msessentials.velocity.commands;

import com.google.inject.Inject;
import com.velocitypowered.api.command.Command;
import com.velocitypowered.api.command.CommandSource;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.spongepowered.api.entity.living.player.Player;
import rocks.milspecsg.msessentials.velocity.messages.PluginMessages;
import rocks.milspecsg.msessentials.velocity.utils.PluginPermissions;
import rocks.milspecsg.msessentials.velocity.utils.ProxyTeleportUtils;

public class TeleportAcceptCommand implements Command {

    @Inject
    private PluginMessages pluginMessages;

    @Inject
    private ProxyTeleportUtils proxyTeleportUtils;

    @Override
    public void execute(CommandSource source, @NonNull String[] args) {
        if (!source.hasPermission(PluginPermissions.TELEPORT_REQUEST)) {
            source.sendMessage(pluginMessages.noPermission);
            return;
        }

        if(source instanceof Player) {
            Player player = (Player) source;
            if(proxyTeleportUtils.teleportationMap.containsKey(player.getUniqueId())) {
                //TODO Implement teleport accepting
                proxyTeleportUtils.teleportationMap.remove(player.getUniqueId());


            }

        }
    }
}
