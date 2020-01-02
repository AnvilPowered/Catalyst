package rocks.milspecsg.msessentials;

import com.google.inject.Key;
import com.velocitypowered.api.event.Subscribe;
import com.google.inject.Injector;
import com.google.inject.TypeLiteral;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import rocks.milspecsg.msessentials.commands.MSEssentialsCommandManager;
import rocks.milspecsg.msessentials.events.ProxyStaffChatEvent;
import rocks.milspecsg.msessentials.listeners.ProxyChatListener;
import rocks.milspecsg.msessentials.listeners.ProxyJoinListener;
import rocks.milspecsg.msessentials.listeners.ProxyLeaveListener;
import rocks.milspecsg.msessentials.listeners.ProxyStaffChatListener;
import rocks.milspecsg.msessentials.modules.chatutils.ChatFilter;
import rocks.milspecsg.msrepository.CommonConfigurationModule;
import rocks.milspecsg.msrepository.api.config.ConfigurationService;

import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import java.io.File;
import java.nio.file.Paths;

@Plugin(
        id = MSEssentialsPluginInfo.id,
        name = MSEssentialsPluginInfo.name,
        version = MSEssentialsPluginInfo.version,
        authors = MSEssentialsPluginInfo.authors,
        description = MSEssentialsPluginInfo.description,
        url = MSEssentialsPluginInfo.url
)
public class MSEssentials {

    @Override
    public String toString() {
        return MSEssentialsPluginInfo.id;
    }

    @Inject
    Logger logger;

    @Inject
    private Injector velocityRootInjector;

    @Inject
    private ProxyServer proxyServer;


    public static ProxyServer server;

    public static MSEssentials plugin = null;
    private Injector injector = null;

    public static LuckPerms api;

    private boolean alreadyLoadedOnce = false;

    @Subscribe
    public void onInit(ProxyInitializeEvent event) throws LoginException {
        plugin = this;
        initServices();
        initCommands();
        initListeners();
        loadConfig();
        server = proxyServer;
    }

    public void initServices() {
        api = LuckPermsProvider.get();
        injector = velocityRootInjector.createChildInjector(new VelocityModule(), new MSEssentialsConfigurationModule());
    }

    private void initCommands() {
        if (!alreadyLoadedOnce) {
            injector.getInstance(Key.get(MSEssentialsCommandManager.class)).register(this);
            alreadyLoadedOnce = true;
        }
    }

    public static ProxyServer getServer() {
        return server;
    }

    private void initListeners() {
        logger.info("Injecting listeners");
        proxyServer.getEventManager().register(this, injector.getInstance(ProxyJoinListener.class));
        proxyServer.getEventManager().register(this, injector.getInstance(ProxyLeaveListener.class));
        proxyServer.getEventManager().register(this, injector.getInstance(ProxyChatListener.class));
        proxyServer.getEventManager().register(this, injector.getInstance(ProxyStaffChatListener.class));
    }

    public void loadConfig() {
        logger.info("Loading config");
        injector.getInstance(ConfigurationService.class).load(this);
    }

    private static class MSEssentialsConfigurationModule extends CommonConfigurationModule {
        @Override
        protected void configure() {
            super.configure();
            File configFilesLocation = Paths.get("plugins/" + MSEssentialsPluginInfo.id).toFile();
            if (!configFilesLocation.exists()) {
                if (!configFilesLocation.mkdirs()) {
                    throw new IllegalStateException("Unable to create config directory");
                }
            }
            bind(new TypeLiteral<ConfigurationLoader<CommentedConfigurationNode>>() {
            }).toInstance(HoconConfigurationLoader.builder().setPath(Paths.get(configFilesLocation + "/msessentials.conf")).build());
            bind(ChatFilter.class);

        }
    }
}
