package rocks.milspecsg.msessentials.commands;

import com.google.inject.Inject;
import com.velocitypowered.api.command.Command;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.text.TextComponent;
import org.checkerframework.checker.nullness.qual.NonNull;
import rocks.milspecsg.msessentials.api.member.MemberManager;
import rocks.milspecsg.msessentials.misc.PluginMessages;
import rocks.milspecsg.msessentials.misc.PluginPermissions;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BanCommand implements Command {

    @Inject
    private ProxyServer proxyServer;

    @Inject
    private PluginMessages pluginMessages;

    @Inject
    private MemberManager<TextComponent> memberManager;

    @Override
    public void execute(CommandSource source, @NonNull String[] args) {
        if (!source.hasPermission(PluginPermissions.BAN)) {
            source.sendMessage(pluginMessages.noPermission);
            return;
        }

        if (args.length == 0) {
            source.sendMessage(pluginMessages.notEnoughArgs);
            return;
        }
        String username = args[0];

        if (proxyServer.getPlayer(username).filter(p -> p.hasPermission(PluginPermissions.BAN_EXEMPT)).isPresent()) {
            source.sendMessage(pluginMessages.banExempt);
            return;
        }
        String banReason = args.length > 1 ? String.join(" ", args).replace(username, "") : "The ban hammer has spoken!";
        memberManager.ban(username, banReason).thenAcceptAsync(source::sendMessage);
    }

    @Override
    public List<String> suggest(CommandSource src, String[] args) {
        if (args.length == 1) {
            return proxyServer.matchPlayer(args[0]).stream().map(Player::getUsername).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
