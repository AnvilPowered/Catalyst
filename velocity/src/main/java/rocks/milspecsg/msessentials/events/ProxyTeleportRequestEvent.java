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

package rocks.milspecsg.msessentials.events;

import com.velocitypowered.api.proxy.Player;

public class ProxyTeleportRequestEvent {

    private final Player sourcePlayer;
    private final Player targetPlayer;

    public Player getSourcePlayer() {
        return sourcePlayer;
    }

    public Player getTargetPlayer() {
        return targetPlayer;
    }

    public ProxyTeleportRequestEvent(Player sourcePlayer, Player targetPlayer) {
        this.sourcePlayer = sourcePlayer;
        this.targetPlayer = targetPlayer;
    }
}
