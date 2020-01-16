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

package rocks.milspecsg.msessentials.velocity.module;

import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import net.kyori.text.TextComponent;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import rocks.milspecsg.msessentials.api.model.member.Member;
import rocks.milspecsg.msessentials.common.module.CommonModule;
import rocks.milspecsg.msessentials.velocity.plugin.MSEssentialsPluginInfo;
import rocks.milspecsg.msrepository.api.util.BasicPluginInfo;
import rocks.milspecsg.msrepository.api.util.PluginInfo;

import java.io.File;
import java.nio.file.Paths;

@Singleton
@SuppressWarnings({"unchecked", "UnstableApiUsage"})
public class VelocityModule extends CommonModule<
    TextComponent,
    Player,
    Player,
    CommandSource> {

    @Override
    protected void configure() {
        super.configure();

        File configFilesLocation = Paths.get("plugins/" + MSEssentialsPluginInfo.id).toFile();
        System.out.println(Member.class.getPackage().toString());
        if (!configFilesLocation.exists()) {
            if (!configFilesLocation.mkdirs()) {
                throw new IllegalStateException("Unable to create config directory");
            }
        }

        bind(new TypeLiteral<ConfigurationLoader<CommentedConfigurationNode>>() {
        }).toInstance(HoconConfigurationLoader.builder().setPath(Paths.get(configFilesLocation + "/msessentials.conf")).build());

        bind(BasicPluginInfo.class).to(MSEssentialsPluginInfo.class);

        bind(new TypeLiteral<PluginInfo<TextComponent>>() {
        }).to(MSEssentialsPluginInfo.class);
    }
}
