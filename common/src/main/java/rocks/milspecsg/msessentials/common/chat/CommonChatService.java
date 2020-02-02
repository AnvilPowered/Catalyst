package rocks.milspecsg.msessentials.common.chat;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import rocks.milspecsg.msessentials.api.chat.ChatService;
import rocks.milspecsg.msessentials.api.data.config.Channel;
import rocks.milspecsg.msessentials.api.data.key.MSEssentialsKeys;
import rocks.milspecsg.msessentials.api.member.MemberManager;
import rocks.milspecsg.msrepository.api.data.key.Key;
import rocks.milspecsg.msrepository.api.data.registry.Registry;
import rocks.milspecsg.msrepository.api.util.StringResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Singleton
public class CommonChatService<TString, TCommandSource> implements ChatService<TString> {

    @Inject
    Registry registry;

    @Inject
    MemberManager<TString> memberManager;

    @Inject
    StringResult<TString, TCommandSource> stringResult;

    Map<UUID, String> channelMap = new HashMap<>();

    @Override
    public void switchChannel(UUID userUUID, String channelId) {
        channelMap.put(userUUID, channelId);
    }

    @Override
    public String getChannelId(UUID userUUID) {
        String channelId = channelMap.get(userUUID);
        if (channelId == null) {
            return registry.getOrDefault(MSEssentialsKeys.CHAT_DEFAULT_CHANNEL);
        }
        return channelId;
    }

    @Override
    public Optional<Channel> getChannel(String channelId) {
        return registry.get(MSEssentialsKeys.CHAT_CHANNELS).flatMap(channels ->
            channels.stream()
                .filter(c -> c.id.equals(channelId))
                .findAny()
        );
    }

    @Override
    public Optional<String> getChannelPrefix(String channelId) {
        return getChannel(channelId).map(c -> c.prefix);
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
                finalName = optionalMember.get().getNickName();
            } else {
                finalName = nameColor + finalName;
            }
            System.out.println(userName + message + finalName);
            return stringResult
                .builder()
                .append(stringResult.deserialize(replacePlaceholders(message, prefix, finalName, hasChatColorPermission, suffix, serverName, channelPrefix, MSEssentialsKeys.PROXY_CHAT_FORMAT_MESSAGE)))
                .onHoverShowText(stringResult.deserialize(replacePlaceholders(message, prefix, finalName, hasChatColorPermission, suffix, serverName, channelPrefix, MSEssentialsKeys.PROXY_CHAT_FORMAT_HOVER)))
                .onClickSuggestCommand(replacePlaceholders(message, prefix, userName, hasChatColorPermission, suffix, finalName, channelPrefix, MSEssentialsKeys.PROXY_CHAT_FORMAT_CLICK_COMMAND))
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
}
