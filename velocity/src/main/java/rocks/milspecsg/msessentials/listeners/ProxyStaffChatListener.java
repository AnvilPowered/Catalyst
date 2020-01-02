package rocks.milspecsg.msessentials.listeners;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import rocks.milspecsg.msessentials.events.ProxyStaffChatEvent;
import rocks.milspecsg.msessentials.misc.LuckpermsHook;
import rocks.milspecsg.msessentials.misc.PluginMessages;
import rocks.milspecsg.msessentials.misc.PluginPermissions;

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
