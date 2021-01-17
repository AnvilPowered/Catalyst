/*
 * Catalyst - AnvilPowered
 *   Copyright (C) 2020
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with this program.  If not, see https://www.gnu.org/licenses/.
 */

package org.anvilpowered.catalyst.api.registry;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.List;

@ConfigSerializable
public class ChatChannel {

    @Setting("id")
    public String id;

    @Setting("aliases")
    public List<String> aliases;

    @Setting("prefix")
    public String prefix;

    @Setting("servers")
    public List<String> servers;

    @Setting(value = "alwaysVisible", comment = "Whether members of this channel will always receive messages from this channel even when they are not in it. ")
    public boolean alwaysVisible;

    @Setting(value = "discordChannel", comment = "Discord channel id that chat should be relayed to")
    public String discordChannel;
}