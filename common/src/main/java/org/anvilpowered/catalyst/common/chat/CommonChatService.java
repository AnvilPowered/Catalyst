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

package org.anvilpowered.catalyst.common.chat;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.anvilpowered.anvil.api.data.key.Key;
import org.anvilpowered.anvil.api.data.registry.Registry;
import org.anvilpowered.anvil.api.util.StringResult;
import org.anvilpowered.anvil.api.util.UserService;
import org.anvilpowered.catalyst.api.chat.ChatService;
import org.anvilpowered.catalyst.api.data.config.Channel;
import org.anvilpowered.catalyst.api.data.key.CatalystKeys;
import org.anvilpowered.catalyst.api.member.MemberManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Singleton
public class CommonChatService<
    TPlayer extends TCommandSource,
    TString,
    TCommandSource>
    implements ChatService<TString, TPlayer> {

    @Inject
    Registry registry;

    @Inject
    MemberManager<TString> memberManager;

    @Inject
    StringResult<TString, TCommandSource> stringResult;

    @Inject
    private UserService<TPlayer, TPlayer> userService;

    Map<UUID, String> channelMap = new HashMap<>();

    @Override
    public void switchChannel(UUID userUUID, String channelId) {
        channelMap.put(userUUID, channelId);
    }

    @Override
    public String getChannelIdForUser(UUID userUUID) {
        String channelId = channelMap.get(userUUID);
        if (channelId == null) {
            return registry.getOrDefault(CatalystKeys.CHAT_DEFAULT_CHANNEL);
        }
        return channelId;
    }

    @Override
    public Optional<Channel> getChannelFromId(String channelId) {
        return registry.get(CatalystKeys.CHAT_CHANNELS).flatMap(channels ->
            channels.stream()
                .filter(c -> c.id.equals(channelId))
                .findAny()
        );
    }

    @Override
    public Optional<String> getChannelPrefix(String channelId) {
        return getChannelFromId(channelId).map(c -> c.prefix);
    }

    @Override
    public int getChannelUserCount(String channelId) {
        return (int) userService.getOnlinePlayers()
            .stream()
            .filter(p -> getChannelIdForUser(userService.getUUID(p))
                .equals(channelId)).count();
    }

    @Override
    public TString getUsersInChannel(String channelId) {
        List<String> channelUsersList = userService.getOnlinePlayers()
            .stream()
            .filter(p -> getChannelIdForUser(userService.getUUID(p)).equals(channelId))
            .map(p -> userService.getUserName(p))
            .collect(Collectors.toList());

        return stringResult.builder()
            .green().append("------------------- ")
            .gold().append(channelId)
            .green().append(" --------------------\n")
            .append(String.join(", ", channelUsersList))
            .build();
    }

    @Override
    public CompletableFuture<Void> sendMessageToChannel(String channelId, TString message, Predicate<? super TPlayer> checkOverridePerm) {
        return CompletableFuture.runAsync(() -> userService.getOnlinePlayers().forEach(p -> {
            if (checkOverridePerm.test(p) || getChannelIdForUser(userService.getUUID(p)).equals(channelId)) {
                stringResult.send(message, p);
            }
        }));
    }

    @Override
    public CompletableFuture<Void> sendGlobalMessage(TString message) {
        return CompletableFuture.runAsync(() -> userService.getOnlinePlayers().forEach(p -> stringResult.send(message, p)));
    }

    @Override
    public CompletableFuture<TString> formatMessage(
        String prefix,
        String nameColor,
        String userName,
        String message,
        boolean hasChatColorPermission,
        String suffix,
        String serverName,
        String channelId,
        String channelPrefix
    ) {
        return memberManager.getPrimaryComponent().getOneForUser(userName).thenApplyAsync(optionalMember -> {
            if (!optionalMember.isPresent()) {
                return stringResult.fail("Couldn't find a user matching that name!");
            }

            String finalName = optionalMember.get().getUserName();
            if (optionalMember.get().getNickName() != null) {
                finalName = optionalMember.get().getNickName() + "&r";
            } else {
                finalName = nameColor + finalName + "&r";
            }
            return stringResult
                .builder()
                .append(stringResult.deserialize(replacePlaceholders(message, prefix, finalName, hasChatColorPermission, suffix, serverName, channelPrefix, CatalystKeys.PROXY_CHAT_FORMAT_MESSAGE)))
                .onHoverShowText(stringResult.deserialize(replacePlaceholders(message, prefix, finalName, hasChatColorPermission, suffix, serverName, channelPrefix, CatalystKeys.PROXY_CHAT_FORMAT_HOVER)))
                .onClickSuggestCommand(replacePlaceholders(message, prefix, userName, hasChatColorPermission, suffix, finalName, channelPrefix, CatalystKeys.PROXY_CHAT_FORMAT_CLICK_COMMAND))
                .build();
        });
    }

    private String replacePlaceholders(
        String rawMessage,
        String prefix,
        String userName,
        boolean hasChatColorPermission,
        String suffix,
        String serverName,
        String channelPrefix,
        Key<String> key
    ) {
        return registry.get(key)
            .orElseThrow(() -> new IllegalStateException("Missing chat formatting!"))
            .replace("%prefix%", prefix)
            .replace("%username%", userName)
            .replace("%suffix%", suffix)
            .replace("%server%", serverName)
            .replace("%channel%", channelPrefix)
            .replace("%message%", hasChatColorPermission ? rawMessage : stringResult.removeColor(rawMessage));
    }

    @Override
    public String getPlayerList() {
        return userService.getOnlinePlayers().stream().map(userService::getUserName).collect(Collectors.joining(", \n"));
    }

    @Override
    public TString list() {
        return stringResult.builder()
            .green().append("------------------- Online Players --------------------\n")
            .gray().append(getPlayerList())
            .build();
    }
}
