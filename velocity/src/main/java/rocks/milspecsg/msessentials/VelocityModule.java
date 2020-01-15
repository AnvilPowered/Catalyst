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

package rocks.milspecsg.msessentials;

import com.google.inject.*;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import net.kyori.text.TextComponent;
import rocks.milspecsg.msrepository.api.misc.*;
import rocks.milspecsg.msrepository.api.util.*;
import rocks.milspecsg.msrepository.common.misc.*;
import rocks.milspecsg.msrepository.velocity.util.*;

@SuppressWarnings({"unchecked", "UnstableApiUsage"})
public class VelocityModule extends CommonModule<
    TextComponent,
    Player,
    CommandSource> {

    @Override
    protected void configure() {
        super.configure();

        BindingExtensions be = new CommonBindingExtensions(binder());

        bind(new TypeLiteral<UserService<Player, Player>>() {
        }).to(VelocityUserService.class);

        bind(BasicPluginInfo.class).to(MSEssentialsPluginInfo.class);

        bind(new TypeLiteral<PluginInfo<TextComponent>>() {
        }).to(MSEssentialsPluginInfo.class);

        bind(new TypeLiteral<StringResult<TextComponent, CommandSource>>() {
        }).to(new TypeLiteral<VelocityStringResult>() {
        });

        bind(CurrentServerService.class).to(VelocityCurrentServerService.class);

        bind(KickService.class).to(VelocityKickService.class);
    }
}
