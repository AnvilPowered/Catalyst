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

package rocks.milspecsg.msessentials.api.chat;

import rocks.milspecsg.msessentials.api.data.config.Channel;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface ChatService<TString> {

    void switchChannel(UUID userUUID, String channelId);

    String getChannelId(UUID userUUID);

    Optional<Channel> getChannel(String channelId);

    Optional<String> getChannelPrefix(String channelId);

    CompletableFuture<TString> formatMessage(String prefix, String nameColor, String userName, String message, boolean hasChatColorPermission, String suffix, String serverName, String channelId, String channelPrefix);
}
